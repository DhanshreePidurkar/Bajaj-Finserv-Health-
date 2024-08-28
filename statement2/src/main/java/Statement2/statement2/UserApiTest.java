package Statement2.statement2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class UserApiTest {

	private static final String BASE_URL = "https://bfhldevapigw.healthrx.co.in/automation-campus/create/user";
	private static final String ROLL_NUMBER = "1";

	@Test
	public void testCreateUserWithValidDetails() throws Exception {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("firstName", "John");
		requestBody.put("lastName", "Doe");
		requestBody.put("phoneNumber", 1234567890);
		requestBody.put("emailId", "john.doe@test.com");

		String response = sendPostRequest(BASE_URL, requestBody);
		System.out.println(response);

		assertStatusCode(response, 201);
	}

	@Test
	public void testCreateUserWithDuplicatePhoneNumber1() throws Exception {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("firstName", "Jane");
		requestBody.put("lastName", "Doe");
		requestBody.put("phoneNumber", 9999999999L);
		requestBody.put("emailId", "jane.doe@test.com");

		String response = sendPostRequest(BASE_URL, requestBody);
		System.out.println(response);

		assertStatusCode(response, 400);
	}

	@Test
	public void testCreateUserWithoutRollNumber1() throws Exception {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("firstName", "Test");
		requestBody.put("lastName", "User");
		requestBody.put("phoneNumber", 1234567891);
		requestBody.put("emailId", "test.user@test.com");

		String response = sendPostRequest(BASE_URL, requestBody);
		System.out.println(response);

		assertStatusCode(response, 401);
	}

	@Test
	public void testCreateUserWithDuplicatePhoneNumber() throws Exception {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("firstName", "Jane");
		requestBody.put("lastName", "Doe");
		requestBody.put("phoneNumber", 9999999999L); // Note the L suffix for long literal
		requestBody.put("emailId", "jane.doe@test.com");

		String response = sendPostRequest(BASE_URL, requestBody);
		System.out.println(response);

		assertStatusCode(response, 400);
	}

	@Test
	public void testCreateUserWithoutRollNumber() throws Exception {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("firstName", "Test");
		requestBody.put("lastName", "User");
		requestBody.put("phoneNumber", 1234567891L); // Note the L suffix for long literal
		requestBody.put("emailId", "test.user@test.com");

		String response = sendPostRequest(BASE_URL, requestBody);
		System.out.println(response);

		assertStatusCode(response, 401);
	}

	@Test
	public void testCreateUserWithDuplicateEmail() throws Exception {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("firstName", "Duplicate");
		requestBody.put("lastName", "User");
		requestBody.put("phoneNumber", 9876543210L); // Note the L suffix for long literal
		requestBody.put("emailId", "duplicate@test.com");

		String response = sendPostRequest(BASE_URL, requestBody);
		System.out.println(response);

		assertStatusCode(response, 400);
	}

	private String sendPostRequest(String baseUrl, Map<String, Object> requestBody) {
		// TODO Auto-generated method stub
		return null;
	}

	private void assertStatusCode(String response, int expectedStatus) {
		int statusCode = Integer.parseInt(response.split(":")[0].trim());
		Assert.assertEquals(statusCode, expectedStatus);
	}

}
