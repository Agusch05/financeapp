package com.code.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.financeapp.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
