package com.agustin.financeapp.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "debts")
public class Debt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double totalAmount;
    private double monthlyPayment;
    private int remainingMonths;
    private double interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
    
    public Debt() {}
    
    public Debt(double totalAmount, double monthlyPayment, int remainingMonths, double interestRate, LocalDate startDate, LocalDate endDate) {
        this.totalAmount = totalAmount;
        this.monthlyPayment = monthlyPayment;
        this.remainingMonths = remainingMonths;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    // Getters
    public Long getId() { return id; }
    public double getTotalAmount() { return totalAmount; }
    public double getMonthlyPayment() { return monthlyPayment; }
    public int getRemainingMonths() { return remainingMonths; }
    public double getInterestRate() { return interestRate; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }
    public void setRemainingMonths(int remainingMonths) { this.remainingMonths = remainingMonths; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}