package com.ada.group3.testapi.user;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;


public class UserStepDefinition {

    private RequestSpecification request;
    private Response response;

    private UsuarioRequest usuario;

    public UserStepDefinition() {
        request = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON);
        usuario = new UsuarioRequest();
        usuario.setName("kauan");
        usuario.setLastName("silva");
        usuario.setEmail("kauansilva@gmail.com");
        usuario.setCpf("36998333056");
        usuario.setUsername("kauan.silva");
        usuario.setPassword("St@rG@z3r^#");

    }


    @Given("user is new")
    public void userIsNew() {

    }

    @When("I post user")
    public void postUser() {
        response = request.body(usuario).when().post("/api/v1/usuarios");
    }

    @Then("user was registered")
    public void productWasRegistered() {
        response.then().statusCode(201);
    }

    @Given("user already registered")
    public void userAlreadyRegistered() {
        response = request.given()
                .auth()
                .preemptive()
                .basic("kauan.123", "St@rG@z3r^#")
                .when()
                .get("/usuarios/" + usuario.getUsername());

    }

    @When("I search for username")
    public void searchForUsername() {
        response = request.given()
                .auth()
                .preemptive()
                .basic("kauan.123", "St@rG@z3r^#")
                .when()
                .get("/api/v1/usuarios/" + usuario.getUsername());;
    }

    @Then("user with username was found")
    public void userWasFound() {
        var username = response.jsonPath().getString("username");
        Assertions.assertEquals(usuario.getUsername(), username);
    }
}






