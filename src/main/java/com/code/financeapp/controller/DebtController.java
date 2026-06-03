package com.code.financeapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.code.financeapp.model.Debt;
import com.code.financeapp.model.Payment;
import com.code.financeapp.service.DebtService;

@RestController
@RequestMapping("/debts")
@CrossOrigin(origins = "*")
public class DebtController {
    
    private final DebtService debtService;
    
    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }
    
    @GetMapping
    public List<Debt> getAllDebts() {
        return debtService.getAllDebts();
    }
    
    @GetMapping("/active")
    public List<Debt> getActiveDebts() {
        return debtService.getActiveDebts();
    }
    
    @GetMapping("/inactive")
    public List<Debt> getInactiveDebts() {
        return debtService.getInactiveDebts();
    }
    
    @GetMapping("/reminders")
    public List<Debt> getDebtsWithReminder() {
        return debtService.getDebtsWithReminder();
    }
    
    @GetMapping("/{id}/payments")
    public List<Payment> getPayments(@PathVariable Long id) {
        return debtService.getPaymentsByDebt(id);
    }
    
    @PostMapping
    public Debt createDebt(@RequestBody Debt debt) {
        return debtService.addDebt(debt);
    }
    
    @PostMapping("/{id}/pay")
    public Debt makePayment(@PathVariable Long id) {
        return debtService.makePayment(id);
    }
    
    @DeleteMapping("/{id}")
    public Debt deleteDebt(@PathVariable Long id) {
        return debtService.deleteDebt(id);
    }
}