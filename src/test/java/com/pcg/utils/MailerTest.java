package com.pcg.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Properties;

import javax.mail.Session;

import org.junit.Test;
import org.mockito.Mock;



public class MailerTest {
	
	
	@Test
	public void shouldSendEmailsToSelectedRecipients()
	{
		
	}
}

/**

@Component
public class Mailer
{
	@Value("${smtp.auth}")
	private String auth;
	@Value("${smtp.starttls.enable}")
	private String startTls;
	@Value("${smtp.host}")
	private String smtpHost;
	@Value("${smtp.port}")
	private String smtpPort;
	@Value("${mail.from}")
	private String mailFrom;
	@Value("${mail.username}")
	private String mailUsername;
	@Value("${mail.password}")
	private String mailPassword;
	@Value("${mail.to.group.tech}")
	private String mailToTech;
	@Value("${mail.to.group.project}")
	private String mailToProject;
	@Value("${mail.tech.subject}")
	private String techSubject;
	@Value("${mail.tech.logs}")
	private String techLogs;
	@Value("${mail.project.logs}")
	private String projectLogs;
	private Session session;
	private Properties properties;
	

	public void emailTeams(String techMessageBody, String projectMessageBody)
	{
		this.properties = new Properties();
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.starttls.enable", startTls);
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.port", smtpPort);
		
		this.session = Session.getInstance(properties,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(mailUsername, mailPassword);
		            }
		         });
		 try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(mailFrom));

	         // Set To: header field of the header. (Mailing to TECH team)
	         message.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(mailToTech));

	         // Set Subject: header field
	         message.setSubject(techSubject);

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText(techMessageBody);
	         //messageBodyPart.setText("This is message body");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = techLogs;
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	       
	         message = new MimeMessage(session);

	      
	         message.setFrom(new InternetAddress(mailFrom));

	        
	         message.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(mailToProject));

	       
	         message.setSubject(techSubject);

	       
	         messageBodyPart = new MimeBodyPart();

	      
	         messageBodyPart.setText(projectMessageBody);
	        

	     
	         multipart = new MimeMultipart();

	     
	         multipart.addBodyPart(messageBodyPart);

	  
	         messageBodyPart = new MimeBodyPart();
	         filename = projectLogs;
	         source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	      
	         message.setContent(multipart);

	       
	         Transport.send(message);
	         
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	}

}
*/
