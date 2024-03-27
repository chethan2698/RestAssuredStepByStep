package dailyCode_2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class LoggingDemo {
	
	@Test
	public void TestLogging() {
		
		given()
		
		.when()
			.get("https://www.google.co.in/")
			
		.then()
//			.log().headers()
//			.log().body()
//			.log().cookies()
			.log().status()
//			.log().all()
		;
				
	}

}
