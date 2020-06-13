Feature: FriendPost

  Scenario: Get friend's post date
    Given Existing friend's post
    When Asks for post's date
    Then Received date

  Scenario: Get friend's profile image
    Given Existing friend's post
    When Asks for profile image
    Then Received profile image

  Scenario: Get friend's name
    Given Existing friend's post
    When Asks for friend's name
    Then Received friend's name

  Scenario: Get friend's surname
    Given Existing friend's post
    When Asks for friend's surname
    Then Received friend's surname

  Scenario: Get friend's post Resource Links
    Given Existing friend's post
    When Asks for friend's posts links
    Then Received friend's posts links

  Scenario: Set friend's post Resource Links
    Given Existing friend's post
    When Provides friend's posts links
    Then Links to friend's posts sent