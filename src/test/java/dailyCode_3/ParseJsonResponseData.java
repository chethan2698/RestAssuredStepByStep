package dailyCode_3;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;


public class ParseJsonResponseData {
	
	@Test
	public void TestJsonResponse() {
		
		Approach1();
		
		Approach2();
		
	}
	
	
	static void Approach1() {
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("data[2].id", equalTo(9))
			.body("data[0].first_name", equalTo("Michael"))
			.log().all()
	;
		
	}
		
	static void Approach2() {
		
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		;
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String fn = res.jsonPath().get("data[0].email").toString();
		Assert.assertEquals("michael.lawson@reqres.in", fn);
		
	}
	
	@Test
	public void TestJsonResponseBodyData() throws ParseException {
		
		Response res = given()
				.contentType(ContentType.JSON)
			.when()
				.get("https://reqres.in/api/users?page=2")
			;
		
		// converting response to json object
		JSONObject jo = new JSONObject(res.asString()); //import org.json.JSONObject;
		
		// getting all email in json array and check rachel.howell@reqres.in in array 
		String email = "rachel.howell@reqres.in";
		boolean flag = false;
		
		for(int i = 0; i < jo.getJSONArray("data").length(); i++) {
			
			String emailExpected = jo.getJSONArray("data").getJSONObject(i).get("email").toString();
			System.out.println(emailExpected);
			
			flag = emailExpected.equals(email) ? true : false ;
			
			if(flag)
				break;
		
		}
		
		Assert.assertEquals(flag, true);
		
		/*
			o/p :
			michael.lawson@reqres.in
			lindsay.ferguson@reqres.in
			tobias.funke@reqres.in
			byron.fields@reqres.in
			george.edwards@reqres.in
			rachel.howell@reqres.in
			PASSED: TestJsonResponseBodyData

		*/
	
	}
	
}
