Feature: edit activity
    Description: A user edits an activity in a project
    Actor: User

Scenario: The user edits an activity
    Given a project with id "030901" has and activity "Beta"
    When the user inputs start date "week 40 year 2020" and end date "week 42 year 2020"
    Then set the start to "week 40 year 2020" and end date to "week 42 year 2020" for the activity "Beta" 
    
Scenario: End date is before start date
    Given a project with id "030901" has and activity "Beta"
   When the user inputs start date "week 42 year 2020" and end date "week 40 year 2020"
    Then an error message "End date is before start date" is given for the activity 
    
Scenario: change name of activity
	When the user chooses the activity "charlie"
    And  the user changes the activity name to "Gamma"
    Then the activitys name is changed to "Gamma"
    
Scenario: change description of activity
	When the user chooses the activity "charlie"
    And  the user changes the activity description to "test Description"
    Then the activitys description is changed to "test Description"
	