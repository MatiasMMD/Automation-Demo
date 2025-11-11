@Browsing
Feature: Product Browsing and Navigation
  As a shopper on the demoblaze store
  I want to navigate the site, view products, and filter by category
  So that I can find the items I want to buy.

Background:
    Given the user is on the Home Page

@Navigation
    Scenario: TC-NAV-001 - Navigate to Home from another page
    Given the user navigates to the Cart page
    When the user clicks the Home link in the navbar
    Then the user should be redirected to the Home Page

@Navigation
  Scenario: TC-NAV-002 - View "Contact" modal
    When the user clicks the Contact link in the navbar
    Then the New message modal should be displayed

@Navigation
  Scenario: TC-NAV-003 - View "About us" modal
    When the user clicks the About us link in the navbar
    Then the About us modal should be displayed
    And the video player inside the modal should be visible and playable

@Categories
  Scenario Outline: TC-CAT-001 - Filter products by category
    When the user clicks the "<CategoryName>" category link
    Then the product grid should be updated
    And a product from that category like "<ExampleProduct>" should be visible
    Examples:
      | CategoryName | ExampleProduct     |
      | Phones       | Samsung galaxy s6  |
      | Laptops      | Sony vaio i5       |
      | Monitors     | Apple monitor 24   |

@Products
  Scenario: TC-PROD-001 - View product details
    When the user clicks on the "Samsung galaxy s6" product link in the "Phones" category
    Then the user should be redirected to the product detail page
    And the product details for Samsung galaxy s6 should be displayed correctly
    And the Add to cart button should be visible