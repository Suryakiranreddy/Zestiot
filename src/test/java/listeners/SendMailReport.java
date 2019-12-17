package listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_DIAL_Delhi;
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_Delhi_BSSPL_User;
import enh.db.cases.AV_2293_Scheduled_And_Sensor_ATA_Hyd;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_DIAL_Delhi;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_Delhi_BSSPL_User;
import enh.db.cases.AV_2294_Scheduled_And_Sensor_ATD_Hyd;
import enh.db.cases.AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_DIAL_Delhi_Validation;
import enh.db.cases.AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_HYD_Validation;
import utilities.GlobalUtil;
import utilities.Utility;

public class SendMailReport extends Utility {
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

	
	/**
	 * @throws IOException
	 * @throws MessagingException
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	@Test
	public static void sendEmailToClient() throws IOException, MessagingException {
		String subject1 = PROP.getProperty("subject");
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
		String mailBody="<html>"
    			+ "<p style=\"color:#008ae6;\">Hi All, <br>Please find attached <b><i>'"+strDate+" Automation Test Results'</i> </b> report and also find below consolidated report for each Automation Test Case triggred by Jenkins."	
				+ AV_2268_COBT_For_DIALCelebi_User.email_COBT_For_DIALCelebi_User5.toString() + ""
				+ AV_2268_COBT_For_GMR_HYD_AISATS_User.email_COBT_For_DIALCelebi_User5.toString() + ""
				+ AV_2268_COBT_For_GMR_HYD_SG_User.email_COBT_For_DIALCelebi_User5.toString() + ""
				+ AV_2293_Scheduled_And_Sensor_ATA_Hyd.email_report_Scheduled_And_Sensor_ATA_For_Hyd1.toString() + ""
				+ AV_2294_Scheduled_And_Sensor_ATD_Hyd.email_report_Scheduled_And_Sensor_ATD_For_Hyd1.toString() + ""
				+ AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_HYD_Validation.email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_Hyd1.toString() + ""
				+ AV_2293_Scheduled_And_Sensor_ATA_DIAL_Delhi.email_report_Scheduled_And_Sensor_ATA_For_Delhi1.toString() + ""
				+ AV_2294_Scheduled_And_Sensor_ATD_DIAL_Delhi.email_report_Scheduled_And_Sensor_ATD_For_Delhi1.toString() + ""
				+ AV_2307_SensorATA_OnBlock_OffBlock_SensorATD_DIAL_Delhi_Validation.email_report_SensorATA_OnBlock_OffBlock_SensorATD_For_DIAL_Delhi1.toString() + ""
				+ AV_2293_Scheduled_And_Sensor_ATA_Delhi_BSSPL_User.email_report_Scheduled_And_Sensor_ATA_For_BSSPL_Delhi1.toString() + ""
				+ AV_2294_Scheduled_And_Sensor_ATD_Delhi_BSSPL_User.email_report_Scheduled_And_Sensor_ATD_For_BSSPL_Delhi1.toString() + ""
    		    +" <p style=\"color:#008ae6;\"><br><br><br> Thanks & Regards,<br>Automation Team</p>"
    			+ "<html>";
    	
		messageBodyPart.setContent(mailBody, "text/html");
		
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
