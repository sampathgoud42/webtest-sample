package com.redmart.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://redmart.com")
public class RedMartPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(RedMartPage.class);
	 private Robot robot;
	 
	@Managed(driver="chrome")
	WebDriver driver;

	public RedMartPage(final WebDriver driver) {
		super(driver);
		this.driver=driver;

	}
	
	//Landing Page Title
	String landpagetitle="Online Grocery Shopping - Groceries Delivery Singapore | RedMart";
	String errPasswordsNotMatch="The passwords do not match.";

	//Landing Page Elements
	
	@FindBy(id="Layer_1")  //svg.iconLogo
	WebElement orgLogo;
	
	@FindBy(id="NAVBAR_SIGNUP_BTN")
	WebElement signup;

	@FindBy(id="NAVBAR_SIGNIN_BTN")
	WebElement login;
	
	@FindBy(id="NAVBAR_SIGNOUT_BTN")
	WebElement logoutButton;
	
	@FindBy(id="cartPreviewInner")
	WebElement cart;
	
	@FindBy(xpath="//input[@placeholder='Email Address']")
	WebElement emailInput;
	
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement pwdInput;
	
	@FindBy(xpath="//input[@placeholder='Confirm Password']")
	WebElement confirmPwdInput;
	
	@FindBy(css="div.Auth__signup_button___1PuOd > div") //(xpath="//div[contains(text(),'Sign up')]")
	WebElement signupButton;

	@FindBy(xpath="	//p[@class='Input__input_error_message___18S36']")
	WebElement  pwdNotMatch;
		
	@FindBy	(css="div.Button__button___1-iVe.Button__primary___1R0WH")	//(xpath="//div[contains(text(),'Log In')]")
	WebElement logInButton;
	
	
	@FindBy(id="accountPreviewInner")
	WebElement accoutPreviewLink;
	
	
	String sectionHref="//section/div/a[@href='/textToReplace']";
	String marketPlaceStore="//div[@class='productShelf']//h2[contains(text(),'textToReplace')]/preceding::a";
	
	public void launchRedMart(){
		driver.get("https://redmart.com");
		driver.manage().window().maximize();
//		Dimension d = new Dimension(800,480);
//		driver.manage().window().setSize(d);
	}
	
	public void gotoHomePage() {
		driver.switchTo().defaultContent();
		element(orgLogo).waitUntilVisible();
		orgLogo.click();
	}
	
	public void verifyCompanyLogoElement(){
		waitForAngularRequestsToFinish();
		element(orgLogo).waitUntilVisible();
		driver.getTitle().equalsIgnoreCase(landpagetitle);
	}

	public void signupNewuser(String email, String password) {
		element(signup).waitUntilClickable();
		signup.click();
		element(emailInput).waitUntilVisible();
		emailInput.clear();
		emailInput.sendKeys(email);
		pwdInput.clear();
		pwdInput.sendKeys(password);
		confirmPwdInput.clear();
		confirmPwdInput.sendKeys("hjgadhgasdhasggf");
		element(signupButton).waitUntilClickable();
		signupButton.click();
		
		boolean isMatch=false;
		element(pwdNotMatch).waitUntilPresent();
		
		if(element(pwdNotMatch).getText().equals(errPasswordsNotMatch))
			isMatch=true;
		Assert.assertTrue("The incorrect password message is not matched with expected message"+errPasswordsNotMatch, isMatch);
		confirmPwdInput.clear();
		confirmPwdInput.sendKeys(password);
		element(signupButton).waitUntilClickable();
		signupButton.click();
		element(accoutPreviewLink).waitUntilClickable();
	}
	
	public void verifyUsernameOnLandingPage(String username) {
		driver.navigate().refresh();
		element(accoutPreviewLink).waitUntilVisible();
		Assert.assertTrue("The account is not available "+username, accoutPreviewLink.isEnabled());
	}
	
	public void login(String email, String password) {
		element(login).waitUntilVisible();
		login.click();
		element(emailInput).waitUntilVisible();
		emailInput.clear();
		emailInput.sendKeys(email);
		pwdInput.clear();
		pwdInput.sendKeys(password);
		element(logInButton).waitUntilClickable();
		logInButton.click();		
	}

	
	public void logoutAndCloseDriver() {
		Actions builder = new Actions(driver);
		builder.moveToElement(accoutPreviewLink).build().perform();
		logoutButton.click();
		driver.close();
		
	}
	
	
	/* --- General Methods	----------	*/
	
	public String generateRandomEmail() {
		
		Random rand = new Random();
		int  n = rand.nextInt(50000) + 1;
		String s1="Redmartdummy"+n+"@gmail.com";
		return s1;
		
	}


	private static boolean isElementPresentAndDisplay(WebDriver driver, By by) {

		try {               
			return driver.findElement(by).isDisplayed();
		} catch (StaleElementReferenceException e) {
			return false;
		}

	}
	
	public boolean clickStaleElement(String xpathS, WebElement elem) {
	    boolean result = false;
	    int attempts = 0;
	    Actions builder = new Actions(driver);
	    while(attempts < 10) {
	        try {
	        	new WebDriverWait(driver, 10).
	        	ignoring(StaleElementReferenceException.class).
	        	until(ExpectedConditions.elementToBeClickable(By.xpath(xpathS)));
	        	builder.moveToElement(elem).click().build().perform();
	            result = true;
	            break;
	        } catch(Exception e) {

	        	builder.moveToElement(elem).click().build().perform();
	        }
	        
	        attempts++;
	    }
	    return result;
	}
	
	public boolean waitAndClickElement(WebElement elem) {
	    boolean result = false;
	    int attempts = 0;
	    Actions builder = new Actions(driver);
	    while(attempts < 5) {
	        try {
	        	Thread.sleep(1000);
	        	elem.click();
	            result = true;
	            break;
	        } catch(Exception e) {
	        }
	        waitABit(500);
	        attempts++;
	    }
	    return result;
	}

	
	public void sendKeysCustom(final WebElement element, String s){
		
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().sendKeys(s).build().perform();
		

	}
	
	   public void jsRequestsToFinish() {
		   waitForAngularRequestsToFinish();
	        if ((boolean) getJavascriptExecutorFacade().executeScript(
	                "return (typeof angular !== 'undefined')? true : false;")) {
	            getJavascriptExecutorFacade()
	                    .executeAsyncScript(
	                            "var callback = arguments[arguments.length - 1];"
	                                    + "angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
	        }
	    }

	   
	   public void waitForListToLoad(List<WebElement> list, int timeoutSeconds,
				boolean assertListNotEmpty) {
		   jsRequestsToFinish();
			int counter = 0;
			while ((list.size() == 0) && (counter < timeoutSeconds)) {
				waitABit(1000L);
				++counter;
			}
			if (assertListNotEmpty)
				Assert.assertTrue("List is empty", list.size() > 0);
		}


}