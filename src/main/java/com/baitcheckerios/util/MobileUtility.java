package com.baitcheckerios.util;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.baitcheckerios.listener.MyExtentListeners;
import com.beust.jcommander.internal.Lists;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;

public class MobileUtility {
	public static final String VERIFYCLICKMESSAGE = "Verify user is able to click on ";
	public static final String DIRECTIONDOWN = "direction";
	public static final String DIRECTIONUP = "direction";
	
	public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private MobileUtility() {
	}

	public static void printLogInfo(String message) {
		logger.info(message);
	}

	public static void waitForElementToLoad(int milliSeconds) throws InterruptedException {
		Thread.sleep(milliSeconds);
	}
	
	

	public static void clickOnElement(WebElement element,AppiumDriver<?> driver, String elementName) throws Exception {

		try {

			printLogInfo("Verifying element is displayed or not");
			waitForElementToLoad(4000);
			waitForElement(element, driver, elementName, 10);
			element.click();
			waitForElementToLoad(4000);
			printLogInfo("After Click on: " + elementName);
			MyExtentListeners.test.info(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ " ||  User is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName
					+ "\'" + "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName
					+ "\'" + " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			throw error;
		}

	}

	public static void clickElement(WebElement element, AppiumDriver<?> driver, String elementName)
			throws Exception {
		String s = "After Click on: " + elementName;

		try {

			printLogInfo("---------Verifying element is displayed or not ---------");
			waitForElementToLoad(4000);
			waitForElement(element, driver, elementName, 10);
			element.click();
			waitForElementToLoad(4000);
			printLogInfo(s);
			MyExtentListeners.test.info(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ " ||  User is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName
					+ "\'" + "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName
					+ "\'" + " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			throw error;
		}

	}
	public static void clickElement(WebElement element, WebDriver driver, String elementName)
			throws Exception {
		String s = "After Click on: " + elementName;

		try {

			printLogInfo("---------Verifying element is displayed or not ---------");
			waitForElementToLoad(4000);
		
			element.click();
			waitForElementToLoad(4000);
			printLogInfo(s);
			MyExtentListeners.test.info(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ " ||  User is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName
					+ "\'" + "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName
					+ "\'" + " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture((AppiumDriver<?>) driver, elementName));
			throw error;
		}

	}

	public static void waitForElement(WebElement element,AppiumDriver<?> driver, String elementName,
			int seconds) throws IOException, InterruptedException {
		try {
			printLogInfo("Waiting for visibility of element: " + elementName);
			MobileUtility.isEleDisplayed(element, 2, 1, elementName);
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			printLogInfo("Element is visible : " + elementName);
		} catch (ElementNotVisibleException e) {
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			printLogInfo("---------Element is not visible---------" + elementName);
		} catch (AssertionError e) {
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			printLogInfo("---------Element is not visible---------" + elementName);
			throw e;
		}
	}
	
	public static void waitForElement(WebElement element,WebDriver driver, String elementName,
			int seconds) throws IOException, InterruptedException {
		try {
			printLogInfo("Waiting for visibility of element: " + elementName);
			MobileUtility.isEleDisplayed(element, 2, 1, elementName);
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			printLogInfo("Element is visible : " + elementName);
		} catch (ElementNotVisibleException e) {
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			printLogInfo("---------Element is not visible---------" + elementName);
		} catch (AssertionError e) {
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			printLogInfo("---------Element is not visible---------" + elementName);
			throw e;
		}
	}
	
	

	public static boolean isEleDisplayed(WebElement element, int seconds, int loop, String elementName)
			throws IOException, InterruptedException {

		boolean flag = false;

		int count = loop;
		while (count > 0) {
			try {
				printLogInfo("---------Verifying element is displayed or not ---------");
				count--;
				element.isDisplayed();
				flag = true;
				break;

			} catch (RuntimeException e) {
				Thread.sleep((long) seconds * 1000);
				flag = false;
			}
			if (flag) {
				MyExtentListeners.test.info("Verify " + "\'" + elementName + "\'" + " is displayed  || " + "\'"
						+ elementName + "\'" + " is displayed ");
			}else {
			MyExtentListeners.test.info("Verify " + "\'" + elementName + "\'" + " is displayed  || " + "\'"
					+ elementName + "\'" + " is not displayed ");}
		}
		return flag;
	}

	public static void tapycoordinates(IOSDriver driver) {

		new TouchAction(driver).tap(point(235, 179)).waitAction(waitOptions(ofMillis(1000))).release().perform();

	}

	public static void tapycoordinates1(IOSDriver driver, int x, int y, String elementname) {

		new TouchAction(driver).tap(point(x, y)).waitAction(waitOptions(ofMillis(1000))).release().perform();
		printLogInfo("Element :" + elementname + "is clicked ");

		MyExtentListeners.test.info("Verify user is able to click on  " + elementname + " is clicked ");

	}

	public static String capture(AppiumDriver<?> driver, String screenShotName) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String dest = MyExtentListeners.screenShotPath + screenShotName + ".png";
		printLogInfo(dest);
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}
	public static String capture(WebDriver driver, String screenShotName) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest = MyExtentListeners.screenShotPath + screenShotName + ".png";
		printLogInfo(dest);
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}

	public static void horizontalSwipeByPercentage(IOSDriver driver, double startPercentage, double endPercentage,
			double anchorPercentage) {
		printLogInfo("I am doing horizontalSwipeByPercentage...");
		Dimension size = driver.manage().window().getSize();
		int anchor = (int) (size.height * anchorPercentage);
		int startPoint = (int) (size.width * startPercentage);
		int endPoint = (int) (size.width * endPercentage);
		new TouchAction(driver).press(point(startPoint, anchor)).waitAction(waitOptions(ofMillis(1000)))
				.moveTo(point(endPoint, anchor)).release().perform();
	}

	public static void swipebyautomation(IOSDriver driver) {

		new TouchAction(driver).tap(point(235, 786)).waitAction(waitOptions(ofMillis(3000))).perform();

	}

//	public static void testingScrollToElement(WebElement element,AppiumDriver<?> driver) throws InterruptedException {
//		Map<String, Object> parms = new HashMap<>();
//		parms.put(DIRECTIONDOWN, "down");
//		parms.put("element", ((RemoteWebElement) element).getId());
//		driver.executeScript("mobile:scroll", parms);
//		Thread.sleep(2000);
//		element.click();
//		Thread.sleep(2000);
//	}

	public static void iosScrollToElement1(WebElement element,AppiumDriver<?> driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> parms = new HashMap<>();
		parms.put(DIRECTIONDOWN, "up");
		parms.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile:scroll", parms);

		WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust the timeout as needed
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		Thread.sleep(1000);

	}

	public static void scrolltoparticulartext(IOSDriver driver, String text) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<>();
		scrollObject.put(DIRECTIONUP, "up");
		scrollObject.put("name", text);
		js.executeScript("mobile: swipe", scrollObject);
		Thread.sleep(1000);
			

	}

