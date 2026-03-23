package com.agustin.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agustin.financeapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}

