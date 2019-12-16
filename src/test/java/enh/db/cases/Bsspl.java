package enh.db.cases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bsspl {
	
	public static ArrayList<String> list_logid = new ArrayList<String>();
	public static String logId=null;
	
	public static void bsspl(String flightNum) throws SQLException {
		
		if(!flightNum.equalsIgnoreCase("null")) {
		String FlightNum=flightNum.replace("-", " ");
		System.out.println("FlightNum - "+FlightNum);
		String SQL_Querry1="SELECT * FROM `DailyFlightSchedule_Merged` "
				+ "where date(IFNULL(atd, sensor_atd))= '"+SQL_Queries.yesterDate()+"' and operationunit = 22 and flightnumber_departure like '%"+FlightNum+"%' ";
		
		ResultSet result = DBWrapper.Connect(SQL_Querry1);
		while(result.next()) {
			 logId=  result.getString("LogId");
			list_logid.add(logId);
			System.out.println(list_logid);
			
		}
	System.out.println("list_logid.size() - "+list_logid.size());
		
			String SQL_Querry2= "SELECT * FROM `EquipActivityLogs` where flight_pk='"+logId+"' and operationname='fle' ";
			ResultSet result2 = DBWrapper.Connect(SQL_Querry2);
			while(result2.next()) {
				String flight_pk=  result2.getString("flight_pk");
				String flightno=  result2.getString("flightno");
				String devid=  result2.getString("devid");
				String operationname=  result2.getString("operationname");
				String flogdate=  result2.getString("flogdate");
				String tlogdate=  result2.getString("tlogdate");
				System.out.println(flight_pk+"|"+flightno+"|"+devid+"|"+operationname+"|"+flogdate+"|"+tlogdate);
				
			}
		}
	
		DBWrapper.dbConnectionClose();
	}

}
