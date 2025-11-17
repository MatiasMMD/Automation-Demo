package pages;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class PlaceOrderModalPage extends BasePage{
    public PlaceOrderModalPage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }

    @FindBy(id = "orderModal")
    private WebElement orderModal;

    @FindBy(id = "name")
    private WebElement nameForm;

    @FindBy(id = "country")
    private WebElement countryForm;

    @FindBy(id = "city")
    private WebElement cityForm;

    @FindBy(id = "card")
    private WebElement cardForm;

    @FindBy(id = "month")
    private WebElement monthForm;

    @FindBy(id = "year")
    private WebElement yearForm;

    @FindBy(xpath = "//div[@id='orderModal']//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(xpath = "//div[@id='orderModal']//button[text()='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[contains(@class, 'sweet-alert')]//button[contains(@class, 'confirm')]")
    private WebElement okButton;

    @FindBy(xpath = "//p[@class='lead text-muted']")
    private WebElement purchaseDetailsText;

    public boolean purchaseModalDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(orderModal));
        return elementIsDisplayed(orderModal);
    }

    public boolean purchaseModalClosed() {
        return wait.until(ExpectedConditions.invisibilityOf(orderModal));
    }

    public boolean purchaseConfirmationDisplayed(){
        return elementIsDisplayed(okButton);
    }

    public Map<String, String> getPurchaseDetails() {
        // Espera a que el texto 'Id:' aparezca, lo que confirma que los detalles se han cargado.
        By confirmationTextLocator = By.xpath("//div[contains(@class, 'sweet-alert') and contains(., 'Id:')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationTextLocator));

        String detailsText = textFromElement(purchaseDetailsText);

        return Arrays.stream(detailsText.split("\n"))
                .map(line -> line.split(":", 2))
                .filter(parts -> parts.length == 2)
                .collect(Collectors.toMap(
                    parts -> parts[0].trim(),
                    parts -> parts[1].trim()));
    }

    public void fillFullForm(String name, String country, String city, String creditCard, String month, String year){
        write(nameForm, name);
        write(countryForm, country);
        write(cityForm, city);
        write(cardForm, creditCard);
        write(monthForm, month);
        write(yearForm, year);
    }

    public void clickPurchase(){
        clickElement(purchaseButton);
    }

    public void clickOkButton(){
        clickElement(okButton);
    }

    public void clickCloseButton(){
        clickElement(closeButton);
    }
}
