package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utils.ExcelUtility;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getExtent();
    private static ExtentTest test;
    private ExcelUtility ex;

    @Override
    public void onStart(ITestContext context) {
        try {
            ex = new ExcelUtility();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest base = (BaseTest) result.getInstance();
        write(result.getName(), "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest base = (BaseTest) result.getInstance();

        String screenshotPath =
                ScreenshotUtils.takeScreenshot(
                        base.driver,
                        result.getMethod().getMethodName() + "_FAIL"
                );

        test.fail("❌ Test Failed")
            .addScreenCaptureFromPath(screenshotPath);

        write(result.getName(), "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("⚠️ Test Skipped");
        write(result.getName(), "SKIP");
    }

    private void write(String testName, String status) {
        try {
            ex.write(testName, status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            ex.closeExcelFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        extent.flush();
    }
}