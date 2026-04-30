package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HealthInsuranceResultPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final By resultCountText =
            By.xpath("//div[contains(text(),'matching Health Insurance Plans')]");

    private final By planDetailButtons =
            By.className("pd-btn");

    private final By insurerName =
            By.xpath("//div[contains(@class,'pdh-insurer')]");

    private final By planName =
            By.className("pdh-name");

    public HealthInsuranceResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        this.wait.ignoring(StaleElementReferenceException.class);
    }

    // ✅ Stabilize API results
    public int getNumberOfResults() {

        WebElement resultEle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(resultCountText)
        );

        int previous = -1;
        int current = 0;

        for (int i = 0; i < 5; i++) {
            current = Integer.parseInt(resultEle.getText().split(" ")[0]);
            if (current == previous) {
                return current;
            }
            previous = current;
            sleep(1000);
        }
        return current;
    }

    
    
    public List<List<String>> fetchAllPlanDetails() throws InterruptedException {

        List<List<String>> allPlansData = new ArrayList<>();

        getNumberOfResults();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(planDetailButtons, 0));

        int totalPlans = driver.findElements(planDetailButtons).size();

        for (int index = 0; index < totalPlans; index++) {

            // re-locate buttons every iteration
            Thread.sleep(3000);
        	List<WebElement> buttons = driver.findElements(planDetailButtons);
            WebElement detailsBtn = buttons.get(index);

            scrollIntoView(detailsBtn);
            wait.until(ExpectedConditions.elementToBeClickable(detailsBtn)).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(planName));

            // Collect plan data into inner list
            List<String> singlePlanData = collectPlanData();

            allPlansData.add(singlePlanData);

            driver.navigate().back();
            if(index==totalPlans-1)break;
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(planDetailButtons, 0));
        }

        return allPlansData;
    }

    // ✅ Collect data for ONE plan
    private List<String> collectPlanData() {

        List<String> planData = new ArrayList<>();

        String insurerText = driver.findElement(insurerName).getText();
        String planText = driver.findElement(planName).getText();

        String priceValue = driver.findElement(
                By.cssSelector("div.pdh-buy-amt div.rupee-val")
        ).getText().trim();
        String pricePerYear = "₹" + priceValue + " /yr";

        String sumAssuredValue = driver.findElement(
                By.cssSelector("div.pdh-sa-val div.rupee-val")
        ).getText().trim();
        String sumAssured = "₹" + sumAssuredValue;
        planData.add(insurerText);
        planData.add(planText);
        planData.add(pricePerYear);
        planData.add(sumAssured);

        return planData;
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", element
        );
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public WebElement getViewMemberDetailsButton() {
    	return driver.findElement(By.xpath("//div[@class='mb__action-box']"));
    }
    public WebElement getEditDetailsButton() {
    	return driver.findElement(By.xpath("//button[normalize-space()='Edit Details']"));
    }
}
