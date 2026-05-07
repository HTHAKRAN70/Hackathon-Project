package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusinessInsurancePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // ================= LOCATORS — Contact Details Form =================

    private final By continueButton =
            By.xpath("//*[@id='form1']/section[1]/div/div/div/div[2]/div/div[10]/button");

    private final By mobileField =
            By.id("ContentPlaceHolder1_txtMobile");

    private final By emailField =
            By.id("ContentPlaceHolder1_txtEmail");

    private final By companyNameField =
            By.id("ContentPlaceHolder1_txtNameOfCompany");

    private final By pincodeField =
            By.id("ContentPlaceHolder1_txtPincode");

    private final By mobileError =
            By.id("ContentPlaceHolder1_rfvMobile");

    // ================= LOCATORS — Industry Selection =================

    private final By industryCategoryDropdown =
            By.id("ContentPlaceHolder1_ddlIndustryCategory");

    private final By industrySubCategoryDropdown =
            By.id("ContentPlaceHolder1_ddlIndustrySubCategory");

    private final By getQuoteButton =
            By.id("btnGetQuote");

    private final By errorModal =
            By.id("erroModalPopup");

    // ================= LOCATORS — Worker Details =================

    private final By skilledWorkerCount =
            By.xpath("//*[@id='ContentPlaceHolder1_txtNoOfSkilledWorkers']");

    private final By skilledWorkerSalary =
            By.xpath("//*[@id='ContentPlaceHolder1_txtSalaryOfSkilledWorkers']");

    private final By semiSkilledWorkerCount =
            By.xpath("//*[@id='ContentPlaceHolder1_txtNoOfSemiSkilledWorkers']");

    private final By semiSkilledWorkerSalary =
            By.xpath("//*[@id='ContentPlaceHolder1_txtSalaryOfSemiSkilledWorkers']");

    private final By unskilledWorkerCount =
            By.xpath("//*[@id='ContentPlaceHolder1_txtNoOfUnskilledWorkers']");

    private final By unskilledWorkerSalary =
            By.xpath("//*[@id='ContentPlaceHolder1_txtSalaryOfUnskilledWorkers']");

    private final By proceedButton =
            By.xpath("//*[@id='btnGetQuote']");

    // ================= CONSTRUCTOR =================

    public BusinessInsurancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    // ================= ACTION METHODS — TC2 =================

    public void clickContinueWithoutDetails() {
        driver.findElement(continueButton).click();
    }

    public String getMobileErrorMessage() {
        return driver.findElement(mobileError).getText();
    }

    // ================= ACTION METHODS — TC3 =================

    public void fillContactDetails(String mobile, String email,
                                   String companyName, String pincode) {
        driver.findElement(mobileField).sendKeys(mobile);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(companyNameField).sendKeys(companyName);
        driver.findElement(pincodeField).sendKeys(pincode);
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    // ================= ACTION METHODS — TC4 =================

    public void selectIndustryCategory(int index) {
        new Select(driver.findElement(industryCategoryDropdown)).selectByIndex(index);
    }

    public void selectIndustrySubCategory(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(industrySubCategoryDropdown));
        new Select(driver.findElement(industrySubCategoryDropdown)).selectByIndex(index);
    }

    public void clickGetQuote() {
        driver.findElement(getQuoteButton).click();
    }

    public String getValidationMessage() {
        // Wait for modal to be present in DOM (Bootstrap fade may block visibility check)
        wait.until(ExpectedConditions.presenceOfElementLocated(errorModal));
        // Read text via JS to bypass any visibility/opacity issues during Bootstrap animation
        return (String) js.executeScript(
                "return document.getElementById('erroModalPopup').innerText;");
    }

    public void dismissErrorModal() {
        js.executeScript("document.getElementById('btnOk').click();");
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        js.executeScript("document.querySelector('.modal-backdrop').remove();");
        js.executeScript("document.getElementById('erroModalPopup').style.display='none';");
    }

    // ================= ACTION METHODS — TC5 =================

    public void fillWorkerDetails(String skilledCount, String skilledSalary,
                                  String semiSkilledCount, String semiSkilledSalary,
                                  String unskilledCount, String unskilledSalary) {
        driver.findElement(skilledWorkerCount).sendKeys(skilledCount);
        driver.findElement(skilledWorkerSalary).sendKeys(skilledSalary);
        driver.findElement(semiSkilledWorkerCount).sendKeys(semiSkilledCount);
        driver.findElement(semiSkilledWorkerSalary).sendKeys(semiSkilledSalary);
        driver.findElement(unskilledWorkerCount).sendKeys(unskilledCount);
        driver.findElement(unskilledWorkerSalary).sendKeys(unskilledSalary);
    }

    public void clickProceed() {
        js.executeScript("arguments[0].click();", driver.findElement(proceedButton));
    }
}