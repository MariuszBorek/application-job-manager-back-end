package com.manager.controller;

import com.manager.model.Sheet;
import com.manager.model.User;
import com.manager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAllSheets() {
        return userService.findAllUsers();
    }

    @GetMapping("/login/{email}/{password}")
    public User getUser(@PathVariable("email") final String email,
                        @PathVariable("password") final String password) {
        return userService.getUser(email, password);
    }

    @PostMapping
    public User addUser(@RequestBody final User user) {
        return userService.addUser(user);
    }

}
