package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import base.BaseTest;
import helpers.ConfigReader;

import static org.testng.Assert.assertEquals;

public class UpdateUserAPITest extends BaseTest {

    @Test
    public void testUpdateUser() {
    	createTest("Update User API Test");
        RestAssured.baseURI = "https://reqres.in/api";
        String apiKey = ConfigReader.get("api_key");

        String requestBody = """
            {
              "name": "Bhargavi Updated",
              "job": "Senior QA Engineer"
            }
            """;

        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json")
            .header("x-api-key", apiKey)
            .body(requestBody)
            .when()
            .put("/users/2") // Updating user with ID 2
            .then()
            .extract().response();

        System.out.println("🛠 PUT Status: " + response.statusCode());
        System.out.println("🔄 Response: " + response.asString());

        assertEquals(response.statusCode(), 200);
    }
}
