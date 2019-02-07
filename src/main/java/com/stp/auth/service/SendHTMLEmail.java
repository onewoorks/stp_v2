
package com.stp.auth.service;
//File Name SendHTMLEmail.java

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

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

public class SendHTMLEmail {

	public static void main(String[] args) {

		final String username = "flight@mpc.gov.my";
		final String password = "passwordmis";
		// Get system properties

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "domino01.mpc.gov.my");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("flight@mpc.gov.my", "Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("saufirasid@gmail.com", "user"));

			msg.setSubject("Testing Subject", "UTF-8");

			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent("<h1>Hello Bro</h1>", "text/html");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);
			Transport.send(msg);

			System.out.println("Done");

		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sendHtmlEmail(String recieverEmail, String Html) {

		final String username = "flight@mpc.gov.my";
		final String password = "passwordmis";
		// Get system properties

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "domino01.mpc.gov.my");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("flight@mpc.gov.my", "Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recieverEmail, "user"));

			msg.setSubject("Testing Subject", "UTF-8");

			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(Html, "text/html");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);
			Transport.send(msg);
			System.out.println("Done");

		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String convertHtmlString(String HTML) {

		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(HTML));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		return content;
	}
}