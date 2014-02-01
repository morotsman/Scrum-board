Feature: Check that it's possible to connect to the todo application

@ApplicationStatus
Scenario: Do a GET request on the context root of the todo application to verify that the application is up and running.
Given the todo application
When executing GET /todo
Then the status code should be 200