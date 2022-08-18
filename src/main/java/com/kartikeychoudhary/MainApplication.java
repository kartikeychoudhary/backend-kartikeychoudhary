package com.kartikeychoudhary;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kartikeychoudhary.implementations.UserImplementation;
import com.kartikeychoudhary.modal.Role;
import com.kartikeychoudhary.modal.User;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

/** 
 * @author 	Kartikey Choudhary (https://kartikeychoudhary.com)
 * @version 1.0
 * @since	04/10/2022
 */

@SpringBootApplication
@EnableEncryptableProperties
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	CommandLineRunner run(UserImplementation userImpl) {
//		return args -> {
//			if(userImpl.getUser("admin@admin.com")==null) {
//				userImpl.saveRole(new Role(null, "ROLE_USER"));
//				userImpl.saveRole(new Role(null, "ROLE_ADMIN"));
//				userImpl.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//				userImpl.saveUser(new User(null, "Admin", "admin@admin.com", "admin", new ArrayList<>()));
//				userImpl.addRoleToUser("admin@admin.com", "ROLE_SUPER_ADMIN");
//				}
//			};
//	}

}
