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

/* Metoda setup() oznaczona adnotacją @BeforeClass, - wykonywana raz, przed wszystkimi testami w klasie. 
    Ustawia bazowy URI dla RestAssured oraz tworzy specyfikację requestu, 
    która będzie używana we wszystkich testach. 
    Specyfikacja ta ustawia nagłówki Accept i Content-Type na JSON, co jest typowe dla API, które zwracają dane w formacie JSON.
 */
    @BeforeClass
    public void setup()
        RestAssured.useRelaxedHTTPSValidation();

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
        // Asercja #1: "success" == true
        Boolean success = res.jsonPath().getBoolean("success");
        Assert.assertEquals(success, Boolean.TRUE, "'success' should be true");

        // Asercja #2: element "data" istnieje i nie jest pusty
        //      w jeje ramach są wykonywane 2 metody z klasy Assert - assertNotNull() i assertTrue()

        Object data = res.jsonPath().get("data");
        Assert.assertNotNull(data, "'data' should exist");

        String dataString = String.valueOf(data).trim();
        Assert.assertTrue(!dataString.isEmpty() && !dataString.equals("[]") && !dataString.equals("{}"),
                "'data' should be non-empty");
    }

// Jeśli "data" jest obiektem lub innym typem, konwertujemy go na string i sprawdzamy, czy nie jest pusty.
// !dataString.isEmpty() - sprawdza, czy "dataString" nie jest pustym stringiem.
// !dataString.equals("[]") - sprawdza, czy "dataString" nie jest pustą listą.
// !dataString.equals("{}") - sprawdza, czy "dataString" nie jest pustym obiektem.

}