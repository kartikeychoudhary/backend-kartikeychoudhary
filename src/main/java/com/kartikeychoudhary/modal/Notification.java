package com.kartikeychoudhary.modal;

public class Notification {
	private String message;
	private String title;
	private int priority;
	
	Notification() {}

	public Notification(String message, String title, int priority) {
		super();
		this.message = message;
		this.title = title;
		this.priority = priority;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
