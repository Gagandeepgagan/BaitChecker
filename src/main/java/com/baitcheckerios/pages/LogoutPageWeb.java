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

public class LogoutPageWeb extends BasePage<WebDriver> implements LogoutElement {

	public LogoutPageWeb(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.css-175oi2r.r-1awozwy.r-13awgt0.r-18u37iz.r-17s6mgv.r-1d7mnkm > div:nth-child(3)")
	private WebElement logoutBtn;

	@FindBy(xpath = "//*[text()='Yes, logout']")
	private WebElement logoutConfirmBtn;

	@FindBy(xpath = "//*[text()='Log in again']")
	private WebElement loginAgainBtn;

	public void logout() throws Exception {

		MobileUtility.printLogInfo(" inside LoginPageWeb : Logout");
		MobileUtility.clickElement(logoutBtn, driver, "logoutBtn");
		MobileUtility.clickElement(logoutConfirmBtn, driver, "logoutConfirmBtn");
		String screenshotName="logout"+MobileUtility.getDate();
		MobileUtility.capture(driver, screenshotName);

		if (driver.getCurrentUrl().contains("logout")) {
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
