Feature: Test to user administration

@TestContext
Scenario: List all users
Given the System knows about the users: Niklas,Lena,Hektor,Beata
When the client requests GET /user
Then the status code for the user request should be 200
And the response should be JSON:
  """
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user"}],
"users":[
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Beata"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],
    "userName":"Beata","password":null,"firstName":"aName","lastName":"aLastName","email":"email@something.com","phoneNumber":"aPhoneNumber"},
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Hektor"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],
    "userName":"Hektor","password":null,"firstName":"aName","lastName":"aLastName","email":"email@something.com","phoneNumber":"aPhoneNumber"},
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Lena"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],
    "userName":"Lena","password":null,"firstName":"aName","lastName":"aLastName","email":"email@something.com","phoneNumber":"aPhoneNumber"},
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],
    "userName":"Niklas","password":null,"firstName":"aName","lastName":"aLastName","email":"email@something.com","phoneNumber":"aPhoneNumber"}]}
"""

@TestContext
Scenario: List users when no users exists
When the client requests GET /user
Then the status code for the user request should be 200
And the response should be JSON:
  """
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user"}],"users":[]}
  """

@TestContext
Scenario: Create a user
Given the request body for the user request is:
"""
{"password":"aPassword", "firstName":"aName", "lastName":"aLastName", "phoneNumber":"aPhoneNumber", "email":"email@something.com"}
"""
When the client requests PUT /user/Niklas
Then the status code for the user request should be 201
And the response should be JSON:
  """
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],"userName":"Niklas","password":null,"firstName":"aName","lastName":"aLastName","email":"email@something.com","phoneNumber":"aPhoneNumber"}
  """

@TestContext
Scenario: Try to create an already existing user
Given the System knows about the user: Niklas
And the request body for the user request is:
"""
{"firstName":"aName2", "lastName":"aLastName2", "phoneNumber":"aPhoneNumber2", "email":"email@something.com2", "userName": "invalid"}
"""
When the client requests PUT /user/Niklas
Then the status code for the user request should be 201
And the response should be JSON:
  """
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],"userName":"Niklas","password":null,"firstName":"aName2","lastName":"aLastName2","email":"email@something.com2","phoneNumber":"aPhoneNumber2"}
  """


@TestContext
Scenario: Change password
Given the System knows about the user: Niklas
And the request body for the user request is:
"""
{"password":"aPassword2", "firstName":"aName2", "lastName":"aLastName2", "phoneNumber":"aPhoneNumber2", "email":"email@something.com2"}
"""
When the client requests PUT /user/Niklas
Then the status code for the user request should be 201
And the response should be JSON:
  """
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],"userName":"Niklas","password":null,"firstName":"aName2","lastName":"aLastName2","email":"email@something.com2","phoneNumber":"aPhoneNumber2"}
  """


@TestContext
Scenario: Delete a user
Given the System knows about the user: Niklas
When the client requests DELETE /user/Niklas
Then the status code for the user request should be 200

@TestContext
Scenario: Delete a user that does not exist
When the client requests DELETE /user/Niklas
Then the status code for the user request should be 404

@TestContext
Scenario: Get a user
Given the System knows about the user: Niklas
When the client requests GET /user/Niklas
Then the status code for the user request should be 200
And the response should be JSON:
  """
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],"userName":"Niklas","password":null,"firstName":"aName","lastName":"aLastName","email":"email@something.com","phoneNumber":"aPhoneNumber"}
  """

@TestContext
Scenario: Get a user that don't exist
When the client requests GET /user/Niklas
Then the status code for the user request should be 404

@TestContext
Scenario: List user that belong to teams
Given the System knows about the user: Niklas
And the System knows about the team: aTeam
And that the user:Niklas is member of the team:aTeam
When the client requests GET /user/Niklas
Then the status code for the user request should be 200
And the response should be JSON:
  """
{"links":
[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},
{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},
{"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/aTeam/membership/Niklas"}],
"userName":"Niklas","password":null,"firstName":"aName","lastName":"aLastName","email":"email@something.com","phoneNumber":"aPhoneNumber"}
  """


@TestContext
Scenario: List users that belong to teams
Given the System knows about the user: Niklas
And the System knows about the team: aTeam
And that the user:Niklas is member of the team:aTeam
When the client requests GET /user
Then the status code for the user request should be 200
And the response should be JSON:
  """
{"links":
[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user"}],
"users":[
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},
    {"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"}],"userName":"Niklas","password":null,"firstName":"aName","lastName":"aLastName","email":"email@something.com","phoneNumber":"aPhoneNumber"}]}
  """


