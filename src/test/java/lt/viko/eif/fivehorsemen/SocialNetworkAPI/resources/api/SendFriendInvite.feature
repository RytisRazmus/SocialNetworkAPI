Feature: Send friend invite to another User

  Scenario: Send friend invite
    Given User is registered in the system *
    When User enters friend id
    Then Friend invite is sent

