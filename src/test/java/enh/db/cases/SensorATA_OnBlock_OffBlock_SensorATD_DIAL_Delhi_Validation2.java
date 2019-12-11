package enh.db.cases;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class SensorATA_OnBlock_OffBlock_SensorATD_DIAL_Delhi_Validation2{
	public static int totalScheduledArrival =0;
	public static int totalScheduledDeparture= 0;
	public static int notNullSensorATA=0;
	public static int notNullOnBlockTime= 0;
	public static int notNullOffBlockTime=0;
	public static int notNullSensorATD =0;
	public static int totalFlightsContainAll= 0;
	
	public static ArrayList<String> sensorATAIsNullList = new ArrayList<String>();
	public static ArrayList<String> onBlockIsNullList = new ArrayList<String>();
	public static ArrayList<String> offBlockIsNullList = new ArrayList<String>();
	public static ArrayList<String> sensorATDIsNullList= new ArrayList<String>();
	public static ArrayList<String> status0List = new ArrayList<String>();
	public static ArrayList<String> status1List = new ArrayList<String>();
	
	public static StringBuilder email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_DIAL_Delhi1 = new StringBuilder();
	
	
	public static void SensorATA_LessThanOnBlock_LessThanOffBlock_LessThanSensorATD_Delhi_Report(int operationunit) throws Exception{
	
		ResultSet result = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightScheduleArrival_GMR`where \r\n" + 
				"date(IFNULL(sta, eta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+"");
		while (result.next())
		{				
			totalScheduledArrival = result.getInt("count(*)");
			System.out.println(totalScheduledArrival);
		}	
		
		ResultSet result1 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightScheduleDeparture_GMR` where \r\n" +
				"date(IFNULL(std, etd))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+"");
		while (result1.next())
		{				
			totalScheduledDeparture = result1.getInt("count(*)");
			System.out.println(totalScheduledDeparture);
		}	
		
		ResultSet result2 = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where \r\n" + 
			"gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta, eta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") \r\n" + 
			"and sensor_ata is not null");
						while (result2.next())
						{				
							notNullSensorATA = result2.getInt("count(*)");
							System.out.println(notNullSensorATA);
						}
						
		ResultSet result3 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, on_block_time, off_block_time, sensor_atd FROM `DailyFlightSchedule_Merged` where\r\n" + 
			"gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`where date(IFNULL(sta, eta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") \r\n" + 
			"and (sensor_ata is null and on_block_time is not null and off_block_time is not null and sensor_atd is not null)");
						while (result3.next())
						{				
							String str_LogID = result3.getString("logid");
							String str_flightNumber_Arrival= result3.getString("flightnumber_arrival");
							String str_On_Block_Time = result3.getString("on_block_time");
							String str_Off_Block_Time = result3.getString("off_block_time");
							String str_Sensor_ATD = result3.getString("sensor_atd");
							sensorATAIsNullList.add(str_LogID);
						}
						
		ResultSet result4 = DBWrapper.Connect("SELECT * FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR` \r\n"
				+ "where date(IFNULL(sta, eta))='"+SQL_Queries.yesterDate()+"' and operationunit = "+operationunit+" ) and on_block_time is not null");
								while (result4.next())
								{				
									notNullOnBlockTime = result4.getInt("count(*)");
									System.out.println(notNullOnBlockTime);
								}
								
		ResultSet result5 = DBWrapper.Connect("SELECT logid, flightnumber_arrival, sensor_ata, off_block_time, sensor_atd FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR`\r\n "
			+ "where date(IFNULL(sta, eta))='"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+") and on_block_time is null \r\n"
					+ "and (sensor_ata is not null and off_block_time is not null and sensor_atd is not null)");
								while (result5.next())
								{	
									String str_LogID = result5.getString("logid");
									String str_flightNumber_Arrival= result5.getString("flightnumber_arrival");
									String str_Sensor_ATA = result5.getString("sensor_ata");
									String str_Off_Block_Time = result5.getString("off_block_time");
									String str_Sensor_ATD = result5.getString("sensor_atd");
											
									onBlockIsNullList.add(str_LogID);
								}
			 DBWrapper.dbConnectionClose();	
}
}

	