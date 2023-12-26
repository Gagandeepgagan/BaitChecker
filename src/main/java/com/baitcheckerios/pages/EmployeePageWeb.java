package com.baitcheckerios.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.listener.MyExtentListeners;
import com.baitcheckerios.util.MobileUtility;

public class EmployeePageWeb extends BasePage<WebDriver> implements EmployeeElement {

	public EmployeePageWeb(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.css-175oi2r.r-1awozwy.r-13awgt0.r-18u37iz.r-17s6mgv.r-1d7mnkm > div:nth-child(2)")
	private WebElement plusIcon;

	@FindBy(xpath = "//*[text()='Create employee account']")
	private WebElement createEmployeeAccount;

	@FindBy(xpath = "//input[@placeholder='First name']")
	private WebElement employeeFirstName;

	@FindBy(xpath = "//*[text()='Next step']")
	private WebElement nextStepBtn;

	@FindBy(xpath = "//*[text()='Select all customers']")
	private WebElement selectAllCustomersCheckbox;

	@FindBy(xpath = "//*[text()='Create account']")
	private WebElement createAccountBtn;

	@FindBy(xpath = "//*[contains(text(),'accounts registered')]/../following-sibling::div[last()]/div/div/div")
	private WebElement getEmployeeName;

	@FindBy(xpath = "//img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAYAAABV7bNHAAAACXBIWXMAACE4AAAhOAFFljFgAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAL9SURBVHgB7ZuBVdswEIYvnSAjuBM0TFAxAXSCphM0G5ANYANggrYTJExAOoG9Adng54TEeyHPcmz5LraMvvf0/N4ZyaeLLOnkH6JMJpPJZFJlRgMBYM6XBZcrLoZLwWXub++57Hx5ms1mf+mzYAPD5YbLC9pT+joFTRnu4O+OgakL1JKmCHfsFnLc0pTgDt1DnnuaApAdOcekPZK4A8sWndz4vysO6i287U+L+itKEdthuEk1hL1nBNqxk/6cUgNuWQ7x3KVTcFuD54b21pQaDb96iYhf3AepnMQoYmevEaagSLiuaWjXkAJfSAcTsP/jtKGiSLjuli/bwO1rUkArQN8Cdomc6jFg/06pgHA6saCewK1qtfMQKaCSzVtv6+z8iog8T7v9Q7RescmgFaB9nRECxxUNr+meFNAKUBWwG+pPKEA7UkArQE8B+0/qz1XA/p9SQWtD17CCWVT2QSrApQWhpb6EfKpRUmqw02uEycnqiVH0PpKKFu0YnD42OdnOKGHHVziNPRSzh2OLg3qFt21a1F9SynAH7qDHHU0B7sgD5HmgKQHZkTSNkXMM3LxSIh476ad5SN8WuAl43TFQL77O2Y9VBxMvWOB2v4bcAZtdxQ7FCxW5lMUesu34JEMlGc1kMplMZrpofdVo0h9KkZ6OEXH6QylKjFnHiP76QylKjO0IBLoqsljGoT6Djv5QimF1jBjnyDlmmJGESP2h4PPHq2OEkP7wjP6cV4EGQf2hoE/j+TQEYf2hoF/D6xihpD8U9E/0s3eMeMEE7L30h1JI6xhjAqSpP5RiOB0jFPWHUkBQx9g5m7dPqW1IQR/YByk/xQRUwHiU7pK+xASoCtjH9EEvpGRTkel9AM3f2VcYfh900+Bf5+Q1Zg4yfNlQmvzoevIYNbFykGyADKVFxcH5Sh2JnaR/kZIuWQnr6yVFEBUgv2O+pDSC9Bac2F1+9DLPD7QrwgWFV7UxsOVy4X2NQuqfS2yOY8u7SmNIKnKBefR5WSaTyWQyn5RXR7ZLzByaW2AAAAAASUVORK5CYII=']")
	private List<WebElement> toggleIcon;

	@FindBy(xpath = "//img[@src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHkAAAB4CAYAAADWpl3sAAAACXBIWXMAACE4AAAhOAFFljFgAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAJuSURBVHgB7d3hUdtAEEDhdSpICZTgEkgHdJCkAlJCOkg6wB1QgksAKgAqgA6WFZYGDQNYJ93pdn3vm9HYP8zMnZ7lsdHJFgEAAAAAAAAAAAAAAAAAAAAAAAAAAGiCql7adq8H3e0/275LQDburW172576+XT3z6RlfdCP3EQLbeP9qZ87lxbZxH/p18KEPhL49RVKWmQTv9bj3IeeEHiwlUq+iW/djtl7Dd0FtpvdxIdXm0PNyHcTH+cydGLgzoO0xnbSmb69C53CzUt3wkv04EpapcfffLkLPSNwuE8K2UUKTeAFIoQmcAaeQxM4I4+hCVyAp9AELshDaAKvoGZoAq+oRmgCV7BmaAJXtEZoAjtQMjSBHSkRmsAO5QxNYMdyhCZwAEtCEziQmaEv0/6EwNXNCE3giAqFJrA3mUMT2KtMoQns3cLQBI5iZuiTDez9Coq5VHC6NP0fHU0czSdjYWBCe5cpMKG9yhyY0N4UCkxoL3Te2aQ/mobQteiC04Ua8GrK5miG88GEdkwznvAntENaYEUHoR3Rgkt2CO2ArrAmi9AV6YqL7ghdgVZYVUnoFWnFZbOEXoE6WBdN6II8BB6NhdC5eQo8GhOhc/EYeDQ2Qi/lOfBojISeK0Lg0VgJncp2wIWm8fAFqoSeSmN/FXJq6GtpkU38f8JOcnc0zAjd3tGsh5/TCRl4kBj6XCqpeQXF44TH3Nr2Y7PZPItDNq6d3fwWfKx7ZmvQI/i9CUd0mz8Z1LHJ76IHHhwJfSEt63fOzfCMt+1vtMADPXxi2PdzeervbwUAAAAAAAAAAAAAAAAAAAAAAAAAAKAJL9U4I5gIN0WeAAAAAElFTkSuQmCC']")
	private WebElement crossIcon;

	@FindBy(xpath = "//*[text()='Create account']")
	private WebElement flashMessageSuccess;

	public void createEmployee() throws Exception {
		String nameOfEmployee = "Vishnu3";
		MobileUtility.clickElement(plusIcon, driver, "plusIcon");
		MobileUtility.clickElement(createEmployeeAccount, driver, "createEmployeeAccount");
		MobileUtility.type(employeeFirstName, nameOfEmployee + Keys.TAB + "Ladwal" + Keys.TAB + nameOfEmployee
				+ "@vtnetzwelt.com" + Keys.TAB + "123 54687935", "employee details", driver);
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(selectAllCustomersCheckbox, driver, "selectAllCustomersCheckbox");
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(createAccountBtn, driver, "createAccountBtn");
		try {
			String message = MobileUtility.getFlashMsgText(driver, flashMessageSuccess);
			MobileUtility.printLogInfo(message);
		} catch (Exception e) {

		}
		MobileUtility.clickElement(toggleIcon.get(toggleIcon.size() - 1), driver, "toggleIcon");
		if (getEmployeeName.getAttribute("innerHTML").contains(nameOfEmployee)) {
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
			Assert.assertFalse(true);
		}
	}

}
