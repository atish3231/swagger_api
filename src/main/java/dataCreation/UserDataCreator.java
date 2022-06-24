package dataCreation;

import com.github.javafaker.Faker;

import data_Model.UserDataClass;

import java.util.ArrayList;
import java.util.List;

public class UserDataCreator {

	public static UserDataClass user;

	public static Faker fake = new Faker();

	public UserDataClass DataForUSerCreation() {
		user = new UserDataClass();
		user.setId(fake.number().randomDigitNotZero());
		user.setUsername(fake.name().username());
		user.setFirstName(fake.name().firstName());
		user.setLastName(fake.name().lastName());
		user.setEmail(fake.internet().emailAddress());
		user.setPassword(fake.internet().password());
		user.setPhone(fake.phoneNumber().phoneNumber());
		user.setUserStatus("1");
		return user;
	}

	public List<UserDataClass> createUser(long numberOfUsers) {

		List<UserDataClass> userList = new ArrayList<>();

		for (int count = 0; count < numberOfUsers; count++) {

			userList.add(DataForUSerCreation());
		}

		return userList;
	}

}
