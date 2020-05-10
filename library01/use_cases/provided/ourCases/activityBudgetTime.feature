Feature: Allocate shifts and budgetted time
  Description: The user allocates time on an activity.
  Actor: User

Scenario: The user tries to allocate more time than an activity has left
	Given a project with  id "05-20-63" exists 
	And the activity "Beta" exists in the project
	And the activity "Beta" has a total of "5" hours budgetted
	And the user with id "MIOX" is assigned to the activity "Beta"
	Then the user "MIOX" logs "8" hours on day "20-05-2020" in the activity "Beta"
	Then the time "MIOX" "20-05-2020" "8" can not be found in the activity "Beta"
	
	Scenario: The user removes a shift
	Given a project with  id "05-20-63" exists 
	And the activity "Beta" exists in the project
	And the activity "Beta" has a total of "50" hours budgetted
	And the user with id "MIOX" is assigned to the activity "Beta"
	Then the user "MIOX" logs "8" hours on day "20-05-2020" in the activity "Beta"
	Then the time "MIOX" "20-05-2020" "8" can be found in the activity "Beta"
	Then the user "MIOX" removes the shift "20-05-2020" in the activity "Beta"
	Then the time "MIOX" "20-05-2020" "8" can not be found in the activity "Beta"
	
	Scenario: change budgetted hours of activity
	When the user chooses the activity "charlie"
	And the user changes its budgetted time to "150"
	Then the activity's budgetted time is set to "150"
