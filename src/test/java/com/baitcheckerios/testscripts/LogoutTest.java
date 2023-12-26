package com.baitcheckerios.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baitcheckerios.library.Baselib;
import com.baitcheckerios.pages.LogoutPageWeb;
import com.baitcheckerios.pages.LogoutElement;

public class LogoutTest extends Baselib {

	private LogoutElement logoutElement;

	@BeforeClass
	@Parameters("platform")
	public void setUp(@Optional("web") String platform) {
		if (platform.equals("web") || platform.equals("safari")) {
			System.out.println("WEB");
			logoutElement = new LogoutPageWeb(driver);

		} else if (platform.equals("ios")) {
			System.out.println("IOS");
			

		} else if (platform.equals("android")) {
			System.out.println("ANDROID");
			
		} else {
			throw new IllegalArgumentException("Invalid platform provided");
		}
	}


	@Test(priority = 3, enabled = true/* , dependsOnMethods = "TestLogin" */)
	public void TestLogout() throws Exception {
		System.out.println(" TestLogout() ");
		logoutElement.logout();
	}
}
