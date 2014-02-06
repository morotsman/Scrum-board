Feature: Test add/remove sprints to a team

@TestContext
Scenario: Create a story for a team
Given the System knows about the team: aTeam
And the request body for the story request is:
"""
{"description":"Description", "estimate":"8"}
"""
When the clients creates a story with PUT /team/aTeam/story/FirstStory
Then the status code for the story request should be 201
And the story response should be JSON:
"""
{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story/FirstStory"}],"name":"FirstStory","description":"Description","estimate":8}
"""