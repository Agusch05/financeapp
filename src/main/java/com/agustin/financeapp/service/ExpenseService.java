package com.agustin.financeapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agustin.financeapp.model.Expense;
import com.agustin.financeapp.repository.ExpenseRepository;

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
}