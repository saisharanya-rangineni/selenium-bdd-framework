@cart @regression
Feature: Shopping Cart Functionality
  As a logged-in user
  I want to manage items in my shopping cart
  So that I can purchase products I need

  Background:
    Given the user is logged in as "standard_user"

  @smoke
  Scenario: Add a single item to the cart
    When the user adds "Sauce Labs Backpack" to the cart
    Then the cart badge should show "1"
    And the cart should contain "Sauce Labs Backpack"

  Scenario: Add multiple items to the cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user adds "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart badge should show "3"

  Scenario: Remove an item from the cart
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user removes "Sauce Labs Backpack" from the cart
    Then the cart badge should show "1"
    And the cart should not contain "Sauce Labs Backpack"

  Scenario: Continue shopping from cart page
    When the user adds "Sauce Labs Backpack" to the cart
    And the user navigates to the cart
    And the user clicks continue shopping
    Then the user should be on the inventory page

  @smoke
  Scenario: Complete checkout process
    When the user adds "Sauce Labs Backpack" to the cart
    And the user navigates to the cart
    And the user proceeds to checkout
    And the user enters checkout information:
      | firstName | lastName | postalCode |
      | Sharanya  | R        | 500032     |
    And the user completes the order
    Then the order confirmation message should be displayed
    And the confirmation should contain "Thank you for your order"
