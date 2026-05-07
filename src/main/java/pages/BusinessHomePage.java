package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusinessHomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // ================= LOCATORS =================

    private final By businessInsuranceMenu =
            By.xpath("//*[@id='top-bar']/div/ul[1]/li[3]/span[1]");

    private final By workmenCompensationLink =
            By.xpath("//*[@id='top-bar']/div/ul[1]/li[3]/ul/li[2]/a");

    // ================= CONSTRUCTOR =================

    public BusinessHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    // ================= ACTION METHODS =================

    public String getParentWindowHandle() {
        return driver.getWindowHandle();
    }

    public void clickBusinessInsuranceMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(businessInsuranceMenu)).click();
    }

    public void clickWorkmenCompensationLink() {
        js.executeScript("arguments[0].click();",
                driver.findElement(workmenCompensationLink));
    }

    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }

    public boolean switchToNewTab(String parentHandle) {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                return true;
            }
        }
        return false;
    }
}