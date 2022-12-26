package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    List<Role> listRoles();

    void saveRole(Role role);

    Collection<? extends GrantedAuthority> getAuthorities();
}
