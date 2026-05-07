package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import config.ConfigReader;
import pages.CarHomePage;
import pages.CarInsurancePage;
import utils.ScreenshotUtils;

@Listeners(listeners.TestListener.class)
public class TestCarInsurance extends BaseTest {

    CarHomePage carHomePage;
    CarInsurancePage carInsurancePage;
    SoftAssert softAssert;

    @BeforeClass
    public void initPages() {
        softAssert = new SoftAssert();
        carHomePage = new CarHomePage(driver);
        carInsurancePage = new CarInsurancePage(driver);
    }

    @Test(priority = 1)
    public void testNavigateToCarInsurance() {
        driver.get(ConfigReader.getProperty("base.url"));
        carHomePage.clickCarMenu();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = ConfigReader.getProperty("car.insurance.url");
        Assert.assertEquals(currentUrl, expectedUrl, "URL does not match!");
        System.out.println("Test 1 Passed: Navigated to Car Insurance page and URL verified.");
    }

    @Test(priority = 2, dependsOnMethods = {"testNavigateToCarInsurance"})
    public void testEnterPincodeAndProceed() {
        carInsurancePage.enterRegistrationNumber("23456");
        carInsurancePage.clickViewQuotes();
        String text = carInsurancePage.getErrorMessageText();
        Assert.assertEquals(text, "Not a valid registration number", "Validation error message mismatch!");
        System.out.println("Test 2 - Validation Error: " + text);
        carInsurancePage.clickNewCarToggle();
    }

    @Test(priority = 3, dependsOnMethods = {"testEnterPincodeAndProceed"})
    public void testSelectCarDetails() {
        carInsurancePage.selectFordBrand();
        carInsurancePage.selectAspireModel();
        carInsurancePage.selectDieselFuel();
        carInsurancePage.selectBaseVariant();
        carInsurancePage.selectRegistrationYearOption();
        carInsurancePage.selectYear2025();
        System.out.println("Test 3 Passed: Car details selected successfully.");
    }

    @Test(priority = 4, dependsOnMethods = {"testSelectCarDetails"})
    public void testReviewAndProceed() {
        carInsurancePage.printCarReviewDetails();
        carInsurancePage.selectPolicyNotExpired();
        carInsurancePage.selectClaimDontKnow();
        carInsurancePage.clickViewQuotes();
        System.out.println("Test 4 Passed: Details reviewed and quotes page loaded.");
    }

    @Test(priority = 5, dependsOnMethods = {"testReviewAndProceed"})
    public void testVerifyPlansAndDetails() {
        String totalPlans = carInsurancePage.getNumberOfPlans();
        System.out.println("Total Plans Available: " + totalPlans);
        carInsurancePage.clickFirstPlanDetails();
        ScreenshotUtils.takeScreenshot(driver, "testVerifyPlansAndDetails");
        System.out.println("Test 5 Passed: Plan details viewed successfully.");
    }
}