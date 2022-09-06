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

import com.kartikeychoudhary.dto.TodoDTO;
import com.kartikeychoudhary.exceptions.CustomGenericRuntimeException;
import com.kartikeychoudhary.modal.Todo;
import com.kartikeychoudhary.response.GenericResponse;
import com.kartikeychoudhary.services.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {
	private final TodoService todoImpl;
	
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
	ResponseEntity<Todo> saveTodo(@RequestBody TodoDTO todo){
		todo.setArchived(false);
		todo.setCompleted(false);
		return ResponseEntity.ok().body(todoImpl.saveUpdateTodo(todo.convert()));
	}
	
	@PatchMapping
	ResponseEntity<Todo> updateTodo(@RequestBody TodoDTO todo){
		return ResponseEntity.ok().body(todoImpl.saveUpdateTodo(todo.convert()));
	}
	
	@PatchMapping("/toggle")
	ResponseEntity<Todo> toggleTodo(@RequestBody TodoDTO todo){
		if(todo.getCompleted() == null) {throw new CustomGenericRuntimeException("isComplete is null");}
		return ResponseEntity.ok().body(todoImpl.saveUpdateTodo(todo.convert()));
	}
	
	@PatchMapping("/archive")
	ResponseEntity<Todo> archiveTodo(@RequestBody TodoDTO todo){
		if(todo.getArchived() == null) {throw new CustomGenericRuntimeException("isComplete is null");}
		return ResponseEntity.ok().body(todoImpl.archiveTodo(todo.convert()));
	}
	
	@DeleteMapping
	ResponseEntity<GenericResponse> deleteTodo(@RequestBody TodoDTO todo){
		todoImpl.deleteTodo(todo.convert());
		return ResponseEntity.ok().build();
	}
}
