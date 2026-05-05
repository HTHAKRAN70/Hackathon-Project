package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;

public class ComplaintPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By nameField = By.xpath("//*[@id=\"name-input\"]");
    private final By mobileField = By.xpath("//*[@id=\"mobile-number-email-input\"]");
    private final By emailField = By.xpath("//*[@id=\"email-input\"]");
    private final By complaintField = By.xpath("//*[@id=\"message-input\"]");
    private By productDropdown = By.xpath("//*[@id='product-input']");
    private final By policyField=By.xpath("//*[@id=\"policy-input\"]");
    private final By submitBtn = By.xpath("//*[@id=\"email-me-form\"]/button");
    private By phoneErrorMsg = By.xpath("//*[@id='email-me-form']/div[3]/div[2]");
    private By successMessage = By.xpath("//*[@id='contact-step-thanks']/h5");
    private By emailErrorMsg = By.xpath("//*[@id='email-me-form']/div[4]/div[2]");

    public ComplaintPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterMobile(String mobile) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobileField)).clear();
        driver.findElement(mobileField).sendKeys(mobile);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);

    }
    public void selectTermInsurance() {

        // Click dropdown
        wait.until(ExpectedConditions.elementToBeClickable(productDropdown)).click();

        // Click "Term"
        By termOption = By.xpath("//*[@id=\"product-input\"]/option[5]");
        wait.until(ExpectedConditions.elementToBeClickable(termOption)).click();
    }
    public void enterPolicy(String mobile) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobileField)).clear();
        driver.findElement(mobileField).sendKeys(mobile);
    }

    public void enterComplaint(String complaint) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(complaintField)).clear();
        driver.findElement(complaintField).sendKeys(complaint);
    }

    public void fillForm(String name,String email, String policy, String complaint,String mobile) {
        enterName(name);
        enterEmail(email);
        selectTermInsurance();
        enterPolicy(policy);
        enterComplaint(complaint);
        enterMobile(mobile);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }


    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage))
                .getText();
    }

    public String getPhoneErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneErrorMsg))
                .getText();
    }



    public String getEmailErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrorMsg))
                .getText();
    }


}
