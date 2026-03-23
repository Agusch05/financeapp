package com.agustin.financeapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agustin.financeapp.model.Debt;
import com.agustin.financeapp.repository.DebtRepository;

@Service
public class DebtService {
    
    private final DebtRepository debtRepository;
    
    public DebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }
    
    public List<Debt> getAllDebts() {
        return debtRepository.findAll();
    }
    
    public Debt addDebt(Debt debt) {
        return debtRepository.save(debt);
    }
}