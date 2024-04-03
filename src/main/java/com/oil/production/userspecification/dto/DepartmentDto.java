package com.oil.production.userspecification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {

    private Long id;
    private String nameUz;
    private String nameEn;
    private String nameRu;

    private Integer depOrder;

    private DepartmentDto parentDepartment;

}
