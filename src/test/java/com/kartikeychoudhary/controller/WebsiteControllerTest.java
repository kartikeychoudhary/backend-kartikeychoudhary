package com.kartikeychoudhary.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kartikeychoudhary.constants.Constants;
import com.kartikeychoudhary.dto.ContactDTO;
import com.kartikeychoudhary.exceptions.CustomWebsiteRuntimeException;
import com.kartikeychoudhary.implementations.UserImplementation;
import com.kartikeychoudhary.modal.Contact;
import com.kartikeychoudhary.response.GenericResponse;
import com.kartikeychoudhary.services.WebsiteService;

class WebsiteControllerTest {
	
	private UserImplementation userImpl;
	private WebsiteService websiteService;
	
	private WebsiteController wc;
	
	@BeforeEach
	void initService() {
		userImpl = Mockito.mock(UserImplementation.class);
		websiteService = Mockito.mock(WebsiteService.class);

		wc = new WebsiteController(userImpl, websiteService);
	}
	
	@Test
	void getAllTest() {
		List<Contact> ls = new ArrayList<>();
		ls.add(new Contact());
		Mockito.when(websiteService.getAllContacts()).thenReturn(ls);
		ResponseEntity<GenericResponse> response = wc.getAll();
		assertNotNull(response.getBody().getPayLoad().get(Constants.RESULT));
	}
	
	@Test
	void saveContactTest() {
		ContactDTO contact = new ContactDTO();
		contact.setEmail("email");
		contact.setMessage("message");
		contact.setName("name");
		contact.setArchived(false);
		contact.setSubject("subject");
		Mockito.doNothing().when(websiteService).saveContact(Mockito.any());
		ResponseEntity<GenericResponse> response = wc.saveContact(contact);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Mockito.doThrow(CustomWebsiteRuntimeException.class).when(websiteService).saveContact(Mockito.any());
		response = wc.saveContact(contact);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void refreshTokenTest() throws IOException {
		String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXJ0aWtleTMxY2hvdWRoYXJ5QGdtYWlsLmNvbSIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6MjIyMi9sb2dpbiIsImV4cCI6MTY2MTc2MTIxN30.7gX1oCa1V2UeSH6W785Qqygn3_UiXAn4680_6rhtHgM";
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(request.getHeader(Mockito.any())).thenReturn(token);
		Mockito.when(request.getRequestURL()).thenReturn(new StringBuffer());

		Mockito.when(response.getOutputStream()).thenReturn(null);
		ResponseEntity<GenericResponse> res = wc.refreshToken(request, response);
		assertNotNull(res.getBody().getPayLoad());
	}
	
}
