//package pages;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//
//public class BikeDetailsPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    private final By bikeRideHeading = By.xpath("//div[normalize-space()='Which bike do you ride?']");
//    private final By brandBajaj = By.xpath("//div[contains(@class,'modal-form-links four-links-row')]//div[2]");
//    private final By modelCT100 = By.xpath("//h5[normalize-space()='CT 100']");
//    private final By variantSpoke = By.xpath("//div[normalize-space()='Spoke (100 CC)']");
//    private final By year2023 = By.xpath("//span[text()='2023']");
//    private final By rtoField = By.xpath("//div[text()='Bike registration RTO']");
//    private final By cityHyderabad = By.xpath("//h5[normalize-space()='Hyderabad']");
//    private final By centralHydCentral = By.xpath("//p[text()='Hyderabad Central']");
//    private final By prevPolicyExpiryDropdown = By.xpath("//div[text()='Previous policy expired?']");
//    private final By notExpiredValue = By.xpath("//div[text()='Not expired']");
//    private final By viewQuotesFinalBtn = By.xpath("//button[contains(@title,'View Quotes')]");
//    private final By planDetailsBtn = By.xpath("(//p[text()='Plan Details'])[1]");
//
//    public BikeDetailsPage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//    }
//
//    public void clickBikeRideHeading() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(bikeRideHeading)).click();
//    }
//
//    public void selectBajajCT100() {
//        wait.until(ExpectedConditions.elementToBeClickable(brandBajaj)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(modelCT100)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(variantSpoke)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(year2023)).click();
//    }
//
//    public void selectRTOAndStatus() {
//        wait.until(ExpectedConditions.elementToBeClickable(rtoField)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(cityHyderabad)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(centralHydCentral)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(prevPolicyExpiryDropdown)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(notExpiredValue)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(viewQuotesFinalBtn)).click();
//    }
//
//    public void clickPlanDetailsWithJS() {
//        WebElement planDetails = wait.until(ExpectedConditions.presenceOfElementLocated(planDetailsBtn));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", planDetails);
//    }
//}
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BikeDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators from your manual script
    private final By bikeRideHeading = By.xpath("//div[normalize-space()='Which bike do you ride?']");
    private final By brandBajaj = By.xpath("//div[contains(@class,'modal-form-links four-links-row')]//div[2]");
    private final By modelCT100 = By.xpath("//h5[normalize-space()='CT 100']");
    private final By variantSpoke = By.xpath("//div[normalize-space()='Spoke (100 CC)']");
    private final By year2023 = By.xpath("//span[text()='2023']");
    private final By rtoField = By.xpath("//div[text()='Bike registration RTO']");
    private final By cityHyderabad = By.xpath("//h5[normalize-space()='Hyderabad']");
    private final By centralHydCentral = By.xpath("//p[text()='Hyderabad Central']");
    private final By prevPolicyExpiryDropdown = By.xpath("//div[text()='Previous policy expired?']");
    private final By notExpiredValue = By.xpath("//div[text()='Not expired']");
    private final By viewQuotesFinalBtn = By.xpath("//button[contains(@title,'View Quotes')]");
    private final By planDetailsBtn = By.xpath("(//p[text()='Plan Details'])[1]");
    private final By allPlanCards = By.xpath("(//p[text()='Plan Details'])");

    public BikeDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickBikeRideHeading() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bikeRideHeading)).click();
    }

    public void selectBajajCT100() {
        wait.until(ExpectedConditions.elementToBeClickable(brandBajaj)).click();
        wait.until(ExpectedConditions.elementToBeClickable(modelCT100)).click();
        wait.until(ExpectedConditions.elementToBeClickable(variantSpoke)).click();
        wait.until(ExpectedConditions.elementToBeClickable(year2023)).click();
    }

    public void selectRTOAndStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(rtoField)).click();
        wait.until(ExpectedConditions.elementToBeClickable(cityHyderabad)).click();
        wait.until(ExpectedConditions.elementToBeClickable(centralHydCentral)).click();
        wait.until(ExpectedConditions.elementToBeClickable(prevPolicyExpiryDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(notExpiredValue)).click();
        wait.until(ExpectedConditions.elementToBeClickable(viewQuotesFinalBtn)).click();
    }

    public int getPlanCount() {
    	
        // Ensuring the plans are visible before counting
        wait.until(ExpectedConditions.visibilityOfElementLocated(allPlanCards));
        List<WebElement> plans = driver.findElements(allPlanCards);
        return plans.size();
    }

    public void clickPlanDetailsWithJS() {
        WebElement planDetails = wait.until(ExpectedConditions.presenceOfElementLocated(planDetailsBtn));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", planDetails);
    }
}