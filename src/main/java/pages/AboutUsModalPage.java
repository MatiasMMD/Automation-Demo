package pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverSetup;

public class AboutUsModalPage extends BasePage{
    public AboutUsModalPage() {
        super(WebDriverSetup.getDriver());
        PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, (int) configReader.getExplicitWait()), this);
    }

    @FindBy(id = "videoModalLabel")
    private WebElement aboutUsModalMessage;

    @FindBy(className = "vjs-big-play-button")
    private WebElement videoAboutUs;

    public boolean aboutUsModalDisplayed(){
        return elementIsDisplayed(aboutUsModalMessage);
    }

    public void checkVideoAboutUs(){
        elementIsDisplayed(videoAboutUs);
        clickElement(videoAboutUs);
    }
}
