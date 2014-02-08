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

    @Given("^that the team: (\\w+) has the story: (.*)$")
    public void the_System_knows_about_the_story(String teamName, String storyName) {
        StoryDTO storyDTO = new StoryDTO();
        storyDTO.setDescription("SomeDescription");
        storyDTO.setEstimate(8L);
        storyHelperImpl.putResource(BASE_URL + "/team/" + teamName + "/story/" + storyName, storyDTO);
    }

    @When("^the clients creates a story with PUT (.*)$")
    public void the_clients_creates_a_story_with_PUT(String url) {
        sprintResponse = storyHelperImpl.putResource(BASE_URL + url, requestBody);
    }

    @When("^the clients deletes a story with DELETE (.*)$")
    public void the_clients_deletes_a_story_with_DELETE_team_aTeam_story_aStory(String url) {
        sprintResponse = storyHelperImpl.deleteResource(BASE_URL + url);
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
