package com.kartikeychoudhary.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.kartikeychoudhary.dto.JournalItemDTO;
import com.kartikeychoudhary.modal.JournalItem;
import com.kartikeychoudhary.services.JournalService;


class JournalControllerTest {
	
	private JournalService journalImpl;
	private JournalController journalController;
	
	@BeforeEach
	public void initService() {
		journalImpl = Mockito.mock(JournalService.class);
		journalController = new JournalController(journalImpl);
	}

	@Test
	void testGetJournalItem() {
		List<JournalItem> list = new ArrayList<>();
		Mockito.when(journalImpl.getAllJournalItemForDay(Mockito.any(Date.class))).thenReturn(list);
		assertNotNull(journalController.getJournalItem("20220901"));
	}

	@Test
	void testGetAllJournalItem() {
		List<JournalItem> list = new ArrayList<>();
		Mockito.when(journalImpl.getAll()).thenReturn(list);
		assertNotNull(journalController.getAllJournalItem());
	}

	@Test
	void testSaveJournalItem() {
		JournalItemDTO dto = new JournalItemDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		Mockito.when(journalImpl.saveUpdateJournalItem(Mockito.any(JournalItem.class))).thenReturn(dto.convert());
		assertNotNull(journalController.saveJournal(dto));
	}

	@Test
	void testUpdateJournalItem() {
		JournalItemDTO dto = new JournalItemDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		dto.setId(1L);
		Mockito.when(journalImpl.saveUpdateJournalItem(Mockito.any(JournalItem.class))).thenReturn(dto.convert());
		assertNotNull(journalController.updateJournal(dto));
	}

	@Test
	void testArchiveJournalItem() {
		JournalItemDTO dto = new JournalItemDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		dto.setId(1L);
		Mockito.when(journalImpl.archiveJournalItem(Mockito.any(JournalItem.class))).thenReturn(dto.convert());
		assertNotNull(journalController.archiveJournalItem(dto));
	}

	@Test
	void testDeleteJournalItem() {
		JournalItemDTO dto = new JournalItemDTO();
		dto.setArchived(false);
		dto.setDate(new Date(20220901));
		dto.setDescription("description");
		dto.setId(1L);
		Mockito.when(journalImpl.deleteJournalItem(Mockito.any(JournalItem.class))).thenReturn("done");
		assertNotNull(journalController.deleteJournalItem(dto));
	}


}
