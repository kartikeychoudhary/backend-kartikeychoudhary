package com.kartikeychoudhary.implementations;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.kartikeychoudhary.modal.JournalItem;
import com.kartikeychoudhary.repository.JournalRepo;

class JournalImplementationsTest {
	
	private JournalRepo journalRepo;
	
	private JournalImplementations journalImplementations;
	
	@BeforeEach
	void initService() {
		journalRepo = Mockito.mock(JournalRepo.class);
		journalImplementations = new JournalImplementations(journalRepo);
	}

	@Test
	void testGetAllJournalItemForDay() {
		List<JournalItem> items = new ArrayList<>();
		items.add(new JournalItem());
		Mockito.when(journalRepo.findAllJournalItemByDate(Mockito.any(Date.class))).thenReturn(items);
		Date date = new Date(20220801);
		assertTrue(journalImplementations.getAllJournalItemForDay(date).size()>0);
	}

	@Test
	void testGetAll() {
		List<JournalItem> items = new ArrayList<>();
		items.add(new JournalItem());
		Mockito.when(journalRepo.findAll()).thenReturn(items);
		assertTrue(journalImplementations.getAll().size()>0);
	}

	@Test
	void testSaveUpdateJournalItem() {
		JournalItem item = new JournalItem();
		Mockito.when(journalRepo.save(item)).thenReturn(item);
		assertNotNull(journalImplementations.saveUpdateJournalItem(item));
	}

	@Test
	void testDeleteJournalItem() {
		JournalItem item = new JournalItem();
		Mockito.doNothing().when(journalRepo).delete(item);
		journalImplementations.deleteJournalItem(item);
		assertThatNoException();
	}

	@Test
	void testArchiveJournalItem() {
		JournalItem item = new JournalItem();
		item.setArchived(false);
		Mockito.when(journalRepo.save(Mockito.any(JournalItem.class))).thenReturn(item);
		assertTrue(journalImplementations.archiveJournalItem(item).getArchived());
	}

}
