# new feature
# Tags: optional

Feature: Allows users to reject friend invites

  Scenario: Delete friend invite
    Given User has a friend invite
    When User wants to delete it
    Then Friend invite deleted