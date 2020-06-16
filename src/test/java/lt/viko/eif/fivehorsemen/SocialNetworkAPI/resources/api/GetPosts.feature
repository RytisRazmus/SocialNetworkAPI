# new feature
# Tags: optional

Feature: Get posts made my friends.

  Scenario: Get posts by friends
    Given User has friend who posted
    When User opens posts feed
    Then User sees posts made by friends