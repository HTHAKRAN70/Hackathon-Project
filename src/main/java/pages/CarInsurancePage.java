package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.util.List;

public class CarInsurancePage extends BasePage {

    private final WaitUtils waitUtils;

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
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    public void enterRegistrationNumber(String regNumber) {
        driver.findElement(registrationInput).sendKeys(regNumber);
    }

    public void clickViewQuotes() {
        driver.findElement(viewQuotesButton).click();
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }

    public void clickNewCarToggle() {
        driver.findElement(newCarToggle).click();
    }

    public void selectFordBrand() {
        waitUtils.hardWait(2000);
        WebElement brand = driver.findElement(fordBrand);
        jsClick(brand);
    }

    public void selectAspireModel() {
        jsClick(driver.findElement(aspireModel));
    }

    public void selectDieselFuel() {
        jsClick(driver.findElement(dieselFuel));
    }

    public void selectBaseVariant() {
        jsClick(driver.findElement(baseVariant));
    }

    public void selectRegistrationYearOption() {
        WebElement element = waitForVisibility(registrationYearOption);
        element.click();
    }

    public void selectYear2025() {
        driver.findElement(year2025).click();
    }

    public void printCarReviewDetails() {
        waitUtils.hardWait(2000);
        System.out.println("Car Make: "   + driver.findElement(carReviewField1).getText());
        System.out.println("Car Model: "  + driver.findElement(carReviewField2).getText());
        System.out.println("Fuel Type: "  + driver.findElement(carReviewField3).getText());
        System.out.println("Variant: "    + driver.findElement(carReviewField4).getText());
        System.out.println("Year: "       + driver.findElement(carReviewField5).getText());
    }

    public void selectPolicyNotExpired() {
        driver.findElement(policyNotExpired).click();
    }

    public void selectClaimDontKnow() {
        driver.findElement(claimDontKnow).click();
    }

    public String getNumberOfPlans() {
        waitUtils.hardWait(8000);
        return driver.findElement(noOfPlans).getText();
    }

    public void clickFirstPlanDetails() {
        List<WebElement> plans = driver.findElements(planDetailsButtons);
        WebElement firstPlan = plans.get(0);
        scrollIntoView(firstPlan);
        firstPlan.click();
        waitUtils.hardWait(4000);
    }
}
