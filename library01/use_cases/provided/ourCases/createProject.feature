Feature: Create Project
    Description: The user creates a project
    Actors: User

Scenario: User creates a project
     Given a user creates a project with name "Alpha"
     Then the project with the ID is contained in the list

Scenario: User creates a project with an empty name
     Given a user creates another project with name ""
    Then the project is not created
     And the user receives an error message "The project has no name, so it was not created"