package com.kartikeychoudhary.implementations;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.kartikeychoudhary.constants.Constants;
import com.kartikeychoudhary.exceptions.CustomWebsiteRuntimeException;
import com.kartikeychoudhary.modal.Contact;
import com.kartikeychoudhary.repository.ContactRepo;
import com.kartikeychoudhary.services.NotificationService;

class WebsiteImplementationTest {
	
	private NotificationService ns;
	private ContactRepo contactRepo;
	
	private WebsiteImplementation wi;

	
	@BeforeEach
	void initService() {
		contactRepo = Mockito.mock(ContactRepo.class);
		ns = Mockito.mock(NotificationService.class);
		wi = new WebsiteImplementation(ns, contactRepo);
		ReflectionTestUtils.setField(wi, "maxMessageLength", 10);
		ReflectionTestUtils.setField(wi, "maxEmailLength", 10);
		ReflectionTestUtils.setField(wi, "maxTitleLength", 10);
		ReflectionTestUtils.setField(wi, "defaultPriority", 5);
	}
	@Test
	void getAllContactsTest() {
		List<Contact> ls = Collections.singletonList(new Contact());
		Mockito.when(contactRepo.findAll()).thenReturn(ls);
		Assert.assertNotNull(wi.getAllContacts());
	}
	@Test
	void saveContactTest() {
		Contact testObject = new Contact();
		testObject.setEmail("t@test.com");
		testObject.setSubject("Subject");
		testObject.setMessage("Message");
		testObject.setName("Name");
		Mockito.when(contactRepo.save(testObject)).thenReturn(testObject);
		Mockito.doNothing().when(ns).createNotification(Mockito.any(), Mockito.any(), Mockito.anyInt());
		
		wi.saveContact(testObject);
		assertThatNoException();
		
		testObject.setSubject("12345678901");
		CustomWebsiteRuntimeException exp1 = assertThrows(CustomWebsiteRuntimeException.class, ()->wi.saveContact(testObject));
		assertEquals(Constants.SUBJECT_LENGTH_ERROR,exp1.getMessage());
		
		testObject.setEmail("12345678901");
		CustomWebsiteRuntimeException exp2 = assertThrows(CustomWebsiteRuntimeException.class, ()->wi.saveContact(testObject));
		assertEquals(Constants.EMAIL_LENGTH_ERROR,exp2.getMessage());

		testObject.setMessage("12345678901");
		CustomWebsiteRuntimeException exp3 = assertThrows(CustomWebsiteRuntimeException.class, ()->wi.saveContact(testObject));
		assertEquals(Constants.MESSAGE_LENGTH_ERROR,exp3.getMessage());
	}
}
