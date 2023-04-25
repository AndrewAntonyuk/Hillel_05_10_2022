package com.hillel.task29springsecurity.config;

import com.hillel.task29springsecurity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/users/user/register").permitAll()
                                .requestMatchers("/products/get-all").permitAll()
                                .requestMatchers("/products/product-get/**").permitAll()
                                .requestMatchers("/ping").permitAll()
                                .requestMatchers("/users/**").hasAnyRole("ADMIN")
                                .requestMatchers("/products/**").hasAnyRole("ADMIN")
                                .anyRequest()
                                .authenticated())
                .formLogin()
                .defaultSuccessUrl("/ping", true)
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .logout();

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(UserServiceImpl userService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        authenticationProvider.setUserDetailsService(userService);

        return authenticationProvider;
    }
}
