package com.oil.production.userspecification.user;

import com.oil.production.userspecification.dto.AddressDto;
import com.oil.production.userspecification.dto.DepartmentDto;
import com.oil.production.userspecification.dto.RoleDto;
import com.oil.production.userspecification.entities.Address;
import com.oil.production.userspecification.entities.Department;
import com.oil.production.userspecification.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private String password;
    private DepartmentDto departmentDto;
    private Set<RoleDto> roleDtos;
    private AddressDto addressDto;

}
