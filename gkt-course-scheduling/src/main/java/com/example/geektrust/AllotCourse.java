package com.example.geektrust;

import java.util.List;

public class AllotCourse {
	AllotCourse(String fullCommand) {
		String courseId = fullCommand.split("\\s+")[1];
		Course course = Courses.getCourse(courseId);
		List<RegisterCourse> regCourses = course.getRegCourses();
		regCourses.sort((s1, s2) -> s1.getEmail().compareTo(s2.getEmail()));
		String message = course.isValid(regCourses.size()) ? "CONFIRMED":"COURSE_CANCELED";
		for (RegisterCourse regCourse : regCourses) {
			System.out
					.println(regCourse.display() + " " + regCourse.getEmail() + " " + course.display() + " " + message);
		}
	}
}
