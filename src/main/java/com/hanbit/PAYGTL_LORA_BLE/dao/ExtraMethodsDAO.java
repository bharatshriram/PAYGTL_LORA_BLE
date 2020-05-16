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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.MailRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.RestCallVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.SMSRequestVO;
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
			message.setSubject(mailrequestvo.getSubject());
			message.setText(mailrequestvo.getMessage());

			Transport.send(message);
			result = "Success";

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ResponseEntity<String> sendsms(SMSRequestVO smsRequestVO) {
		// TODO Auto-generated method stub
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.postForEntity(ExtraConstants.SMSAPI+smsRequestVO.getToMobileNumber()+ExtraConstants.SenderID+smsRequestVO.getMessage(), HttpMethod.POST, String.class);
		
		return response;
		
	}
	
	public ResponseEntity<TataResponseVO> restcallget(RestCallVO restcallvo) throws IOException {
		
	
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		final String authHeaderValue = "Basic "	+ Base64.getEncoder().encodeToString((ExtraConstants.TataUserName + ':' + ExtraConstants.TataPassword).getBytes());
		
		headers.set("Authorization", authHeaderValue);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<TataResponseVO> response = restTemplate.exchange(ExtraConstants.TataGatewayURL+restcallvo.getMeterID().toLowerCase()+restcallvo.getUrlExtension()+restcallvo.getTataTransactionID(), HttpMethod.GET, entity, TataResponseVO.class);
		
		return response;
	}

	public String restcallpost(RestCallVO restcallvo) throws IOException {
	
	URL url = new URL(ExtraConstants.TataGatewayURL+restcallvo.getMeterID().toLowerCase()+"/payloads/dl"+ExtraConstants.QueryParams);
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    
    urlConnection.setRequestProperty("Content-Type", ExtraConstants.ContentType); 
    
	final String authHeaderValue = "Basic "	+ Base64.getEncoder().encodeToString((ExtraConstants.TataUserName + ':' + ExtraConstants.TataPassword).getBytes());

	urlConnection.setRequestProperty("Authorization", authHeaderValue);
	
		// Send post request
		urlConnection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
		wr.writeBytes(restcallvo.getDataFrame());
		wr.flush();
		wr.close();
	
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
		SMSRequestVO smsRequestVO = new SMSRequestVO();
		MailRequestVO mailRequestVO = new MailRequestVO();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT t.MeterID, t.TataReferenceNumber, cmd.MobileNumber, cmd.Email FROM topup AS t LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = t.CRNNumber WHERE t.Status BETWEEN 0 AND 1 AND t.Source = 'web'");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				RestCallVO restcallvo  = new RestCallVO();
				restcallvo.setUrlExtension("/payloads/dl/");
				restcallvo.setMeterID(rs.getString("MeterID").toLowerCase());
				restcallvo.setTataTransactionID(rs.getString("TataReferenceNumber"));
				
				ResponseEntity<TataResponseVO> response = restcallget(restcallvo);
				
				PreparedStatement pstmt1 = con.prepareStatement("UPDATE topup SET Status = ?, AcknowledgeDate= NOW() WHERE TataReferenceNumber = ?");

				pstmt1.setInt(1, response.getBody().getTransmissionStatus());
				pstmt1.setLong(2, response.getBody().getId());
				if(pstmt1.executeUpdate() > 0){
					
					smsRequestVO.setToMobileNumber(rs.getString("MobileNumber"));
					smsRequestVO.setMessage(response.getBody().getTransmissionStatus() == 2 ? "Thank You for Recharging your Meter with MIU ID: "+ rs.getString("MeterID")+". Your request has been processed successfully." : "Your Recharge request has failed to reach the Meter (MIU ID: "+ rs.getString("MeterID")+"). Kindly retry after sometime. Deducted Amount will be refunded in 5-7 working days. We regret the inconvenience caused.");
					
//					sendsms(smsRequestVO);
					
					mailRequestVO.setToEmail(rs.getString("Email"));
					mailRequestVO.setSubject("Recharge Status!!!");
					mailRequestVO.setMessage(response.getBody().getTransmissionStatus() == 2 ? "Thank You for Recharging your Meter with MIU ID: "+ rs.getString("MeterID")+". Your request has been processed successfully." : "Your Recharge request has failed to reach the Meter (MIU ID: "+ rs.getString("MeterID")+"). Kindly retry after sometime. Deducted Amount will be refunded in 5-7 working days. We regret the inconvenience caused.");
					
					sendmail(mailRequestVO);
					
				}

			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
	}
	
