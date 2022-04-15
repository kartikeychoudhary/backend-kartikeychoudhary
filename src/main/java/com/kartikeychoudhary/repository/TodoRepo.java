package com.kartikeychoudhary.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartikeychoudhary.modal.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long> {
	List<Todo> findAllTodoByDate(Date date);
}
