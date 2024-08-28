package Statement2.statement2;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

public class UserApiHttpClientTest {

	private static final String BASE_URL = "https://bfhldevapigw.healthrx.co.in/automation-campus/create/user";
	private static final String ROLL_NUMBER = "1";

	private HttpResponse sendPostRequest(String jsonBody, boolean includeRollNumber) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(BASE_URL);

		if (includeRollNumber) {
			post.setHeader("roll-number", ROLL_NUMBER);
		}
		post.setHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(jsonBody));

		return client.execute(post);
	}

	@Test
	public void testCreateUserWithValidDetails() throws IOException {
		String jsonBody = "{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"phoneNumber\": 1234567890, \"emailId\": \"john.doe@test.com\" }";
		HttpResponse response = sendPostRequest(jsonBody, true);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	}

	@Test
	public void testCreateUserWithDuplicatePhoneNumber() throws IOException {
		String jsonBody = "{ \"firstName\": \"Jane\", \"lastName\": \"Doe\", \"phoneNumber\": 9999999999, \"emailId\": \"jane.doe@test.com\" }";
		HttpResponse response = sendPostRequest(jsonBody, true);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	}

	@Test
	public void testCreateUserWithoutRollNumber() throws IOException {
		String jsonBody = "{ \"firstName\": \"Test\", \"lastName\": \"User\", \"phoneNumber\": 1234567891, \"emailId\": \"test.user@test.com\" }";
		HttpResponse response = sendPostRequest(jsonBody, false);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 401);
	}

	@Test
	public void testCreateUserWithDuplicateEmail() throws IOException {
		String jsonBody = "{ \"firstName\": \"Duplicate\", \"lastName\": \"User\", \"phoneNumber\": 9876543210, \"emailId\": \"duplicate@test.com\" }";
		HttpResponse response = sendPostRequest(jsonBody, true);
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	}



}
