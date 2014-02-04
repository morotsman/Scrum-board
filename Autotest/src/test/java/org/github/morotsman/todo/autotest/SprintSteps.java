package org.github.morotsman.todo.autotest;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.github.morotsman.todo.autotest.util.RestHelper;
import org.github.morotsman.todo.autotest.util.TestContext;
import org.github.morotsman.todo.web.dto.SprintDTO;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

public class SprintSteps {

    private static final String BASE_URL = "http://localhost:8080/todo/services/v1";

    private RestHelper<SprintDTO> membershipHelperImpl = TestContext.instance().getSprintHelperImpl();

    private ResponseEntity<String> sprintResponse;


    @Given("^that the team: (\\w+) has the sprint: (.*)$")
    public void createSprint(String teamName, String sprintName) {
        membershipHelperImpl.putResource(BASE_URL + "/team/" + teamName+ "/sprint/"+ sprintName);
    }

    @Given("^that the team: (\\w+) has the sprints: (.*)$")
    public void createSprints(String teamName, String sprints) {
        for(String sprint: sprints.split(",")){
            createSprint(teamName,sprint);
        }
    }

    @When("^the clients creates a sprint with PUT (.*)$")
    public void the_clients_creates_a_sprint_with_PUT(String url) {
        sprintResponse = membershipHelperImpl.putResource(BASE_URL + url);
    }

    @When("^the clients remove a sprint with DELETE (.*)$")
    public void the_clients_remove_a_sprint_with_DELETE_team(String url) {
        sprintResponse = membershipHelperImpl.deleteResource(BASE_URL + url);
    }

    @When("^the clients gets a sprint with GET (.*)$")
    public void the_clients_remove_a_sprint_with_GET_team_aTeam_sprint___(String url) {
        sprintResponse = membershipHelperImpl.getResource(BASE_URL + url);
    }

    @Then("^the status code for the sprint request should be (\\d+)$")
    public void the_status_code_for_the_sprint_request_should_be(int expectedStatus) {
        assertStatusCode(expectedStatus, sprintResponse.getStatusCode().value());
    }

    @Then("^the sprint response should be JSON:$")
    public void the_sprint_response_should_be_JSON(String expectedBody) {
        Assert.assertEquals("Expected a different response body.", StringUtils.trimAllWhitespace(expectedBody), sprintResponse.getBody());
    }



    private void assertStatusCode(int expected, int actual) {
        Assert.assertEquals("Expected the status code to be " + expected, expected, actual);
    }

}
