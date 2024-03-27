package dailyCode_2;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	
	@Test
	public void TestCookies() {
		
		baseURI = "https://www.google.co.in/";
		
		given()
			.baseUri(baseURI)
		.when()
			.get()
		.then()
			.cookie("AEC", "Ae3NU9N_uhBx4BTbNeRJ8GU9cPYzpakcXfRpNJcysCqsbYjRjRAqCBBruKo")
		;
		
		// This test will fail bcz everytime the cookie value will change
	}
	
	
	@Test
	public void GetCookiesInfo() {
		
		baseURI = "https://www.google.co.in/";
		
		Response response = given()
			.baseUri(baseURI)
		.when()
			.get();
		
		//get single cookie info
		String cookie = response.getCookie("AEC"); // or .cookie
		System.out.println(cookie);
		
		//get all cookie info
		Map<String, String> cookies = response.getCookies(); // or .cookies
		
		//********** Approach 1 **********
		
		// Get an iterator for the Map
		Iterator<Map.Entry<String, String>> iterator = cookies.entrySet().iterator();

        // Iterate through the Map using the iterator
		while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
		
		//********** Approach 2 **********
		
		for(String ooo : cookies.keySet()) {
			
			String value = response.getCookie(ooo);
			System.out.println("Key: " + ooo + ", Value: " + value);
			
		}
		
	}

}
