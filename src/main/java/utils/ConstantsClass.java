package utils;

public class ConstantsClass {
	public static final String baseUri = "https://petstore.swagger.io/v2";
	public static final String createMultipleUserEndPoint = "/user/createWithArray";
	public static final String updatePetStatusEndPoint = "/pet";
	public static final String updateUserDetailEndPoint = "/user/";
	public static final String getPetStatusEndPoint = "/pet/findByStatus";
	public static final String createMultiplePetEndPoint = "/pet";

	public static int value(Object id) {
		int i = 0;

		if (id instanceof Integer) {
			i = (int) id;
		} else if (id instanceof Long) {
			long l = (long) id;

			i = (int) l;
		}
		return i;
	}
}
