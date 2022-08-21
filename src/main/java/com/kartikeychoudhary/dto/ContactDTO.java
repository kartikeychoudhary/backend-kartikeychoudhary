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
		contact.setEmail(this.getEmail());
		contact.setMessage(this.getMessage());
		contact.setArchived(this.getArchived());
		contact.setName(this.getName());
		contact.setSubject(this.getSubject());

		return contact;
	}
}
