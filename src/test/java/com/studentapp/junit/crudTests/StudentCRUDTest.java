package com.studentapp.junit.crudTests;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.hamcrest.Matcher;
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

import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCRUDTest extends TestBase {
	
	static String randomValue = TestUtils.getRandomValue();
	
	static String firstName = "fnameUser"+randomValue;
	static String lastName = "lnameUser"+randomValue;
	static String trainingProgramme = "foo";
	static String emailID = randomValue+"fl@gmail.com";
	
	static String studentId;
	
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
		studentId = (String) value.get("id");
		
//		assertThat(value, hasvalue(firstName));
	}
	
	

	@Title("update the student info and validate info weather updated")
	@Test
	public void t3() {
		
		ArrayList<String> courses =new ArrayList<String>();
		courses.add("Java");
		courses.add("python");
		
		firstName = firstName+"_updated";
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(emailID);
		student.setProgramme(trainingProgramme);
		student.setCourses(courses);
		
			
		SerenityRest.rest().given()
		.contentType(ContentType.JSON)
		.log()
		.all()
		.when()
		.body(student)
		.put("/"+studentId)
		.then()
		.log()
		.all();
	}

	@Title("This test verifies the updated request")
	@Test
	public void t4() {
		
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
		
		System.out.println("value extracted -"+value);

//		assertThat(value, hasvalue(firstName));
	}
	
	@Title("Delete a student and verify if deleted")
	@Test
	public void t5() {
		
		SerenityRest.rest().given()
		.when()
		.delete("/"+"100"); //studentId
	
		SerenityRest.rest().given()
		.when()
		.get("/"+ "100")
		.then()
		.log()
		.all()
		.statusCode(404);
		
	
		
	}
}
