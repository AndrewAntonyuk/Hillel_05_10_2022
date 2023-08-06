package com.hillel.online_shop.controller;

import com.hillel.online_shop.dto.user.UserRequestDTO;
import com.hillel.online_shop.dto.user.UserResponseDTO;
import com.hillel.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/register")
public class RegisterController {

    private final UserService<UserRequestDTO, UserResponseDTO> userService;

    @PostMapping
    public Long create(@Validated @RequestBody UserRequestDTO userRequestDTO) {
        return userService.create(userRequestDTO);
    }
}
