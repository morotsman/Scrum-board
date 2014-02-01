Feature: Test to user administration

@TestContext
@UserStep
Scenario: List all users
Given the System knows about the users:Niklas,Lena,Hektor,Beata
When the client requests GET /user
Then the status code for the user request should be 200
And the response should be JSON:
  """
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user"}],
"users":[
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Beata"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},{"rel":"applyForMembership","href":"http://localhost:8080/todo/services/v1/team/insertATeamNameHere/membership/Beata"}],
    "userName":"Beata"},
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Hektor"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},{"rel":"applyForMembership","href":"http://localhost:8080/todo/services/v1/team/insertATeamNameHere/membership/Hektor"}],
    "userName":"Hektor"},
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Lena"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},{"rel":"applyForMembership","href":"http://localhost:8080/todo/services/v1/team/insertATeamNameHere/membership/Lena"}],
    "userName":"Lena"},
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},{"rel":"applyForMembership","href":"http://localhost:8080/todo/services/v1/team/insertATeamNameHere/membership/Niklas"}],
    "userName":"Niklas"}]}
"""

#@UserStep
#Scenario: List users when no users exists
#Given the System knows about the users:
#When the client requests GET /user
#Then the status code for the user request should be 200
#And the response should be JSON:
#  """
#{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user"}],"users":[]}
#  """

#@UserStep
#Scenario: Create a user
#Given the System knows about the users:
#When the client requests PUT /user/Niklas
#Then the status code for the user request should be 201
#And the response should be JSON:
#  """
#{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},{"rel":"applyForMembership","href":"http://localhost:8080/todo/services/v1/team/insertATeamNameHere/membership/Niklas"}],"userName":"Niklas"}
#  """

#@UserStep
#Scenario: Try to create an already existing user
#Given the System knows about the users:Niklas
#When the client requests PUT /user/Niklas
#Then the status code for the user request should be 409


#@UserStep
#Scenario: Delete a user
#Given the System knows about the users:Niklas
#When the client requests DELETE /user/Niklas
#Then the status code for the user request should be 200

#@UserStep
#Scenario: Delete a user that does not exist
#Given the System knows about the users:
#When the client requests DELETE /user/Niklas
#Then the status code for the user request should be 404

#@UserStep
#Scenario: Get a user
#Given the System knows about the users:Niklas
#When the client requests GET /user/Niklas
#Then the status code for the user request should be 200
#And the response should be JSON:
#  """
#{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},{"rel":"applyForMembership","href":"http://localhost:8080/todo/services/v1/team/insertATeamNameHere/membership/Niklas"}],"userName":"Niklas"}
#  """

#@UserStep
#Scenario: Get a user that don't exist
#Given the System knows about the users:
#When the client requests GET /user/Niklas
#Then the status code for the user request should be 404


