/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;

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
		PreparedStatement pstmt1 = null;
		ResultSet resultSet1 = null;
		ResponseVO responsevo = new ResponseVO();
		UserDetails userDetails = new UserDetails();

		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"SELECT u.ID, u.UserID, u.UserName, u.UserPassword, u.RoleID, cmd.MeterID, u.CommunityID, c.CommunityName, u.BlockID, u.CustomerID, u.CRNNumber, b.BlockName, b.Email AS bemail, b.MobileNumber AS bmobile, cmd.MobileNumber AS cmobile, cmd.Email AS cemail FROM USER AS u LEFT JOIN community AS c ON c.CommunityID = u.CommunityID LEFT JOIN block AS b ON b.BlockID = u.BlockID LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = u.CRNNumber WHERE u.UserID = ? AND u.UserPassword = ?");
			pstmt.setString(1, loginvo.getUserID());
			pstmt.setString(2, loginvo.getPassword());
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {

				if (loginvo.getUserID().equals(resultSet.getString("UserID"))) {

					if (loginvo.getPassword().equals(resultSet.getString("UserPassword"))) {

						if (loginvo.getSource().equalsIgnoreCase("web")) {
							
							if(resultSet.getInt("RoleID") == 6) {
								
								throw new BusinessException("USER NOT AUTHORIZED TO LOGIN");
							}

							userDetails.setRoleID(resultSet.getInt("RoleID"));
							userDetails.setBlockID(resultSet.getInt("BlockID"));
							userDetails.setEmail((userDetails.getRoleID() == 2 || userDetails.getRoleID() == 5) ? resultSet.getString("bemail") : (userDetails.getRoleID() == 3) ? resultSet.getString("cemail") : "");
							userDetails.setMobileNumber((userDetails.getRoleID() == 2 || userDetails.getRoleID() == 5) ? resultSet.getString("bmobile") : (userDetails.getRoleID() == 3) ? resultSet.getString("cmobile") : "");
							userDetails.setCustomerID(resultSet.getInt("CustomerID"));
							userDetails.setCRNNumber(resultSet.getString("CRNNumber"));
							userDetails.setuserName(resultSet.getString("UserName"));
							userDetails.setCommunity(resultSet.getInt("CommunityID"));
							userDetails.setCommunityName(resultSet.getString("CommunityName"));
							userDetails.setBlockName(resultSet.getString("BlockName"));
							userDetails.setID(resultSet.getInt("ID"));

							responsevo.setUserDetails(userDetails);
							responsevo.setResult("Success");
							responsevo.setMessage("Successfully Logged In");

						} else {

							if (resultSet.getInt("RoleID") == 3 && loginvo.getSource().equalsIgnoreCase("mobile")) {
								
								userDetails.setRoleID(resultSet.getInt("RoleID"));
								userDetails.setBlockID(resultSet.getInt("BlockID"));
								userDetails.setEmail((userDetails.getRoleID() == 2 || userDetails.getRoleID() == 5)
										? resultSet.getString("bemail")
										: (userDetails.getRoleID() == 3) ? resultSet.getString("cemail") : "");
								userDetails
										.setMobileNumber((userDetails.getRoleID() == 2 || userDetails.getRoleID() == 5)
												? resultSet.getString("bmobile")
												: (userDetails.getRoleID() == 3) ? resultSet.getString("cmobile") : "");
								userDetails.setCustomerID(resultSet.getInt("CustomerID"));
								userDetails.setCRNNumber(resultSet.getString("CRNNumber"));
								userDetails.setuserName(resultSet.getString("UserName"));
								userDetails.setCommunity(resultSet.getInt("CommunityID"));
								userDetails.setCommunityName(resultSet.getString("CommunityName"));
								userDetails.setBlockName(resultSet.getString("BlockName"));
								userDetails.setMeterID(resultSet.getString("MeterID"));
								userDetails.setID(resultSet.getInt("ID"));
								if (userDetails.getCRNNumber() != null) {
									pstmt1 = con.prepareStatement(
											"SELECT TransactionID, CommandType, DataFrame from command WHERE CRNNumber = ? and Status = 0");
									pstmt1.setString(1, userDetails.getCRNNumber());
									resultSet1 = pstmt1.executeQuery();
									if (resultSet1.next()) {
										userDetails.setPendingCommandType(resultSet1.getInt("CommandType"));
										userDetails.setPendingTransactionID(resultSet1.getInt("TransactionID"));
										userDetails.setDataFrame(resultSet1.getString("DataFrame"));
									} else {
										userDetails.setPendingCommandType(-1);
										userDetails.setPendingTransactionID(-1);
									}
								}

								responsevo.setUserDetails(userDetails);
								responsevo.setResult("Success");
								responsevo.setMessage("Successfully Logged In");
								
							} else if (resultSet.getInt("RoleID") == 6 && loginvo.getSource().equalsIgnoreCase("mobile")) {
								
								responsevo.setResult("Success");
								responsevo.setMessage("Successfully Logged In");
							
							} else {
								throw new BusinessException("USER NOT AUTHORIZED TO LOGIN");
								
							}

						}

					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Incorrect Password");
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
			pstmt = con.prepareStatement(
					"SELECT CustomerID, CRNNumber, UserPassword, CommunityID, BlockID FROM user WHERE UserID = ?");
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (rs.getInt("CommunityID") != 0 && rs.getInt("BlockID") != 0) {

					if (rs.getInt("CustomerID") != 0) {

						pstmt1 = con.prepareStatement("SELECT Email FROM customer WHERE CustomerID=?");
						pstmt1.setString(1, rs.getString("CustomerID"));

					} else {
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
				mailrequestvo.setUserPassword(
						Encryptor.decrypt(ExtraConstants.key1, ExtraConstants.key2, rs.getString("UserPassword")));
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

	public ResponseVO changepassword(UserManagementRequestVO usermanagementvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement("UPDATE user SET UserPassword = ?, ModifiedDate = NOW() where UserID = ?");
			pstmt.setString(1, usermanagementvo.getNewPassword());
			pstmt.setString(2, usermanagementvo.getUserID().trim());

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Password Updated Successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("Password Updation Failed");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
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

			pstmt = con.prepareStatement("SELECT UserPassword FROM user where UserID = ?");
			pstmt.setString(1, usermanagementvo.getUserID());
			rs = pstmt.executeQuery();
			if (rs.next()) {

				result = usermanagementvo.getOldPassword().toLowerCase().equalsIgnoreCase(rs.getString("UserPassword").toLowerCase());
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
