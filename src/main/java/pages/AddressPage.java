package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage {
	WebDriver driver;
	WebDriverWait wait;
	public AddressPage(WebDriver driver) {
		this.driver=driver;
	    this.wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		
	}
	
	public WebElement getYouPinCodeInput() {
		return driver.findElement(By.xpath("(//input[@placeholder='6 Digit Pincode'])[1]"));
	}
	public WebElement getParentPinCodeInput() {
		return driver.findElement(By.xpath("(//input[@placeholder='6 Digit Pincode'])[2]"));
	}
	public WebElement getMobileNumberInput() {
		return driver.findElement(By.id("want-expert"));
	}
	public WebElement getContinueButton() {
		return driver.findElement(By.xpath("//div[@class='next-btn']"));
	}

}
