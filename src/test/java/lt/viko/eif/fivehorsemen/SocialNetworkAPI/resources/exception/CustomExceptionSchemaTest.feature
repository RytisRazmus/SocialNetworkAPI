# new feature
# Tags: optional

Feature: Custom Exception Test

  Scenario: Gets Error Message
    Given Exception exists
    When Message is requested
    Then Receive error message

  Scenario: Gets Error Code
    Given Exception exists
    When Error code is requested
    Then Receive error code