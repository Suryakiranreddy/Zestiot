package enh.db.cases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWrapper {
	
	public static Connection con;

	public static ResultSet Connect(String dB_Queru) throws SQLException {

		con = DriverManager.getConnection(
				"jdbc:mysql://avileapuat.ckfsniqh1gly.us-west-2.rds.amazonaws.com:3306/AviLeap", "AviLeap_Read",
				"AviLeap_Read");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(dB_Queru);

		return rs;
	}
	
	public static void dbConnectionClose() throws SQLException {
		con.close();
	}


}
