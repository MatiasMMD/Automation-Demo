@Purchase
Feature: Product Purchase Flow
  As a customer
  I want to place an order for the items in my cart
  So that I can complete my purchase.

Background:
    Given the user is logged in
    Given the user added the "Samsung galaxy s6" from the "Phones" category
    And the user is on the Cart page

@PurchaseHappyPath
  Scenario: TC-PURCH-001 - Successful purchase with valid data
    Given the user clicks the Place Order button
    When the user fills the Place Order form with valid data
    And the user clicks the Purchase button
    Then the confirmation modal should be displayed
    And the purchase details should be correct

@PurchaseHappyPathTwo
  Scenario: TC-PURCH-002 - Verify cart is empty after successful purchase
    Given the user clicks the Place Order button
    Given the user completes a successful purchase
    When the user clicks OK on the confirmation modal
    Then the user should be redirected to the Home Page
    And the cart should be empty when navigating to the Cart page

@PurchaseValidation
  Scenario: TC-PURCH-003 - Attempt to purchase with an empty form
    Given the user clicks the Place Order button
    When the user clicks the Purchase button
    Then the user should see an alert with the text "Please fill out Name and Creditcard."
    And the Place Order modal should remain open

@PurchaseValidationTwo
  Scenario Outline: TC-PURCH-004 - Attempt to purchase with missing mandatory fields
    Given the user clicks the Place Order button
    When the user fills the Place Order form but omits the "<FieldName>"
    And the user clicks the Purchase button
    Then the user should see an alert with the text "<ValidationMessage>"
    And the Place Order modal should remain open

    Examples:
      | FieldName   | ValidationMessage                       |
      | Name        | Please fill out Name and Creditcard.    |
      | Credit card | Please fill out Name and Creditcard.    |

@PurchaseFlow
  Scenario: TC-PURCH-005 - Close the "Place Order" modal
    Given the user clicks the Place Order button
    Then the Place Order modal should be displayed
    When the user clicks the Close button on the Place Order modal
    Then the Place Order modal should be closed
    And the user should remain on the Cart page
    And the items should still be in the cart