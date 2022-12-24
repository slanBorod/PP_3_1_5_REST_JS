package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.AppService;

@Controller
@RequestMapping("/")
public class MyRestController {

    private final AppService appService;



    public MyRestController(AppService appService) {
        this.appService = appService;
        ;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @GetMapping("/user")
    public String getCurrentUserInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", appService.getUserByEmail(user.getEmail()));
        return "show";
    }

    @GetMapping("/admin/users")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", appService.getUserByEmail(user.getEmail()));

        return "index";
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        appService.saveUser(user);
        return user;
    }
}