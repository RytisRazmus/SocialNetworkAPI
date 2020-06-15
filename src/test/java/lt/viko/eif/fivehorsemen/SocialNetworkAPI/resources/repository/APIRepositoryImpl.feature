Feature: APIRepositoryImpl

  Scenario: Get user
    Given Existing user
    When User enters user email and password
    Then User gets desired user

  Scenario: Search friend
    Given Existing friend
    When user starts a friend search
    Then User gets desired friend

  Scenario: Delete friend invite
    When User wants to delete 1 friend invite
    Then Friend invite is deleted

  Scenario: Accept friend invite
    Given Two existing users
    When User accepts other user invite
    Then They become friends

  Scenario: Get friend posts
    Given Existing posts
    When User wants to get user posts
    Then User gets posts

  Scenario: Add new user
    Given New user
    When Wants to add a new user
    Then New user added

  Scenario: Insert friend invite
    When Wants to insert friend invite
    Then Friend invite is inserted

  Scenario: Get friends
    Given Existing friends
    When User wants to get all friends
    Then User gets all friends

  Scenario: Add a new post
    Given New post
    When User want to add a new post
    Then new post posted

  Scenario: get city
    Given Existing city
    When User want to get the city
    Then The city is received

  Scenario: Identify user
    When User wants to identify user
    Then User is identified