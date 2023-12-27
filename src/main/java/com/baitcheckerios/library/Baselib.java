package com.baitcheckerios.library;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Baselib {

	public AndroidDriver androidDriver;
	public IOSDriver iosDriver;
	public WebDriver driver;
	String projectpath = System.getProperty("user.dir");

	@BeforeSuite(alwaysRun = true)
	@Parameters("platform")
	public void launchApp(@Optional("web") String platform) throws Exception {

		/* to run the test: mvn test -Dplatform=ios */

		if (platform.equalsIgnoreCase("android")) {

			File file = new File(projectpath + "/src/test/resources/application/Baitchecker.apk");
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
//			desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "08261JECB11358");
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
			desiredCapabilities.setCapability("newCommandTimeout", 450000);
			desiredCapabilities.setCapability("autoGrantPermissions", true);
			desiredCapabilities.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
			androidDriver = new AndroidDriver(new URL("http://0.0.0.0:4723"), desiredCapabilities);
			androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if (platform.equalsIgnoreCase("ios")) {

			File file = new File(projectpath + "/src/test/resources/application/ConsumerScan.app");
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

			desiredCapabilities.setCapability("appium:automationName", "XCUITest");
			desiredCapabilities.setCapability("appium:platformName", "ios");
			// desiredCapabilities.setCapability("appium:osVersion", "17.1");
			// desiredCapabilities.setCapability("appium:deviceName", "Surender’s
			// iPhone");
			desiredCapabilities.setCapability("appium:xcodeSignId", "iphone Developer");
			desiredCapabilities.setCapability("appium:udid", "00008110-0002048A022A801E");
//			desiredCapabilities.setCapability("appium:udid", "00008020-001214382689002E");
			desiredCapabilities.setCapability("showXcodeLog", true);
			desiredCapabilities.setCapability("xcodeOrgId", "4G6NXA9PX6");
//			desiredCapabilities.setCapability("app", /* file.getAbsolutePath() */ "com.apple.mobileslideshow");

			iosDriver = new IOSDriver(new URL("http://0.0.0.0:4723"), desiredCapabilities);
			// iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if (platform.equalsIgnoreCase("web")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.setExperimentalOption("debuggerAddress", "localhost:60734");
			driver = new ChromeDriver(opt);

			/*
			 * Capabilities cap=((RemoteWebDriver) driver).getCapabilities(); Map<String,
			 * Object> myCap=cap.asMap(); System.out.println(myCap);
			 */

//			driver.manage().window().maximize();
//			driver.navigate().refresh();
		} else if (platform.equalsIgnoreCase("safari")) {

			driver = new SafariDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

	}

	@AfterSuite(enabled = true)
	@Parameters("platform")
	public void teardown(String platform) {

		if (platform.equalsIgnoreCase("web")) {
//			driver.quit();
		}

	}
}
