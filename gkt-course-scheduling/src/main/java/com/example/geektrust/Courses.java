package com.example.geektrust;

import java.util.HashMap;
import java.util.Map;

public class Courses {
	
	private static Map<String, Course> courses = new HashMap<>();

	public static void addCourse(String courseId, Course course) {
		courses.put(courseId, course);
	}
	
	public static Course getCourse(String courseId) {
		return courses.get(courseId);
	}
	
	

	public static String getCourseName(String courseId) {
		return courses.get(courseId).courseName();
	}

	public static void addRegCourse(String courseId, RegisterCourse regCourse) {
		courses.get(courseId).addRegisterCourse(regCourse);
	}
}
