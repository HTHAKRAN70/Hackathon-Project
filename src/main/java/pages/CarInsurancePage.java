//package pages;
//
//import base.BasePage;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import utils.WaitUtils;
//
//import java.util.List;
//
//public class CarInsurancePage extends BasePage {
//
//    private final WaitUtils waitUtils;
//
//    // Locators
//    private final By registrationInput      = By.xpath("//input[@type='text']");
//    private final By viewQuotesButton       = By.xpath("//button[@title='View Quotes']");
//    private final By errorMessage           = By.xpath("//p[@class='fs w--error']");
//    private final By newCarToggle           = By.xpath("//div[@class='car-new-qf landing-modifier form-row first mopro-homepage-lp']//div[1]//span[2]");
//    private final By fordBrand              = By.xpath("//div[@class=\"top-make\"]/div/div/img/following-sibling::div[contains(normalize-space(.), 'Ford')]");
//    private final By aspireModel            = By.xpath("//span[normalize-space()='Aspire']");
//    private final By dieselFuel             = By.xpath("//li[normalize-space()='Diesel']");
//    private final By baseVariant            = By.xpath("//span[normalize-space()='1.5D Base MT']");
//    private final By registrationYearOption = By.xpath("//div[@class=\"wizard-content\"]/div/div[2]/div[2]/div[3]/span[3]");
//    private final By year2025               = By.xpath("//div[@class='w--multi_select_options']//span[normalize-space()='2025']");
//    private final By policyNotExpired       = By.xpath("//div[contains(text(),'Not Expired')]");
//    private final By claimDontKnow          = By.xpath("//div[contains(text(),\"I don't know\")]");
//    private final By noOfPlans              = By.xpath("//span[@class='font-bold' and contains(@x-text, 'plans')]");
//    private final By planDetailsButtons     = By.xpath("//button[normalize-space()='Plan Details']");
//    private final By carReviewField1        = By.xpath("//div[@class='fb-car-review']/div[3]/div[1]");
//    private final By carReviewField2        = By.xpath("//div[@class='fb-car-review']/div[3]/div[2]");
//    private final By carReviewField3        = By.xpath("//div[@class='fb-car-review']/div[4]");
//    private final By carReviewField4        = By.xpath("//div[@class='fb-car-review']/div[5]");
//    private final By carReviewField5        = By.xpath("//div[@class='fb-car-review']/div[6]");
//
//    public CarInsurancePage(WebDriver driver) {
//        super(driver);
//        this.waitUtils = new WaitUtils(driver);
//    }
//
//    public void enterRegistrationNumber(String regNumber) {
//        driver.findElement(registrationInput).sendKeys(regNumber);
//    }
//
//    public void clickViewQuotes() {
//        driver.findElement(viewQuotesButton).click();
//    }
//
//    public String getErrorMessageText() {
//        return driver.findElement(errorMessage).getText();
//    }
//
//    public void clickNewCarToggle() {
//        driver.findElement(newCarToggle).click();
//    }
//
//    public void selectFordBrand() {
//        waitUtils.hardWait(2000);
//        WebElement brand = driver.findElement(fordBrand);
////        jsClick(brand);
//        brand.click();
//    }
//
//    public void selectAspireModel() {
//        jsClick(driver.findElement(aspireModel));
//    }
//
//    public void selectDieselFuel() {
//        jsClick(driver.findElement(dieselFuel));
//    }
//
//    public void selectBaseVariant() {
//        jsClick(driver.findElement(baseVariant));
//    }
//
//    public void selectRegistrationYearOption() {
//        WebElement element = waitForVisibility(registrationYearOption);
//        element.click();
//    }
//
//    public void selectYear2025() {
//        driver.findElement(year2025).click();
//    }
//
//    public void printCarReviewDetails() {
//        waitUtils.hardWait(2000);
//        System.out.println("Car Make: "   + driver.findElement(carReviewField1).getText());
//        System.out.println("Car Model: "  + driver.findElement(carReviewField2).getText());
//        System.out.println("Fuel Type: "  + driver.findElement(carReviewField3).getText());
//        System.out.println("Variant: "    + driver.findElement(carReviewField4).getText());
//        System.out.println("Year: "       + driver.findElement(carReviewField5).getText());
//    }
//
//    public void selectPolicyNotExpired() {
//        driver.findElement(policyNotExpired).click();
//    }
//
//    public void selectClaimDontKnow() {
//        driver.findElement(claimDontKnow).click();
//    }
//
//    public String getNumberOfPlans() {
//        waitUtils.hardWait(8000);
//        return driver.findElement(noOfPlans).getText();
//    }
//
//    public void clickFirstPlanDetails() {
//        List<WebElement> plans = driver.findElements(planDetailsButtons);
//        WebElement firstPlan = plans.get(0);
//        scrollIntoView(firstPlan);
//        firstPlan.click();
//        waitUtils.hardWait(4000);
//    }
//}

