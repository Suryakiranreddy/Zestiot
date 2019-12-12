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
	public static int NumberOfFlightsWithAllTheBoardingActivities=0;
	public static int NumberOfFlightsWithoutAllTheBoardingActivities=0;
	public static int NoPCBactivities = 0;
	public static int withPCBactivitiesButNoPCGactivities=0;
	public static int withPCBactivitiesButNoPCBAactivities =0;
	public static int noPBTnoPushback =0;
	public static int missingFlight=0;
	public static int j=1;
	
	 public static StringBuilder email_BoardingActivities1= new StringBuilder();
	 public static StringBuilder email_BoardingActivities2= new StringBuilder();	 
	 public static StringBuilder email_BoardingActivities3= new StringBuilder();
	 public static StringBuilder email_BoardingActivities4= new StringBuilder();
	 public static StringBuilder email_BoardingActivities5= new StringBuilder();

	public static void boardingActivities() throws Exception
	{
		email_BoardingActivities1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities1.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> Total flights without the Boarding activities (PCG, PCB, and PCBA): </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Departure Number</th>"
			 		+ "<th style=\"width:25%\">Departure Date </th></tr>");
		
		email_BoardingActivities2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities2.append("<table style=\"width:100%\" id=\"t01\"><h4><caption> Total flights with No PCB activities:  </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Departure Number</th>"
			 		+ "<th style=\"width:25%\">Departure Date </th></tr>");
	
		email_BoardingActivities3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities3.append("<table style=\"width:100%\" id=\"t01\"><h4><caption>	Total flights with PCB activities but no PCG activities  </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Departure Number</th>"
			 		+ "<th style=\"width:25%\">Departure Date </th><th style=\"width:25%\">Operation PCB timestamp: Start & End </th></tr>");
	
		email_BoardingActivities4.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
		email_BoardingActivities4.append("<table style=\"width:100%\" id=\"t01\"><h4><caption>	Total Number of flights with PCB activities but no PCBA activities:   </caption></h4>"
		 		+ "<tr><th style=\"width:25%\">Flight Unique ID</th> <th style=\"width:25%\">Flight Number - Departure Number</th>"
			 		+ "<th style=\"width:25%\">Departure Date </th><th style=\"width:25%\">Operation PCB timestamp: Start & End </th></tr>");
	
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
							NumberOfFlightsWithAllTheBoardingActivities= NumberOfFlightsWithAllTheBoardingActivities+1;							
							
						}
						if(!flightInfoText.contains("PC Boarding") && (!flightInfoText.contains("PC Gate Arrival"))&& (!flightInfoText.contains("PCB Arrival ")))
						{
							NumberOfFlightsWithoutAllTheBoardingActivities = NumberOfFlightsWithoutAllTheBoardingActivities+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNumm[] =flightNum.split("-");
							String flightId=getDriver().findElement(By.xpath("(//div[@id='selectedCard']["+i+"]/div/div/div/div)[1]")).getAttribute("title");
							String deparDate=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/div/div/div[8]/div/div[2]/p[4]"));
							email_BoardingActivities1.append("<tr><td><b style=\"color:red;\">"+flightNumm[0]+"</b></td><td><b style=\"color:red;\">"+flightId+"</b></td>"
									+ "<td><b style=\"color:red;\">"+deparDate+"</b></td></tr>");

						}
						if((!flightInfoText.contains("PC Boarding")))
						{
							NoPCBactivities = NoPCBactivities+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNumm[] =flightNum.split("-");
							String flightId=getDriver().findElement(By.xpath("(//div[@id='selectedCard']["+i+"]/div/div/div/div)[1]")).getAttribute("title");
							String deparDate=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/div/div/div[8]/div/div[2]/p[4]"));
							email_BoardingActivities2.append("<tr><td><b style=\"color:red;\">"+flightNumm[0]+"</b></td><td><b style=\"color:red;\">"+flightId+"</b></td>"
									+ "<td><b style=\"color:red;\">"+deparDate+"</b></td></tr>");

						}
						if(flightInfoText.contains("PC Boarding") && (!flightInfoText.contains("PC Gate Arrival")))
						{
							withPCBactivitiesButNoPCGactivities= withPCBactivitiesButNoPCGactivities+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNumm[] =flightNum.split("-");
							String flightId=getDriver().findElement(By.xpath("(//div[@id='selectedCard']["+i+"]/div/div/div/div)[1]")).getAttribute("title");
							String deparDate=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/div/div/div[8]/div/div[2]/p[4]"));
							email_BoardingActivities3.append("<tr><td><b style=\"color:red;\">"+flightNumm[0]+"</b></td><td><b style=\"color:red;\">"+flightId+"</b></td>"
									+ "<td><b style=\"color:red;\">"+deparDate+"</b></td></tr>");

						}
						if(flightInfoText.contains("PC Boarding")&& (!flightInfoText.contains("PCB Arrival ")))
							{
							withPCBactivitiesButNoPCBAactivities= withPCBactivitiesButNoPCBAactivities+1;
							String flightNum=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/descendant::p[1]"));
							String flightNumm[] =flightNum.split("-");
							String flightId=getDriver().findElement(By.xpath("(//div[@id='selectedCard']["+i+"]/div/div/div/div)[1]")).getAttribute("title");
							String deparDate=getElementText(By.xpath("//div[@id='selectedCard']["+i+"]/div/div/div[8]/div/div[2]/p[4]"));
							email_BoardingActivities4.append("<tr><td><b style=\"color:red;\">"+flightNumm[0]+"</b></td><td><b style=\"color:red;\">"+flightId+"</b></td>"
									+ "<td><b style=\"color:red;\">"+deparDate+"</b></td></tr>");

							}						
												
			}
			catch(Exception e)
			{
				missingFlight=missingFlight+1;
				//e.printStackTrace();
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> "+i+" - "+getElementText(By.xpath("//div[@id='selectedCard']["+i+"]"))+"</b>");
			} 

		}
		
		email_BoardingActivities5.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: #000000;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");

		email_BoardingActivities5.append("<h4 align=\"center\" style=\"color:#008ae6;\">Executed For :COBT For DIALCelebi</h4><h5 align=\"center\" style=\"color:#008ae6;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_BoardingActivities5.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:15%\"><b>Date</b></th><th style=\"width:20%\"><b>Total No. of Flights</b></th> <th style=\"width:20%\"><b>COBT  Not detected</b></th>"
			 		+ "<th style=\"width:25%\"><b>OFFBLOCK not detected</b></th><th style=\"width:20%\"><b>Actual OFFBLOCK & COBT diff  > 5mins</b></th>"
			 		+ " </tr>");
	/*	email_BoardingActivities5.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+cobtTimeDiff.size()+"</b></td>"
			 		+ " <td> <b style=\"color:red;\">"+cobtIsNotNull.size()+"</b></td> <td><b style=\"color:red;\">"+ofBlockIsNotNull.size()+"</b></td> <td><b style=\"color:red;\">"+str_cobtDiff+"</b></td>  </tr></table>");			 
*/
		
		
		
		
		}	
		}
