package com.kartikeychoudhary.dto;

import com.kartikeychoudhary.modal.Contact;


public class ContactDTO {
	
	private String email;
	private String message;
	private String name;
	private String subject;
	private Boolean archived;
	
	public Contact convert() {
		Contact contact = new Contact();
		contact.setArchived(this.archived);
		contact.setMessage(this.message);
		contact.setName(this.name);
		contact.setSubject(this.subject);
		contact.setEmail(this.email);
		return contact;
	}
	
	public ContactDTO() {
		// need empty constructor
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
	
	
}
