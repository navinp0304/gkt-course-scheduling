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
	
	void createCourse(String course_name, String instructor, String date, Integer
	minEmployees, Integer maxEmployees){
		this.coursename=course_name;
		this.instructor=instructor;
		this.date = date;
		this.minEmployees = minEmployees;
		this.maxEmployees = maxEmployees;
	}

	// Course(String course_name, String instructor, String date, Integer
	// minEmployees, Integer maxEmployees)
	Course(String fullCommand) {
		try {
			String[] tokens = fullCommand.split("\\s+");
			DateValidator dateService = new DateValidator(tokens[3]);
			createCourse(tokens[1],tokens[2],tokens[3],Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]));
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