package steps;

import utils.WebDriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

     @Before
    public void setup() {
        WebDriverSetup.setup();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                WebDriver driver = WebDriverSetup.getDriver();
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (Exception e) {
                logger.error("Failed to take screenshot for scenario: " + scenario.getName(), e);
            }
        }
        WebDriverSetup.quitDriver();
    }   
}
