package extentlisteners;




import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page.ScreenshotOptions;

import base.BaseTest;


//import base.BaseTest;


public class ExtentManager {

	private static ExtentReports extent;
	public static String fileName;
	
	

	    public static ExtentReports createInstance(String fileName) {
	       ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	       
	        //ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + fileName);

	        
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        htmlReporter.config().setCss("body { font-family: Arial, sans-serif; }");
	        htmlReporter.config().setJs("console.log('Extent Report Loaded');");
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("Automation Tester", "Bipin Kumar Singh");
	        extent.setSystemInfo("Organization", "Hike");
	        extent.setSystemInfo("Build no", "release_1234");
	        
	        
	        return extent;
	    }

	
	    
		public static void captureScreenshot() throws IOException {
			
			Date d = new Date();
			 fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";

			//BaseTest.getPage().screenshot(new ScreenshotOptions().setPath(Paths.get("./reports/"+fileName)));
			BaseTest.getPage().screenshot(new ScreenshotOptions().setPath(Paths.get(System.getProperty("user.dir") + "/reports/" + fileName)));

			
//		File screeshot = ((TakesScreenshot)  BaseTest.driver).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(screeshot, new File(".//reports//"+fileName));
		}
		
		

//		public static void captureElementScreenshot(WebElement element) throws IOException {
//			
//			Date d = new Date();
//			String fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
//
//			
//			
//			File screeshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(screeshot, new File(".//screenshot//"+"Element_"+fileName));
//		}

	 


	}
