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