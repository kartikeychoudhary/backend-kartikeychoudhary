package com.kartikeychoudhary.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kartikeychoudhary.implementations.TodoImplementation;
import com.kartikeychoudhary.modal.Todo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {
	private final TodoImplementation todoImpl;
	
	@GetMapping
	ResponseEntity<List<Todo>> getTodo(@RequestParam String date){
		Date d = new Date(Long.parseLong(date));
		List<Todo> todo = todoImpl.getTodoForDay(d);
		return ResponseEntity.ok().body(todo);
	}
	
	@GetMapping("/all")
	ResponseEntity<List<Todo>> getAllTodo(){
		List<Todo> todo = todoImpl.getAll();
		return ResponseEntity.ok().body(todo);
	}
	
	@PostMapping
	ResponseEntity<Todo> saveTodo(@RequestBody Todo todo){
		todo.setArchived(false);
		todo.setCompleted(false);
		return ResponseEntity.ok().body(todoImpl.saveTodo(todo));
	}
	
	@PatchMapping
	ResponseEntity<Todo> updateTodo(@RequestBody Todo todo){
		return ResponseEntity.ok().body(todoImpl.updateTodo(todo));
	}
	
	@PatchMapping("/toggle")
	ResponseEntity<Todo> toggleTodo(@RequestBody Todo todo){
		if(todo.getCompleted() == null) {throw new RuntimeException("isComplete is null");}
		todo.setCompleted(!todo.getCompleted());
		return ResponseEntity.ok().body(todoImpl.updateTodo(todo));
	}
	
	@PatchMapping("/archive")
	ResponseEntity<Todo> archiveTodo(@RequestBody Todo todo){
		if(todo.getArchived() == null) {throw new RuntimeException("isComplete is null");}
		todo.setArchived(!todo.getArchived());
		return ResponseEntity.ok().body(todoImpl.archiveTodo(todo));
	}
	
	@DeleteMapping
	ResponseEntity<?> deleteTodo(@RequestBody Todo todo){
		todoImpl.deleteTodo(todo);
		return ResponseEntity.ok().build();
	}
}
