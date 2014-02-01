package org.github.morotsman.todo.autotest;


import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.codehaus.jackson.map.ObjectMapper;
import org.github.morotsman.todo.autotest.util.RestHelper;
import org.github.morotsman.todo.autotest.util.RestHelperImpl;
import org.github.morotsman.todo.autotest.util.TestContext;
import org.github.morotsman.todo.web.dto.MembershipDTO;
import org.github.morotsman.todo.web.dto.TeamDTO;
import org.github.morotsman.todo.web.dto.UserDTO;
import org.junit.Assert;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TeamSteps {

    private static final String BASE_URL = "http://localhost:8080/todo/services/v1";
    private static final String TEAM_URL =  BASE_URL + "/team/";
    private static final String USER_URL =  BASE_URL + "/user/";

    private RestHelper<TeamDTO> teamHelperImpl  = new RestHelperImpl<TeamDTO>(TeamDTO.class);
    private RestHelperImpl<String> teamsHelperImpl = new RestHelperImpl<String>(String.class);
    private RestHelperImpl<UserDTO> userHelperImpl = new RestHelperImpl<UserDTO>(UserDTO.class);
    private RestHelperImpl<MembershipDTO> membershipHelperImpl = new RestHelperImpl<MembershipDTO>(MembershipDTO.class);

    private ResponseEntity<TeamDTO> teamResponse;

    private ResponseEntity<String> teamsResponse;

    private ResponseEntity<TeamDTO> createdTeam;

    private ObjectMapper mapper = new ObjectMapper();

    private Set<String> teamsToDelete = new HashSet<String>();
    private Set<String> usersToDelete = new HashSet<String>();

    private TeamDTO requestBody;


    @Before("@TeamStep")
    public void setup(){
    }


    @Given("^the System knows about the team: (.*)$")
    public void the_System_knows_about_the_team(String teams) {
        if(!StringUtils.hasLength(teams)){
            return;
        }
        for(String team : teams.split(",")){
            createTeam(team);
        }
    }

    private void createTeam(String team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setDescription("Description");
        createdTeam = teamHelperImpl.putResource(TEAM_URL + StringUtils.trimAllWhitespace(team),teamDTO);
        Assert.assertEquals("Expected a team to be created", 201, createdTeam.getStatusCode().value());
        addResourceToDelete(createdTeam.getBody());
    }

    private void addResourceToDelete(TeamDTO teamDTO){
        teamsToDelete.add(getSelfRef(teamDTO));
    }

    public String getSelfRef(ResourceSupport resource){
        return resource.getLink("self").getHref();
    }

    @Given("^that the team: (\\w+) has the members: (.*)$")
    public void that_the_team_has_members(String teamName, String users) {
        createTeam(teamName);
        for(String user: users.split(",")){
            createUser(user);
            Assert.assertEquals("Expected the membership to be created.", 201, membershipHelperImpl.putResource(TEAM_URL + "/" + teamName + "/membership/" + user, null).getStatusCode().value());
        }

    }

    private void createUser(String user) {
        ResponseEntity<UserDTO> response = userHelperImpl.putResource(USER_URL+user,null);
        if(response.hasBody()){
            usersToDelete.add(RestHelperImpl.getSelfRef(response.getBody()));
        }

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
    public void the_client_requests_PUT_team(String teamName) {
        teamResponse = teamHelperImpl.putResource(TEAM_URL +"/"+teamName, requestBody);
        addResourceToDelete(teamResponse.getBody());
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
        TeamDTO actual = teamResponse.getBody();
        TeamDTO expected = mapper.readValue(expectedBody, TeamDTO.class);

        Assert.assertEquals("Expected a different description", expected.getDescription(), actual.getDescription());

        Assert.assertEquals("Expected a different number of links", expected.getLinks().size(), actual.getLinks().size());

        for(Link link: actual.getLinks()){
            Assert.assertTrue("Unexpected link found.",expected.getLinks().contains(link));
        }
    }

    @Then("^the teams response should be JSON:$")
    public void the_teams_response_should_be_JSON(String expected) throws IOException {
        Assert.assertEquals("Expected a different response body", StringUtils.trimAllWhitespace(expected), teamsResponse.getBody());
    }

    /*
    private String removeLastLinkPart(Link link){
        String[] path = link.getHref().split("/");
        path[path.length-1]= "";
        return StringUtils.arrayToDelimitedString(path, "/");
    }
    */

    @After("@TeamStep")
    public void tearDown(){
        for(String each: teamsToDelete){
            Assert.assertEquals("Could not delete: " + each, 200, teamHelperImpl.deleteResource(each).getStatusCode().value());
        }
        for(String each: usersToDelete){
            Assert.assertEquals("Could not delete: " + each, 200, userHelperImpl.deleteResource(each).getStatusCode().value());
        }
    }



}
