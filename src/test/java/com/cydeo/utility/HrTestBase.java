package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class HrTestBase {

    @BeforeAll
    public static void init(){

       baseURI="http://3.84.34.15:1000/ords/hr";
    }


    @AfterAll
    public static void destroy(){

        reset(); // Reset all variables to default values
    }
}