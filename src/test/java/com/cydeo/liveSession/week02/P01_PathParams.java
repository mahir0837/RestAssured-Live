package com.cydeo.liveSession.week02;

import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class P01_PathParams extends FakeStoreTestBase {



    @Test
    void responsePath() {
        given()
                .pathParams("id",109)
                .when()
                .get("/products/{id}").prettyPeek()
                .then()
                .statusCode(is(equalTo(200)))
                .contentType(is(equalTo(ContentType.JSON.withCharset("utf-8"))))
                .body("id",is(equalTo(109)))
                .body("title",is(equalTo("Classic Grey Hooded Sweatshirt")))
                .body("category.name",is(equalTo("Clothes")));

    }
}
