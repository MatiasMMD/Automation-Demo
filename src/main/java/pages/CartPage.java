package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class CartPage extends BasePage {
    public CartPage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }

    @FindBy(id = "tbodyid")
    private WebElement cartTableBody;

    @FindBy(id = "totalp")
    private WebElement totalPriceLabel;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    public void clickPlaceOrderButton(){
        clickElement(placeOrderButton);
    }

    public boolean productListed(String productName) {
        wait.until(ExpectedConditions.visibilityOf(cartTableBody));
        String productCellLocator = String.format("//tbody[@id='tbodyid']//td[text()='%s']", productName);
        return elementIsDisplayed(productCellLocator);
    }

    public boolean productNotListed(String productName) {
        String productCellLocator = String.format("//tbody[@id='tbodyid']//td[text()='%s']", productName);
        return waitForElementToDisappear(productCellLocator);
    }

    public boolean placeOrderButtonDisplayed(){
        return elementIsDisplayed(placeOrderButton);
    }

    public String getTotalPrice() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(1));
            shortWait.until(ExpectedConditions.visibilityOf(totalPriceLabel));
            return totalPriceLabel.getText();
        } catch (org.openqa.selenium.TimeoutException e) {
            return ""; 
        }
    }

    public double getTotalPriceAsDouble() {
        String priceText = getTotalPrice();
        if (priceText == null || priceText.trim().isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(priceText);
    }

    public void deleteProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(cartTableBody));
        String deleteLinkLocator = String.format("//td[text()='%s']/following-sibling::td/a[text()='Delete']", productName);
        clickElement(deleteLinkLocator);
    }

    public boolean cartNotEmpty() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody[@id='tbodyid']/tr")));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void deleteFirstProductInCart() {
        clickElement("//tbody[@id='tbodyid']//a[text()='Delete']");
    }
}
