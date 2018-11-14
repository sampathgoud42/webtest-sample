package com.redmart.tests;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.redmart.steps.RedMartSteps;
import com.redmart.tools.RedMart;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
//import net.serenitybdd.junit.runners.SeresnityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Story(RedMart.LandingPage.Login.class)
@UseTestDataFrom(value="Test01_assignment2.csv", separator=',')
@RunWith(SerenityParameterizedRunner.class)
public class Test002_Redmart_Login{
	
	String email,password,name,firstName,lastName,postalCode;
	@Managed (driver="chrome")
	WebDriver driver;
	
	@Steps 
	RedMartSteps redMartSteps;
	
	
	@Test
	@Pending
	@Title("Test002 - Login")
	public void test002_Login(){
		redMartSteps.launchRedMart();
	}
	
	@After
	public void logout() {
		redMartSteps.logoutAndCloseDriver();
	}
	
}
