package enh.web.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.KeywordUtil;
import utilities.TestDataProvider;


public class ZestIOT_TestCasesMailReport {

public static StringBuilder testCase_Summary_Report = new StringBuilder();	
	public static Calendar cal;
	public static DateFormat dateFormat;
	
	public static void zestIOT_TestCasesMailReport() {
	Date date = new Date();
	 SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");  
	 String strDate = formatter.format(date);
	
	
	cal = Calendar.getInstance();
	   dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  // System.out.println("Today's date is "+dateFormat.format(cal.getTime()));
   String todayDate= dateFormat.format(cal.getTime());
	
	testCase_Summary_Report.append("<html>"
			+ "<p style=\"color:#008ae6;\">Hi All, <br>Please find below executed "+strDate+" Automation test cases status Report triggred by Jenkins.");	
	testCase_Summary_Report.append("<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th,td {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>");
	testCase_Summary_Report.append("<h4 align=\"center\" style=\"color:#008ae6;\"> Daily Status Report for Automation ("+todayDate+")</h4>");
	
	testCase_Summary_Report.append("<table style=\"width:100%\" id=\"t01\"><tr>"
					+ "<th style=\"width:10%\"><b> S.No.</b></th>"
					+ "<th style=\"width:50%\"><b>Test case name</b></th>"
					+ "<th style=\"width:50%\"><b>Executed For</b></th>"
					+ "<th style=\"width:15%\"><b> Execution Type</b></th>"
					+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
					+ "<td><b>1</b></td> "
					+ "<td> <b> AV 2268 Validate Accuracy of COBT </b></td>"
					+ " <td> <b> DIAL Celebi </b></td> "
					+ "<td><b> DB </b></td> "
 					+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>2</b></td> "
			+ "<td> <b> AV 2268 Validate Accuracy of COBT </b></td>"
			+ " <td> <b> GMR-HYD-AISATS </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>3</b></td> "
			+ "<td> <b> AV 2268 Validate Accuracy of COBT </b></td>"
			+ " <td> <b> GMR-HYD-SG </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>4</b></td> "
			+ "<td> <b> AV 2307 Validate LANDING ONBLOCK OFFBLOCK AIRBORNE timestamps of Arrival and Departure aircrafts Any Data source </b></td>"
			+ " <td> <b> DIAL-Delhi </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>5</b></td> "
			+ "<td> <b> AV 2307 Validate LANDING ONBLOCK OFFBLOCK AIRBORNE timestamps of Arrival and Departure aircrafts Any Data source </b></td>"
			+ " <td> <b> GMR-Hyd </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>6</b></td> "
			+ "<td> <b> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </b></td>"
			+ " <td> <b> GMR-Hyd </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>7</b></td> "
			+ "<td> <b> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </b></td>"
			+ " <td> <b> DIAL-Delhi </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>8</b></td> "
			+ "<td> <b> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </b></td>"
			+ " <td> <b> BSSPL-Delhi </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>9</b></td> "
			+ "<td> <b> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </b></td>"
			+ " <td> <b> AISATS-Hyd </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>10</b></td> "
			+ "<td> <b> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </b></td>"
			+ " <td> <b> SG-Hyd </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>11</b></td> "
			+ "<td> <b> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </b></td>"
			+ " <td> <b> CELEBI-Delhi </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>12</b></td> "
			+ "<td> <b> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </b></td>"
			+ " <td> <b> GMR-Hyd </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>13</b></td> "
			+ "<td> <b> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </b></td>"
			+ " <td> <b> DIAL-Delhi </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>14</b></td> "
			+ "<td> <b> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </b></td>"
			+ " <td> <b> BSSPL-Delhi </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>15</b></td> "
			+ "<td> <b> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </b></td>"
			+ " <td> <b> AISATS-Hyd </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>16</b></td> "
			+ "<td> <b> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </b></td>"
			+ " <td> <b> SG-Hyd </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append(" <tr> "
			+ "<td><b>17</b></td> "
			+ "<td> <b> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </b></td>"
			+ " <td> <b> CELEBI-Delhi </b></td> "
			+ "<td><b> DB </b></td> "
			+ " </tr>");
	testCase_Summary_Report.append("</table>");
			
	testCase_Summary_Report.append(" <br><br><br> Thanks & Regards,<br>Automation Team</p>"
			+ "<html>");
	
	}
}
