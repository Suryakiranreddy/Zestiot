package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	
	public static String todayDateTime(){
		Date date = new Date();
		 SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");  
		 String strDate = formatter.format(date);
		 System.out.println(strDate);  
		return strDate;  
		  
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		todayDateTime();
	}

}
