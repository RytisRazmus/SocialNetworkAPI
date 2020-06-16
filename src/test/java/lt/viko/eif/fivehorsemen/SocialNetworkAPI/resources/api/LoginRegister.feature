Feature: Login and register

  Scenario: Create new User
    Given User enters his information
    When User presses register
    Then New User created

  Scenario: User logs in
    Given User is already registered in the system
    When User enters email and password
    Then User logs in