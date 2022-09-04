package com.kartikeychoudhary.dto;

import java.sql.Date;

import com.kartikeychoudhary.modal.Todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
	private Long id;
	private String description;
	private Date date;
	private Boolean completed;
	private Boolean archived;
	
	public Todo convert() {
		Todo todo = new Todo();
		todo.setId(this.id);
		todo.setDescription(this.description);
		todo.setDate(this.date);
		todo.setCompleted(this.completed);
		todo.setArchived(this.archived);
		return todo;
	}
}
