package com.kartikeychoudhary.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartikeychoudhary.modal.JournalItem;

public interface JournalRepo extends JpaRepository<JournalItem, Long>{
	List<JournalItem> findAllJournalItemByDate(Date date);

}
