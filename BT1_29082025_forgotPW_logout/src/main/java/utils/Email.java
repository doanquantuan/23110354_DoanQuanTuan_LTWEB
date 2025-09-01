package utils;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.UserModel;

public class Email {

	public String getRandom() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
		
		
		
	}

	public static boolean sendEmail(UserModel user) {
		boolean test = false;
		String toEmail = user.getEmail();
		String fromEmail = "td735429@gmail.com";
		String password = "oaql agko zgta wyvi";
		
		try {
			Properties pr = configEmail(new Properties());
			Session session = Session.getInstance(pr, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});
			Message mess = new MimeMessage(session);
			
			mess.setHeader("Content-Type", "text/plain; charset=UTF-8");

			mess.setFrom(new InternetAddress(fromEmail));

			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			mess.setSubject("Confirm Code");
			
			mess.setText("Your code is: " + user.getCode());
			
			Transport.send(mess);
			
			test = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return test;
	}
		
	public boolean EmailSend(UserModel user) {
		boolean test = false;
		String toEmail = user.getEmail();
		String fromEmail = "td735429@gmail.com";
		String password = "oaql agko zgta wyvi";
		try {
			Properties pr = configEmail(new Properties());
			Session session = Session.getInstance(pr, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			});
			Message mess = new MimeMessage(session);
			mess.setHeader("Content-Type", "text/plain; charset=UTF-8");

			mess.setFrom(new InternetAddress(fromEmail));

			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			mess.setSubject("Yours  Password");

			mess.setText("Password: " + user.getPassWord());
			
			Transport.send(mess);
			
			test = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}

	private static Properties configEmail(Properties pr) {
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");
		pr.put("mail.smtp.socketFactory.port", "587");
		pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return pr;
	}
}
