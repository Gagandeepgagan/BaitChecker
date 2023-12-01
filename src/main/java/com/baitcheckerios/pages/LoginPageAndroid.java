package com.baitcheckerios.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.listener.MyExtentListeners;
import com.baitcheckerios.util.MobileUtility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class LoginPageAndroid extends BasePage<AndroidDriver<MobileElement>> implements LoginElement {
	public LoginPageAndroid(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	@FindBy(xpath = "//android.widget.EditText[@text='Email']")
	private WebElement emailField;

	@FindBy(xpath = "//android.widget.EditText[@text='Password']")
	private WebElement passwordField;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Log in']")
	private WebElement loginBtn;

	@FindBy(xpath = "//android.widget.TextView[contains(@text,'Hello')]")
	private WebElement welcomeText;

	@FindBy(xpath = "//android.widget.ImageView")
	private List<WebElement> logoutBtn;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Yes, logout']")
	private WebElement logoutConfirmBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Log in again']")
	private WebElement loginAgainBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Create customer account']")
	private WebElement createCustomerAccount;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Create employee account']")
	private WebElement createEmployeeAccount;

	@FindBy(xpath = "//android.widget.EditText[@text='Company name']")
	private WebElement companyName;

	@FindBy(xpath = "//android.widget.TextView[@text='Next step']")
	private WebElement nextStepBtn;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Take photo of floor plan']" )
	private WebElement  takePhotoBtn;
	
	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Shutter']" )
	private WebElement cameraBtn;
	
	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Done']" )
	private WebElement doneBtn;
	
	@FindBy(xpath = "//android.widget.TextView[@content-desc='Crop']" )
	private WebElement cropBtn;
	

	@FindBy(xpath = "//android.widget.TextView[@text='Save to create customer']")
	private WebElement saveToCreateCustomerBtn;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='hide customers']/../preceding-sibling::android.view.ViewGroup[last()]/android.view.ViewGroup")
	private WebElement getCompanyName;
	
	@FindBy(xpath = "//android.widget.TextView[@text='show customers']")
	private WebElement showCustomers;
	
	public void login(String email, String pass) throws Exception {
		System.out.println(" inside LoginPageAndroid : Login ");
		driver.activateApp("com.baitchecker");
		MobileUtility.type(emailField, email, "email", driver);
		MobileUtility.type(passwordField, pass, "pass", driver);
		MobileUtility.clickElement(loginBtn, driver, "loginBtn");
		MobileUtility.waitForElement(welcomeText, driver, "welcomeText", 10);
		boolean isWelcomeTextDisplayed = MobileUtility.isEleDisplayed(welcomeText, 5, 1, "welcomeText");
		if (Boolean.TRUE.equals(isWelcomeTextDisplayed)) {
			MobileUtility.printLogInfo("User is logged in successfully");
			MyExtentListeners.test.pass(MarkupHelper.createLabel("Verify if user is logged-in || User is logged in successfully", ExtentColor.GREEN));
			MyExtentListeners.test.addScreenCaptureFromPath(MobileUtility.capture(driver, "LoginSuccess"));
			Assert.assertTrue(isWelcomeTextDisplayed);
		} else if (Boolean.FALSE.equals(isWelcomeTextDisplayed)) {
			MobileUtility.printLogInfo("User is not logged in");
			MyExtentListeners.test.fail(MarkupHelper.createLabel("Verify if user is logged-in || User is not logged in", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(MobileUtility.capture(driver, "LoginFailure"));
			Assert.assertFalse(isWelcomeTextDisplayed);
		}

	}
	public void createCustomer() throws Exception {
		String nameOfCompany="Company android";
		System.out.println(" inside LoginPageAndroid : createCustomer");
		MobileUtility.clickElement(logoutBtn.get(logoutBtn.size() - 3), driver, "plusIcon");
		MobileUtility.clickElement(createCustomerAccount, driver, "createCustomerAccount");
		MobileUtility.type(companyName, nameOfCompany,"companyName", driver);
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(takePhotoBtn, driver, "takePhotoBtn");
		MobileUtility.clickElement(cameraBtn, driver, "cameraBtn");
		MobileUtility.clickElement(doneBtn, driver, "doneBtn");
		MobileUtility.clickElement(cropBtn, driver, "cropBtn");
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(saveToCreateCustomerBtn, driver, "saveToCreateCustomerBtn");
		MobileUtility.clickElement(showCustomers, driver, "showCustomers");
		MobileUtility.scrollUsingFling(driver);
		Assert.assertEquals(getCompanyName.getText(), nameOfCompany);
	}
	
	public void createEmployee() throws Exception {
		System.out.println(" inside LoginPageWeb : createEmployee");
		 Activity activity = new Activity("com.baitchecker", "com.baitchecker.MainActivity");
	        driver.startActivity(activity);
	    				MobileUtility.clickElement(logoutBtn.get(logoutBtn.size() - 3), driver, "plusIcon");
			MobileUtility.clickElement(createEmployeeAccount, driver, "createEmployeeAccount");
			
	}

	public void logout() throws Exception {
		System.out.println(" inside LoginPageAndroid : Logout");
		MobileUtility.clickElement(logoutBtn.get(logoutBtn.size() - 1), driver, "logoutBtn");
		MobileUtility.clickElement(logoutConfirmBtn, driver, "logoutConfirmBtn");
		MobileUtility.waitForElement(loginAgainBtn, driver, "loginAgainBtn", 10);
		boolean isLoginBtnDisplayed = MobileUtility.isEleDisplayed(loginAgainBtn, 5, 1, "loginAgainBtn");
		Assert.assertTrue(isLoginBtnDisplayed);
	}
}
