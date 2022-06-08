package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class ParseCommandTest {

	@Test
	void testParseCommand() {
		ParseCommand pcmd = new ParseCommand();
		assertNotEquals(pcmd,null);
	}
	
	@Test
	void testRun2() {
		ParseCommand parseCommand = new ParseCommand();
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		String inputData = "ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 3\n"
				+ "REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "ALLOT OFFERING-DATASCIENCE-BOB\n";
		
		String expected = "OFFERING-DATASCIENCE-BOB\n"
				+ "REG-COURSE-WOO-DATASCIENCE ACCEPTED\n"
				+ "REG-COURSE-ANDY-DATASCIENCE ACCEPTED\n"
				+ "REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED\n"
				+ "REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED\n"
				+ "";
				
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setIn(new ByteArrayInputStream(inputData.getBytes()));

		parseCommand.run("sample_input/input2.txt");

		System.setIn(stdin);
		System.setOut(stdout);
		assertEquals(expected, outContent.toString());
	}
	
	
	
	@Test
	void testRun3() {
		ParseCommand parseCommand = new ParseCommand();
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		String inputData = "ADD-COURSE-OFFERING PYTHON JOHN 05062022 1 3\n"
				+ "REGISTER WOO@GMAIL.COM OFFERING-PYTHON-JOHN\n"
				+ "REGISTER ANDY@GMAIL.COM OFFERING-PYTHON-JOHN\n"
				+ "REGISTER BOBY@GMAIL.COM OFFERING-PYTHON-JOHN\n"
				+ "CANCEL REG-COURSE-BOBY-PYTHON\n"
				+ "ALLOT OFFERING-PYTHON-JOHN\n";
		
		String expected = "OFFERING-PYTHON-JOHN\n"
				+ "REG-COURSE-WOO-PYTHON ACCEPTED\n"
				+ "REG-COURSE-ANDY-PYTHON ACCEPTED\n"
				+ "REG-COURSE-BOBY-PYTHON ACCEPTED\n"
				+ "REG-COURSE-BOBY-PYTHON CANCEL_ACCEPTED\n"
				+ "REG-COURSE-ANDY-PYTHON ANDY@GMAIL.COM OFFERING-PYTHON-JOHN PYTHON JOHN 05062022 CONFIRMED\n"
				+ "REG-COURSE-WOO-PYTHON WOO@GMAIL.COM OFFERING-PYTHON-JOHN PYTHON JOHN 05062022 CONFIRMED\n";
				
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setIn(new ByteArrayInputStream(inputData.getBytes()));

		parseCommand.run("sample_input/input3.txt");

		System.setIn(stdin);
		System.setOut(stdout);
		assertEquals(expected, outContent.toString());
	}

	@Test
	void testRun4() {
		ParseCommand parseCommand = new ParseCommand();
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		String inputData = "ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 1 2\n"
				+ "REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "REGISTER BOBY@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "ALLOT OFFERING-DATASCIENCE-BOB\n";
		
		String expected = "OFFERING-DATASCIENCE-BOB\n"
				+ "REG-COURSE-ANDY-DATASCIENCE ACCEPTED\n"
				+ "REG-COURSE-WOO-DATASCIENCE ACCEPTED\n"
				+ "COURSE_FULL_ERROR\n"
				+ "REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED\n"
				+ "REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED\n"
				+ "";
				
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setIn(new ByteArrayInputStream(inputData.getBytes()));

		parseCommand.run("sample_input/input4.txt");

		System.setIn(stdin);
		System.setOut(stdout);
		assertEquals(expected, outContent.toString());
	}

	@Test
	void testRun5() {
		ParseCommand parseCommand = new ParseCommand();
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		String inputData = "ADD-COURSE-OFFERING DATASCIENCE BOB 05062022 3 5\n"
				+ "REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "REGISTER ALICE@GMAIL.COM \n"
				+ "ALLOT OFFERING-DATASCIENCE-BOB\n"
				+ "";
		
		String expected = "OFFERING-DATASCIENCE-BOB\n"
				+ "REG-COURSE-ANDY-DATASCIENCE ACCEPTED\n"
				+ "REG-COURSE-WOO-DATASCIENCE ACCEPTED\n"
				+ "INPUT_DATA_ERROR\n"
				+ "REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 COURSE_CANCELED\n"
				+ "REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 COURSE_CANCELED\n"
				+ "";
				
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setIn(new ByteArrayInputStream(inputData.getBytes()));

		parseCommand.run("sample_input/input5.txt");

		System.setIn(stdin);
		System.setOut(stdout);
		assertEquals(expected, outContent.toString());
	}

	@Test
	void testNoFile() {
		ParseCommand parseCommand = new ParseCommand();

		parseCommand.run("nosuchfilehere/nodir");
		assertNotEquals(parseCommand,null);
	}
	
	@Test
	void testRun6() {
		ParseCommand parseCommand = new ParseCommand();
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		String inputData = "ADD-COURSE-OFFERING DATASCIENCE BOB 05362022 1 3\n"
				+ "REGISTER WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "REGISTER ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB\n"
				+ "ALLOT OFFERING-DATASCIENCE-BOB\n";
		
		String expected = "OFFERING-DATASCIENCE-BOB\n"
				+ "REG-COURSE-WOO-DATASCIENCE ACCEPTED\n"
				+ "REG-COURSE-ANDY-DATASCIENCE ACCEPTED\n"
				+ "REG-COURSE-ANDY-DATASCIENCE ANDY@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED\n"
				+ "REG-COURSE-WOO-DATASCIENCE WOO@GMAIL.COM OFFERING-DATASCIENCE-BOB DATASCIENCE BOB 05062022 CONFIRMED\n"
				+ "";
				
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setIn(new ByteArrayInputStream(inputData.getBytes()));

		parseCommand.run("sample_input/input2.txt");

		System.setIn(stdin);
		System.setOut(stdout);
		assertEquals(expected, outContent.toString());
	}
	
	
	
}
