package com.kartikeychoudhary.modal;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
 * @author 	Kartikey Choudhary (https://kartikeychoudhary.com)
 * @version 1.0
 * @since	04/10/2022
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();
}
