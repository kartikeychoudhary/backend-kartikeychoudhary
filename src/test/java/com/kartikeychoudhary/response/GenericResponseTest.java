package com.kartikeychoudhary.response;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class GenericResponseTest {

	@Test
	void testAccessors() {
		GenericResponse gr = new GenericResponse();
		gr.setPayLoad(new HashMap<>());
		gr.setStatus(HttpStatus.OK);
		assertNotNull(gr.getPayLoad());
		assertNotNull(gr.getStatus());
		gr = new GenericResponse(HttpStatus.OK, new HashMap<>());
		assertNotNull(gr.getPayLoad());
		assertNotNull(gr.getStatus());
	}

}
