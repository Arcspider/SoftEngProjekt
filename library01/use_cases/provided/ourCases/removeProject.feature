Feature: Remove project
  Description: A project is deleted
  Actors: User

  Scenario: The user deletes a project
   Given the user deletes a project "030901"
    And the project "030901" exists
    Then the project is deleted
    And the project "030901" no longer exists

 # Scenario: The user deletes a project that doesn't exist
  #	Given that the project "030901" doesn't exist
   # And the user tries to delete the project
   # Then the error message "This project doesn't exist" is shown