package enh.web.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.web.pages.Alerts;
import enh.web.pages.HomePage;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;
import utilities.TestDataProvider;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_006_Verify_FlightInfo_Alerts extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;

	@DataProvider
	public Object[][] testData() throws Exception {
		Object data[][] = TestDataProvider.excelReader(ConfigReader.getValue("ExcelPath"), "FlightInfo");

		// System.out.println(Repository.getProperty("SSTestDataFilePath"));
		return data;
	}

	@Test(testName = "ZestIOT_006_VerifyFlightInfoAlerts", groups = {
			"Regression" }, description = "verify flightinfo alrts", dataProvider = "testData")
	public void test(String str_flightInfo, String str_type, String str_subType)
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
			HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\"> Test Date: </b>" + ""+str_flightInfo +" "+str_type +" "+str_subType+"");
			HomePage.login(ConfigReader.getValue("loginUser"), ConfigReader.getValue("loginPassword"),
					ConfigReader.getValue("hotelCode"));
			Alerts.verifyFlightInfoAlerts();
			HomePage.logOut();
			
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
				test(str_flightInfo, str_type, str_subType);
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
