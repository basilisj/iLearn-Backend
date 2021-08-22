package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.mail.internet.MimeUtility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.model.Email;
import com.example.model.User;
import com.example.repository.DiscussionRepo;
import com.example.repository.RolesRepo;
import com.example.repository.UserRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


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
	private RolesRepo rDao;
	private DiscussionRepo dDao;
	
	
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
	
	//private JavaMailSender javaMailSender;
	
	/*
	@Bean
	public void sendEmail()throws Exception{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("ta0898210@gmail.com");
		mail.setSubject("testing mail");
		mail.setText("we heckin got here bois!!!!!");
		
		javaMailSender.send(mail);
	}*/
	
	/*
	public void sendMail() throws Exception{
		String recipient = request.getParameter("recipient");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		String resultMessage = "";
		
		try {
		       MimeUtility.sendEmail(host, port, user, pass, recipient, subject,
		               content);
		       resultMessage = "The e-mail was sent successfully";
		   } catch (Exception ex) {
		       ex.printStackTrace();
		       resultMessage = "There were an error: " + ex.getMessage();
		   } finally {
		       request.setAttribute("Message", resultMessage);
		       getServletContext().getRequestDispatcher("/Result.jsp").forward(
		               request, response);
		   }
	}
	*/
	
	private void sendmail() throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("tutorialspoint@gmail.com", "<your password>");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("ta0898210@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tutorialspoint@gmail.com"));
		   msg.setSubject("Tutorials point email");
		   msg.setContent("Tutorials point email", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Tutorials point email", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		   attachPart.attachFile("/var/tmp/image19.png");
		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}
	/*
    protected void doPost(HttpServletRequest request,
       HttpServletResponse response) throws ServletException, IOException {
   // reads form fields
  

   

   
}
}
*/
	
	/*
	public void sendResetEmail(String email) {
		User user = new User();
		// find a user in the database by email
		user = UserRepo.findByEmail(email);
		// throw an exception if no user was found
		if (user == null) {
			throw new EmailDoesNotExistException();
		}
		// create new temporary password
		String temporaryPassword = generatePassayPassword();
		// create the email to send to user
		Email emailToSend = new Email(email);
		try {
			MimeMessage message = new MimeMessage(emailToSend.getSession());
			// format the email content
			message.setFrom(new InternetAddress(emailToSend.getSendFrom()));
			message.setRecipient(RecipientType.TO, new InternetAddress(emailToSend.getSendTo()));
			message.setSubject("YAWP -- Password Reset");
			String messageBody = "<h6>You have requested a password reset.<h6> <br> Below is your username and new temporary password."
					+ "Once you've logged in, you will be asked to set your new password.<br>" + "<b>User</b>: "
					+ user.getUsername() + "<br>" + "<b>Pass</b>: " + temporaryPassword + "<br><br><br><br>"
					+ "If you have any issues or questions, please contact us:\n" + "<h4>YAWP Team<h4><br>"
					+ "<b>Phone</b>: 555-5555<br>" + "<b>Address</b>: 462 South 4th Street, Suite 1600<br>"
					+ "				   Louisville, KY 40202<br>";
			messageBody = "<font face=\"courier new\" size=\"10px\">" + messageBody + "</font></p>";
			message.setContent(messageBody, "text/html");
			// set the email delivery method
			Transport transport = emailToSend.getSession().getTransport("smtp");
			transport.connect(emailToSend.getHost(), emailToSend.getSendFrom(), emailToSend.getPassword());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			// throw an exception if an error occurred sending the mail
			System.out.println(e.getMessage());
			throw new EmailNotSentException();
		}
		// if this is successful we hash the temp password, set it to the user and wait for them to login
		String hashedTemp = hashPassword(temporaryPassword);
		StoredPassword sp = user.getPasswordHolder();
		sp.setHashedPassword(hashedTemp);
		// update Password Table
		passDao.save(sp);
		// update User Table
		userDao.save(user);
	}*/
	
	  

	   /* @Override
	    public void sendMail(Mail mail) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(mail.getRecipient(), mail.getRecipient());

	        msg.setSubject(mail.getSubject());
	        msg.setText(mail.getMessage());

	        javaMailSender.send(msg);
	    }*/

	   /* @Override
	    public void sendMailWithAttachments(Mail mail) throws MessagingException {
	        MimeMessage msg = javaMailSender.createMimeMessage();

	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	        helper.setTo("to_@email");

	        helper.setSubject("Testing from Spring Boot");

	        helper.setText("Find the attached image", true);

	        helper.addAttachment("hero.jpg", new ClassPathResource("hero.jpg"));

	        javaMailSender.send(msg);
	    }*/
}
