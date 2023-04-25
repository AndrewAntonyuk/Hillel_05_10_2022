package com.hillel.task29springsecurity.service;

import com.hillel.task29springsecurity.dto.UserBaseDto;
import com.hillel.task29springsecurity.dto.UserDto;

import java.util.List;

public interface UserService {
    Long create(UserDto userDto);

    void delete(Long id);

    UserBaseDto getById(Long id);

    List<UserBaseDto> getAll();
}
