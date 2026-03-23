package com.agustin.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agustin.financeapp.model.Debt;

public interface DebtRepository extends JpaRepository<Debt, Long> {
}