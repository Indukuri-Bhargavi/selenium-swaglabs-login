package api;

import base.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import helpers.ConfigReader;

import static org.testng.Assert.assertEquals;



public class CreateUserAPITest extends BaseTest{

    @Test
    public void testCreateUser() {
    	createTest("Create User API Test");
        RestAssured.baseURI = "https://reqres.in/api";
        String apiKey = ConfigReader.get("api_key"); 

        String requestBody = """
            {
              "name": "Bhargavi",
              "job": "QA Engineer"
            }
            """;

        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json")
            .header("x-api-key", apiKey)
            .body(requestBody)
            .when()
            .post("/users")
            .then()
            .extract().response();
        assertEquals(response.statusCode(), 201); // Created
        assertEquals(response.jsonPath().getString("name"), "Bhargavi");
    }
}
