package utils;


import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class DriverFactory {

    public WebDriver initDriver(String browserName) {
        WebDriver driver = null;
        System.out.println("Launching browser: " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {
            
            ChromeOptions options = new ChromeOptions();
            
            // Your required stealth/disable settings
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            
            EdgeOptions options = new EdgeOptions();
            
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-blink-features=AutomationControlled");
            
            driver = new EdgeDriver(options);
        } else {
            System.out.println("Browser " + browserName + " is not supported.");
        }

        return driver;
    }
}

