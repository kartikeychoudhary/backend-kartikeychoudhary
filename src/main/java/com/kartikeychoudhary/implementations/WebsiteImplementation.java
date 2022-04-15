package com.kartikeychoudhary.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kartikeychoudhary.modal.Contact;
import com.kartikeychoudhary.repository.ContactRepo;
import com.kartikeychoudhary.services.WebsiteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebsiteImplementation implements WebsiteService{
	private final ContactRepo contactRepo;
	
	@Override
	public List<Contact> getAllContacts() {
		log.info("Get all contacts messsages");
		return contactRepo.findAll();
	}

	@Override
	public void saveContact(Contact contact) {
		log.info("Saving contact message : " + contact.getEmail());
		contactRepo.save(contact);
	}

}
