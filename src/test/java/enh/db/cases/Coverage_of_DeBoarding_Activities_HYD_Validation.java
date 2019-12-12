package enh.db.cases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.HtmlReportUtil;

public class Coverage_of_DeBoarding_Activities_HYD_Validation {
	
	public static int totalFlightsArrived= 0;
	public static int totalActivePassengerCoaches =0;
	
	public static ArrayList<String> List_totalPassengerCoachesUtilizedForDeboarding = new ArrayList<String>();
	public static ArrayList<String> List_totalFlightsWithAllDeboardingActivities = new ArrayList<String>();
	
		
	public static void Coverage_of_DeBoarding_Activities_HYD_Validation_Report(int operationunit, String devID) throws Exception {
		
		ResultSet result = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightSchedule_Merged` where gmrpk_arrival in (SELECT gmrpk FROM `DailyFlightScheduleArrival_GMR` \r\n "
				+ "where date(IFNULL(sta,mediator_sta))= '"+SQL_Queries.yesterDate()+"' and operationunit= "+operationunit+")");
		while (result.next())
		{				
			totalFlightsArrived = result.getInt("count(*)");
		}
		System.out.println(totalFlightsArrived);
		
		ResultSet result2 = DBWrapper.Connect("SELECT count(distinct(devid)) FROM `HealthMonitor` where date(logdate) = '"+SQL_Queries.yesterDate()+"' and devid like '%"+devID+"%'");
		while (result2.next())
		{				
			totalActivePassengerCoaches = result2.getInt("count(distinct(devid))");
		}
		System.out.println(totalActivePassengerCoaches);
		
		ResultSet result3 = DBWrapper.Connect("SELECT devid FROM `EquipActivityLogs` where date(flogdate) = '"+SQL_Queries.yesterDate()+"' \r\n"
				+ "and devid like '%"+devID+"%' and assigned=1 and operationname ='pcd' group by devid");
		while (result3.next())
		{				
			String str_devID = result3.getString("devid");
			List_totalPassengerCoachesUtilizedForDeboarding.add(str_devID);
		}
		System.out.println(List_totalPassengerCoachesUtilizedForDeboarding.size());
		
		ResultSet result4 = DBWrapper.Connect("SELECT flight_pk FROM `EquipActivityLogs` where date(flogdate) = '"+SQL_Queries.yesterDate()+"' and devid like '%"+devID+"%' \r\n"
				+ "and assigned=1 and operationname in ('pcd', 'pcda') group by flight_pk");
		while (result4.next())
		{				
			String str_flight_Pk = result3.getString("flight_pk");
			List_totalFlightsWithAllDeboardingActivities.add(str_flight_Pk);
		}
		System.out.println(List_totalFlightsWithAllDeboardingActivities.size());
		
		
		DBWrapper.dbConnectionClose();
		}
				
	}

	