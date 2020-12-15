package com.studentapp.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class FirstSerenityBDDRestAssuredTest {

	@Title("Title - get all students info ")
	@Test
	public void getAllStudents() {

		RestAssured.baseURI = "http://localhost:8080/student";
		SerenityRest.given().when().get("/list").then().log().all().statusCode(200);
	}

	@Test
	public void thisTestFails() {

		RestAssured.baseURI = "http://localhost:8080/student";
		SerenityRest.given().when().get("/list").then().log().all().statusCode(500);
	}

	@Ignore
	@Test
	public void thisTestSkipped() {
	}

	@Test
	public void thisTestThrowsError() {

		System.out.println("This is an error -" + 1 / 0);
	}

	@Test
	public void fileDoesntExist() throws FileNotFoundException {

		File file = new File("N://nofile.txt");
		FileReader fileReader = new FileReader(file);
	}

	@Manual
	@Test
	public void thisIsManualTest() {
	}

}
