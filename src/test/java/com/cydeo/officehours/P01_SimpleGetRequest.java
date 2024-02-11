package com.cydeo.officehours;

import com.cydeo.utility.GameOfThronesTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SimpleGetRequest extends GameOfThronesTestBase {

    /**
     *     Task 1 :
     *      * - Given accept type is "application/json"
     *      * - When users sends GET request to "/api/v2/Characters"
     *      * - Then status code is 200
     *      * - And Content - Type is "application/json; charset=utf-8"
     *      * - And Date Header is exist
     *      * - And response contains "Jon Snow"
     *      *
     */

    @Test
    public void getAllCharacters() {


        Response response = given().accept(ContentType.JSON).
                when().get("/Characters");

        System.out.println("response = " + response.prettyPrint());

        //     *      * - Then status code is 200
        assertEquals(200,response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        //     *      * - And Content - Type is "application/json; charset=utf-8"
        assertEquals("application/json; charset=utf-8",response.contentType());

        //     *      * - And Date Header is exist
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //     *      * - And response contains "Jon Snow"
        assertTrue(response.asString().contains("Jon Snow"));

        // Get me Jon Snow by using Response.Path
        Map<String,Object> jsonSnow = response.path("[2]");
        System.out.println("jsonSnow = " + jsonSnow);


        // String fName = response.path("[2].fullName");

        String fName = response.path("fullName[2]");
        System.out.println("fName = " + fName);

    }


    /**
     * Task 2 :
     * - Given accept type is application/json
     * - And Path Param id is 1
     * - When users sends request to /api/v2/Characters
     * - Then status code is 200
     * - And Content - Type is application/json; charset=utf-8
     * - And id is 1
     * - And name is Samwell Tarly
     * - And title is Maester
     * - BONUS -> Make sure image link is working ( is not broken ) !!!!!
     */
    @Test
    public void getSingleCharacter() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 1).
                when().get("/Characters/{id}");




        response.prettyPrint();


        //      * - Then status code is 200
        assertEquals(200,response.statusCode());

        //     * - And Content - Type is application/json; charset=utf-8
        assertEquals("application/json; charset=utf-8",response.contentType());

        //     * - And id is 1
        int id = response.path("id");
        assertEquals(1,id);

        //     * - And fullname is Samwell Tarly
        String fullname = response.path("fullName");
        assertEquals("Samwell Tarly",fullname);

        //     * - And title is Maester
        assertEquals("Maester",response.path("title"));


       // BONUS -> Make sure image link is working ( is not broken ) !!!!!
        String imageUrl = response.path("imageUrl");
        System.out.println("imageUrl = " + imageUrl);

        /*
        Response response1 = get(imageUrl);
        response1.statusCode()
         */

        int statusCodeImage = get(imageUrl).statusCode();
        System.out.println("statusCodeImage = " + statusCodeImage);
        assertEquals(200,statusCodeImage);

    }
}