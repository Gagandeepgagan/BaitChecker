package com.baitcheckerios.testscripts;

import org.testng.annotations.Test;

import com.baitcheckerios.init.InitializePages;
import com.baitcheckerios.library.Baselib;


public class Test1 extends Baselib {
			

		
		@Test(priority = 1, description = "Login to app", enabled = false)
		  public void loginApp() throws Exception {
			 InitializePages init = new InitializePages(androidDriver, iosDriver, driver);
			  init.loginPageAndroid.login("gagandeep.bains@vtnetzwelt.com", "Password@123");
		
	 
		}
		
	
		
		
	}
