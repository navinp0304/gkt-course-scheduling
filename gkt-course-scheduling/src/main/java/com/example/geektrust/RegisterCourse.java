package com.example.geektrust;

public class RegisterCourse {
	private String email;
	private String courseId;
	private String regCourseName;

	RegisterCourse(String fullCommand) {
		try {
			String[] tokens = fullCommand.split("\\s+");
			email = tokens[1];
			courseId = tokens[2];
			String[] emailtokens = email.split("@");
			String user = emailtokens[0];
			regCourseName = "REG-COURSE-" + user + "-" + Courses.getCourseName(courseId);
			Courses.addRegCourse(courseId, this);

			System.out.println(display() + " ACCEPTED");
		} catch (IllegalArgumentException iex) {
//			iex.printStackTrace();
			throw new IllegalArgumentException("COURSE_FULL_ERROR");
		} catch (ArrayIndexOutOfBoundsException aex) {
			throw new IllegalArgumentException("INPUT_DATA_ERROR");
		} catch (Exception ex) {
			throw ex;
		}

	}

	public String display() {
		return regCourseName;
	}

	public String getEmail() {
		return email;
	}

}
