package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class EmailService {

	private String to;
	private String from;
	private String host;
	private Properties properties;
	private Session session;
	
	private UserRepo uDao;
	
	
	public void sendTestMail() {
	    
	    to = "test@ymail.com";
	    from = "web@gmail.com";
	    host = "localhost";
	    properties = System.getProperties();
	    properties.setProperty("mail.stmp.host", host);
	    session = Session.getDefaultInstance(properties);

	    try {
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.setSubject("This is a subject");
	        message.setText("This is actual message");
	        Transport.send(message);
	        System.out.println("Sent message successfully");
	    } catch (MessagingException e) {
	        System.out.println("Failed to send Email");
	        e.printStackTrace();
	    }
	}
	
	/*public void sendMail(int mail, int userId){
		//This should send emails to multiple people
		    User u = uDao.findByUserId(userId);
		    

		    to = u.getEmail();
		    from = "web@gmail.com";
		    host = "localhost";
		    properties = System.getProperties();
		    properties.setProperty("mail.stmp.host", host);
		    session = Session.getDefaultInstance(properties);

		    try {
		        MimeMessage message = new MimeMessage(session);
		        message.setFrom(new InternetAddress(from));
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		        message.setSubject("Thank you for your order");
		        String mess = "Hell\n";

		        message.setText(mess);
		        Transport.send(message);
		        System.out.println("Sent message successfully");
		    } catch (MessagingException e) {
		        System.out.println("Failed to send Email");
		        e.printStackTrace();
		    }

		}*/
	
	  

	    @Override
	    public void sendMail(Mail mail) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(mail.getRecipient(), mail.getRecipient());

	        msg.setSubject(mail.getSubject());
	        msg.setText(mail.getMessage());

	        javaMailSender.send(msg);
	    }

	    @Override
	    public void sendMailWithAttachments(Mail mail) throws MessagingException {
	        MimeMessage msg = javaMailSender.createMimeMessage();

	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	        helper.setTo("to_@email");

	        helper.setSubject("Testing from Spring Boot");

	        helper.setText("Find the attached image", true);

	        helper.addAttachment("hero.jpg", new ClassPathResource("hero.jpg"));

	        javaMailSender.send(msg);
	    }
}
