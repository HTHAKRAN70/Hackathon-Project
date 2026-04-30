package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
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
        age=new AgePage(driver);
        address=new AddressPage(driver);
        result=new HealthInsuranceResultPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

	@Test
	public void testHealthInsurance() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[normalize-space()='Best Health Insurance plans. Customized for you.']")));
		home.getGenderButton("Male").click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		member.SelectMembers(true,true,false,true,true,true,3,0);
		member.getNextButton().click();
		age.SelectAge("25","25","0","4m,4y,4m","70","70");
		age.getNextButton().click();
		Thread.sleep(2000);
		address.getYouPinCodeInput().sendKeys("251201");
		address.getParentPinCodeInput().sendKeys("251201");
		address.getMobileNumberInput().sendKeys("7017583580");
	    address.getContinueButton().click();
	    int noOfResults=result.getNumberOfResults();
	    System.out.println(noOfResults);
	    List<List<String>> allPlans = result.fetchAllPlanDetails();
		 for (int i = 0; i < allPlans.size(); i++) {
		     List<String> plan = allPlans.get(i);
		     System.out.println("Insurer      : " + plan.get(0));
		     System.out.println("Plan Name    : " + plan.get(1));
		     System.out.println("Price / Year : " + plan.get(2));
		     System.out.println("Sum Assured  : " + plan.get(3));
		 }
	}
	@Test
	public void testCoupleChildrenAgeGapLess18() throws InterruptedException {
		
		home.getGenderButton("Male").click();
		try {
			Thread.sleep(4000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		member.SelectMembers(true,true,false,true,true,true,3,0);
		member.getNextButton().click();
		age.SelectAge("18","18","0","11m,11m,4y","35","35");
		age.getNextButton().click();
		String errorMssg=age.getErrorMessage();
		softAssert.assertEquals(errorMssg,"Your kid has to be atleast 18 years younger than you and spouse");	
		softAssert.assertAll();
	}
	@Test
	public void testeditmemberdetailsbutton() throws InterruptedException {
		
		home.getGenderButton("Male").click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		member.SelectMembers(true,true,false,true,true,true,3,0);
		member.getNextButton().click();
		age.SelectAge("25","25","0","4m,4y,4m","70","70");
		age.getNextButton().click();
		Thread.sleep(2000);
		address.getYouPinCodeInput().sendKeys("251201");
		address.getParentPinCodeInput().sendKeys("251201");
		address.getMobileNumberInput().sendKeys("7017583580");
	    address.getContinueButton().click();
	    result.getViewMemberDetailsButton().click();
	    result.getEditDetailsButton().click();
	    String currUrl=driver.getCurrentUrl();
	    softAssert.assertEquals(currUrl,"https://www.coverfox.com/health-plan/");
	    softAssert.assertAll();
		
	}
	
	@Test
	public void testwithoutPinCodePhoneNumber() throws InterruptedException {
		home.getGenderButton("Male").click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		member.SelectMembers(true,true,false,true,true,true,3,0);
		member.getNextButton().click();
		age.SelectAge("25","25","0","4m,4y,4m","70","70");
		age.getNextButton().click();
		Thread.sleep(2000);
		address.getYouPinCodeInput().sendKeys("251201");
		address.getParentPinCodeInput().sendKeys("");
		address.getMobileNumberInput().sendKeys("7017583580");
	    address.getContinueButton().click();
	    String erroMssg= address.getErrorMssg();
	    softAssert.assertEquals(erroMssg,"Please enter a valid pincode");
	    softAssert.assertAll();
	   
	}
	
	@Test
	public void testWithoutSelectingAnyMember() throws InterruptedException {
		home.getGenderButton("Male").click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		member.SelectMembers(false,false,false,false,false,false,3,0);
		member.getNextButton().click();
		String errorMssg=member.getErrorMessage();
		softAssert.assertEquals(errorMssg,"* Select one or more members to continue");
	    softAssert.assertAll();
	}
	
}
