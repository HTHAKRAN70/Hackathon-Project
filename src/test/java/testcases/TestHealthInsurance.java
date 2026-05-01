package testcases;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.BaseTest;
import pages.AddressPage;
import pages.AgePage;
import pages.HealthInsuranceResultPage;
import pages.HomePage;
import pages.MembersPage;
public class TestHealthInsurance extends BaseTest {

    HomePage home;
    MembersPage member;
    AddressPage address;
    AgePage age;
    HealthInsuranceResultPage result;
    WebDriverWait wait;
    SoftAssert softAssert;

    @BeforeMethod
    public void initPages() {
        softAssert = new SoftAssert();
        home = new HomePage(driver);
        member = new MembersPage(driver);
        age = new AgePage(driver);
        address = new AddressPage(driver);
        result = new HealthInsuranceResultPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    
    @DataProvider(name = "validInsuranceData")
    public Object[][] validInsuranceData() {
        return new Object[][] {
            {"25","25","0","4m,4y,4m","70","70","251201","251201","7017583580"}
        };
    }

    @DataProvider(name = "ageGapInvalidData")
    public Object[][] ageGapInvalidData() {
        return new Object[][] {
            {"18","18","0","11m,11m,4y","35","35",
             "Your kid has to be atleast 18 years younger than you and spouse"}
        };
    }

    @DataProvider(name = "editMemberData")
    public Object[][] editMemberData() {
        return new Object[][] {
            {"25","25","0","4m,4y,4m","70","70","251201","251201","7017583580",
             "https://www.coverfox.com/health-plan/"}
        };
    }

    @DataProvider(name = "invalidPincodeData")
    public Object[][] invalidPincodeData() {
        return new Object[][] {
            {"25","25","0","4m,4y,4m","70","70","251201","", "7017583580",
             "Please enter a valid pincode"}
        };
    }

    @DataProvider(name = "noMemberSelectedData")
    public Object[][] noMemberSelectedData() {
        return new Object[][] {
            {"* Select one or more members to continue"}
        };
    }

    @Test(dataProvider = "noMemberSelectedData" , priority=0)
    public void testWithoutSelectingAnyMember(String expectedError) {
    	home.selectGender("Male");
    	member.selectMembers(false, false, false, false, false, false, 0, 0);
        member.getNextButton().click();
        String mssg=member.getErrorMessage();
        softAssert.assertEquals(mssg, expectedError);
        softAssert.assertAll();
    }
    
    @Test(priority=1,dependsOnMethods ="testWithoutSelectingAnyMember",dataProvider = "ageGapInvalidData")
    public void testCoupleChildrenAgeGapLess18(
            String yourAge, String spouseAge, String daughterAge,
            String sonAges, String fatherAge, String motherAge,
            String expectedError) throws InterruptedException {
    	Thread.sleep(2000);
        member.selectMembers(true, true, false, true, true, true, 3, 0);
        member.getNextButton().click();
        age.selectAge(yourAge, spouseAge, daughterAge, sonAges, fatherAge, motherAge);
        age.clickNext();
        softAssert.assertEquals(age.getErrorMessage(), expectedError);
        softAssert.assertAll();
    }
    
    @Test(priority=2,dependsOnMethods="testCoupleChildrenAgeGapLess18",dataProvider = "invalidPincodeData")
    public void testWithoutPinCode(
            String yourAge, String spouseAge, String daughterAge,
            String sonAges, String fatherAge, String motherAge,
            String youPin, String parentPin, String mobile,
            String expectedError) {	
        age.selectAge(yourAge, spouseAge, daughterAge, sonAges, fatherAge, motherAge);
        age.clickNext();
        address.enterAddressDetails(youPin, parentPin, mobile);
        softAssert.assertEquals(address.getErrorMessage(), expectedError);
        softAssert.assertAll();
    }
    
    @Test(priority=3,dependsOnMethods="testWithoutPinCode",dataProvider = "editMemberData")
    public void testEditMemberDetailsButton(
            String yourAge, String spouseAge, String daughterAge,
            String sonAges, String fatherAge, String motherAge,
            String youPin, String parentPin, String mobile,
            String expectedUrl) {
        address.enterAddressDetails(youPin, parentPin, mobile);
        result.getViewMemberDetailsButton().click();
        result.getEditDetailsButton().click();
        softAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        softAssert.assertAll();
    }
    
    @Test(priority=4,dependsOnMethods="testEditMemberDetailsButton",dataProvider = "validInsuranceData")
    public void testHealthInsurance(
            String yourAge, String spouseAge, String daughterAge,
            String sonAges, String fatherAge, String motherAge,
            String youPin, String parentPin, String mobile) throws InterruptedException {
        member.getNextButton().click();
        age.selectAge(yourAge, spouseAge, daughterAge, sonAges, fatherAge, motherAge);
        age.clickNext();
        address.enterAddressDetails(youPin, parentPin, mobile);
        int resultCount = result.getNumberOfResults();
        softAssert.assertTrue(resultCount > 0, "No insurance plans found");
        List<List<String>> allPlans = result.fetchAllPlanDetails();
        softAssert.assertTrue(allPlans.size() > 0, "Plans list is empty");
        softAssert.assertAll();
    }

    

    

    
    
}