Feature: End to end test for Passenger API

  Scenario: user should be able to add passenger
    Given A list of passengers are available
    When I add a passenger to list
    Then The passenger is added

  Scenario:  user should be able to remove passenger
    Given A list of passengers are available
    When I remove a passenger from the list
    Then The passenger is removed