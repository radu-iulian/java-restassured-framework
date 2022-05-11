@regression @GoRest
Feature: GoRest users endpoints tests
  For documentation about the API please check: https://gorest.co.in

  Scenario: GoRest user registration flow
    Given I'm new on GoRest API
    When I create a new GoRest user
      | name | gender | email   | status |
      | Radu | male   | @random | active |
    Then the received status code is 201
    And the user registration response JSON schema matches the one expected
    And the user response is successfully validated
    And the get user endpoint response reflects the changes

  Scenario: GoRest user update flow
    Given I'm an already GoRest registered user
      | name   | gender | email   | status |
      | Radu   | male   | @random | active |
    When I update the GoRest user with the following details
      | name   | gender | email   | status   |
      | Iulian | female | @random | inactive |
    Then the received status code is 200
    And the update user response JSON schema matches the one expected
    And the user response is successfully validated
    And the get user endpoint response reflects the changes

  Scenario: GoRest user patch flow
    Given I'm an already GoRest registered user
      | name   | gender | email   | status |
      | Radu   | male   | @random | active |
    When I patch the GoRest user with the following details
      | name   | email   |
      | Iulian | @random |
    Then the received status code is 200
    And the patch user response JSON schema matches the one expected
    And the user response is successfully validated
    And the get user endpoint response reflects the changes

  Scenario: GoRest delete user flow
    Given I'm an already GoRest registered user
      | name | gender | email   | status |
      | Radu | male   | @random | active |
    When I delete my GoRest user
    Then the received status code is 204
    And the get user endpoint returns 404 after the user deletion

  ########################### Negative tests ###########################

  Scenario: [N] GoRest user registration flow with invalid data
    Given I'm new on GoRest API
    When I try to create a new GoRest user with invalid data
      | name | gender            | email   | status |
      | Radu | notMaleOrFemale   | @random | active |
    And the error response JSON schema matches the one expected
    Then I receive a GO_REST_BLANK_USER_GENDER error