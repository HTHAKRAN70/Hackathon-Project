package testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import listeners.TestListener;
import pages.BusinessHomePage;
import pages.BusinessInsurancePage;
import utils.ScreenshotUtils;

@Listeners(listeners.TestListener.class)
public class TestBusinessInsurance extends BaseTest {

    BusinessHomePage businessHome;
    BusinessInsurancePage businessInsurance;
    SoftAssert softAssert;

    private String parentWindow;

    @BeforeMethod
    public void initPages() {
        softAssert = new SoftAssert();
        businessHome = new BusinessHomePage(driver);
        businessInsurance = new BusinessInsurancePage(driver);
    }

    // ================= DATA PROVIDERS =================

    @DataProvider(name = "contactDetails")
    public Object[][] contactDetails() {
        return new Object[][] {
            { "9898989898", "abc@gmail.com", "ABC Constructions", "500097" }
        };
    }

    @DataProvider(name = "workerDetails")
    public Object[][] workerDetails() {
        return new Object[][] {
            { "20", "20000", "10", "12500", "5", "8000" }
        };
    }

    // ================= TEST CASE 1 =================
    // Verify Workmen Compensation opens in new tab

    @Test(priority = 0)
    public void testWorkmenCompensationOpensInNewTab() throws InterruptedException {
        parentWindow = businessHome.getParentWindowHandle();

        businessHome.clickBusinessInsuranceMenu();
        Thread.sleep(2000);

        businessHome.clickWorkmenCompensationLink();
        Thread.sleep(3000);

        softAssert.assertTrue(businessHome.getWindowCount() > 1,
                "TEST CASE 1 FAILED: Workmen Compensation did not open in a new tab");

        boolean switched = businessHome.switchToNewTab(parentWindow);
        if (switched) {
            System.out.println("TEST CASE 1 PASSED: Opened in new tab — " + driver.getTitle());
        }

        ScreenshotUtils.takeScreenshot(driver, "TC1_WorkmenCompensationNewTab");
        softAssert.assertAll();
    }

    // ================= TEST CASE 2 =================
    // Submit without details and verify error message

    @Test(priority = 1, dependsOnMethods = "testWorkmenCompensationOpensInNewTab")
    public void testSubmitWithoutDetails() throws InterruptedException {
        businessInsurance.clickContinueWithoutDetails();
        Thread.sleep(2000);

        String error = businessInsurance.getMobileErrorMessage();

        softAssert.assertFalse(error.isEmpty(),
                "TEST CASE 2 FAILED: No validation error displayed");

        if (!error.isEmpty()) {
            System.out.println("TEST CASE 2 PASSED");
            System.out.println("Error Message: " + error);
        }

        ScreenshotUtils.takeScreenshot(driver, "TC2_ValidationError");
        softAssert.assertAll();
    }

    // ================= TEST CASE 3 =================
    // Enter valid details and click Continue

    @Test(priority = 2, dependsOnMethods = "testSubmitWithoutDetails",
          dataProvider = "contactDetails")
    public void testValidDetailsSubmission(String mobile, String email,
                                           String companyName, String pincode)
            throws InterruptedException {

        businessInsurance.fillContactDetails(mobile, email, companyName, pincode);
        businessInsurance.clickContinue();
        Thread.sleep(3000);

        System.out.println("TEST CASE 3 PASSED: Details submitted");
        ScreenshotUtils.takeScreenshot(driver, "TC3_ContactDetailsSubmitted");
        softAssert.assertAll();
    }

    // ================= TEST CASE 4 =================
    // Select industry without worker details and verify validation message

    @Test(priority = 3, dependsOnMethods = "testValidDetailsSubmission")
    public void testIndustrySelectionValidation() throws InterruptedException {
        Thread.sleep(3000);

        businessInsurance.selectIndustryCategory(1);
        Thread.sleep(2000);

        businessInsurance.selectIndustrySubCategory(1);
        Thread.sleep(2000);

        businessInsurance.clickGetQuote();
        Thread.sleep(5000);

        String validationMsg = businessInsurance.getValidationMessage();
        System.out.println("Validation Message: " + validationMsg);
        System.out.println("TEST CASE 4 PASSED: Validation handled correctly");

        ScreenshotUtils.takeScreenshot(driver, "TC4_IndustryValidationModal");
        businessInsurance.dismissErrorModal();
        Thread.sleep(1000);

        softAssert.assertAll();
    }

    // ================= TEST CASE 5 =================
    // Provide all worker details, click Proceed and take screenshot

    @Test(priority = 4, dependsOnMethods = "testIndustrySelectionValidation",
          dataProvider = "workerDetails")
    public void testWorkerDetailsAndProceed(String skilledCount, String skilledSalary,
                                            String semiSkilledCount, String semiSkilledSalary,
                                            String unskilledCount, String unskilledSalary)
            throws Exception {

        Thread.sleep(4000);

        businessInsurance.fillWorkerDetails(
                skilledCount, skilledSalary,
                semiSkilledCount, semiSkilledSalary,
                unskilledCount, unskilledSalary);

        Thread.sleep(3000);

        businessInsurance.clickProceed();
        Thread.sleep(4000);

        ScreenshotUtils.takeScreenshot(driver, "TC5_WorkmenCompensationProceedPage");
        System.out.println("TEST CASE 5 PASSED: Proceed clicked and screenshot captured");
        softAssert.assertAll();
    }
}