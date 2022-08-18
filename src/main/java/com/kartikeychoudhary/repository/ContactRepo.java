package com.kartikeychoudhary.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kartikeychoudhary.modal.Contact;


public interface ContactRepo extends JpaRepository<Contact, Long> {

}
