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
import io.appium.java_client.ios.IOSDriver;

public class EmployeePageIos extends BasePage<IOSDriver<MobileElement>> implements EmployeeElement {

	public EmployeePageIos(IOSDriver<MobileElement> driver) {
		super(driver);
	}

	String screenshotName = "createEmployee";
	String firstName = "tester";
	String lastName = "g";
	String email= "tester@gmail.com";
	String number = "123456789";

	@FindBy(xpath = "//android.widget.ImageView")
	private List<WebElement> logoutBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Create employee account']")
	private WebElement createEmployeeAccount;

	@FindBy(xpath = "//android.widget.EditText")
	private List<WebElement> employeeFirstName;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='Next step']")
	private WebElement nextStepBtn;

	@FindBy(xpath = "//*[text()='Select all customers']")
	private WebElement selectAllCustomersCheckbox;

	@FindBy(xpath = "//*[text()='Create account']")
	private WebElement createAccountBtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Create employee account']")
	private WebElement getEmployeeName;

	@FindBy(xpath = "//android.widget.TextView[@text='Create employee account']")
	private WebElement crossIcon;
	@FindBy(xpath = "//img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAL9SURBVHgB7ZuBVdswEIYvnSAjuBM0TFAxAXSCphM0G5ANYANggrYTJExAOoG9Adng54TEeyHPcmz5LraMvvf0/N4ZyaeLLOnkH6JMJpPJZFJlRgMBYM6XBZcrLoZLwWXub++57Hx5ms1mf+mzYAPD5YbLC9pT+joFTRnu4O+OgakL1JKmCHfsFnLc0pTgDt1DnnuaApAdOcekPZK4A8sWndz4vysO6i287U+L+itKEdthuEk1hL1nBNqxk/6cUgNuWQ7x3KVTcFuD54b21pQaDb96iYhf3AepnMQoYmevEaagSLiuaWjXkAJfSAcTsP/jtKGiSLjuli/bwO1rUkArQN8Cdomc6jFg/06pgHA6saCewK1qtfMQKaCSzVtv6+z8iog8T7v9Q7RescmgFaB9nRECxxUNr+meFNAKUBWwG+pPKEA7UkArQE8B+0/qz1XA/p9SQWtD17CCWVT2QSrApQWhpb6EfKpRUmqw02uEycnqiVH0PpKKFu0YnD42OdnOKGHHVziNPRSzh2OLg3qFt21a1F9SynAH7qDHHU0B7sgD5HmgKQHZkTSNkXMM3LxSIh476ad5SN8WuAl43TFQL77O2Y9VBxMvWOB2v4bcAZtdxQ7FCxW5lMUesu34JEMlGc1kMplMZrpofdVo0h9KkZ6OEXH6QylKjFnHiP76QylKjO0IBLoqsljGoT6Djv5QimF1jBjnyDlmmJGESP2h4PPHq2OEkP7wjP6cV4EGQf2hoE/j+TQEYf2hoF/D6xihpD8U9E/0s3eMeMEE7L30h1JI6xhjAqSpP5RiOB0jFPWHUkBQx9g5m7dPqW1IQR/YByk/xQRUwHiU7pK+xASoCtjH9EEvpGRTkel9AM3f2VcYfh900+Bf5+Q1Zg4yfNlQmvzoevIYNbFykGyADKVFxcH5Sh2JnaR/kZIuWQnr6yVFEBUgv2O+pDSC9Bac2F1+9DLPD7QrwgWFV7UxsOVy4X2NQuqfS2yOY8u7SmNIKnKBefR5WSaTyWQyn5RXR7ZLzByaW2AAAAAASUVORK5CYII=']")
	private List<WebElement> toggleIcon;

	public void createEmployee() throws Exception {
		MobileUtility.printLogInfo(" inside LoginPageWeb : createEmployee");
	
//			MobileUtility.stopAParticularAppActivity(driver);
//			driver.activateApp("com.baitchecker");
		
		MobileUtility.clickElement(logoutBtn.get(logoutBtn.size() - 3), driver, "plusIcon");
		MobileUtility.clickElement(createEmployeeAccount, driver, "createEmployeeAccount");
		MobileUtility.type(employeeFirstName.get(0),firstName,"firstName", driver);
		MobileUtility.type(employeeFirstName.get(1),lastName,"lastName", driver);
		MobileUtility.type(employeeFirstName.get(2),email,"email", driver);
		MobileUtility.type(employeeFirstName.get(3),number,"number", driver);
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
//		MobileUtility.checkValidationMsg(screenshotName, driver, validationMsg);
		MobileUtility.clickElement(selectAllCustomersCheckbox, driver, "selectAllCustomersCheckbox");
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
//		MobileUtility.checkValidationMsg(screenshotName, driver, validationMsg);
		MobileUtility.clickElement(createAccountBtn, driver, "createAccountBtn");
//		MobileUtility.checkValidationMsg(screenshotName, driver, errorMsg);
		MobileUtility.clickElement(toggleIcon.get(toggleIcon.size() - 1), driver, "toggleIcon");

		if (getEmployeeName.getAttribute("innerHTML").contains(firstName
				+" "+lastName)) {
			MobileUtility.printLogInfo("Employee created successfully");
			MyExtentListeners.test.pass(MarkupHelper
					.createLabel("Verify if employee is created || Employee created successfully", ExtentColor.GREEN));
			MyExtentListeners.test.addScreenCaptureFromPath(MobileUtility.capture(driver, "CreateEmployeeSuccess"));
			Assert.assertTrue(true);
		} else {
			MobileUtility.clickElement(crossIcon, driver, "crossIcon");
			MobileUtility.printLogInfo("Employee creation failed");
			MyExtentListeners.test.fail(MarkupHelper
					.createLabel("Verify if employee is created || Employee creation failed", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(MobileUtility.capture(driver, "LoginFailure"));
			Assert.fail("Create Employee failed.");
		}
	}

}
