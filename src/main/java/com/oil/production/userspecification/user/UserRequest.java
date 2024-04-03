package com.oil.production.userspecification.user;

import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private String roleNameUz;
    private String roleNameEn;
    private String roleNameRu;
    private String address;
    private Long regionId;
    private Long districtId;
    private Long departmentId;

}
