package com.baitcheckerios.pages;

import java.io.IOException;

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

public class CustomerPageWeb extends BasePage<WebDriver> implements CustomerElement {

	public CustomerPageWeb(WebDriver driver) {
		super(driver);
	}

	String screenshotName = "createCustomer";

	@FindBy(css = "div.css-175oi2r.r-1awozwy.r-13awgt0.r-18u37iz.r-17s6mgv.r-1d7mnkm > div:nth-child(1)")
	private WebElement plusIcon;

	@FindBy(xpath = "//*[text()='Create customer account']")
	private WebElement createCustomerAccount;

	@FindBy(xpath = "//input[@placeholder='Company name']")
	private WebElement companyName;

	@FindBy(xpath = "//*[text()='Next step']")
	private WebElement nextStepBtn;

	@FindBy(css = "input[placeholder='E-mail address']")
	private WebElement emailTxt;

	@FindBy(xpath = "//*[text()='Save to create customer']")
	private WebElement saveToCreateCustomerBtn;

	@FindBy(xpath = "//*[contains(text(),'no immediate needs')]/../following-sibling::div[last()-1]/div/div/div")
	private WebElement getCompanyName;

	@FindBy(xpath = "//*[contains(text(),'show customers')]")
	private WebElement showCustomers;

	@FindBy(css = "div.css-175oi2r.r-13awgt0.r-j2kj52>div")
	public WebElement errorMsg;

	@FindBy(css = " div.css-146c3p1.r-13tplrl.r-17ul5sj.r-1b43r93.r-knv0ih")
	public WebElement validationMsg;

	public void createCustomer() throws Exception {
		MobileUtility.printLogInfo(" inside LoginPageWeb : createCustomer");

		String nameOfCompany = "test";

		MobileUtility.clickElement(plusIcon, driver, "plusIcon");
		MobileUtility.clickElement(createCustomerAccount, driver, "createCustomerAccount");
		MobileUtility.type(companyName, nameOfCompany, "companyName", driver);
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		checkValidationMsg(validationMsg);
		MobileUtility.type(emailTxt, "test@invalid", "email address", driver);
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		checkValidationMsg(validationMsg);
		MobileUtility.clickElement(saveToCreateCustomerBtn, driver, "saveToCreateCustomerBtn");
		checkValidationMsg(errorMsg);
		MobileUtility.clickElement(showCustomers, driver, "showCustomers");

		if (getCompanyName.getAttribute("innerHTML").matches(nameOfCompany)) {
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

	public void checkValidationMsg(WebElement element) throws IOException {
		String sshotName = screenshotName + MobileUtility.getDate();
		MobileUtility.capture(driver, sshotName);
		String validateMsg = MobileUtility.getFlashMsgText(driver, element);
		if (validateMsg != null && !validateMsg.isEmpty()) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify if user is able to create customer ||  " + "\'" + validateMsg + "\'", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + sshotName + ".png");
			Assert.fail("Failed: " + validateMsg);
		}
	}
}
