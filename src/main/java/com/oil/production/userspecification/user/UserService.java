package com.oil.production.userspecification.user;

import java.util.List;

public interface UserService {


    UserDto save(UserDto userDto);

    UserDto update(UserDto userDto, Long id);

    void delete(Long id);

    List<UserDto> filteringBy(UserRequest userRequest);

}
