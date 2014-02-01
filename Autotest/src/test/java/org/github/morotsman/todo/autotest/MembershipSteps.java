package org.github.morotsman.todo.autotest;


import cucumber.annotation.After;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.codehaus.jackson.map.ObjectMapper;
import org.github.morotsman.todo.autotest.util.RestHelperImpl;
import org.github.morotsman.todo.web.dto.MembershipDTO;
import org.github.morotsman.todo.web.dto.TeamDTO;
import org.github.morotsman.todo.web.dto.UserDTO;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class MembershipSteps {

    private static final String BASE_URL = "http://localhost:8080/todo/services/v1";
    private static final String USER_URL = "http://localhost:8080/todo/services/v1/user/";
    private static final String TEAM_URL = "http://localhost:8080/todo/services/v1/team/";

    private RestHelperImpl<UserDTO> userHelperImpl = new RestHelperImpl<UserDTO>(UserDTO.class);
    private RestHelperImpl<TeamDTO> teamHelperImpl = new RestHelperImpl<TeamDTO>(TeamDTO.class);
    private RestHelperImpl<MembershipDTO> membershipHelperImpl = new RestHelperImpl<MembershipDTO>(MembershipDTO.class);
    private RestHelperImpl<String> membershipsHelperImpl = new RestHelperImpl<String>(String.class);

    private ResponseEntity<TeamDTO> createdTeam;
    private ResponseEntity<UserDTO> createdUser;

    private ResponseEntity<MembershipDTO> membershipResponse;
    private ResponseEntity<String> membershipsResponse;

    private ObjectMapper mapper = new ObjectMapper();


    @Given("^the System knows about the user:(\\w+) and the team:(.*)$")
    public void the_System_knows_about_the_user_and_the_team(String userName, String teamName) {
        createTeam(teamName);
        createUser(userName);
    }

    private void createTeam(String teamName){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setDescription("Description");
        createdTeam = teamHelperImpl.putResource(TEAM_URL + StringUtils.trimAllWhitespace(teamName),teamDTO);
        Assert.assertEquals("Expected a team to be created", 201, createdTeam.getStatusCode().value());
    }

    private void createUser(String userName){
        createdUser = userHelperImpl.putResource(USER_URL + StringUtils.trimAllWhitespace(userName),null);
        Assert.assertEquals("Could not create user " + userName, 201, createdUser.getStatusCode().value());
    }

    @Given("^that the user:(\\w+) is member of the team:(.*)$")
    public void that_the_user_is_member_of_the_team(String userName, String teamName) {
        createTeam(teamName);
        createUser(userName);
        createMembership("/team/" + teamName + "/membership/" + userName);
    }

    private void createMembership(String url){
        membershipResponse = membershipHelperImpl.putResource(BASE_URL + url,null);
    }

    @When("^the client apply for membership with PUT (.*)$")
    public void the_client_apply_for_membership_with_PUT(String url) {
        createMembership(url);
    }

    @When("^removing the membership with DELETE (.*)$")
    public void removing_the_membership_with_DELETE_team_membership(String url) {
        membershipResponse = membershipHelperImpl.deleteResource(BASE_URL + url);
    }

    @When("^the client request information about a membership with GET (.*)$")
    public void the_client_request_information_about_a_membership_with_GET(String url) {
        membershipResponse = membershipHelperImpl.getResource(BASE_URL + url);
    }

    @Then("^the status code for the membership request should be (\\d+)$")
    public void the_status_code_for_the_membership_request_should_be(int expectedStatus) {
        assertStatusCode(expectedStatus, membershipResponse.getStatusCode().value());
    }

    private void assertStatusCode(int expected, int actual) {
        Assert.assertEquals("Expected the status code to be " + expected, expected, actual);
    }

    @Then("^the membership response should be JSON:$")
    public void the_membership_response_should_be_JSON(String expectedBody) throws IOException {
        MembershipDTO expected = mapper.readValue(expectedBody, MembershipDTO.class);
        Assert.assertNotNull("Expected a date", membershipResponse.getBody().getDateAdded());
        Assert.assertEquals("Expected a different selfRef", RestHelperImpl.getSelfRef(expected), RestHelperImpl.getSelfRef(membershipResponse.getBody()));
        Assert.assertEquals("Expected a different team ref", expected.getLink("team"), membershipResponse.getBody().getLink("team"));
        Assert.assertEquals("Expected a different team ref", expected.getLink("user"), membershipResponse.getBody().getLink("user"));
        Assert.assertEquals("Expected three links", expected.getLinks().size() , membershipResponse.getBody().getLinks().size());
    }


    @When("^the client request information about memberships with GET (.*)$")
    public void the_client_request_information_about_memberships_with_GET(String url) {
        membershipsResponse = membershipsHelperImpl.getResource(BASE_URL + url);
    }

    @Then("^the status code for the memberships request should be (\\d+)$")
    public void the_status_code_for_the_memberships_request_should_be(int expectedStatus) {
        assertStatusCode(expectedStatus, membershipsResponse.getStatusCode().value());
    }

    @Then("^the memberships response should be JSON:$")
    public void the_memberships_response_should_be_JSON(String expectedBody) {
        Assert.assertEquals("Expected a different response body", expectedBody, membershipsResponse.getBody());
    }


    @After("@MembershipStep")
    public void tearDown(){
        Assert.assertEquals("Could not delete team.", 200, teamHelperImpl.deleteResource(RestHelperImpl.getSelfRef(createdTeam.getBody())).getStatusCode().value());
        Assert.assertEquals("Could not delete user.", 200, userHelperImpl.deleteResource(RestHelperImpl.getSelfRef(createdUser.getBody())).getStatusCode().value());

    }

}
