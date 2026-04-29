package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MembersPage {
	private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public MembersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    public void SelectMembers(Boolean You,Boolean partner,Boolean daughter, Boolean Son, Boolean father, Boolean Mother, int noOfSon, int noOfDaughter) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Select your gender']")));
    	if(You) {
    		driver.findElement(By.xpath("//div[@class='ms-option'][1]")).click();
    	}
    	if(partner) {
    		driver.findElement(By.xpath("(//div[@class='ms-option'])[2]")).click();
    		
    	}
    	if(You||partner) {
    		if(daughter) {
    			driver.findElement(By.xpath("//div[@class='ms-option'][3]")).click();
    			if(noOfDaughter>1) {
    				for(int i=0;i<noOfDaughter-1;i++) {
    					driver.findElement(By.xpath("//div[@class='ms-action'][normalize-space()='+'][1]")).click();
    	    			
    				}
    			}
    		}
    		if(Son) {
    			driver.findElement(By.cssSelector("div[class='member-container'] div:nth-child(4) div:nth-child(2)")).click();
    			if(noOfSon>1) {
    				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[normalize-space()='+'])[1]")));
    				for(int i=0;i<noOfSon-1;i++) {
    					driver.findElement(By.xpath("(//div[normalize-space()='+'])[1]")).click();
    	    		}
    			}
    		}
    		
    	}
    	if(father) {
    		driver.findElement(By.xpath("//div[@class='member-container']//div[5]")).click();
    	}
    	
    	if(Mother) {
    		driver.findElement(By.xpath("(//div[@class='ms-option'])[2]")).click();
    		
    	}
    	
    }
    public WebElement getNextButton() {
    	return driver.findElement(By.xpath("(//div[@class='next-btn'])[1]"));
    }
    
   
    

}
