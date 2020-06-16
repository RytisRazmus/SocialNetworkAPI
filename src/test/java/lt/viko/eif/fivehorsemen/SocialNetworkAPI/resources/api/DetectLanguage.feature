# new feature
# Tags: optional

Feature: Detect language users are using

  Scenario: Detect language
    When Client enters some text
    Then The language is returned based on the text