package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailsenderService {
	
	
	  @Autowired 
	  private JavaMailSender mailSender;
	  
	  
	  public void sendEmail(String to, String subject,String body) throws  MailException{
		  
		  SimpleMailMessage mailMessage = new SimpleMailMessage();
		  
		  mailMessage.setTo(to);
		  mailMessage.setSubject(subject);
		  mailMessage.setText(body);
		  mailSender.send(mailMessage);
		  
	  }
	 
	
}
