package rough;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class LoginTest extends BaseTest{
	
	
	@Test
	public void doLogin() {
		Browser browser = getBrowser("chromium");
		navigate(browser, "http://www.google.com");	
		type("Hello Playwright", "searchBox");
		
	}
	
	@Test
	public void doClick() {
		doLogin();
		click("googlesearch");
	}
	
	@Test
	public void doGmailLogin() {
		Browser browser = getBrowser("Firefox");
		navigate(browser, "http://www.gmail.com");	
		type("java@way2automation.com", "username");
	}
	
	
	
	
	
	

}
