package com.hillel.task29springsecurity.dto;

import com.hillel.task29springsecurity.entity.User;
import lombok.Data;

@Data
public class UserBaseDto {
    private Long id;

    private String name;

    private User.Role role;
}
