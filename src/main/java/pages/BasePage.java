package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WebDriverSetup;
import utils.ConfigReader;
import utils.ActionsHelper;

public class BasePage {
    protected WebDriver driver; 
    protected WebDriverWait wait;
    protected ConfigReader configReader;
    protected ActionsHelper actionsHelper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.configReader = ConfigReader.getInstance(); 
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(configReader.getExplicitWait()));
        this.actionsHelper = new ActionsHelper(driver);
    }

    public void navigateToBaseUrl() {
        driver.get(configReader.getBaseUrl());
    }

    public void navigateTo(String urlOrPath) {
        if (urlOrPath.startsWith("http://") || urlOrPath.startsWith("https://")) {
            driver.get(urlOrPath); 
        } else {
            driver.get(configReader.getBaseUrl() + urlOrPath); 
        }
    }

    public void goToLinkText (String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

    private WebElement find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    // Métodos con locators.
    public void clickElement (String locator){
        int attempts = 0;
        while(attempts < 2) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
                break;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public void submitElement(String locator){
        find(locator).submit();
    }

    public void write (String locator, String textToWrite){
        find(locator).clear();
        find(locator).sendKeys(textToWrite);
    }

    public int dropdownSize(String locator){
        Select dropdown = new Select(find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        return dropdownOptions.size();
    }

    public void selectDropdownByValue (String locator, String valueToSelect){
        Select dropdown = new Select(find(locator));
        dropdown.selectByValue(valueToSelect);
    }

    public void selectDropdownByIndex (String locator, int valueToSelect){
        Select dropdown = new Select(find(locator));
        dropdown.selectByIndex(valueToSelect);
    }

    public void selectFromDropdownByText (String locator, String valueToSelect){
        Select dropdown = new Select(find(locator));
        dropdown.selectByVisibleText(valueToSelect);
    }

    public String textFromElement (String locator){
        return find(locator).getText();
    }

    public boolean elementEnabled(String locator){
        return find(locator).isEnabled(); 
    }

    public boolean elementIsDisplayed (String locator){
        try {
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public boolean waitForElementToDisappear(String locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
    }

    // Métodos con WebElement

    public void clickElement (WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void submitElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element)).submit();
    }

    public void write (WebElement element, String textToWrite){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(textToWrite);
    }

    public int dropdownSize(WebElement element){
        Select dropdown = new Select(element);
        List<WebElement> dropdownOptions = dropdown.getOptions();
        return dropdownOptions.size();
    }

    public void selectDropdownByValue (WebElement element, String valueToSelect){
        Select dropdown = new Select(element);
        dropdown.selectByValue(valueToSelect);
    }

    public void selectDropdownByIndex (WebElement element, int valueToSelect){
        Select dropdown = new Select(element);
        dropdown.selectByIndex(valueToSelect);
    }

    public void selectFromDropdownByText (WebElement element, String valueToSelect){
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(valueToSelect);
    }

    public String textFromElement (WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public boolean elementEnabled(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isEnabled();
    }

    public boolean elementIsDisplayed (WebElement element){
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Métodos que usan locators para casos dinámicos.
    public String getValueFromTable (String locator, int row, int column){
        String cellINeed = locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        return find(cellINeed).getText();
    }

    public void setValueOnTable (String locator, int row, int column, String stringToSend){
        String cellToFill = locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        find(cellToFill).sendKeys(stringToSend);
    }

    public void switchToiFrame (int iframeIndex){
        driver.switchTo().frame(iframeIndex);
    }

    public void switchToParentFrame(){
        driver.switchTo().parentFrame();
    }

    public void dismissAlert(){
        try{
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e){
            e.printStackTrace();
        }
    }

    public String alertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public List<WebElement> bringMeAllElements (String locator){
        return driver.findElements(By.xpath(locator)); 
    }

    public List<WebElement> bringMeAllElements (By locator){
        return driver.findElements(locator);
    }

    public List<WebElement> bringMeAllElementsByClassName (String locator){
        return driver.findElements(By.className(locator));
    }

    public void selectNthElementFromList (String locator, int index){
        List<WebElement> list = driver.findElements(By.className(locator));
        list.get(index).click();
    }

    public void selectCriteriaFromList (String locator, String criteria){
        List<WebElement> list = driver.findElements(By.className(locator));
        for (WebElement element : list) {
            if (element.getText().equals(criteria)) {
                element.click();
                break;
            }
        }
    }
}