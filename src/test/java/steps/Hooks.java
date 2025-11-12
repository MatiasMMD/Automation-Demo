package steps;

import utils.WebDriverSetup;
import pages.CartPage;
import pages.MainPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void setup() {
        WebDriverSetup.setup();
        MainPage mainPage = new MainPage();
        mainPage.navigateToDemoBlaze();
    }

    @After
    public void tearDown() {
        WebDriverSetup.quitDriver();
    }
}
