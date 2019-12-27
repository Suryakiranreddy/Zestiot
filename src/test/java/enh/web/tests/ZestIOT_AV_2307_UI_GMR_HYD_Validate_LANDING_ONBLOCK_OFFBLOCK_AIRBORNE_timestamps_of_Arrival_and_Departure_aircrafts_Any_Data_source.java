package enh.web.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.web.pages.AV_2307_UI_CELEBI_SensorATA_OnBlock_OffBlock_SensorATD_Validation;
import enh.web.pages.HomePage;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_AV_2307_UI_GMR_HYD_Validate_LANDING_ONBLOCK_OFFBLOCK_AIRBORNE_timestamps_of_Arrival_and_Departure_aircrafts_Any_Data_source extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;

	@Test(testName = "ZestIOT_AV_2307_UI_GMR_HYD_Validate_LANDING_ONBLOCK_OFFBLOCK_AIRBORNE_timestamps_of_Arrival_and_Departure_aircrafts_Any_Data_source", groups = {
			"Regression" }, description = "Validate LANDING, ONBLOCK, OFFBLOCK and AIRBORNE timestamps of Arrival and Departure aircrafts from Any Data source for GMR-HYD in UI")
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

			HomePage.prodlogin("anantgmr@zestiot.io","zestiot123",
					ConfigReader.getValue("hotelCode"));
			AV_2307_UI_CELEBI_SensorATA_OnBlock_OffBlock_SensorATD_Validation.verifySensorATA_OnBlock_OffBlock_SensorATD_timestamps("HYD", "GMR");
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
