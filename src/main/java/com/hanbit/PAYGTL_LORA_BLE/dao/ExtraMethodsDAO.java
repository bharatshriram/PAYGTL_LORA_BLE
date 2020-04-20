package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.MailRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.RestCallVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TataResponseVO;
/**
 * @author K VimaL Kumar
 * 
 */
@EnableScheduling
public class ExtraMethodsDAO {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
		return connection;
	}
	
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
		
		URL url = new URL(ExtraConstants.TataGatewayURL+restcallvo.getMeterID()+restcallvo.getUrlExtension());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        
        if(restcallvo.getUrlExtension().equalsIgnoreCase("/DownlinkPayloadStatus/latest")) {
        	urlConnection.setRequestMethod("GET");
        }
        
        urlConnection.setRequestProperty("Content-Type", ExtraConstants.ContentType); // or any other mime
		// type
        urlConnection.setRequestProperty("X-M2M-Origin", ExtraConstants.XM2MOrigin);// other any other

        urlConnection.setRequestProperty("X-M2M-RI", ExtraConstants.XM2MRI);

        urlConnection.setRequestProperty("Accept", ExtraConstants.Accept); // or any other mime type

        urlConnection.setRequestProperty("Cache-Control", ExtraConstants.CacheControl);// other any other

		final String authHeaderValue = "Basic "
				+ Base64.getEncoder().encodeToString((ExtraConstants.TataUserName + ':' + ExtraConstants.TataPassword).getBytes());

		urlConnection.setRequestProperty("Authorization", authHeaderValue);
		
		if(!restcallvo.getUrlExtension().equalsIgnoreCase("/DownlinkPayloadStatus/latest")) {
			// Send post request
			urlConnection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
			wr.writeBytes(restcallvo.getData());
			wr.flush();
			wr.close();
        }
		
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		StringBuffer responses = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			responses.append(inputLine);
		}
		in.close();
		return responses.toString();
	}
	
//	@Scheduled(cron="0 0/30 * * * ?")
	@Scheduled(cron="0 0/5 * * * ?") 
	public void topupstatusupdatecall() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// modify query accordingly
			con = getConnection();
			pstmt = con.prepareStatement("SELECT DISTINCT MeterID FROM topup WHERE Status BETWEEN 0 AND 1 AND Source = 'web'");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				RestCallVO restcallvo  = new RestCallVO();
				restcallvo.setUrlExtension("/DownlinkPayloadStatus/latest");
				restcallvo.setMeterID(rs.getString("MeterID").toLowerCase());
				
				String response = restcall(restcallvo).toString();
				
				Gson gson = new Gson();
				
				TataResponseVO responsevo = new TataResponseVO();
				
				JSONObject jsonObj = new JSONObject(response);
				
				responsevo = gson.fromJson(jsonObj.getJSONObject("m2m:cin").getString("con"), TataResponseVO.class);
				
				// write if condition based on reponse from tata api
				
					//  0A0000000042290100000000000000000000000017.
				
				System.out.println("tatareferencenum:----- "+responsevo.getPayloads_dl().getTag());

					if (responsevo.getPayloads_dl().getTag().startsWith("T") == true) {
						
						PreparedStatement pstmt1 = con.prepareStatement("UPDATE topup SET Status = ?, AcknowledgeDate= NOW() WHERE MeterID = ? and TataReferenceNumber = ?");

						pstmt1.setInt(1, responsevo.getPayloads_dl().getTransmissionStatus());
						pstmt1.setString(2, responsevo.getPayloads_dl().getDeveui());
						pstmt1.setString(3, responsevo.getPayloads_dl().getTag());

						if (pstmt1.executeUpdate() > 0) {
							String result = "Success";
						}

					}
				
		} 
		} catch (Exception e) {
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
	}
	
//	@Scheduled(cron="0 0/30 * * * ?")
	@Scheduled(cron="0 0/5 * * * ?") 
	public void commandstatusupdatecall() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// modify query accordingly
			con = getConnection();
			pstmt = con.prepareStatement("SELECT DISTINCT MeterID FROM command WHERE Status BETWEEN 0 AND 1 AND Source = 'web'");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				RestCallVO restcallvo  = new RestCallVO();
				restcallvo.setUrlExtension("/DownlinkPayloadStatus/latest");
				restcallvo.setMeterID(rs.getString("MeterID").toLowerCase());
				
				String response = restcall(restcallvo);
				
				Gson gson = new Gson();
				
				TataResponseVO responsevo = new TataResponseVO();
				
				JSONObject jsonObj = new JSONObject(response);
				
				responsevo = gson.fromJson(jsonObj.getJSONObject("m2m:cin").getString("con"), TataResponseVO.class);
				
				// write if condition based on reponse from tata api
				
					//  0A0000000042290100000000000000000000000017.

					if (responsevo.getPayloads_dl().getTag().startsWith("C") == true) {

						PreparedStatement pstmt1 = con.prepareStatement("UPDATE command SET Status = ?, ModifiedDate= NOW() WHERE MeterID = ? and TataReferenceNumber = ?");

						pstmt1.setInt(1, responsevo.getPayloads_dl().getTransmissionStatus());
						pstmt1.setString(2, responsevo.getPayloads_dl().getDeveui());
						pstmt1.setString(3, responsevo.getPayloads_dl().getTag());

						if (pstmt1.executeUpdate() > 0) {
							String result = "Success";
						}

					}
				
		}
		} catch (Exception e) {
			
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		
	}
}
