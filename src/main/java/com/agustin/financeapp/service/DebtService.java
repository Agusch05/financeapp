package com.agustin.financeapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agustin.financeapp.model.Debt;
import com.agustin.financeapp.model.Payment;
import com.agustin.financeapp.repository.DebtRepository;
import com.agustin.financeapp.repository.PaymentRepository;

@Service
@Transactional
public class DebtService {
    
    private final DebtRepository debtRepository;
    private final PaymentRepository paymentRepository;
    
    public DebtService(DebtRepository debtRepository, PaymentRepository paymentRepository) {
        this.debtRepository = debtRepository;
        this.paymentRepository = paymentRepository;
    }
    
    public List<Debt> getAllDebts() {
        return debtRepository.findAll();
    }
    
    public List<Debt> getActiveDebts() {
        return debtRepository.findAll().stream()
            .filter(Debt::isActive)
            .collect(Collectors.toList());
    }
    
    public List<Debt> getInactiveDebts() {
        return debtRepository.findAll().stream()
            .filter(d -> !d.isActive())
            .collect(Collectors.toList());
    }
    
    public Debt addDebt(Debt debt) {
        debt.setActive(true);
        debt.setNextPaymentDate(debt.getStartDate().plusMonths(1));
        return debtRepository.save(debt);
    }
    
    public Debt makePayment(Long debtId) {
        Optional<Debt> optionalDebt = debtRepository.findById(debtId);
        if (optionalDebt.isEmpty()) {
            throw new RuntimeException("Deuda no encontrada");
        }
        
        Debt debt = optionalDebt.get();
        
        if (!debt.isActive()) {
            throw new RuntimeException("Esta deuda ya está pagada");
        }
        
        // Calcular número de cuota
        int monthNumber = debt.getTotalMonths() - debt.getRemainingMonths() + 1;
        
        // Registrar el pago
        Payment payment = new Payment(
            debt.getMonthlyPayment(),
            LocalDate.now(),
            monthNumber,
            debt
        );
        
        paymentRepository.save(payment);
        
        // Procesar el pago en la deuda
        debt.processPayment();
        
        return debtRepository.save(debt);
    }
    
    public List<Payment> getPaymentsByDebt(Long debtId) {
        return paymentRepository.findByDebtId(debtId);
    }
    
    public List<Debt> getDebtsWithReminder() {
        LocalDate today = LocalDate.now();
        LocalDate reminderDate = today.plusDays(7);
        
        return debtRepository.findAll().stream()
            .filter(Debt::isActive)
            .filter(debt -> {
                LocalDate nextPayment = debt.getNextPaymentDate();
                return nextPayment != null && 
                       !nextPayment.isBefore(today) && 
                       !nextPayment.isAfter(reminderDate);
            })
            .collect(Collectors.toList());
    }
    
    public Debt deleteDebt(Long id) {
        Optional<Debt> debt = debtRepository.findById(id);
        if (debt.isPresent()) {
            debtRepository.deleteById(id);
            return debt.get();
        }
        return null;
    }
}