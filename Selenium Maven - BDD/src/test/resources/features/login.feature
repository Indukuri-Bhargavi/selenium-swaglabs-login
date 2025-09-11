Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given User is on the login page
    When User enters username "standard_user" and password "secret_sauce"
    And clicks on the login button
    Then User should be redirected to the home page
