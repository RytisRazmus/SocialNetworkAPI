# new feature
# Tags: optional

Feature: Accept friend invite

  Scenario: Accept friend Invite
    Given User gets a friend invite
    When User accepts the friend invite
    Then Friend is added