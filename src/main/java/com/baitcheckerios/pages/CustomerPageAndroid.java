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

public class CustomerPageAndroid extends BasePage<AndroidDriver<MobileElement>> implements CustomerElement {

	public CustomerPageAndroid(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	String screenshotName = "createCustomer";

	@FindBy(xpath = "//android.widget.ImageView")
	private List<WebElement> logoutBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Create customer account']")
	private WebElement createCustomerAccount;

	@FindBy(xpath = "//android.widget.EditText[@text='Company name']")
	private WebElement companyName;

	@FindBy(xpath = "//android.widget.TextView[@text='Next step']")
	private WebElement nextStepBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Take photo of floor plan']")
	private WebElement takePhotoBtn;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Shutter']")
	private WebElement cameraBtn;

	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Done']")
	private WebElement doneBtn;

	@FindBy(xpath = "//android.widget.TextView[@content-desc='Crop']")
	private WebElement cropBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Save to create customer']")
	private WebElement saveToCreateCustomerBtn;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='hide customers']/../preceding-sibling::android.view.ViewGroup[last()]/android.view.ViewGroup")
	private WebElement getCompanyName;

	@FindBy(xpath = "//android.widget.TextView[@text='show customers']")
	private WebElement showCustomers;

	public void createCustomer() throws Exception {
		String nameOfCompany = "Company android";
		MobileUtility.printLogInfo(" inside LoginPageAndroid : createCustomer");
		MobileUtility.clickElement(logoutBtn.get(logoutBtn.size() - 3), driver, "plusIcon");
		MobileUtility.clickElement(createCustomerAccount, driver, "createCustomerAccount");
		MobileUtility.type(companyName, nameOfCompany, "companyName", driver);
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
	
		if (getCompanyName.getText().matches(nameOfCompany)) {
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
