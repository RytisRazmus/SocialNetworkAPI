Feature: Friend

  Scenario: Get friend's id
    Given Existing friend
    When Asks for friend's id
    Then Received friend's id

  Scenario: Get friend's name
    Given Existing friend
    When Asks for friend name
    Then Received friend name

  Scenario: Get friend's surname
    Given Existing friend
    When Asks for friend surname
    Then Received friend surname

  Scenario: Get friend's image
    Given Existing friend
    When Asks for friend's image
    Then Received friend's image

  Scenario: Get friend Resource Links
    Given Existing friend
    When Asks for friends links
    Then Received links of friends

  Scenario: Set friend Resource Links
    Given Existing friend
    When Provides friends links
    Then Links of friends sent