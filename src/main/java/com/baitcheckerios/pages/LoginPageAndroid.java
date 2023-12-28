package com.baitcheckerios.pages;

import java.io.IOException;
import java.util.List;

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
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class LoginPageAndroid extends BasePage<AndroidDriver<MobileElement>> implements LoginElement {
	public LoginPageAndroid(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	@FindBy(xpath = "//android.widget.EditText")
	private WebElement emailField;
	
//	@FindBy(xpath = "//android.widget.EditText[@text='Email']")
//	private WebElement emailField;

	@FindBy(xpath = "//android.widget.EditText[@text='Password']")
	private WebElement passwordField;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Log in']")
	private WebElement loginBtn;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Hello')]")
	private WebElement welcomeText;

	
	
	public void login(String email, String pass) throws Exception {
	
			MobileUtility.printLogInfo(" inside LoginPageAndroid : Login ");
			driver.activateApp("com.baitchecker");
			MobileUtility.type(emailField, email, "email", driver);
			MobileUtility.type(passwordField, pass, "pass", driver);
			MobileUtility.clickElement(loginBtn, driver, "loginBtn");
			String screenshotName="loginAndroid"+MobileUtility.getDate();
			MobileUtility.capture(driver, screenshotName);
//			MobileUtility.waitForToastMessage(driver, "Your Toast Message", 10);
//			MobileUtility.waitForElement(welcomeText, driver, "welcomeText", 10);
			boolean isWelcomeTextDisplayed = MobileUtility.isElementDisplayed(welcomeText);
			if (Boolean.TRUE.equals(isWelcomeTextDisplayed)) {
				MobileUtility.printLogInfo("User is logged in successfully");
				MyExtentListeners.test.pass(MarkupHelper.createLabel("Verify if user is logged-in || User is logged in successfully", ExtentColor.GREEN));
				MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName+".png");
				Assert.assertTrue(isWelcomeTextDisplayed);
			} else if (Boolean.FALSE.equals(isWelcomeTextDisplayed)) {
				MobileUtility.printLogInfo("User is not logged in");
				MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify if user is logged-in || User is not logged in", ExtentColor.RED));
				MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName+".png");
				Assert.fail("User is not logged in");
			}
		
	}

	
	

	
}
