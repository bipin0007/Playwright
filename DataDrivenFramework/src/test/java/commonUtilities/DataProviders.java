package commonUtilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="bankManagerDP")
	//,parallel=true
	public static Object[][] getDataSuite1(Method m) {
		
		System.out.println(m.getName());

		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		String testcase= m.getName();
		return DataUtils.getData(testcase, excel);

	}

	
	@DataProvider(name="customerDP")
	public static Object[][] getDataSuite2(Method m) {
		
		System.out.println(m.getName());

		ExcelReader excel = new ExcelReader(Constants.SUITE2_XL_PATH);
		String testcase= m.getName();
		return DataUtils.getData(testcase, excel);

	}
	
//	@DataProvider
//	public Object[][] getData(){
//		String sheetName="addCustomer";
//		
//		//ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.xlsx");
//		
//		int rowNum = excel.getRowCount(sheetName);
//		int colNum = excel.getColumnCount(sheetName);
//		
//		Object[][] data = new Object[rowNum-1][colNum];
//		
//		for(int rows=2;rows<=rowNum;rows++) {
//			
//			
//			for(int cols=0;cols<colNum; cols++) {
//				
//				
//				data[rows -2][cols] = excel.getCellData(sheetName, cols, rows);
//			}
//		}
//		return data;
		
	//}
	
	


}
