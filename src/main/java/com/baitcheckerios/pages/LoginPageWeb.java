package com.baitcheckerios.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.library.GenericLib;
import com.baitcheckerios.listener.MyExtentListeners;
import com.baitcheckerios.util.MobileUtility;

public class LoginPageWeb extends BasePage<WebDriver> implements LoginElement {

	public LoginPageWeb(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='Email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordField;

	@FindBy(xpath = "//*[contains(text(),'Log in')]")
	private WebElement loginBtn;

	@FindBy(css = "div.css-175oi2r.r-13awgt0.r-j2kj52>div")
	private WebElement errorMsg;

	@FindBy(css = " div.css-146c3p1.r-13tplrl.r-17ul5sj.r-1b43r93.r-knv0ih")
	private WebElement validationMsg;

	public void login(String email, String pass) throws Exception {
		MobileUtility.printLogInfo(" inside LoginPageWeb : Login");
		driver.get("https://bait-staging-web.vtnetzwelt.com/login");
		MobileUtility.waitForElementToLoad(4000);
		MobileUtility.type(emailField, email, "email", driver);
		MobileUtility.typeEncrypted(passwordField, pass, "pass", driver);
		MobileUtility.clickElement(loginBtn, driver, "loginBtn");
		String screenshotName="login"+MobileUtility.getDate();
		MobileUtility.capture(driver, screenshotName);
		String errMsg = MobileUtility.getFlashMsgText(driver, errorMsg);
		String validateMsg = MobileUtility.getFlashMsgText(driver, validationMsg);
		
		
			if (validateMsg != null && !validateMsg.isEmpty()) {
				MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify user is able to Login"
						+ " || User is not able to Login : Validation Message - " + "\'" + validateMsg + "\'",
						ExtentColor.RED));
				MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName+".png");
				Assert.fail("Login failed: " + validateMsg);
			} else if (errMsg != null && !errMsg.isEmpty()) {
				MyExtentListeners.test.fail(MarkupHelper.createLabel(
						"Verify user is able to Login" + " || User is not able to Login : " + "\'" + errMsg + "\'",
						ExtentColor.RED));
				MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName+".png");
				Assert.fail("Login failed: " + errMsg);
			} else {
				Assert.assertEquals(driver.getCurrentUrl(), "https://bait-staging-web.vtnetzwelt.com/dashboard");
				MyExtentListeners.test.pass(MarkupHelper.createLabel(
						"Verify user is able to Login" + " || User is able to Login successfully. ",
						ExtentColor.GREEN));
				MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName+".png");
				MobileUtility.printLogInfo("Login successful!");
				
			}
		
	}

	
	
}
