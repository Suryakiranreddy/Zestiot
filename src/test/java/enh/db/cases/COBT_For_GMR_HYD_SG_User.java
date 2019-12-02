package enh.db.cases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utilities.HtmlReportUtil;
import utilities.SendMailBodyToZestIOT;

public class COBT_For_GMR_HYD_SG_User {
	 public static ArrayList<String> cobtIsNotNull= new ArrayList<String>();
	 public static ArrayList<String> ofBlockIsNotNull= new ArrayList<String>();
	 public static ArrayList<String> cobtTimeDiff= new ArrayList<String>();
	 
	 public static void cOBT_For_GMR_HYD_SG_User() throws Exception{
		  Connection con = DriverManager.getConnection(
					"jdbc:mysql://avileapuat.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
					"AviLeap_Read");
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(SQL_Queries.strQuery_14);
            while (result.next()){
				
				String str_LogID = result.getString("Logid");	
				String str_FlightNumber_Arrival = result.getString("FlightNumber_Arrival");
				String str_cobt = result.getString("cobt");
				cobtIsNotNull.add(str_cobt);
				String str_Off_Block_Time = result.getString("Off_Block_Time");				
				//HtmlReportUtil.stepInfo("");
				//System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | ");
				System.out.println(" ");
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> FlightNumber:-</b><b style=\"color:red;\"> "+str_FlightNumber_Arrival
						+" </b>|| <b style=\"color:red;\">Cobt:-</b><b style=\"color:red;\"> "+ str_cobt 
						+"<b> || <b style=\"color:red;\">Off Block:- </b> <b style=\"color:red;\">"+ str_Off_Block_Time +"  </b>");		
			}
			System.out.println("Total Flights with cobt is null:- "+cobtIsNotNull.size());
			//HtmlReportUtil.stepInfo("<b style=\"color:red;\">  No. of Flights for which cobt is null::- "+cobtIsNotNull.size() +"</b>");
			ResultSet result2 = stmt.executeQuery(SQL_Queries.strQuery_15);
			while (result2.next()){
				
				String str_LogID = result2.getString("Logid");	
				String str_FlightNumber_Arrival = result2.getString("FlightNumber_Arrival");
				String str_cobt = result2.getString("cobt");
				
				String str_Off_Block_Time = result2.getString("Off_Block_Time");
				ofBlockIsNotNull.add(str_Off_Block_Time);
				//HtmlReportUtil.stepInfo("");
				//System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | ");
				System.out.println(" ");
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> FlightNumber:-</b><b style=\"color:red;\"> "+str_FlightNumber_Arrival
						+" </b>|| <b style=\"color:red;\">Cobt:-</b><b style=\"color:red;\"> "+ str_cobt 
						+"<b> || <b style=\"color:red;\">Off Block:- </b> <b style=\"color:red;\">"+ str_Off_Block_Time +"  </b>");			
			}
			System.out.println("Total Flights with off block time is null:- "+ofBlockIsNotNull.size());
			//HtmlReportUtil.stepInfo("<b style=\"color:red;\"> No of Flights for which off block time is null:- "+ofBlockIsNotNull.size() +"</b>");
			ResultSet result3 = stmt.executeQuery(SQL_Queries.strQuery_16);
			while (result3.next()){
				String str_LogID = result3.getString("Logid");	
				String str_FlightNumber_Arrival = result3.getString("FlightNumber_Arrival");
				String str_cobt = result3.getString("cobt");
				
				String str_Off_Block_Time = result3.getString("Off_Block_Time");
				String str_cobtTimeDiff = result3.getString("CONCAT('',TIMEDIFF(Off_Block_Time, cobt))");
				int str_cobtSecDiff1 = result3.getInt("TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt))");
				int str_cobtSecDiff2 = result3.getInt("TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time))");
				cobtTimeDiff.add(str_Off_Block_Time);
				System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | "+ str_cobtTimeDiff +" | ");
				
				//HtmlReportUtil.stepInfo("");
				if(str_cobtSecDiff1>300 || str_cobtSecDiff2>300  ) {
				//System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | "+ str_cobtTimeDiff +" | ");
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> FlightNumber:-</b><b style=\"color:red;\"> "+str_FlightNumber_Arrival
						+" </b>|| <b style=\"color:red;\">Cobt:-</b><b style=\"color:red;\"> "+ str_cobt 
						+"<b> || <b style=\"color:red;\">Off Block:- </b> <b style=\"color:red;\">"+ str_Off_Block_Time 
						+" </b>|| <b style=\"color:red;\">Cobt Diff:-</b><b style=\"color:red;\"> "+ str_cobtTimeDiff +"  </b>");
				}else {
					/*HtmlReportUtil.stepInfo("<b style=\"color:green;\"> FlightNumber:-</b><b style=\"color:green;\"> "+str_FlightNumber_Arrival
							+" </b>|| <b style=\"color:green;\">Cobt:-</b><b style=\"color:green;\"> "+ str_cobt 
							+"<b> || <b style=\"color:green;\">Off Block:- </b> <b style=\"color:green;\">"+ str_Off_Block_Time 
							+" </b>|| <b style=\"color:green;\">Cobt Diff:-</b><b style=\"color:green;\"> "+ str_cobtTimeDiff +"  </b>");	
				*/
				}
				
			}
			System.out.println("Total Flights  - "+cobtTimeDiff.size());
			HtmlReportUtil.stepInfo("<b style=\"color:green;\"> Total number of Flights: - "+cobtTimeDiff.size() +"</b>");
			HtmlReportUtil.stepInfo("<b style=\"color:red;\">  No. of Flights for which cobt is null::- "+cobtIsNotNull.size() +"</b>");
			HtmlReportUtil.stepInfo("<b style=\"color:red;\"> No of Flights for which off block time is null:- "+ofBlockIsNotNull.size() +"</b>");
			
			ResultSet result4 = stmt.executeQuery(SQL_Queries.strQuery_17);
			while (result4.next()){			
				String str_LogID = result4.getString("count(CONCAT('',TIMEDIFF(Off_Block_Time, cobt)))");		
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> No. of Flights for which difference between offblock and COBT is > 5mins :- "+str_LogID +"</b>");
				System.out.println(" No. of Flights for which difference between offblock and COBT is > 5mins :- "+str_LogID);
			}
			con.close();
			//SendMailBodyToZestIOT.sendHtmlEmail("COBT_For_GMR_HYD_SG_User", "hi");
	 }
		
  
  public static void cobtGreaterThan5Min() throws Exception{
		
		ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_17);
		while (result.next()){			
			String str_LogID = result.getString("count(CONCAT('',TIMEDIFF(Off_Block_Time, cobt)))");		
			HtmlReportUtil.stepInfo("<b style=\"color:red;\"> No. of Flights for which difference between offblock and COBT is > 5mins :- "+str_LogID +"</b>");
			System.out.println(" No. of Flights for which difference between offblock and COBT is > 5mins :- "+str_LogID);
		}
		DBWrapper.dbConnectionClose();
	}

  
	public static void cobtIsNotNull() throws Exception{
			
			ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_14);
			while (result.next()){
				
				String str_LogID = result.getString("Logid");	
				String str_FlightNumber_Arrival = result.getString("FlightNumber_Arrival");
				String str_cobt = result.getString("cobt");
				cobtIsNotNull.add(str_cobt);
				String str_Off_Block_Time = result.getString("Off_Block_Time");				
				//HtmlReportUtil.stepInfo("");
				//System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | ");
				System.out.println(" ");
			}
			System.out.println("Total Flights with cobt is null:- "+cobtIsNotNull.size());
			HtmlReportUtil.stepInfo("<b style=\"color:red;\">  No. of Flights for which cobt is null::- "+cobtIsNotNull.size() +"</b>");
			DBWrapper.dbConnectionClose();
		}

  
	public static void ofBlockTimeIsNotNull() throws Exception{
			
			ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_15);
			while (result.next()){
				
				String str_LogID = result.getString("Logid");	
				String str_FlightNumber_Arrival = result.getString("FlightNumber_Arrival");
				String str_cobt = result.getString("cobt");
				
				String str_Off_Block_Time = result.getString("Off_Block_Time");
				ofBlockIsNotNull.add(str_Off_Block_Time);
				//HtmlReportUtil.stepInfo("");
				//System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | ");
				System.out.println(" ");
			}
			System.out.println("Total Flights with off block time is null:- "+ofBlockIsNotNull.size());
			HtmlReportUtil.stepInfo("<b style=\"color:red;\"> No of Flights for which off block time is null:- "+ofBlockIsNotNull.size() +"</b>");
			DBWrapper.dbConnectionClose();
		}
	
	
	public static void totalFlights() throws Exception{
		System.out.println("| "+ "str_LogID" +" | "+ "str_FlightNumber_Arrival" +" | "+ "str_cobt" +" | "+ "str_Off_Block_Time" +" | "+ "str_cobtTimeDiff" +" | ");
		//HtmlReportUtil.stepInfo("<b style=\"color:blue;\"> | "+ "LogID" +" | "+ "FlightNumber_Arrival" +" | "+ "cobt" +" | "+ "str_Off_Block_Time" +" | "+ "str_cobtTimeDiff" +" | </b>");
		
			ResultSet result = DBWrapper.Connect(SQL_Queries.strQuery_16);
			while (result.next()){
				String str_LogID = result.getString("Logid");	
				String str_FlightNumber_Arrival = result.getString("FlightNumber_Arrival");
				String str_cobt = result.getString("cobt");
				
				String str_Off_Block_Time = result.getString("Off_Block_Time");
				String str_cobtTimeDiff = result.getString("CONCAT('',TIMEDIFF(Off_Block_Time, cobt))");
				int str_cobtSecDiff1 = result.getInt("TIME_TO_SEC(TIMEDIFF(Off_Block_Time, cobt))");
				int str_cobtSecDiff2 = result.getInt("TIME_TO_SEC(TIMEDIFF(cobt, Off_Block_Time))");
				cobtTimeDiff.add(str_Off_Block_Time);
				System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | "+ str_cobtTimeDiff +" | ");
				
				//HtmlReportUtil.stepInfo("");
				if(str_cobtSecDiff1>300 || str_cobtSecDiff2>300  ) {
				//System.out.println("| "+ str_LogID +" | "+ str_FlightNumber_Arrival +" | "+ str_cobt +" | "+ str_Off_Block_Time +" | "+ str_cobtTimeDiff +" | ");
				HtmlReportUtil.stepInfo("<b style=\"color:red;\"> FlightNumber:-</b><b style=\"color:red;\"> "+str_FlightNumber_Arrival
						+" </b>|| <b style=\"color:red;\">Cobt:-</b><b style=\"color:red;\"> "+ str_cobt 
						+"<b> || <b style=\"color:red;\">Off Block:- </b> <b style=\"color:red;\">"+ str_Off_Block_Time 
						+" </b>|| <b style=\"color:red;\">Cobt Diff:-</b><b style=\"color:red;\"> "+ str_cobtTimeDiff +"  </b>");
				}else {
					/*HtmlReportUtil.stepInfo("<b style=\"color:green;\"> FlightNumber:-</b><b style=\"color:green;\"> "+str_FlightNumber_Arrival
							+" </b>|| <b style=\"color:green;\">Cobt:-</b><b style=\"color:green;\"> "+ str_cobt 
							+"<b> || <b style=\"color:green;\">Off Block:- </b> <b style=\"color:green;\">"+ str_Off_Block_Time 
							+" </b>|| <b style=\"color:green;\">Cobt Diff:-</b><b style=\"color:green;\"> "+ str_cobtTimeDiff +"  </b>");	
				*/
				}
				
			}
			System.out.println("Total Flights  - "+cobtTimeDiff.size());
			HtmlReportUtil.stepInfo("<b style=\"color:green;\"> Total number of Flights: - "+cobtTimeDiff.size() +"</b>");
			
			DBWrapper.dbConnectionClose();
			
			
		}
	

}
