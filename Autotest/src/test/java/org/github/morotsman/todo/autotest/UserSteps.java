package org.github.morotsman.todo.autotest;


import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.github.morotsman.todo.autotest.util.RestHelper;
import org.github.morotsman.todo.autotest.util.TestContext;
import org.github.morotsman.todo.web.dto.UserDTO;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class UserSteps {

    private static final String BASE_URL = "http://localhost:8080/todo/services/v1/user";

    private RestHelper<UserDTO> userHelperImpl = TestContext.instance().getUserHelperImpl();

    private ResponseEntity<String> response;

    private ObjectMapper mapper = new ObjectMapper();

    private UserDTO requestBody;


    @Given("^the System knows about the user: (.*)$")
    public void that_user_exists(String user)  {
        if(!StringUtils.hasLength(user)){
            return;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("aPassword");
        userDTO.setFirstName("aName");
        userDTO.setLastName("aLastName");
        userDTO.setPhoneNumber("aPhoneNumber");
        userDTO.setEmail("email@something.com");

        Assert.assertEquals("Could not create user " + user,201, userHelperImpl.putResource(BASE_URL +"/" +user, userDTO).getStatusCode().value());
    }


    @Given("^the System knows about the users: (.*)$")
    public void that_users_exists(String users)  {
        if(!StringUtils.hasLength(users)){
            return;
        }
        for(String user : users.split(",")){
            that_user_exists(user);
        }
    }

    @Given("^the request body for the user request is:$")
    public void the_request_body_for_the_user_request_is(String body) throws IOException {
        requestBody =  mapper.readValue(body, UserDTO.class);
    }

    /*
    @Given("^that the user: Niklas has the membership in the teams: aTeam,bTeam$")
    public void that_the_user_Niklas_has_the_membership_in_the_teams_aTeam_bTeam() {
        // Express the Regexp above with the code you wish you had
    }
    */

    @When("^the client lists users with GET /user(.*)$")
    public void executing_GET_user(String requestParameters) {
        response = userHelperImpl.getResource(BASE_URL + requestParameters);
    }

    @When("^the client requests PUT /user/(.*)$")
    public void executing_PUT_user(String userName) throws IOException {
        response = userHelperImpl.putResource(BASE_URL + "/"+ userName,requestBody);
    }

    @When("^the client requests DELETE /user/(.*)")
    public void the_client_requests_DELETE_user(String userName) {
        response =  userHelperImpl.deleteResource(BASE_URL +"/" +userName);
    }

    @When("^the client requests GET /user/(.*)")
    public void the_client_requests_GET_user(String userName) {
        response = userHelperImpl.getResource(BASE_URL + "/"+userName);
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



}
