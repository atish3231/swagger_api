package data_Model;

import java.util.ArrayList;
import java.util.HashMap;

public class PetDataClass {

	public String id;
	public String name;
	public HashMap<String, String> category;
	public ArrayList<String> photoUrls;
	public HashMap<String, String> tag;
	public ArrayList<HashMap<String, String>> tags;
	public String status;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;

	}

	public void setCategory(HashMap<String, String> category) {
		this.category = category;

	}

	public void setTag(HashMap<String, String> tag) {
		this.tag = tag;

	}

	public void setPhotoUrls(ArrayList<String> photoUrlList) {
		this.photoUrls = photoUrlList;

	}

	public void setTags(ArrayList<HashMap<String, String>> tags2) {
		this.tags = tags2;

	}

	public void setStatus(String status) {
		this.status = status;

	}

}
