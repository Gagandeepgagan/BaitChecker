package com.baitcheckerios.pages;

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

public class LogoutPageIos extends BasePage<IOSDriver<MobileElement>> implements LogoutElement {

	public LogoutPageIos(IOSDriver<MobileElement> driver) {
		super(driver);
	}
	

	@FindBy(xpath = "(//XCUIElementTypeOther[@name='show customers'])/ancestor::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]")
	private WebElement logoutBtn;

	/*
	 * @iOSXCUITFindBy(iOSNsPredicate="name == 'Yes, logout'") private WebElement
	 * logoutConfirmBtn;
	 */

	@FindBy(xpath = "//XCUIElementTypeOther[@name='Yes, logout']")
	private WebElement logoutConfirmBtn;

//	 @iOSXCUITFindBy(iOSClassChain=" **/XCUIElementTypeOther[`name=='Log in again'`]")
//	 private WebElement loginAgainBtn;

	@FindBy(xpath = "//XCUIElementTypeOther[@name='Log in again']")
	private WebElement loginAgainBtn;

	

	public void logout() throws Exception {
		MobileUtility.printLogInfo(" inside LoginPageIos : Logout  ");
		MobileUtility.clickElement(logoutBtn, driver, "logoutBtn");
		MobileUtility.clickElement(logoutConfirmBtn, driver, "logoutConfirmBtn");
		String screenshotName = "logoutIos" + MobileUtility.getDate();
		MobileUtility.capture(driver, screenshotName);
		boolean isLoginBtnDisplayed = MobileUtility.isElementDisplayed(loginAgainBtn);
		if (Boolean.TRUE.equals(isLoginBtnDisplayed)) {
			MyExtentListeners.test.pass(MarkupHelper.createLabel(
					"Verify user is able to Logout" + " || User is able to Logout successfully!", ExtentColor.GREEN));
			MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName + ".png");
			MobileUtility.printLogInfo("Logout successful!");
		} else {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify user is able to Logout" + " || User is not able to Logout. ", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + screenshotName + ".png");
			Assert.fail("Logout failed.");
		}
	}
}