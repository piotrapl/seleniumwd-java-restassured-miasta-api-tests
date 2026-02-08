/*
@DataProvider(name = "municipalitiesByName") - to jest adnotacja TestNG, 
oznacza metodę jako dostawcę danych dla testów. 
public static Object[][] municipalitiesByName() - metoda zwraca dwuwymiarową tablicę obiektów, 
gdzie każdy element tablicy to zestaw danych dla pojedynczego testu.
*/
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