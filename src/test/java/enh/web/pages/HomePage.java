package enh.web.pages;

import org.openqa.selenium.By;

import utilities.ConfigReader;
import utilities.KeywordUtil;

public class HomePage extends KeywordUtil {

	// ---TestAdmin login--------
	private static By emaiAdress = By.id("login");
	private static By passWord = By.id("password");
	private static By loginButton = By.xpath("//span[contains(.,'Ready for Departure')]");
	
	private static By imgCircle = By.xpath("//img[@class='img-circle']");
	private static By logout = By.xpath("//span[contains(.,'Logout')]");
	

	/**********************************************************************************************************
	 * Method Name: Login
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         21/Sept/2019                   Rajesh			                             AD/-/Sept/2019                                     
	 ***********************************************************************************************************/
	
	public static void login(String email, String password, String hotelcode) throws Exception {
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========ZestIOT login function started=============</b>");
		launchToUrl(ConfigReader.getValue("BASE_URL"));
		String currentURL = getCurrentUrl();
		System.out.println(currentURL);
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">'URL launched: https://staygrid.com/ '</b>");
		inputText(emaiAdress, email);
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">'" + email + "'</b> Username entered");
		pause(1000);
		inputText(passWord, password);
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">'" + "**********" + "'</b> Password entered");
		pause(5000);
		click(loginButton);
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========ZestIOT login successfull=============</b>");
	}
	public static void prodlogin(String email, String password, String hotelcode) throws Exception {
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========ZestIOT login function started=============</b>");
		getDriver().get("https://avileap.com/login");
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">'URL launched: https://staygrid.com/ '</b>");
		inputText(emaiAdress, email);
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">'" + email + "'</b> Username entered");
		pause(1000);
		inputText(passWord, password);
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">'" + "**********" + "'</b> Password entered");
		pause(5000);
		click(loginButton);
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========ZestIOT login successfull=============</b>");
	}

	/**********************************************************************************************************
	 * Method Name: Logout
	 * Description : 
	 * Change History:
	 *             Date                     Created By          Change Description        Code Review and date
	 *             ****                     **********            ****************          *******************
	 *         21/Sept/2019                   Rajesh			                             AD/-/Sept/2019                                     
	 ***********************************************************************************************************/
	
	public static void logOut() throws Exception {
		
		
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========ZestIOT logout function started=============</b>");
		click(imgCircle);
		clickJS(logout);
		//HtmlReportUtil.stepInfo("<b style=\"color:green;\">=========ZestIOT logout successfull=============</b>");
	}



}