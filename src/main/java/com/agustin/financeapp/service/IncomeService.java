package com.agustin.financeapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agustin.financeapp.model.Income;
import com.agustin.financeapp.repository.IncomeRepository;

@Service
public class IncomeService {
    
    private final IncomeRepository incomeRepository;
    
    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }
    
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }
    
    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }
}