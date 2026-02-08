package com.polandapi.data;

import org.testng.annotations.DataProvider;

public class MunicipalitiesData {

    @DataProvider(name = "municipalitiesByName")
    public static Object[][] municipalitiesByName() {
        return new Object[][]{
                {"Lodz"},
                {"Lublin"}
        };
    }
}