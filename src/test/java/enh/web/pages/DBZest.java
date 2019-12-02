/*package enh.web.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import utilities.HtmlReportUtil;

public class DBZest {
	
	public static  Connection con;
	
	public static java.util.List<String> getDBConnectionAndExecuteQuery_FlightNumberArrival() throws SQLException {
		
		con = DriverManager.getConnection(
				"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		Statement stmt = con.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT * FROM `DailyFlightSchedule_Merged` where (date(atd)='"+DBZest.currentdate()+"' or date(sensor_atd)='"+DBZest.currentdate()+"') and\r\n" + 
		
				"operationunit = 4 and flightnumber_arrival not like '%leader%' and (sta is not null and eta is not null) and flightnumber_arrival like '%sg%'");	
		java.util.List<String> flights = new ArrayList<>();
		
		while (rs.next()){							        
	                String str_FlightNumberArrival = rs.getString("FlightNumber_Arrival");
	                flights.add(str_FlightNumberArrival);
	                System. out.println("Departed Flights from DB: "+str_FlightNumberArrival);
	                HtmlReportUtil
					.stepInfo("<b style=\"color:green;\">Departed Flights from DB: " + str_FlightNumberArrival + "</b>");
	               
	        }	
		
		Collections.sort(flights);
		
		 return flights;
	}
	
	public static void dbConnectionClose() throws SQLException {
		con.close();
	}
	
	public static String currentdate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
public static java.util.List<String> verify_that_On_Block_and_Off_Block_timestamps_are_between_Sensor_ATA_and_Sensor_ATD_timestamps() throws SQLException, ParseException {
		
		con = DriverManager.getConnection(
				"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		Statement stmt = con.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT logid, flightnumber_arrival, Sensor_ATA, On_Block_Time, Off_Block_Time, Sensor_ATD FROM `DailyFlightSchedule_Merged`\r\n" + 
				"  where (date(atd)='2019-09-21' or date(sensor_atd)='2019-09-21') and \r\n" + 
				"operationunit = 4 and flightnumber_arrival not like '%leader%' and \r\n" + 
				"(sta is not null and eta is not null) and flightnumber_arrival like '%sg%'");	
		java.util.List<String> logId = new ArrayList<>();
		
		
		while (rs.next()){							        
            
			String str_LogID = rs.getString("logid");
			System.out.println(str_LogID);
			String	flight_NumberArrival = rs.getString("flightnumber_arrival");
			System.out.println(flight_NumberArrival.trim());
			String	flight_Sensor_ATA1 = rs.getString("Sensor_ATA");
			//String mystring = new String(flight_Sensor_ATA);
			String flight_Sensor_ATA =flight_Sensor_ATA1.substring(0, 19);
			Date sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(flight_Sensor_ATA);
			System.out.println(sdf);
			System.out.println(flight_Sensor_ATA.trim());
			String	flight_On_Block_Time = rs.getString("On_Block_Time");
			System.out.println(flight_On_Block_Time.trim());
			String	flight_Off_Block_Time = rs.getString("Off_Block_Time");
			System.out.println(flight_Off_Block_Time.trim());
			String	flight_Sensor_ATD  = rs.getString("Sensor_ATD");
			System.out.println(flight_Sensor_ATD.trim());
			            
            
    }	
	
		Collections.sort(logId);
		 return logId;
	}
	
	

	public static void main(String[] args) throws SQLException, ParseException {
		DBZest.verify_that_On_Block_and_Off_Block_timestamps_are_between_Sensor_ATA_and_Sensor_ATD_timestamps();
		DBZest.dbConnectionClose();
		
		
	}
	

	
	

}
*/