package com.code.financeapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.financeapp.model.Expense;
import com.code.financeapp.repository.ExpenseRepository;

@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }
    
    public Optional<Expense> getById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }
    
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }
    public List<Expense> getCurrentExpenses() {
        LocalDate today = LocalDate.now();
        return expenseRepository.findAll().stream()
            .filter(e -> e.getDate() == null || !e.getDate().isAfter(today))
            .collect(Collectors.toList());
    }
}