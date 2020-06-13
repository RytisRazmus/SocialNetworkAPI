Feature: FriendInvite

  Scenario: Get future friend's id
    Given Existing user and friend invite
    When Asks for future friend's id
    Then Received future friend's id

  Scenario: Get friend invite Resource Links
    Given Existing user and friend invite
    When Asks for friend invite's links
    Then Received friend invite's links

  Scenario: Set friend invite Resource Links
    Given Existing user and friend invite
    When Provides friend invite's links
    Then Links for friend invite sent