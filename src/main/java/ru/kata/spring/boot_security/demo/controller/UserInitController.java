package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/")
public class UserInitController {

    private final UserService userService;


    public UserInitController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String getCurrentUserInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.getUserByEmail(user.getEmail()));
        return "show";
    }

    @GetMapping("/admin/users")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.getUserByEmail(user.getEmail()));

        return "index";
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }
}