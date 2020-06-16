Feature: MySqlConnectionF

  Scenario: Get User From Database
    When User enters credentials
    Then User receives requested user

  Scenario: Delete Friend Invitation
    When User enters invite id
    Then User receives deletion status

  Scenario: Find Friends By Name
    When User enters a name
    Then User receives friends

  Scenario: Accept Friends Invitation
    When User enters friends id "3"
    Then User receives invitation status

  Scenario: Gets Friends Posts
    When User enters friends id "2"
    Then User receives friends posts

  Scenario: Gets Friend Invites
    When User enters friends id "3"
    Then User receives friend invites

  Scenario: Adds New User
    When User enters required credentials
    Then User receives addition status

  Scenario: User Invites Friend
    When User enters friends id "1"
    Then User receives invitation status

  Scenario: User Gets His Friends
    When User enters his id "2"
    And User sends request for friends
    Then User receives friends

  Scenario: User Adds A Post
    When User enters post information
    Then User receives post status

  Scenario: User Gets Name Of His City
    When User enters his id "2"
    Then User receives city name

  Scenario: User Gets Name Of Friend
    When User enters friends id "2"
    Then User receives friend name