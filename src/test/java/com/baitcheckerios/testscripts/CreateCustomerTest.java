package com.baitcheckerios.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baitcheckerios.library.Baselib;
import com.baitcheckerios.pages.CustomerElement;
import com.baitcheckerios.pages.CustomerPageWeb;

public class CreateCustomerTest extends Baselib {

	private CustomerElement customerElement;

	@BeforeClass
	@Parameters("platform")
	public void setUp(@Optional("web") String platform) {
		if (platform.equals("web") || platform.equals("safari")) {
			System.out.println("WEB");
			customerElement = new CustomerPageWeb(driver);

		} else if (platform.equals("ios")) {
			System.out.println("IOS");
			

		} else if (platform.equals("android")) {
			System.out.println("ANDROID");
			
		} else {
			throw new IllegalArgumentException("Invalid platform provided");
		}
	}

	

	@Test(priority = 1, enabled = false/* , dependsOnMethods = "TestLogin" */)
	public void TestCreateCustomer() throws Exception {
		System.out.println(" TestCreateCustomer() ");
		customerElement.createCustomer();
	}

}
