package com.kartikeychoudhary.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kartikeychoudhary.modal.Role;
import com.kartikeychoudhary.modal.User;
import com.kartikeychoudhary.repository.RoleRepo;
import com.kartikeychoudhary.repository.UserRepo;
import com.kartikeychoudhary.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserImplementation implements UserService, UserDetailsService{
	
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.error("START : loadUserByUsername()");
		User user = userRepo.findByEmail(username);
		if(user == null) {
			log.error("User Not found in the database");
			throw new UsernameNotFoundException("User not found");
		}else {
			log.info("User found in the database: {}", username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role->
		{
			authorities.add(new SimpleGrantedAuthority(role.getType()));
		});
		log.error("END : loadUserByUsername()");
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
	@Override
	public User saveUser(User user) {
		log.info("Saving new user : {}", user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role : {}", role.toString());
		
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String email, String type) {
		log.info("Adding role  : {} to User : {}",type, email);
		User user = userRepo.findByEmail(email);
		Role role = roleRepo.findByType(type);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String email) {
		log.info("Fetching email : {}", email);
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users");
		return userRepo.findAll();
	}

	

}
