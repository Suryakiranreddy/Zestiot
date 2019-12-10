package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import enh.db.cases.COBT_For_DIALCelebi_User;
import enh.db.cases.COBT_For_GMR_HYD_AISATS_User;
import enh.db.cases.COBT_For_GMR_HYD_SG_User;
import enh.db.cases.SQL_Queries;

public class SendMailBodyToZestIOT extends Utility {
	public static String mailBody;
 
    public static void sendHtmlEmail() throws Exception {
    	Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");  
		String strDate = formatter.format(date);
    	
    	 mailBody="<html>"
    			+ "<p style=\"color:#008ae6;\">Hi All, <br>Please find attached current sprint <b><i>'"+strDate+" Automation Test Results'</i> </b>triggred by Jenkins.<br><br> "
				+COBT_For_DIALCelebi_User.email_COBT_For_DIALCelebi_User5.toString()+"<br><br>"
				+COBT_For_GMR_HYD_AISATS_User.email_COBT_For_DIALCelebi_User5.toString()+"<br><br>"
				+COBT_For_GMR_HYD_SG_User.email_COBT_For_DIALCelebi_User5.toString()+"<br><br>"
    			+" <br><br><br> Thanks & Regards,<br>Automation Team</p>"
    			+ "<html>";
    	
    	
    	
    	
    }
 
}
