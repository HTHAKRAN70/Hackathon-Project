package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Navigation + Actions
    private final By contactUsMenu =
            By.xpath("//a[contains(text(),'Contact Us')]");

    private final By fileComplaintBtn =
            By.xpath("//*[@id=\"contact-step-call\"]/button");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickContactUs() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsMenu)).click();
    }

    public void clickFileComplaint() {
        wait.until(ExpectedConditions.elementToBeClickable(fileComplaintBtn)).click();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}