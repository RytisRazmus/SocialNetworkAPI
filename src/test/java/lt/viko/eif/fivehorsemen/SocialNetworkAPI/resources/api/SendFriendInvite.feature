Feature: Send friend invite to another User

  Scenario: Send friend invite
    Given User is registered in the system
    When User enters friend id
    Then Friend invite is sent

  Scenario: Get friend list
    Given User has an id
    When User has friends
    Then Show friends

  Scenario: Add post
    Given User has an id
    When User enters necessary post data
    Then User adds a post