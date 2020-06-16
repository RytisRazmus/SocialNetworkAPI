# new feature
# Tags: optional

Feature: Verify if the entered email is valid

  Scenario: Verify email
    Given Client specifies an email
    When Client sends a request
    Then Verification result is returned