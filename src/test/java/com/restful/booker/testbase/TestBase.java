package com.restful.booker.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        // RestAssured.port = 3030;
        RestAssured.basePath = "/booking";
    }
}
// "token": "1f7d5a73b62d892"