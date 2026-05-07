//package pages;
//
//import base.BasePage;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//public class CarHomePage extends BasePage {
//
//    private final By carMenuLocator = By.xpath("//li[@data-hover='Car']");
//
//    public CarHomePage(WebDriver driver) {
//        super(driver);
//    }
//
//    public void navigateTo(String url) {
//        driver.get(url);
//    }
//
//    public String getCurrentUrl() {
//        return driver.getCurrentUrl();
//    }
//
//    public void clickCarMenu() {
//        driver.findElement(carMenuLocator).click();
//    }
//}

package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarHomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By carMenuLocator = By.xpath("//li[@data-hover='Car']");

    public CarHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCarMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(carMenuLocator)).click();
    }
}
