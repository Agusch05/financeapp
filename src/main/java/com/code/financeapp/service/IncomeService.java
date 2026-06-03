package com.code.financeapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.code.financeapp.model.Income;
import com.code.financeapp.repository.IncomeRepository;

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
    public List<Income> getCurrentIncomes() {
        LocalDate today = LocalDate.now();
        return incomeRepository.findAll().stream()
            .filter(i -> i.getDate() == null || !i.getDate().isAfter(today))
            .collect(Collectors.toList());
    }
}