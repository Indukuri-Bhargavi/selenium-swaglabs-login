package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class UserApiTest {

    @Test
    public void verifyGetUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        given()
            .when()
            .get("/users/2")
            .then()
            .statusCode(200)
            .body("data.first_name", equalTo("Janet"))
            .body("data.id", equalTo(2));
    }
}
