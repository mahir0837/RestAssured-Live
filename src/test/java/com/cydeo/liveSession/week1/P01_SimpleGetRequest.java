package com.cydeo.liveSession.week1;


import com.cydeo.utility.HrTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SimpleGetRequest extends HrTestBase {

    /**
     * 1. Send request to HR url and save the response
     * 2. GET /regions
     * 3. Store the response in Response Object that comes from GET Request
     * 4. Print out followings
     * - Headers
     * - Content-Type
     * - Status Code
     * - Response
     * - Date
     * - Verify response body has "Europe"
     * - Verify response has Date
     */
    @Test
    public void task1() {

        Response response = given().log().all().accept("application/json"). // telling API I need data in json format
                when().get("/regions"); // baseURI+basePath

        // response.prettyPrint();


        //     *     - Headers
        System.out.println("---------------------------");
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("response.headers() = " + response.headers());

        //     *     - Response
        // response.getHeaders() == response.headers()

        //     *     - Content-Type
        System.out.println("---------------------------");
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.contentType() = " + response.contentType());


        //     *     - Status Code
        System.out.println("---------------------------");
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.statusCode() = " + response.statusCode());


        //     *     - Date
        System.out.println("---------------------------");
        System.out.println("response.getHeader(\"Date\") = " + response.getHeader("Date"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));

        //     *     - Verify response body has "Europe"
        boolean resultEurope = response.body().asString().contains("Europe");
        assertTrue(resultEurope);

        assertTrue(response.asString().contains("Europe"));

        //     *     - Verify response has Date
        boolean dateHeader = response.getHeaders().hasHeaderWithName("Date");
        assertTrue(dateHeader);
        System.out.println("dateHeader = " + dateHeader);


    }

}
