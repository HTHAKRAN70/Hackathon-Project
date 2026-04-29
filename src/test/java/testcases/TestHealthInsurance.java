package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    @BeforeMethod
    public void initPages() {
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
		age.SelectAge("25","25","0","4,4,4","70","70");
		age.getNextButton().click();
		Thread.sleep(4000);
		address.getYouPinCodeInput().sendKeys("251201");
		address.getParentPinCodeInput().sendKeys("251201");
		address.getMobileNumberInput().sendKeys("7017583580");
	    address.getContinueButton().click();
	    int noOfResults=result.getNumberOfResults();
	    System.out.println(noOfResults);
	    List<List<String>> allPlans = result.fetchAllPlanDetails();

	 // Example iteration
	 for (int i = 0; i < allPlans.size(); i++) {

	     List<String> plan = allPlans.get(i);

	     System.out.println("Plan Index: " + i);
	     System.out.println("Insurer      : " + plan.get(0));
	     System.out.println("Plan Name    : " + plan.get(1));
	     System.out.println("Price / Year : " + plan.get(2));
	     System.out.println("Sum Assured  : " + plan.get(3));
	 }
	    
	    Thread.sleep(10000);
	}
	
}
