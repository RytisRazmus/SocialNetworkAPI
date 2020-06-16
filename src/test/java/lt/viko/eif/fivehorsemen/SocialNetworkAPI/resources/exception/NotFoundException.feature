# new feature
# Tags: optional

Feature: Custom Exception Test

  Scenario: Sets Error Code
    Given NotFound Exception exists
    Then Set error code

  Scenario: Gets NotFound Error Code
    Given NotFound Exception exists
    Then Get error code

  Scenario: Sets Error Message
    Given NotFound Exception exists
    Then Set error message

  Scenario: Gets NotFound Error Message
    Given NotFound Exception exists
    Then Get error message