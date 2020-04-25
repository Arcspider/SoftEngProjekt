Feature: Assign workers to activity
  Description: A user assigns a worker to an activity, for a certain amount of time.
  Actors: User

#  Scenario: Assigning a worker to an activity
#    When the user chooses the worker "HAH"
#    And the user chooses the activity "Optimering"
#    And the user allocates "55" hours
#    Then the worker "HAH" is assigned the activity "Optimering" for "55" hours.

#  Scenario: Assigning a worker to an activity which is already assigned.
#    When the user chooses the worker "HAH"
#    And the user chooses the activity "Optimering"
#    Given "HAH" is already assigned "Optimering"
#    Then an error message is shown
#
#  Scenario: Assigning a worker who doesn't have enough time
#    When the user chooses the worker "HAH"
#    And the user chooses the activity "Optimering"
#    And the user allocates "55" hours
#    Given the worker doesn't have "55" remaining hours
#    Then an error message is shown