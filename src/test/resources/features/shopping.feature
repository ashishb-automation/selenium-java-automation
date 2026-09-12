@ui @shopping
Feature: Customer shopping workflow
  As a customer
  I want to manage my cart and session
  So that I can shop confidently

  Background:
    Given the customer is on the login page
    When the customer signs in with valid credentials

  Scenario: Customer adds and removes an item
    When the customer adds "Sauce Labs Backpack" to the cart
    Then the cart badge should display 1
    When the customer removes "Sauce Labs Backpack" from the cart
    Then the cart badge should display 0

  Scenario: Customer logs out successfully
    When the customer logs out
    Then the login page should be displayed
