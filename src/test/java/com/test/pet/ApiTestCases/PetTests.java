package com.test.pet.ApiTestCases;

import static io.restassured.RestAssured.given;
import static utils.ConstantsClass.baseUri;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import dataCreation.PetDataCreator;
import data_Model.PetDataClass;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PetTests extends BaseTest {

	public static String PET_ENDPOINT = baseUri + "/pet";

	PetDataCreator pet_data_creator = new PetDataCreator();
	PetDataClass pet = pet_data_creator.petCreationData();
	Object id = 0;
	String status = "";
	String name = "";

	@Test(priority = 0)
	public void addNewPet() {

		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON).body(pet)
				.post(PET_ENDPOINT);
		JsonPath jsonPathEvaluator = response.jsonPath();
		id = jsonPathEvaluator.get("id");
		response.then().statusCode(200);
		response.prettyPrint();
	}

	@Test(priority = 2, dependsOnMethods = "addNewPet")
	public void updatePet() {

		int i = (int) id;
		PetDataClass pet = pet_data_creator.petDataforUpdate(i);
		Response response = given().header("Content-Type", "application/json").body(pet).put(PET_ENDPOINT);
		response.prettyPrint();
		response.then().statusCode(200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		name = jsonPathEvaluator.get("name");
		status = jsonPathEvaluator.get("status");
		Assert.assertEquals(name, pet.name);
		Assert.assertEquals(status, pet.status);

	}

	@Test(priority = 3, dependsOnMethods = "updatePet")
	public void getPetsByStatus() {
		Response response = given().header("Content-Type", "application/json").queryParam("status", status)
				.get(PET_ENDPOINT + "/findByStatus");
		response.prettyPrint();
		response.then().statusCode(200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<String> getAllNames = jsonPathEvaluator.get("name");
		if (!getAllNames.contains(name)) {
			Assert.fail("Pet Name not matched when called by Id and status" + "Status" + status);
		}
	}

}
