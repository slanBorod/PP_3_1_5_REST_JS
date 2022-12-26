package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUserById(Long id);

    List<User> getAllUsers();

    void saveUser(User user);

    void removeUser(Long id);

    void update(User user);

    @Transactional
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    User getUserByEmail(String email);
}
