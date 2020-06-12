Feature: User

  Scenario: Get user's password
    Given Existing user
    When Asks for password
    Then Received password

  Scenario: Get user's last seen date
    Given Existing user
    When Asks last seen date
    Then Last seen date received

  Scenario: Get user's birthday
    Given Existing user
    When Asks for birthday
    Then Birthday date received

  Scenario: Get user's Id
    Given Existing user
    When Asks for password
    Then Received password

  Scenario: Get user's Email
    Given Existing user
    When Asks for email
    Then Received email

  Scenario: Get user's Name
    Given Existing user
    When Asks for name
    Then Received name

  Scenario: Get user's Surname
    Given Existing user
    When Asks for surname
    Then Received surname

  Scenario: Get user's Phone nummber
    Given Existing user
    When Asks for Phone number
    Then Received Phone number

  Scenario: Get user's Resource Links
    Given Existing user
    When Asks for links
    Then Received links

  Scenario: Set user's Id
    Given Existing user
    When Provides for links
    Then Links sent