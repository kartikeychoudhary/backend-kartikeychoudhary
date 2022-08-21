package com.kartikeychoudhary.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kartikeychoudhary.constants.Constants;
import com.kartikeychoudhary.dto.ContactDTO;
import com.kartikeychoudhary.exceptions.CustomWebsiteRuntimeException;
import com.kartikeychoudhary.implementations.UserImplementation;
import com.kartikeychoudhary.modal.Contact;
import com.kartikeychoudhary.modal.Role;
import com.kartikeychoudhary.modal.User;
import com.kartikeychoudhary.response.GenericResponse;
import com.kartikeychoudhary.services.WebsiteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/kartikeychoudhary")
@CrossOrigin("*")
@Slf4j
public class WebsiteController {
	private final UserImplementation userImpl;
	private final WebsiteService websiteService;
	
	GenericResponse gr;
	Map<String, Object> payload;
	HttpStatus status;
	
	@PostMapping("/contact")
	ResponseEntity<GenericResponse> saveContact(@RequestBody ContactDTO contactDto){
		gr = new GenericResponse();
		payload = new HashMap<>();
		contactDto.setArchived(false);
		try {
			websiteService.saveContact(contactDto.convert());
			payload.put(Constants.MESSAGE, "ok");
			status=HttpStatus.OK;
		}catch(CustomWebsiteRuntimeException e) {
			payload.put(Constants.MESSAGE, e);
			status=HttpStatus.BAD_REQUEST;
		}
		gr.setPayLoad(payload);
		gr.setStatus(status);
		return new ResponseEntity<>(gr, status);
	}
	
	@GetMapping("/getAllContacts")
	ResponseEntity<GenericResponse> getAll(){
		gr = new GenericResponse();
		payload = new HashMap<>();
		List<Contact> results = websiteService.getAllContacts();
		status = HttpStatus.OK;
		payload.put(Constants.RESULT, results);
		gr.setPayLoad(payload);
		gr.setStatus(status);
		return new ResponseEntity<>(gr, status);
	}
	
	@GetMapping("/refreshToken")
	ResponseEntity<GenericResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		gr = new GenericResponse();
		payload = new HashMap<>();
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refreshToken);
				String username = decodedJWT.getSubject();
				User user = userImpl.getUser(username);
				String accessToken = JWT.create()
						.withSubject(user.getEmail())
						.withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream().map(Role::getType).collect(Collectors.toList()))
						.sign(algorithm);
				HashMap<String, String> tokens = new HashMap<>();
				tokens.put(Constants.TOKEN, accessToken);
				tokens.put(Constants.REFRESH_TOKEN, refreshToken);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
				status = HttpStatus.OK;
				payload.put(Constants.TOKEN, accessToken);
				payload.put(Constants.REFRESH_TOKEN, refreshToken);

			}catch(Exception e) {
				log.error("Error logging in: {}", e.getMessage());
				response.setHeader(Constants.ERROR, e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				HashMap<String, String> error = new HashMap<>();
				error.put(Constants.ERROR, e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
				status = HttpStatus.BAD_REQUEST;
				payload.put(Constants.MESSAGE, "error");
			}
			
		}else {
			throw new CustomWebsiteRuntimeException("Refresh Token is missing");
		}
		
		gr.setPayLoad(payload);
		gr.setStatus(status);
		return new ResponseEntity<>(gr, status);
	} 
	
	
}
