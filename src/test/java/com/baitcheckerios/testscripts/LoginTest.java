package com.baitcheckerios.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baitcheckerios.library.Baselib;
import com.baitcheckerios.pages.LoginElement;
import com.baitcheckerios.pages.LoginPageAndroid;
import com.baitcheckerios.pages.LoginPageIos;
import com.baitcheckerios.pages.LoginPageWeb;
import com.baitcheckerios.util.MobileUtility;

public class LoginTest extends Baselib {

//	private LoginPageWeb loginElement1;
//	private LoginPageIos  loginElement2;
//	private  LoginPageAndroid loginElement3;

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

	@DataProvider(name = "loginData")
	public Object[][] loginData() {
		return new Object[][] {

				{ "gagandeep.bains@vtnetzwelt.com", "" }, { "", "Password@123" },
				{ "gagandeep.bains@vtnetzwelt.com", "Test@123" }, { "gagandeep.bains@vtnetzwelt", "Password@123" },
				{ "gagandeep.bains+6@vtnetzwelt.com", "Password@123" },

				{ "gagandeep.bains@vtnetzwelt.com", "Password@123" } };
	}

	@Test(priority = 0, enabled = true, dataProvider = "loginData")
	public void TestLogin(String email, String password) throws Exception {
		MobileUtility.printLogInfo(" TestLogin() ");
		loginElement.login(email, password);

	}

}
