package com.api.steps;

import com.api.engine.EndPoints;
import com.api.requests.PassengerRequest;
import com.api.responses.PassengerResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class PassengerSteps {

    private static Response response;
    private static List<Map<String, String>> passengers;
    private static String id;
    RequestSpecification request;

    @Given("A list of passengers are available")
    public void aListOfPassengersAreAvailable() {
        response = EndPoints.getPassengers(0, 10);
        String jsonString = response.asString();
        passengers = JsonPath.from(jsonString).get("data");
    }

    @When("I add a passenger to list")
    public void iAddAPassengerToList() {
        PassengerRequest requestBody = new PassengerRequest("Alexander", 12, 5);
        response = EndPoints.addPassenger(requestBody);
    }

    @Then("The passenger is added")
    public void thePassengerIsAdded() {
        Assert.assertEquals(200, response.getStatusCode());

        id = response.jsonPath().get("_id");
        response = EndPoints.getPassenger(id);

        PassengerResponse passengerResponse = response.getBody().as(PassengerResponse.class);
        Assert.assertEquals("Alexander", passengerResponse.getName());
    }

    @When("I remove a passenger from the list")
    public void iRemoveAPassengerFromTheList() {
        response = EndPoints.deletePassenger(id);
    }

    @Then("The passenger is removed")
    public void thePassengerIsRemoved() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Passenger data deleted successfully.",
                JsonPath.from(response.asString()).get("message"));
    }
}
