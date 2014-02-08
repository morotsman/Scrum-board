Feature: Test add/remove sprints to a team

@TestContext
Scenario: Create a story for a team
Given the System knows about the team: aTeam
And the request body for the story request is:
"""
{"description":"Description", "estimate":"8"}
"""
When the clients creates a story with PUT /team/aTeam/story/aStory
Then the status code for the story request should be 201
And the story response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story/aStory"}],"name":"aStory","description":"Description","estimate":8}
"""


@TestContext
Scenario: Create a story for a non existing team
Given the request body for the story request is:
"""
{"description":"Description", "estimate":"8"}
"""
When the clients creates a story with PUT /team/aTeam/story/aStory
Then the status code for the story request should be 404


@TestContext
Scenario: Get information about a story
Given the System knows about the team: aTeam
And that the team: aTeam has the story: aStory
When the clients get a story with GET /team/aTeam/story/aStory
Then the status code for the story request should be 200
And the story response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story/aStory"}],"name":"aStory","description":"SomeDescription","estimate":8}
"""

@TestContext
Scenario: Get information about a story on a non existing team
When the clients get a story with GET /team/bTeam/story/aStory
Then the status code for the story request should be 404

@TestContext
Scenario: Get information about a non existing story
Given the System knows about the team: aTeam
When the clients get a story with GET /team/bTeam/story/aStory
Then the status code for the story request should be 404

@TestContext
Scenario: Modify a story
Given the System knows about the team: aTeam
And that the team: aTeam has the story: aStory
And the request body for the story request is:
"""
{"description":"Changed_Description", "estimate":"10"}
"""
When the clients creates a story with PUT /team/aTeam/story/aStory
Then the status code for the story request should be 201
And the story response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story/aStory"}],"name":"aStory","description":"Changed_Description","estimate":10}
"""

@TestContext
Scenario: Delete a story
Given the System knows about the team: aTeam
And that the team: aTeam has the story: aStory
When the clients deletes a story with DELETE /team/aTeam/story/aStory
Then the status code for the story request should be 200

@TestContext
Scenario: Delete a non existing story
Given the System knows about the team: aTeam
When the clients deletes a story with DELETE /team/aTeam/story/aStory
Then the status code for the story request should be 404

@TestContext
Scenario: Delete a story on a non existing team
When the clients deletes a story with DELETE /team/aTeam/story/aStory
Then the status code for the story request should be 404


@TestContext
Scenario: List all stories for a team
Given the System knows about the team: aTeam
And that the team: aTeam has the stories: aStory,bStory
When the clients creates a story with GET /team/aTeam/story
Then the status code for the stories request should be 200
And the stories response should be JSON:
"""
{
    "links":[
        {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story"}
        ],
        "stories":[
            {
                "links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story/aStory"}],
                "name":"aStory",
                "description":"SomeDescription",
                "estimate":8
            },
            {
                "links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story/bStory"}],
                "name":"bStory",
                "description":"SomeDescription",
                "estimate":8
            }
        ]
}
"""

@TestContext
Scenario: List all stories for a team when team has no stories
Given the System knows about the team: aTeam
When the clients creates a story with GET /team/aTeam/story
Then the status code for the stories request should be 200
And the stories response should be JSON:
"""
{
    "links":[
        {"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story"}
        ],
        "stories":[]
}
"""
