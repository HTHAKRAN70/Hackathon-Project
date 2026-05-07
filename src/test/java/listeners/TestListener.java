//package listeners;
//
//import org.testng.*;
//import com.aventstack.extentreports.*;
//
//import base.BaseTest;
//import utils.ExtentManager;
//import utils.ScreenshotUtils;
//
//public class TestListener implements ITestListener {
//
//    private static ExtentReports extent = ExtentManager.getExtent();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest extentTest =
//            extent.createTest(result.getMethod().getMethodName());
//        test.set(extentTest);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//
//        BaseTest base = (BaseTest) result.getInstance();
//
//        String screenshotPath =
//            ScreenshotUtils.takeScreenshot(
//                base.driver,
//                result.getMethod().getMethodName() + "_PASS");
//
//        test.get().pass("✅ Test Passed");
//
//        if (screenshotPath != null) {
//            test.get().addScreenCaptureFromPath(screenshotPath);
//        }
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//        BaseTest base = (BaseTest) result.getInstance();
//
//        String screenshotPath =
//            ScreenshotUtils.takeScreenshot(
//                base.driver,
//                result.getMethod().getMethodName() + "_FAIL");
//
//        test.get().fail(result.getThrowable());
//
//        if (screenshotPath != null) {
//            test.get().addScreenCaptureFromPath(screenshotPath);
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        test.get().skip("⚠️ Test Skipped");
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        extent.flush();
//    }
//}


package listeners;

import org.testng.*;
import com.aventstack.extentreports.*;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getExtent();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Maps each test method name to a human-readable description and TC label
    private String getTestDescription(String methodName) {
        switch (methodName) {
            case "testWorkmenCompensationOpensInNewTab":
                return "TC1 — Verify Workmen Compensation link opens in a new tab";
            case "testSubmitWithoutDetails":
                return "TC2 — Submit form without details and verify validation error";
            case "testValidDetailsSubmission":
                return "TC3 — Enter valid contact details and click Continue";
            case "testIndustrySelectionValidation":
                return "TC4 — Select industry without worker details and verify modal validation";
            case "testWorkerDetailsAndProceed":
                return "TC5 — Fill all worker details, click Proceed and capture screenshot";
            default:
                return methodName;
        }
    }

    private String getPassMessage(String methodName) {
        switch (methodName) {
            case "testWorkmenCompensationOpensInNewTab":
                return "New tab opened successfully with Workmen Compensation page loaded";
            case "testSubmitWithoutDetails":
                return "Validation error displayed as expected when form submitted without details";
            case "testValidDetailsSubmission":
                return "Valid contact and company details submitted successfully — form advanced to next step";
            case "testIndustrySelectionValidation":
                return "Bootstrap modal appeared with validation message after submitting without worker details";
            case "testWorkerDetailsAndProceed":
                return "All worker categories filled (Skilled / Semi-skilled / Unskilled) and Proceed clicked — quote page loaded";
            default:
                return "Test Passed";
        }
    }

    private String getTcScreenshotName(String methodName) {
        switch (methodName) {
            case "testWorkmenCompensationOpensInNewTab": return "TC1_WorkmenCompensationNewTab";
            case "testSubmitWithoutDetails":             return "TC2_ValidationError";
            case "testValidDetailsSubmission":           return "TC3_ContactDetailsSubmitted";
            case "testIndustrySelectionValidation":      return "TC4_IndustryValidationModal";
            case "testWorkerDetailsAndProceed":          return "TC5_WorkmenCompensationProceedPage";
            default:                                     return null;
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(getTestDescription(methodName));
        extentTest.info("Test started: <b>" + methodName + "</b>");
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest extentTest = test.get();

        extentTest.pass(getPassMessage(methodName));

        // Attach the TC-named screenshot taken inside the test method
        String tcShot = getTcScreenshotName(methodName);
        if (tcShot != null) {
            java.io.File f = new java.io.File("screenshots/" + tcShot + ".png");
            if (f.exists()) {
                try { extentTest.addScreenCaptureFromPath(f.getAbsolutePath(), tcShot); }
                catch (Exception e) { e.printStackTrace(); }
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest extentTest = test.get();

        extentTest.fail("❌ Test Failed: <b>" + methodName + "</b>");
        extentTest.fail(result.getThrowable());

        // Take a fresh failure screenshot and attach it
        BaseTest base = (BaseTest) result.getInstance();
        String screenshotPath = ScreenshotUtils.takeScreenshot(
                base.driver, methodName + "_FAIL");
        if (screenshotPath != null) {
            java.io.File f = new java.io.File(screenshotPath);
            try { extentTest.addScreenCaptureFromPath(f.getAbsolutePath(), "Failure Screenshot"); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⚠️ Test Skipped — depends on a previously failed test: "
                + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}