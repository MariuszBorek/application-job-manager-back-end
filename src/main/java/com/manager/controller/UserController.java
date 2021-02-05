package com.manager.controller;

import com.manager.dto.Message;
import com.manager.model.*;
import com.manager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/create")
    public Users addUser(@RequestBody final Users user) {
        return userService.addUser(user);
    }

    @GetMapping("/users/login/{email}/{password}")
    public Users getUser(@PathVariable("email") final String email,
                         @PathVariable("password") final String password) {
        return userService.getUser(email, password);
    }

    @GetMapping(produces = "application/json")
    @RequestMapping( "/validateLogin" )
    public Message validateLogin() {
        return new Message("User successfully authenticated");
    }
}
