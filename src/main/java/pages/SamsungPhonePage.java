package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class SamsungPhonePage extends BasePage{
    public SamsungPhonePage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }

    @FindBy(id = "more-information")
    private WebElement phoneInfo;

    @FindBy(xpath = "//a[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    public boolean cartButtonDisplayed(){
        return elementIsDisplayed(addToCartButton);
    }

    public boolean phoneInfo(){
        return elementIsDisplayed(phoneInfo);
    }
}
