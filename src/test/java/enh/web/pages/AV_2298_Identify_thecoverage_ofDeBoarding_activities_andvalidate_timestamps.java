package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import enh.db.cases.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class AV_2298_Identify_thecoverage_ofDeBoarding_activities_andvalidate_timestamps extends KeywordUtil {
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	private static By PCDeplane = By.xpath("//div[@title = 'Bay Departure - Drop arrival']/following-sibling::div[1]/div[2]/descendant::p");
	
		
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	public static int DepatedFlightsCount=0;
	public static int PCDAndPCDABothFormed =0;
	public static int PCDAndPCDABothNotFormed =0;
	public static int PCDeplaneNotFormed=0;
	public static int PCDFormedPCDA_NotFormed=0;
	
	public static int missingFlight=0;
	public static int j=1;
		
	/**********************************************************************************************************
	 * Method Name: Verify PCD and PCDA Deboarding activities
	 * Description : To verify that PCD and PCDA activities are displayed on UI
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         12/Dec/2019                   Padmaja	                                   AD/-/Dec/2019                                      
	 ***********************************************************************************************************/
	public static void verifyDeboardingActivities() throws Exception
	{
		String userName=getElementText(By.xpath("html/body/app-root/div/app-topnav/mat-toolbar/div[4]/div[2]"));
		System.out.println(userName);
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\"> Reports for "+userName+" </b>");
		delay(10000);
		hoverElement(menu);
		delay(2000);
		hoverElement(flightInfo);
		delay(2000);
		click(turnaroundList);
		delay(2000);
		click(departed);
		delay(2000);
		
		//calenderInput("13/NOV/2019");
		clearInput(By.xpath("//input[@name='date']"));
		delay(2000);
		inputText(By.xpath("//input[@name='date']"), SQL_Queries.yesterDate());
		delay(2000);
		System.out.println("Final");
		
		click(By.xpath("//mat-icon[contains(.,'search')]"));
		delay(10000);
		System.out.println("done");
		String str_DepatedFlights = getElementText(departed);
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		 DepatedFlightsCount=Integer.parseInt(str_DepatedFlightsCount);
		System.out.println(str_DepatedFlightsCount);
		WebDriverWait w = new WebDriverWait(getDriver(), 20);
		for(int i=1;i<=DepatedFlightsCount;i++)
		{
			try
			{
				delay(3000);
				js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+i+"]")));
							
				delay(3000);
				click(By.xpath("//div[@id='selectedCard']["+i+"]"));
				delay(3000);
				
				String flightActivityList=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
				
						if(flightActivityList.contains("PC Deplane") && (flightActivityList.contains("PCD Arrival")))
						{
							PCDAndPCDABothFormed= PCDAndPCDABothFormed+1;
						}
						if(!flightActivityList.contains("PC Deplane") && (!flightActivityList.contains("PCD Arrival")))
						{
							PCDAndPCDABothNotFormed = PCDAndPCDABothNotFormed+1;
							String flightNumber=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
						    String flightNum[] =flightNumber.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[3]/div")).getAttribute("title");
							String arrivalDateAndTime = getElementText(By.xpath("//div[@id='selectedCard'][1]//div[@class= 'arrival-time-col']/descendant::div[3]/p[5]"));
							//HtmlReportUtil.stepInfo("<b style=\"color:red;\">"+i+" Flight: "+flightNumber+" PCD Arrival and PC Deplane both are not formed </b>");
						}
						if(!flightActivityList.contains("PC Deplane"))
						{
							PCDeplaneNotFormed = PCDeplaneNotFormed+1;
							String flightNumber=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNum[] =flightNumber.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[3]/div")).getAttribute("title");
							String arrivalDateAndTime = getElementText(By.xpath("//div[@id='selectedCard'][1]//div[@class= 'arrival-time-col']/descendant::div[3]/p[5]"));
							//HtmlReportUtil.stepInfo("<b style=\"color:red;\">"+i+" Flight: "+flightNumber+" PC Depalne is not formed </b>");
						}
						if(flightActivityList.contains("PC Deplane") && (!flightActivityList.contains("PCD Arrival")))
						{
							PCDFormedPCDA_NotFormed= PCDFormedPCDA_NotFormed+1;
							String flightNumber=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNum[] =flightNumber.split("-");
							String logID = getWebElement(By.xpath("//div[@id='selectedCard'][1]/descendant::div[3]/div")).getAttribute("title");
							String arrivalDateAndTime = getElementText(By.xpath("//div[@id='selectedCard'][1]//div[@class= 'arrival-time-col']/descendant::div[3]/p[5]"));
							//HtmlReportUtil.stepInfo("<b style=\"color:black;\">"+i+" Flight: "+flightNumber+" only Pushback is formed </b>");
						}
						click(By.xpath("//div[@id='selectedCard']["+i+"]"));
						delay(2000);
						try{
							j=j+1;
							js.executeScript("arguments[0].scrollIntoView();",getWebElement(By.xpath("//div[@id='selectedCard']["+j+"]")));	
							delay(3000);
						}catch(Exception e)
						{
							//e.printStackTrace();
						} 					
			}
			catch(Exception e)
			{
				missingFlight=missingFlight+1;
				//e.printStackTrace();
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> "+i+" - "+getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"))+"</b>");
			} 

		}
		HtmlReportUtil.stepInfo("<b style=\"color:blue;\">Total number of Flights for Celebi user : "+DepatedFlightsCount+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which both PC Deplane and PCD Arrival both are formed : "+PCDAndPCDABothFormed+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which both PC Deplane and PCD Arrival both are NOT formed : "+PCDAndPCDABothNotFormed+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which PC Deplane is NOT formed : "+PCDeplaneNotFormed+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:black;\">No. of flights which PC Deplane is formed but PCD Arrival is NOT formed : "+PCDFormedPCDA_NotFormed+"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:brown;\">No. of missing flights in automation execution : "+missingFlight+"</b>");
		}	
		}
	


