package com.cydeo.day05;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;


public class ORDSHamcrestTest extends HrTestBase {

    @DisplayName("GET request to Employee IT_PROG endpoint and chaining")
    @Test
    public void employeesTest(){

        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)
        //expected names
        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\": \"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .statusCode(200)
                .body("items.job_id",everyItem(is("IT_PROG")))
                .body("items.first_name",containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))//contains with order
                .body("items.email",containsInAnyOrder("VPATABAL","DAUSTIN","BERNST","AHUNOLD","DLORENTZ")) //contains without order
                .body("items.first_name",equalTo(names));// equality of lists assertion

    }

    @DisplayName("GET request to Employee IT_PROG endpoint and chaining")
    @Test
    public void employeesTest2(){


        Response response=given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\": \"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .statusCode(200)
                .body("items.job_id",everyItem(is("IT_PROG")))
        .extract().response();

        response.prettyPrint();

    }


}
