package utils;



import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
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



public class TLSEmail {
	
	
	public static void sendEmailNewUser(String toEmail, String username, String password) throws MessagingException{
		
		String template = "Has sido registado en ElBanco. Tus credenciales son:\n\n" +
				"\tnombre de usuario : %s\n" +
				"\tcontraseña        : %s\n\n" +
				"Atentamente,\n" +
				"ElBanco";
		String body = String.format(template, username, password);
		
		String subject = "Credenciales";
		
		sendEmail(toEmail, subject, body);
	

	}
	
	
	private static void sendEmail(String toEmail, String subject, String body) throws MessagingException{
		
		final String fromEmail = "iiielbancoiii@gmail.com"; //requires valid gmail id
		final String password = "elbanco123"; // correct password for gmail id
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getInstance(props, auth);
		
		
		  MimeMessage msg = new MimeMessage(session);
		  //set message headers
		  msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		  msg.addHeader("format", "flowed");
		  msg.addHeader("Content-Transfer-Encoding", "8bit");
		
		  try {
			msg.setFrom(new InternetAddress("iiielbancoiii@gmail.com", "ElBanco"));
			msg.setReplyTo(InternetAddress.parse("iiielbancoiii@gmail.com", false));
			
			  msg.setSubject(subject, "UTF-8");
			
			  msg.setText(body, "UTF-8");
			
			  msg.setSentDate(new Date());
			
			  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			  System.out.println("Message is ready");
			  Transport.send(msg);  
			
			  System.out.println("EMail Sent Successfully!!");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  
	    
		
	}

}
