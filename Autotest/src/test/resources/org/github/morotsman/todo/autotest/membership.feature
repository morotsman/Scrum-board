Feature: Test add/remove users to a team

@MembershipStep
Scenario: Add a user to a team
Given the System knows about the user:Niklas and the team:theTeam
When the client apply for membership with PUT /team/theTeam/membership/Niklas
Then the status code for the membership request should be 201
And the membership response should be JSON:
"""
{"links":[
{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/theTeam/membership/Niklas"},
{"rel":"team","href":"http://localhost:8080/todo/services/v1/team/theTeam"},
{"rel":"user","href":"http://localhost:8080/todo/services/v1/user/Niklas"}
],"dateAdded":"2014-01-27"}
"""

@MembershipStep
Scenario: Add a non existing user to a team
Given the System knows about the user:Niklas and the team:theTeam
When the client apply for membership with PUT /team/theTeam/membership/NonExisting
Then the status code for the membership request should be 404

@MembershipStep
Scenario: Add a user to a non existing team
Given the System knows about the user:Niklas and the team:theTeam
When the client apply for membership with PUT /team/nonExisting/membership/Niklas
Then the status code for the membership request should be 404



@MembershipStep
Scenario: Remove a user from a team
Given that the user:Niklas is member of the team:theTeam
When removing the membership with DELETE /team/theTeam/membership/Niklas
Then the status code for the membership request should be 200

@MembershipStep
Scenario: Remove a non existing user from a team
Given that the user:Niklas is member of the team:theTeam
When removing the membership with DELETE /team/theTeam/membership/Niklas2
Then the status code for the membership request should be 404

@MembershipStep
Scenario: Remove a user from a non existing team
Given that the user:Niklas is member of the team:theTeam
When removing the membership with DELETE /team/nonExisting/membership/Niklas
Then the status code for the membership request should be 404

@MembershipStep
Scenario: Remove a user that is not a member from a team
Given the System knows about the user:Niklas and the team:theTeam
When removing the membership with DELETE /team/theTeam/membership/Niklas
Then the status code for the membership request should be 404


@MembershipStep
Scenario: GET information about a membership
Given that the user:Niklas is member of the team:theTeam
When the client request information about a membership with GET /team/theTeam/membership/Niklas
Then the status code for the membership request should be 200
And the membership response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/theTeam/membership/Niklas"},
{"rel":"team","href":"http://localhost:8080/todo/services/v1/team/theTeam"},
{"rel":"user","href":"http://localhost:8080/todo/services/v1/user/Niklas"}
],"dateAdded":"2014-01-27"}
"""


@MembershipStep
Scenario: GET information but user is non existing
Given that the user:Niklas is member of the team:theTeam
When the client request information about a membership with GET /team/theTeam/membership/NonExisting
Then the status code for the membership request should be 404


@MembershipStep
Scenario: GET information about a membership team is non existing
Given that the user:Niklas is member of the team:theTeam
When the client request information about a membership with GET /team/NonExisting/membership/Niklas
Then the status code for the membership request should be 404




