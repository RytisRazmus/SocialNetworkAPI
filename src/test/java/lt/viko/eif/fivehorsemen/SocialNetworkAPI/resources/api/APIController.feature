Feature: APIControllerF

  Scenario: Create new User
    Given User enters his information
    When User presses register
    Then New User created

  Scenario: User logs in
    Given User enters email and password
    When User tries to login
    Then User logs in

  Scenario: Get friend invites
    Given User has an id
    When User looks for friend invites
    Then User sees friend invites

  Scenario: Send friend invite
    Given User is logged in
    When User enters friend id
    Then Friend invite is sent

  Scenario: Get friend list
    Given User id
    When User has friends
    Then Show friends

  Scenario: Add post
    Given User selects add post
    When User enters necessary post data
    Then User adds a post

  Scenario: Search for friend
    Given User selects search
    When User enters friends name
    Then User sees a list of friends

  Scenario: Delete friend invite
    Given User has a friend invite
    When User wants to delete it
    Then Friend invite deleted

  Scenario: Accept friend Invite
    Given User gets a friend invite
    When User accepts the friend invite
    Then Friend is added

  Scenario: Get posts by friends
    Given User has friend who posted
    When User opens posts feed
    Then User sees posts made by friends

  Scenario: User wants to see the weather
    Given User has specified his living location
    When User wants to see the weather
    Then User gets weather information

  Scenario: Detect language
    When Client enters some text
    Then The language is returned based on the text

  Scenario: User want to see a love match percent
    Given User and his friend have an id
    When User presses calculate
    Then Love percent is returned

  Scenario: Verify email
    Given Client specifies an email
    When Client sends a request
    Then Verification result is returned