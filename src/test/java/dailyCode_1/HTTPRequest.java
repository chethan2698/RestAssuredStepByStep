package dailyCode_1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * 
REST Assured : is an open-source Java library that makes it easy to test RESTful APIs. 
It simplifies the process of sending HTTP requests, asserting responses, and handling JSON data. 
REST Assured abstracts away much of the boilerplate code involved in working directly with 
HttpURLConnection or other low-level libraries.


given()
	content type, set cookies, add auth, add para, set header info etc .....

when()
	get(), post(), put(), delete() etc.....
	
then()
	validate status code, extract response, extract headers cookies & response body .........

*/

public class HTTPRequest {

	int id;

	@Test(priority = 0)
	void GetRequest() {

		baseURI = "https://reqres.in/api";

		given()

		.when().get("/users?page=2")
		.then()
		.statusCode(200).body("page", equalTo(2))
		.body("data[0].id", equalTo(7)).body("data[1].first_name", equalTo("Lindsay")).log().all();

	}

	@Test(priority = 1)
	void PostRequest() {

		baseURI = "https://reqres.in/api";

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Chethan");
		data.put("job", "Test Engineer");

		// eg 1 : Create User
//		given()
//			.contentType("application/json")
//			.body(data)
//		.when()
//			.post("/users")
//		.then()
//			.log().all();

		// eg 2 : get created id
		id = given().contentType("application/json").body(data)
			.when().post("/users").jsonPath().getInt("id");

		System.out.println(id);

	}

	@Test(priority = 2, dependsOnMethods = { "PostRequest" })
	void PutRequest() {

		baseURI = "https://reqres.in/api";

		HashMap data = new HashMap();
		data.put("name", "Virat");
		data.put("job", "Cricketer");

		given().contentType("application/json").body(data)
		.when().put(String.format("/users/%s", id))
		.then().statusCode(200).log().all();

	}

	@Test(priority = 3)
	void DeleteRequest() {

		baseURI = "https://reqres.in/api";

		given()
		.when().delete(String.format("/users/%s", id))
		.then().statusCode(204).log().all();

	}

}
