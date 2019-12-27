package enh.web.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import enh.web.pages.AV_2390_UI_CELEBI_Detect_totalNo_offlights_forwhichfinal_COBT_has_highORless_difference_compare_toActual_Offblock;
import enh.web.pages.HomePage;
import listeners.CustomListeners;
import listeners.ExecutionStartEndListner;
import utilities.ConfigReader;
import utilities.GlobalUtil;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

@Listeners({ CustomListeners.class, ExecutionStartEndListner.class })
public class ZestIOT_AV_2390_UI_SG_HYD_Detect_totalNo_offlights_forwhich_finalCOBT_hashighOrless_difference_compareto_Actual_Offblock extends KeywordUtil {
	String stepInfo = "";
	int retryCount = getIntValue("retryCount");
	static int retryingNumber = 1;

	@Test(testName = "ZestIOT_AV_2390_UI_SG_HYD_Detect_totalNo_offlights_forwhich_finalCOBT_hashighOrless_difference_compareto_Actual_Offblock", groups = {
			"Regression" }, description = "Validate COBT and OffBlock time difference is more or less than 5min for SG-HYD in UI")
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

			HomePage.prodlogin("anantspice@zestiot.io","zestiot123",
					ConfigReader.getValue("hotelCode"));
			AV_2390_UI_CELEBI_Detect_totalNo_offlights_forwhichfinal_COBT_has_highORless_difference_compare_toActual_Offblock.verifyCOBTAndOffBlockTimeDifferenceForFlights("HYD", "SG");
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
