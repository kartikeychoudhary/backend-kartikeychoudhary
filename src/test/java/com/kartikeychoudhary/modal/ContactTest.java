package com.kartikeychoudhary.modal;

import org.junit.jupiter.api.Test;

import com.kartikeychoudhary.util.POJOTestUtil;

class ContactTest {

	@Test
	void testAccessors() {
		POJOTestUtil.validateAccessors(Contact.class);
	}

}
