package com.kartikeychoudhary.modal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class TodoTest {

	@Test
	void testAccessors() {
		Todo item = new Todo();
		item.setArchived(false);
		item.setDate(new Date(20220901));
		item.setDescription("description");
		item.setCompleted(false);
		item.setId(1L);
		assertNotNull(item.getId());
		assertEquals("description", item.getDescription());
		assertNotNull(item.getDate());
		assertEquals(false , item.getArchived());
		assertEquals(false , item.getCompleted());
	}	

}
