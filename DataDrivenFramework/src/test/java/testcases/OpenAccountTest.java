package testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import base.BaseTest;
import commonUtilities.Constants;
import commonUtilities.DataProviders;
import commonUtilities.DataUtils;
import commonUtilities.ExcelReader;

public class OpenAccountTest extends BaseTest{
	
	@Test(dataProviderClass = DataProviders.class, dataProvider ="bankManagerDP")
	public void openAccountTest(Hashtable<String, String>data) {
		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtils.checkExecution("BankManagerSuite", "OpenAccountTest", data.get("Runmode"), excel);
		browser= getBrowser(data.get("browser"));
		navigate(browser, Constants.URL);
		click("bmlBtn_CSS");
		click("openaccount_CSS");
		select(data.get("customer"),"cusname_CSS");
		select(data.get("currency"),"cur_CSS");
		click("process_XPATH");
	}

}
