package base;


import org.openqa.selenium.WebDriver;

import utils.DriverFactory;

public class BasePage {
    public WebDriver driver;

    
    public WebDriver setupDriver(String browserName) {
        DriverFactory df = new DriverFactory();
        driver = df.initDriver(browserName);
        return driver;
    }
}

