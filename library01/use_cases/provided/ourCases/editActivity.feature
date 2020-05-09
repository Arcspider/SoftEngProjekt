Feature: edit activity
    Description: A user edits an activity in a project
    Actor: User

Scenario: The user edits an activity
    Given a project with id "030901" has and activity "Beta"
    When the user inputs start date "40-2020" and end date "42-2020"
    Then set the start to "40-2020" and end date to "42-2020" for the activity "Beta"

Scenario: End date is before start date
    Given a project with id "030901" has and activity "Beta"
   When the user inputs start date "42-2020" and end date "40-2020"
    Then an error message "End date is before start date" is given for the activity

Scenario: change name of activity
	When the user chooses the activity "charlie"
    And  the user changes the activity name to "Gamma"
    Then the activitys name is changed to "Gamma"

Scenario: change description of activity
	When the user chooses the activity "charlie"
    And  the user changes the activity description to "test Description"
    Then the activitys description is changed to "test Description"

Scenario: change budgetted hours of activity
	When the user chooses the activity "charlie"
	And the user changes its budgetted time to "150"
	Then the activity's budgetted time is set to "150"
