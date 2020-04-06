Feature: Create Project
    Description: The user creates a project
    Actors: User
    
Scenario: User creates a project
     Given a user creates a project with name "Alpha"
     And there is no project with the ID from the project
     When a project is created
     Then the project with the ID is contained in the list

#Scenario: User creates a project with the same name as an existing project
#     Given a user creates another project with name "Alpha "
#     And there is a project with the name "Alpha "
#     Then the project is not created
#     And the user receives the error message "There is already a project with that name"