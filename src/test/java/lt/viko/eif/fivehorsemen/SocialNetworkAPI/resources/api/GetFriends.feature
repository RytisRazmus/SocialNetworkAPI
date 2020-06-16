# new feature
# Tags: optional

Feature: Get list of users friends

  Scenario: Get friend list
    Given User is registered
    When User has friends
    Then Show friends