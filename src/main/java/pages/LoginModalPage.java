package pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class LoginModalPage extends BasePage{
    public LoginModalPage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }

    @FindBy(id = "loginusername")
    private WebElement usernameLIForm;

    @FindBy(id = "loginpassword")
    private WebElement passwordLIForm;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginModalButton;

    @FindBy(id = "logInModal")
    private WebElement loginModal;

    public void enterUsername (String username){
        clickElement(usernameLIForm);
        write(usernameLIForm, username);
    }

    public void enterPassword (String password){
        clickElement(passwordLIForm);
        write(passwordLIForm, password);
    }

    public void clickLoginPopup (){
        clickElement(loginModalButton);
    }

    public void loginWithCredentials(String username, String password) {
        write(usernameLIForm, username);
        write(passwordLIForm, password);
        clickElement(loginModalButton);
        wait.until(ExpectedConditions.invisibilityOf(loginModal));
    }
}
