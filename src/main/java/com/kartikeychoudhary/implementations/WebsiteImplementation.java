package com.kartikeychoudhary.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kartikeychoudhary.constants.Constants;
import com.kartikeychoudhary.exceptions.CustomWebsiteRuntimeException;
import com.kartikeychoudhary.modal.Contact;
import com.kartikeychoudhary.repository.ContactRepo;
import com.kartikeychoudhary.services.NotificationService;
import com.kartikeychoudhary.services.WebsiteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebsiteImplementation implements WebsiteService{
	
	private final NotificationService ns;
	
	private final ContactRepo contactRepo;
	
	@Value("${MESSAGE_LENGTH}")
	private int maxMessageLength;
	
	@Value("${EMAIL_LENGTH}")
	private int maxEmailLength;
	
	@Value("${TITLE_LENGTH}")
	private int	maxTitleLength;
	
	@Value("${PRIORITY}")
	private int defaultPriority;
	
	@Override
	public List<Contact> getAllContacts() {
		log.info("Get all contacts messsages");
		return contactRepo.findAll();
	}

	@Override
	public void saveContact(Contact contact) {
		log.info("Saving contact message : " + contact.getEmail());
		if(contact.getMessage().length() > this.maxMessageLength) {throw new CustomWebsiteRuntimeException(Constants.MESSAGE_LENGTH_ERROR);}
		if(contact.getEmail().length() > this.maxEmailLength) {throw new CustomWebsiteRuntimeException(Constants.EMAIL_LENGTH_ERROR);}
		if(contact.getSubject().length() > this.maxTitleLength) {throw new CustomWebsiteRuntimeException(Constants.SUBJECT_LENGTH_ERROR);}
		contactRepo.save(contact);
		ns.createNotification("From: \n"+contact.getEmail()+"\n\nSubject:\n"+ contact.getSubject() + "\n\nMessage: \n"+contact.getMessage(), contact.getSubject(), 5);
	}

}
