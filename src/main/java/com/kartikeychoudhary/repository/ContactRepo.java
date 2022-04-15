package com.kartikeychoudhary.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartikeychoudhary.modal.Contact;
import com.kartikeychoudhary.modal.JournalItem;
import com.kartikeychoudhary.modal.Todo;

public interface ContactRepo extends JpaRepository<Contact, Long> {

}
