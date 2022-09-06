package com.kartikeychoudhary.dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.kartikeychoudhary.util.POJOTestUtil;

class ContactDTOTest {

	@Test
	void testAccessors() {
		POJOTestUtil.validateAccessors(ContactDTO.class);
	}

	
	@Test
	void testContactDTOConvert() {
		ContactDTO dto = new ContactDTO();
		dto.setName("name");
		assertEquals("name", dto.convert().getName());
	}
}
