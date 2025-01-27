package testcases;


import java.util.Hashtable;
import org.testng.annotations.Test;
import base.BaseTest;
import commonUtilities.Constants;
import commonUtilities.DataProviders;
import commonUtilities.DataUtils;
import commonUtilities.ExcelReader;

public class AddCustomerTest extends BaseTest{

	@Test(dataProviderClass = DataProviders.class, dataProvider ="bankManagerDP")
	public void addCustomerTest(Hashtable<String, String>data) {
		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtils.checkExecution("BankManagerSuite", "AddCustomerTest", data.get("Runmode"), excel);
		
		browser= getBrowser(data.get("browser"));
		navigate(browser, Constants.URL);
		click("bmlBtn_CSS");
		click("AddCusBtn_CSS");
		type(data.get("firstname"),"firstname_CSS");
		type(data.get("lastname"),"lastname_XPATH");
		type(data.get("postcode"),"postcode_css");
		click("addbtn_CSS");
		
		
		
		
		
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
