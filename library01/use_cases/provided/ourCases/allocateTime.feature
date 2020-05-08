Feature: Allocate Time
  Description: The user allocates time they have spent working on an activity.
  Actor: User

  Scenario: The user allocates time to a specified activity.
    Given a project with  id "05-20-63" exists
	And the activity "Beta" exists in the project
	And the activity "Beta" has a total of "150" hours budgetted
    And the user with id "MIOX" is assigned to the activity "Beta"
	Then the user "MIOX" logs "5" hours on day "20-05-2020" in the activity "Beta"
    Then the time "MIOX" "20-05-2020" "5" can be found in the activity "Beta"


	Scenario: The user allocates time to an activity they've already assigned time to
	 Given a project with  id "05-20-63" exists
	And the activity "Beta" exists in the project
	And the activity "Beta" has a total of "150" hours budgetted
    And the user with id "MIOX" is assigned to the activity "Beta"
    Then the user "MIOX" logs "5" hours on day "20-05-2020" in the activity "Beta"
    Then the user "MIOX" logs "5" hours on day "20-05-2020" in the activity "Beta"
    Then the time "MIOX" "20-05-2020" "10" can be found in the activity "Beta"

    Scenario: The user allocates abscence
    Given a project with  id "05-20-63" exists
	And the activity "Beta" exists in the project
	And the activity "Charlie" exists in the project
    And the user with id "MIOX" is assigned to the activity "Beta"
    And the activity "Beta" has a total of "150" hours budgetted
    And the user with id "MIOX" is assigned to the activity "Charlie"
    And the activity "Charlie" has a total of "150" hours budgetted
    Then the user "MIOX" logs "5" hours on day "20-05-2020" in the activity "Beta"
    Then the user "MIOX" logs "5" hours on day "27-05-2020" in the activity "Charlie"
    Then the time "MIOX" "20-05-2020" "5" can be found in the activity "Beta"
    Then the time "MIOX" "27-05-2020" "5" can be found in the activity "Charlie"
    Then the user "MIOX" logs absence in the form of "Vacation" in the time period "15-05-2020" to "25-05-2020"
    Then the time "MIOX" "20-05-2020" "5" can not be found in the activity "Beta"
    Then the time "MIOX" "27-05-2020" "5" can be found in the activity "Charlie"
    
    Scenario: The user allocates time to a specified activity, and the activity's budgetted time changes
    Given a project with  id "05-20-63" exists 
    And the activity "Beta" exists in the project
	And the activity "Beta" has a total of "150" hours budgetted
	And the user with id "MIOX" is assigned to the activity "Beta"
	Then the user "MIOX" logs "5" hours on day "20-05-2020" in the activity "Beta"
	Then the time "MIOX" "20-05-2020" "5" can be found in the activity "Beta"
	Then the activity has "145" hours left
	
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
	