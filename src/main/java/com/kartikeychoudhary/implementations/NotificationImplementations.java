package com.kartikeychoudhary.implementations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kartikeychoudhary.exceptions.CustomWebsiteRuntimeException;
import com.kartikeychoudhary.modal.Notification;
import com.kartikeychoudhary.services.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationImplementations implements NotificationService {

	private final RestTemplate rest;
	
	@Value("${GOTIFY_API_TOKEN}")
	private  String token;
	
	@Value("${GOTIFY_URL}")
	private  String url;
	
	@Value("${TITLE_LENGTH}")
	private  int maxTitleLength;
	
	@Value("${NOTIFICATION_LENGTH}")
	private  int maxMessageLength;
	
	@Value("${PRIORITY}")
	private  int defaultPriority;

	@Override
	public  void createNotification(String message, String title, int priority) {
		log.info("NotificationUtil.runNotification() : start");
		Runnable runnable = () -> {
			try {
				this.sendNotification(this.validateAndCreateNotification(message, title, priority));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		};
		new Thread(runnable).start();
	}
	
	
	public Notification validateAndCreateNotification(String message, String title, int priority) {
		log.info("NotificationUtil.createNotification() : start");

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

	public void sendNotification(Notification notification) throws JsonProcessingException {
		log.info("NotificationUtil.sendNotification() : start");

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(notification);
	
		String body = json;

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
		try {
		ResponseEntity<String> responseEntity = rest.exchange(url+"message?token="+token, HttpMethod.POST, requestEntity, String.class);
		if(null == responseEntity) {throw new CustomWebsiteRuntimeException("Response is empty");}
		HttpStatus httpStatus = responseEntity.getStatusCode();
		int status = httpStatus.value();
		String response = responseEntity.getBody();
		log.info("NotificationUtil.sendNotification() : " + notification.toString());
		log.info("NotificationUtil.sendNotification() Response status : " + status);
		log.info("NotificationUtil.sendNotification() Response : " + response);
		}catch(CustomWebsiteRuntimeException e) {
			log.error("NotificationUtil.sendNotification() : ERROR ", e);
		}
	}

}
