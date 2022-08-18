package com.kartikeychoudhary.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.kartikeychoudhary.exceptions.CustomWebsiteRuntimeException;
import com.kartikeychoudhary.modal.Notification;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 


@Slf4j
public class NotificationUtil {
	
	@Value("${GOTIFY_API_TOKEN}")
	private static String token;
	
	@Value("${GOTIFY_URL}")
	private static String url;
	
	@Value("${TITLE_LENGTH}")
	private static int maxTitleLength;
	
	@Value("${NOTIFICATION_LENGTH}")
	private static int maxMessageLength;
	
	@Value("${PRIORITY}")
	private static int defaultPriority;
	
	
	public static void runNotification(String message, String title, int priority) {
		log.info("NotificationUtil.runNotification() : start");
		Runnable runnable = () -> {
			try {
				sendNotification(createNotification(message, title, priority));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		new Thread(runnable).start();
	}
	
	private NotificationUtil() {}
	
	public static Notification createNotification(String message, String title, int priority) {
		log.info("NotificationUtil.createNotification() : start" + maxMessageLength + maxTitleLength + priority);

		Notification notification=null;
		try {
			if(message.length() > maxMessageLength) {throw new CustomWebsiteRuntimeException("Notification Message length exceded the max allowed value");}
			if(title.length() > maxTitleLength) {throw new CustomWebsiteRuntimeException("Notification Title length exceded the max allowed value");}
			if(priority > defaultPriority) {throw new CustomWebsiteRuntimeException("Notification priority value not allowed");}
			notification = new Notification(message, title, priority);
		}catch(CustomWebsiteRuntimeException e) {
			log.error("Create Notification : " + e);
		}
		return notification;
	}			

	public static void sendNotification(Notification notification) throws JsonProcessingException {
		log.info("NotificationUtil.sendNotification() : start");

		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(notification);
	
		String body = json;

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
		ResponseEntity<String> responseEntity = rest.exchange(url+"message?token="+token, HttpMethod.POST, requestEntity, String.class);
		HttpStatus httpStatus = responseEntity.getStatusCode();
		int status = httpStatus.value();
		String response = responseEntity.getBody();
		log.info("NotificationUtil.sendNotification() : " + notification.toString());
		log.info("NotificationUtil.sendNotification() Response status : " + status);
		log.info("NotificationUtil.sendNotification() Response : " + response);
	}
}
