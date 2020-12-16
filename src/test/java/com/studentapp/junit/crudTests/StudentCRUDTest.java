package com.studentapp.junit.crudTests;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCRUDTest extends TestBase {
	
	static String randomValue = TestUtils.getRandomValue();
	
	static String firstName = "fnameUser"+randomValue;
	static String lastName = "lnameUser"+randomValue;
	static String trainingProgramme = "foo";
	static String emailID = randomValue+"fl@gmail.com";
	
	@Title("This test will create a new student")
	@Test
	public void t1() {
		
		ArrayList<String> courses =new ArrayList<String>();
		courses.add("Java");
		courses.add("python");
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(emailID);
		student.setProgramme(trainingProgramme);
		student.setCourses(courses);
		
		/**
		 *  you have write 
		 *  SerenityRest.rest().given() 
		 *  to print out the logs in reports
		 * 
		 * */
		
		SerenityRest.rest().given()
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
	
	@Title("This test extracts the created student record")
	@Test
	public void t2() {
		
		/*  Extract all the values for the firstName we are sending 
		 * 
		 * */
		String p1 ="findAll{it.firstName=='";
		String p2 ="'}.get{0}";
		
		HashMap<String,Object> value = SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()    
		.path(p1+firstName+p2); 
		
		// .path("findAll{it.firstName==''}.firstName.get{0}");  returns only firstnames of all values
		// findAll{it.firstName==''}.get{0}

		// findAll returns a hashmap 
		// it is a json path syntax from restAssured
		
		System.out.println("value extracted -"+value);
		
	}
	
	
	@Title("This test validates the extracted student record")
	@Test
	public void t3() {
		
		
	}

}
