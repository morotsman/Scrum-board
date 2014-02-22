Feature: Test add/remove sprints to a team

@TestContext
Scenario: Create a sprint for a team
Given the System knows about the team: aTeam
When the clients creates a sprint with PUT /team/aTeam/sprint/2_14_3
Then the status code for the sprint request should be 201
And the sprint response should be JSON:
"""
{"links":[
    {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint/2_14_3"},
    {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/aTeam"}
],
"name":"2_14_3"}
"""

@TestContext
Scenario: Create a sprint that already exists
Given the System knows about the team: aTeam
And that the team: aTeam has the sprint: 2_14_3
When the clients creates a sprint with PUT /team/aTeam/sprint/2_14_3
Then the status code for the sprint request should be 409

@TestContext
Scenario: Create a sprint that already exists for another team
Given the System knows about the teams: aTeam,bTeam
And that the team: aTeam has the sprint: 2_14_3
When the clients creates a sprint with PUT /team/bTeam/sprint/2_14_3
Then the status code for the sprint request should be 201
And the sprint response should be JSON:
 """
 {"links":[
     {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/bTeam/sprint/2_14_3"},
     {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/bTeam"}
 ],
 "name":"2_14_3"}
 """

 @TestContext
 Scenario: Delete a sprint
 Given the System knows about the team: aTeam
 And that the team: aTeam has the sprint: 2_14_3
 When the clients remove a sprint with DELETE /team/aTeam/sprint/2_14_3
 Then the status code for the sprint request should be 200

@TestContext
Scenario: Delete a non existing sprint
Given the System knows about the team: aTeam
When the clients remove a sprint with DELETE /team/aTeam/sprint/2_14_3
Then the status code for the sprint request should be 404

@TestContext
Scenario: Get a sprint
Given the System knows about the team: aTeam
And that the team: aTeam has the sprint: 2_14_3
When the clients gets a sprint with GET /team/aTeam/sprint/2_14_3
Then the status code for the sprint request should be 200
And the sprint response should be JSON:
    """
    {"links":[
        {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint/2_14_3"},
        {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/aTeam"}
    ],
    "name":"2_14_3"}
    """

@TestContext
Scenario: Get a non existing sprint
Given the System knows about the team: aTeam
When the clients gets a sprint with GET /team/aTeam/sprint/2_14_3
Then the status code for the sprint request should be 404

@TestContext
Scenario: Get a non existing team
When the clients gets a sprint with GET /team/aTeam/sprint/2_14_3
Then the status code for the sprint request should be 404

@TestContext
Scenario: Get all sprints
Given the System knows about the team: aTeam
And that the team: aTeam has the sprints: 2_14_3,2_14_4
When the clients gets a sprint with GET /team/aTeam/sprint
Then the status code for the sprint request should be 200
And the sprint response should be JSON:
"""
{
    "links":
        [
            {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint"},
            {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/aTeam"}
        ],
    "sprints":
    [
        {
            "links":
                [
                    {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint/2_14_3"},
                    {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/aTeam"}
                ]
            ,"name":"2_14_3"
        },
        {
            "links":
                [
                    {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint/2_14_4"},
                    {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/aTeam"}
                ]
            ,"name":"2_14_4"
        }
    ]
}
"""

@TestContext
Scenario: List sprints when non exists
Given the System knows about the team: aTeam
When the clients gets a sprint with GET /team/aTeam/sprint
Then the status code for the sprint request should be 200
And the sprint response should be JSON:
"""
{
    "links":
        [
            {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/sprint"},
            {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/aTeam"}
        ],
    "sprints":
    [
    ]
}
"""

@TestContext
Scenario: Verfify that sprints fpr another team is not listed
Given the System knows about the teams: aTeam,bTeam
And that the team: aTeam has the sprints: 2_14_3,2_14_4
When the clients gets a sprint with GET /team/bTeam/sprint
Then the status code for the sprint request should be 200
And the sprint response should be JSON:
"""
{
    "links":
        [
            {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/bTeam/sprint"},
            {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/bTeam"}
        ],
    "sprints":
    [
    ]
}
"""

