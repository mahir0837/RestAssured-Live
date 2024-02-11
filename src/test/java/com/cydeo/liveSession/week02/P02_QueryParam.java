package com.cydeo.liveSession.week02;

import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_QueryParam extends FakeStoreTestBase {

    /**
     * 1- Given accept type is Json
     * 2- Query Parameters value is
     * - limit —> 1
     * - offset —> 100
     * 3- When user sends GET request to /products
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - limit and offset values are matching with query params
     * - Each product has id
     * - Each product has category id
     * - Get all product names
     * - Get product ids
     * - Get all category names
     */
    @Test
    public void task1() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .queryParam("limit", 1)
                .and()
                .queryParam("offset", 100)
                .when()
                .get("/products")
                .then()
                .statusCode(is(equalTo(200)))
                .contentType(ContentType.JSON)
                .body("id", everyItem(notNullValue()))
                .body("category.id", everyItem(notNullValue())).extract().jsonPath();
        List<String> nameList = jsonPath.getList("title");
        List<Integer> idList = jsonPath.getList("id");
        List<Integer> categoryNameList = jsonPath.getList("category.name");
        System.out.println("nameList = " + nameList);
        System.out.println("idList = " + idList);
        System.out.println("categoryNameList = " + categoryNameList);

    }
}