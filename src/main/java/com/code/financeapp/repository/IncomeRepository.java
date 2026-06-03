package com.code.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.financeapp.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}