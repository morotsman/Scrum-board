Feature: Test to add/delete/modify a team


@TeamStep
Scenario: List all teams
Given the System knows about the team: aTeam, bTeam, cTeam
When the client requests GET /team
Then the status code for the teams request should be 200
And the teams response should be JSON:
  """
{"teams":[{"links":[
    {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"}],"description":"Description"},
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/bTeam"}],"description":"Description"},
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/cTeam"}],"description":"Description"}
]}
  """


@TeamStep
Scenario: Create a team
Given the request body for the request is:
"""
{"description":"Description"}
"""
When the client requests PUT /team/aTeam
Then the status code for the team request should be 201
And the team response should be JSON:
  """
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"}],"description":"Description"}
  """


@TeamStep
Scenario: GET a team
Given the System knows about the team: aTeam
When the client requests GET /team/aTeam
Then the status code for the team request should be 200
And the team response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"}],"description":"Description"}
  """

@TeamStep
Scenario: GET a team that don't exist
Given the client requests GET /team/1213212321
Then the status code for the team request should be 404

@TeamStep
Scenario: DELETE a team
Given the System knows about the team: aTeam
When the client requests DELETE /team/aTeam
Then the status code for the team request should be 200

@TeamStep
Scenario: DELETE a team  that don't exist
Given the client requests DELETE /team/1213212321
Then the status code for the team request should be 404


@TeamStep
Scenario: Modify a team
Given the System knows about the team: aTeam
When the request body for the request is:
"""
{"description":"Updated_Description"}
"""
And the client requests PUT /team/aTeam
Then the status code for the team request should be 201
And the team response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"}],"description":"Updated_Description"}
  """



@TeamStep
Scenario: GET a team with team members
Given that the team: aTeam has the members: Niklas,Lena
When the client requests GET /team/aTeam
Then the status code for the team request should be 200
And the team response should be JSON:
"""
{"links":
[
{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"},
{"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/aTeam/membership/Niklas"},
{"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/aTeam/membership/Lena"}
],"description":"Description"}
  """


@TeamStep
Scenario: List all teams
Given that the team: aTeam has the members: Niklas,Lena
When the client requests GET /team
Then the status code for the teams request should be 200
And the teams response should be JSON:
  """
{"teams":[{"links":[
    {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"}],"description":"Description"}
]}
  """


@TeamStep
Scenario: List user that belong to teams
Given that the team: aTeam has the members: Niklas
When the client requests GET /user/Niklas
Then the status code for the user request should be 200
And the response should be JSON:
  """
{"links":
[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},
{"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},
{"rel":"applyForMembership","href":"http://localhost:8080/todo/services/v1/team/insertATeamNameHere/membership/Niklas"},
{"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/aTeam/membership/Niklas"}],
"userName":"Niklas"}
  """


@TeamStep
Scenario: List users that belong to teams
Given that the team: aTeam has the members: Niklas
When the client requests GET /user
Then the status code for the user request should be 200
And the response should be JSON:
  """
{"links":
[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user"}],
"users":[
    {"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/user/Niklas"},
    {"rel":"teams","href":"http://localhost:8080/todo/services/v1/team"},
    {"rel":"applyForMembership","href":"http://localhost:8080/todo/services/v1/team/insertATeamNameHere/membership/Niklas"}],"userName":"Niklas"}]}
  """






