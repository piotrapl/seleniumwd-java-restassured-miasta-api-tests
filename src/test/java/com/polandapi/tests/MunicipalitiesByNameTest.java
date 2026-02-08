package com.polandapi.tests;

import com.polandapi.base.BaseApiTest;
import com.polandapi.data.MunicipalitiesData;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MunicipalitiesByNameTest extends BaseApiTest {

    @Test(dataProvider = "municipalitiesByName", dataProviderClass = MunicipalitiesData.class)
    public void shouldReturnMunicipalityByName_Positive(String name) {
        String path = "/api/v1/municipalities/name/" + name;

        Response res = get(path);

        // Optional sanity check (keeps failures clearer)
        Assert.assertEquals(res.getStatusCode(), 200, "HTTP status should be 200");

        // Your required assertions:
        assertSuccessTrueAndDataNotEmpty(res);
    }
}