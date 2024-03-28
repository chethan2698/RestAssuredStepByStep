package dailyCode_1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import ConstantClasses.Pojo_PostRequest;

public class WaysToCreatePostRequestBody {

	/*
	 * 1) Post request body using MashMap 2) Post request body Creation using
	 * org.json 3) Post request body creation using POJO class 4) Post request body
	 * using external json file data
	 */

	// Post request body using MashMap
	@Test
	void testPostUsingHashMap() {

		HashMap data = new HashMap();

		data.put("name", "chethan");
		data.put("age", "25");
		data.put("email", "chethan@gmail.com");
		data.put("grade", "A");

		String[] arr = { "Java", "Selenium" };
		data.put("courses", arr);

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students")

				.then()
				// validations
				.statusCode(201).body("name", equalTo("chethan")).body("age", equalTo("25"))
				.body("email", equalTo("chethan@gmail.com")).body("grade", equalTo("A"))
				.body("courses[0]", equalTo("Java")).body("courses[1]", equalTo("Selenium"))
				.header("Content-Type", "application/json").log().all();
		;

	}

	// Post request body Creation using org.json
	@Test
	void testPostUsingJsonLibrary() {

		JSONObject data = new JSONObject();

		data.put("name", "Virat");
		data.put("age", "32");
		data.put("email", "virat@gmail.com");
		data.put("grade", "A");
		String[] arr = { "Java", "Selenium" };
		data.put("courses", arr);

		given().contentType("application/json").body(data)
		
				.when().post("http://localhost:3000/students")

				.then()
				// validations
				.statusCode(201).body("name", equalTo("Virat")).body("age", equalTo("25"))
				.body("email", equalTo("virat@gmail.com")).body("grade", equalTo("A"))
				.body("courses[0]", equalTo("Java")).body("courses[1]", equalTo("Selenium"))
				.header("Content-Type", "application/json").log().all();
		;

	}

	// Post request body Creation using Pojo Class
	@Test
	void testPostUsingPojoClass() {

		Pojo_PostRequest data = new Pojo_PostRequest();

		data.setName("Nag's");
		data.setAge("25");
		data.setEmail("nag@gmail.com");
		data.setGrade("A");

		String[] arr = { "Java", "Selenium" };
		data.setCourses(arr);

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students")

				.then()
				// validations
				.statusCode(201).body("name", equalTo("Nag's")).body("age", equalTo("25"))
				.body("email", equalTo("nag@gmail.com")).body("grade", equalTo("A")).body("courses[0]", equalTo("Java"))
				.body("courses[1]", equalTo("Selenium")).header("Content-Type", "application/json").log().all();
		;

	}

	// Post request body Creation using Json external file
	@Test
	void testPostUsingJsonExternalFile() throws IOException, ParseException {

		File f = new File(".\\Body.json");// . represents current project location
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt); //import org.json.JSONObject;

		given().contentType("application/json").body(jo)

				.when().post("http://localhost:3000/students")

				.then()
				// validations
				.statusCode(201).body("name", equalTo("Ramesh")).body("age", equalTo("50"))
				.body("email", equalTo("ramesh@example.com")).body("grade", equalTo("B"))
				.body("courses[0]", equalTo("python")).body("courses[1]", equalTo("selenium"))
				.header("Content-Type", "application/json").log().all();
		;

	}

}
