package com.hillel.online_shop.dto.user;

import com.hillel.online_shop.entity.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String login;

    private String email;

    private BigDecimal balance;

    private User.Role role;

}
