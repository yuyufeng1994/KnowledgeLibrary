package com.lib.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.lib.enums.Const;

/**
 * 
 * @author Liou CZ
 */
public class SendEmail {

	public static String HOST = null;
	public static String PROTOCOL = "smtp";
	public static int PORT = 0;
	public static String FROM = null;// 发件人的email
	public static String PWD = null;// 发件人密码
	

	static {
		Properties prop = new Properties();
		InputStream in = Const.class.getResourceAsStream("/jdbc.properties");
		try {
			prop.load(in);
			HOST = prop.getProperty("mail_host").trim();
			PORT = Integer.valueOf(prop.getProperty("mail_port"));
			FROM = prop.getProperty("mail_user").trim();
			PWD = prop.getProperty("mail_pwd").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		send("13574472507@163.com", "test");
	}

	/**
	 * 获取Session
	 * 
	 * @return
	 */
	private static Session getSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);// 设置服务器地址
		props.put("mail.store.protocol", PROTOCOL);// 设置协议
		props.put("mail.smtp.port", PORT);// 设置端口
		props.put("mail.smtp.auth", true);

		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}

		};
		Session session = Session.getDefaultInstance(props, authenticator);

		return session;
	}

	public static void send(String toEmail, String content) {
		Session session = getSession();
		try {
			// System.out.println("--send--"+content);
			// Instantiate a message
			Message msg = new MimeMessage(session);

			// Set message attributes
			msg.setFrom(new InternetAddress(FROM));
			InternetAddress[] address = { new InternetAddress(toEmail) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("SOKLIB知识库管理系统账号激活邮件");
			msg.setSentDate(new Date());
			msg.setContent(content, "text/html;charset=utf-8");

			// Send the message
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
