package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);

    @Query("select distinct u from User u left join fetch u.roles")
    List<User> getAllUsers();

    User findByEmail(String email);
}
