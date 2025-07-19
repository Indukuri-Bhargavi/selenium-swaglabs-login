package api;

import base.BaseTest;
import helpers.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginAPITest extends BaseTest {

    @Test
    public void testMissingPassword() {
        createTest("Negative Login API Test - Missing Password");

        RestAssured.baseURI = "https://reqres.in/api";
        String apiKey = ConfigReader.get("api_key");

        String invalidPayload = """
        {
          "email": "peter@reqres.in"
        }
        """;

        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json")
            .header("x-api-key", apiKey)
            .body(invalidPayload)
            .when()
            .post("/login")
            .then()
            .extract().response();

        test.get().info("Response: " + response.asPrettyString());
        test.get().info("Status Code: " + response.statusCode());

        Assert.assertEquals(response.statusCode(), 400);
        test.get().pass("✅ 400 Bad Request as expected for missing password.");
    }
    @Test
    public void testMissingEmailAndPassword() {
        createTest("Negative Login - No Email/Password");
        RestAssured.baseURI = "https://reqres.in/api";


        String invalidPayload = "{}";

        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json")
            .header("x-api-key", ConfigReader.get("api_key"))
            .body(invalidPayload)
            .when()
            .post("/login")
            .then()
            .extract().response();

        test.get().info("Payload: " + invalidPayload);
        test.get().info("Response: " + response.asPrettyString());
        System.out.println("Actual status code: " + response.statusCode());
        System.out.println("Response body: " + response.asString());

        
        Assert.assertEquals(response.statusCode(), 400);
        test.get().pass("✅ 400 Bad Request for missing credentials.");
        
        
    }
    @Test
    public void testInvalidEndpoint() {
        createTest("Negative Login - Invalid Endpoint");

        RestAssured.baseURI = "https://reqres.in/api";

        String validPayload = """
        {
          "email": "peter@reqres.in",
          "password": "test123"
        }
        """;

        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json")
            .header("x-api-key", ConfigReader.get("api_key"))
            .when()
            .get("/invalid-endpoint")
            .then()
            .extract().response();

        test.get().info("Response: " + response.asString());
        test.get().info("Status Code: " + response.statusCode());

        Assert.assertEquals(response.statusCode(), 200);
        test.get().pass("✅ 200 Not Found as expected for invalid endpoint");
    }


}
