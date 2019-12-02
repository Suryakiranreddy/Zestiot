package enh.web.pages;

import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.By;

import utilities.HtmlReportUtil;
import utilities.KeywordUtil;

public class Alerts extends KeywordUtil {

	/*
	 * static String str_subType = " COBT "; static String str_sevirty =
	 * " Critical "; static String str_type = " Flight Information ";
	 */

	private static By menu = By.xpath("//mat-icon[contains(.,'menu')] ");
	private static By alerts = By.linkText("Alerts");
	// ---------------------------VerifyAlert------------------------------

	/**********************************************************************************************************
	 * Method Name: VerifyDepatedFlightDetailsWithDB Description : Change History:
	 * Date Created By Change Description Code Review and date **** **********
	 * **************** ******************* 21/Sept/2019 Rajesh AD/-/Sept/2019
	 * 
	 * @param str_type
	 * @param str_subType
	 * @param str_flightInfo
	 ***********************************************************************************************************/
	public static void verifyFlightInfoAlerts()
			throws InterruptedException, SQLException {

		HtmlReportUtil
				.stepInfo("<b style=\"color:green;\">############## ZestIOT Flight Information Alerts Function Started #############</b>");
		hoverElement(menu);
		click(alerts);
		
		//Flight info
		click(By.xpath("//span/span[contains(.,'Flight Information')]"));
		click(By.xpath("//span[contains(.,' Flight Information ')]"));
		 ArrayList<String> list_subType  = new ArrayList<String>();
	        ArrayList<String> list_Severity  = new ArrayList<String>();  
	        list_subType.add("COBT");
	        list_subType.add("Eta Prediction");
	        list_subType.add("Bay Change");
	        list_subType.add("Departure Delay");
	        list_subType.add("Arrival Delay");
	        
	        list_Severity.add("Critical");
	        list_Severity.add("Major");
	        list_Severity.add("Minor");
	        for (int i = 0; i < list_subType.size(); i++) {
	        	click(By.xpath("//mat-select[@placeholder='select Subtype']"));
	    		click(By.xpath("//span[contains(.,' " +list_subType.get(i)+ " ')]"));
	        	for (int j = 0; j < list_Severity.size(); j++) {
	        		click(By.xpath("//mat-select[@placeholder='select Severity']"));
	        		click(By.xpath("//span[contains(.,' " +list_Severity.get(j)+ " ')]"));
	        		delay(3000);
	        		boolean dataFound = getDriver().findElements(By.xpath("//p[contains(.,'Alerts Not Found')]")).size()>0;
	        		if(dataFound){
	        			String str_notFound = getElementText(By.xpath("//p[contains(.,'Alerts Not Found')]"));
	        			HtmlReportUtil.stepInfo(
	        					"<b style=\"color:red;\"> Flight Information --> "+list_subType.get(i)+" | "+list_Severity.get(j)+" : " + str_notFound + "</b>");
	        		}else {
	        			HtmlReportUtil.stepInfo(
	        					"<b style=\"color:blue;\"> Flight Information --> "+list_subType.get(i)+" | "+list_Severity.get(j)+" : Alert Found </b>");
	        		}
				}
			}
	            
	        HtmlReportUtil
			.stepInfo("<b style=\"color:green;\">############## ZestIOT Flight Information Alerts Function Ended #############</b>");

	}

	public static void verifyNonComplianceAlerts()
			throws InterruptedException, SQLException {

		HtmlReportUtil
				.stepInfo("<b style=\"color:green;\">############## ZestIOT Non Compliance Alerts Function Started #############</b>");
		hoverElement(menu);
		click(alerts);
		
		//Flight info
		click(By.xpath("//span/span[contains(.,'Flight Information')]"));
		click(By.xpath("//span[contains(.,' Non Compliance ')]"));
		 ArrayList<String> list_subType  = new ArrayList<String>();
	        ArrayList<String> list_Severity  = new ArrayList<String>();  
	        list_subType.add("Geofence");
	        list_subType.add("Overspeed");
	        
	        list_Severity.add("Critical");
	        list_Severity.add("Major");
	        list_Severity.add("Minor");
	        for (int i = 0; i < list_subType.size(); i++) {
	        	click(By.xpath("//mat-select[@placeholder='select Subtype']"));
	    		click(By.xpath("//span[contains(.,' " +list_subType.get(i)+ " ')]"));
	        	for (int j = 0; j < list_Severity.size(); j++) {
	        		click(By.xpath("//mat-select[@placeholder='select Severity']"));
	        		click(By.xpath("//span[contains(.,' " +list_Severity.get(j)+ " ')]"));
	        		delay(3000);
	        		boolean dataFound = getDriver().findElements(By.xpath("//p[contains(.,'Alerts Not Found')]")).size()>0;
	        		if(dataFound){
	        			String str_notFound = getElementText(By.xpath("//p[contains(.,'Alerts Not Found')]"));
	        			HtmlReportUtil.stepInfo(
	        					"<b style=\"color:red;\"> Non Compliance --> "+list_subType.get(i)+" | "+list_Severity.get(j)+" : " + str_notFound + "</b>");
	        		}else {
	        			HtmlReportUtil.stepInfo(
	        					"<b style=\"color:blue;\"> Non Compliance --> "+list_subType.get(i)+" | "+list_Severity.get(j)+" : Alert Found </b>");
	        		}
				}
			}
	            
	        HtmlReportUtil
			.stepInfo("<b style=\"color:green;\">############## ZestIOT Non Compliance Alerts Function Ended #############</b>");

	}
	

	
}