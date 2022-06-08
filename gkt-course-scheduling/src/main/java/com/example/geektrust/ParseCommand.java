package com.example.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;


public class ParseCommand {
	Course course = null;

	private Map<String,BiConsumer<String,Course>> commandExecutor=Map.of(		
			"ADD-COURSE-OFFERING", (addCourse,dummyCourse) -> {
				try {
					this.course = new Course(addCourse);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			},
			"REGISTER", (registerCmd,dummyCourse) -> {
				try {
					RegisterCourse regCourse = new RegisterCourse(registerCmd);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			},"ALLOT", (allotCmd,course)		-> {			
				try {
				AllotCourse allotCourse = new AllotCourse(allotCmd);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				} 
			},"CANCEL", (cancelCmd,course) -> {
				CancelRegCourse cancelRegCourse = new CancelRegCourse(cancelCmd, course);
			}
		
	);

	public void run(String inputFileName) {
		Scanner input = null;
		try {
			input = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException e) {
			return ;
		}

		while (input.hasNextLine()) {
			String fullCommand = new String(input.nextLine());
			String[] commands = fullCommand.split("\\s+");
			String command=commands[0];
			commandExecutor.get(command).accept(fullCommand,this.course);
		}
	}
}