package com.code.financeapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.financeapp.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByDebtId(Long debtId);
}