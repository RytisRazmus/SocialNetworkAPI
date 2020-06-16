Feature: Get users friend invites

  Scenario: Get friend invites
    Given User is registered in the system
    When User looks for friend invites
    Then User sees friend invites