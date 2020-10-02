Feature:  User Get Spartan Data
  Scenario: Authenticated User Should be able to Get Spartan Data
    Given credentials with USER_role is provided
    When user try to send get request on "/spartans/10"
    Then user should get status code 200