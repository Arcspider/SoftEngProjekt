Feature: add activity to a project
    Description: A user adds an activity to a project.
    Actor: User

Scenario: add activity to a project.
   Given a project with id "030901" 
#  And the user adds activity "Beta"
#  Then the activity "Beta" is added to "030901"

# Scenario: add activity to a project when project has an activity with same name.
#    Given a project "030901" 
#    And the user is a leader for "030901"
#    When the user adds activity "Beta" 
#    And "030901" has a activity "Beta"
#   Then an error message "030901 already has an activity with the name Beta " is given
