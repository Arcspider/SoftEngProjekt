Feature: Allocate Time
  Description: The user allocates time they have spent working on an activity.
  Actor: User

  Scenario: The user allocates time to a specified activity.
    Given a project with  id "05-20-63" exists
	And the activity "Beta" exists in the project
    And the user with id "MIOX" is assigned to the activity "Beta"
    Then the user logs "5" hours on day "20-05-2020"
    Then the time "MIOX" "20-05-2020" "5" can be found in the activity "Beta"
#
#  Scenario: The user allocates time to an activity they are not assigned to.
#    Given the user with id "HAH" is not assigned to activity "Beta"
#    And the user allocates "time" to activity "Beta"
#    Then add "time" to the user
#    And add "time" spent by user on activity "Beta"
	Scenario: The user allocates time to an activity they've already assigned time to
	 Given a project with  id "05-20-63" exists
	And the activity "Beta" exists in the project
    And the user with id "MIOX" is assigned to the activity "Beta"
    Then the user logs "5" hours on day "20-05-2020"
    And the user again logs "5" hours on day "20-05-2020"
    Then the time "MIOX" "20-05-2020" "10" can be found in the activity "Beta"

    Scenario: The user allocates abscence
    Given a project with  id "05-20-63" exists
	And the activity "Beta" exists in the project
	And the activity "Charlie" exists in the project
    And the user with id "MIOX" is assigned to the activity "Beta"
    And the user with id "MIOX" is assigned to the activity "Charlie"
    Then the user logs "5" hours on day "20-05-2020"
    Then the user logs "5" hours on day "21-05-2020"
    Then the time "MIOX" "20-05-2020" "5" can be found in the activity "Beta"
    Then the time "MIOX" "27-05-2020" "5" can be found in the activity "Charlie"
    Then the user "MIOX" logs absence in the form of "Vacation" in the time period "15-05-2020" to "25-05-2020"
    Then the time "MIOX" "27-05-2020" "0" can be found in the activity "Beta"
    Then the time "MIOX" "27-05-2020" "5" can be found in the activity "Charlie"
