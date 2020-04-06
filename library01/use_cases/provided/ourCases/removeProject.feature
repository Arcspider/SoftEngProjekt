Feature: Remove project
  Description: A project is deleted
  Actors: User

  Scenario: The user deletes a project
    Given the user deletes a project "030901"
    And the project "030901" exists
    Then the project is deleted
#    And the project "030901" no longer exists
#
#  Scenario: The user deletes a project that doesn't exist
#    Given the user deletes project "030901"
#    And the project "030901" doesn't exist
#    Then the error message "This project doesn't not exist" is shown