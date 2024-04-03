package com.oil.production.userspecification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Long id;
    private String address;
    private DistrictDto districtDto;
}
