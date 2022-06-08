package com.example.geektrust;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateValidator {

	DateValidator(String validateDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy");
			sdf.setLenient(false);
			Date date = sdf.parse(validateDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			String courseDate = new SimpleDateFormat("ddmmyyyy").format(cal.getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException("INPUT_DATA_ERROR");
		}
	}
}
