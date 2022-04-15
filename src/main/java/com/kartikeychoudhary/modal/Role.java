package com.kartikeychoudhary.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * @author 	Kartikey Choudhary (https://kartikeychoudhary.com)
 * @version 1.0
 * @since	04/10/2022
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String type;
}
