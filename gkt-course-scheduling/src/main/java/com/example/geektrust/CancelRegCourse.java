package com.example.geektrust;

public class CancelRegCourse {
	CancelRegCourse(String fullCommand,Course course) {
		String[] tokens = fullCommand.split("\\s+");
		String regCourse = tokens[1];
		course.removeCourse(regCourse);
		System.out.println(regCourse +" CANCEL_ACCEPTED");
	}
}