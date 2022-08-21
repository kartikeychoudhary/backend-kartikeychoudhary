package com.kartikeychoudhary.implementations;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kartikeychoudhary.exceptions.CustomWebsiteRuntimeException;
import com.kartikeychoudhary.modal.Notification;

class NotificationImplementationsTest {
	private NotificationImplementations ns;

	private RestTemplate rest;

	@BeforeEach
	void initService() {
		rest = Mockito.mock(RestTemplate.class);
		ns = new NotificationImplementations(rest);
		ReflectionTestUtils.setField(ns, "maxMessageLength", 10);
		ReflectionTestUtils.setField(ns, "maxTitleLength", 10);
		ReflectionTestUtils.setField(ns, "defaultPriority", 5);
		ReflectionTestUtils.setField(ns, "url", "http://localhost/");
		ReflectionTestUtils.setField(ns, "token", "12345678qwertyu");

	}

	@Test
	void createNotificationTest() {
		ResponseEntity<String> response = new ResponseEntity<String>("ok", HttpStatus.OK);
		Mockito.when(rest.exchange(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.eq(String.class)))
				.thenReturn(response);

		ns.createNotification("Test", "Test", 5);
		assertThatNoException();
	}

	@Test
	void sendNotificationTest() throws JsonProcessingException {
		Notification notification = ns.validateAndCreateNotification("Test", "Test", 5);
		ResponseEntity<String> response = new ResponseEntity<String>("OK", HttpStatus.OK);
		Mockito.when(rest.exchange(Mockito.any(String.class), Mockito.eq(HttpMethod.POST), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		ns.sendNotification(notification);
		assertThatNoException();
	}

	@Test
	void validateAndCreateNotificationTest() {
		assertNull(ns.validateAndCreateNotification("123456789011", "Test", 5));
		assertNull(ns.validateAndCreateNotification("Test", "123456789011", 5));
		assertNull(ns.validateAndCreateNotification("Test3", "Test3", 6));
	}

}
