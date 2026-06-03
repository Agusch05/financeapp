package com.code.financeapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.financeapp.model.User;
import com.code.financeapp.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // CREAR USUARIO (encripta la contraseña)
    public User addUser(User user) {
        user.setId(null);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
    
    // ACTUALIZAR USUARIO (encripta la nueva contraseña)
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        User existingUser = existingUserOpt.get();
        String encodedPassword = passwordEncoder.encode(updatedUser.getPassword());
        existingUser.setPassword(encodedPassword);
        return userRepository.save(existingUser);
    }
    
    // CAMBIO DE CONTRASEÑA SEGURO
    public User changePassword(Long id, String currentPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        User user = userOpt.get();
        
        // Verificar contraseña actual usando BCrypt
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }
        
        if (newPassword == null || newPassword.length() < 5) {
            throw new RuntimeException("La nueva contraseña debe tener al menos 5 caracteres");
        }
        
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        return userRepository.save(user);
    }
}