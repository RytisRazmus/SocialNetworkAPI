Feature: Post

  Scenario: Get post's user Id
    Given Existing post
    When Asks for user's id
    Then Received id

  Scenario: Get post's image
    Given Existing post
    When Asks for image
    Then Received image

  Scenario: Get post's description
    Given Existing post
    When Asks for description
    Then Received description