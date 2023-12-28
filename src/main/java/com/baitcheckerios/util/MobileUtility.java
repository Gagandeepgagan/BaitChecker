package com.baitcheckerios.util;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.baitcheckerios.library.GenericLib;
import com.baitcheckerios.listener.MyExtentListeners;
import com.google.common.collect.ImmutableList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
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
	
	public static void scrollByCoordinates(AndroidDriver<MobileElement> driver) {
		 new TouchAction(driver).press(PointOption.point(500, 1800)).waitAction()
         .moveTo(PointOption.point(500, 400))
         .release().perform();
	}
	
	public static void stopAParticularAppActivity(AndroidDriver<MobileElement> driver) {
        try {

        	printLogInfo("# inside stopAParticularAppActivity # ");

        	  ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
            new TouchAction(driver).press(PointOption.point(500, 2200)).waitAction()
                    .moveTo(PointOption.point(500, 100))
                    .release().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String sDate = sdf.format(date);
		return sDate;
	}

	public static void checkValidationMsg(String screenshotName, WebDriver driver, WebElement element)
			throws IOException {
		String sshotName = screenshotName + MobileUtility.getDate();
		MobileUtility.capture(driver, sshotName);
		String validateMsg = MobileUtility.getFlashMsgText(driver, element);
		if (validateMsg != null && !validateMsg.isEmpty()) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(
					"Verify if user is able to create customer ||  " + "\'" + validateMsg + "\'", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(GenericLib.screenShotPath + sshotName + ".png");
			Assert.fail("Failed: " + validateMsg);
		}
	}

	public static void waitForToastMessage(AppiumDriver<MobileElement> driver, String toastMessage,
			int timeoutSeconds) {
		for (int i = 0; i < timeoutSeconds; i++) {
			try {
				MobileElement toastElement = driver.findElement(By.xpath("//android.widget.Toast"));
				String actualToastMessage = toastElement.getText();
				System.out.println("actualToastMessage: " + actualToastMessage);
				if (actualToastMessage.equals(toastMessage)) {
					return; // Toast message found, exit the loop
				}
			} catch (Exception e) {
				// Toast not found, wait and retry
			}
			// Wait for a short period before retrying
//            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		}
		// If the loop completes without finding the toast, throw an exception or log an
		// error
		throw new RuntimeException("Toast message not found within the specified timeout.");
	}

	public static void clickOnElement(WebElement element, AppiumDriver<?> driver, String elementName) throws Exception {

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
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			throw error;
		}

	}

	public static void clickElement(WebElement element, AppiumDriver<?> driver, String elementName) throws Exception {
		String s = "After Click on: " + elementName;

		try {

			printLogInfo("---------Verifying element : " + elementName + " is displayed or not ---------");
			waitForElement(element, driver, elementName, 10);
			element.click();
			waitForElementToLoad(1000);
			printLogInfo(s);
			MyExtentListeners.test.info(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ " ||  User is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ "  || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));

			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			throw error;
		}

	}

	public static void clickElementSimple(WebElement element, AppiumDriver<?> driver, String elementName)
			throws Exception {
		String s = "After Click on: " + elementName;

		try {

			printLogInfo("---------Verifying element : " + elementName + " is displayed or not ---------");
			waitForElement(element, driver, elementName, 10);
			element.click();
			waitForElementToLoad(4000);
			printLogInfo(s);

		} catch (Exception error) {

		}

	}

	public static void clickElement(WebElement element, WebDriver driver, String elementName) throws Exception {
		String s = "After Click on: " + elementName;

		try {

			printLogInfo("---------Verifying element is displayed or not ---------");
			waitForElement(element, driver, elementName, 10);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript(
					"arguments[0].setAttribute('style', 'border:2px solid green; background:lightblue');", element);
			element.click();
			waitForElementToLoad(2000);
			printLogInfo(s);
			MyExtentListeners.test.info(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ " ||  User is able to click on " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test
					.fail(MarkupHelper.createLabel(
							VERIFYCLICKMESSAGE + "\'" + elementName + "\'" + "<font color=Cyan>"
									+ "  || User is not able to click on " + "\'" + elementName + "\'",
							ExtentColor.RED));

			throw error;
		} catch (Exception error) {
			MyExtentListeners.test.fail(MarkupHelper.createLabel(VERIFYCLICKMESSAGE + "\'" + elementName + "\'"
					+ " || User is not able to click on " + "\'" + elementName + "\'", ExtentColor.RED));
			MyExtentListeners.test.addScreenCaptureFromPath(capture(driver, elementName));
			throw error;
		}

	}

	public static void waitForElement(WebElement element, AppiumDriver<?> driver, String elementName, int seconds)
			throws IOException, InterruptedException {
		try {
			printLogInfo("Waiting for visibility of element: " + elementName);
			isEleDisplayed(element, 2, 1, elementName);
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

	public static void waitForElement(WebElement element, WebDriver driver, String elementName, int seconds)
			throws IOException, InterruptedException {
		try {
			printLogInfo("Waiting for visibility of element: " + elementName);
			isEleDisplayed(element, 2, 1, elementName);
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

	public static boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();

		} catch (Exception e) {
			return false;
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
				printLogInfo("element not displayed");
				Thread.sleep((long) seconds * 1000);
				flag = false;
			}

		}
		if (flag) {
			MyExtentListeners.test.info("Verify " + "\'" + elementName + "\'" + " is displayed  || " + "\'"
					+ elementName + "\'" + " is displayed ");
		} else {
			MyExtentListeners.test.info("Verify " + "\'" + elementName + "\'" + " is displayed  || " + "\'"
					+ elementName + "\'" + " is not displayed ");
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

	public static String getFlashMsgText(WebDriver driver, WebElement flashMessageLocator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement flashMessageElement = wait.until(ExpectedConditions.visibilityOf(flashMessageLocator));
			String flashMessageText = flashMessageElement.getText();
			printLogInfo("Flash Message: " + flashMessageText);
			return flashMessageText;
		} catch (TimeoutException e) {
			printLogInfo("Flash Message did not appear.");
			return "";
		}
	}

	public static void scrollinIOS(AppiumDriver<?> driver) {
		try {
			printLogInfo("# Before (scrollinIOS) #");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<>();
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);
			printLogInfo("# After (scrollinIOS) #");
			waitForElementToLoad(4000);
		} catch (Exception e) {
			printLogInfo("# catch: scrollinIOS #");
		}
	}

	public static void scrollinIOStoElement(WebElement element, AppiumDriver<?> driver) {
		try {
			printLogInfo("# Before (scrollinIOS) #");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<>();
			scrollObject.put("direction", "down");
			scrollObject.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile: scroll", scrollObject);
			printLogInfo("# After (scrollinIOS) #");
			waitForElementToLoad(4000);
		} catch (Exception e) {
			printLogInfo("# catch: scrollinIOS #");
		}
	}

	public static void iosScrollToElement1(WebElement element, AppiumDriver<?> driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> parms = new HashMap<>();
		parms.put(DIRECTIONDOWN, "up");
		parms.put("element", ((RemoteWebElement) element).getId());
		js.executeScript("mobile:scroll", parms);

		WebDriverWait wait = new WebDriverWait(driver, 10); // Adjust the timeout as needed
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		waitForElementToLoad(1000);

	}

	public static void scrolltoparticulartext(IOSDriver driver, String text) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<>();
		scrollObject.put(DIRECTIONUP, "up");
		scrollObject.put("name", text);
		js.executeScript("mobile: swipe", scrollObject);
		waitForElementToLoad(1000);

	}

	public static void sendNumericValueInsideActiveKeypadAndPressEnter(String value, AppiumDriver<?> driver) {
		driver.switchTo().activeElement().sendKeys(value);
		driver.switchTo().activeElement().sendKeys(Keys.ENTER);
	}

	public static void type(WebElement element, String value, String elementName, AppiumDriver<?> driver)
			throws Exception {
		try {
			printLogInfo("---------Method type  ---------");
			waitForElementToLoad(1000);
			element.click();
			element.clear();
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

	public static String encryptPassword(String password) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		SecretKey secretKey = keyGenerator.generateKey();

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] encryptedPassword = cipher.doFinal(password.getBytes());

		return new String(encryptedPassword);
	}

	public static String decryptPassword(String encryptedPassword) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		SecretKey secretKey = keyGenerator.generateKey();

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] decryptedPassword = cipher.doFinal(encryptedPassword.getBytes());

		return new String(decryptedPassword);
	}

	public static void type(WebElement element, String value, String elementName, WebDriver driver) throws Exception {
		try {
			printLogInfo("---------Method type  ---------");
			waitForElementToLoad(5000);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript(
					"arguments[0].setAttribute('style', 'border:2px solid green; background:lightblue');", element);
			element.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
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

	public static void typeEncrypted(WebElement element, String value, String elementName, WebDriver driver)
			throws Exception {
		try {
			printLogInfo("---------Method type  ---------");
			waitForElementToLoad(5000);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript(
					"arguments[0].setAttribute('style', 'border:2px solid green; background:lightblue');", element);
			element.click();
			element.sendKeys(value);

			printLogInfo("---------hide keyboard  ---------");
			MyExtentListeners.test.info("Verify user is able to type " + "\'" + encryptPassword(value) + "\'" + "in "
					+ "\'" + elementName + "\'" + " || User is able to type " + "\'" + encryptPassword(value) + "\'"
					+ "in " + "\'" + elementName + "\'");
		} catch (AssertionError error) {
			MyExtentListeners.test
					.fail(MarkupHelper.createLabel(
							"Verify user is not able to type " + "\'" + encryptPassword(value) + "\'" + "in " + "\'"
									+ elementName + "\'" + " || User is not able to type " + "\'"
									+ encryptPassword(value) + "\'" + "in " + "\'" + elementName + "\'",
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
			waitForElementToLoad(2000);
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
			driver.findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(500000)"));
			printLogInfo("# After (scrollUsingFling) #");
			waitForElementToLoad(4000);
		} catch (Exception e) {
			printLogInfo("# catch: scrollUsingFling #");
		}
	}

	static double SCROLL_RATIO = 0.5;
	static Duration SCROLL_DUR = Duration.ofMillis(500);

	public static void scrollNclick(IOSDriver driver, WebElement byEl) {
		String prevPageSource = "";
		boolean flag = false;

		while (!isEndOfPage(driver, prevPageSource)) {
			prevPageSource = driver.getPageSource();

			try {
				byEl.isDisplayed();
				System.out.println("byEl.isDisplayed()");
			} catch (org.openqa.selenium.NoSuchElementException e) {
				scrollinIOS(driver);
				System.out.println("catch: byEl.isDisplayed()");
			}
		}

	}

	public enum ScrollDirection {
		UP, DOWN, LEFT, RIGHT
	}

	public static void scroll(IOSDriver driver, ScrollDirection dir, double scrollRatio) {

		if (scrollRatio < 0 || scrollRatio > 1) {
			throw new Error("Scroll distance must be between 0 and 1");
		}
		Dimension size = driver.manage().window().getSize();
		System.out.println(size);
		Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
		int bottom = midPoint.y + (int) (midPoint.y * scrollRatio);
		int top = midPoint.y - (int) (midPoint.y * scrollRatio);
		// Point Start = new Point(midPoint.x, bottom );
		// Point End = new Point(midPoint.x, top );
		int left = midPoint.x - (int) (midPoint.x * scrollRatio);
		int right = midPoint.x + (int) (midPoint.x * scrollRatio);

		if (dir == ScrollDirection.UP) {
			swipe(driver, new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
		} else if (dir == ScrollDirection.DOWN) {
			swipe(driver, new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
		} else if (dir == ScrollDirection.LEFT) {
			swipe(driver, new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
		} else {
			swipe(driver, new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
		}
	}

	protected static void swipe(IOSDriver driver, Point start, Point end, Duration duration) {

		PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence swipe = new Sequence(input, 0);
		swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
		swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		/*
		 * if (isAndroid) { duration = duration.dividedBy(ANDROID_SCROLL_DIVISOR); }
		 * else { swipe.addAction(new Pause(input, duration)); duration = Duration.ZERO;
		 * }
		 */
		swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
		swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		((AppiumDriver) driver).perform(ImmutableList.of(swipe));
	}

	public static boolean isEndOfPage(IOSDriver driver, String pageSource) {
		return pageSource.equals(driver.getPageSource());
	}
}
