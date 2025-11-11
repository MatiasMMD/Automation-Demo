package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class CartPage extends BasePage {
    public CartPage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }
}
