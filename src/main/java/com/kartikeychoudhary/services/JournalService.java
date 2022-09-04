package com.kartikeychoudhary.services;

import java.sql.Date;
import java.util.List;

import com.kartikeychoudhary.modal.JournalItem;

public interface JournalService {
	List<JournalItem> getAllJournalItemForDay(Date date);
	List<JournalItem> getAll();
	JournalItem saveUpdateJournalItem(JournalItem journalItem);
	String deleteJournalItem(JournalItem journalItem);
	JournalItem archiveJournalItem(JournalItem journalItem);
}
