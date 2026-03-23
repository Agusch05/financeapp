package com.agustin.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agustin.financeapp.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}