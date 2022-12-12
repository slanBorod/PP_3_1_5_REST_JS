package ru.kata.spring.boot_security.demo;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import javax.annotation.PostConstruct;
import java.util.ArrayList;


@Component
public class Init {
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public Init(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");

        var userRoles = new ArrayList<Role>();
        var adminRoles = new ArrayList<Role>();

        userRoles.add(roleUser);
        adminRoles.add(roleAdmin);

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);

        // Пароль - имя юзера
        User admin = new User("admin", "admin@mail.ru", "$2a$12$83mK/oHMHQpBwmO9lABdfe85zWw3HZFZOVWyqhMDKjJj93jdcXnJq");
        User user = new User("user", "user@mail.ru", "$2a$12$nkbXmHrMUMPmmmYvd6EsA.UNo6.HNSTzKXNvNYl26QNda4wwcKbe6");

        user.setRoles(userRoles);
        admin.setRoles(adminRoles);

        userRepository.save(user);
        userRepository.save(admin);
    }
}