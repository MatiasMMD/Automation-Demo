package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;


public class WebDriverSetup {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static ConfigReader config = ConfigReader.getInstance();

    public static void setup() {
        String browser = config.getBrowser().toLowerCase();
        WebDriver driverInstance;

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driverInstance = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driverInstance = new FirefoxDriver(firefoxOptions);
                break;
            case "edge": 
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driverInstance = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new RuntimeException("Browser '" + browser + "' is not supported. Please choose 'chrome', 'firefox' or 'edge'.");
        }
        webDriver.set(driverInstance);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
        getDriver().manage().window().maximize(); 
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }   
}
