package com.baitcheckerios.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.listener.MyExtentListeners;
import com.baitcheckerios.util.MobileUtility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class LoginPageIos extends BasePage<IOSDriver<MobileElement>> implements LoginElement {

	public LoginPageIos(IOSDriver<MobileElement> driver) {
		super(driver);
	}

	@FindBy(xpath="//XCUIElementTypeTextField[@value='Email']")
	private WebElement emailField;

	@FindBy(xpath="//XCUIElementTypeSecureTextField[@value='Password']")
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
	
	@FindBy(xpath="(//XCUIElementTypeOther[@name='show customers'])/ancestor::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]")
	private WebElement logoutBtn;
	
	@FindBy(xpath="(//XCUIElementTypeOther[@name='show customers'])/ancestor::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]")
	private WebElement plusBtn;
	
	/*
	 * @iOSXCUITFindBy(iOSNsPredicate="name == 'Yes, logout'") private WebElement
	 * logoutConfirmBtn;
	 */
	
	@FindBy(xpath="//XCUIElementTypeOther[@name='Yes, logout']")
	private WebElement logoutConfirmBtn;
	
	//@iOSXCUITFindBy(iOSClassChain=" **/XCUIElementTypeOther[`name=='Log in again'`]")
	//private WebElement loginAgainBtn;
	
	@FindBy(xpath="//XCUIElementTypeOther[@name='Log in again']")
	private WebElement loginAgainBtn;
	
	@FindBy(xpath = "//XCUIElementTypeOther[@name='Create customer account']")
	private WebElement createCustomerAccount;
	
	@FindBy(xpath = "//XCUIElementTypeOther[@name='Create employee account']")
	private WebElement createEmployeeAccount;

	@FindBy(xpath = "//XCUIElementTypeTextField[@value='Company name']")
	private WebElement companyName;

	@FindBy(xpath = "//XCUIElementTypeOther[@name='Next step']")
	private WebElement nextStepBtn;
	
	@FindBy(xpath = "//XCUIElementTypeOther[@name='Take photo of floor plan']" )
	private WebElement  takePhotoBtn;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@name='OK']" )
	private WebElement okBtn;
	
	@FindBy(xpath = "//XCUIElementTypeButton[@name='PhotoCapture']" )
	private WebElement cameraBtn;
	
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Use Photo']" )
	private WebElement doneBtn;
	
	@FindBy(xpath = "//XCUIElementTypeStaticText[@name='Choose']" )
	private WebElement cropBtn;
	

	@FindBy(xpath = "//XCUIElementTypeOther[@name='Save to create customer']")
	private WebElement saveToCreateCustomerBtn;

	@FindBy(xpath = "(//XCUIElementTypeOther[@name='hide customers'])[1]/preceding-sibling::XCUIElementTypeOther/XCUIElementTypeOther")
	private List<WebElement> getCompanyName;
	
	@FindBy(xpath = "(//XCUIElementTypeOther[@name='show customers'])[2]")
	private WebElement showCustomers;

	@FindBy(xpath = "(//XCUIElementTypeOther[@name='hide customers'])[2]")
	private WebElement hideCustomers;
	
	public void login(String email, String pass) throws Exception {
		
		MobileUtility.printLogInfo(" inside LoginPageIos : Login ");
		driver.activateApp("com.baitchecker1");
		MobileUtility.type(emailField, email+ Keys.ENTER, "email", driver);
		MobileUtility.type(passwordField, pass+ Keys.ENTER, "pass", driver);
		MobileUtility.clickElement(loginBtn, driver, "loginBtn");
		MobileUtility.waitForElement(welcomeText, driver, "welcomeText", 10);
		boolean isWelcomeTextDisplayed = MobileUtility.isEleDisplayed(welcomeText, 2, 3, "welcomeText");
		MobileUtility.printLogInfo(String.valueOf(isWelcomeTextDisplayed));
		if (Boolean.TRUE.equals(isWelcomeTextDisplayed)) {
			MobileUtility.printLogInfo("User is logged in successfully");
			MyExtentListeners.test.pass(MarkupHelper
					.createLabel("Verify if user is logged-in || User is logged in successfully", ExtentColor.GREEN));
			MyExtentListeners.test.addScreenCaptureFromPath(MobileUtility.capture(driver, "LoginSuccess"));
			Assert.assertTrue(isWelcomeTextDisplayed);
		} else if (Boolean.FALSE.equals(isWelcomeTextDisplayed)) {
			MobileUtility.printLogInfo("User is not logged in");
			MyExtentListeners.test.fail(
					MarkupHelper.createLabel("Verify if user is logged-in || User is not logged in", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(MobileUtility.capture(driver, "LoginFailure"));
			Assert.assertTrue(isWelcomeTextDisplayed);
		}
		
	}

	public void createCustomer() throws Exception {
		String nameOfCompany="dvdf";
		MobileUtility.printLogInfo(" inside LoginPageIos : createCustomer");
		MobileUtility.clickElement(plusBtn, driver, "plusIcon");
		MobileUtility.clickElement(createCustomerAccount, driver, "createCustomerAccount");
		MobileUtility.type(companyName, nameOfCompany+Keys.ENTER,"companyName", driver);
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(takePhotoBtn, driver, "takePhotoBtn");
		try {
			MobileUtility.clickElementSimple(okBtn, driver, "okBtn");
		}catch(Exception e) {
			
		}
		MobileUtility.clickElement(cameraBtn, driver, "cameraBtn");
		MobileUtility.clickElement(doneBtn, driver, "doneBtn");
		MobileUtility.clickElement(cropBtn, driver, "cropBtn");
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(saveToCreateCustomerBtn, driver, "saveToCreateCustomerBtn");
		MobileUtility.clickElement(showCustomers, driver, "showCustomers");
//		MobileUtility.scrollNclick(driver,hideCustomers);
		MobileUtility.printLogInfo(getCompanyName.get(getCompanyName.size()-1).getText());
		Assert.assertEquals(getCompanyName.get(getCompanyName.size()-1).getText(), nameOfCompany);
	}

	public void createEmployee() {

	}

	public void logout() throws Exception {
		MobileUtility.printLogInfo(" inside LoginPageIos : Logout  ");
		MobileUtility.clickElement(logoutBtn, driver, "logoutBtn");
		MobileUtility.clickElement(logoutConfirmBtn, driver, "logoutConfirmBtn");
		MobileUtility.waitForElement(loginAgainBtn, driver, "loginAgainBtn", 10);
		boolean isLoginBtnDisplayed = MobileUtility.isEleDisplayed(loginAgainBtn, 5, 1, "loginAgainBtn");
		Assert.assertTrue(isLoginBtnDisplayed);
	}
}
