# new feature
# Tags: optional

Feature: Allows users to search for friends by typing text

  Scenario: Search for friend
    Given User selects search
    When User enters friends name
    Then User sees a list of friends