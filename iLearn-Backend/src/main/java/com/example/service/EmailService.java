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

	public void sendEmail(User user) throws Exception {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("ta0898210@gmail.com");
		mail.setSubject("Password Reset Request from user " + user.getUsername());
		mail.setText("This user requires a password reset");

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
/*
	@Autowired
	@Qualifier("emailConfigBean")
	private Configuration emailConfig;

	@Override
	public void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException {

		Map model = new HashMap();
		model.put("name", mailModel.getName());
		model.put("location", "Sri Lanka");
		model.put("signature", "https://techmagister.info");
		model.put("content", mailModel.getContent());
		
		 * Add below line if you need to create a token to verification emails and
		 * uncomment line:32 in "email.ftl"
		 * model.put("token",UUID.randomUUID().toString());
		 

		mailModel.setModel(model);

		log.info("Sending Email to: " + mailModel.getTo());

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		mimeMessageHelper.addInline("logo.png", new ClassPathResource("classpath:/techmagisterLogo.png"));

		Template template = emailConfig.getTemplate("email.ftl");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getModel());

		mimeMessageHelper.setTo(mailModel.getTo());
		mimeMessageHelper.setText(html, true);
		mimeMessageHelper.setSubject(mailModel.getSubject());
		mimeMessageHelper.setFrom(mailModel.getFrom());

		emailSender.send(message);

	}*/
	/*
	 * public void sendmail() throws AddressException, MessagingException,
	 * IOException { Properties props = new Properties();
	 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.starttls.enable",
	 * "true"); props.put("mail.smtp.host", "smtp.gmail.com");
	 * props.put("mail.smtp.port", "587");
	 * 
	 * Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	 * protected PasswordAuthentication getPasswordAuthentication() { return new
	 * PasswordAuthentication("ta0898210@gmail.com", "Password1256"); } }); Message
	 * msg = new MimeMessage(session); msg.setFrom(new
	 * InternetAddress("ta0898210@gmail.com", false));
	 * 
	 * msg.setRecipients(Message.RecipientType.TO,
	 * InternetAddress.parse("ta0898210@gmail.com"));
	 * msg.setSubject("Tutorials point email");
	 * msg.setContent("Tutorials point email", "text/html"); msg.setSentDate(new
	 * Date());
	 * 
	 * MimeBodyPart messageBodyPart = new MimeBodyPart();
	 * messageBodyPart.setContent("Tutorials point email", "text/html");
	 * 
	 * Multipart multipart = new MimeMultipart();
	 * multipart.addBodyPart(messageBodyPart); msg.setContent(multipart);
	 * Transport.send(msg); }
	 */

}
