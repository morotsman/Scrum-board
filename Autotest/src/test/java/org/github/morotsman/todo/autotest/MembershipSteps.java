package org.github.morotsman.todo.autotest;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.difflib.StringUtills;
import org.codehaus.jackson.map.ObjectMapper;
import org.github.morotsman.todo.autotest.util.RestHelper;
import org.github.morotsman.todo.autotest.util.RestHelperImpl;
import org.github.morotsman.todo.autotest.util.TestContext;
import org.github.morotsman.todo.web.dto.MembershipDTO;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class MembershipSteps {

    private static final String BASE_URL = "http://localhost:8080/todo/services/v1";

    private RestHelper<MembershipDTO> membershipHelperImpl = TestContext.instance().getMembershipHelperImpl();
    private RestHelper<String> membershipsHelperImpl = new RestHelperImpl<String>();

    private ResponseEntity<String> membershipResponse;
    private ResponseEntity<String> membershipsResponse;

    private ObjectMapper mapper = new ObjectMapper();



    @Given("^that the user:(\\w+) is member of the team:(.*)$")
    public void that_the_user_is_member_of_the_team(String userName, String teamName) throws IOException {
        createMembership("/team/" + teamName + "/membership/" + userName);
    }

    private void createMembership(String url) {
        membershipResponse = membershipHelperImpl.putResource(BASE_URL + url);
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
        Assert.assertEquals("Expected a different body", StringUtils.trimAllWhitespace(expectedBody),membershipResponse.getBody());
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
        Assert.assertEquals("Expected a different response body", StringUtils.trimAllWhitespace(expectedBody), membershipsResponse.getBody());
    }




}
