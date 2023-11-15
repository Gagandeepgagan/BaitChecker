package com.baitcheckerios.pages;

import org.openqa.selenium.WebElement;
import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.util.MobileUtility;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPageIos extends BasePage<IOSDriver<MobileElement>> implements LoginElement {

	public LoginPageIos(IOSDriver<MobileElement> driver) {
		super(driver);
	}

	@iOSXCUITFindBy()
	private WebElement emailField;

	@iOSXCUITFindBy()
	private WebElement passwordField;

	@iOSXCUITFindBy()
	private WebElement loginBtn;

	public void login(String email, String pass) throws Exception {
		System.out.println(" inside LoginPageIos ");

		MobileElement photosAppIcon = driver.findElement(MobileBy.AccessibilityId("Photos"));
		photosAppIcon.click();

		/*
		 * driver.activateApp("com.baitchecker"); MobileUtility.type(emailField, email,
		 * "email", driver); MobileUtility.type(passwordField, pass, "pass", driver);
		 * MobileUtility.clickElement(loginBtn, driver, "loginBtn");
		 */
	}

	public void createCustomer() throws Exception {

	}

	public void logout() throws Exception {

	}
}
