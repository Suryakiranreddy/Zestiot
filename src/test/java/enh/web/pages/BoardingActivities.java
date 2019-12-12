package enh.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import enh.db.cases.SQL_Queries;
import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class BoardingActivities extends KeywordUtil {
	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By flightInfo = By.xpath("//a[contains(.,'Flight Info')]");
	private static By turnaroundList = By.xpath("//a[contains(.,'Turnaround List')]");
	private static By departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
	static JavascriptExecutor js = (JavascriptExecutor) getDriver();
	public static int DepatedFlightsCount=0;
	public static int pushbackAndArrival=0;
	public static int pushback=0;
	public static int pushback_NoPBT = 0;
	public static int PBT_NoPushback=0;
	public static int pbArrival =0;
	public static int noPBTnoPushback =0;
	public static int missingFlight=0;
	public static int j=1;

	public static void boardingActivities() throws Exception
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
		//departed = By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]");
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
				String flightInfoText=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"));
				
						if(flightInfoText.contains("PC Boarding") && (flightInfoText.contains("PC Gate Arrival"))&& (flightInfoText.contains("PCB Arrival ")))
						{
							pushbackAndArrival= pushbackAndArrival+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							
						}
						if(!flightInfoText.contains("PC Boarding") && (!flightInfoText.contains("PC Gate Arrival"))&& (!flightInfoText.contains("PCB Arrival ")))
						{
							pushback_NoPBT = pushback_NoPBT+1;
							String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
						}
						if((!flightInfoText.contains("PC Boarding")))
						{
							PBT_NoPushback = PBT_NoPushback+1;
							String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
						}
						if(flightInfoText.contains("PC Boarding") && (!flightInfoText.contains("PC Gate Arrival")))
						{
								pushback= pushback+1;
								String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
						}
						if(flightInfoText.contains("PC Boarding")&& (!flightInfoText.contains("PCB Arrival ")))
							{
								pbArrival= pbArrival+1;
								String x1=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
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
		
		}	
		}
