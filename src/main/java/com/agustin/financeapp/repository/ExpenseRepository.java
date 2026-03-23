package com.agustin.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agustin.financeapp.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
