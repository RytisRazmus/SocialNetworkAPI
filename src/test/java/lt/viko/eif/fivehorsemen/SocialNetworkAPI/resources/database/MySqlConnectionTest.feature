Feature: MySqlConnectionTest

  Scenario: Get user from database
    Given User exists
    When User enters credentials
    Then User receives user