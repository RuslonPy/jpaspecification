package com.oil.production.userspecification.user;

import com.oil.production.userspecification.entities.Address;
import com.oil.production.userspecification.entities.Department;
import com.oil.production.userspecification.entities.Role;
import com.oil.production.userspecification.entities.User;
import com.oil.production.userspecification.repositories.AddressRepository;
import com.oil.production.userspecification.repositories.DepartmentRepository;
import com.oil.production.userspecification.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository, AddressRepository addressRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = mapToUser(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        User updatedUser = mapToUser(userDto);
        updatedUser.setId(existingUser.getId());
        userRepository.save(updatedUser);
        return userDto;
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        user.setUserState(User.State.DELETED);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> filteringBy(UserRequest userRequest) {

        UserSpecification userSpecification = new UserSpecification();
        List<User> users = userRepository.findAll(userSpecification.filter(userRequest));

        return users.stream()
                .map(mapper::toUserDTO)
                .toList();
    }

    private User mapToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMiddleName(userDto.getMiddleName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        Department department = departmentRepository.findById(userDto.getDepartment().getId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + userDto.getDepartment().getId()));
        user.setDepartment(department);

        Set<Role> roles = userDto.getName().stream()
                .map(role -> roleRepository.findById(role.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + role.getId())))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        Address address = addressRepository.findById(userDto.getAddress().getId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + userDto.getAddress().getId()));
        user.setAddress(address);

        return user;
    }
}
