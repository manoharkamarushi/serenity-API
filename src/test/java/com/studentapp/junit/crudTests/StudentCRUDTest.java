package com.studentapp.junit.crudTests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class StudentCRUDTest extends TestBase {
	
	
	@Title("This test will create a new student")
	@Test
	public void createStudent() {
		
		ArrayList<String> courses =new ArrayList<String>();
		courses.add("Java");
		courses.add("python");
		
		StudentClass student = new StudentClass();
		student.setFirstName("fname");
		student.setLastName("lname");
		student.setEmail("fname.lastname@gmail.com");
		student.setProgramme("Training");
		student.setCourses(courses);
		
		
		SerenityRest.given()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.when()
		.body(student)
		.post()
		.then()
		.log()
		.all()
		.statusCode(201);
		
	}

}
