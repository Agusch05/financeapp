package com.code.financeapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.financeapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByNickname(String nickname);
}
