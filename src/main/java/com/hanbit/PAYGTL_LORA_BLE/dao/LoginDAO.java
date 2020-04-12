/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.LoginVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.MailRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserManagementRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.UserDetails;
import com.hanbit.PAYGTL_LORA_BLE.utils.Encryptor;

/**
 * @author K VimaL Kumar
 * 
 */
public class LoginDAO {

	public static int CommunityID;

	public Connection con = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
		return connection;
	}

	public ResponseVO validateUser(LoginVO loginvo) throws ClassNotFoundException, BusinessException, SQLException {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ResponseVO responsevo = new ResponseVO();
		UserDetails userDetails = new UserDetails();

		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"SELECT ID, UserID, UserName, UserPassword, RoleID, ActiveStatus, CommunityID, BlockID, CustomerID FROM user where UserID = ? AND UserPassword = ?");
			pstmt.setString(1, loginvo.getUserID());
			pstmt.setString(2, loginvo.getPassword());
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				
				if (loginvo.getUserID().equals(resultSet.getString("UserID"))) {

					if (loginvo.getPassword().equals(resultSet.getString("UserPassword"))) {

							userDetails.setroleID(resultSet.getInt("RoleID"));
							userDetails.setBlockID(resultSet.getInt("BlockID"));
							userDetails.setCustomerID(resultSet.getInt("CustomerID"));
							userDetails.setuserName(resultSet.getString("UserName"));
							userDetails.setCommunity(resultSet.getInt("CommunityID"));
							userDetails.setID(resultSet.getInt("ID"));
							responsevo.setUserDetails(userDetails);
							responsevo.setResult("Success");
							responsevo.setMessage("Successfully Logged In");

						}
					else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Invalid Password");
					}

					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Invalid UserID");
					}
				} else {
					responsevo.setResult("Failure");
					responsevo.setMessage("Invalid Credentials");
				}

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			pstmt.close();
			resultSet.close();
			con.close();

		}
		
		return responsevo;
	}

	public ResponseVO forgotpassword(String userid) throws SQLException, MessagingException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			ExtraMethodsDAO maildao = new ExtraMethodsDAO();
			MailRequestVO mailrequestvo = new MailRequestVO();
			pstmt = con.prepareStatement("SELECT CustomerID, UserPassword, CommunityID, BlockID FROM user WHERE UserID = ?");
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

					if(rs.getInt("CommunityID")!=0 && rs.getInt("BlockID")!=0) {
						
						if(rs.getInt("CustomerID")!=0) {
							
							pstmt1 = con.prepareStatement("SELECT Email FROM customer WHERE CustomerID=?");
							pstmt1.setString(1, rs.getString("CustomerID"));
						
					}else {
						pstmt1 = con.prepareStatement("SELECT Email FROM block WHERE BlockID=?");
						pstmt1.setString(1, rs.getString("BlockID"));
						
					}
						rs1 = pstmt1.executeQuery();

						if (rs1.next()) {
							mailrequestvo.setToEmail(rs1.getString("Email"));
						}
					} else {
						mailrequestvo.setToEmail(ExtraConstants.fromEmail);
					}
						
					mailrequestvo.setUserID(userid);
					mailrequestvo.setUserPassword(Encryptor.decrypt(ExtraConstants.key1, ExtraConstants.key2,rs.getString("UserPassword")));
					responsevo.setResult(maildao.sendmail(mailrequestvo));
			
			} else {
				responsevo.setMessage("UserID is not Registered");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// pstmt.close();
			// ps.close();
			// rs.close();
			con.close();
		}

		return responsevo;

	}
	
	public String changepassword(UserManagementRequestVO usermanagementvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();

			pstmt = con
					.prepareStatement("UPDATE user SET UserPassword = ?, ModifiedDate = NOW() where UserID = ?");
			pstmt.setString(1, usermanagementvo.getNewPassword());
			pstmt.setString(2, usermanagementvo.getUserID().trim());

			if (pstmt.executeUpdate() > 0) {
				result = "Success";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}

		return result;
	}

	public boolean checkuserid(String userid) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT UserID FROM user where UserID = ?");
			pstmt.setString(1, userid.trim());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}
	
	public boolean checkoldpassword(UserManagementRequestVO usermanagementvo) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT UserPassword  FROM user where UserID = ?");
			pstmt.setString(1, usermanagementvo.getUserID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				result = usermanagementvo.getOldPassword().contentEquals(rs.getString("UserPassword"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;

	}
	
}
