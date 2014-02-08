Feature: Test to add/delete/modify a team


@TestContext
Scenario: List all teams
Given the System knows about the teams: aTeam, bTeam, cTeam
When the client requests GET /team
Then the status code for the teams request should be 200
And the teams response should be JSON:
  """
{"teams":[
    {
        "links":[
            {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"},
            {"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/aTeam/membership"},
            {"rel":"sprint","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint"},
            {"rel":"story","href":"http://localhost:8080/todo/services/v1/team/aTeam/story"}
        ],
        "description":"Description",
        "teamName":"aTeam"
    },
    {
        "links":[
            {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/bTeam"},
            {"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/bTeam/membership"},
            {"rel":"sprint","href":"http://localhost:8080/todo/services/v1/team/bTeam/sprint"},
            {"rel":"story","href":"http://localhost:8080/todo/services/v1/team/bTeam/story"}
        ],
        "description":"Description",
        "teamName":"bTeam"
    },
    {
        "links":[
            {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/cTeam"},
            {"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/cTeam/membership"},
            {"rel":"sprint","href":"http://localhost:8080/todo/services/v1/team/cTeam/sprint"},
            {"rel":"story","href":"http://localhost:8080/todo/services/v1/team/cTeam/story"}
        ],
        "description":"Description",
        "teamName":"cTeam"
    }
]}
  """


@TestContext
Scenario: Create a team
Given the request body for the request is:
"""
{"description":"Description"}
"""
When the client requests PUT /team/aTeam
Then the status code for the team request should be 201
And the team response should be JSON:
  """
{
    "links":[
        {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"},
        {"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/aTeam/membership"},
        {"rel":"sprint","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint"},
        {"rel":"story","href":"http://localhost:8080/todo/services/v1/team/aTeam/story"}
    ],
    "description":"Description",
    "teamName":"aTeam"
}
  """


@TestContext
Scenario: GET a team
Given the System knows about the team: aTeam
When the client requests GET /team/aTeam
Then the status code for the team request should be 200
And the team response should be JSON:
"""
{
    "links":[
        {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"},
        {"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/aTeam/membership"},
        {"rel":"sprint","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint"},
        {"rel":"story","href":"http://localhost:8080/todo/services/v1/team/aTeam/story"}
    ],
    "description":"Description",
    "teamName":"aTeam"}
  """

@TestContext
Scenario: GET a team that don't exist
Given the client requests GET /team/1213212321
Then the status code for the team request should be 404

@TestContext
Scenario: DELETE a team
Given the System knows about the team: aTeam
When the client requests DELETE /team/aTeam
#Then the status code for the team request should be 200

@TestContext
Scenario: DELETE a team  that don't exist
Given the client requests DELETE /team/1213212321
Then the status code for the team request should be 404


@TestContext
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
{
    "links":[
        {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam"},
        {"rel":"membership","href":"http://localhost:8080/todo/services/v1/team/aTeam/membership"},
        {"rel":"sprint","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint"},
        {"rel":"story","href":"http://localhost:8080/todo/services/v1/team/aTeam/story"}
    ],
    "description":"Updated_Description",
    "teamName":"aTeam"
}
  """
















