package dailyCode_2;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class PathAndQueryParameters {
	
	
	/*
	 
	 URI/URL : https://reqres.in/api/users?delay=3&id=2	 
	 domine : https://reqres.in	 
	 path : /api/users	 
	 query : ?delay=3&id=2
	 	 
	 */
	
	@Test
	public void TestPathAndQueryParameter() {
		
		baseURI = "https://reqres.in";
		
		given()
		.log().all()
		.baseUri(baseURI)
		.pathParam("mypath1", "api")
		.pathParam("mypath2", "users")
		.queryParam("delay", 3)
		.queryParam("id", 2)
		
		.when()
		.get("/{mypath1}/{mypath2}")
		
		.then()
		.statusCode(200)
		.log().all()
		;
	
	}
	
	/*
	 
	 URI/URL : https://restful-booker.herokuapp.com/booking	 
	 domine : https://restful-booker.herokuapp.com	 
	 path : /booking
	 	 
	 */
	
	@Test
	public void pathVariable1()
	{
		
			given()
				.log()
				.all()
				.baseUri("https://restful-booker.herokuapp.com/")
				.basePath("{resourcePath}") // variable name
			    .pathParam("resourcePath", "booking") // variable name and value
			.when()
				.get()
			.then()
				.statusCode(200)
				.log()
				.all();
	}
	
	@Test
	public void pathVariable2()
	{
		given()
				.log()
				.all()
			    .pathParam("resourcePath", "booking") // variable name and value
			.when()
				.get("https://restful-booker.herokuapp.com/{resourcePath}") // variable name
			.then()
				.statusCode(200)
				.log()
				.all();
	}
	
	@Test
	public void pathVariable3()
	{
		given()
				.log()
				.all()
			.when()
				.get("https://restful-booker.herokuapp.com/{resourcePath}", "booking") // variable name and value
			.then()
				.statusCode(200)
				.log()
				.all();
	}
	
	@Test
	public void pathVariable4()
	{
			given()
				.log()
				.all()
			.when()
				.get("https://restful-booker.herokuapp.com/{resourcePath}/{bookingId}", "booking",10)
			.then()
				.statusCode(200)
				.log()
				.all();
	}
	
	
	/*
	 
	Note – If you are using path parameters with baseURI() or RestAssured.baseURI, it will not work.	
	You will get an error on running the below program:-
		
	 */	
	@Test
	public void pathVariable5()
	{
			given()
				.log()
				.all()
				.baseUri("https://restful-booker.herokuapp.com/{resourcePath}")
			    .pathParam("resourcePath", "booking")
			.when()
				.get()
			.then()
				.log()
				.all();
	}
}
