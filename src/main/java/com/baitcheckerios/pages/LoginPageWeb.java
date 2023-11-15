package com.baitcheckerios.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.util.MobileUtility;

import io.appium.java_client.AppiumDriver;

public class LoginPageWeb extends BasePage<WebDriver> implements LoginElement {

	public LoginPageWeb(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='Email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordField;

	@FindBy(xpath = "//*[contains(text(),'Log in')]")
	private WebElement loginBtn;

	@FindBy(xpath = "//img")
	private List<WebElement> logoutBtn;

	@FindBy(xpath = "//*[text()='Yes, logout']")
	private WebElement logoutConfirmBtn;

	@FindBy(xpath = "//*[text()='Log in again']")
	private WebElement loginAgainBtn;

	@FindBy(xpath = "//*[text()='Create customer account']")
	private WebElement createCustomerAccount;

	@FindBy(xpath = "//input[@placeholder='Company name']")
	private WebElement companyName;

	@FindBy(xpath = "//*[text()='Next step']")
	private WebElement nextStepBtn;

	@FindBy(xpath = "//*[text()='Save to create customer']")
	private WebElement saveToCreateCustomerBtn;

	@FindBy(xpath = "//*[contains(text(),'no immediate needs')]/../following-sibling::div[last()-1]/div/div/div")
	private WebElement getCompanyName;
	
	@FindBy(xpath = "//*[contains(text(),'show customers')]")
	private WebElement showCustomers;

	public void login(String email, String pass) throws Exception {
		System.out.println(" inside LoginPageWeb : Login");
		driver.get("https://bait-staging-web.vtnetzwelt.com/login");
		Thread.sleep(10000);
		MobileUtility.type(emailField, email, "email", driver);
		MobileUtility.type(passwordField, pass, "pass", driver);
		MobileUtility.clickElement(loginBtn, driver, "loginBtn");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getCurrentUrl(), "https://bait-staging-web.vtnetzwelt.com/dashboard");
	}

	public void createCustomer() throws Exception {
		String nameOfCompany="Company 11";
		System.out.println(" inside LoginPageWeb : createCustomer");
		MobileUtility.clickElement(logoutBtn.get(logoutBtn.size() - 3), driver, "plusIcon");
		MobileUtility.clickElement(createCustomerAccount, driver, "createCustomerAccount");
		MobileUtility.type(companyName, nameOfCompany,"companyName", driver);
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(saveToCreateCustomerBtn, driver, "saveToCreateCustomerBtn");
		MobileUtility.clickElement(showCustomers, driver, "showCustomers");
		Assert.assertEquals(getCompanyName.getAttribute("innerHTML"), nameOfCompany);
	}

	public void createEmployee() {

	}

	public void logout() throws Exception {
		System.out.println(" inside LoginPageWeb : Logout");
		MobileUtility.clickElement(logoutBtn.get(logoutBtn.size() - 1), driver, "logoutBtn");
		MobileUtility.clickElement(logoutConfirmBtn, driver, "logoutConfirmBtn");
		MobileUtility.waitForElement(loginAgainBtn, driver, "loginAgainBtn", 10);
		boolean isLoginBtnDisplayed = MobileUtility.isEleDisplayed(loginAgainBtn, 5, 1, "loginAgainBtn");
		Assert.assertTrue(isLoginBtnDisplayed);
	}
}
