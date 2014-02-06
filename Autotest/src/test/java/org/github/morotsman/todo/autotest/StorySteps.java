package org.github.morotsman.todo.autotest;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.github.morotsman.todo.autotest.util.RestHelper;
import org.github.morotsman.todo.autotest.util.TestContext;
import org.github.morotsman.todo.web.dto.StoryDTO;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class StorySteps {


    private static final String BASE_URL = "http://localhost:8080/todo/services/v1";

    private RestHelper<StoryDTO> storyHelperImpl = TestContext.instance().getStoryHelperImpl();

    private ResponseEntity<String> sprintResponse;

    private ObjectMapper mapper = new ObjectMapper();

    private StoryDTO requestBody;


    @Given("^the request body for the story request is:$")
    public void the_request_body_for_the_story_request_is(String body) throws IOException {
        requestBody =  mapper.readValue(body, StoryDTO.class);
    }

    @When("^the clients creates a story with PUT (.*)$")
    public void the_clients_creates_a_story_with_PUT(String url) {
        sprintResponse = storyHelperImpl.putResource(BASE_URL + url, requestBody);
    }

    @Then("^the status code for the story request should be (\\d+)$")
    public void the_status_code_for_the_story_request_should_be(int expectedStatus) {
        Assert.assertEquals("Expected the status code to be " + expectedStatus, expectedStatus, sprintResponse.getStatusCode().value());
    }

    @Then("^the story response should be JSON:$")
    public void the_story_response_should_be_JSON(String expectedBody) {
        Assert.assertEquals("Expected a different response body.", expectedBody, sprintResponse.getBody());
    }



}
