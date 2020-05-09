Feature: Get assigned workers
    Description: Lists all workers assigned to an activity
    Actors: User

Scenario: Getting the workers assigned to an activity
	Given an activity "Test" has workers assigned to it
	Then the user can get a list of the workers

Scenario: There are no workers assigned to the activity
	Given an activity "Test" that has no workers assigned to it
	Then the message "There are no workers assigned" is displayed
