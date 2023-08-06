package com.hillel.online_shop.service.impl;

import com.hillel.online_shop.dto.user.UserRequestDTO;
import com.hillel.online_shop.dto.user.UserResponseDTO;
import com.hillel.online_shop.entity.User;
import com.hillel.online_shop.exception.UserNotFoundException;
import com.hillel.online_shop.repository.UserRepository;
import com.hillel.online_shop.service.CartService;
import com.hillel.online_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService<UserRequestDTO, UserResponseDTO> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var byLogin = userRepository.findByLogin(username)
                .orElseThrow(() -> new IllegalArgumentException("invalid login"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(byLogin.getLogin())
                .password(byLogin.getPassword())
                .authorities(byLogin.getRole().name())
                .build();
    }

    @Override
    public UserResponseDTO findByLogin(String login) {
        var byLogin = userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("invalid login"));
        return modelMapper.map(byLogin, UserResponseDTO.class);
    }

    @Override
    @Transactional
    public Long create(UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getId() != null) {
            throw new IllegalArgumentException("field \"id\" must be null");
        }
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User user = userRepository.save(modelMapper.map(userRequestDTO, User.class));
        createCart(user);

        return user.getId();
    }

    @Override
    public void update(UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getPassword() != null) {
            userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        } // TODO: 29.04.23 if password is blank
        safeFill(userRequestDTO);
        userRepository.save(modelMapper.map(userRequestDTO, User.class));
    }

    @Override
    public void block(long id) {
        User user = getById(id);
        user.setBlocked(true);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = getById(id);
        cartService.delete(user.getCart().getId());
        userRepository.deleteById(id);
    }

    @Override
    public void unblock(long id) {
        User user = getById(id);
        user.setBlocked(false);
        userRepository.save(user);
    }

    @Override
    public void makeUserAdmin(long id) {
        User user = getById(id);
        user.setRole(User.Role.ROLE_ADMIN);
        userRepository.save(user);
    }

    @Override
    public UserResponseDTO findById(long id) {
        return modelMapper.map(getById(id), UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    private User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id " + id + " not found!"));
    }

    private void createCart(User user) {
        cartService.create(user.getId());
    }

    private void safeFill(UserRequestDTO userRequestDTO) {
        User user = getById(userRequestDTO.getId());

        if (userRequestDTO.getFirstName() == null) {
            userRequestDTO.setFirstName(user.getFirstName());
        }
        if (userRequestDTO.getLastName() == null) {
            userRequestDTO.setLastName(user.getLastName());
        }
        if (userRequestDTO.getLogin() == null) {
            userRequestDTO.setLogin(user.getLogin());
        }
        if (userRequestDTO.getPassword() == null) {
            userRequestDTO.setPassword(user.getPassword());
        }
        if (userRequestDTO.getAge() == null) {
            userRequestDTO.setAge(user.getAge());
        }
        if (userRequestDTO.getEmail() == null) {
            userRequestDTO.setEmail(user.getEmail());
        }
        if (userRequestDTO.getBalance() == null) {
            userRequestDTO.setBalance(user.getBalance());
        }

        userRequestDTO.setRole(user.getRole());
    }
}