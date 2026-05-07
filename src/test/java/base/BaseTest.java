//package base;
//
//import java.time.Duration;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//
//import config.ConfigReader;
//
//public class BaseTest extends BasePage {
//
//    public WebDriver driver;
//
//    @BeforeMethod
//    public void setUp() {
//
//        String browserName = ConfigReader.getProperty("browser");
//
//        driver = setupDriver(browserName);
//
//        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));
//
//        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
//        driver.manage().window().maximize();
//
//        driver.get(ConfigReader.getProperty("url"));
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}


package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * BaseTest sets up and tears down the WebDriver for all test classes.
 * Every test class in the testcases package should extend BaseTest.
 */
public class BaseTest {

    /** Shared WebDriver instance available to all subclass tests. */
    public WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.coverfox.com/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
