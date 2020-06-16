# new feature
# Tags: optional

Feature: Get the weather by users city

  Scenario: User wants to see the weather
    Given User has specified his living location
    When User wants to see the weather
    Then User gets weather information