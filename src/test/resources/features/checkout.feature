@ui @checkout
Feature: Customer checkout journey
  As a customer
  I want to review and complete my purchase
  So that I can confirm my order securely

  Background:
    Given the customer is on the login page
    When the customer signs in with valid credentials

  Scenario: Customer completes checkout with multiple products
    When the customer adds multiple products to the cart
    And the customer proceeds to checkout
    And the customer enters checkout details with first name "Ashish", last name "Reddy", and zip code "12345"
    And the customer continues checkout
    Then the checkout overview should be displayed
    When the customer finishes checkout
    Then the order confirmation should be displayed

  Scenario: Customer cancels checkout from the summary
    When the customer adds "Sauce Labs Backpack" to the cart
    And the customer proceeds to checkout
    And the customer enters checkout details with first name "Ashish", last name "Reddy", and zip code "12345"
    And the customer continues checkout
    Then the checkout overview should be displayed
    When the customer cancels checkout
    Then the inventory page should be displayed
