@ui @shopping
Feature: Customer shopping workflow
  As a customer
  I want to manage my cart and session
  So that I can shop confidently in the application

  Background:
    Given the customer is on the login page
    When the customer signs in with username "standard_user" and password "secret_sauce"

  Scenario: Customer can add and remove an item from the cart
    When the customer adds "Sauce Labs Backpack" to the cart
    Then the cart badge should display 1
    When the customer removes "Sauce Labs Backpack" from the cart
    Then the cart badge should display 0

  Scenario: Customer can log out successfully
    When the customer logs out
    Then the login page should be displayed
