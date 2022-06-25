package dataCreation;

import com.github.javafaker.Faker;

import data_Model.PetDataClass;

import java.util.ArrayList;
import java.util.HashMap;

public class PetDataCreator {

	public static PetDataClass pet = new PetDataClass();
	public static Faker fake = new Faker();
	private static final String PHOTO_URL = "https://www.tesco.ie/groceries/MarketingContent/Sites/Retail/superstore/Online/P/i/departments/2016/Pets/1BC.jpg";

	public PetDataClass petDataforUpdate(int petID) {

		pet = new PetDataClass();
		HashMap<String, String> categoryMap = new HashMap<String, String>();
		categoryMap.put("id", "1");
		categoryMap.put("name", "Dogs");

		ArrayList<String> photoUrlList = new ArrayList<String>();
		photoUrlList.add(PHOTO_URL);

		HashMap<String, String> tag = new HashMap<String, String>();
		tag.put("id", "0");
		tag.put("name", "golden-retriever");

		ArrayList<HashMap<String, String>> tags = new ArrayList<HashMap<String, String>>();
		tags.add(tag);

		pet.setId(String.valueOf(petID));
		pet.setName("Updated" + fake.name().firstName());
		pet.setCategory(categoryMap);
		pet.setPhotoUrls(photoUrlList);
		pet.setTags(tags);
		pet.setStatus("Sold");

		return pet;
	}

	public PetDataClass petCreationData() {
		HashMap<String, String> categoryMap = new HashMap<String, String>();
		categoryMap.put("id", "1");
		categoryMap.put("name", "Dogs");

		ArrayList<String> photoUrlList = new ArrayList<String>();
		photoUrlList.add(PHOTO_URL);

		HashMap<String, String> tag = new HashMap<String, String>();
		tag.put("id", "0");
		tag.put("name", "golden-retriever");

		ArrayList<HashMap<String, String>> tags = new ArrayList<HashMap<String, String>>();
		tags.add(tag);

		pet.setId(fake.number().digit());
		pet.setName(fake.name().firstName());
		pet.setCategory(categoryMap);
		pet.setPhotoUrls(photoUrlList);
		pet.setTags(tags);
		pet.setStatus("available");

		return pet;
	}
}
