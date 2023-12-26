package com.baitcheckerios.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.baitcheckerios.library.GenericLib;

public class MyExtentListeners implements ITestListener {

	public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
	public static String screenShotPath;
	public static String screenshotName;
	public static int iPassCount = 0;
	public static int iFailCount = 0;
	public static int iSkippedCount = 0;
	public static String sStartTime;
	public static String sEndTime;
	public static long lTotalExecutionTime;
	protected static List<String> sStatus = new ArrayList<>();
	public static List<String> sStartMethodTime = new ArrayList<>();
	public static List<String> sMethodEndTime = new ArrayList<>();
	public static long startTime;
	public static long endtTime;
	String className;
	public static String folName;

	/** Description: On Test Starts Create a report based on @Test Method Name */
	public void onTestStart(ITestResult result) {

		className = result.getMethod().getMethodName();
		className = className.substring(className.lastIndexOf(".") + 1, className.length());
		test = extent.createTest(className);
		startTime = result.getStartMillis();
		sStartMethodTime.add(startTime + "");
		parentTest.set(test);
		test.info(className + " has started");
		logger.info(className + " has started");
	}

	/** Description: On Test Success Write Status Passed to Extent Report */
	public void onTestSuccess(ITestResult result) {
		endtTime = result.getEndMillis();
		sStatus.add("Passed");
		test.pass(MarkupHelper.createLabel(className + " case has PASSED", ExtentColor.GREEN));
		try {
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description: On Test Failure Write Status Failed to Extent Report With Label
	 * RED
	 */
	public void onTestFailure(ITestResult result) {
		sStatus.add("Failed");
		test.fail(MarkupHelper.createLabel(className + " test script has FAILED", ExtentColor.RED));
		try {
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description: On Test Skipped Write Status Skipped to Extent Report With Label
	 * YELLOW
	 */
	public void onTestSkipped(ITestResult result) {
		sStatus.add("Skipped");
		test.skip(MarkupHelper.createLabel(className + " test script has SKIPPED", ExtentColor.YELLOW));
		try {
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onStart(ITestContext context) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String sDate = sdf.format(date);
		folName = "Run-" + sDate;
		sStartTime = sDate;
		String testOutputDir = GenericLib.SDIRPATH + "/test-output";
		String htmlDir = GenericLib.SDIRPATH + "/reports/";
		screenShotPath = GenericLib.SDIRPATH + "/reports" + "/screenshots/";
		String reportpath = GenericLib.SDIRPATH + "/src/test/resources/extent-config.xml";
		File testOutputFile = new File(testOutputDir);

		if (!testOutputFile.exists()) {
			return;
		}
		/* Delete the test output folder */
		if (testOutputFile.isDirectory()) {
			boolean deleted = testOutputFile.delete();
			if (!deleted) {
				// logger.info("Failed to delete the directory: " + testOutputFile.getAbsolutePath());
			}
		}

		/* Setting Extent Report Location */
		File file = new File(htmlDir);
		if (!(file.exists())) {
			file.mkdirs();
//			logger.info("--Extent folder created");

		}
		
		String filepath = htmlDir + "Report" +sDate+ ".html";
	//	logger.info(filepath);
		File file1 = new File(filepath);
		if (!(file1.exists())) {
			try {
				boolean created = file1.createNewFile();
				if (created) {
					//logger.info("File is created");
				} else {
					//logger.info("Failed to create the file: " + file1.getAbsolutePath());

				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		/* Setting ScreenShot Report Location */
		File file3 = new File(screenShotPath);
		if (!(file3.exists())) {
			file3.mkdirs();
		//	logger.info("--Screenshot folder created");
		}

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(file1);
		extent = ExtentManager.createInstance(file.toString());
		htmlReporter.config().setDocumentTitle(filepath);

		try {
			htmlReporter.loadXMLConfig(reportpath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		try {
			iPassCount = context.getPassedTests().size();
			iFailCount = context.getFailedTests().size();
			iSkippedCount = context.getSkippedTests().size();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String sDate = sdf.format(date);
			sEndTime = sDate;
			lTotalExecutionTime = context.getEndDate().getTime() - context.getStartDate().getTime();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}