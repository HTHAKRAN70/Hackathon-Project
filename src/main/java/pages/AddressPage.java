//package pages;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class AddressPage {
//	WebDriver driver;
//	WebDriverWait wait;
//	public AddressPage(WebDriver driver) {
//		this.driver=driver;
//	    this.wait=new WebDriverWait(driver,Duration.ofSeconds(20));
//		
//	}
//	
//	public WebElement getYouPinCodeInput() {
//		return driver.findElement(By.xpath("(//input[@placeholder='6 Digit Pincode'])[1]"));
//	}
//	public WebElement getParentPinCodeInput() {
//		return driver.findElement(By.xpath("(//input[@placeholder='6 Digit Pincode'])[2]"));
//	}
//	public WebElement getMobileNumberInput() {
//		return driver.findElement(By.id("want-expert"));
//	}
//	public WebElement getContinueButton() {
//		return driver.findElement(By.xpath("//div[@class='next-btn']"));
//	}
//	public String getErrorMssg() {
//		return driver.findElement(By.xpath("//div[contains(@class,'error-ui')]")).getText();
//	}
//
//}
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ================= LOCATORS =================

    private final By youPincodeInput =
            By.xpath("(//input[@placeholder='6 Digit Pincode'])[1]");

    private final By parentPincodeInput =
            By.xpath("(//input[@placeholder='6 Digit Pincode'])[2]");

    private final By mobileNumberInput =
            By.id("want-expert");

    private final By continueButton =
            By.xpath("//div[@class='next-btn']");

    private final By errorMessage =
            By.xpath("//div[contains(@class,'error-ui')]");

    // ================= CONSTRUCTOR =================

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ================= ACTION METHODS =================

    public void enterYouPincode(String pincode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(youPincodeInput))
            .sendKeys(pincode);
    }

    public void enterParentPincode(String pincode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(parentPincodeInput))
            .sendKeys(pincode);
    }

    public void enterMobileNumber(String mobileNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumberInput))
            .sendKeys(mobileNumber);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton))
            .click();
    }

    // ================= COMPOSED ACTION =================

    public void enterAddressDetails(String youPin, String parentPin, String mobile) {
        enterYouPincode(youPin);
        if(parentPin.length()!=0)enterParentPincode(parentPin);
        enterMobileNumber(mobile);
        clickContinue();
    }

    // ================= GETTERS =================

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage))
                       .getText();
        } catch (Exception e) {
            return "";
        }
    }
}
