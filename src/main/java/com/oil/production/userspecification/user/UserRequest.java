package com.oil.production.userspecification.user;

import com.oil.production.userspecification.entities.Address;
import com.oil.production.userspecification.entities.Department;
import com.oil.production.userspecification.entities.Role;
import lombok.Data;

@Data
public class UserRequest {

    // User details
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;

    // Role details
    private String roleNameUz;
    private String roleNameEn;
    private String roleNameRu;

    // Address details
    private String address;
    private Long regionId;
    private Long districtId;

    private Long departmentId;
}
