# new feature
# Tags: optional

Feature: Get love match percent from two users

  Scenario: User want to see a love match percent
    Given User and his friend have an id
    When User presses calculate
    Then Love percent is returned