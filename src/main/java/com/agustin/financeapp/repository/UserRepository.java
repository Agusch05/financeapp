package com.agustin.financeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agustin.financeapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}