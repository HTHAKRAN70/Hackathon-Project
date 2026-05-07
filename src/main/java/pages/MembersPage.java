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
    
    private final By youOption =
            By.xpath("(//div[contains(@class,'ms-option')])[1]");

    private final By partnerOption =
            By.xpath("(//div[contains(@class,'ms-option')])[2]");

    private final By daughterOption =
            By.xpath("(//div[contains(@class,'ms-option')])[3]");

    private final By sonOption =
            By.cssSelector("div.member-container div:nth-child(4) div:nth-child(2)");

    private final By fatherOption =
            By.xpath("//div[@class='member-container']//div[5]");

    private final By motherOption =
            By.xpath("(//div[contains(@class,'ms-option')])[6]");

    private final By plusButton =
            By.xpath("(//div[normalize-space()='+'])[1]");

    private final By nextButton =
            By.xpath("(//div[contains(@class,'next-btn')])[1]");

    private final By errorMessage =
            By.xpath("//div[@class='sel-error']");


    public MembersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void selectMembers(
            boolean you,
            boolean partner,
            boolean daughter,
            boolean son,
            boolean father,
            boolean mother,
            int noOfSon,
            int noOfDaughter) {

        handleToggle(youOption, you);
        handleToggle(partnerOption, partner);

        if (you || partner) {

            if (daughter) {
                handleToggle(daughterOption, true);
                addMembers(noOfDaughter);
            }

            if (son) {
                handleToggle(sonOption, true);
                addMembers(noOfSon);
            }
        }

        handleToggle(fatherOption, father);
        handleToggle(motherOption, mother);
    }


    private void handleToggle(By locator, boolean shouldBeSelected) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        boolean isSelected = element.getAttribute("class").contains("selected");

        if (shouldBeSelected != isSelected) {
            element.click();
        }
    }

    private void addMembers(int count) {
        if (count > 1) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(plusButton));
            for (int i = 0; i < count - 1; i++) {
                driver.findElement(plusButton).click();
            }
        }
    }

    public WebElement getNextButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(nextButton));
    }

    public String getErrorMessage() {
	try {
	        WebElement error =
	            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
	
	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].scrollIntoView(true);", error);
	
	        return error.getText();
	
	    } catch (Exception e) {
	        return "";
	    }

    }
}