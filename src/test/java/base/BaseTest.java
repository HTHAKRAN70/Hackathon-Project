package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import config.ConfigReader;
import utils.DriverFactory;

public class BaseTest {

    public WebDriver driver;

    @BeforeTest
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();

        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterTest
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
