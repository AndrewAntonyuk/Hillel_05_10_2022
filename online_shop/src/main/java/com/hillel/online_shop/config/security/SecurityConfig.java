package com.hillel.online_shop.config.security;

import com.hillel.online_shop.service.impl.UserServiceImpl;
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
public class SecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/shop/register/**").permitAll()
                                .requestMatchers("/shop/products/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                                .requestMatchers("/shop/users/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                                .requestMatchers("/shop/customer/**").hasAnyRole("USER")
                                .anyRequest()
                                .authenticated())
                .formLogin()
                .defaultSuccessUrl("/shop", true)
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
    public DaoAuthenticationProvider getDaoAuthenticationProvider(UserServiceImpl userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
