package ru.kata.spring.boot_security.demo.entity;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public User() {}


    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }

    public List<String> getRolesName() {
        List<Role> roles = getRoles();
        List<String> resultRole = new ArrayList<>();
        for(Role role : roles) {
            resultRole.add(
                    role.getName().replaceFirst("ROLE_", "")) ;
        }
        return resultRole;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() { return email;}

}

