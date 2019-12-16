package enh.web.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.db.cases.Bsspl;
import enh.db.cases.SQL_Queries;
import enh.web.pages.DepartedFlights;
import enh.web.pages.HomePage;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;
import utilities.TestDataProvider;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_000_bsspl extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;

	@DataProvider
	public Object[][] testData() throws Exception {
		Object data[][] = TestDataProvider.excelReader(ConfigReader.getValue("ExcelPath"), "bsspl");
		return data;
	}

	@Test(testName = "ZestIOT_000_bsspl", groups = {
			"Regression" }, description = "ZestIOT_000_bsspl", dataProvider = "testData")
	public void test(String Mon_Flight_Num, String Tue_Flight_Num,String Wed_Flight_Num,String Thu_Flight_Num,String Fri_Flight_Num,String Sat_Flight_Num,String Sun_Flight_Num)
			throws Throwable {
		try {
			setTestCaseID(getClass().getSimpleName());
			// ======================BASIC SETTING FOR
			// TEST=====================================================
			if (retryingNumber == 1)
				initTest();
			// ================== END BASIC SETTING
			// ============================================================
			// .........Script Start...........................
			
			/*if(SQL_Queries.todayDayDateTime().contains("Tue")) {
				Bsspl.bsspl(Mon_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Wed")) {
				Bsspl.bsspl(Tue_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Thu")) {
				Bsspl.bsspl(Wed_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Fri")) {
				Bsspl.bsspl(Thu_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Sat")) {
				Bsspl.bsspl(Fri_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Sun")) {
				Bsspl.bsspl(Sat_Flight_Num);
			}else if(SQL_Queries.todayDayDateTime().contains("Mon")) {
				Bsspl.bsspl(Sun_Flight_Num);
			}
*/
			Bsspl.bsspl(Fri_Flight_Num);
			
			// .........Script Start...........................
		} catch (Exception e) {
			if (retryCount > 0) {
				String imagePath = takeScreenshot(getDriver(), getTestCaseID() + "_" + retryingNumber);

				logStepFail(stepInfo + " - " + KeywordUtil.lastAction);
				logStepError(e.getMessage());
				HtmlReportUtil.attachScreenshot(imagePath, false);

				GlobalUtil.getTestResult().setScreenshotref(imagePath);

				HtmlReportUtil.stepInfo("Trying to Rerun" + " " + getTestCaseID() + " for " + retryingNumber + " time");
				retryCount--;
				retryingNumber++;
				utilities.LogUtil.infoLog(getClass(), "****************Waiting for " + getIntValue("retryDelayTime")
						+ " Secs before retrying.***********");
				delay(getIntValue("retryDelayTime"));
				// Rerun same test
				test( Mon_Flight_Num, Tue_Flight_Num,Wed_Flight_Num, Thu_Flight_Num, Fri_Flight_Num, Sat_Flight_Num, Sun_Flight_Num);
			} else {
				String imagePath = takeScreenshot(getDriver(), getTestCaseID());
				logStepFail(stepInfo + " - " + KeywordUtil.lastAction);
				logStepError(e.getMessage());
				HtmlReportUtil.attachScreenshot(imagePath, false);
				GlobalUtil.getTestResult().setScreenshotref(imagePath);
				GlobalUtil.setTestException(e);
				throw e;
			}
		}
	}// End Test

}
