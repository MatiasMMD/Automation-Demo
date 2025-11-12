package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class ProductDetailPage extends BasePage{
    public ProductDetailPage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }

    @FindBy(className = "name")
    private WebElement productNameTitle;

    @FindBy(className = "price-container")
    private WebElement productPrice;

    @FindBy(id = "more-information")
    private WebElement productDescription;

    @FindBy(xpath = "//a[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    public void clickAddToCartButton(){
        clickElement(addToCartButton);
    }

    public boolean cartButtonDisplayed(){
        return elementIsDisplayed(addToCartButton);
    }

    public String getProductName() {
        return textFromElement(productNameTitle);
    }

    public String getProductPrice() {
        return textFromElement(productPrice);
    }

    public double getProductPriceAsDouble() {
        String priceText = getProductPrice();
        String cleanedPrice = priceText.replaceAll("[^0-9]", "");
        return Double.parseDouble(cleanedPrice);
    }
}
