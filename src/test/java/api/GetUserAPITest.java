package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import base.BaseTest;

import static org.testng.Assert.assertEquals;

public class GetUserAPITest extends BaseTest{

    @Test
    public void testGetUser() {
    	createTest("Get User API Test");
        RestAssured.baseURI = "https://reqres.in/api";

        Response response = RestAssured
            .given()
            .when()
            .get("/users/2")
            .then()
            .statusCode(200)
            .extract().response();

        System.out.println("Response Body: " + response.getBody().asString());
        assertEquals(response.jsonPath().getString("data.email"), "janet.weaver@reqres.in");
    }
    
    
}
