package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ContactUsPage;
import pages.ComplaintPage;
import utils.ScreenshotUtils;

public class TestComplaintFlow extends BaseTest {
    // Reusable navigation method
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
        System.out.println("Url: " + url);
        ScreenshotUtils.takeScreenshot(driver, "Image1");
        Assert.assertTrue(url.contains("https://www.coverfox.com/contact/"));
    }
    @Test(priority = 2)
    public void invalidPhoneNumberTest() {
        ComplaintPage page = goToComplaintPage();
        page.fillForm("TestUser", "test@gmail.com", "00010","Ds","123");
        page.clickSubmit();
        String actualError = page.getPhoneErrorMessage();
        String expectedError = "We can’t proceed without your mobile number or invalid mobile number. Help us?";
        Assert.assertEquals(actualError.trim(), expectedError);
        ScreenshotUtils.takeScreenshot(driver, "Image2");
        System.out.println("Validation Passed - Invalid Phone Error Displayed"+actualError);

    }
    @Test(priority = 3)
    public void missingPolicyIdTest() {
        ComplaintPage page = goToComplaintPage();
        page.fillForm("TestUser", "test@gmail.com", "","Ds","9876543210");
        page.clickSubmit();
        String successMsg = page.getSuccessMessage();
        Assert.assertTrue(
                successMsg.contains("THANK YOU"),
                "Success message not displayed correctly"
        );
        ScreenshotUtils.takeScreenshot(driver, "Image3");
        System.out.println("Form submitted successfully without Policy Id"+successMsg);
    }
    @Test(priority = 4)
    public void successfulSubmissionTest() {

        ComplaintPage page = goToComplaintPage();

        page.fillForm("TestUser", "test@gmail.com", "00001","Ds","9876543210");
        page.clickSubmit();
        String successMsg = page.getSuccessMessage();
        Assert.assertTrue(
                successMsg.contains("THANK YOU"),
                "Success message not displayed correctly"
        );
        ScreenshotUtils.takeScreenshot(driver, "Image4");
        System.out.println("Form submitted successfully"+successMsg);
    }
    @Test(priority = 5)
    public void invalidEmailTest() {

        ComplaintPage page = goToComplaintPage();
        page.fillForm("TestUser", "test", "00010","Ds","9876543210");
        page.clickSubmit();
        String actualError = page.getEmailErrorMessage();
        String expectedText = "We can’t proceed without your email address or invalid email address";
        Assert.assertTrue(
                actualError.contains(expectedText),
                "Email validation message not displayed correctly"
        );
        ScreenshotUtils.takeScreenshot(driver, "Image5");
        System.out.println("Invalid email validation passed: "+actualError);

    }
}