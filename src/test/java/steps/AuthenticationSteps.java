package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;

import utils.ConfigReader;
import pages.MainPage;
import pages.SignupModalPage;
import pages.LoginModalPage;

public class AuthenticationSteps {
    private MainPage mainPage = new MainPage();
    private SignupModalPage signupModalPage = new SignupModalPage();
    private LoginModalPage loginModalPage = new LoginModalPage();
    private ConfigReader configReader = ConfigReader.getInstance();

    // Background
    @Given("the user is on the Home Page")
    public void navigateToDemoBlaze(){
        mainPage.navigateToDemoBlaze();
    }

    // TC-AUTH-001 y TC-AUTH-002
    @When("the user clicks the Sign up link")
    public void clickSignupLink() {
        mainPage.clickSignupLink();
    }

        @And("the user enters a unique username and password in the sign up modal")
    public void createNewAccount() {
        signupModalPage.signupNewAcc(configReader.getUsername(), configReader.getPassword());
    }

    @And("the user clicks the Sign up button")
    public void clickSignupButton() {
        signupModalPage.clickSignup();
    }

    @And("the user enters the existing username and existing password in the sign up modal")
    public void createExistingAccount(){
        signupModalPage.signupNewAcc(configReader.getUsername(), configReader.getPassword());
    }

    // TC-AUTH-003 , TC-AUTH-004 y TC-AUTH-005
    @When("the user clicks the Log in link")
    public void clickLogin(){
        mainPage.clickLoginLink();
    }


    @And("the user enters the username and password and clicks the login button in the login modal")
    public void loginAccount(){
        loginModalPage.loginWithCredentials(configReader.getUsername(), configReader.getPassword());
    }

    @Then("the Welcome text link should be visible in the navbar")
    public void welcomeText(){
        String expectedText = "Welcome " + configReader.getUsername();
        String actualText = mainPage.loginText();
        Assert.assertEquals(expectedText, actualText);
    }

    @And("the user enters the existing username and a incorrect password and clicks the login button in the login modal")
    public void wrongPassword(){
        loginModalPage.loginWithCredentials(configReader.getUsername(), "incorrectPassword");
    }

    @And("the user enters a random username and a random password and clicks the login button in the login modal")
    public void userDoesntExist(){
        loginModalPage.loginWithCredentials("JavaPremium21", "SeleniumtheBest123");
    }

    // TC-AUTH-006
    @Given("the user is logged in")
    public void userLoggedin(){
        mainPage.clickLoginLink();
        loginModalPage.loginWithCredentials(configReader.getUsername(), configReader.getPassword());
    }

    @When("the user clicks the Logout link")
    public void logoutUser(){
        mainPage.clickLogoutLink();
    }

    @Then("the Log in link should be visible in the navbar")
    public void loginDisplayed(){
        mainPage.loginLinkDisplayed();
    }

    @And("the Sign up link should be visible in the navbar")
    public void signupDisplayed(){
        mainPage.signupLinkDisplayed();
    }

    @And("the Welcome text link should not be visible")
    public void welcomeTextNotDisplayed(){
        mainPage.welcomeTextNotDisplayed();
    }

    // Verificación de alertas de popups Webs
    @Then("the user should see an alert with the text {string}")
    public void verifyAlertText(String expectedText) {
        Assert.assertEquals(expectedText, mainPage.alertText());
        mainPage.acceptAlert();
    }
}
