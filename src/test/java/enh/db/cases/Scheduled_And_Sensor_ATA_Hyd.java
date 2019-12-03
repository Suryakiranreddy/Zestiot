package enh.db.cases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class Scheduled_And_Sensor_ATA_Hyd {
	public static int totalScheduledArrival = 0;
	public static int nullSensorATA = 0;
	public static int notNullSensorATA =0;
	public static int onBlockFromSensor= 0;
	public static int onBlockFromCV =0;
	int totalSensorATA= 0;
	int totalArrivedWithSensorATA = 0;
	
	public static String str_LogID = null;
	public static String str_flight_Number= null;
	public static String str_flight_ArrivalId= null;
	public static String str_flight_STA = null;
	public static String str_flight_ETA= null;
	public static String str_flight_ATA = null;
	
	public static String str_LogID2 = null;
	public static String str_flight_Number_Arrival= null;
	public static String str_flight_Sensor_ATA= null;
	public static String str_flight_On_Block= null;
	public static String str_flight_Status= null;
	public static String str_Difference_OnBlock_SensorATA= null;
		
	public static ArrayList<String> sensorATA_NullList = new ArrayList<String>();
	public static ArrayList<String> status0List = new ArrayList<String>();
	public static ArrayList<String> status1List = new ArrayList<String>();
	
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Hyd1 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Hyd2 = new StringBuilder();
	public static StringBuilder email_report_Scheduled_And_Sensor_ATA_For_Hyd3 = new StringBuilder();
		
	
	public static void scheduledAndSensorATAForHyderabad() throws Exception{
		
	ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_017);
	while (result.next())
	{				
		totalScheduledArrival = result.getInt("count(*)");
		System.out.println(totalScheduledArrival);
		HtmlReportUtil.stepInfo("<b style=\"color:purple;\"> Airport : GMR_HYD :</b> <b style=\"color:green;\">Total No. of flights Scheduled Arrival (based on STA or Mediator-STA) = "+ totalScheduledArrival +"</b>");
	}
	ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_018);
	while (result2.next())
	{				
		notNullSensorATA = result2.getInt("count(*)");
		System.out.println(notNullSensorATA);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights detected by Sensor(Merged table) = "+ notNullSensorATA +"</b>");
	}
	ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_019);
	while (result3.next())
	{				
		nullSensorATA = result3.getInt("count(*)");
		System.out.println(nullSensorATA);
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights NOT detected by Sensor(Arrival table) = "+ nullSensorATA +"</b>");
	}
			
	ResultSet result4 = DBWrapper.Connect(SQL_Queries.strQuery_020);
	while (result4.next())
	{	
		String str_LogID = result4.getString("logid");
		String str_flight_Number = result4.getString("flightnumber");
		String str_flight_STA = result4.getString("sta");
		String str_flight_ETA = result4.getString("eta");
		String str_flight_ATA = result4.getString("ata");
		
		sensorATA_NullList.add(str_LogID);
					
		if(sensorATA_NullList.size()>0){
			System.out.println("In for loop");
			HtmlReportUtil.stepInfo("LogId:  "+ str_LogID +"||  FlightNo:  "+ str_flight_Number +" || STA:  "+ str_flight_STA +" ||ETA:  "+ str_flight_ETA +" || ATA:  "+ str_flight_ATA +" ");	
		}
			else{
			//HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total Flights for which Sensor_ATA is null = "+nullSensorATA +" </b>");	
			}
	}
	ResultSet result5 = DBWrapper.Connect(SQL_Queries.strQuery_033);
	while (result5.next())
	{				
		onBlockFromSensor = result5.getInt("count(*)");
		System.out.println(onBlockFromSensor);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights On-Block time is detected by Sensor (EquipActivity table) = "+ onBlockFromSensor +"</b>");
	}
	ResultSet result6 = DBWrapper.Connect(SQL_Queries.strQuery_034);
	while (result6.next())
	{				
		onBlockFromCV = result6.getInt("count(*)");
		System.out.println(onBlockFromCV);
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights On-Block time is detected by CV (EquipActivity table) = "+ onBlockFromCV +"</b>");
	}
	ResultSet result7 = DBWrapper.Connect(SQL_Queries.strQuery_035);
	while (result7.next()) {
		String str_flight_Status = result7.getString("status");
		if (str_flight_Status.equals("1"))
		{
			status1List.add(str_LogID2);
		}
		
		if (str_flight_Status.equals("0"))
		{
			status0List.add(str_LogID2);
		}
	}
		HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which Sensor_ATA is less than On-Block time (Merged table) = "+ status1List.size() +"</b>");
		HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which Sensor_ATA is greater than On-Block time (Merged table) = "+ status0List.size() +"</b>");
	ResultSet result8 = DBWrapper.Connect(SQL_Queries.strQuery_035);
	while (result8.next())
	{				
		String str_LogID2 = result8.getString("logid");
		String str_flight_Number_Arrival = result8.getString("flightnumber_arrival");
		String str_flight_Sensor_ATA = result8.getString("sensor_ata");
		String str_flight_On_Block = result8.getString("on_block_time");
		String str_flight_Status = result8.getString("status");
		String str_Difference_OnBlock_SensorATA = result8.getString("difference");
		
		if (str_flight_Status.equals("0"))
		{
			status0List.add(str_LogID2);
		if(status0List.size()>0)
		{
			HtmlReportUtil.stepInfo("LogId:  "+ str_LogID2 +"||  FlightNoArrival:  "+ str_flight_Number_Arrival +" || Sensor_ATA:  "+ str_flight_Sensor_ATA +" ||On_Block:  "+ str_flight_On_Block +" || Difference:  "+ str_Difference_OnBlock_SensorATA +" ");		
		}
		else 
		{
			//HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which Sensor_ATA is less than On-Block time (Merged table) = "+status0List.size()+" </b>");
		}
		}
	}
		
	DBWrapper.dbConnectionClose();
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void scheduledAndSensorATAForHyderabadReport() throws Exception {
		ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_017);
		while (result.next())
		{				
			totalScheduledArrival = result.getInt("count(*)");
			System.out.println(totalScheduledArrival);
			//HtmlReportUtil.stepInfo("<b style=\"color:purple;\"> Airport : GMR_HYD :</b> <b style=\"color:green;\">Total No. of flights Scheduled Arrival (based on STA or Mediator-STA) = "+ totalScheduledArrival +"</b>");
		}
		ResultSet result2 = DBWrapper.Connect(SQL_Queries.strQuery_018);
		while (result2.next())
		{				
			notNullSensorATA = result2.getInt("count(*)");
			System.out.println(notNullSensorATA);
			//HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights detected by Sensor(Merged table) = "+ notNullSensorATA +"</b>");
		}
		ResultSet result3 = DBWrapper.Connect(SQL_Queries.strQuery_019);
		while (result3.next())
		{				
			nullSensorATA = result3.getInt("count(*)");
			System.out.println(nullSensorATA);
			//HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights NOT detected by Sensor(Arrival table) = "+ nullSensorATA +"</b>");
		}
				
		ResultSet result4 = DBWrapper.Connect(SQL_Queries.strQuery_020);
		while (result4.next())
		{	
			String str_LogID = result4.getString("logid");
			String str_flight_Number = result4.getString("flightnumber");
			String str_flight_STA = result4.getString("sta");
			String str_flight_ETA = result4.getString("eta");
			String str_flight_ATA = result4.getString("ata");
			
			sensorATA_NullList.add(str_LogID);
						
			if(sensorATA_NullList.size()>0){
				System.out.println("In for loop");
				//HtmlReportUtil.stepInfo("LogId:  "+ str_LogID +"||  FlightNo:  "+ str_flight_Number +" || STA:  "+ str_flight_STA +" ||ETA:  "+ str_flight_ETA +" || ATA:  "+ str_flight_ATA +" ");	
			}
				else{
				//HtmlReportUtil.stepInfo("<b style=\"color:blue;\">=========Total Flights for which Sensor_ATA is null = "+nullSensorATA +" </b>");	
				}
		}
		ResultSet result5 = DBWrapper.Connect(SQL_Queries.strQuery_033);
		while (result5.next())
		{				
			onBlockFromSensor = result5.getInt("count(*)");
			System.out.println(onBlockFromSensor);
			//HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights On-Block time is detected by Sensor (EquipActivity table) = "+ onBlockFromSensor +"</b>");
		}
		ResultSet result6 = DBWrapper.Connect(SQL_Queries.strQuery_034);
		while (result6.next())
		{				
			onBlockFromCV = result6.getInt("count(*)");
			System.out.println(onBlockFromCV);
			//HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights On-Block time is detected by CV (EquipActivity table) = "+ onBlockFromCV +"</b>");
		}
		ResultSet result7 = DBWrapper.Connect(SQL_Queries.strQuery_035);
		while (result7.next()) {
			String str_flight_Status = result7.getString("status");
			if (str_flight_Status.equals("1"))
			{
				status1List.add(str_LogID2);
			}
			
			if (str_flight_Status.equals("0"))
			{
				status0List.add(str_LogID2);
			}
		}
			//HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which Sensor_ATA is less than On-Block time (Merged table) = "+ status1List.size() +"</b>");
			//HtmlReportUtil.stepInfo("<b style=\"color:red;\">No. of flights for which Sensor_ATA is greater than On-Block time (Merged table) = "+ status0List.size() +"</b>");
		ResultSet result8 = DBWrapper.Connect(SQL_Queries.strQuery_035);
		while (result8.next())
		{				
			String str_LogID2 = result8.getString("logid");
			String str_flight_Number_Arrival = result8.getString("flightnumber_arrival");
			String str_flight_Sensor_ATA = result8.getString("sensor_ata");
			String str_flight_On_Block = result8.getString("on_block_time");
			String str_flight_Status = result8.getString("status");
			String str_Difference_OnBlock_SensorATA = result8.getString("difference");
			
			if (str_flight_Status.equals("0"))
			{
				//status0List.add(str_LogID2);
			if(status0List.size()>0)
			{
				//HtmlReportUtil.stepInfo("LogId:  "+ str_LogID2 +"||  FlightNoArrival:  "+ str_flight_Number_Arrival +" || Sensor_ATA:  "+ str_flight_Sensor_ATA +" ||On_Block:  "+ str_flight_On_Block +" || Difference:  "+ str_Difference_OnBlock_SensorATA +" ");		
			}
			else 
			{
				//HtmlReportUtil.stepInfo("<b style=\"color:green;\">No. of flights for which Sensor_ATA is less than On-Block time (Merged table) = "+status0List.size()+" </b>");
			}
			}
		}
		
		email_report_Scheduled_And_Sensor_ATA_For_Hyd1.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #336600;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_Hyd1.append("<h4 align=\"center\" style=\"color:#336600;\">AirPort Name : GMR-Hyderabad</h4>");
		email_report_Scheduled_And_Sensor_ATA_For_Hyd1.append("<h4 align=\"center\" style=\"color:#336600;\">Executed For :Scheduled and Sensor-ATA</h4><h5 align=\"center\" style=\"color:#336600;\" >Execution Time: "+SQL_Queries.todayDayDateTime()+" </h5>");
		email_report_Scheduled_And_Sensor_ATA_For_Hyd1.append("<table style=\"width:100%\" id=\"t01\"><tr><th style=\"width:10%\"><b>Date</b></th><th style=\"width:15%\"><b>Total No. of Flights Scheduled Arrival</b></th> <th style=\"width:15%\"><b>No. of flights detected by Sensor</b></th>"
		 		+ "<th style=\"width:15%\"><b>No. of flights NOT detected by Sensor</b></th><th style=\"width:20%\"><b>No. of flights On-Block time is detected by Sensor</b></th><th style=\"width:15%\"><b>No. of flights On-Block time is detected by CV</b></th>"
		 		+ " </tr>");
		email_report_Scheduled_And_Sensor_ATA_For_Hyd1.append(" <tr> <td><b>"+SQL_Queries.yesterDate()+"</b></td> <td><b>"+totalScheduledArrival+"</b></td>"
		 		+ " <td> <b style=\"color:green;\">"+notNullSensorATA+"</b></td> <td><b style=\"color:red;\">"+nullSensorATA+"</b></td> <td><b style=\"color:green;\">"+onBlockFromSensor+"</b></td> <td><b style=\"color:green;\">"+onBlockFromCV+"</b></td></tr></table>");			 	
		email_report_Scheduled_And_Sensor_ATA_For_Hyd2.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATA_For_Hyd2.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #336600;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_Hyd2.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights Landed (Not Detected by Flight Sensor but detected from other data source)</caption><tr><th style=\"width:10%\"><b>LogID</b></th>"
				+ "<th style=\"width:15%\"><b>Arrival Flight No.</b></th> "
				+ "<th style=\"width:15%\"><b>STA</b></th>"
		 		+ "<th style=\"width:15%\"><b>ETA</b></th>"
		 		+ "<th style=\"width:20%\"><b>ATA</b></th>"
		 		+ " </tr>");
		ResultSet result41 = DBWrapper.Connect(SQL_Queries.strQuery_020);
		while (result41.next())
		{	
			String str_LogID = result41.getString("logid");
			String str_flight_Number = result41.getString("flightnumber");
			String str_flight_STA = result41.getString("sta");
			String str_flight_ETA = result41.getString("eta");
			String str_flight_ATA = result41.getString("ata");
			
			//sensorATA_NullList.add(str_LogID);
						
			if(sensorATA_NullList.size()>0){
				System.out.println("In for loop");
				email_report_Scheduled_And_Sensor_ATA_For_Hyd2.append(" <tr> <td><b style=\"color:red;\">"+str_LogID+"</b></td> <td><b style=\"color:red;\">"+str_flight_Number+"</b></td>"
				 		+ " <td> <b style=\"color:red;\">"+str_flight_STA+"</b></td> <td><b style=\"color:red;\">"+str_flight_ETA+"</b></td> <td><b style=\"color:red;\">"+str_flight_ATA+"</b></td></tr>");			 	
						}
				else{
					email_report_Scheduled_And_Sensor_ATA_For_Hyd2.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");	
				}
		}
		email_report_Scheduled_And_Sensor_ATA_For_Hyd2.append("</table>");
		
		email_report_Scheduled_And_Sensor_ATA_For_Hyd3.append("<br><br>");
		email_report_Scheduled_And_Sensor_ATA_For_Hyd3.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; color: white;} table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #336600;font-weight: bold;}</style>");
		email_report_Scheduled_And_Sensor_ATA_For_Hyd3.append("<table style=\"width:100%\" id=\"t01\"><caption> Total Flights for which LANDING time detected by Flight Sensor > ONBLOCK time detected by FLight Sensor</caption><tr>"
				+ "<th style=\"width:10%\"><b>LogID</b></th>"
				+ "<th style=\"width:15%\"><b>Arrival Flight No.</b></th> "
				+ "<th style=\"width:15%\"><b>Sensor-ATA</b></th>"
		 		+ "<th style=\"width:15%\"><b>On-Block</b></th>"
		 		+ "<th style=\"width:20%\"><b>Difference between Sensor_ATA and On-Block</b></th>"
		 		+ " </tr>");
		
		
		
			if(status0List.size()>0) {
				
		ResultSet result81 = DBWrapper.Connect(SQL_Queries.strQuery_035);
		while (result81.next())
		{				
			String str_LogID2 = result81.getString("logid");
			String str_flight_Number_Arrival = result81.getString("flightnumber_arrival");
			String str_flight_Sensor_ATA = result81.getString("sensor_ata");
			String str_flight_On_Block = result81.getString("on_block_time");
			String str_flight_Status = result81.getString("status");
			String str_Difference_OnBlock_SensorATA = result81.getString("difference");
			email_report_Scheduled_And_Sensor_ATA_For_Hyd3.append(" <tr> <td><b style=\"color:red;\">"+str_LogID2+"</b></td>"
				+ " <td><b style=\"color:red;\">"+str_flight_Number_Arrival+"</b></td>"
				+ " <td> <b style=\"color:red;\">"+str_flight_Sensor_ATA+"</b></td>"
				+ " <td><b style=\"color:red;\">"+str_flight_On_Block+"</b></td> "
				+ "<td><b style=\"color:red;\">"+str_Difference_OnBlock_SensorATA+"</b></td></tr>");			 	
				//HtmlReportUtil.stepInfo("LogId:  "+ str_LogID2 +"||  FlightNoArrival:  "+ str_flight_Number_Arrival +" || Sensor_ATA:  "+ str_flight_Sensor_ATA +" ||On_Block:  "+ str_flight_On_Block +" || Difference:  "+ str_Difference_OnBlock_SensorATA +" ");		
			
			
		}
		}
		else 
		{
			email_report_Scheduled_And_Sensor_ATA_For_Hyd3.append("<tr><td colspan=\"5\"><b style=\"color:red;\">No values found </b></td></tr>");
		}
		email_report_Scheduled_And_Sensor_ATA_For_Hyd3.append("</table>");		
		
		
		HtmlReportUtil.test.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Hyd1.toString());
		HtmlReportUtil.testHist.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Hyd1.toString());
		 
		 ExtentTest child0 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
		child0.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");			
		 ExtentTest child00 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
		 child00.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\">Total Flights Scheduled for arrival ("+SQL_Queries.yesterDate()+"): "+totalScheduledArrival+"</b>");
				
		 			 
		 ExtentTest child1 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights Landed (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 child1.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights Landed (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 ExtentTest child11 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights Landed (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 child11.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights Landed (Detected by Flight Sensor): "+notNullSensorATA+"</b>");
		 
		 ExtentTest child2 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights Landed (Not Detected by Flight Sensor but detected from other data source): "+nullSensorATA+"</b>");
		 child2.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Hyd2.toString());
		 ExtentTest child22 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights Landed (Not Detected by Flight Sensor but detected from other data source): "+nullSensorATA+"</b>");
		 child22.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Hyd2.toString());
		 	 
		 ExtentTest child3 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 child3.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 ExtentTest child33 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 child33.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights ONBLOCK (Detected by Flight Sensor): "+onBlockFromSensor+"</b>");
		 
		 ExtentTest child4 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights ONBLOCK (Not Detected by Flight Sensor but detected from other data source): "+onBlockFromCV+"</b>");
		 child4.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights ONBLOCK (Not Detected by Flight Sensor but detected from other data source): "+onBlockFromCV+"</b>");
		 ExtentTest child44 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights ONBLOCK (Not Detected by Flight Sensor but detected from other data source): "+onBlockFromCV+"</b>");
		 child44.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights ONBLOCK (Not Detected by Flight Sensor but detected from other data source): "+onBlockFromCV+"</b>");
		 
		 ExtentTest child5 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights for which LANDING time detected by Flight Sensor < ONBLOCK time detected by FLight Sensor : "+status1List.size()+"</b>");
		 child5.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights for which LANDING time detected by Flight Sensor < ONBLOCK time detected by FLight Sensor : "+status1List.size()+"</b>");
		 ExtentTest child55 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:green;\" align=\"center\"> Total Flights for which LANDING time detected by Flight Sensor < ONBLOCK time detected by FLight Sensor : "+status1List.size()+"</b>");
		 child55.log(LogStatus.INFO, "<b style=\"color:green;\" align=\"center\"> Total Flights for which LANDING time detected by Flight Sensor < ONBLOCK time detected by FLight Sensor : "+status1List.size()+"</b>");
		 
		 ExtentTest child6 = HtmlReportUtil.extentNoHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights for which LANDING time detected by Flight Sensor > ONBLOCK time detected by FLight Sensor : "+status0List.size()+"</b>");
		 child6.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Hyd3.toString());
		 ExtentTest child66 = HtmlReportUtil.extentPreserverHistory.startTest("<b style=\"color:red;\" align=\"center\"> Total Flights for which LANDING time detected by Flight Sensor > ONBLOCK time detected by FLight Sensor : "+status0List.size()+"</b>");
		 child66.log(LogStatus.INFO, email_report_Scheduled_And_Sensor_ATA_For_Hyd3.toString());

		 HtmlReportUtil.test.appendChild(child0).appendChild(child1).appendChild(child2).appendChild(child3).appendChild(child4).appendChild(child5).appendChild(child6);
		 HtmlReportUtil.testHist.appendChild(child00).appendChild(child11).appendChild(child22).appendChild(child33).appendChild(child44).appendChild(child55).appendChild(child66);
		DBWrapper.dbConnectionClose();
	
	}
	
}
	