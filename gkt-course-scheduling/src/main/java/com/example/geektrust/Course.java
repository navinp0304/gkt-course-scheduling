package com.example.geektrust;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private String coursename;
	private String instructor;
	private String date;
	private Integer minEmployees;
	private Integer maxEmployees;
	private List<RegisterCourse> regCourses = new ArrayList<>();
	private String courseId;

	// Course(String course_name, String instructor, String date, Integer
	// minEmployees, Integer maxEmployees)
	Course(String fullCommand) {
		try {
			String[] tokens = fullCommand.split("\\s+");
			this.coursename = tokens[1];
			this.instructor = tokens[2];
			DateValidator dateService = new DateValidator(tokens[3]);
			this.date = tokens[3]; // after validation
			this.minEmployees = Integer.parseInt(tokens[4]);
			this.maxEmployees = Integer.parseInt(tokens[5]);
			courseId = "OFFERING-" + this.coursename + "-" + this.instructor;
			Courses.addCourse(courseId, this);
			System.out.println(courseId);
		} catch (Exception ex) {
			throw new ArrayIndexOutOfBoundsException("INPUT_DATA_ERROR");
		}
	}

	private Boolean throwCourseFull() {
		throw new IllegalArgumentException("COURSE_FULL_ERROR");
	}

	public void addRegisterCourse(RegisterCourse regCourse) {
		Boolean retval = (regCourses.size() == maxEmployees) ? throwCourseFull() : false;
		regCourses.add(regCourse);
	}

	public void removeCourse(String regCourseName) {
		regCourses.removeIf(e -> e.display().equals(regCourseName));
	}

	public List<RegisterCourse> getRegCourses() {
		return regCourses;
	}

	public String courseName() {
		return this.coursename;
	}

	public String display() {
		return courseId + " " + coursename + " " + instructor + " " + date;
	}

	public Boolean isValid(Integer x) {
		return (minEmployees <= x) && (x <= maxEmployees);
	}
}