//	@Scheduled(cron="0 0/30 * * * ?")
	@Scheduled(cron="0 0/2 * * * ?") 
	public void commandstatusupdatecall() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT MeterID, TataReferenceNumber FROM command WHERE Status BETWEEN 0 AND 1");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				RestCallVO restcallvo  = new RestCallVO();
				restcallvo.setUrlExtension("/payloads/dl/");
				restcallvo.setMeterID(rs.getString("MeterID").toLowerCase());
				restcallvo.setTataTransactionID(rs.getString("TataReferenceNumber"));
				
				ResponseEntity<TataResponseVO> response = restcallget(restcallvo);
				
				PreparedStatement pstmt1 = con.prepareStatement("UPDATE command SET Status = ?, ModifiedDate= NOW() WHERE TataReferenceNumber = ?");

				pstmt1.setInt(1, response.getBody().getTransmissionStatus());
				pstmt1.setLong(2, response.getBody().getId());
				pstmt1.executeUpdate();

			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
	}
	
//	@Scheduled(cron="0 0/30 * * * ?")
	@Scheduled(cron="0 0/5 * * * ?") 
	public void vacationstatusupdatecall() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT MeterID, TataReferenceNumber FROM vacation WHERE Status BETWEEN 0 AND 1 AND Source = 'web'");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				RestCallVO restcallvo  = new RestCallVO();
				restcallvo.setUrlExtension("/payloads/dl/");
				restcallvo.setMeterID(rs.getString("MeterID").toLowerCase());
				restcallvo.setTataTransactionID(rs.getString("TataReferenceNumber"));
				
				ResponseEntity<TataResponseVO> response = restcallget(restcallvo);
				
				PreparedStatement pstmt1 = con.prepareStatement("UPDATE vacation SET Status = ?, ModifiedDate= NOW() WHERE TataReferenceNumber = ?");

				pstmt1.setInt(1, response.getBody().getTransmissionStatus());
				pstmt1.setLong(2, response.getBody().getId());
				pstmt1.executeUpdate();

			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
	}
	
//	@Scheduled(cron="0 0/30 * * * ?")
	@Scheduled(cron="0 0 7 ? * TUE,FRI") 
	public void communicationfailurealert() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		MailRequestVO mailRequestVO = null;
		SMSRequestVO smsRequestVO = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT cmd.MeterID, b.Email, b.MobileNumber, cmd.HouseNumber, cmd.CRNNumber FROM customermeterdetails AS cmd LEFT JOIN block AS b ON cmd.BlockID = b.BlockID");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				pstmt1 = con.prepareStatement("SELECT ((SELECT (TIMESTAMPDIFF (MINUTE, (SELECT IotTimeStamp FROM displaybalancelog WHERE MeterID = ?), NOW()))) - (SELECT NoAMRInterval FROM alertsettings)) AS diff");
				pstmt1.setString(1, rs.getString("MeterID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt("diff") > 0) {
						
						mailRequestVO = new MailRequestVO();
						smsRequestVO = new SMSRequestVO();
						
						mailRequestVO.setSubject("No Communication from MIU ID: "+rs.getString("MeterID"));
						mailRequestVO.setToEmail(rs.getString("Email"));
						mailRequestVO.setMessage("Dear Admin, \n \n There is no communication from MIU ID: "+rs.getString("MeterID")+" installed at House Number: " + rs.getString("HouseNumber") + "with CRN Number: " + rs.getString("CRNNumber") + ". Kindly look into the issue and resolve it as soon as possible.");
						
						smsRequestVO.setMessage("There is no communication from MIU ID: "+rs.getString("MeterID")+" installed at House Number: " + rs.getString("HouseNumber") + "with CRN Number: " + rs.getString("CRNNumber") + ".");
						smsRequestVO.setToMobileNumber(rs.getString("MobileNumber"));
						
						sendmail(mailRequestVO);
						sendsms(smsRequestVO);
						
					}
				}

			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
	}

}
