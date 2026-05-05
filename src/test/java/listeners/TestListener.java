package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtils;
public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getExtent();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
            extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        BaseTest base = (BaseTest) result.getInstance();

        String screenshotPath =
            ScreenshotUtils.takeScreenshot(
                base.driver,
                result.getMethod().getMethodName() + "_PASS");

        test.get().pass("✅ Test Passed");

        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        BaseTest base = (BaseTest) result.getInstance();

        String screenshotPath =
            ScreenshotUtils.takeScreenshot(
                base.driver,
                result.getMethod().getMethodName() + "_FAIL");

        test.get().fail(result.getThrowable());

        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}