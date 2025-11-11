package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;

import pages.MainPage;
import pages.SamsungPhonePage;
import pages.AboutUsModalPage;
import pages.CartPage;
import pages.ContactModalPage;

public class HomePageSteps {
    private MainPage mainPage = new MainPage();
    private ContactModalPage contactModalPage = new ContactModalPage();
    private AboutUsModalPage aboutUsModalPage = new AboutUsModalPage();
    private SamsungPhonePage samsungPhonePage = new SamsungPhonePage();

    // TC-NAV-001
    @Given("the user navigates to the Cart page")
    public void navigateToCartPage(){
        mainPage.clickCartLink();
    }

    @When("the user clicks the Home link in the navbar")
    public void navigateToHomePage(){
        mainPage.clickHomeLink();
    }

    @Then("the user should be redirected to the Home Page")
    public void homePageRedirect(){
        mainPage.productGridDisplayed();
    }

    // TC-NAV-002
    @When("the user clicks the Contact link in the navbar")
    public void navigateToContactModal(){
        mainPage.clickContactLink();
    }

    @Then("the New message modal should be displayed")
    public void contactModalDisplayed(){
        contactModalPage.messageModalDisplayed();
    }

    // TC-NAV-003
    @When("the user clicks the About us link in the navbar")
    public void navigateToAboutUsModal(){
        mainPage.clickAboutUsLink();
    }

    @Then("the About us modal should be displayed")
    public void aboutUsModalDisplayed(){
        aboutUsModalPage.aboutUsModalDisplayed();
    }

    @And("the video player inside the modal should be visible and playable")
    public void aboutUsVideo(){
        aboutUsModalPage.checkVideoAboutUs();
    }

    // TC-CAT-001
    @When("the user clicks the {string} category link")
    public void clickCatLink(String category){
        mainPage.clickCategory(category);
    }

    @Then("the product grid should be updated")
    public void productGridUpdated() {
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        Assert.assertTrue("La grilla de productos debería estar visible.", mainPage.productGridDisplayed());
    }

    @And("a product from that category like {string} should be visible")
    public void productCatVisible(String productName) {
        Assert.assertTrue("El producto '" + productName + "' debería ser visible.", mainPage.productCatGrid(productName));
    }

    // TC-PROD-001
    @When("the user clicks on the Samsung galaxy s6 product link")
    public void clickSamsungPhone(){
        mainPage.clickSamsungPhone();
    }

    @Then("the user should be redirected to the product detail page")
    public void samsungPhonePage(){
        Assert.assertTrue("El botón 'Add to cart' debería ser visible en la página de detalles del producto.", samsungPhonePage.cartButtonDisplayed());
    }

    @And("the product details for Samsung galaxy s6 should be displayed correctly")
    public void samsungInfoDisplayed(){
        samsungPhonePage.phoneInfo();
    } 

    @And("the Add to cart button should be visible")
    public void addCartDisplayed(){
        samsungPhonePage.cartButtonDisplayed();
    }
}
