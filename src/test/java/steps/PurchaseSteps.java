package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;

import pages.MainPage;
import pages.PlaceOrderModalPage;
import java.util.Map;
import pages.CartPage;
import pages.ProductDetailPage;
import utils.ConfigReader;

public class PurchaseSteps {
    private MainPage mainPage = new MainPage();
    private ProductDetailPage productDetailPage = new ProductDetailPage();
    private CartPage cartPage = new CartPage();
    private PlaceOrderModalPage placeOrderModalPage = new PlaceOrderModalPage();
    private ConfigReader configReader = ConfigReader.getInstance();

    // Background
    @Given("the user added the {string} from the {string} category")
    public void addProduct(String productName, String category){
        mainPage.clickProduct(productName, category);
        productDetailPage.clickAddToCartButton();
        mainPage.acceptAlert();
        mainPage.clickHomeLink();
    }

    @Given("the user clicks the Place Order button")
    public void clickPlaceOrderButton(){
        Assert.assertTrue("El carrito no debería estar vacío antes de intentar realizar un pedido.", cartPage.cartNotEmpty());
        cartPage.clickPlaceOrderButton();
    }

    @Then("the Place Order modal should be displayed")
    public void placeOrderModalDisplayed(){
        Assert.assertTrue("El modal 'Place order' debería estar visible", placeOrderModalPage.purchaseModalDisplayed());
    }

    // TC-PURCH-001
    @When("the user fills the Place Order form with valid data")
    public void fillForm(){
        placeOrderModalPage.fillFullForm(configReader.getName(), configReader.getCountry(), configReader.getCity(), configReader.getCreditCard(), configReader.getMonth(), configReader.getYear());
    }

    @And("the user clicks the Purchase button")
    public void clickPurchase(){
        placeOrderModalPage.clickPurchase();
    }

    @Then("the confirmation modal should be displayed")
    public void confirmationModalDisplayed(){
        Assert.assertTrue("El modal 'Thank you for your purchase!' debería estar visible.", placeOrderModalPage.purchaseConfirmationDisplayed());
    }

    @And("the purchase details should be correct")
    public void purchaseDetailsCorrect(){

        Assert.assertTrue("El modal de confirmación de compra debería estar visible.", placeOrderModalPage.purchaseConfirmationDisplayed());
    }

    // TC-PURCH-002
    @Given("the user completes a successful purchase")
    public void successfulPurchase(){
        placeOrderModalPage.fillFullForm(configReader.getName(), configReader.getCountry(), configReader.getCity(), configReader.getCreditCard(), configReader.getMonth(), configReader.getYear());
        placeOrderModalPage.clickPurchase();
    }

    @When("the user clicks OK on the confirmation modal")
    public void confirmPurchase(){
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        placeOrderModalPage.clickOkButton();
    }

    @And("the cart should be empty when navigating to the Cart page")
    public void cartEmpty(){
        mainPage.clickCartLink();
        Assert.assertFalse("La tabla del carrito no está vacía, pero debería estarlo.", cartPage.cartNotEmpty());
    }

    // TC-PURCH-003
    @And("the Place Order modal should remain open")
    public void placeOrderModalRemains(){
        placeOrderModalPage.purchaseModalDisplayed();
    }

    // TC-PURCH-004
    @When("the user fills the Place Order form but omits the {string}")
    public void fillIncompleteForm(String fieldToOmit) {
        String name = configReader.getName();
        String country = configReader.getCountry();
        String city = configReader.getCity();
        String creditCard = configReader.getCreditCard();
        String month = configReader.getMonth();
        String year = configReader.getYear();

        switch (fieldToOmit.toLowerCase()) {
            case "name":
                name = "";
                break;
            case "credit card":
                creditCard = "";
                break;
        }
        placeOrderModalPage.fillFullForm(name, country, city, creditCard, month, year);
    }

    // TC-PURCH-005
    @When("the user clicks the Close button on the Place Order modal")
    public void clickCloseButton(){
        placeOrderModalPage.clickCloseButton();
    }

    @Then("the Place Order modal should be closed")
    public void placeOrderModalClosed(){
        Assert.assertTrue("El modal 'Place order' no debería ser visible", placeOrderModalPage.purchaseModalClosed());
    }

    @And("the user should remain on the Cart page")
    public void cartPageRedirect(){
        Assert.assertTrue("El usuario esta en la 'Cart Page' y fue redireccionado correctamente", cartPage.placeOrderButtonDisplayed());
    }

    @And("the items should still be in the cart")
    public void itemsStillInCart() {
        Assert.assertTrue("Los items deberían permanecer en el carrito.", cartPage.cartNotEmpty());
    }
}
