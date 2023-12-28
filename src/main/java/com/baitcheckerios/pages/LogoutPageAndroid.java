package com.baitcheckerios.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.library.Baselib;
import com.baitcheckerios.library.GenericLib;
import com.baitcheckerios.listener.MyExtentListeners;
import com.baitcheckerios.util.MobileUtility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LogoutPageAndroid extends BasePage<AndroidDriver<MobileElement>> implements LogoutElement {

	public LogoutPageAndroid(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	@FindBy(xpath = "//android.widget.ImageView")
	private List<WebElement> logoutBtn;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Yes, logout']")
	private WebElement logoutConfirmBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Log in again']")
	private WebElement loginAgainBtn;

	public void logout() throws Exception {
		MobileUtility.printLogInfo(" inside LoginPageAndroid : Logout");
		MobileUtility.clickElement(logoutBtn.get(logoutBtn.size() - 1), driver, "logoutBtn");
		MobileUtility.clickElement(logoutConfirmBtn, driver, "logoutConfirmBtn");
//		MobileUtility.waitForElement(loginAgainBtn, driver, "loginAgainBtn", 10);
		
		String screenshotName="logoutAndroid"+MobileUtility.getDate();
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