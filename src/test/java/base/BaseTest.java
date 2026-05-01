package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import config.ConfigReader;

public class BaseTest extends BasePage {

    public  WebDriver driver;

    @BeforeTest
    public void setUp() {

        String browserName = ConfigReader.getProperty("browser");

        driver = setupDriver(browserName);

        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}