package dailyCode_2;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeadersDemo {
	
	@Test
	public void TestHeders() {
		
		given()
		
		.when()
			.get("https://www.google.co.in/")
			
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws")
			.log().headers()
		;
				
	}
	
	
	@Test
	public void GetHeders() {
		
		Response res = given()		
						.when()
							.get("https://www.google.co.in/");
				
		// get single header info
		String header = res.getHeader("Content-Type");
		System.out.println(header);
		
		// or
		System.out.println(res.header("Content-Type"));
		
		
		//get all headers info
		Headers headers = res.getHeaders();
		for(Header h : headers) {
			
			System.out.println(h.getName() + "          " + h.getValue());
			
		}
		
		// or
		Headers headers1 = res.headers();
		for(Header h1 : headers1) {
			
			System.out.println(h1.getName() + "          " + h1.getValue());
			
		}
		
	}
	

}
