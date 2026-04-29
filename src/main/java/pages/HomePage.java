package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
    }
    public WebElement getGenderButton(String gender) {
    	return driver.findElement(By.xpath("//div[normalize-space()='"+gender+"']"));
    }
    public WebElement getMobileInputBox() {
    	return driver.findElement(By.xpath("(//input[@type='tel'])[1]"));
    }
    public WebElement getStartedButton() {
    	return driver.findElement(By.xpath("//button[normalize-space()='Get Started']"));
    	
    }
    
    
    
    

}
