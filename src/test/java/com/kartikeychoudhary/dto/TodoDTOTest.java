package com.kartikeychoudhary.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class TodoDTOTest {

	@Test
	void testAccessors() {
		TodoDTO item = new TodoDTO();
		item.setArchived(false);
		item.setDate(new Date(20220901));
		item.setDescription("description");
		item.setCompleted(false);
		item.setId(1L);
		assertNotNull(item.getId());
		assertEquals("description", item.getDescription());
		assertNotNull(item.getDate());
		assertEquals(false , item.getArchived());
		assertEquals(false , item.getCompleted());	}

	@Test
	void testContactDTOConvert() {
		TodoDTO dto = new TodoDTO();
		dto.setDescription("description");
		assertEquals("description", dto.convert().getDescription());
	}

}
