package com.cydeo.utility;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
public class FakeStoreTestBase {


        @BeforeAll
        public static void init(){

            baseURI="https://api.escuelajs.co/api/v1";
        }


        @AfterAll
        public static void destroy(){

            reset(); // Reset all variables to default values
        }
    }

