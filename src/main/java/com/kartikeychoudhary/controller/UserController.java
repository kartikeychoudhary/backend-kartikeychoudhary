package com.kartikeychoudhary.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kartikeychoudhary.implementations.UserImplementation;
import com.kartikeychoudhary.modal.Role;
import com.kartikeychoudhary.modal.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
	private final UserImplementation userImpl;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getUsers(){
		return ResponseEntity.ok().body(userImpl.getUsers());
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
		return ResponseEntity.created(uri).body(userImpl.saveUser(user));
	}
	
	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role){
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/role/save").toUriString());
		return ResponseEntity.created(uri).body(userImpl.saveRole(role));
	}
	
	@PostMapping("/role/add")
	public ResponseEntity<?> saveRole(@RequestBody String email, @RequestBody String type){
		userImpl.addRoleToUser(email, type);
		return ResponseEntity.ok().build();
	}
}
