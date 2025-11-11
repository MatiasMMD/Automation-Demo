package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class MainPage extends BasePage {
    public MainPage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }

    @FindBy(id = "nava") 
    private WebElement homeButton;

    @FindBy(xpath = "//a[contains(text(),'Home ')]")
    private WebElement homeLink;

    @FindBy(xpath = "//a[contains(text(),'Contact')]")
    private WebElement contactLink;

    @FindBy(xpath = "//a[contains(text(),'About us')]")
    private WebElement aboutUsLink;

    @FindBy(id = "cartur")
    private WebElement cartLink;

    @FindBy(id = "login2")
    private WebElement loginLink;

    @FindBy(id = "signin2")
    private WebElement singinLink;

    @FindBy(id = "nameofuser")
    private WebElement welcomeButton;

    @FindBy(id = "logout2")
    private WebElement logoutlink;

    @FindBy(id = "tbodyid")
    private WebElement productGrid;

    @FindBy(xpath = "//a[contains(text(),'Phones')]")
    private WebElement phoneCat;

    @FindBy(xpath = "//a[contains(text(), 'Samsung galaxy s6')]")
    private WebElement samsungPhone;

    public void navigateToDemoBlaze() {
        navigateTo("https://www.demoblaze.com/index.html");
    }

    public void clickSignupLink(){
        clickElement(singinLink);
    }

    public void clickLoginLink (){
        clickElement(loginLink);
    }

    public void clickLogoutLink(){
        clickElement(logoutlink);
    }

    public void clickCartLink(){
        clickElement(cartLink);
    }

    public void clickHomeLink(){
        clickElement(homeLink);
    }

    public void clickContactLink(){
        clickElement(contactLink);
    }

    public void clickAboutUsLink(){
        clickElement(aboutUsLink);
    }

    public void clickCategory(String category){
        String categoryLocator = String.format("//a[contains(text(), '%s')]", category);
        clickElement(categoryLocator);
    }

    public void clickSamsungPhone(){
        clickElement(samsungPhone);
    }

    public String loginText (){
        return textFromElement(welcomeButton);
    }

    public boolean loginLinkDisplayed(){
        return elementIsDisplayed(loginLink);
    }

    public boolean signupLinkDisplayed(){
        return elementIsDisplayed(singinLink);
    }

    public boolean welcomeTextNotDisplayed(){
        return elementIsDisplayed(welcomeButton);
    }

    public boolean productGridDisplayed(){
        return elementIsDisplayed(productGrid);
    }

    public boolean productCatGrid(String productName){
        return elementIsDisplayed(String.format("//a[text()='%s']", productName));
    }
}
