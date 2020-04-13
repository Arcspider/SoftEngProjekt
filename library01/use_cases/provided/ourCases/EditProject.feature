Feature: Edit project
    Description: The user edits the project.
    Actors: User
    
#Scenario: User edits a projects dates
 #   When the user chooses the project with id "030901"
  #  And the user enters the start and end dates "week 40" and "week 42"
   # And the dates are in the project period
    #Then the projects start and end dates are changed to "week 40" and "week 42"
    
Scenario: Change projects description
    When the user chooses the project with id of project Alpha. 
    And the user enters description "Test description"
    Then the projects description is overwritten with "Test description"
    
Scenario: Change projects name
    When the user chooses the project with id "030901"
    And the user changes the name from "Alpha" to "Gamma"
    Then the projects Name is changed to "Gamma"
