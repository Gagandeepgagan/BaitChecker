package com.baitcheckerios.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baitcheckerios.library.Baselib;
import com.baitcheckerios.pages.EmployeeElement;
import com.baitcheckerios.pages.EmployeePageAndroid;
import com.baitcheckerios.pages.EmployeePageIos;
import com.baitcheckerios.pages.EmployeePageWeb;
import com.baitcheckerios.util.MobileUtility;

public class CreateEmployeeTest extends Baselib {

	private EmployeeElement employeeElement;

	@BeforeClass
	@Parameters("platform")
	public void setUp(@Optional("web") String platform) {
		if (platform.equals("web") || platform.equals("safari")) {
			System.out.println("WEB");
			employeeElement = new EmployeePageWeb(driver);

		} else if (platform.equals("ios")) {
			System.out.println("IOS");
			employeeElement = new EmployeePageIos(iosDriver);

		} else if (platform.equals("android")) {
			System.out.println("ANDROID");
			employeeElement = new EmployeePageAndroid(androidDriver);
		} else {
			throw new IllegalArgumentException("Invalid platform provided");
		}
	}


	@Test(priority = 2)
	public void TestCreateEmployee() throws Exception {
		MobileUtility.printLogInfo(" TestCreateEmployee() ");
		employeeElement.createEmployee();;
	}
}
