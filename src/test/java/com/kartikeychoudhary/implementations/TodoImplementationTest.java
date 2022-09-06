package com.kartikeychoudhary.implementations;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.kartikeychoudhary.modal.Todo;
import com.kartikeychoudhary.repository.TodoRepo;

class TodoImplementationTest {

	private TodoRepo todoRepo;
	
	private TodoImplementation todoImplementation;
	
	@BeforeEach
	void initService() {
		todoRepo = Mockito.mock(TodoRepo.class);
		todoImplementation = new TodoImplementation(todoRepo);
	}
	
	@Test
	void testGetAllTodoForDay() {
		List<Todo> items = new ArrayList<>();
		items.add(new Todo());
		Mockito.when(todoRepo.findAllTodoByDate(Mockito.any(Date.class))).thenReturn(items);
		Date date = new Date(20220801);
		assertTrue(todoImplementation.getTodoForDay(date).size()>0);
	}

	@Test
	void testGetAll() {
		List<Todo> items = new ArrayList<>();
		items.add(new Todo());
		Mockito.when(todoRepo.findAll()).thenReturn(items);
		assertTrue(todoImplementation.getAll().size()>0);
	}

	@Test
	void testSaveUpdateTodo() {
		Todo item = new Todo();
		Mockito.when(todoRepo.save(item)).thenReturn(item);
		assertNotNull(todoImplementation.saveUpdateTodo(item));
	}

	@Test
	void testDeleteTodo() {
		Todo item = new Todo();
		Mockito.doNothing().when(todoRepo).delete(item);
		todoImplementation.deleteTodo(item);
		assertThatNoException();
	}

	@Test
	void testArchiveTodo() {
		Todo item = new Todo();
		item.setArchived(false);
		Mockito.when(todoRepo.save(Mockito.any(Todo.class))).thenReturn(item);
		assertTrue(todoImplementation.archiveTodo(item).getArchived());
	}
	

	@Test
	void testToogleTodo() {
		Todo item = new Todo();
		item.setCompleted(false);
		Mockito.when(todoRepo.save(Mockito.any(Todo.class))).thenReturn(item);
		assertTrue(todoImplementation.toggleTodo(item).getCompleted());
	}

}
