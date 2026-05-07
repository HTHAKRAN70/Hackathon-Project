package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarHomePage extends BasePage {

    private final By carMenuLocator = By.xpath("//li[@data-hover='Car']");

    public CarHomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickCarMenu() {
        driver.findElement(carMenuLocator).click();
    }
}
