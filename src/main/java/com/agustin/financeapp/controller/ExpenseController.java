package com.agustin.financeapp.controller;

import java.util.List;
import java.util.Optional;

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
    
    @GetMapping("/{id}")
    public Optional<Expense> getById(@PathVariable Long id) {
        return expenseService.getById(id);
    }

    @PostMapping
    public Expense create(@RequestBody Expense expense) {
        if (expense.getUser() != null && expense.getUser().getId() != null) {
            List<User> users = userService.getAllUsers();
            User existingUser = users.stream()
                .filter(u -> u.getId().equals(expense.getUser().getId()))
                .findFirst()
                .orElse(null);
            
            if (existingUser != null) {
                expense.setUser(existingUser);
            } else if (!users.isEmpty()) {
                expense.setUser(users.get(0));
            }
        }
        return expenseService.save(expense);
    }
    
    @PutMapping("/{id}")
    public Expense update(@PathVariable Long id, @RequestBody Expense expense) {
        expense.setId(id);
        return expenseService.save(expense);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        expenseService.deleteById(id);
    }
}