	public static void sendNumericValueInsideActiveKeypadAndPressEnter(String value,AppiumDriver<?> driver) {
		driver.switchTo().activeElement().sendKeys(value);
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
	}

	public static void type(WebElement element, String value, String elementName, AppiumDriver<?> driver)
			throws Exception {
		try {
			printLogInfo("---------Method type  ---------");
			Thread.sleep(5000);
			element.click();
			element.sendKeys(value);
			hideKeyboard(driver);
			printLogInfo("---------hide keyboard  ---------");
			MyExtentListeners.test
					.info("Verify user is able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'"
							+ " || User is able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify user is not able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'"
							+ " || User is not able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'",
					ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("Unable to type on " + elementName);
		} catch (ElementNotVisibleException e) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify user is not typed " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'"
							+ " || User is not able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'",
					ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			Assert.fail("Unable to type in " + elementName);
		}
	}
	public static void type(WebElement element, String value, String elementName, WebDriver driver)
			throws Exception {
		try {
			printLogInfo("---------Method type  ---------");
			Thread.sleep(5000);
			element.click();
			element.sendKeys(value);
			
			printLogInfo("---------hide keyboard  ---------");
			MyExtentListeners.test
					.info("Verify user is able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'"
							+ " || User is able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify user is not able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'"
							+ " || User is not able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'",
					ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture((AppiumDriver<?>) driver, elementName));
			Assert.fail("Unable to type on " + elementName);
		} catch (ElementNotVisibleException e) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify user is not typed " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'"
							+ " || User is not able to type " + "\'" + value + "\'" + "in " + "\'" + elementName + "\'",
					ExtentColor.RED));

			MyExtentListeners.test.addScreenCaptureFromPath(capture((AppiumDriver<?>) driver, elementName));
			Assert.fail("Unable to type in " + elementName);
		}
	}

	public static void hideKeyboard(AppiumDriver<?> driver) {
		try {
			driver.hideKeyboard();
			Thread.sleep(2000);
		} catch (Throwable e) {

		}
	}


	public static String gettext(WebElement elename, String elementName) throws IOException, InterruptedException {
		printLogInfo("--------- get text from element  ---------");
		String eleText = null;
		try {
			isEleDisplayed(elename, 2, 5, elementName);
			eleText = elename.getText();
		} catch (ElementNotVisibleException e) {
			Assert.fail("Unable to fetch text from " + "\'" + elename + "\'");

		}
		return eleText;
	}

	public static void scrollDownIos(IOSDriver driver) throws InterruptedException {
		new TouchAction(driver).longPress(PointOption.point(150, 690)).waitAction().moveTo(PointOption.point(147, 403))
				.release().perform();

	}

	public static void swipehorizontal(IOSDriver driver, int x1, int x2, int y) throws InterruptedException {
		new TouchAction(driver).press(point(x1, y)).waitAction(waitOptions(ofMillis(1000))).moveTo(point(x2, y))
				.release().perform();
		Thread.sleep(5000);
	}

	public static void tapbycodinates1(IOSDriver driver, int x, int y) throws InterruptedException {

		new TouchAction(driver).tap(point(x, y)).waitAction(waitOptions(ofMillis(2000))).release().perform();

	}
	
	 public static void scrollUsingFling(AppiumDriver<?> driver) {
	        try {
	            printLogInfo("# Before (scrollUsingFling) #");
	            driver.findElement(MobileBy.AndroidUIAutomator(
	                    "new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(500000)"));
	            printLogInfo("# After (scrollUsingFling) #");
	            waitForElementToLoad(4000);
	        } catch (Exception e) {
	        	printLogInfo("# catch: scrollUsingFling #");
	        }
	    }
}
