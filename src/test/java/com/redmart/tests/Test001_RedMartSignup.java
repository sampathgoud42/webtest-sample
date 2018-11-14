package com.redmart.tests;

import java.util.Random;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.redmart.steps.RedMartSteps;
import com.redmart.tools.RedMart;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.serenitybdd.junit.runners.SeresnityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Story(RedMart.LandingPage.SignUp.class)
@UseTestDataFrom(value="Test01_assignment2.csv", separator=',')
@RunWith(SerenityParameterizedRunner.class)
public class Test001_RedMartSignup{
	
	String email,password,name,firstName,lastName,postalCode;
	@Managed (driver="chrome")
	WebDriver driver;
	
	@Steps 
	RedMartSteps redMartSteps;
	
	
	@Test
	@Title("Test001 - Redmart - Signup ")
	public void test001_RedMartSignup(){
		redMartSteps.launchRedMart();
		redMartSteps.verifyCompanyLogoElementAndpageTitle();
		Random rand = new Random();
		int  n = rand.nextInt(50000) + 1;
		redMartSteps.signupNewuser(email+n+"@gmail.com", password);
		redMartSteps.verifyUsernameOnLandingPage(name);
	}
	
	@After
	public void logout() {
		redMartSteps.logoutAndCloseDriver();
	}
		

	
}
