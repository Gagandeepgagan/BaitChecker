package com.baitcheckerios.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baitcheckerios.library.Baselib;
import com.baitcheckerios.pages.LoginElement;
import com.baitcheckerios.pages.LoginPageAndroid;
import com.baitcheckerios.pages.LoginPageIos;
import com.baitcheckerios.pages.LoginPageWeb;

public class LoginTest extends Baselib {

	private LoginElement loginElement;

	@BeforeClass
	@Parameters("platform")
	public void setUp(@Optional("web") String platform) {
		if (platform.equals("web") || platform.equals("safari")) {
			System.out.println("WEB");
			loginElement = new LoginPageWeb(driver);

		} else if (platform.equals("ios")) {
			System.out.println("IOS");
			loginElement = new LoginPageIos(iosDriver);

		} else if (platform.equals("android")) {
			System.out.println("ANDROID");
			loginElement = new LoginPageAndroid(androidDriver);
		} else {
			throw new IllegalArgumentException("Invalid platform provided");
		}
	}

	@Test(priority = 0,enabled=true)
	public void TestLogin() throws Exception {
		System.out.println(" TestLogin() ");
		loginElement.login("gagandeep.bains@vtnetzwelt.com", "Password@12");
	}
	
	@Test(priority = 1/* , dependsOnMethods = "TestLogin" */,enabled = true)
	public void TestCreateCustomer() throws Exception {
		System.out.println(" TestCreateCustomer() ");
		loginElement.createCustomer();
	}
//	@Test(priority = 2)
//	public void TestCreateEmployee() throws Exception {
//		System.out.println(" TestCreateEmployee() ");
//		loginElement.createEmployee();;
//	}

	@Test(priority = 3,enabled=true/* , dependsOnMethods = "TestLogin" */)
	public void TestLogout() throws Exception {
		System.out.println(" TestLogout() ");
		loginElement.logout();
	}
}
