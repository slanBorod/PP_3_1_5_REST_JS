package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.AppService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    private final AppService appService;

    @Autowired
    public UserRestController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> userInfoPage(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<>(appService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("admin/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = appService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//NoSuchUserException("There is no user with ID = " +
                    //id + "in Database");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("admin/users/{id}")
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        appService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("admin/users/{id}")
    public ResponseEntity<User> update(@RequestBody @Valid User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        appService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        appService.removeUser(id);
    }
}
