package com.agustin.financeapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.agustin.financeapp.model.User;
import com.agustin.financeapp.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
        
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("Creating user: " + user.getName());
        User savedUser = userService.addUser(user);
        System.out.println("User created with ID: " + savedUser.getId());
        return savedUser;
    }
}