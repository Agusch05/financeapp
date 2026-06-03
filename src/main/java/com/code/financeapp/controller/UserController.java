package com.code.financeapp.controller; 

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.code.financeapp.dto.LoginRequest;
import com.code.financeapp.dto.PasswordChangeRequest;
import com.code.financeapp.model.User;
import com.code.financeapp.repository.UserRepository;
import com.code.financeapp.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
        
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByNickname(loginRequest.getName());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }
        // Devolvemos el usuario sin la contraseña
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/{id}/password")
    public User changePassword(@PathVariable Long id, @RequestBody PasswordChangeRequest request) {
        return userService.changePassword(id, request.getCurrentPassword(), request.getNewPassword());
    }
}