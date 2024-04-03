package com.oil.production.userspecification.user;

import com.oil.production.userspecification.dto.AddressDto;
import com.oil.production.userspecification.dto.DepartmentDto;
import com.oil.production.userspecification.dto.DistrictDto;
import com.oil.production.userspecification.dto.RoleDto;
import com.oil.production.userspecification.dto.RegionDto;
import com.oil.production.userspecification.entities.*;

import java.util.Set;

public interface UserMapper {
    UserDto toUserDTO(User user);

    Department toDepartment(DepartmentDto departmentDto);

    Set<Role> toRoles(Set<RoleDto> roles);

    User toUser(UserDto userDto);

    Address toAddress(AddressDto addressDto);

    Region toRegion(RegionDto regionDto);

    District toDistrict(DistrictDto districtDto);
}
