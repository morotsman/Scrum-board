package org.github.morotsman.todo.autotest;


import cucumber.annotation.After;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.github.morotsman.todo.autotest.util.RestHelperImpl;
import org.github.morotsman.todo.autotest.util.TestContext;
import org.github.morotsman.todo.web.dto.TeamDTO;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.util.StringUtils;


import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

public class UserSteps {

    private static final String BASE_URL = "http://localhost:8080/todo/services/v1/user/";

    private RestHelperImpl<String> userHelperImpl = new RestHelperImpl<String>(String.class);

    private ResponseEntity<String> response;
    private Set<String> userToDelete = new HashSet<String>();

    @Given("^the System knows about the users:(.*)$")
    public void that_users_exists(String users) {
        if(!StringUtils.hasLength(users)){
            return;
        }
        for(String user : users.split(",")){
            Assert.assertEquals("Could not create user " + user,201, userHelperImpl.putResource(BASE_URL + user,null).getStatusCode().value());
            userToDelete.add(BASE_URL + user);
        }
    }

    @Given("^that the user: Niklas has the membership in the teams: aTeam,bTeam$")
    public void that_the_user_Niklas_has_the_membership_in_the_teams_aTeam_bTeam() {
        // Express the Regexp above with the code you wish you had
    }

    @When("^the client requests GET /user$")
    public void executing_GET_user() {
        response = userHelperImpl.getResource(BASE_URL);
    }

    @When("^the client requests PUT /user/(.*)$")
    public void executing_PUT_user(String userName) {
        response = userHelperImpl.putResource(BASE_URL+userName,null);
        userToDelete.add(BASE_URL+userName);
    }

    @When("^the client requests DELETE /user/(.*)")
    public void the_client_requests_DELETE_user(String userName) {
        response =  userHelperImpl.deleteResource(BASE_URL+userName);
        userToDelete.remove(BASE_URL+userName);
    }

    @When("^the client requests GET /user/(.*)")
    public void the_client_requests_GET_user(String userName) {
        response = userHelperImpl.getResource(BASE_URL+userName);
    }

    @Then("^the status code for the user request should be (\\d+)$")
    public void GET_user_should_result_in_status_code(int expectedStatus) {
        assertStatusCode(expectedStatus, response.getStatusCode().value());
    }

    @Then("^the response should be JSON:$")
    public void the_response_should_be_JSON(String expected) throws UnsupportedEncodingException {
        Assert.assertEquals(StringUtils.trimAllWhitespace(expected),response.getBody());
    }

    private void assertStatusCode(int expected, int actual) {
        Assert.assertEquals("Expected the status code to be " + expected, expected, actual);
    }

    @After("@UserStep")
    public void tearDown(){
        for(String user: userToDelete){
            Assert.assertEquals("Expected the user to be deleted.", 200, userHelperImpl.deleteResource(user).getStatusCode().value());
        }
    }


}
