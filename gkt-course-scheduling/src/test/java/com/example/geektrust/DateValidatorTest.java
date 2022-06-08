package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateValidatorTest {

	@Test
	void test() {
		try {
			DateValidator invalidDate = new DateValidator("62152022");
		} catch (Exception ex) {
			assertEquals(ex.getMessage(), "INPUT_DATA_ERROR");
		}
	}

}
