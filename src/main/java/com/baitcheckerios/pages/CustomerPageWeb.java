package com.baitcheckerios.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.baitcheckerios.library.BasePage;
import com.baitcheckerios.util.MobileUtility;

public class CustomerPageWeb extends BasePage<WebDriver> implements CustomerElement {

	public CustomerPageWeb(WebDriver driver) {
		super(driver);
	}



	@FindBy(css = "div.css-175oi2r.r-1awozwy.r-13awgt0.r-18u37iz.r-17s6mgv.r-1d7mnkm > div:nth-child(1)")
	private WebElement plusIcon;

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


	public void createCustomer() throws Exception {
		String nameOfCompany = "Company 11";
		MobileUtility.printLogInfo(" inside LoginPageWeb : createCustomer");
		MobileUtility.clickElement(plusIcon, driver, "plusIcon");
		MobileUtility.clickElement(createCustomerAccount, driver, "createCustomerAccount");
		MobileUtility.type(companyName, nameOfCompany, "companyName", driver);
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(nextStepBtn, driver, "nextStepBtn");
		MobileUtility.clickElement(saveToCreateCustomerBtn, driver, "saveToCreateCustomerBtn");
		MobileUtility.clickElement(showCustomers, driver, "showCustomers");
		Assert.assertEquals(getCompanyName.getAttribute("innerHTML"), nameOfCompany);
	}


}
