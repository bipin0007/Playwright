package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForSelectorOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

import commonUtilities.ExcelReader;
import extentlisteners.ExtentListeners;

public class BaseTest {

	private Playwright playwright;
	public Browser browser;
	public Page page;
	private static Properties prop = new Properties();
	private static FileInputStream fis;
	private Logger log = Logger.getLogger(this.getClass());
	
	public static ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.xlsx");

	
	private static ThreadLocal<Playwright> pw = new ThreadLocal<>();
	private static ThreadLocal<Browser> br = new ThreadLocal<>();
	private static ThreadLocal<Page> pg = new ThreadLocal<>();
	
	
	
	public static Playwright getPlaywright() {
		return pw.get();
	}
	
	public static Browser getBrowser() {
		return br.get();
	}
	public static Page getPage() {
		return pg.get();
	}

	@BeforeSuite
	public void setUp() {
		log.info("Test Execution Started");
		try {
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		log.info("Log4j configuration loaded successfully.");
		
		}catch(Exception e) {
			log.error("Failed to configure Log4j.", e);
	        throw new RuntimeException("Log4j setup failed.", e);
		}

		try {
			fis = new FileInputStream("./src/test/resources/properties/OR.properties");
		} catch (FileNotFoundException e) {
			log.error("OR.properties file not found.", e);
			throw new RuntimeException("OR.properties file not found.", e);
		}
		try {
			prop.load(fis);
			log.info("OR.properties loaded successfully.");
		} catch (IOException e) {
			log.error("Error occurred while loading OR.properties.", e);
	        throw new RuntimeException("Failed to load OR.properties.", e);
		}

	    if (prop.isEmpty()) {
	        log.error("OR.properties file is empty or not loaded correctly.");
	        throw new RuntimeException("OR.properties file is empty.");
	    }
	}
	
	public void click(String locatorKey) {
		String selector = prop.getProperty(locatorKey);
		if(selector==null) {
			 log.error("Locator not found for key: " + locatorKey);
		        ExtentListeners.getExtent().fail("Locator not found for key: " + locatorKey);
		        Assert.fail("Locator not found for key: " + locatorKey);
		}
		try {
			getPage().locator(prop.getProperty(locatorKey)).click();
			log.info("Clicking on an element : "+locatorKey);
			ExtentListeners.getExtent().info("Clicking on an element : "+locatorKey);
		}
		catch(Throwable t) {
			log.error("error while clicking on an element : "+ t.getMessage());
			ExtentListeners.getExtent().fail("error while clicking on an element : "+ locatorKey+"  Error message is :"+t.getMessage());
			Assert.fail(t.getMessage());
		}		
	}
	
	public boolean isElementPresent(String locatorKey) {
	    String selector = prop.getProperty(locatorKey);
	    if (selector == null) {
	        log.error("Locator not found for key: " + locatorKey);
	        ExtentListeners.getExtent().fail("Locator not found for key: " + locatorKey);
	        return false;
	    }
	    Page currentPage = getPage();
	    currentPage.waitForSelector(selector, new WaitForSelectorOptions().setTimeout(2000));
	    boolean isVisible = currentPage.locator(selector).isVisible();
	    if (isVisible) {
	        log.info("Element is visible: " + locatorKey);
	        ExtentListeners.getExtent().info("Element is visible: " + locatorKey);
	    } else {
	        log.error("Element is not visible: " + locatorKey);
	        ExtentListeners.getExtent().fail("Element is not visible: " + locatorKey);
	    }
	    return isVisible;
	}

 public void type(String value, String locatorKey) {
	try {
		getPage().locator(prop.getProperty(locatorKey)).fill(value);
		log.info("Typing in an Element : "+locatorKey+" and entered the value as :"+value);
		ExtentListeners.getExtent().info("Typing in an Element : "+locatorKey+" and entered the value as :"+value);
	}
	catch(Throwable t) {
		log.error("error while typing on an element : "+ t.getMessage());
		ExtentListeners.getExtent().fail("error while typing on an element : "+ t.getMessage()+" The Entered locater key and and value is :"+ locatorKey + value);
		Assert.fail(t.getMessage());
	}
	}
 
 public void select(String value, String locatorKey) {
		try {
			getPage().selectOption(prop.getProperty(locatorKey),new  SelectOption().setLabel(value));
			log.info("Selecting an Element : "+locatorKey+" and Selected the value as :"+value);
			ExtentListeners.getExtent().info("Selecting an Element : "+locatorKey+" and Selected the value as :"+value);
		}
		catch(Throwable t) {
			log.error("error while Selecting an element : "+ t.getMessage());
			ExtentListeners.getExtent().fail("error while Selecting an element : "+ t.getMessage()+" The Selected locater key and and value is :"+ locatorKey + value);
			Assert.fail(t.getMessage());
		}
		}

	public Browser getBrowser(String browserName) {

		playwright = Playwright.create();
		pw.set(playwright);

		switch (browserName) {
		case "chrome":
			log.info("Launching Google Chrome browser");
			ExtentListeners.getExtent().info("Google Chrome launched");
			return getPlaywright().chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

		case "Firefox":
			log.info("Launching firefox browser");
			ExtentListeners.getExtent().info("Firefox launched");
			return getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));

		case "Safari":
			log.info("Launching safari browser");
			ExtentListeners.getExtent().info("safari launched");
			return getPlaywright().chromium()
					.launch(new BrowserType.LaunchOptions().setChannel("Safari").setHeadless(false));

		case "headless":
			log.info("Launching headless browser");
			ExtentListeners.getExtent().info("headless launched");
			return getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

		case "webkit":
			log.info("Launching WEBKIT browser");
			ExtentListeners.getExtent().info("webkit launched");
			return getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			
		case "chromium":
			log.info("Launching Chromium browser");
			ExtentListeners.getExtent().info("Chromium launched");
			return getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		default:
			throw new IllegalArgumentException("Invalid browser name provided: " + browserName);
		}
	}
	
	public void navigate(Browser browser, String url) {
		if(browser==null) {
			throw new IllegalStateException("Browser instance is not initialized. Call getBrowser() first.");
		}
		this.browser=browser;
		br.set(browser);
		page =getBrowser().newPage();
		pg.set(page);
		getPage().navigate(url);
		log.info("Navigated to the URL :-> " + url);
		ExtentListeners.getExtent().info("The website url :-> "+ url);
		
		 getPage().onDialog(dialog -> {
	            try{
	                Thread.sleep(2000);
	            }catch (Exception e){
	                e.printStackTrace();
	            }dialog. accept(); System.out.println(dialog.message()); });
		
	}
	
	
	@AfterMethod
	public void quit() {
	    if (getPage() != null) {
	    	getBrowser().close();
	        getPage().close();
	        getPlaywright().close();
	    }
	    log.info("Test resources closed successfully.");
	}
}
