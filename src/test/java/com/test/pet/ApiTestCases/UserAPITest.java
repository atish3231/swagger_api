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

	@Test(priority = 0)
	public void createListOfUser() {

		UserDataCreator us = new UserDataCreator();
		getUserList = us.createUser(5);

		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON)
				.body(getUserList).post(ConstantsClass.createMultipleUserEndPoint);
		response.prettyPrint();

		response.then().statusCode(200);
		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
	}

	@Test(dependsOnMethods = "createListOfUser", priority = 1)
	public void updateUserDetail() {

		UserDataCreator us = new UserDataCreator();

		UserDataClass user = us.DataForUSerCreation();
		username = user.username;
		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON).body(user)
				.put(String.format("/user/%s", getUserList.get(0).username));
		response.prettyPrint();

		response.then().statusCode(200);
		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
	}

	@Test(dependsOnMethods = "updateUserDetail", priority = 2)
	public void getUserDetail() {

		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON)
				.get(String.format("/user/%s", username));
		response.prettyPrint();

		response.then().statusCode(200);
		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
	}
}