package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class CarInsurancePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private final By registrationInput      = By.xpath("//input[@type='text']");
    private final By viewQuotesButton       = By.xpath("//button[@title='View Quotes']");
    private final By errorMessage           = By.xpath("//p[@class='fs w--error']");
    private final By newCarToggle           = By.xpath("//div[@class='car-new-qf landing-modifier form-row first mopro-homepage-lp']//div[1]//span[2]");
    private final By fordBrand              = By.xpath("//div[@class=\"top-make\"]/div/div/img/following-sibling::div[contains(normalize-space(.), 'Ford')]");
    private final By aspireModel            = By.xpath("//span[normalize-space()='Aspire']");
    private final By dieselFuel             = By.xpath("//li[normalize-space()='Diesel']");
    private final By baseVariant            = By.xpath("//span[normalize-space()='1.5D Base MT']");
    private final By registrationYearOption = By.xpath("//div[@class=\"wizard-content\"]/div/div[2]/div[2]/div[3]/span[3]");
    private final By year2025               = By.xpath("//div[@class='w--multi_select_options']//span[normalize-space()='2025']");
    private final By policyNotExpired       = By.xpath("//div[contains(text(),'Not Expired')]");
    private final By claimDontKnow          = By.xpath("//div[contains(text(),\"I don't know\")]");
    private final By noOfPlans              = By.xpath("//span[@class='font-bold' and contains(@x-text, 'plans')]");
    private final By planDetailsButtons     = By.xpath("//button[normalize-space()='Plan Details']");
    private final By carReviewField1        = By.xpath("//div[@class='fb-car-review']/div[3]/div[1]");
    private final By carReviewField2        = By.xpath("//div[@class='fb-car-review']/div[3]/div[2]");
    private final By carReviewField3        = By.xpath("//div[@class='fb-car-review']/div[4]");
    private final By carReviewField4        = By.xpath("//div[@class='fb-car-review']/div[5]");
    private final By carReviewField5        = By.xpath("//div[@class='fb-car-review']/div[6]");

    public CarInsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void enterRegistrationNumber(String regNumber) {
        WaitUtils.waitForVisibility(driver, registrationInput).sendKeys(regNumber);
    }

    public void clickViewQuotes() {
        WaitUtils.waitForVisibility(driver, viewQuotesButton).click();
    }

    public String getErrorMessageText() {
        return WaitUtils.waitForVisibility(driver, errorMessage).getText();
    }

    public void clickNewCarToggle() {
        WaitUtils.waitForVisibility(driver, newCarToggle).click();
    }

    public void selectFordBrand() {
        WaitUtils.waitForVisibility(driver, fordBrand).click();
    }

    public void selectAspireModel() {
        jsClick(WaitUtils.waitForVisibility(driver, aspireModel));
    }

    public void selectDieselFuel() {
        jsClick(WaitUtils.waitForVisibility(driver, dieselFuel));
    }

    public void selectBaseVariant() {
        jsClick(WaitUtils.waitForVisibility(driver, baseVariant));
    }

    public void selectRegistrationYearOption() {
        WaitUtils.waitForVisibility(driver, registrationYearOption).click();
    }

    public void selectYear2025() {
        WaitUtils.waitForVisibility(driver, year2025).click();
    }

    public void printCarReviewDetails() {
        System.out.println("Car Make: "  + WaitUtils.waitForVisibility(driver, carReviewField1).getText());
        System.out.println("Car Model: " + WaitUtils.waitForVisibility(driver, carReviewField2).getText());
        System.out.println("Fuel Type: " + WaitUtils.waitForVisibility(driver, carReviewField3).getText());
        System.out.println("Variant: "   + WaitUtils.waitForVisibility(driver, carReviewField4).getText());
        System.out.println("Year: "      + WaitUtils.waitForVisibility(driver, carReviewField5).getText());
    }

    public void selectPolicyNotExpired() {
        WaitUtils.waitForVisibility(driver, policyNotExpired).click();
    }

    public void selectClaimDontKnow() {
        WaitUtils.waitForVisibility(driver, claimDontKnow).click();
    }

    public String getNumberOfPlans() {
        return WaitUtils.waitForVisibility(driver, noOfPlans).getText();
    }

    public void clickFirstPlanDetails() {
        List<WebElement> plans = driver.findElements(planDetailsButtons);
        WebElement firstPlan = plans.get(0);
        scrollIntoView(firstPlan);
        firstPlan.click();
    }
}
