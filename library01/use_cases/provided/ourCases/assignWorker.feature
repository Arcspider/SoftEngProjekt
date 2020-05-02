Feature: assign Worker
  Description: The user assign a worker to a activity.
  Actor: User

  Scenario: The user assign a worker to a activity.
    Given a project with  id "05-20-63"
	And has the activity "Beta"
 	And the worker with id "HH20" exists'
   Then the user assign the worker to the activity

