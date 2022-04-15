package com.kartikeychoudhary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartikeychoudhary.modal.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByType(String type);
}
