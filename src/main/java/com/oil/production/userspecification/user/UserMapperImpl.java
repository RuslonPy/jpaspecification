package com.oil.production.userspecification.user;

import com.oil.production.userspecification.dto.*;
import com.oil.production.userspecification.entities.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapperImpl implements UserMapper{

    @Override
    public UserDto toUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setRoleDtos(user.getRoles().stream().map(this::toRoleDto).collect(Collectors.toSet()));
        userDto.setDepartmentDto(toDepartmentDto(user.getDepartment()));
        userDto.setAddressDto(toAddressDto(user.getAddress()));
        return userDto;
    }

    private AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setAddress(address.getAddress());
        addressDto.setDistrictDto(toDistrictDto(address.getDistrict()));
        return addressDto;
    }

    private DistrictDto toDistrictDto(District district) {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setId(district.getId());
        districtDto.setNameUz(district.getNameUz());
        districtDto.setNameEn(district.getNameEn());
        districtDto.setNameRu(district.getNameRu());
        districtDto.setDisOrder(district.getDisOrder());
        districtDto.setRegionDto(toRegionDto(district.getRegion()));
        return districtDto;
    }

    private RegionDto toRegionDto(Region region) {
        RegionDto regionDto = new RegionDto();
        regionDto.setId(region.getId());
        regionDto.setNameUz(region.getNameUz());
        regionDto.setNameEn(region.getNameEn());
        regionDto.setNameRu(region.getNameRu());
        return regionDto;
    }

    private DepartmentDto toDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setNameUz(department.getNameUz());
        departmentDto.setNameEn(department.getNameEn());
        departmentDto.setNameRu(department.getNameRu());
        departmentDto.setDepOrder(department.getDepOrder());
        if(department.getParentDepartment() != null)
            departmentDto.setParentDepartment(toDepartmentDto(department.getParentDepartment()));
        return departmentDto;
    }

    private RoleDto toRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setNameUz(role.getNameUz());
        roleDto.setNameEn(role.getNameEn());
        roleDto.setNameRu(role.getNameRu());
        roleDto.setRoleOrder(role.getRoleOrder());
        return roleDto;
    }

    @Override
    public User toUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMiddleName(userDto.getMiddleName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setDepartment(toDepartment(userDto.getDepartmentDto()));
        user.setRoles(toRoles(userDto.getRoleDtos()));
        user.setAddress(toAddress(userDto.getAddressDto()));
        return user;
    }

    @Override
    public Department toDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setNameUz(departmentDto.getNameUz());
        department.setNameEn(departmentDto.getNameEn());
        department.setNameRu(departmentDto.getNameRu());
        department.setDepOrder(departmentDto.getDepOrder());
        if (departmentDto.getParentDepartment() != null) {
            department.setParentDepartment(toDepartment(departmentDto.getParentDepartment()));
        }
        return department;
    }

    @Override
    public Set<Role> toRoles(Set<RoleDto> roleDtos) {
        Set<Role> roles = new HashSet<>();
        for (RoleDto roleDto : roleDtos) {
            Role role = new Role();
            role.setNameUz(roleDto.getNameUz());
            role.setNameEn(roleDto.getNameEn());
            role.setNameRu(roleDto.getNameRu());
            role.setRoleOrder(roleDto.getRoleOrder());
            roles.add(role);
        }
        return roles;
    }

    @Override
    public Address toAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setAddress(addressDto.getAddress());
        address.setDistrict(toDistrict(addressDto.getDistrictDto()));
        return address;
    }

    @Override
    public District toDistrict(DistrictDto districtDto) {
        District district = new District();
        district.setNameUz(districtDto.getNameUz());
        district.setNameEn(districtDto.getNameEn());
        district.setNameRu(districtDto.getNameRu());
        district.setDisOrder(districtDto.getDisOrder());
        district.setRegion(toRegion(districtDto.getRegionDto()));
        return district;
    }

    @Override
    public Region toRegion(RegionDto regionDto) {
        Region region = new Region();
        region.setNameUz(regionDto.getNameUz());
        region.setNameEn(regionDto.getNameEn());
        region.setNameRu(regionDto.getNameRu());
        return region;
    }
}
