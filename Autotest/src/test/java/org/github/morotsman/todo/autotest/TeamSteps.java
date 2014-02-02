package org.github.morotsman.todo.autotest;


import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.codehaus.jackson.map.ObjectMapper;
import org.github.morotsman.todo.autotest.util.*;
import org.github.morotsman.todo.web.dto.TeamDTO;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class TeamSteps {

    private static final String BASE_URL = "http://localhost:8080/todo/services/v1";
    private static final String TEAM_URL =  BASE_URL + "/team/";

    private RestHelper<TeamDTO> teamHelperImpl = TestContext.instance().getTeamHelperImpl();
    private RestHelper<TeamDTO> teamsHelperImpl = new RestHelperImpl<TeamDTO>();

    private ResponseEntity<String> teamResponse;

    private ResponseEntity<String> teamsResponse;


    private ObjectMapper mapper = new ObjectMapper();

    private TeamDTO requestBody;


    @Before("@TeamStep")
    public void setup(){
    }


    @Given("^the System knows about the team: (.*)$")
    public void the_System_knows_about_the_team(String team) {
        if(!StringUtils.hasLength(team)){
            return;
        }
        createTeam(team);
    }

    @Given("^the System knows about the teams: (.*)$")
    public void the_System_knows_about_the_teams(String teams) {
        if(!StringUtils.hasLength(teams)){
            return;
        }
        for(String team : teams.split(",")){
            createTeam(team);
        }
    }

    private void createTeam(String team)  {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setDescription("Description");
        Assert.assertEquals("Expected a team to be created", 201, teamHelperImpl.putResource(TEAM_URL + StringUtils.trimAllWhitespace(team),teamDTO).getStatusCode().value());
    }




    @When("^the request body for the request is:$")
    public void the_request_body_for_the_request_is(String body) throws IOException {
        requestBody =  mapper.readValue(body, TeamDTO.class);
    }

    @When("^the client requests GET /team/(.*)")
    public void the_client_requests_GET_team(String teamName) {
        teamResponse = teamHelperImpl.getResource(TEAM_URL +teamName);
    }

    @When("^the client requests DELETE /team/(.*)$")
    public void the_client_requests_DELETE_team(String teamName) {
        teamResponse = teamHelperImpl.getResource(TEAM_URL + teamName);
    }

    @When("^the client requests PUT /team/(.*)$")
    public void the_client_requests_PUT_team(String teamName) throws IOException {
        teamResponse = teamHelperImpl.putResource(TEAM_URL +"/"+teamName, requestBody);

    }

    @When("^the client requests GET /team$")
    public void the_client_requests_GET_team() {
        teamsResponse = teamsHelperImpl.getResource(TEAM_URL);
    }

    @Then("^the status code for the team request should be (\\d+)$")
    public void the_status_code_for_the_team_request_should_be(int expectedStatus) {
        assertStatusCode(expectedStatus, teamResponse.getStatusCode().value());
    }

    private void assertStatusCode(int expected, int actual) {
        Assert.assertEquals("Expected the status code to be " + expected, expected, actual);
    }

    @Then("^the status code for the teams request should be (\\d+)$")
    public void the_status_code_for_the_teams_request_should_be(int expectedStatus) {
        assertStatusCode(expectedStatus, teamsResponse.getStatusCode().value());
    }

    @Then("^the team response should be JSON:$")
    public void the_team_response_should_be_JSON(String expectedBody) throws IOException {
        String actual = teamResponse.getBody();


        Assert.assertEquals("Expected a different response body.", StringUtils.trimAllWhitespace(expectedBody), actual);

    }

    @Then("^the teams response should be JSON:$")
    public void the_teams_response_should_be_JSON(String expected) throws IOException {
        Assert.assertEquals("Expected a different response body", StringUtils.trimAllWhitespace(expected), teamsResponse.getBody());
    }





}
