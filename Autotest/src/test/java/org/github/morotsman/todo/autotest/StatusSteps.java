package org.github.morotsman.todo.autotest;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StatusSteps {

    private RestTemplate restTemplate= new RestTemplate();

    private ResponseEntity<String> response;

    @Given("^the todo application$")
    public void givenTheTodoApplication() {

    }


    @When("^executing GET /todo")
    public void getTodo()
    {
        response = restTemplate.getForEntity("http://localhost:8080/todo", String.class);
    }

    @Then("^the status code should be 200$")
    public void theStatusCodeShouldBe() {
        Assert.assertEquals(response.getStatusCode().value(), 200);
    }
}
