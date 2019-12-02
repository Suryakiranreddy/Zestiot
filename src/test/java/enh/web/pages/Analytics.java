package enh.web.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class Analytics extends KeywordUtil{
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	static SoftAssert softAssertion= new SoftAssert();
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By AnalyticsButton = By.xpath("//a[contains(.,'Analytics')]");
	//private static By viewStatics = By.xpath("//button[text()= ' View Statistics '] ");
	
	/* Analytics Login credentials */
	private static By staticsUsername = By.xpath("//div[@id='formContent']//input[@id= 'login']");
	private static By staticsPassword = By.xpath("//input[@name= 'pwd']");
	private static By staticsLogin = By.xpath("//input[@type= 'submit']");
	private static By staticsLogout = By.xpath("//button[text()= 'Log Out']");
	
	/* Analytics Side navigation menu buttons */
	private static By MenuButton = By.xpath("//i[@class= 'fas fa-align-justify']");
	private static By OnTimePerformanceButton =By.xpath("//a/img[@id= 'otp']");
	private static By TurnaroundButton = By.xpath("//a/img[@id= 'tnard']");
	private static By BayUtilizationButton = By.xpath("//div[@class= 'sidenav ']/a[4]");
	private static By EquipmentUtilizationButton = By.xpath("//a/img[@id= 'equiputil']");
	private static By BeltUtilizationButton = By.xpath("//div[@class= 'sidenav ']/a[6]");
	private static By SettingsButton = By.xpath("//div[@class= 'sidenav ']/a[7]");
	
	/* Analytics OTP page cards */
	private static By OTPForAll = By.xpath("//div[@id= 'Percentage_OTP_all_flights_flag']");
	private static By OTPForMetroAndNonmetro = By.xpath("//div[@id= 'Percentage_OTP_metro_nonmetro_flights_flag']");
	private static By OTPForNarrowbody = By.xpath("//div[@id= 'Percentage_OTP_all_narrow_body_flights_flag']");
	private static By OTPForBombardier = By.xpath("//div[@id= 'Percentage_OTP_all_bombardier_flights_flag']");
	
	/* Analytics Turnaround page cards */
	private static By AverageTurnaroundTime = By.xpath("//div[@id= 'Avg_Turnaround_Time']");
	private static By PercentageActivities = By.xpath("//div[@id= 'Turnaround_Duration_Narrow']");
	private static By Top3Bottom3Activities = By.xpath("//div[@id= 'Turnaround_Duration_Bombardier']");
	private static By FasterTurnaround = By.xpath("//div[@id= 'PercentFlights_Faster_Than_Expected']");
	
	/* Analytics Equipment Utilization page cards */
	private static By AverageFlightsServedByEquipment = By.xpath("//div[@id= 'Average_number_of_flights_served_by_all_equipments']");
	private static By AverageDistanceTravelled = By.xpath("//div[@id= 'Total_distance_travelled']");
	private static By AverageIgnitionTime = By.xpath("//div[@id= 'Average_ignition_on_time']");
	private static By NumberOfNoncompliantEquipment = By.xpath("//div[@id= 'Number_of_non_compliant_equipments']");
		
	static String mainWindow;	
	
	/**********************************************************************************************************
	 * Method Name: LoginToAnalyticsPage
	 * Description :To login to Analytics page with valid credentials
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         29/Oct/2019                   Padmaja			                            AD/-/Oct-Nov/2019                                     
	 * @throws InterruptedException 
	 ***********************************************************************************************************/
	// ######################### Analytics Login Verification Started ###############################
	
	public static void LoginToAnalyticsPage() throws InterruptedException {

		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">################# ZestIOT Analytics Login Page Verification Started #################</b>");
			mainWindow =  getDriver().getWindowHandle();
			hoverElement(menu);
			delay(1000);
			click(AnalyticsButton);
			//click(viewStatics);
		 	Set<String> allWindows = getDriver().getWindowHandles();
			for (String window:allWindows){
		        String title = getDriver().switchTo().window(window).getTitle();
		    	if (title.contains("analytics")){
		    		String url = getDriver().getCurrentUrl();
		    	    System.out.println(url);
		    	}
		    }
		    inputText(staticsUsername, "nilesh@zestiot.io");
		    HtmlReportUtil.stepInfo("<b style=\"color:green;\">'" + staticsUsername + "'</b> Username entered for Analytics screen");
		    
		    inputText(staticsPassword, "zestiot123");
		    HtmlReportUtil.stepInfo("<b style=\"color:green;\">'" + "*********" + "'</b> Password entered for Analytics screen");
		    click(staticsLogin);
		    HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========Analytics page login successfull=============</b>");
		HtmlReportUtil.stepInfo(
				"<b style=\"color:green;\">################# ZestIOT Analytics Login Page Verification Ended #################</b>");
	// ######################### Analytics Login Verification Ended ###############################
	}		
	/**********************************************************************************************************
	 * Method Name: VerifyingAnalyticsPage
	 * Description : To verify tabs in Analytics page
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         30/Oct/2019                   Padmaja			                            AD/-/Oct-Nov/2019                                     
	 * @throws InterruptedException 
	 ***********************************************************************************************************/
	// ######################### Analytics Page Verification Started ###############################
	
	// ######################### On Time Performance Page Verification Started #####################
		public static void AnalyticsPage() throws InterruptedException {

			HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">################# ZestIOT Analytics Page Verification Started #################</b>");
			HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== On Time Performance Page Veriifcation Started ======</b>");
			delay(5000);
			js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
			delay(5000);
			click(MenuButton);
			delay(2000);
			click(OnTimePerformanceButton);
			click(OTPForAll);
			List<WebElement> elm = getDriver().findElements(By.xpath("//*[@id='otp_airlines_agg']/div[1]/canvas"));
			String expectedValue= "zr_0";
    		String actualValue = elm.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue.equals(actualValue))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** OTP for all airlines- Chart is Displayed ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** OTP for all airlines- Chart NOT Displayed ********</b>");
    		}	
    		delay(3000);
    		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");   
    		delay(3000);
    		List<WebElement> elm2 = getDriver().findElements(By.xpath("//*[@id='otphrly']/div[1]/canvas"));
			String expectedValue2= "zr_0";
    		String actualValue2 = elm2.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue2.equals(actualValue2))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** OTP on hourly basis- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** OTP on hourly basis- Chart NOT Displayed ********</b>");
    		}
    		delay(3000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		delay(3000);
			click(OTPForMetroAndNonmetro);
			delay(3000);
			List<WebElement> elm3 = getDriver().findElements(By.xpath("//div[@id= 'metro_nonmetro_aggAirlines']/div[1]/canvas"));
			String expectedValue3= "zr_0";
    		String actualValue3 = elm3.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue3.equals(actualValue3))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** OTP for metro & non-metro- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** OTP for metro & non-metro- Chart NOT Displayed ********</b>");
    		}
			
			delay(3000);
			js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
			delay(3000);
			click(OTPForNarrowbody);
			delay(3000);
			List<WebElement> elm4 = getDriver().findElements(By.xpath("//div[@id= 'otp_narrow_aggAirlines']/div[1]/canvas"));
			String expectedValue4= "zr_0";
    		String actualValue4 = elm4.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue4.equals(actualValue4))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** OTP all narrow body airlines- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** OTP all narrow body airlines- Chart NOT Displayed ********</b>");
    		}
			
			delay(3000);
			js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
			delay(3000);			
			click(OTPForBombardier);
			delay(3000);
			List<WebElement> elm5 = getDriver().findElements(By.xpath("//div[@id= 'otp_bombardier_aggAirlines']/div[1]/canvas"));
			String expectedValue5= "zr_0";
    		String actualValue5 = elm5.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue5.equals(actualValue5))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** OTP for each individual airline bombardier body type- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** OTP for each individual airline bombardier body type- Chart NOT Displayed ********</b>");
    		}
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		delay(3000);
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== On Time Performance Page Veriifcation Started ======</b>");
			
			// ######################### On Time Performance Page Verification Ended #####################
			
			// ######################### Turnaround Page Verification Started #####################
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Turnaround Page Veriifcation Started ======</b>");
    		delay(2000);
    		click(MenuButton);
    		click(TurnaroundButton);
    		//delay(20000);			// Page loading is slow because of bulk data
    		click(AverageTurnaroundTime);
    		delay(2000);
    		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    		delay(3000);
			List<WebElement> elm6 = getDriver().findElements(By.xpath("//div[@id='Turnaround_Airport']/div[1]/canvas"));
			String expectedValue6= "zr_0";
    		String actualValue6 = elm6.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue6.equals(actualValue6))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Turnaround Duration- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Turnaround Duration- Chart NOT Displayed ********</b>");
    		}
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)"); 	
    		delay(3000);
    		click(PercentageActivities);
    		delay(2000);
    		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    		delay(2000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		delay(2000);
    		click(Top3Bottom3Activities);
    		delay(2000);
    		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    		delay(2000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		delay(2000);
    		click(FasterTurnaround);
    		delay(2000);
    		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    		delay(2000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		delay(3000);
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Turnaround Page Veriifcation Ended ======</b>");
    		// ######################### Turnaround Page Verification Ended #####################
    		
    		// ######################### Bay Utilization Page Verification Started #####################
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Bay Utilization Page Veriifcation Started ======</b>");
    		click(MenuButton);
    		click(BayUtilizationButton);
    		delay(5000);
    		js.executeScript("window.scrollBy(0,500)");
    		List<WebElement> elm10 = getDriver().findElements(By.xpath("//div[@id='bay_util_first_chart']/div[1]/canvas"));
			String expectedValue10= "zr_0";
    		String actualValue10 = elm10.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue10.equals(actualValue10))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Actual Bay Utilization- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Actual Bay Utilization- Chart NOT Displayed ********</b>");
    		}
    		delay(2000);
    		js.executeScript("window.scrollBy(0,500)");
    		delay(2000);
    		List<WebElement> elm11 = getDriver().findElements(By.xpath("//div[@id='bay_util_scheduled_chart']/div[1]/canvas"));
			String expectedValue11= "zr_0";
    		String actualValue11 = elm11.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue11.equals(actualValue11))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Scheduled bay Utilization- Chart is Displayed ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Scheduled Bay Utilization- Chart NOT Displayed ********</b>");
    		}
    		delay(2000);
    		js.executeScript("window.scrollBy(0,500)");
    		delay(2000);
    		List<WebElement> elm12 = getDriver().findElements(By.xpath("//div[@id='bay_util_overall']/div[1]/canvas"));
			String expectedValue12= "zr_0";
    		String actualValue12 = elm12.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue12.equals(actualValue12))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Overall bay Utilization- Chart is Displayed ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Overall bay Utilization- Chart NOT Displayed ********</b>");
    		}
    		delay(2000);
    		js.executeScript("window.scrollBy(0,500)");
    		delay(2000);
    		List<WebElement> elm13 = getDriver().findElements(By.xpath("//div[@id='taxi_in_out']/div[1]/canvas"));
			String expectedValue13= "zr_0";
    		String actualValue13 = elm13.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue13.equals(actualValue13))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Taxi In & Taxi Out- Chart is Displayed ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Taxi In & Taxi Out- Chart NOT Displayed ********</b>");
    		}
    		delay(2000);
    		js.executeScript("window.scrollBy(0,500)");
    		delay(2000);
    		List<WebElement> elm14 = getDriver().findElements(By.xpath("//div[@id='distribution_taxi_in_out']/div[1]/canvas"));
			String expectedValue14= "zr_0";
    		String actualValue14 = elm14.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue14.equals(actualValue14))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Taxi In & Taxi Out Distribution- Chart is Displayed ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Taxi In & Taxi Out Distribution- Chart NOT Displayed ********</b>");
    		}
    		delay(2000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		delay(2000);		
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Bay Utilization Page Veriifcation Ended ======</b>");
    		// ######################### Bay Utilization Page Verification Ended #####################
    		
    		// ######################### Equipment Utilization Page Verification Started #####################
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Equipment Utilization Page Veriifcation Started ======</b>");
    		click(MenuButton);
    		delay(2000);
    		click(EquipmentUtilizationButton);
    		delay(3000);		// Page taking more time to load
    		click(AverageFlightsServedByEquipment);
    		delay(1000);
    		js.executeScript("window.scrollBy(0,500)");
    		List<WebElement> elm15 = getDriver().findElements(By.xpath("//div[@id='Total_flights_served_by_each_equipment']/div[1]/canvas"));
			String expectedValue15= "zr_0";
    		String actualValue15 = elm15.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue15.equals(actualValue15))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Total flights served by each equipment- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Total flights served by each equipment- Chart NOT Displayed ********</b>");
    		}
    		
    		delay(1000);
    		js.executeScript("window.scrollBy(0,500)");
    		List<WebElement> elm16 = getDriver().findElements(By.xpath("//div[@id='Total_flights_served_by_each_type_of_equipment']/div[1]/canvas"));
			String expectedValue16= "zr_0";
    		String actualValue16 = elm16.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue16.equals(actualValue16))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Total flights served by each type of equipment- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Total flights served by each type of equipment- Chart NOT Displayed ********</b>");
    		}
    		delay(1000);
    		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    		List<WebElement> elm17 = getDriver().findElements(By.xpath("//div[@id='Average_Equipment_usage']/div[1]/canvas"));
			String expectedValue17= "zr_0";
    		String actualValue17 = elm17.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue17.equals(actualValue17))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Total flights served by each type of equipment- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Total flights served by each type of equipment- Chart NOT Displayed ********</b>");
    		}
    		
    		delay(2000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		click(AverageDistanceTravelled);
    		delay(2000);
    		js.executeScript("window.scrollBy(0,500)");
    		List<WebElement> elm18 = getDriver().findElements(By.xpath("//div[@id= 'Total_distance_travelled_chart']/div[1]/canvas"));
			String expectedValue18= "zr_0";
    		String actualValue18 = elm18.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue18.equals(actualValue18))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Total Distance Travelled- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Total Distance Travelled- Chart NOT Displayed ********</b>");
    		}
    		delay(2000);
    		js.executeScript("window.scrollBy(0,500)");
    		List<WebElement> elm19 = getDriver().findElements(By.xpath("//div[@id= 'equiputil_heatmap']/div[1]/canvas"));
			String expectedValue19= "zr_0";
    		String actualValue19 = elm19.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue19.equals(actualValue19))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Equipment heatmap- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Equipment heatmap- Chart NOT Displayed ********</b>");
    		}
    		delay(2000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		click(AverageIgnitionTime);
    		delay(1000);
    		js.executeScript("window.scrollBy(0,500)");
    		List<WebElement> elm20 = getDriver().findElements(By.xpath("//div[@id= 'avg_ingnition_time']/div[1]/canvas"));
			String expectedValue20= "zr_0";
    		String actualValue20 = elm20.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue20.equals(actualValue20))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Ignition on time- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Ignition on time- Chart NOT Displayed ********</b>");
    		}
    		delay(1000);
    		js.executeScript("window.scrollBy(0,500)");
    		List<WebElement> elm21 = getDriver().findElements(By.xpath("//div[@id= 'avg_ingnition_by_device']/div[1]/canvas"));
			String expectedValue21= "zr_0";
    		String actualValue21 = elm21.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue21.equals(actualValue21))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Ignition on time by device- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Ignition on time by device- Chart NOT Displayed ********</b>");
    		}
    		delay(1000);
    		js.executeScript("window.scrollBy(0,500)");
    		List<WebElement> elm22 = getDriver().findElements(By.xpath("//div[@id= 'equipment_moved']/div[1]/canvas"));
			String expectedValue22= "zr_0";
    		String actualValue22 = elm22.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue22.equals(actualValue22))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Equipment moved when its ON- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Equipment moved when its ON- Chart NOT Displayed ********</b>");
    		}
    		
    		delay(1000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		click(NumberOfNoncompliantEquipment);
    		delay(3000);
    		js.executeScript("window.scrollBy(0,700)");
    		List<WebElement> elm23 = getDriver().findElements(By.xpath("//div[@id= 'non_compliance_equip']/div[1]/canvas"));
			String expectedValue23= "zr_0";
    		String actualValue23 = elm23.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue23.equals(actualValue23))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Number of equipment that were non compliant for each type of equipment- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Number of equipment that were non compliant for each type of equipment- Chart NOT Displayed ********</b>");
    		}
    		
    		js.executeScript("window.scrollBy(0,500)");
    		delay(4000);
    		List<WebElement> elm24 = getDriver().findElements(By.xpath("//div[@id= 'non_compliance_hourly']/div[1]/canvas"));
			String expectedValue24= "zr_0";
    		String actualValue24 = elm24.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue24.equals(actualValue24))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Non compliance on hourly basis- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Non compliance on hourly basis- Chart NOT Displayed ********</b>");
    		}
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Equipment Utilization Page Veriifcation Ended ======</b>");    		
    		// ######################### Equipment Utilization Page Verification Ended #####################
    		
    		// ######################### Belt Utilization Page Verification Started #####################
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Belt Utilization Page Veriifcation Started ======</b>");
    		delay(3000);
    		click(MenuButton);
    		delay(2000);
    		click(BeltUtilizationButton);
    		delay(6000);
    		List<WebElement> elm25 = getDriver().findElements(By.xpath("//div[@id= 'beltutil_heatmap']/div[1]/canvas"));
			String expectedValue25= "zr_0";
    		String actualValue25 = elm25.get(0).getAttribute("data-zr-dom-id");
    		if(expectedValue25.equals(actualValue25))
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Utilization heatmap- Chart is Displayed  ********</b>");
    		}
    		else
    		{
    			HtmlReportUtil.stepInfo(
    					"<b style=\"color:red;\">******** Utilization heatmap- Chart NOT Displayed ********</b>");
    		}
    		delay(3000);
    		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Belt Utilization Page Veriifcation Ended ======</b>");
    		// ######################### Belt Utilization Page Verification Ended #####################
    		
    		// ######################### Settings Page Verification started #####################
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Settings Page Veriifcation Started ======</b>");
    		delay(2000);
    		click(MenuButton);
    		click(SettingsButton);
    		delay(2000);
    		WebElement element = getDriver().findElement(By.xpath("//button[@class='btn btn-light btn-sm dropdown-toggle btn-block']"));
    		if(element.isDisplayed())
    		{
    		HtmlReportUtil.stepInfo(
    					"<b style=\"color:green;\">******** Change Theme- Dropdown is Present  ********</b>");
    		}
    		else
    		{
        		HtmlReportUtil.stepInfo(
        					"<b style=\"color:red;\">******** Change Theme- Dropdown is NOT Present  ********</b>");
        	}	
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">====== Settings Page Veriifcation Ended ======</b>");   		
    		
    		// ######################### Settings Page Verification Ended #####################
    		HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">############## ZestIOT Analytics Page Verification Ended #############</b>");
			// ######################### Analytics Page Verification Ended ###############################
		
			HtmlReportUtil.stepInfo("<b style=\"color:green;\">######## Analytics page logout initiated #######</b>");
			delay(3000);
			click(staticsLogout);
			getDriver().switchTo().window(mainWindow);
			
			HtmlReportUtil.stepInfo("<b style=\"color:green;\">######## Analytics page logout successfull ########</b>");
			softAssertion.assertAll();
		}
	}
	
	
