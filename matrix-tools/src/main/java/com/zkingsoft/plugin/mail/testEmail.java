package com.zkingsoft.plugin.mail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class testEmail {
	/*public static void main(String args[]) {
		try {
			send_email();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	//发送邮件
	public static void send_email(String to,String subject,String content,String host,String port,String auth,String email,String password) throws IOException, AddressException, MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host); //设置邮箱的smtp
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", auth);
		//设置邮箱的POP3/IMAP/SMTP/Exchange服务 
		Authenticator authenticator = new MyAuthenticator(email, password);//账户和授权码
		javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(new InternetAddress(email));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mailMessage.setSubject(subject, "UTF-8");
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		html.setContent(content.trim(), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		Transport.send(mailMessage);
	}
}
