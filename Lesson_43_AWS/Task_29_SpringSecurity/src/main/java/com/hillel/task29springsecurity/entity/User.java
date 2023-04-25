package com.hillel.task29springsecurity.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_USER, ROLE_ADMIN, ROLE_ANONYMOUS
    }
}
