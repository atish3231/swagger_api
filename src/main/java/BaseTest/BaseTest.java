package BaseTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import utils.ConstantsClass;

public class BaseTest {

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = ConstantsClass.baseUri;
	}

	public String generateStringFromResource(String path) throws IOException {
		String temp = "";
		try {
			temp = new String(Files.readAllBytes(Paths.get(path)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}