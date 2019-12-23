package enh.web.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import enh.db.cases.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class AV_2307_UI_CELEBI_SensorATA_OnBlock_OffBlock_SensorATD_Validation extends KeywordUtil{
	
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	
	public static int DepatedFlightsCount=0;
	public static int missingFlight =0;
	public static int landingTimeIsNotNull =0;
	public static int onBlockTimeIsNotNull =0;
	public static int offBlockTimeIsNotNull =0;
	public static int airborneTimeIsNotNull =0;
	
	public static ArrayList list_landingTimeIsNull = new ArrayList();

	/**********************************************************************************************************
	 * Method Name: Verify SensorATA < OnBlock < OffBlock < SensorATD timestamps
	 * Description : To verify SensorATA, OnBlock, OffBlock and SensorATD timestamps displayed on UI
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         20/Dec/2019                   Padmaja	                                   AD/-/Dec/2019                                      
	 * @throws Exception 
	 ***********************************************************************************************************/
	public static void verifySensorATA_OnBlock_OffBlock_SensorATD_timestamps() throws Exception
	{
		String userName=getElementText(By.xpath("html/body/app-root/div/app-topnav/mat-toolbar/div[4]/div[2]"));
		System.out.println(userName);
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\"> Reports for "+userName+" </b>");
		delay(10000);
		click(menu);
		delay(2000);
		click(flightInfo);
		delay(2000);
		click(turnaroundList);
		delay(2000);
		click(departed);
		delay(2000);
		clearInput(By.xpath("//input[@name='date']"));
		delay(2000);
		inputText(By.xpath("//input[@name='date']"), SQL_Queries.yesterDate());
		delay(2000);
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(10000);
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		DepatedFlightsCount=Integer.parseInt(str_DepatedFlightsCount);
		System.out.println(str_DepatedFlightsCount);
		for(int i=1;i<=3;i++)
		{
			try {
				delay(3000);
				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
				delay(3000);
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(3000);
				String flightInfoText=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
				hoverElement(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[2]"));
								
				String landingDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[3]/p[5]"));
				System.out.println(landingDateAndTime);
				String landingTime= landingDateAndTime.replace("Landed: ", "");
				System.out.println("landing time:" +landingTime);
				
				String onBlockDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'arrival-time-col']/descendant::div[3]/p[6]/span"));
				System.out.println(onBlockDateAndTime); 
				
				hoverElement(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']"));
				String offBlockDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div[3]/p[3]/span"));
				
				String airborneDateAndTime = getElementText(By.xpath("//div[@id='selectedCard']["+i+"]//div[@class= 'departure-time-col']/descendant::div[3]/p[4]"));
				String airborneTime= airborneDateAndTime.replace("Airborne: ", "");
				System.out.println("Airborne time:" +airborneTime);
				
				if(!landingTime.equals("-")) {
					landingTimeIsNotNull = landingTimeIsNotNull + 1;
				}
				else {
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					System.out.println("Landing not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);
				}
				
				if(!onBlockDateAndTime.equals("-")) {
					onBlockTimeIsNotNull = onBlockTimeIsNotNull + 1;
				}
				else {
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					System.out.println("onblock not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);
				}
				
				
				if(!offBlockDateAndTime.equals("-")) {
					offBlockTimeIsNotNull = offBlockTimeIsNotNull + 1;
				}
				else {
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					System.out.println("offblock not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);
				}
				
				
				if(!airborneTime.equals("-")) {
					airborneTimeIsNotNull = airborneTimeIsNotNull + 1;
				}
				else {
					String flightNumber =getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
					System.out.println("airborne not available Flight Info:" +flightNumber+ " "+landingTime+ " " +onBlockDateAndTime+ " " +offBlockDateAndTime+ " " +airborneTime);
				}				
				
				if(!landingTime.equals("-") && (!onBlockDateAndTime.equals("-")) && (!offBlockDateAndTime.equals("-")) && (!airborneTime.equals("-")))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date landingCaptured = sdf.parse(landingTime);
					Date onBlockCaptured = sdf.parse(onBlockDateAndTime);
					Date offBlockCaptured = sdf.parse(offBlockDateAndTime);
					Date airborneCaptured = sdf.parse(airborneTime);
				if(landingCaptured.before(onBlockCaptured) && onBlockCaptured.before(offBlockCaptured) && offBlockCaptured.before(airborneCaptured)) {
					System.out.println("correct sequence");
				}
				else {
					System.out.println("not in sequence");
				}
				}
			}
			catch(Exception e)
			{
				missingFlight=missingFlight+1;
				//e.printStackTrace();
				//HtmlReportUtil.stepInfo("<b style=\"color:red;\"> "+i+" - "+getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"))+"</b>");
			} 
		}
		
}
}
