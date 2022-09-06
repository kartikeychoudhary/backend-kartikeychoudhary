package com.kartikeychoudhary.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class JournalItemDTOTest {

	@Test
	void testAccessors() {
		JournalItemDTO item = new JournalItemDTO();
		item.setArchived(false);
		item.setDate(new Date(20220901));
		item.setDescription("description");
		item.setId(1L);
		assertNotNull(item.getId());
		assertEquals("description", item.getDescription());
		assertNotNull(item.getDate());
		assertEquals(false , item.getArchived());
	}

	@Test
	void testContactDTOConvert() {
		JournalItemDTO dto = new JournalItemDTO();
		dto.setDescription("description");
		assertEquals("description", dto.convert().getDescription());
	}

}
