package com.code.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.financeapp.model.Debt;

public interface DebtRepository extends JpaRepository<Debt, Long> {
}