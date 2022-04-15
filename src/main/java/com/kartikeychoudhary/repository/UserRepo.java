package com.kartikeychoudhary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartikeychoudhary.modal.User;


public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
}
