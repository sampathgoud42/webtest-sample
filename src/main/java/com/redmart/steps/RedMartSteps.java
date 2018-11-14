package com.redmart.steps;


import org.junit.Assert;

import com.redmart.pages.RedMartPage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class RedMartSteps extends ScenarioSteps {

	
	private static final long serialVersionUID = 1L;
	
	RedMartPage redMartLandingPage;
	

	@Step("Launch Redmart")
	public void launchRedMart() {
		redMartLandingPage.launchRedMart();
	}
	@Step("Go To Redmart Home Page")
	public void goToHomePage() {
		redMartLandingPage.gotoHomePage();
	}
	
	@Step("Verify welcome message: {0}")
	public void verifyCompanyLogoElementAndpageTitle() {
		redMartLandingPage.verifyCompanyLogoElement();
	}
	
	@Step("Signup for new user with email {0} and password is {1}")
	public void signupNewuser(String email, String password) {
		redMartLandingPage.signupNewuser(email, password);
	}
	
	@Step("Login for new user with email {0} and password is {1}")
	public void login(String email, String password) {
		redMartLandingPage.login(email, password);
	}
	
	@Step("Verify welcome message: {0}")
	public void verifyUsernameOnLandingPage(String name) {
		redMartLandingPage.verifyUsernameOnLandingPage(name);
	}	
	
	@Step("logout and close browser")
	public void logoutAndCloseDriver() {
		redMartLandingPage.logoutAndCloseDriver();
	}
	
	
	@Step("Intentioanl failuer for reports")
	public void failThis(){
		
		Assert.assertTrue(false);
	}
	
}

