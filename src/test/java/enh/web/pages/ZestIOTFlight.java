package enh.web.pages;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ZestIOTFlight {
	
	static WebDriver driver;
	static JavascriptExecutor js = (JavascriptExecutor) driver;
	static Actions action;
	
	static String str_ChocksOff;
	static String str_ChocksOn;
	static LocalTime int_ATATim;
	static LocalTime int_ChocksOn;
	static LocalTime int_Landed;
	static String str_AirBorneTime;
	static LocalTime int_ChocksOff;
	static String str_ATDTime;
	static LocalTime time_ATDTime;
	static java.util.Date date_Landed_Date;
	static java.util.Date date_AirBorneTime_Date;
	static int b;
	
	/**********************************************************************************************************
	 * Method Name: VerifyingTime
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         21/Sept/2019                   Rajesh			                                        AD/-/Sept/2017                                      
	 ***********************************************************************************************************/
	public static void VerifyingATAandATDTime() throws InterruptedException, SQLException, IOException {
		WebElement elm3 = driver.findElement(By.xpath("//mat-icon[contains(.,'menu')]"));
		action.moveToElement(elm3).build().perform();
		
		WebElement elm4 = driver.findElement(By.xpath("//a[contains(.,'Flight Info')]"));
		action.moveToElement(elm4).build().perform();
		
	

		
		System.out.println("############## ZestIOT Departed Flights Validation with DB Function Started #############");
		driver.findElement(By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]")).click();
		String str_DepatedFlights = driver.findElement(
				By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]")).getText();
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		System.out.println(str_DepatedFlightsCount);
		driver.findElement(By.xpath(
				"/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-airport-view-v1/div/div[1]/button[2]/mat-icon")).click();

		List<WebElement> elm_flight = driver.findElements(By.cssSelector(".flightno-col p"));
		for (WebElement webElement1 : elm_flight) {
			js.executeScript("arguments[0].scrollIntoView();", webElement1);
			
		}
	
		List<String> flightsUi = new ArrayList<>();
		List<WebElement> elm_flights = driver.findElements(By.cssSelector(".flightno-col p"));
		System.out.println(elm_flights.size());
		for (int i = 0; i <= elm_flights.size()-3; i++) {
			
			try {
			b = i+1;
			String str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
			flightsUi.add(str_DepartedFlights);
			elm_flights.get(i).click();
			try {
				WebElement elm1 = driver.findElement(By.xpath("(//*[@id=\"selectedCard\"]/div/div[1]/div[7]/div/div[1])["+b+"]"));
				action.moveToElement(elm1).build().perform();
				System.out.println("================Departed Flight Number: "+str_DepartedFlights+"===============");
			
			//######################## Capture Landed Info ##############################
			String strLanded = driver.findElement(By.xpath("(//*[@id='selectedCard']/div/div[1]/div[7]/div/div[2]/p[4])["+b+"]")).getText();
			System.out.println(strLanded);
			String[] strLanded1  = strLanded.split(" ");
			String str_Landed = strLanded1[2];
			String str_Landed_Date = strLanded1[1];
			int_Landed = LocalTime.parse(str_Landed);
			date_Landed_Date = new SimpleDateFormat("dd/MM/yyyy").parse(str_Landed_Date); 
			System.out.println("Landed Time: "+str_Landed+"");
			
			//######################## ATA Info ##############################
			String strATATime = driver.findElement(By.xpath("(//*[@id=\"selectedCard\"]/div/div[1]/div[7]/div/div[2]/p[3]/span)["+b+"]")).getText();
			System.out.println(strATATime);
			String[] strATATime1  = strATATime.split(" ");
			String str_ATATime = strATATime1[1];
			System.out.println(str_ATATime);
			int_ATATim = LocalTime.parse(str_ATATime);
			driver.findElement(By.xpath("//*[@id=\"staticheader\"]/div[2]")).click();
			System.out.println("ATA Time: "+strATATime+"");
			
			//######################## Chocks On Info ##############################
			boolean ChocksOnTime = driver.findElements(By.xpath("//div[@title='Bay Chocks-On']/following-sibling::div/div[2]/div/p/span[1]")).size()>0;
			if(ChocksOnTime) {
				str_ChocksOn = driver.findElement(By.xpath("//div[@title='Bay Chocks-On']/following-sibling::div/div[2]/div/p/span[1]")).getText();
			}else {
				str_ChocksOn = driver.findElement(By.xpath("(//div[@title='Bay Chocks-On']/following-sibling::div/div[2]/div[1]/p/span[1])[2]")).getText();		
			}
			System.out.println("Chocks On Time: "+str_ChocksOn+"");
			int_ChocksOn = LocalTime.parse(str_ChocksOn);
			
			//######################## Chocks Off Info ##############################
			boolean ChocksOff = driver.findElements(By.xpath("//div[@title=\"Bay Chocks-Off\"]/following-sibling::div/div[2]/div/p/span[2]")).size()>0;
				//getDriver().findElements(By.xpath("((//div[@title=\"Bay Chocks-Off\"]/following-sibling::div)[3]/div/div/p/span[2])[2]")).size()>0;
			if(ChocksOff) {
				str_ChocksOff = driver.findElement(By.xpath("//div[@title='Bay Chocks-Off']/following-sibling::div/div[2]/div/p/span[2]")).getText();
			}else {
				str_ChocksOff = driver.findElement(By.xpath("(//div[@title=\"Bay Chocks-Off\"]/following-sibling::div)[1]/div[1]/div[1]/p/span[2]")).getText();		
				
			}
			int_ChocksOff = LocalTime.parse(str_ChocksOff);
			//######################## AirBorne Off Info ##############################
			WebElement elm2 = driver.findElement(By.xpath("(//*[@id=\"selectedCard\"]/div/div[1]/div[8]/div/div[1])["+b+"]"));
			action.moveToElement(elm2).build().perform();
			String strAirBorneTime = driver.findElement(By.xpath("(//p[contains(.,' AIRBORNE')])["+b+"]")).getText();	
			String[] strAirBorneTime1  = strAirBorneTime.split(" ");
			str_AirBorneTime = strAirBorneTime1[2];
			String str_AirBorneTime_Date = strAirBorneTime1[1];
			date_AirBorneTime_Date = new SimpleDateFormat("dd/MM/yyyy").parse(str_AirBorneTime_Date);

			//######################## Landed time less than ATA on time ##############################
			boolean Landed_Less_Than_ATA = int_ATATim.isAfter(int_Landed);
			if(Landed_Less_Than_ATA) {
				System.out.println("<b style=\"color:green;\">Landed Time Less then ATA Time: "+int_Landed+"<"+int_ATATim+"</b>");
			}else {
				System.out.println("<b style=\"color:red;\">Landed Time Less then ATA Time: "+int_Landed+"<"+int_ATATim+"</b>");
			}
			
			//######################## ATA Time Equals to Chocks on Time ##############################
			boolean ATATim_Less_Than_ChocksOn = int_ChocksOn.equals(int_ATATim);
			if(ATATim_Less_Than_ChocksOn) {
				System.out.println("<b style=\"color:green;\">ATA Time Equals to Chocks on Time: "+int_ATATim+"="+int_ChocksOn+"</b>");
			}else {
				System.out.println("<b style=\"color:red;\">ATA Time is not Equals to Chocks on Time: "+int_ATATim+"="+int_ChocksOn+"</b>");
			}
			} catch (Exception e) {
				System.out.println("<b style=\"color:red;\">: Exception: "+e+"</b>");
			}
			//######################## Chocks on time less than Chocks off time ###############################
			boolean ChocksOn_Less_Than_ChocksOff = int_ChocksOff.isAfter(int_ChocksOn);
			if(ChocksOn_Less_Than_ChocksOff) {
				System.out.println("<b style=\"color:green;\">Chocks on Time Less then Chocks Off Time: "+int_ChocksOn+"<"+int_ChocksOff+"</b>");
			}else {
				System.out.println("<b style=\"color:red;\">Chocks on Time is not Less then Chocks Off Time: "+int_ChocksOn+"<"+int_ChocksOff+"</b>");
			}
			
			//######################## Chocks off Time  Equals to ATD time ###############################
			boolean ChocksOff_Equals_To_ATD = int_ChocksOff.equals(time_ATDTime);
			if(ChocksOff_Equals_To_ATD) {
				System.out.println("<b style=\"color:green;\">Chocks off Time Equals to ATD time: "+int_ChocksOff+"="+int_ATATim+"</b>");
			}else {
				System.out.println("<b style=\"color:red;\">Chocks off Time not Equals to ATD time: "+int_ChocksOff+"="+int_ATATim+"</b>");
			}
			
			
			//######################## Chocks off time less than AirBorne time ###############################
			
			LocalTime time_AirBorneTime = LocalTime.parse(str_AirBorneTime);	
			boolean ChocksOff_Less_Than_AirBorne = time_AirBorneTime.isAfter(int_ChocksOff);
			boolean Landed_Date_Equal_to_AirBorne = date_Landed_Date.equals(date_AirBorneTime_Date);
			
			if(Landed_Date_Equal_to_AirBorne) {
			if(ChocksOff_Less_Than_AirBorne) {
				System.out.println("<b style=\"color:green;\">Chocks Off Time Less then AirBorne Time: "+int_ChocksOff+"<"+time_AirBorneTime+"</b>");
			}else {
				System.out.println("<b style=\"color:red;\">Chocks Off Time is not Less then AirBorne Time: "+int_ChocksOff+"<"+time_AirBorneTime+"</b>");
			}
			}else {
				System.out.println("<b style=\"color:blue;\">Landed and airborne time is different: "+date_Landed_Date+"Not equal"+date_AirBorneTime_Date+"</b>");
			}
			elm_flights.get(i).click();
			js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(b));
			
			}catch (Exception e) {
				
				System.out.println("<b style=\"color:red;\">Exception: "+e+"</b>");
				
				elm_flights.get(i).click();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(b));
			}
		}
		
	}
	
	/**********************************************************************************************************
	 * Method Name: VerifyingChoksOnandChocksOff
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         21/Sept/2019                   Rajesh			                                        AD/-/Sept/2017                                      
	 ***********************************************************************************************************/
	public static void VerifyingChoksOnandChocksOff() throws InterruptedException, SQLException, IOException {
		WebElement elm3 = driver.findElement(By.xpath("//mat-icon[contains(.,'menu')]"));
		action.moveToElement(elm3).build().perform();
		
		WebElement elm4 = driver.findElement(By.xpath("//a[contains(.,'Flight Info')]"));
		action.moveToElement(elm4).build().perform();
		driver.findElement(By.xpath("//a[contains(.,'Turnaround List')]"));
		
		
		driver.findElement(By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]")).click();
		String str_DepatedFlights = driver.findElement(
				By.xpath("//*[@class='mat-tab-label-content'][contains(.,'Departed')]")).getText();
		String str_DepatedFlightsCount = str_DepatedFlights.replaceAll("[^0-9]", "");
		System.out.println(str_DepatedFlightsCount);
		driver.findElement(By.xpath(
				"/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-airport-view-v1/div/div[1]/button[2]/mat-icon")).click();

		List<WebElement> elm_flight = driver.findElements(By.cssSelector(".flightno-col p"));
		for (WebElement webElement1 : elm_flight) {
			js.executeScript("arguments[0].scrollIntoView();", webElement1);
			
		}
	
		List<String> flightsUi = new ArrayList<>();
		List<WebElement> elm_flights = driver.findElements(By.cssSelector(".flightno-col p"));
		System.out.println(elm_flights.size());
		for (int i = 0; i <= elm_flights.size()-3; i++) {
			
			try {
			b = i+1;
			String str_DepartedFlights = elm_flights.get(i).getText().split("-")[0].trim();
			flightsUi.add(str_DepartedFlights);
			elm_flights.get(i).click();
			
			//######################## Chocks On Info ##############################
			boolean ChocksOnTime = driver.findElements(By.xpath("//div[@title='Bay Chocks-On']/following-sibling::div/div[2]/div/p/span[1]")).size()>0;
			if(ChocksOnTime) {
				str_ChocksOn = driver.findElement(By.xpath("//div[@title='Bay Chocks-On']/following-sibling::div/div[2]/div/p/span[1]")).getText();
			}else {
				str_ChocksOn = driver.findElement(By.xpath("(//div[@title='Bay Chocks-On']/following-sibling::div/div[2]/div[1]/p/span[1])[2]")).getText();		
			}
			System.out.println("Chocks On Time: "+str_ChocksOn+"");
			int_ChocksOn = LocalTime.parse(str_ChocksOn);
			
			//######################## Chocks Off Info ##############################
			boolean ChocksOff = driver.findElements(By.xpath("//div[@title=\"Bay Chocks-Off\"]/following-sibling::div/div[2]/div/p/span[2]")).size()>0;
				//getDriver().findElements(By.xpath("((//div[@title=\"Bay Chocks-Off\"]/following-sibling::div)[3]/div/div/p/span[2])[2]")).size()>0;
			if(ChocksOff) {
				str_ChocksOff = driver.findElement(By.xpath("//div[@title='Bay Chocks-Off']/following-sibling::div/div[2]/div/p/span[2]")).getText();
			}else {
				str_ChocksOff = driver.findElement(By.xpath("(//div[@title=\"Bay Chocks-Off\"]/following-sibling::div)[1]/div[1]/div[1]/p/span[2]")).getText();		
				
			}
			int_ChocksOff = LocalTime.parse(str_ChocksOff);
			
			//######################## Chocks on time less than Chocks off time ###############################
			boolean ChocksOn_Less_Than_ChocksOff = int_ChocksOff.isAfter(int_ChocksOn);
			if(ChocksOn_Less_Than_ChocksOff) {
				System.out.println("<b style=\"color:green;\">Chocks on Time Less then Chocks Off Time: "+int_ChocksOn+"<"+int_ChocksOff+"</b>");
			}else {
				System.out.println("<b style=\"color:red;\">Chocks on Time is not Less then Chocks Off Time: "+int_ChocksOn+"<"+int_ChocksOff+"</b>");
			}
			
			elm_flights.get(i).click();
			js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(b));
			
			}catch (Exception e) {
				
				System.out.println("<b style=\"color:red;\">Exception: "+e+"</b>");
				
				elm_flights.get(i).click();
				js.executeScript("arguments[0].scrollIntoView();", elm_flights.get(b));
			}
			}
		
	}
	
	

}
