package com.example.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.example.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void send(String to, String subject, String body) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true); // true indicates
														// multipart message
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true); // true indicates html
		// continue using helper object for more functionalities like adding
		// attachments, etc.
		javaMailSender.send(message);

}

	public void sendEmail() throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("ta0898210@gmail.com");
		mail.setSubject("Password Reset Request from user ");
		mail.setText("This user requires a password reset");

		javaMailSender.send(mail);
	}
	
	public void sendUserLogin(User user)throws Exception{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("This is your iLearn login info, Please don't share it.");
		mail.setText("This is your username: " + user.getUsername() + "\n" +
				"This is your password: " + user.getPassword() + "\n");
		
		javaMailSender.send(mail);
	}

	public void sendMail(User user, String reciever, String subject, String message) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(user.getEmail());
		mail.setTo(reciever);
		mail.setSubject(subject);
		mail.setText(message);

		javaMailSender.send(mail);

	}

	 public void sendSimpleEmail(String toAddress, String subject, String message) {

		  SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		  simpleMailMessage.setTo(toAddress);
		  simpleMailMessage.setSubject(subject);
		  simpleMailMessage.setText(message);
		  javaMailSender.send(simpleMailMessage);
		 
	 }
	 
}
