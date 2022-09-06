package com.kartikeychoudhary.modal;

import org.junit.jupiter.api.Test;

import com.kartikeychoudhary.util.POJOTestUtil;

class UserTest {

	@Test
	void testAccessors() {
		POJOTestUtil.validateAccessors(User.class);
	}

}
