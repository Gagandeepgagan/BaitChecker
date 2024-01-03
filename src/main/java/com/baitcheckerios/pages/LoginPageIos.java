package com.baitcheckerios.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.library.GenericLib;
import com.baitcheckerios.listener.MyExtentListeners;
import com.baitcheckerios.util.MobileUtility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class LoginPageIos extends BasePage<IOSDriver<MobileElement>> implements LoginElement {

	public LoginPageIos(IOSDriver<MobileElement> driver) {
		super(driver);
	}

	@FindBy(xpath="//XCUIElementTypeTextField")
	private WebElement emailField;

	@FindBy(xpath="//XCUIElementTypeSecureTextField")
	private WebElement passwordField;

	@FindBy(xpath="//XCUIElementTypeOther[@name='Log in']")
	private WebElement loginBtn;
	
	/*
	 * @iOSXCUITFindBy(
	 * iOSNsPredicate="type == 'XCUIElementTypeStaticText' AND value BEGINSWITH 'Hello'"
	 * ) private WebElement welcomeText;
	 */
	@FindBy(xpath="//XCUIElementTypeStaticText[@name='This is what’s up today:']")
	private WebElement welcomeText;
	
	
	

	
	public void login(String email, String pass) throws Exception {
		
		MobileUtility.printLogInfo(" inside LoginPageIos : Login ");
		driver.activateApp("com.baitchecker1");
		MobileUtility.type(emailField, email+Keys.ENTER, "email", driver);
		MobileUtility.type(passwordField, pass+Keys.ENTER, "pass", driver);
		MobileUtility.clickElement(loginBtn, driver, "loginBtn");
//		MobileUtility.waitForElement(welcomeText, driver, "welcomeText", 10);
	
		
		String screenshotName="loginIos"+MobileUtility.getDate();
		MobileUtility.capture(driver, screenshotName);
		boolean isWelcomeTextDisplayed = MobileUtility.isEleDisplayed(welcomeText, 2, 3, "welcomeText");
		MobileUtility.printLogInfo(String.valueOf(isWelcomeTextDisplayed));
		if (Boolean.TRUE.equals(isWelcomeTextDisplayed)) {
			MobileUtility.printLogInfo("User is logged in successfully");
			MyExtentListeners.test.pass(MarkupHelper
					.createLabel("Verify if user is logged-in || User is logged in successfully", ExtentColor.GREEN));
			MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName+".png");
			Assert.assertTrue(isWelcomeTextDisplayed);
		} else if (Boolean.FALSE.equals(isWelcomeTextDisplayed)) {
			MobileUtility.printLogInfo("User is not logged in");
			MyExtentListeners.test.fail(
					MarkupHelper.createLabel("Verify if user is logged-in || User is not logged in", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName+".png");
			Assert.assertTrue(isWelcomeTextDisplayed);
		}
		
	}

	

	

	
}
