package com.baitcheckerios.init;

import org.openqa.selenium.WebDriver;

import com.baitcheckerios.pages.LoginPageAndroid;
import com.baitcheckerios.pages.LoginPageIos;
import com.baitcheckerios.pages.LoginPageWeb;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class InitializePages{

	
	public final LoginPageWeb loginPageWeb;
	public final LoginPageAndroid loginPageAndroid;
	public final LoginPageIos loginPageIos;
	
	
	
	public InitializePages(AndroidDriver androidDriver, IOSDriver iosDriver, WebDriver webDriver) {
	
		
	
		loginPageWeb = new LoginPageWeb(webDriver);
		loginPageAndroid = new LoginPageAndroid(androidDriver);
		loginPageIos = new LoginPageIos(iosDriver);
	
		
	}
}


	
	
