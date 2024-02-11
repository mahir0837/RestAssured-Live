package com.cydeo.liveSession.week02;

import com.cydeo.pojo.FakeStorePojo;
import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
public class P03_Deserialization extends FakeStoreTestBase {

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
     * <p>
     * System.out.println("====== GET ALL PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS ======");
     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
     */

    @Test
    public void task1Java() {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 2)
                .queryParam("limit", 10)
                .queryParam("offset", 0)
                .when()
                .get("categories/{id}/products")
                .then()
                .statusCode(is(equalTo(200)))
                .contentType(is(equalTo(ContentType.JSON.withCharset("utf-8")))).extract().response();

        //RESPONSE
        //GET ALL DATA WITH RESPONSE

        List<Map<String,Object>> allData = response.as(List.class);
//        System.out.println(allData);

        //JSONPATH
        JsonPath jp = response.jsonPath();
        List<Map<String,Object>> jsonAllData = jp.getList("");
//        System.out.println("jsonAllData = " + jsonAllData);

        //PARTIAL BODY JSON PATH
        Map<String,Object>firstMap=jp.getMap("[0]");
//        System.out.println("firstMap : "+firstMap);

        //* System.out.println("====== GET ALL PRODUCTS ======");
        //     * System.out.println("====== GET SECOND PRODUCTS ======");
        //     * System.out.println("====== GET SECOND PRODUCTS PRICE ======");
        System.out.println("jsonAllData.get(1).get(\"price\") = " + jsonAllData.get(1).get("price"));
        //     * System.out.println("====== GET SECOND PRODUCTS IMAGES ======");
        List<List<String>> images = jp.getList("images");
        System.out.println("images = " + images.get(1));

        //     * System.out.println("====== GET SECOND PRODUCTS FIRST IMAGE ======");
        System.out.println("images.get(0) = " + images.get(1).get(0));
        //     * System.out.println("====== GET SECOND PRODUCTS CATEGORY ======");
        List<Map<String,Object>> categoryList = jp.getList("category");
        System.out.println("categoryList.get(1) = " + categoryList.get(1));
        //     * System.out.println("====== GET SECOND PRODUCTS CATEGORY NAME ======");
        System.out.println("categoryList.get(1).get(1) = " + categoryList.get(1).get("name"));
        //     */
    }
}