package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AgePage {
	WebDriver driver;
	WebDriverWait wait;
	public AgePage(WebDriver driver) {
		this.driver=driver;
	    this.wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		
	}
	public void SelectAge(String YourAge,String SpouseAge,String DaughterAge,String sonAge, String fatherAge, String MotherAge) throws InterruptedException {
		WebElement you;
		WebElement Spouse;
		WebElement Daughter;
		WebElement Son;
		WebElement Father;
		WebElement Mother;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ms--lbl")));
		if(Integer.parseInt(YourAge)>0) {
			you=driver.findElement(By.id("Age-You"));
			
			Select select=new Select(you);
			String age=YourAge+"y";
			select.selectByValue(age);
			
		}
		if(Integer.parseInt(SpouseAge)>0) {
			Spouse=driver.findElement(By.id("Age-Spouse"));
			Select select=new Select(Spouse);
			String age=SpouseAge+"y";
			select.selectByValue(age);
		}
		String[] daugtherAges=DaughterAge.split(",");
		if(Integer.parseInt(daugtherAges[0])>0) {
			if(daugtherAges.length>1) {
				for(int i=0;i<daugtherAges.length;i++) {
					String daughterid="Age-Daughter-"+String.valueOf(i+1);
					
					Daughter=driver.findElement(By.id(daughterid));
					Select select=new Select(Daughter);
					String age=daugtherAges[i]+"y";
					select.selectByValue(age);
				}
				
			}else {
				Daughter=driver.findElement(By.id("Age-Daughter"));
				Select select=new Select(Daughter);
				String age=daugtherAges[0]+"y";
				select.selectByValue(age);
			}
		}
		
		String[] sonAges=sonAge.split(",");
		if(Integer.parseInt(sonAges[0])>0) {
			if(sonAges.length>1) {
				for(int i=0;i<sonAges.length;i++) {
					String sonid="Age-Son-"+String.valueOf(i);
					Son=driver.findElement(By.id(sonid));
					Select select=new Select(Son);
					String age=sonAges[i]+"y";
					select.selectByValue(age);
				}
				
			}else {
				Son=driver.findElement(By.id("Age-Son"));
				Select select=new Select(Son);
				String age=sonAges[0]+"y";
				select.selectByValue(age);
			}
		}
		if(Integer.parseInt(fatherAge)>0) {
			Father=driver.findElement(By.id("Age-Father"));
			Select select=new Select(Father);
			String age=fatherAge+"y";
			select.selectByValue(age);
		}
		if(Integer.parseInt(MotherAge)>0) {
			Mother=driver.findElement(By.id("Age-Mother"));
			Select select=new Select(Mother);
			String age=MotherAge+"y";
			select.selectByValue(age);
		}
	}
	public WebElement getNextButton() {
		return driver.findElement(By.xpath("//div[@class='next-btn']"));
	}

}
