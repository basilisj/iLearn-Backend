package com.example.model;

import java.util.Properties;

import javax.mail.Session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email {
	
	// email attributes
	private String sendTo;
	private String sendFrom = "ta0898210@gmail.com";
	private String password = "Password1256";
	private String host = "smtp.gmail.com";
	private Properties properties;
	private Session session;
	
	// email constructors
	public Email(String sendTo, String host, Properties properties, Session session) {
		super();
		this.sendTo = sendTo;
		this.host = host;
		this.properties = properties;
		this.session = session;
	}
	public Email(String sendTo) {
		super();
		this.sendTo = sendTo;
		this.properties = System.getProperties();
		this.properties.put("mail.smtp.auth", true);
		this.properties.put("mail.smtp.starttls.enable", "true");
		this.properties.put("mail.smtp.host", host);
		this.properties.put("mail.smtp.user", sendFrom);
		this.properties.put("mail.smtp.password", password);
		this.properties.put("mail.smtp.port", "465");
		this.properties.put("mail.smtp.ssl.trust", "*");

		this.session = Session.getDefaultInstance(properties);
	}
	
}