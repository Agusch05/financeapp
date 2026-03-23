package com.agustin.financeapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agustin.financeapp.model.Expense;
import com.agustin.financeapp.repository.ExpenseRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }
}

