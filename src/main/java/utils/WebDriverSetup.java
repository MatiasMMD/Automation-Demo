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
        boolean isCi = "true".equalsIgnoreCase(System.getenv("CI"));

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isCi) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--window-size=1920,1080");
                } else {
                    chromeOptions.addArguments("--start-maximized");
                }
                driverInstance = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isCi) {
                    firefoxOptions.addArguments("--headless");
                }
                driverInstance = new FirefoxDriver(firefoxOptions);
                break;
            case "edge": 
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isCi) {
                    edgeOptions.addArguments("--headless");
                }
                driverInstance = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new RuntimeException("Browser '" + browser + "' is not supported. Please choose 'chrome', 'firefox' or 'edge'.");
        }
        webDriver.set(driverInstance);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
        if (!isCi) {
            getDriver().manage().window().maximize();
        }
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
