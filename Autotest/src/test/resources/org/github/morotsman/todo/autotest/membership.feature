Feature: Test add/remove users to a team

@TestContext
Scenario: Add a user to a team
Given the System knows about the user: Niklas
And the System knows about the team: theTeam
When the client apply for membership with PUT /team/theTeam/membership/Niklas
Then the status code for the membership request should be 201
And the membership response should be JSON:
"""
{"links":[
{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/theTeam/membership/Niklas"},
{"rel":"team","href":"http://localhost:8080/todo/services/v1/team/theTeam"},
{"rel":"user","href":"http://localhost:8080/todo/services/v1/user/Niklas"}
],"teamName":"theTeam","userName":"Niklas"}
"""

#@TestContext
Scenario: Add a non existing user to a team
Given the System knows about the user: Niklas
And the System knows about the team: theTeam
When the client apply for membership with PUT /team/theTeam/membership/NonExisting
Then the status code for the membership request should be 404

@TestContext
Scenario: Add a user to a non existing team
Given the System knows about the user: Niklas
And the System knows about the team: theTeam
When the client apply for membership with PUT /team/nonExisting/membership/Niklas
Then the status code for the membership request should be 404



@TestContext
Scenario: Remove a user from a team
Given the System knows about the user: Niklas
And the System knows about the team: theTeam
And that the user:Niklas is member of the team:theTeam
When removing the membership with DELETE /team/theTeam/membership/Niklas
Then the status code for the membership request should be 200

@TestContext
Scenario: Remove a non existing user from a team
When removing the membership with DELETE /team/theTeam/membership/Niklas2
Then the status code for the membership request should be 404

@TestContext
Scenario: Remove a user from a non existing team
When removing the membership with DELETE /team/nonExisting/membership/Niklas
Then the status code for the membership request should be 404

@TestContext
Scenario: Remove a user that is not a member from a team
Given the System knows about the user: Niklas
And the System knows about the team: theTeam
When removing the membership with DELETE /team/theTeam/membership/Niklas
Then the status code for the membership request should be 404


@TestContext
Scenario: GET information about a membership
Given the System knows about the user: Niklas
And the System knows about the team: theTeam
And that the user:Niklas is member of the team:theTeam
When the client request information about a membership with GET /team/theTeam/membership/Niklas
Then the status code for the membership request should be 200
And the membership response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/theTeam/membership/Niklas"},
{"rel":"team","href":"http://localhost:8080/todo/services/v1/team/theTeam"},
{"rel":"user","href":"http://localhost:8080/todo/services/v1/user/Niklas"}
],"teamName":"theTeam","userName":"Niklas"}
"""


@TestContext
Scenario: GET information but user is non existing
Given the System knows about the team: theTeam
When the client request information about a membership with GET /team/theTeam/membership/NonExisting
Then the status code for the membership request should be 404


@TestContext
Scenario: GET information about a membership team is non existing
Given the System knows about the user: Niklas
When the client request information about a membership with GET /team/NonExisting/membership/Niklas
Then the status code for the membership request should be 404


@TestContext
Scenario: List all memberships for a team
Given the System knows about the user: Niklas
And the System knows about the team: theTeam
And that the user:Niklas is member of the team:theTeam
When the client request information about memberships with GET /team/theTeam/membership
Then the status code for the memberships request should be 200
And the memberships response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/theTeam/membership"}],
    "memberships":[
        {
            "links":[
                {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/theTeam/membership/Niklas"},
                {"rel":"team","href":"http://localhost:8080/todo/services/v1/team/theTeam"},
                {"rel":"user","href":"http://localhost:8080/todo/services/v1/user/Niklas"}
            ],
            "teamName":"theTeam","userName":"Niklas"
        }
     ]
}
"""

@TestContext
Scenario: List all memberships for a team when there are no members
Given the System knows about the user: Niklas
And the System knows about the team: theTeam
When the client request information about memberships with GET /team/theTeam/membership
Then the status code for the memberships request should be 200
And the memberships response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/theTeam/membership"}],
    "memberships":[
     ]
}
"""


@TestContext
Scenario: Verify that a user is only member of the team that he applied for membership for
Given the System knows about the user: Niklas
And the System knows about the teams: theTeam,anotherTeam
And that the user:Niklas is member of the team:theTeam
When the client request information about memberships with GET /team/anotherTeam/membership
Then the status code for the memberships request should be 200
And the memberships response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/anotherTeam/membership"}],
    "memberships":[
     ]
}
"""




