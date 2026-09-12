@ui @smoke
Feature: Customer authentication
  As a registered customer
  I want clear and secure sign-in behaviour
  So that I can access the product inventory

  Background:
    Given the customer is on the login page

  Scenario: Standard user signs in successfully
    When the customer signs in with username "standard_user" and password "secret_sauce"
    Then the product inventory should be displayed

  Scenario: Invalid credentials are rejected
    When the customer signs in with username "invalid_user" and password "wrong_password"
    Then an authentication error should be displayed

