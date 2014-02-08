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
Scenario: Delete a story
Given the System knows about the team: aTeam
And that the team: aTeam has the story: aStory
When the clients deletes a story with DELETE /team/aTeam/story/aStory
Then the status code for the story request should be 200


#@TestContext
#Scenario: List all stories for a team
#Given the System knows about the team: aTeam
#And that the team: aTeam has the stories: aStory, bStory
#When the clients creates a story with GET /team/aTeam/story
#Then the status code for the story request should be 200
#And the story response should be JSON:
#"""
#{"links":[{"rel":"self","href":"http://localhost:8080/todo/services/v1/team/aTeam/story/aStory"}],"name":"aStory","description":"Description","estimate":8}
#"""
