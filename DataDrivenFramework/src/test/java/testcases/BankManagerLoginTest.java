package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;


import base.BaseTest;

public class BankManagerLoginTest extends BaseTest {
	

	@Test
	public void LoginAsBankManager() {
		Browser browser= getBrowser("chromium");
		navigate(browser, "https://www.way2automation.com/angularjs-protractor/banking/#/login");
		click("bmlBtn_CSS");
		System.out.println(isElementPresent("AddCusBtn_CSS"));
		isElementPresent("openaccount_CSS");
		isElementPresent("custab_CSS");
        isElementPresent("mainheading_CSS");
        Assert.assertTrue(isElementPresent("AddCusBtn_CSS"),"Bank Manager not logged in");	
	}
	
	
	
	
	
}
