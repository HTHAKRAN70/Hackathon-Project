//package pages;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class HomePage {
//	private WebDriver driver;
//    private WebDriverWait wait;
//
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        
//    }
//    public WebElement getGenderButton(String gender) {
//    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Best Health Insurance plans. Customized for you.']")));
//    	return driver.findElement(By.xpath("//div[normalize-space()='"+gender+"']"));
//    }
//    public WebElement getMobileInputBox() {
//    	return driver.findElement(By.xpath("(//input[@type='tel'])[1]"));
//    }
//    public WebElement getStartedButton() {
//    	return driver.findElement(By.xpath("//button[normalize-space()='Get Started']"));
//    	
//    }
//
//
//}
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ================= LOCATORS =================

    private final By landingHeading =
            By.xpath("//h2[normalize-space()='Best Health Insurance plans. Customized for you.']");

    private final By genderButton =
            By.xpath("//div[normalize-space()='%s']");

    private final By mobileInput =
            By.xpath("(//input[@type='tel'])[1]");

    private final By getStartedButton =
            By.xpath("//button[normalize-space()='Get Started']");

    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void selectGender(String gender) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(landingHeading));
        driver.findElement(
                By.xpath(String.format("//div[normalize-space()='%s']", gender))
        ).click();
    }

    public void enterMobileNumber(String mobileNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobileInput))
            .sendKeys(mobileNumber);
    }

    public void clickGetStarted() {
        wait.until(ExpectedConditions.elementToBeClickable(getStartedButton))
            .click();
    }
}
