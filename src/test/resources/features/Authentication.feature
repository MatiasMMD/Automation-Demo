@Authentication
Feature: Authentication.
  As a user of the demoblaze store
  I want to be able to register, log in, and log out
  So that I can manage my account and make purchases.

Background: 
    Given the user is on the Home Page

@SignUp
  Scenario: TC-AUTH-001 - Successful new user registration
    When the user clicks the Sign up link
    And the user enters a unique username and password in the sign up modal
    And the user clicks the Sign up button
    Then the user should see an alert with the text "Sign up successful."

@SignUp
  Scenario: TC-AUTH-002 - Failed registration due to existing user
    When the user clicks the Sign up link
    And the user enters the existing username and existing password in the sign up modal
    And the user clicks the Sign up button
    Then the user should see an alert with the text "This user already exist."

@Login
  Scenario: TC-AUTH-003 - Successful Login
    When the user clicks the Log in link
    And the user enters the username and password and clicks the login button in the login modal
    Then the Welcome text link should be visible in the navbar

@Login
  Scenario: TC-AUTH-004 - Failed login (Wrong password)
    When the user clicks the Log in link
    And the user enters the existing username and a incorrect password and clicks the login button in the login modal
    Then the user should see an alert with the text "Wrong password."

@Login
  Scenario: TC-AUTH-005 - Failed login (User does not exist)
    When the user clicks the Log in link
    And the user enters a random username and a random password and clicks the login button in the login modal
    Then the user should see an alert with the text "User does not exist."

@Logout
  Scenario: TC-AUTH-006 - Successful Logout
    Given the user is logged in
    When the user clicks the Logout link
    Then the Log in link should be visible in the navbar
    And the Sign up link should be visible in the navbar
    And the Welcome text link should not be visible
