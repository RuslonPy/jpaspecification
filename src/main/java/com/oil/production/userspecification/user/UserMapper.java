package com.oil.production.userspecification.user;

import com.oil.production.userspecification.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto toUserDTO(User user);

}
