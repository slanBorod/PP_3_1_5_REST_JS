package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user) {
        if (user.getRoles().isEmpty()) {
            user.setRoles(Collections.singletonList(roleRepository.findRoleByName("ROLE_USER")));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getAuthorities());
    }

    public User getUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException(String.format("User with email '%s' not found", email));
        }
    }

}
