package com.studentapp.junit;

import org.junit.Ignore;
import org.junit.Test;

import com.studentapp.testbase.TestBase;

import io.restassured.RestAssured;

public class JunitBDDRestAssuredTest extends TestBase {

	@Ignore
	@Test
	public void getAllStudents() {

		RestAssured.baseURI = "http://localhost:8080/student";

		RestAssured.given().when().get("/list").then().log().all().statusCode(200);
	}

}
