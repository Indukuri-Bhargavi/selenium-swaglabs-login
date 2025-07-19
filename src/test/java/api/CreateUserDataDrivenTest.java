package api;

import helpers.ConfigReader;
import helpers.ExcelReader;
import helpers.ExcelWriter;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static base.BaseTest.createTest;
import static base.BaseTest.test;
import static org.testng.Assert.*;

public class CreateUserDataDrivenTest {

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        List<Map<String, String>> data = ExcelReader.getData("test-data/CreateUserData.xlsx", "UserData");
        Object[][] result = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i).get("name");
            result[i][1] = data.get(i).get("job");
            result[i][2] = i + 1; // row number in Excel
        }
        return result;
    }
    @Test(dataProvider = "userData")
    public void createUserTest(String name, String job, int rowIndex) {
        createTest("Create User: " + name + " | Job: " + job);

        // Normalize input
        String safeName = name == null ? "" : name.trim();
        String safeJob  = job == null ? "" : job.trim();

        // Validate input
        boolean isValid = !safeName.isBlank() && !safeName.equalsIgnoreCase("null")
                       && !safeJob.isBlank() && !safeJob.equalsIgnoreCase("null");

        if (!isValid) {
            ExcelWriter.writeResult("test-data/CreateUserData.xlsx", "UserData", rowIndex, 2, "FAIL");
            Assert.fail("Invalid input provided: name/job is null, blank, or 'null'.");
        }

        String requestBody = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", safeName, safeJob);

        Response response = RestAssured
            .given()
            .baseUri("https://reqres.in/api")
            .header("Content-Type", "application/json")
            .header("x-api-key", ConfigReader.get("api_key"))
            .body(requestBody)
            .post("/users");

        String respName = response.jsonPath().getString("name");
        String respJob = response.jsonPath().getString("job");

        test.get().info("Request Body: " + requestBody);
        test.get().info("Response Body: " + response.asString());

        boolean testPass = safeName.equals(respName) && safeJob.equals(respJob);
        String result = testPass ? "PASS" : "FAIL";
        ExcelWriter.writeResult("test-data/CreateUserData.xlsx", "UserData", rowIndex, 2, result);

        Assert.assertEquals(respName, safeName, "Mismatch in name field");
        Assert.assertEquals(respJob, safeJob, "Mismatch in job field");
    }

    }



