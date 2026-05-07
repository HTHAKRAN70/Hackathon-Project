package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import config.ConfigReader;
public class BaseTest extends BasePage {

    public  WebDriver driver;

    @BeforeClass
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


    public WebDriver getDriver(){
    	return driver;
    } 
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

