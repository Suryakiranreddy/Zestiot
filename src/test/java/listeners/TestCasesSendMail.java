package listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import enh.db.cases.AV_2268_COBT_For_DIALCelebi_User;
import enh.db.cases.AV_2268_COBT_For_GMR_HYD_AISATS_User;
import enh.db.cases.AV_2268_COBT_For_GMR_HYD_SG_User;
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_AISATS_Hyd;
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_DIAL_Delhi;
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_Delhi_BSSPL_User;
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_Delhi_CELEBI_User;
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_Hyd;
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_SG_Hyd;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_AISATS_Hyd;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_DIAL_Delhi;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_Delhi_BSSPL_User;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_Delhi_CELEBI_User;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_Hyd;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_SG_Hyd;
import enh.db.cases.AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_DIAL_Delhi_Validation;
import enh.db.cases.AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_HYD_Validation;
import utilities.GlobalUtil;
import utilities.Utility;










/**
 * @author EN
 */
@SuppressWarnings("unused")
public class TestCasesSendMail extends Utility {
	private static String mailPropertiesFile = System.getProperty("user.dir")
			+ "/src/main/resources/ConfigFiles/mail.properties";
	private static Properties PROP = loadPropertyFile(mailPropertiesFile);
	
	// PROP.load(new FileInputStream(mailPropertiesFile));
	public static final String USERNAME = PROP.getProperty("userName");
	public static final String PASSWORD = PROP.getProperty("passWord");
	public static final String EMAILTO1 = PROP.getProperty("emailTo");
	public static final String EMAILTO = GlobalUtil.getCommonSettings().getEmailIds();
	public static final String EMAILTOCC = PROP.getProperty("emailToCC");
	public static final Boolean STARTTLS = true;
	public static final String HOST = PROP.getProperty("host");
	public static final String PORT = PROP.getProperty("port");
	public static final String EMAILAUTH = PROP.getProperty("mail_authentication");
	public static final String SOCKETFACTORYCLASS = PROP.getProperty("socketFactoryClass");
	public static final String FALLBACK = PROP.getProperty("fallback");
	public static final String PATH = null;
	public static final String MODULENAME = null;
	public static final int INDEXOFCOMMA = 0;
	public static final String USERFULLNAME = null;
	public static final String EMAIL_REGEX = "[a-z0-9\\_\\-\\.]+@[a-z0-9\\_\\-\\.]+\\.[a-z]+";
	public static final String REPORT_PATH = "/ExecutionReports/ExecutionReports";
	public static final String DIR_PATH = "user.dir";
	public static final String BLANK_VARIABLE = "";
	   public static StringBuilder testCase_Summary_Report = new StringBuilder();	
	    public static StringBuilder testCase_consolidated_Summary_Report = new StringBuilder();
	    public static Calendar cal;
		public static DateFormat dateFormat;
	// private static final
	private TestCasesSendMail() {
	}

	/**
	 * @throws IOException
	 * @throws MessagingException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test
	public static void sendDSREmailToClient() throws IOException, MessagingException {
		String subject1 = "Automation Test Cases Status Report ";
		//String subject2 = SQL_Queries.todayDayDateTime();
		Properties PROPS = System.getProperties();
		PROPS.put("mail.smtp.host", HOST);
		PROPS.put("mail.smtp.user", USERNAME);
		PROPS.put("mail.smtp.password", PASSWORD);
		PROPS.put("mail.smtp.auth", EMAILAUTH);

		if (!"".equals(PORT)) {
			PROPS.put("mail.smtp.port", PORT);
			PROPS.put("mail.smtp.socketFactory.port", PORT);
		}
		if (!"".equals(STARTTLS))
			PROPS.put("mail.smtp.starttls.enable", STARTTLS);

		if (!"".equals(SOCKETFACTORYCLASS))
			PROPS.put("mail.smtp.socketFactory.class", SOCKETFACTORYCLASS);

		if (!"".equals(FALLBACK))
			PROPS.put("mail.smtp.socketFactory.fallback", FALLBACK);

		Session session = Session.getDefaultInstance(PROPS);
		session.setDebug(false);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(USERNAME, PROP.getProperty("userFullName")));
		Date date = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");  
		 String strDate = formatter.format(date);
		msg.setSubject(strDate +" - "+subject1);

		cal = Calendar.getInstance();
		  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  // System.out.println("Today's date is "+dateFormat.format(cal.getTime()));
	   String todayDate= dateFormat.format(cal.getTime());

		if (!"".equals(EMAILTO)) {
			if (EMAILTO.contains(",")) {
				String[] multipleEmailTo = EMAILTO.split(",");
				for (int j = 0; j < multipleEmailTo.length; j++) {
					if (j == 0)
						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(multipleEmailTo[j]));
					else
						msg.addRecipient(Message.RecipientType.CC, new InternetAddress(multipleEmailTo[j]));
				}
			} else {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(EMAILTO));
			}
		} else if (EMAILTOCC.equals(BLANK_VARIABLE) || EMAILTOCC == null) {
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(EMAILTO));
		}

		System.out.println("Sending Automated Execution Report To -  : " + EMAILTO);
		/*
		 * if (!"".equals(EMAILTOCC)) {
		 * 
		 * msg.addRecipient(Message.RecipientType.CC, new InternetAddress(EMAILTOCC)); }
		 */

