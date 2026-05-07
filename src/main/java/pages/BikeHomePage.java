package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BikeHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By bikeTab = By.xpath("//li[@data-hover='Bike']");
    private final By regInput = By.xpath("(//input[@type='text' and @maxlength='15'])[1]");
    private final By policyExpiryDropdown = By.xpath("//div[text()='Has your previous policy expired?']");
    private final By notExpiredOption = By.xpath("//div[contains(@class, 'bike_policy_details__item') and text()='Not expired']");
    private final By viewQuotesBtn = By.xpath("//button[contains(text(), 'View Quotes')]");
    private final By errorMsg = By.xpath("//p[contains(@class, 'w--error')]");
    private final By notSureBtn = By.xpath("//button[@title='Not sure of your bike number?']");

    public BikeHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickBikeTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bikeTab)).click();
    }

    public void enterRegNumber(String regNum) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(regInput));
        element.clear();
        element.sendKeys(regNum);
    }

    public void handleInitialPolicyStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(policyExpiryDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(notExpiredOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewQuotesBtn)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
    }

    public void clickNotSure() {
        wait.until(ExpectedConditions.elementToBeClickable(notSureBtn)).click();
    }
}