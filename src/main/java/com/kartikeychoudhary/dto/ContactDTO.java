package com.kartikeychoudhary.dto;

import com.kartikeychoudhary.modal.Contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
