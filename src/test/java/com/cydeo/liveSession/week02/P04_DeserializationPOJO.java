package com.cydeo.liveSession.week02;

import com.cydeo.pojo.Category;
import com.cydeo.pojo.FakeStorePojo;
import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class P04_DeserializationPOJO extends FakeStoreTestBase {

    /**
     * Send request to FakeStoreTestBase url and save the response
     * Accept application/json
     * Path Param id is 2
     * Query Param limit 10
     * Query Param offset 0
     * GET /categories/{id}/products
     * Store the response in Response Object that comes from get Request
     * Print out followings
     * - Print response
     * - Content-Type is application/json
     * - Status Code is 200
     * - Retrieve data as JAVA Collections and print out following information
     *
     * System.out.println("====== GET ALL PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
     */
    @Test
    public void task1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 2)
                .queryParam("limit", 10)
                .queryParam("offset", 0).
                when().get("/categories/{id}/products").
                then().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().response();

        JsonPath jp=response.jsonPath();
        List<FakeStorePojo> fakeStorePojos = jp.getList("", FakeStorePojo.class);
        // * System.out.println("====== GET ALL PRODUCTS ======");
//        System.out.println("fakeStorePojos = " + fakeStorePojos);
        //     * System.out.println("====== GET SECOND PRODUCTS ======");
        System.out.println("fakeStorePojos.get(1) = " + fakeStorePojos.get(1));
        //     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
        System.out.println("fakeStorePojos.get(1).getPrice() = " + fakeStorePojos.get(1).getPrice());
        //     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
        System.out.println("fakeStorePojos.get(1).getImages() = " + fakeStorePojos.get(1).getImages());
        //     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
        System.out.println("fakeStorePojos.get(1).getImages().get(0) = " + fakeStorePojos.get(1).getImages().get(0));
        //     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
        System.out.println("fakeStorePojos.get(1).getCategory() = " + fakeStorePojos.get(1).getCategory());
        //     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
        System.out.println("fakeStorePojos.get(1).getCategory().getName() = " + fakeStorePojos.get(1).getCategory().getName());


    }
}
