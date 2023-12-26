package com.baitcheckerios.library;


import java.util.logging.Logger;


public class GenericLib {
	public static final String SDIRPATH = System.getProperty("user.dir");

	public final  Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static String sConfigPath = SDIRPATH + "/src/main/resources/config/config.xlsx";
	public static String sValidationFile = SDIRPATH + "/src/test/resources/testdata/validations.properties";
	public static String sTestData = SDIRPATH + "/src/test/resources/testdata/testdata.xlsx";
	public static String apkFilePath = SDIRPATH+ "/src/test/resources/app/MyScan_1.157.249.apk";
	public static String screenShotPath=SDIRPATH+"/reports/screenshots/";


	
		
}
