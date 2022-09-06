package com.kartikeychoudhary.services;

import java.sql.Date;
import java.util.List;

import com.kartikeychoudhary.modal.Todo;

public interface TodoService {
	List<Todo> getTodoForDay(Date date);
	List<Todo> getAll();
	Todo saveUpdateTodo(Todo todo);
	String deleteTodo(Todo todo);
	Todo archiveTodo(Todo todo);
	Todo toggleTodo(Todo todo);
}