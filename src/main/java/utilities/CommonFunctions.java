package utilities;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.*;

public class CommonFunctions // extends DriverLaunch
{
	static WebDriver driver;
   // sendkeys command using we can pass a text
	public static void setText(WebElement we, String str)
	{
		we.sendKeys(str);
	}
	// click method using clicking the webelement
	public static void click(WebElement we)
	{
	we.click();
	}
	// mouse can perform the corresponding web element action 
	public static void mouseOver(WebElement we)
	{
		Actions actObj=new Actions(driver);
		actObj.moveToElement(we).build().perform();
	}
	 /*While reporting your script status, you need to pass time & date that when your test-script has 
	 finished. e.g SumitMay28201513_15_10 PM)	 */
	public static String getTimeStamp()
	{
		DateFormat DF=DateFormat.getDateTimeInstance();
		Date dte=new Date();
		String DateValue=DF.format(dte);
		DateValue=DateValue.replaceAll(":", "_");
		DateValue=DateValue.replaceAll(",", "");
		return DateValue;
	}
	//select the dropdown using "select by visible text", so pass VisibleText as 'Yellow' to funtion
	public static void selectText(WebElement we, String VisibleText){
	Select selObj=new Select(we);
	selObj.selectByVisibleText(VisibleText);
	}
	
	//select the dropdown using "select by index", so pass IndexValue
		public static void selectIdex(WebElement we, int IndexValue){
		Select selObj=new Select(we);
		selObj.selectByIndex(IndexValue);
		}
	//select the dropdown using "select by value", so pass Value
	public static void selectValue(WebElement we, String Value)
	{
	Select selObj=new Select(we);
	selObj.selectByValue(Value);
	}
	// gettext using we can store text in one string and return that string reference
	public static String getText(WebElement we)
	{
		String Text = we.getText();
		return Text;
	}
	/* This method simulates the browser’s forward button action. 
	It doesn’t allow any parameter and its return type is a void */
	public static void forward()
	{
		driver.navigate().forward();
	}
	//This method simulates the browser’s back button action
	public static void bckward()
	{
		driver.navigate().back();
	}
	// This method will clear the value of any text type element
	public static void clear(WebElement we)
	{
		we.clear();
	}
	// This method clicking the radio buttons
	public static void radio(WebElement we)
	{
		we.click();
	}
	// This method clicking the check box
	public static void checkSelect(WebElement we)
	{
		
		we.click();
	}
	// This method clicking the un check the checkbox
	public static void checkDeselect(WebElement we)
	{
		if(we.isSelected())
		{
		we.click();
		}
	}
	// this method using handles the multiple windowhandles
	public static WebDriver windowHandles(WebElement we)
	{
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		//String a=(String) driver.getTitle();
        System.out.println("Tabs list"+newTab);
        // driver navigate to new window( child window)
       return driver.switchTo().window(newTab.get(1));
      /* driver navigate to parenet window
    	 driver.switchTo().window(newTab.get(0)); */
	}
	
	// Handles the multiple windowhandles then driver navigate to parenet window
	public static WebDriver windowHandlesMainDriver(WebElement we, ArrayList<String> newTab)
	{
	    return driver.switchTo().window(newTab.get(0)); 
	}

	//verify that element (text box) fname is enabled or not
	public static Boolean isDisplayed(WebElement we)
	{
		return we.isDisplayed();
	}
	// This method using the alert accept
	public static void alertAccept()
	{
		driver.switchTo().alert().accept();
	}
	// This method using the alert dismiss or cancel
	public static void alertDismiss()
	{
		driver.switchTo().alert().dismiss();
	}
	/* This method locate the location of the element on the page. It doesn’t allow to pass any parameter,
	but its return type is the Point object.*/
	public static void getLocation(WebElement we)
	{
		Point point = we.getLocation();
		String strLine = System.getProperty("line.separator");
		System.out.println("X cordinate# " + point.x + strLine + "Y cordinate# " + point.y);
	}
	
	// wait for 3 seconds
	public static void mediumWait() 
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//wait for 2 seconds
	public static void lowWait()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// wait for 5 seconds
	public static void longWait()
	{
		System.out.println("inside long wait");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// This method using wait for some time, which as given the dynamically integer value
	public static void Wait(int a)
	{
		System.out.println("inside long wait");
		try {
			Thread.sleep(a);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// right clicking the corresponding driver element.
	public static void rightClick(WebElement we)
	{
		Actions actions = new Actions(driver);
		Action action = actions.contextClick(we).build();
		action.perform();
	}
	// double clicking 
	public static void doubleClick(WebElement we)
	{
		Actions action = new Actions(driver);
		action.doubleClick(we);
		action.perform();
	}
	// dynamically wait
	public static void implicitWait(int a)
	{
		driver.manage().timeouts().implicitlyWait(a, TimeUnit.SECONDS);
	}
	// drag and drop : source file drag and drop into target location
	public static void dragdrop(WebElement source, WebElement target)
	{
		(new Actions(driver)).dragAndDrop(source, target).perform();
	}
	
	
}
