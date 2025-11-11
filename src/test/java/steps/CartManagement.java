package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;

import pages.LoginModalPage;
import pages.MainPage;
import pages.SamsungPhonePage;
import utils.ConfigReader;

public class CartManagement {
    private MainPage mainPage = new MainPage();
    private SamsungPhonePage samsungPhonePage = new SamsungPhonePage();

    // TC-CART-001
    @When("the user navigates to the {string} product detail page in the {string} category")
    public void navigateToProductPage(String productName, String category){
        mainPage.clickProduct(productName, category);
    }

    @And("the user clicks the Add to cart button")
    public void addToCart(){
        samsungPhonePage.clickAddToCart();
    }

    @Then("the user should see an alert with the text Product added.")
    public void verifyProductAddedAlert() {
        Assert.assertEquals("Product added.", mainPage.alertText());
        mainPage.acceptAlert();
    }

}
