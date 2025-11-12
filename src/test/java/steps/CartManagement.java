package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;

import pages.CartPage;
import pages.MainPage;
import pages.ProductDetailPage;

public class CartManagement {
    private MainPage mainPage = new MainPage();
    private ProductDetailPage productDetailPage = new ProductDetailPage();
    private CartPage cartPage = new CartPage();
    private double expectedCartTotal = 0.0;

    // TC-CART-001
    @When("the user navigates to the {string} product detail page in the {string} category")
    public void navigateToProductPage(String productName, String category){
        mainPage.clickProduct(productName, category);
    }

    @And("the user clicks the Add to cart button")
    public void addToCart(){
        productDetailPage.clickAddToCartButton();
    }

    @Then("the user should see an alert with the text Product added.")
    public void verifyProductAddedAlert() {
        Assert.assertEquals("Product added.", mainPage.alertText());
        mainPage.acceptAlert();
    }

    // TC-CART-002
    @Given("the user has added the {string} from the {string} category to the cart")
    public void productAddedToCart(String productName, String category) {
        mainPage.clickProduct(productName, category);
        this.expectedCartTotal += productDetailPage.getProductPriceAsDouble();
        productDetailPage.clickAddToCartButton();
        mainPage.acceptAlert();
        mainPage.clickHomeLink();
    }

    @Then("the product {string} should be listed in the cart table")
    public void productIsListed(String productName){
        Assert.assertTrue("El producto '" + productName + "' no fue encontrado en la tabla del carrito.", cartPage.productListed(productName));
    }

    @And("the total price should be correct")
    public void totalPriceCheck(){
        double actualTotalPrice = cartPage.getTotalPriceAsDouble();
        Assert.assertEquals("El precio total del carrito no coincide con el precio del producto añadido.", expectedCartTotal, actualTotalPrice, 0.01);
    }

    // TC-CART-003
    @And("the user is on the Cart page")
    public void navigateToCart(){
        mainPage.clickCartLink();
    }

    @When("the user deletes the {string} from the cart")
    public void productDeletedFromCart(String productName){
        cartPage.deleteProduct(productName);
    }

    @Then("the product {string} should not be listed in the cart table")
    public void productNotListed(String productName){
        Assert.assertTrue("El producto '" + productName + "' todavía se encuentra en la tabla del carrito después de ser eliminado.", cartPage.productNotListed(productName));
    }

    @And("the cart total should be updated")
    public void totalUpdated(){
        double expectedTotalPrice = 0.0; 
        double actualTotalPrice = cartPage.getTotalPriceAsDouble();
        Assert.assertEquals("El precio total del carrito no se actualizó correctamente a cero.", expectedTotalPrice, actualTotalPrice, 0.01);
    }

    // TC-CART-004
    @And("also the user has added the {string} from the {string} category to the cart")
    public void alsoProductAddedToCart(String productName, String category) {
        mainPage.clickProduct(productName, category);
        this.expectedCartTotal += productDetailPage.getProductPriceAsDouble();
        productDetailPage.clickAddToCartButton();
        mainPage.acceptAlert();
        mainPage.clickHomeLink();
    }

    @And("the total price should be the sum of all item prices")
    public void checkTotalPrice(){
        double actualTotalPrice = cartPage.getTotalPriceAsDouble();
        Assert.assertEquals("El precio total del carrito no coincide con la suma de los precios de los productos.", expectedCartTotal, actualTotalPrice, 0.01);
    }

    // TC-CART-005
    @Then("the cart table should be empty")
    public void cartTableShouldBeEmpty() {
        Assert.assertFalse("La tabla del carrito no está vacía, pero debería estarlo.", cartPage.cartNotEmpty());
    }

    @And("the Place Order button should be visible ")
    public void placeOrderButtonDisplayed(){
        Assert.assertTrue("El botón 'Place Order' debería ser visible.", cartPage.placeOrderButtonDisplayed());
    }
}
