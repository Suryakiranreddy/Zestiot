package enh.web.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.KeywordUtil;
import utilities.TestDataProvider;


public class ZestIOT_TestCasesMailReport extends KeywordUtil{
	
	@DataProvider
	public Object[][] testData() throws Exception {
		Object data[][] = TestDataProvider.excelReader("./src/test/resources/ExcelFiles/AutomationControlSheet.xlsx", "smoke");

		// System.out.println(Repository.getProperty("SSTestDataFilePath"));
		return data;
	}

	@Test(dataProvider = "testData")
	public void test(String Script_Reference, String Flag, String Desc )
			throws Throwable {
		try {

System.out.println(Script_Reference+"-"+Flag+"-"+Desc);
		
			
			// .........Script Start...........................
		} catch (Exception e) {
			
		}
	}// End Test

}
