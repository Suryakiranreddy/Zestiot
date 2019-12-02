package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import java.util.Date;

/**
 * @author EN
 */
@SuppressWarnings("unused")
public class SendMailBodyToZestIOT extends Utility {
 
    public static void sendHtmlEmail(String subject, String message) throws AddressException,
            MessagingException {
    	 // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String userName = "automationteam.enhops@gmail.com";
        String password = "Enhops@123";
 
        // outgoing message information
        String toAddress = "surya.tiyagura@gmail.com";
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
 
    }
 
    /**
     * Test the send html e-mail method
     *
     */
    public static void main(String[] args) {
        // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "automationteam.enhops@gmail.com";
        String password = "Enhops@123";
 
        // outgoing message information
        String mailTo = "stiyyagura@enhops.com";
        String subject = "Hello my friend";
 
        // message contains HTML markups
        String message = "<i>Greetings!</i><br>";
        message += "<b>Wish you a nice day!</b><br>";
        message += "<font color=red>Duke</font>";
 
        SendMailBodyToZestIOT mailer = new SendMailBodyToZestIOT();
 
        try {
            mailer.sendHtmlEmail(subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }}
