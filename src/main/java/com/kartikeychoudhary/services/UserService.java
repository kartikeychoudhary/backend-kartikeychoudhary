package com.kartikeychoudhary.services;

import java.util.List;

import com.kartikeychoudhary.modal.Role;
import com.kartikeychoudhary.modal.User;


public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String email, String type);
	User getUser(String email);
	List<User> getUsers(); // WIP: add pagination 
}
