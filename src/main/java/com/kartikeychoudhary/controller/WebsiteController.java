package com.kartikeychoudhary.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.kartikeychoudhary.exceptions.CustomWebsiteRuntimeException;
import com.kartikeychoudhary.implementations.UserImplementation;
import com.kartikeychoudhary.modal.Contact;
import com.kartikeychoudhary.modal.Role;
import com.kartikeychoudhary.modal.User;
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
	
	@PostMapping("/contact")
	ResponseEntity<?> saveContact(@RequestBody Contact contact){
		contact.setArchived(false);
		try {
			websiteService.saveContact(contact);
		}catch(CustomWebsiteRuntimeException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getAllContacts")
	ResponseEntity<List<Contact>> getAll(){
		List<Contact> results = websiteService.getAllContacts();	
		return ResponseEntity.ok().body(results);
	}
	
	@GetMapping("/refreshToken")
	ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException{
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refreshToken);
				String username = decodedJWT.getSubject();
				User user = userImpl.getUser(username);
				String access_token = JWT.create()
						.withSubject(user.getEmail())
						.withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream().map(Role::getType).collect(Collectors.toList()))
						.sign(algorithm);
				HashMap<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refreshToken);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			}catch(Exception e) {
				log.error("Error logging in: {}", e.getMessage());
				response.setHeader("error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				HashMap<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
			
		}else {
			throw new RuntimeException("Refresh Token is missing");
		}
		return ResponseEntity.ok().build();
	} 
	
	
}
