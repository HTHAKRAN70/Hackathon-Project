//package testcases;
//
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.time.Duration;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//import base.BaseTest;
//import pages.BikeHomePage;
//import pages.BikeDetailsPage;
//
//public class TestBikeInsurance extends BaseTest {
//
//    BikeHomePage bikeHome;
//    BikeDetailsPage bikeDetails;
//    SoftAssert softAssert;
//
//    @BeforeMethod
//    public void initPages() {
//        softAssert = new SoftAssert();
//        bikeHome = new BikeHomePage(driver);
//        bikeDetails = new BikeDetailsPage(driver);
//    }
//
//    @Test(priority = 0)
//    public void testNavigationToBikeSection() {
//        bikeHome.clickBikeTab();
//        
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        boolean isCorrectUrl = wait.until(ExpectedConditions.urlContains("two-wheeler-insurance"));
//        
//        softAssert.assertTrue(isCorrectUrl, "Failed to load Bike section! Current URL: " + driver.getCurrentUrl());
//        softAssert.assertAll();
//    }
//
//    @Test(priority = 1, dependsOnMethods = "testNavigationToBikeSection")
//    public void testRegistrationErrorValidation() {
//        bikeHome.enterRegNumber("TS00AS2345");
//        bikeHome.handleInitialPolicyStatus();
//        
//        String errorMssg = bikeHome.getErrorMessage();
//        softAssert.assertEquals(errorMssg, "Not a valid registration number");
//        softAssert.assertAll();
//    }
//
//    @Test(priority = 2, dependsOnMethods = "testRegistrationErrorValidation")
//    public void testVehicleManualSelection() {
//        bikeHome.clickNotSure();
//        bikeDetails.clickBikeRideHeading();
//        bikeDetails.selectBajajCT100();
//        
//        softAssert.assertTrue(driver.getPageSource().contains("registration RTO"), "Failed to reach RTO step!");
//        softAssert.assertAll();
//    }
//
//    @Test(priority = 3, dependsOnMethods = "testVehicleManualSelection")
//    public void testRTOSelectionAndPlanDetails() {
//        bikeDetails.selectRTOAndStatus();
//        
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.urlContains("results"));
//        
//        bikeDetails.clickPlanDetailsWithJS();
//
//        softAssert.assertTrue(driver.getCurrentUrl().contains("results"), "Did not successfully reach Quotes page!");
//        softAssert.assertAll();
//    }
//}
package testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.BaseTest;
import pages.BikeHomePage;
import pages.BikeDetailsPage;
@Listeners(listeners.TestListener.class)
public class TestBikeInsurance extends BaseTest {

    BikeHomePage bikeHome;
    BikeDetailsPage bikeDetails;
    SoftAssert softAssert;

    // Initialize Page Objects and SoftAssert before every test method
    @BeforeMethod
    public void initPages() {
        softAssert = new SoftAssert();
        bikeHome = new BikeHomePage(driver);
        bikeDetails = new BikeDetailsPage(driver);
    }

    // TEST 1: Verify the user can click the Bike tab and reach the correct page
    @Test(priority = 0)
    public void testNavigationToBikeSection() {
        bikeHome.clickBikeTab();
        
        // Wait for the URL to change to the two-wheeler section
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isCorrectUrl = wait.until(ExpectedConditions.urlContains("two-wheeler-insurance"));
        
        softAssert.assertTrue(isCorrectUrl, "URL did not navigate to bike-insurance! Current: " + driver.getCurrentUrl());
        
        System.out.println("Navigation successful. Current URL: " + driver.getCurrentUrl());
        softAssert.assertAll();
    }

    // TEST 2: Verify that an invalid registration number triggers the correct error message
    @Test(priority = 1, dependsOnMethods = "testNavigationToBikeSection")
    public void testRegistrationErrorValidation() {
        System.out.println("Executing: Registration Error Validation Test...");
        
        // Enter details
        bikeHome.enterRegNumber("TS00AS2345");
        bikeHome.handleInitialPolicyStatus();
        
        // Take screenshot immediately after details are entered and error appears
        utils.ScreenshotUtils.takeScreenshot(driver, "Registration_Entrance_Error");
        System.out.println("Screenshot captured after entering registration details.");
        
        String actualError = bikeHome.getErrorMessage();
        
        // Use a soft assertion to check the error message without stopping execution
        // This ensures subsequent tests (Priority 2, 3, etc.) will not be skipped
        softAssert.assertEquals(actualError, "Not a valid registration number");
        
        System.out.println("Error message validation complete. Moving to next test.");
        softAssert.assertAll();
    }

    // TEST 3: Verify user can manually select bike details (Brand/Model) by clicking "Not Sure"
    @Test(priority = 2, dependsOnMethods = "testRegistrationErrorValidation")
    public void testVehicleManualSelection() {
    	System.out.println("Executing: Manual Vehicle Selection Test...");
        bikeHome.clickNotSure();
        bikeDetails.clickBikeRideHeading();
        bikeDetails.selectBajajCT100();
        
        // Verify the flow moves to the RTO selection screen
        softAssert.assertTrue(driver.getPageSource().contains("registration RTO"), "User failed to reach RTO selection!");
        softAssert.assertAll();
    }

    // TEST 4: Verify that providing an RTO loads the results page with at least one plan
    @Test(priority = 3, dependsOnMethods = "testVehicleManualSelection")
    public void testResultsPageAndPlanCount() {
    	System.out.println("Executing: Results Page and Plan Count Validation Test...");
        bikeDetails.selectRTOAndStatus();
        
        // Wait for the results page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean isResultsLoaded = wait.until(ExpectedConditions.urlContains("results"));
        
        softAssert.assertTrue(isResultsLoaded, "Results page URL mismatch!");
        // Ensure that actual insurance plans are appearing on the screen
        softAssert.assertTrue(bikeDetails.getPlanCount() > 0, "No insurance plans found on results page!");
        softAssert.assertAll();
    }

    // TEST 5: Verify the user can click "Plan Details" to see more information
    @Test(priority = 4, dependsOnMethods = "testResultsPageAndPlanCount")
    public void testTriggerPlanDetailsOverlay() {
    	System.out.println("Executing: Trigger Plan Details Overlay Test...");
        bikeDetails.clickPlanDetailsWithJS();
         
        // Confirm the user is still on the results page after viewing details
        softAssert.assertTrue(driver.getCurrentUrl().contains("results"), "Lost results page context!");
        softAssert.assertAll();
    }
}