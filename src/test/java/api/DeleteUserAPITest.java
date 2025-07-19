package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import base.BaseTest;
import helpers.ConfigReader;

import static org.testng.Assert.assertEquals;

public class DeleteUserAPITest extends BaseTest {

    @Test
    public void testDeleteUser() {
    	createTest("Delete User API Test");
        RestAssured.baseURI = "https://reqres.in/api";
        String apiKey = ConfigReader.get("api_key");

        Response response = RestAssured
            .given()
            .header("x-api-key", apiKey)
            .when()
            .delete("/users/2")
            .then()
            .extract().response();

        System.out.println("🗑 DELETE Status: " + response.statusCode());

        assertEquals(response.statusCode(), 204); // No Content
    }
}
