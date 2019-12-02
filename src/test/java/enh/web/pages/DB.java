package enh.web.pages;

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

public class DB {

	public static Connection con;

	public static ResultSet Connect(String dB_Queru) throws SQLException {

		con = DriverManager.getConnection(
				"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(dB_Queru);

		return rs;
	}

	public static java.util.List<String> getDBConnectionAndExecuteQuery_FlightNumberArrival() throws SQLException {
		
		con = DriverManager.getConnection(
				"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `DailyFlightSchedule_Merged` where (date(atd)='"
				+ DB.currentdate() + "' or date(sensor_atd)='" + DB.currentdate() + "') and\r\n" +

				"operationunit = 4 and flightnumber_arrival not like '%leader%' and (sta is not null and eta is not null) and flightnumber_arrival like '%sg%'");
		java.util.List<String> flights = new ArrayList<>();

		while (rs.next()) {
			String str_FlightNumberArrival = rs.getString("FlightNumber_Arrival");
			flights.add(str_FlightNumberArrival);
			System.out.println("Departed Flights from DB: " + str_FlightNumberArrival);
			HtmlReportUtil.stepInfo(
					"<b style=\"color:green;\">Departed Flights from DB: " + str_FlightNumberArrival + "</b>");

		}

		Collections.sort(flights);

		return flights;
	}

	/*Connection con = DriverManager.getConnection(
	"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
	"AviLeap_Read");

Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(
	"SELECT * FROM `DailyFlightSchedule_Merged` where (date(atd)='2019-09-19' or date(sensor_atd)='2019-09-19') and\r\n"
			+ "operationunit = 4 and flightnumber_arrival not like '%leader%' and (sta is not null and eta is not null) and flightnumber_arrival like '%sg%'");

java.util.List<String> flightsDB = new ArrayList<>();

while (rs.next()) {

String strDepartedFlights = rs.getString("FlightNumber_Arrival");
flightsDB.add(strDepartedFlights);
HtmlReportUtil
		.stepInfo("<b style=\"color:green;\">Departed Flights from DB: " + strDepartedFlights + "</b>");
System.out.println("Departed Flights from DB: " + strDepartedFlights);
}
con.close();
Collections.sort(flightsDB);
System.out.println(flightsDB.toString());*/
	public static void dbConnectionClose() throws SQLException {
		con.close();
	}

	public static String currentdate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static java.util.List<String> verify_that_On_Block_and_Off_Block_timestamps_are_between_Sensor_ATA_and_Sensor_ATD_timestamps()
			throws SQLException, ParseException {

		con = DriverManager.getConnection(
				"jdbc:mysql://avileap-test.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(
				"SELECT logid, flightnumber_arrival, Sensor_ATA, On_Block_Time, Off_Block_Time, Sensor_ATD FROM `DailyFlightSchedule_Merged`\r\n"
						+ "  where (date(atd)='2019-09-21' or date(sensor_atd)='2019-09-21') and \r\n"
						+ "operationunit = 4 and flightnumber_arrival not like '%leader%' and \r\n"
						+ "(sta is not null and eta is not null) and flightnumber_arrival like '%sg%'");
		java.util.List<String> logId = new ArrayList<>();

		while (rs.next()) {

			String str_LogID = rs.getString("logid");
			System.out.println(str_LogID);
			String flight_NumberArrival = rs.getString("flightnumber_arrival");
			System.out.println(flight_NumberArrival.trim());

			String flight_Sensor_ATA1 = rs.getString("Sensor_ATA");
			String flight_Sensor_ATA = flight_Sensor_ATA1.substring(0, 19);
			Date dateTime_Sensor_ATA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(flight_Sensor_ATA);
			System.out.println(dateTime_Sensor_ATA);

			String flight_On_Block_Timeib1 = rs.getString("On_Block_Time");
			String flight_On_Block_Time = flight_On_Block_Timeib1.substring(0, 19);
			Date dateTime_On_Block_Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(flight_On_Block_Time);
			System.out.println(dateTime_On_Block_Time);

			String flight_Off_Block_Time1 = rs.getString("Off_Block_Time");
			String flight_Off_Block_Time = flight_Off_Block_Time1.substring(0, 19);
			Date dateTime_Off_Block_Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(flight_Off_Block_Time);
			System.out.println(dateTime_Off_Block_Time);

			String flight_Sensor_ATD1 = rs.getString("Sensor_ATD");
			String flight_Sensor_ATD = flight_Sensor_ATD1.substring(0, 19);
			Date dateTime_flight_Sensor_ATD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(flight_Sensor_ATD);
			System.out.println(dateTime_flight_Sensor_ATD);

			boolean SensorATALessThanOnBlockTime = dateTime_Sensor_ATA.before(dateTime_On_Block_Time);
			System.out.println(SensorATALessThanOnBlockTime);

			boolean OnBlockLessThanOffBlock = dateTime_On_Block_Time.before(dateTime_Off_Block_Time);
			System.out.println(OnBlockLessThanOffBlock);

			boolean OffBlockLessThanSensorATD = dateTime_Off_Block_Time.before(dateTime_flight_Sensor_ATD);
			System.out.println(OffBlockLessThanSensorATD);
		}

		Collections.sort(logId);
		logId.size();

		for (int i = 0; i <= logId.size(); i++) {

		}

		return logId;
	}

	public static void main(String[] args) throws SQLException, ParseException {
		DB.verify_that_On_Block_and_Off_Block_timestamps_are_between_Sensor_ATA_and_Sensor_ATD_timestamps();
		DB.dbConnectionClose();

	}

}
