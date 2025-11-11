@Cart
Feature: Cart Management
  As a logged in user on the demoblaze store
  I want to add products to my cart and remove them
  So that I can manage my items before purchasing.

Background:
    Given the user is on the Home Page
    Given the user is logged in

@AddToCart
  Scenario: TC-CART-001 - Add a single product to the cart
    When the user navigates to the "Samsung galaxy s6" product detail page in the "Phones" category
    And the user clicks the Add to cart button
    Then the user should see an alert with the text Product added.