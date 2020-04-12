package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.MailRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.RestCallVO;

public class ExtraMethodsDAO {
	
	public String sendmail(MailRequestVO mailrequestvo) {
		
		String result = "Failure";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ExtraConstants.fromEmail, ExtraConstants.fromEmailPassword);// change accordingly
			}});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ExtraConstants.fromEmail));// change accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailrequestvo.getToEmail()));
			message.setSubject("User Credentials For PAYGTL_LORA_BLE Application" + mailrequestvo.getUserID());
			message.setText("Please Save the Credentials for further communication \n"
					+ " Your UserID is : " + mailrequestvo.getUserID() + "\n Your Password is : " + mailrequestvo.getUserPassword());

			Transport.send(message);
			result = "Success";

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public String restcall(RestCallVO restcallvo) throws IOException {
		
//		restcallvo;
		
		URL url = new URL(ExtraConstants.TataGatewayURL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        
        urlConnection.setRequestProperty("Content-Type", ExtraConstants.ContentType); // or any other mime
		// type
        urlConnection.setRequestProperty("X-M2M-Origin", ExtraConstants.XM2MOrigin);// other any other

        urlConnection.setRequestProperty("X-M2M-RI", ExtraConstants.XM2MRI);

        urlConnection.setRequestProperty("Accept", ExtraConstants.Accept); // or any other mime type

        urlConnection.setRequestProperty("Cache-Control", ExtraConstants.CacheControl);// other any other

		final String authHeaderValue = "Basic "
				+ Base64.getEncoder().encodeToString((ExtraConstants.TataUserName + ':' + ExtraConstants.TataPassword).getBytes());

		urlConnection.setRequestProperty("Authorization", authHeaderValue);
		
		return null;
	}

}