		BodyPart messageBodyPart = new MimeBodyPart();
		/*messageBodyPart.setText("Hi, \nPlease find attached current sprint Automation Test Results triggred by Jenkins.  "
				+ " \n \n \nThanks & Regards,\n Automation Team");#00b8e6*/
		String Mail_Body="<html>"
				+ "<p style=\"color:#008ae6;\">Hi All, <br>Please find below  <b><i>'"+strDate+"'</b></i> executed <b><i> 'Automation test cases status Report'</b></i>  triggred by Jenkins."
				+"<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>"
		+"<h3 align=\"center\" style=\"color:#008ae6;\"> Daily Status Report for Automation ("+todayDate+")</h3>"
		
		+"<table style=\"width:100%\" id=\"t01\"><tr>"
						+ "<th style=\"width:10%\"><b> S.No.</b></th>"
						+ "<th style=\"width:50%\"><b>Test case name</b></th>"
						+ "<th style=\"width:25%\"><b>Executed For</b></th>"
						+ "<th style=\"width:15%\"><b> Execution Type</b></th>"
						+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
						+ "<td>1</td> "
						+ "<td>AV 2268 Validate Accuracy of COBT </td>"
						+ " <td>  DIAL Celebi </td> "
						+ "<td> DB </td> "
	 					+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>2</td> "
				+ "<td>  AV 2268 Validate Accuracy of COBT </td>"
				+ " <td>  GMR-HYD-AISATS </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>3</td> "
				+ "<td>  AV 2268 Validate Accuracy of COBT </td>"
				+ " <td>  GMR-HYD-SG </td> "
				+ "<td>DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>4</td> "
				+ "<td>  AV 2307 Validate LANDING ONBLOCK OFFBLOCK AIRBORNE timestamps of Arrival and Departure aircrafts Any Data source </td>"
				+ " <td>  DIAL-Delhi </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>5</td> "
				+ "<td>  AV 2307 Validate LANDING ONBLOCK OFFBLOCK AIRBORNE timestamps of Arrival and Departure aircrafts Any Data source </td>"
				+ " <td>  GMR-Hyd </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>6</td> "
				+ "<td> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </td>"
				+ " <td> GMR-Hyd </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>7</td> "
				+ "<td> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </td>"
				+ " <td> DIAL-Delhi </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>8</td> "
				+ "<td>  AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </td>"
				+ " <td>  BSSPL-Delhi </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>9</td> "
				+ "<td>AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </td>"
				+ " <td>  AISATS-Hyd </td> "
				+ "<td>DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>10</td> "
				+ "<td> AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </td>"
				+ " <td> SG-Hyd </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>11</td> "
				+ "<td>  AV 2293 Identify coverage of Flight Sensor and Validate timestamps of Arrival Aircrafts </td>"
				+ " <td> CELEBI-Delhi </td> "
				+ "<td>DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>12</td> "
				+ "<td> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </td>"
				+ " <td>GMR-Hyd</td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>13</td> "
				+ "<td> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </td>"
				+ " <td> DIAL-Delhi </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>14</td> "
				+ "<td> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </td>"
				+ " <td> BSSPL-Delhi </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>15</td> "
				+ "<td>  AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </b></td>"
				+ " <td>  AISATS-Hyd </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>16</td> "
				+ "<td>  AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts </td>"
				+ " <td>  SG-Hyd </td> "
				+ "<td> DB </td> "
				+ " </tr>"
		+" <tr style=\"color:#008ae6;\"> "
				+ "<td>17</td> "
				+ "<td> AV 2294 Identify coverage of Flight Sensor and Validate timestamps of Departure Aircrafts</td>"
				+ " <td> CELEBI-Delhi </td> "
				+ "<td>DB </td> "
				+ " </tr>"
		+"</table>"
				
+" <p style=\"color:#008ae6;\"><br><br><br> Thanks & Regards,<br>Automation Team</p>"
+ "<html>";
		
	   
    	
		messageBodyPart.setContent(Mail_Body, "text/html");
		//messageBodyPart.setContent("Hi", "text/html");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		if (new File(System.getProperty(DIR_PATH) + REPORT_PATH).exists()) {
			Utility.delDirectory(new File(System.getProperty(DIR_PATH) + REPORT_PATH));
		}
		if (GlobalUtil.getCommonSettings().getHtmlReport().contains("Y")) {
			System.out.println("Copying HTML report");
			copyDirectoryData("HtmlReport", "HtmlReport");
		}
		if (GlobalUtil.getCommonSettings().getXlsReport().contains("Y")) {
			copyDirectoryData("ExcelReport", "ExcelReport");
		}
		if (GlobalUtil.getCommonSettings().getTestLogs().contains("Y")) {
			copyDirectoryData("Logs", "Logs");
		}

		//Utility.createZipFile();

		messageBodyPart = new MimeBodyPart();
		String path = System.getProperty(DIR_PATH) + "/ExecutionReports/HtmlReport/TestReport.html";
		DataSource source = new FileDataSource(path);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName("TestExecutionReport.html");
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);

		Transport transport = session.getTransport("smtp");
		transport.connect(HOST, USERNAME, PASSWORD);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		Utility.delDirectory(new File(System.getProperty(DIR_PATH) + "/ExecutionReports/ExecutionReports"));
	}

	/**
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public static void copyDirectoryData(String sourceDir, String targetDir) throws IOException {
		File srcDir = new File(System.getProperty(DIR_PATH) + "/ExecutionReports/" + sourceDir);
		File destDir = new File(System.getProperty(DIR_PATH) + "/ExecutionReports/ExecutionReports/" + targetDir);
		FileUtils.copyDirectory(srcDir, destDir);
	}
}
