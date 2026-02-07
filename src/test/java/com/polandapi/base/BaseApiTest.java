package com.polandapi.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    protected static final String BASE_URL = "https://local-gov-units.polandapi.com";
    protected RequestSpecification req;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        req = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected Response get(String path) {
        return RestAssured.given()
                .spec(req)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    protected void assertSuccessTrueAndDataNotEmpty(Response res) {
        // Assertion #1: "success" == true
        Boolean success = res.jsonPath().getBoolean("success");
        Assert.assertEquals(success, Boolean.TRUE, "'success' should be true");

        // Assertion #2: "data" exists and is non-empty
        Object data = res.jsonPath().get("data");
        Assert.assertNotNull(data, "'data' should exist");

        // Works for array/object/string-ish responses:
        // If data is a list, size() > 0; otherwise ensure not empty in string form.
        String dataString = String.valueOf(data).trim();
        Assert.assertTrue(!dataString.isEmpty() && !dataString.equals("[]") && !dataString.equals("{}"),
                "'data' should be non-empty");
    }
}