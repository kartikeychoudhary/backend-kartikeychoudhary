package com.kartikeychoudhary.implementations;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kartikeychoudhary.modal.JournalItem;
import com.kartikeychoudhary.repository.JournalRepo;
import com.kartikeychoudhary.services.JournalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JournalImplementations implements JournalService{
	private final JournalRepo journalRepo;
	
	@Override
	public List<JournalItem> getAllJournalItemForDay(Date date) {
		log.info("List of Journal  getAllJournalForDay()");
		return journalRepo.findAllJournalItemByDate(date);
	}

	@Override
	public List<JournalItem> getAll() {
		log.info("Journal getAll()");
		return journalRepo.findAll();
	}

	@Override
	public JournalItem saveJournalItem(JournalItem journalItem) {
		log.info("Journal save saveJournalItem()");
		return journalRepo.save(journalItem);
	}

	@Override
	public JournalItem updateJournalItem(JournalItem journalItem) {
		log.info("Journal save saveJournalItem()");
		return journalRepo.save(journalItem);
	}

	@Override
	public String deleteJournalItem(JournalItem journalItem) {
		log.info("Journal delete deletJournalItem()");	
		journalRepo.delete(journalItem);
		return "Deleted";
	}

	@Override
	public JournalItem archiveJournalItem(JournalItem journalItem) {
		if(journalItem.getArchived() == null) {throw new RuntimeException("Archive value was null");}
		log.info("Journal archive saveJournalItem()");
		return journalRepo.save(journalItem);
	}

}
