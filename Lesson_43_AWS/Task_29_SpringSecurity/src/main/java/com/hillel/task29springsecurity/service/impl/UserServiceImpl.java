package com.hillel.task29springsecurity.service.impl;

import com.hillel.task29springsecurity.dto.UserBaseDto;
import com.hillel.task29springsecurity.dto.UserDto;
import com.hillel.task29springsecurity.entity.User;
import com.hillel.task29springsecurity.exception.NoSuchUserException;
import com.hillel.task29springsecurity.exception.UserAlreadyExistException;
import com.hillel.task29springsecurity.repository.UserRepository;
import com.hillel.task29springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = findUserByName(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .build();
    }

    @Transactional
    @Override
    public Long create(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        if (userRepository.existsByName(userDto.getName())) {
            throw new UserAlreadyExistException("User with name " + userDto.getName() + " already exist");
        }

        return userRepository.save(modelMapper.map(userDto, User.class))
                .getId();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.delete(findUserById(id));
    }

    @Override
    public UserBaseDto getById(Long id) {
        return modelMapper.map(
                findUserById(id), UserBaseDto.class);
    }

    @Override
    public List<UserBaseDto> getAll() {
        List<User> users = (List<User>) userRepository.findAll();

        return users.stream()
                .map(usr -> modelMapper.map(usr, UserBaseDto.class))
                .collect(Collectors.toList());
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException("Can't find user with id " + id));
    }

    private User findUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new NoSuchUserException("Can't find user with name " + name));
    }
}
