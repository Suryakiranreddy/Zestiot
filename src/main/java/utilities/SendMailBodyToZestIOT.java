package utilities;

import enh.db.cases.SQL_Queries;

public class SendMailBodyToZestIOT extends Utility {
	public static String mailBody;
 
    public static void sendHtmlEmail() throws Exception {
    	
    	
    	 mailBody="<html>"
    			+ "<p style=\"color:#008ae6;\">Hi All, <br>Please find attached current sprint <b><i>'"+SQL_Queries.todayDayDateTime()+" Automation Test Results'</i> </b>triggred by Jenkins. "
				
    			+" <br><br><br> Thanks & Regards,<br>Automation Team</p>"
    			+ "<html>";
    	
    	
    	
    	
    }
 
}
