package com.hillel.task29springsecurity.controller;

import com.hillel.task29springsecurity.dto.UserBaseDto;
import com.hillel.task29springsecurity.dto.UserDto;
import com.hillel.task29springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserBaseDto> getAll(){
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserBaseDto getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping
    public Long create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PostMapping("/user/register")
    public Long createRegister(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @DeleteMapping("/user/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }
}
