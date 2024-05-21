Feature: Usuario

  Scenario: Register a new user
    Given user is new
    When I post user
    Then user was registered


  Scenario: Search user already registered
    Given user already registered
    When I search for username
    Then user with username was found
