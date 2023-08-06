package com.hillel.online_shop.controller;

import com.hillel.online_shop.dto.user.UserRequestDTO;
import com.hillel.online_shop.dto.user.UserResponseDTO;
import com.hillel.online_shop.entity.User;
import com.hillel.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop/users")
public class UserController {
    private final UserService<UserRequestDTO, UserResponseDTO> userService;

    @GetMapping("/get-by-login/{login}")
    public UserResponseDTO getByLogin(@PathVariable String login) {
        return userService.findByLogin(login);
    }

    @PostMapping("/create")
    public Long create(@Validated @RequestBody UserRequestDTO userRequestDTO) {
        User.Role role = userRequestDTO.getRole();
        validateRole(role);
        return userService.create(userRequestDTO);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @Validated @RequestBody UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getRole() != null) {
            throw new IllegalArgumentException("field role must be null");
        }

        User.Role role = userService.findById(id).getRole();
        validateRole(role);

        userService.findById(id);
        userRequestDTO.setId(id);
        userService.update(userRequestDTO);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id) {
        User.Role role = userService.findById(id).getRole();
        validateRole(role);

        userService.delete(id);
    }

    @GetMapping("/get/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/get-all")
    public List<UserResponseDTO> getAllUsers() {
        return userService.findAll();
    }

    @PutMapping("/make-user-admin/{userId}")
    public void makeUserAdmin(@PathVariable Long userId) {
        validateSuperAdminRole();

        if (!userService.findById(userId).getRole().equals(User.Role.ROLE_USER)) {
            throw new IllegalArgumentException("User role must be ROLE_USER");
        }

        userService.makeUserAdmin(userId);
    }

    @PutMapping("/block-user/{userId}")
    public void blockUser(@PathVariable Long userId) {
        User.Role role = userService.findById(userId).getRole();
        validateRole(role);
        userService.block(userId);
    }

    @PutMapping("/unblock-user/{userId}")
    public void unblockUser(@PathVariable Long userId) {
        User.Role role = userService.findById(userId).getRole();
        validateRole(role);
        userService.unblock(userId);
    }

    private void validateSuperAdminRole() {
        UserResponseDTO user = userService.findById(getCurrentUserId());
        if (!user.getRole().equals(User.Role.ROLE_SUPER_ADMIN)) {
            throw new AccessDeniedException("You are not authorized to perform this action");
        }
    }

    private void validateRole(User.Role role) {
        if (role.equals(User.Role.ROLE_ADMIN)) {
            validateSuperAdminRole();
        } else if (role.equals(User.Role.ROLE_SUPER_ADMIN)) {
            throw new AccessDeniedException("You are not authorized to perform this action");
        }
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByLogin(auth.getName()).getId();
    }
}
