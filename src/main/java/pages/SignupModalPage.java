package pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class SignupModalPage extends BasePage {
    public SignupModalPage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }

    @FindBy(id = "sign-username")
    private WebElement usernameSUForm;

    @FindBy(id = "sign-password")
    private WebElement passwordSUForm;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signupModalButton;

    public void signupNewAcc(String username, String password){
        clickElement(usernameSUForm);
        write(usernameSUForm, username);
        clickElement(passwordSUForm);
        write(passwordSUForm, password);
    }

    public void clickSignup(){
        clickElement(signupModalButton);
    }
}
