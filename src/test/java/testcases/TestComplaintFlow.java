package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ContactUsPage;
import pages.ComplaintPage;
import utils.ExcelUtility;
import utils.ScreenshotUtils;

public class TestComplaintFlow extends BaseTest {
    String excelPath = System.getProperty("user.dir")
            + "/src/main/resources/data/testdata.xlsx";
    public ComplaintPage goToComplaintPage() {
        ContactUsPage contactPage = new ContactUsPage(driver);
        contactPage.clickContactUs();
        contactPage.clickFileComplaint();
        return new ComplaintPage(driver);
    }
    @Test(priority = 1)
    public void validateContactUsPage() {
        ContactUsPage contact = new ContactUsPage(driver);
        contact.clickContactUs();
        String url = contact.getPageUrl();
        ScreenshotUtils.takeScreenshot(driver, "TC1");
        Assert.assertTrue(url.contains("contact"));
        System.out.println("Contact page validated: " + url);
    }
    @Test(priority = 2, dataProvider = "invalidPhoneData")
    public void invalidPhoneNumberTest(String name, String email, String policy, String complaint, String mobile) {
        ComplaintPage page = goToComplaintPage();
        page.fillForm(name, email, policy, complaint, mobile);
        page.clickSubmit();
        String error = page.getPhoneErrorMessage();
        Assert.assertTrue(error.contains("mobile"));
        ScreenshotUtils.takeScreenshot(driver, "TC2");
        System.out.println("Invalid phone validation: " + error);
    }
    @Test(priority = 3, dataProvider = "missingPolicyData")
    public void missingPolicyIdTest(String name, String email, String policy, String complaint, String mobile) {
        ComplaintPage page = goToComplaintPage();
        page.fillForm(name, email, policy, complaint, mobile);
        page.clickSubmit();
        String success = page.getSuccessMessage();
        Assert.assertTrue(success.contains("THANK YOU"));
        ScreenshotUtils.takeScreenshot(driver, "TC3");
        System.out.println("Missing policy validation: " + success);
    }
    @Test(priority = 4, dataProvider = "successData")
    public void successfulSubmissionTest(String name, String email, String policy, String complaint, String mobile) {
        ComplaintPage page = goToComplaintPage();
        page.fillForm(name, email, policy, complaint, mobile);
        page.clickSubmit();
        String success = page.getSuccessMessage();
        Assert.assertTrue(success.contains("THANK YOU"));
        ScreenshotUtils.takeScreenshot(driver, "TC4");
        System.out.println("Success case: " + success);
    }
    @Test(priority = 5, dataProvider = "invalidEmailData")
    public void invalidEmailTest(String name, String email, String policy, String complaint, String mobile) {
        ComplaintPage page = goToComplaintPage();
        page.fillForm(name, email, policy, complaint, mobile);
        page.clickSubmit();
        String error = page.getEmailErrorMessage();
        Assert.assertTrue(error.contains("email"));
        ScreenshotUtils.takeScreenshot(driver, "TC5");
        System.out.println("Email validation: " + error);
    }
    @DataProvider(name = "invalidPhoneData")
    public Object[][] invalidPhoneData() {
        return ExcelUtility.getFilteredData(excelPath, "ComplaintData", "invalid_phone");
    }
    @DataProvider(name = "missingPolicyData")
    public Object[][] missingPolicyData() {
        return ExcelUtility.getFilteredData(excelPath, "ComplaintData", "missing_policy");
    }
    @DataProvider(name = "successData")
    public Object[][] successData() {
        return ExcelUtility.getFilteredData(excelPath, "ComplaintData", "success");
    }
    @DataProvider(name = "invalidEmailData")
    public Object[][] invalidEmailData() {
        return ExcelUtility.getFilteredData(excelPath, "ComplaintData", "invalid_email");
    }
}
