package com.kartikeychoudhary;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MainApplicationTests {

	@Test
	void applicationContextTest() {
	    MainApplication.main(new String[] {});
	    assertThatNoException();
	}

}
