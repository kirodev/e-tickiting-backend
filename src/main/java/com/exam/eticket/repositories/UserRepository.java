package com.exam.eticket.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.eticket.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByTel(String tel);
	Optional<User> findByEmail(String email);
	
}
