package com.api.engine;

import com.api.requests.PassengerRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndPoints {
    private static final String BASE_URL = "https://api.instantwebtools.net";

    public static Response getPassengers(int pageNumber, int pageSize) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        return request.get(String.format("/v1/passenger?page=%s&size=%s", pageNumber, pageSize));
    }

    public static Response addPassenger(PassengerRequest requestBody) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON)
                .body(requestBody);
        return request.post("/v1/passenger");
    }

    public static Response getPassenger(String passengerId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        return request.get("/v1/passenger/" + passengerId);
    }

    public static Response deletePassenger(String passengerId) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        return request.delete("/v1/passenger/" + passengerId);
    }
}