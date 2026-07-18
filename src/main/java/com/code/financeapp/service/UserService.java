package com.code.financeapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.financeapp.model.User;
import com.code.financeapp.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    // Ya no inyectamos PasswordEncoder
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // CREAR USUARIO (sin encriptar)
    public User addUser(User user) {
        user.setId(null);
        // Guardamos la contraseña directamente
        return userRepository.save(user);
    }
    
    // ACTUALIZAR USUARIO (sin encriptar)
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        User existingUser = existingUserOpt.get();
        existingUser.setPassword(updatedUser.getPassword());
        return userRepository.save(existingUser);
    }
    
    // CAMBIO DE CONTRASEÑA (sin encriptar)
    public User changePassword(Long id, String currentPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        User user = userOpt.get();
        
        // Comparación directa (texto plano)
        if (!currentPassword.equals(user.getPassword())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }
        
        if (newPassword == null || newPassword.length() < 5) {
            throw new RuntimeException("La nueva contraseña debe tener al menos 5 caracteres");
        }
        
        user.setPassword(newPassword); // Guardamos sin encriptar
        return userRepository.save(user);
    }
}