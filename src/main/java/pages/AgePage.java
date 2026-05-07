//package pages;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class AgePage {
//	WebDriver driver;
//	WebDriverWait wait;
//	public AgePage(WebDriver driver) {
//		this.driver=driver;
//	    this.wait=new WebDriverWait(driver,Duration.ofSeconds(20));		
//	}
//	public void SelectAge(String YourAge,String SpouseAge,String DaughterAge,String sonAge, String fatherAge, String MotherAge) throws InterruptedException {
//		WebElement you;
//		WebElement Spouse;
//		WebElement Daughter;
//		WebElement Son;
//		WebElement Father;
//		WebElement Mother;
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ms--lbl")));
//		if(Integer.parseInt(YourAge)>0) {
//			you=driver.findElement(By.id("Age-You"));
//			
//			Select select=new Select(you);
//			String age=YourAge+"y";
//			select.selectByValue(age);
//			
//		}
//		if(Integer.parseInt(SpouseAge)>0) {
//			Spouse=driver.findElement(By.id("Age-Spouse"));
//			Select select=new Select(Spouse);
//			String age=SpouseAge+"y";
//			select.selectByValue(age);
//		}
//		String[] daugtherAges=DaughterAge.split(",");
//		if((int)(daugtherAges[0].charAt(0)-'0')>0) {
//			if(daugtherAges.length>1) {
//				for(int i=0;i<daugtherAges.length;i++) {
//					String daughterid="Age-Daughter-"+String.valueOf(i+1);
//					
//					Daughter=driver.findElement(By.id(daughterid));
//					Select select=new Select(Daughter);
//					String age=daugtherAges[i].substring(0,daugtherAges[i].length()-1)+daugtherAges[i].charAt(daugtherAges[0].length()-1)+"";
//					
//					select.selectByValue(age);
//				}
//				
//			}else {
//				Daughter=driver.findElement(By.id("Age-Daughter"));
//				Select select=new Select(Daughter);
//				String age=daugtherAges[0].substring(0,daugtherAges[0].length()-1)+daugtherAges[0].charAt(daugtherAges[0].length()-1)+"";
//				select.selectByValue(age);
//			}
//		}
//		
//		String[] sonAges=sonAge.split(",");
//		if((int)(sonAges[0].charAt(0)-'0')>0) {
//			if(sonAges.length>1) {
//				for(int i=0;i<sonAges.length;i++) {
//					String sonid="Age-Son-"+String.valueOf(i);
//					Son=driver.findElement(By.id(sonid));
//					Select select=new Select(Son);
//					String age=sonAges[i].substring(0,sonAges[i].length()-1)+sonAges[i].charAt(sonAges[i].length()-1)+"";
//					select.selectByValue(age);
//				}
//				
//			}else {
//				Son=driver.findElement(By.id("Age-Son"));
//				Select select=new Select(Son);
//				String age=sonAges[0].substring(0,sonAges[0].length()-1)+sonAges[0].charAt(sonAges[0].length()-1)+"";
//				select.selectByValue(age);
//			}
//		}
//		if(Integer.parseInt(fatherAge)>0) {
//			Father=driver.findElement(By.id("Age-Father"));
//			Select select=new Select(Father);
//			String age=fatherAge+"y";
//			select.selectByValue(age);
//		}
//		if(Integer.parseInt(MotherAge)>0) {
//			Mother=driver.findElement(By.id("Age-Mother"));
//			Select select=new Select(Mother);
//			String age=MotherAge+"y";
//			select.selectByValue(age);
//		}
//	}
//	
//	public WebElement getNextButton() {
//		return driver.findElement(By.xpath("//div[@class='next-btn']"));
//	}
//	
//	public String getErrorMessage() {
//	    try {
//	        WebElement ele = wait.until(
//	            ExpectedConditions.visibilityOfElementLocated(
//	                By.xpath("//div[contains(@class,'ve--error')]"))
//	        );
//	        return ele.getText();
//	    } catch (Exception e) {
//	        return "";
//	    }
//	}
//}



package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AgePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By pageLabel =
            By.cssSelector(".ms--lbl");

    private final By youAge =
            By.id("Age-You");

    private final By spouseAge =
            By.id("Age-Spouse");

    private final By fatherAge =
            By.id("Age-Father");

    private final By motherAge =
            By.id("Age-Mother");

    private final By nextButton =
            By.xpath("//div[@class='next-btn']");

    private final By errorMessage =
            By.xpath("//div[contains(@class,'ve--error')]");


    // ================= CONSTRUCTOR =================

    public AgePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ================= MAIN ACTION =================

    /**
     * Example:
     * selectAge("25", "25", "0", "4m,4y,4m", "70", "70");
     */
    public void selectAge(
            String yourAge,
            String spouseAge,
            String daughterAges,
            String sonAges,
            String fatherAge,
            String motherAge) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(pageLabel));

        selectAdultAge(youAge, yourAge);
        selectAdultAge(this.spouseAge, spouseAge);
        selectAdultAge(this.fatherAge, fatherAge);
        selectAdultAge(this.motherAge, motherAge);

        selectChildAges("Age-Daughter", daughterAges);
        selectChildAges("Age-Son", sonAges);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    // ================= HELPER METHODS =================

    /**
     * Handles adult ages (years only)
     */
    private void selectAdultAge(By locator, String age) {
        if (isValidAdultAge(age)) {
            new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)))
                    .selectByValue(age + "y");
        }
    }

    /**
     * Handles child ages (months or years: m / y)
     * Example: 4m,4y,4m
     */
    private void selectChildAges(String baseId, String ages) {

        if (!isValidChildAgeGroup(ages)) return;

        String[] ageArray = ages.split(",");

        for (int i = 0; i < ageArray.length; i++) {

            String dropdownId = ageArray.length > 1
                    ? baseId + "-" + i
                    : baseId;

            selectSingleChildAge(By.id(dropdownId), ageArray[i]);
        }
    }

    /**
     * Selects one child dropdown value like 4m or 4y
     */
    private void selectSingleChildAge(By locator, String age) {

        if (age == null || age.trim().equals("0")) return;

        age = age.trim().toLowerCase();

        // must end with m or y
        if (!(age.endsWith("m") || age.endsWith("y"))) return;

        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)))
                .selectByValue(age);
    }

    private boolean isValidAdultAge(String age) {
        return age != null && !age.trim().equals("0") && !age.trim().isEmpty();
    }

    private boolean isValidChildAgeGroup(String ages) {
        return ages != null && !ages.trim().equals("0") && !ages.trim().isEmpty();
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