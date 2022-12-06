package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);

    List<User> findAll();

    void save(User user);


    Optional<User> findById(Long userId);

    void deleteById(Long userId);
}
