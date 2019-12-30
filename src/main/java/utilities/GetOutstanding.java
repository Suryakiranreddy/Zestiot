package utilities;

public class GetOutstanding {/*
	StringBuilder email;
	public void getOutstanding() throws MessagingException {
	    try {
	        String outS = "SELECT period_to, type, amt, status FROM tblinstall "
	                        + "WHERE status like ?";

	        PreparedStatement update = toDB.prepareStatement(outS);

	        email = new StringBuilder();

	        email.append("<html><head><style type='text/css'>table .out {border-width:1px, "
	                    + "border-color: black}</style></head>"
	                    + "<body>"
	                    + "<table class'out'><span style=border-color: black, border-width: 1px>");

	        update.setString(1, "Outstanding");
	        ResultSet results = update.executeQuery();

	        while (results.next()) {
	            System.out.println("in results...");
	            email.append("<tr>");
	            email.append("<td>");
	            long period = results.getLong("period_to");
	            email.append(DateConvert.fromEpoch(period));
	            email.append("</td>");

	            email.append("<td>");
	            email.append(results.getString("type"));
	            email.append("</td>");

	            email.append("<td>");
	            email.append(results.getString("amt"));
	            email.append("</td>");

	            email.append("<td>");
	            email.append(results.getString("status"));
	            email.append("</td>");

	            email.append("<tr>");
	        }

	        email.append("</table></body></html>");
	        sm.populateMailMessage(email.toString());
	        sm.sendMail();
	    } catch (SQLException sql) {
	        sql.printStackTrace();
	    }                 
	}
*/}
