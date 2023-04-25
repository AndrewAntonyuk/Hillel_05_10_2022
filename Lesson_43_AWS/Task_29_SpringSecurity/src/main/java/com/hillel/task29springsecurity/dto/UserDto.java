package com.hillel.task29springsecurity.dto;

import com.hillel.task29springsecurity.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String name;

    private String password;

    private User.Role role;
}
