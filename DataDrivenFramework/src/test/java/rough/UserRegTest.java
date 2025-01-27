package rough;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class UserRegTest extends BaseTest{
	
	@Test
	public void doFillForm() {
		Browser browser =getBrowser("chromium");
		navigate(browser, "https://way2automation.com/way2auto_jquery/index.php");
		type("Bipin Singh", "regName");
		type("+919773889662", "regPhone");
		type("bipin1302@gmail.com", "regEmail");
		type("New Delhi", "regCity");
		type("bipins", "regUserName");
		type("Bipin Singh", "regPass");
		click("regSubmit");
		click("testingWebsite");
		
		
	}
	
	
	
	
	

}
