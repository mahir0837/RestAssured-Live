package com.cydeo.liveSession.week02;

import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class P01_PathParams extends FakeStoreTestBase {


    /**
     * 1- Given accept type is Json
     * 2- Path Parameters value is
     * - id —> 109
     * 3- When user sends GET request to api/v1/products/{id}
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - Print response
     * - id is 109
     * - Name is "Classic Grey Hooded Sweatshirt"
     * - Category name is "Clothes"
     */

    @Test
    void responsePath() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 109)
                .when()
                .get("/products/{id}").prettyPeek()
                .then()
                .statusCode(is(equalTo(200)))
                .contentType(is(equalTo(ContentType.JSON.withCharset("utf-8"))))
                .body("id", is(equalTo(109)))
                .body("title", is(equalTo("Classic Grey Hooded Sweatshirt")))
                .body("category.name", is(equalTo("Clothes")));

    }

    @Test
    public void allInOne() {

        getResponse(109, "products", 200)
                .then()
                .body("id", is(equalTo(109)))
                .body("title", is(equalTo("Classic Grey Hooded Sweatshirt")))
                .body("category.name", is(equalTo("Clothes")));

    }


    public static Response getResponse(int paramvalue, String endpoint, int statusCode) {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", paramvalue)
                .when()
                .get("/" + endpoint + "/{id}").prettyPeek()
                .then()
                .statusCode(is(equalTo(statusCode)))
                .contentType(is(equalTo(ContentType.JSON.withCharset("utf-8"))))
                .extract().response();
        return response;
    }
}
