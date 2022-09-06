package com.kartikeychoudhary.dto;

import java.sql.Date;

import com.kartikeychoudhary.modal.Todo;


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
	public TodoDTO() {
		// need empty constructor
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	public Boolean getArchived() {
		return archived;
	}
	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
	
}
