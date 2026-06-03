package com.code.financeapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "debts")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double totalAmount;
    private double monthlyPayment;
    private int remainingMonths;
    private int totalMonths; // Total de meses originales
    private double interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate nextPaymentDate; // Próxima fecha de pago
    private boolean active; // Activa o pagada
    private double remainingAmount; // Monto restante por pagar
    
    @OneToMany(mappedBy = "debt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();

    public Debt() {}

    public Debt(String name, double totalAmount, double monthlyPayment, int remainingMonths, 
                double interestRate, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.totalAmount = totalAmount;
        this.monthlyPayment = monthlyPayment;
        this.remainingMonths = remainingMonths;
        this.totalMonths = remainingMonths;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nextPaymentDate = startDate.plusMonths(1);
        this.active = true;
        this.remainingAmount = totalAmount;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    
    public double getMonthlyPayment() { return monthlyPayment; }
    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }
    
    public int getRemainingMonths() { return remainingMonths; }
    public void setRemainingMonths(int remainingMonths) { this.remainingMonths = remainingMonths; }
    
    public int getTotalMonths() { return totalMonths; }
    public void setTotalMonths(int totalMonths) { this.totalMonths = totalMonths; }
    
    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public LocalDate getNextPaymentDate() { return nextPaymentDate; }
    public void setNextPaymentDate(LocalDate nextPaymentDate) { this.nextPaymentDate = nextPaymentDate; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public double getRemainingAmount() { return remainingAmount; }
    public void setRemainingAmount(double remainingAmount) { this.remainingAmount = remainingAmount; }
    
    public List<Payment> getPayments() { return payments; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }
    
    // Método para procesar un pago
    public void processPayment() {
        if (!active) {
            throw new IllegalStateException("Esta deuda ya está pagada");
        }
        
        if (remainingMonths <= 0) {
            this.active = false;
            return;
        }
        
        this.remainingMonths--;
        this.remainingAmount -= this.monthlyPayment;
        
        if (remainingMonths <= 0 || remainingAmount <= 0) {
            this.active = false;
            this.remainingAmount = 0;
        } else {
            this.nextPaymentDate = this.nextPaymentDate.plusMonths(1);
        }
    }
}