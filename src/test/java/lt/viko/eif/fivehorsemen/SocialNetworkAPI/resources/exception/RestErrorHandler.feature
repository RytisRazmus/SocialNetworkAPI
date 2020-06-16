# new feature
# Tags: optional

Feature: RestErrorHandler

  Scenario: Handling All Exceptions
    Given NotFoundException exists
    And RestErrorHandler exists
    When ResponseEntity has been created
    Then Get ResponseEntity status code