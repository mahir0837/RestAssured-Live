package com.cydeo.utility;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;


public abstract class GameOfThronesTestBase {

    @BeforeAll
    public static void init(){

        baseURI="https://thronesapi.com/api/v2";
    }


    @AfterAll
    public static void destroy(){

        reset(); // Reset all variables to default values
    }
}