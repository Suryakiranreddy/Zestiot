package enh.web.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_Hyd;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_Hyd;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })

public class ZestIOT_AV_2294__GMR_HYD_Identify_coverage_of_Flight_Sensor_and_Validate_timestamps_of_Departure_Aircrafts extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;

	@Test(testName = "AV_2294_Identify_coverage_of_Flight_Sensor_and_Validate_timestamps_of_Departure_Aircrafts", groups = {
			"Regression" }, description = "Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts for GMR-HYD")
	public void test()
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

			AV_2294_Scheduled_And_Sensor_ATD_Hyd.scheduled_And_Sensor_ATD_For_Hyderabad_Report(4,"prod");
			
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
				test();
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
