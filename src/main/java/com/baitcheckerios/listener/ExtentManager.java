package com.baitcheckerios.listener;

import com.aventstack.extentreports.ExtentReports;


public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance("./reports/HtmlReport.html");
		return extent;
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentReports extent;
		extent = new ExtentReports();
		return extent;
	}
}