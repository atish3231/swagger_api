package com.test.pet.ApiTestCases;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import BaseTest.BaseTest;
import dataCreation.UserDataCreator;
import data_Model.UserDataClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConstantsClass;

public class UserAPITest extends BaseTest {

	public static List<UserDataClass> getUserList;

	String username = "";

	@Test(priority = 0, description = "Create a list of new user's")
	public void createListOfUser() throws Exception {

		UserDataCreator us = new UserDataCreator();
		getUserList = us.createUser(5);

		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON)
				.body(getUserList).post(ConstantsClass.createMultipleUserEndPoint);
		response.then().statusCode(200);
		writeRequestAndResponseInReport(response.asPrettyString());
	}

	@Test(dependsOnMethods = "createListOfUser", description = "Update the newly created user details")
	public void updateUserDetail() throws Exception {

		UserDataCreator us = new UserDataCreator();

		UserDataClass user = us.DataForUSerCreation();
		username = user.username;
		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON).body(user)
				.put(String.format("/user/%s", getUserList.get(0).username));
		response.then().statusCode(200);
		writeRequestAndResponseInReport(response.asPrettyString());
	}

	@Test(dependsOnMethods = "updateUserDetail", description = "Get Updated user details")
	public void getUserDetail() throws Exception {

		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON)
				.get(String.format("/user/%s", username));
		response.then().statusCode(200);
		writeRequestAndResponseInReport(response.prettyPrint());
	}
}