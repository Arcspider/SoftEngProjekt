Feature: Assign workers to activity
  Description: A user assigns a worker to an activity, for a certain amount of time.
  Actors: User

 Scenario: Assigning a worker to an activity
    Given that a worker with the name "Jens Jensen" exists
    And that the activity "Optimering" exists
   When the user chooses the worker and the user chooses the activity
 #   And the user allocates "10" hours to week "3"
#    Then the worker is assigned the activity for the amount of hours.

#  Scenario: Assigning a worker to an activity which is already assigned.
#    Given that a worker with the name "Jens Jensen" exists
#    And that the activity "Optimering" exists
#    And that the worker is assigned to the activity
#    When the user chooses the worker
#    And the user chooses the activity
#    And the user allocates 10 hours
#    Then the worker is not assigned the activity
#    And the user receives an error message "This worker is already assigned to the activity"
#
#  Scenario: Assigning a worker who has too much time
#    Given that a worker with the name "Jens Jensen" exists
#    And that the activity "Optimering" exists
#    When the user chooses the worker
#    And the user chooses the activity
#    And the user allocates "55" hours
#    Then the worker doesn't get assigned to the activity
#    And the user receives an error message "This worker has too many hours allocated"