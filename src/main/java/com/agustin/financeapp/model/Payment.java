package com.agustin.financeapp.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private LocalDate paymentDate;
    private int monthNumber; // Número de cuota pagada
    
    @ManyToOne
    @JoinColumn(name = "debt_id")
    private Debt debt;

    public Payment() {}

    public Payment(double amount, LocalDate paymentDate, int monthNumber, Debt debt) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.monthNumber = monthNumber;
        this.debt = debt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    
    public int getMonthNumber() { return monthNumber; }
    public void setMonthNumber(int monthNumber) { this.monthNumber = monthNumber; }
    
    public Debt getDebt() { return debt; }
    public void setDebt(Debt debt) { this.debt = debt; }
}