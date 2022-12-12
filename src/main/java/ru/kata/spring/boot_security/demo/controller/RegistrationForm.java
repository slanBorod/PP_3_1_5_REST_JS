package ru.kata.spring.boot_security.demo.controller;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.entity.User;


@Data
public class RegistrationForm {
    private String username;
    private String email;
    private String password;
    private boolean enabled;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, email,
                passwordEncoder.encode(password));
    }
}
