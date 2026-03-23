package com.agustin.financeapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.agustin.financeapp.model.Expense;
import com.agustin.financeapp.model.User;
import com.agustin.financeapp.service.ExpenseService;
import com.agustin.financeapp.service.UserService;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @GetMapping
    public List<Expense> getAll() {
        return expenseService.getAll();
    }

    @PostMapping
    public Expense create(@RequestBody Expense expense) {
        // Verificar si el usuario existe
        if (expense.getUser() != null && expense.getUser().getId() != null) {
            // Buscar el usuario existente en la base de datos
            List<User> users = userService.getAllUsers();
            User existingUser = users.stream()
                .filter(u -> u.getId().equals(expense.getUser().getId()))
                .findFirst()
                .orElse(null);
            
            if (existingUser != null) {
                expense.setUser(existingUser);
            } else {
                // Si no existe, crear un usuario por defecto o buscar el primero
                if (!users.isEmpty()) {
                    expense.setUser(users.get(0));
                } else {
                    // Si no hay usuarios, crear uno por defecto
                    User defaultUser = new User();
                    defaultUser.setName("Default");
                    defaultUser.setPassword("default");
                    User savedUser = userService.addUser(defaultUser);
                    expense.setUser(savedUser);
                }
            }
        }
        
        return expenseService.save(expense);
    }
}