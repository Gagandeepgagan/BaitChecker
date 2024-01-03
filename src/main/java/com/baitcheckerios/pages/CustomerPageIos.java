package com.baitcheckerios.pages;

import java.util.List;

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

public class CustomerPageIos extends BasePage<IOSDriver<MobileElement>> implements CustomerElement {

	public CustomerPageIos(IOSDriver<MobileElement> driver) {
		super(driver);
	}

	String screenshotName = "createCustomer";

	@FindBy(xpath = "//android.widget.ImageView")
	private List<WebElement> logoutBtn;

	
	@FindBy(xpath="(//XCUIElementTypeOther[@name='show customers'])/ancestor::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]")
	private WebElement plusBtn;
	
	
	
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
		
		if (getCompanyName.get(getCompanyName.size()-1).getText().matches(nameOfCompany)) {
			MyExtentListeners.test.pass(MarkupHelper.createLabel(
					"Verify user is able create Customer" + " || User is able to create Customer", ExtentColor.GREEN));
			MyExtentListeners.test.addScreenCaptureFromPath(
					GenericLib.screenShotPath + screenshotName + MobileUtility.getDate() + ".png");
			MobileUtility.printLogInfo("Created Customer successfully!");

		} else {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify user is able create Customer" + " || User is not able to create Customer. ",
					ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(
					MobileUtility.capture(driver, GenericLib.screenShotPath + screenshotName));
			Assert.fail("Create Customer failed.");
		}
	}
}
