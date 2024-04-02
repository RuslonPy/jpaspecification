package com.oil.production.userspecification.user;

import com.oil.production.userspecification.entities.Address;
import com.oil.production.userspecification.entities.Department;
import com.oil.production.userspecification.entities.Role;
import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private Department department;
    private Role role;
    private Address address;

}
