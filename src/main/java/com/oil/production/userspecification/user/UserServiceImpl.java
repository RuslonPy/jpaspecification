package com.oil.production.userspecification.user;

import com.oil.production.userspecification.entities.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = mapper.toUser(userDto);
        userRepository.save(user);
        return userDto;
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        User updatedUser = mapper.toUser(userDto);
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
}
