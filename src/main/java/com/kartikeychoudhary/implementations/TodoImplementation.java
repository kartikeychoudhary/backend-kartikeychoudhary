package com.kartikeychoudhary.implementations;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kartikeychoudhary.modal.Todo;
import com.kartikeychoudhary.repository.TodoRepo;
import com.kartikeychoudhary.services.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TodoImplementation implements TodoService {
	
	private final TodoRepo todoRepo;
	
	@Override
	public List<Todo> getTodoForDay(Date date) {
		log.info("List of Todo getTodoForDay()");
		return todoRepo.findAllTodoByDate(date);
	}

	@Override
	public List<Todo> getAll() {
		log.info("Todo getAll()");
		return todoRepo.findAll();
	}

	@Override
	public Todo saveUpdateTodo(Todo todo) {
		log.info("Todo saveTodo()");
		return todoRepo.save(todo);
	}


	@Override
	public String deleteTodo(Todo todo) {
		log.info("deleteTodo()");
		todoRepo.delete(todo);
		return "Todo deleted";
	}

	@Override
	public Todo archiveTodo(Todo todo) {
		todo.setArchived(true);
		return todoRepo.save(todo);
	}

	@Override
	public Todo toggleTodo(Todo todo) {
		todo.setCompleted(!todo.getCompleted());
		this.saveUpdateTodo(todo);
		return todo;
	}

}
