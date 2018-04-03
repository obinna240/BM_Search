package com.pcg.utils;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author oonyimadu
 *
 */
@Component
public class Mailer implements MailerInterface
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
	
	/**
	 * 
	 */
	//public Mailer()
	//{
		
	//}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getStartTls() {
		return startTls;
	}
	public void setStartTls(String startTls) {
		this.startTls = startTls;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getMailUsername() {
		return mailUsername;
	}
	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public String getMailToTech() {
		return mailToTech;
	}
	public void setMailToTech(String mailToTech) {
		this.mailToTech = mailToTech;
	}
	public String getMailToProject() {
		return mailToProject;
	}
	public void setMailToProject(String mailToProject) {
		this.mailToProject = mailToProject;
	}
	public String getTechSubject() {
		return techSubject;
	}
	public void setTechSubject(String techSubject) {
		this.techSubject = techSubject;
	}
	public String getTechLogs() {
		return techLogs;
	}
	public void setTechLogs(String techLogs) {
		this.techLogs = techLogs;
	}
	public String getProjectLogs() {
		return projectLogs;
	}
	public void setProjectLogs(String projectLogs) {
		this.projectLogs = projectLogs;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	/**
	 * Run this before calling email method
	 */
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












/**
   public static void main(String[] args) {
      // Recipient's email ID needs to be mentioned.
     String to = "tjefferson@publicconsultinggroup.co.uk,oonyimadu@publicconsultinggroup.co.uk";//;oonyimadu@publicconsultinggroup.co.uk;tjefferson@publicconsultinggroup.co.uk";

      // Sender's email ID needs to be mentioned
      String from = "noreply@publicconsultinggroup.co.uk";

      final String username = "noreply@publicconsultinggroup.co.uk";//change accordingly
      final String password = "PCGN0R3ply!";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.office365.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
       
       
         message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Testing Subject");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("This is message body");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "logs/postCodeValidator.log";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");
  
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}
	*/