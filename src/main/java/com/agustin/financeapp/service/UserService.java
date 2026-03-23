package com.agustin.financeapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agustin.financeapp.model.User;
import com.agustin.financeapp.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User addUser(User user) {
        // Asegurar que el ID es null para que JPA lo genere
        user.setId(null);
        return userRepository.save(user);
    }
}