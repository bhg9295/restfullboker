package com.restful.booker.crudtest;

import com.restful.booker.model.BookingDates;
import com.restful.booker.model.PatchPoJo;
import com.restful.booker.model.RestPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class CrudTest extends TestBase {
    static String token = "1f7d5a73b62d892";

    @Test
    public void verifyCreateBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("bhg");
        restPojo.setLastname("patel");
        restPojo.setTotalPrice("2000");
        restPojo.setDepositPaid("500");
        BookingDates bd = new BookingDates();
        bd.setCheckIn("12-12-2023");
        bd.setCheckOut("12-15-2023");
        restPojo.setBookingdates(bd);
        restPojo.setAdditionalNeeds("A & B");

        Response response = (Response) given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(restPojo)
                .post("/booking");
        //To fetch response from server
        String responseBody = response.getBody().asString();
        response.then().log().all().statusCode(200);
        JsonPath jsonPath = new JsonPath(responseBody);
        String bookingId = jsonPath.getString("bookingid");
        System.out.println("bookingid " + bookingId);
    }
    @Test
    public void getAllBookingId() {
        Response response = (Response) given()
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getBooking(){
        Response response = (Response) given()
                .basePath("/booking/613")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getBookingIdsWithFirstName(){
        Response response = (Response) given()
                .basePath("/booking?firstname=John")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void updateBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("bhgass");
        restPojo.setLastname("patel");
        restPojo.setTotalPrice("45000");
        restPojo.setDepositPaid("1500");
        BookingDates bd = new BookingDates();
        bd.setCheckIn("10-10-2023");
        bd.setCheckOut("12-12-2023");
        restPojo.setBookingdates(bd);

        Response response = (Response) given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .basePath("/booking/2625")
                .when()
                .body(restPojo)
                .put();

        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void verifyUserUpdateSingleBookingSuccessfully() {
        PatchPoJo.PatchPojo restPojo = new PatchPoJo.PatchPojo();
        restPojo.setFirstname("bhg");
        restPojo.setLastname("patel");
        Response response = given()
                .basePath(RestAssured.basePath + "/1")
                .headers("Content-Type", "application/json", "Authorization", "Basic " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(restPojo)
                .patch();

        response.prettyPrint();
        response.then().statusCode(200);

        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
    }

}









