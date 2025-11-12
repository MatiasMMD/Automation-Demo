@Cart
Feature: Cart Management
  As a logged in user on the demoblaze store
  I want to add products to my cart and remove them
  So that I can manage my items before purchasing.

Background:
    Given the user is on the Home Page
    Given the user is logged in

@ViewCart
  Scenario: TC-CART-001 - View an empty cart
    When the user navigates to the Cart page
    Then the cart table should be empty
    And the Place Order button should be visible 

@AddToCart
  Scenario: TC-CART-002 - Add a single product to the cart
    When the user navigates to the "Samsung galaxy s6" product detail page in the "Phones" category
    And the user clicks the Add to cart button
    Then the user should see an alert with the text Product added.

@ViewCart
  Scenario: TC-CART-003 - Verify product is in the cart after adding
    Given the user has added the "Samsung galaxy s6" from the "Phones" category to the cart
    When the user navigates to the Cart page
    Then the product "Samsung galaxy s6" should be listed in the cart table
    And the total price should be correct

@RemoveFromCart
  Scenario: TC-CART-004 - Remove a product from the cart
    Given the user has added the "Samsung galaxy s6" from the "Phones" category to the cart
    And the user is on the Cart page
    When the user deletes the "Samsung galaxy s6" from the cart
    Then the product "Samsung galaxy s6" should not be listed in the cart table
    And the cart total should be updated

@AddToCart
  Scenario: TC-CART-005 - Add multiple different products to the cart
    Given the user has added the "Samsung galaxy s6" from the "Phones" category to the cart
    And also the user has added the "MacBook air" from the "Laptops" category to the cart
    When the user navigates to the Cart page
    Then the product "Samsung galaxy s6" should be listed in the cart table
    And the product "MacBook air" should be listed in the cart table
    And the total price should be the sum of all item prices