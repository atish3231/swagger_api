package com.test.pet.ApiTestCases;

import static io.restassured.RestAssured.given;
import static utils.ConstantsClass.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import dataCreation.PetDataCreator;
import data_Model.PetDataClass;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PetTests extends BaseTest {

	public static String PET_ENDPOINT = baseUri + "/pet";

	PetDataCreator pet_data_creator = new PetDataCreator();
	PetDataClass pet = pet_data_creator.petCreationData();
	Object id = 0;
	String status = "";
	String name = "";

	@Test(priority = 0, description = "Add a new pet")
	public void addNewPet() throws Exception {

		Response response = given().header("Content-Type", "application/json").body(pet).post(PET_ENDPOINT);
		JsonPath jsonPathEvaluator = response.jsonPath();
		id = jsonPathEvaluator.get("id");
		// Assert status code
		response.then().statusCode(200);
		// writing in extent report
		writeRequestAndResponseInReport(response.prettyPrint());
	}

	@Test(dependsOnMethods = "addNewPet", description = "Verify the new pet using the pet id")
	public void verifyNewPet() throws Exception {
		int i = value(id);
		Response response = given().header("Content-Type", "application/json").get(PET_ENDPOINT + "/" + i);
		// Assert status code
		response.then().statusCode(200);
		// writing in extent report
		writeRequestAndResponseInReport(response.prettyPrint());
	}

	@Test(dependsOnMethods = "verifyNewPet", description = "Update the pet name along with pet status ")
	public void updatePet() throws Exception {

		int i = value(id);

		PetDataClass pet = pet_data_creator.petDataforUpdate(i);
		Response response = given().header("Content-Type", "application/json").body(pet).put(PET_ENDPOINT);
		// Assert status code
		response.then().statusCode(200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		name = jsonPathEvaluator.get("name");
		status = jsonPathEvaluator.get("status");
		// assertion on validating the pet is updated successfully
		Assert.assertEquals(name, pet.name);
		Assert.assertEquals(status, pet.status);
		// writing in extent report
		writeRequestAndResponseInReport(response.prettyPrint());
	}

	@Test(dependsOnMethods = "updatePet", description = "Validate pet by call the status of that pet")
	public void getPetsByStatus() throws Exception {
		Response response = given().header("Content-Type", "application/json").queryParam("status", status)
				.get(PET_ENDPOINT + "/findByStatus");
		// Assert status code
		response.then().statusCode(200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<String> getAllNames = jsonPathEvaluator.get("name");
		if (!getAllNames.contains(name)) {
			// assertion when fetching pet by last updated status and validating the pet is
			// updated or not with the status
			Assert.fail("Pet Name not matched when called by Id and status" + "Status" + status);
		}
		// writing in extent report
		writeRequestAndResponseInReport(response.prettyPrint());
	}

}
