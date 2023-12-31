package com.baitcheckerios.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baitcheckerios.library.Baselib;
import com.baitcheckerios.pages.CustomerElement;
import com.baitcheckerios.pages.CustomerPageAndroid;
import com.baitcheckerios.pages.CustomerPageIos;
import com.baitcheckerios.pages.CustomerPageWeb;
import com.baitcheckerios.util.MobileUtility;

public class CreateCustomerTest extends Baselib {

	private CustomerElement customerElement;
//	private CustomerPageWeb customerPageWeb;

	@BeforeClass
	@Parameters("platform")
	public void setUp(@Optional("web") String platform) {
		if (platform.equals("web") || platform.equals("safari")) {
			System.out.println("WEB");
			customerElement = new CustomerPageWeb(driver);
//			customerPageWeb= new CustomerPageWeb(driver);
		} else if (platform.equals("ios")) {
			System.out.println("IOS");
			customerElement = new CustomerPageIos(iosDriver);

		} else if (platform.equals("android")) {
			System.out.println("ANDROID");
			customerElement = new CustomerPageAndroid(androidDriver);
		} else {
			throw new IllegalArgumentException("Invalid platform provided");
		}
	}

	

	@Test(priority = 1, enabled = true)
	public void TestCreateCustomer() throws Exception {
		MobileUtility.printLogInfo(" TestCreateCustomer() ");
		customerElement.createCustomer();
	}

}
