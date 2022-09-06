package com.kartikeychoudhary.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.kartikeychoudhary.dto.TodoDTO;
import com.kartikeychoudhary.modal.Todo;
import com.kartikeychoudhary.services.TodoService;

class TodoControllerTest {

	private TodoService todoImpl;
	private TodoController todoController;
	
	@BeforeEach
	public void initService() {
		todoImpl = Mockito.mock(TodoService.class);
		todoController = new TodoController(todoImpl);
	}

	@Test
	void testGetJournalItem() {
		List<Todo> list = new ArrayList<>();
		Mockito.when(todoImpl.getTodoForDay(Mockito.any(Date.class))).thenReturn(list);
		assertNotNull(todoController.getTodo("20220901"));
	}

	@Test
	void testGetAllJournalItem() {
		List<Todo> list = new ArrayList<>();
		Mockito.when(todoImpl.getAll()).thenReturn(list);
		assertNotNull(todoController.getAllTodo());
	}

	@Test
	void testSaveJournalItem() {
		TodoDTO dto = new TodoDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		Mockito.when(todoImpl.saveUpdateTodo(Mockito.any(Todo.class))).thenReturn(dto.convert());
		assertNotNull(todoController.saveTodo(dto));
	}

	@Test
	void testUpdateJournalItem() {
		TodoDTO dto = new TodoDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		dto.setId(1L);
		Mockito.when(todoImpl.saveUpdateTodo(Mockito.any(Todo.class))).thenReturn(dto.convert());
		assertNotNull(todoController.updateTodo(dto));
	}

	@Test
	void testArchiveJournalItem() {
		TodoDTO dto = new TodoDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		dto.setId(1L);
		Mockito.when(todoImpl.archiveTodo(Mockito.any(Todo.class))).thenReturn(dto.convert());
		assertNotNull(todoController.archiveTodo(dto));
	}
	
	@Test
	void testToggleJournalItem() {
		TodoDTO dto = new TodoDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		dto.setId(1L);
		dto.setCompleted(false);
Mockito.when(todoImpl.toggleTodo(Mockito.any(Todo.class))).thenReturn(dto.convert());
		assertNotNull(todoController.toggleTodo(dto));
	}
	

	@Test
	void testDeleteJournalItem() {
		TodoDTO dto = new TodoDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		dto.setId(1L);
		Mockito.when(todoImpl.deleteTodo(Mockito.any(Todo.class))).thenReturn("done");
		assertNotNull(todoController.deleteTodo(dto));
	}

}
