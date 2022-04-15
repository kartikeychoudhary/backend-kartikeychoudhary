package com.kartikeychoudhary.services;

import java.util.List;

import com.kartikeychoudhary.modal.Contact;

public interface WebsiteService {
	public List<Contact> getAllContacts();
	public void saveContact(Contact contact);
}
