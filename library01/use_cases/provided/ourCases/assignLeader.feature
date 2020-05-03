Feature: Assign Leader
    Description: Assign a leader to a project
    Actors: User

Scenario: Assigning a worker as leader for a project
	Given that a project with id "030901" does not have a leader
	When the user adds the worker with id "testID" as leader
#	Then the worker with id "testID" as leader for project "030901"

#Scenario: Assigning a worker as leader for a project they already are leader of
#	Given that a worker with id "testID" is a leader for project "030901"
#	When the user adds the worker with id "testID" as leader to the project
#	Then an error message "This worker is already leader for the project" is written

#Scenario: Assigning a worker as leader for a project which already has a leader
#	Given that a worker with id "anotherID" is leader for project "030901"
#	When the user adds the worker with id "testID" as a leader for the project
	#Then an error message "This project already has a leader" is witten