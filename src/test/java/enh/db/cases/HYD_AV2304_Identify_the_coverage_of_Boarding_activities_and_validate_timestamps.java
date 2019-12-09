package enh.db.cases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.annotations.Test;


public class HYD_AV2304_Identify_the_coverage_of_Boarding_activities_and_validate_timestamps {
	public static String TotalFlightsDeparted =null;
	public static ArrayList<String> list_TotalNumberofPassengerCoaches= new ArrayList<String>();
	public static ArrayList<String> list_TotalNumberofPassengerCoachesUtilizedforBoarding = new ArrayList<String>();
	
	@Test
	public static void hYD_AV2304_Identify_the_coverage_of_Boarding_activities_and_validate_timestamps(int operationUnit, String deviceID) throws SQLException {
		
		ResultSet result = DBWrapper.Connect("SELECT count(*) FROM `DailyFlightScheduleDeparture_GMR`"
				+ " where (date(std)='"+SQL_Queries.yesterDate()+"' or date(mediator_std)='"+SQL_Queries.yesterDate()+"') and operationunit= "+operationUnit+"");
		while (result.next())
		{				
		 TotalFlightsDeparted = result.getString("count(*)");
		}	
		System.out.println(TotalFlightsDeparted);
		
		ResultSet result1 = DBWrapper.Connect("SELECT distinct(devid) FROM `GPSLogs` "
				+ "where date(logdate)= '"+SQL_Queries.yesterDate()+"' and devid like '%"+deviceID+"%'");//rgi_ACOA
		while (result1.next())
		{				
			String	TotalNumberofPassengerCoaches = result1.getString("distinct(devid)");
			list_TotalNumberofPassengerCoaches.add(TotalNumberofPassengerCoaches);
		}	
		System.out.println(list_TotalNumberofPassengerCoaches.size());
		
		ResultSet result2 = DBWrapper.Connect("SELECT distinct(devid) FROM `EquipActivityLogs`"
				+ " where (date(flogdate) = '"+SQL_Queries.yesterDate()+"' or date(tlogdate) = '"+SQL_Queries.yesterDate()+"') and devid like '%"+deviceID+"%' and operationname ='pcb'");//rgi_ACOA
		while (result2.next())
		{				
			String	TotalNumberofPassengerCoaches = result2.getString("distinct(devid)");
			list_TotalNumberofPassengerCoachesUtilizedforBoarding.add(TotalNumberofPassengerCoaches);	
		}	
		
		System.out.println(list_TotalNumberofPassengerCoachesUtilizedforBoarding.size());		
		list_TotalNumberofPassengerCoaches.removeAll(list_TotalNumberofPassengerCoachesUtilizedforBoarding);		
		System.out.println(list_TotalNumberofPassengerCoachesUtilizedforBoarding);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		

}
