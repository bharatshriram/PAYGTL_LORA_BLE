/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.BlockRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.CommunityRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.CustomerRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.MailRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TariffRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserManagementRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.BlockResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.CommunityResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.CustomerResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TariffResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.utils.Encryptor;

/**
 * @author K VimaL Kumar
 * 
 */
public class CommunitySetUpDAO {
	
	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	/* Community */

	public List<CommunityResponseVO> getCommunitydetails(int roleid, int id) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommunityResponseVO> communitydetailslist = null;
		try {
			con = getConnection();
			communitydetailslist = new LinkedList<CommunityResponseVO>();
			
			String query = "SELECT CommunityID, CommunityName, Email, MobileNumber, CreatedDate, Address FROM community <change> ";
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid==2 || roleid==3 || roleid==5) ? "WHERE CommunityID = "+id : "ORDER BY CommunityID ASC"));
			
			rs = pstmt.executeQuery();
			CommunityResponseVO communityvo = null;
			
			while (rs.next()) {
				communityvo = new CommunityResponseVO();
				communityvo.setCommunityName(rs.getString("CommunityName"));
				communityvo.setEmail(rs.getString("Email"));
				communityvo.setMobileNumber(rs.getString("MobileNumber"));
				communityvo.setAddress(rs.getString("Address"));
				communityvo.setCommunityID(rs.getInt("CommunityID"));
				communitydetailslist.add(communityvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return communitydetailslist;
	}

	public String addcommunity(CommunityRequestVO communityvo) throws SQLException{
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("INSERT INTO community (CommunityName, Email, MobileNumber, Address, CreatedDate) VALUES (?, ?, ?, ?, NOW())");
			pstmt.setString(1, communityvo.getCommunityName());
			pstmt.setString(2, communityvo.getEmail());
			pstmt.setString(3, communityvo.getmobileNumber());
			pstmt.setString(4, communityvo.getAddress());

			if (pstmt.executeUpdate() > 0) {
				result = "Success";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		return result;
	}
	
	public String editcommunity(CommunityRequestVO communityvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE community SET CommunityName = ?, Email = ?, MobileNumber = ?, Address = ?, ModifiedDate = NOW() WHERE CommunityID = ?");
			pstmt.setString(1, communityvo.getCommunityName());
			pstmt.setString(2, communityvo.getEmail());
			pstmt.setString(3, communityvo.getmobileNumber());
			pstmt.setString(4, communityvo.getAddress());
			pstmt.setInt(5, communityvo.getCommunityID());

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

	/* Block */

	public List<BlockResponseVO> getBlockdetails(int roleid, int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BlockResponseVO> block_list = null;

		try {
			con = getConnection();

			block_list = new LinkedList<BlockResponseVO>();
			
			String query = "SELECT block.BlockID, community.CommunityName, block.BlockName, block.Location, block.MobileNumber, block.Email, block.CreatedDate FROM block LEFT JOIN community ON community.CommunityID = Block.CommunityID <change>";
			
			String RoleName = (roleid==2 || roleid==3 || roleid==5) ? "WHERE block.BlockID = "+id+ " ORDER BY block.BlockID ASC" : " ORDER BY block.BlockID ASC" ;
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", RoleName));
			rs = pstmt.executeQuery();
			BlockResponseVO blockvo = null;

			while (rs.next()) {

				blockvo = new BlockResponseVO();
				blockvo.setCommunityName(rs.getString("CommunityName"));
				blockvo.setBlockName(rs.getString("BlockName"));
				blockvo.setMobile(rs.getString("MobileNumber"));
				blockvo.setEmail(rs.getString("Email"));
				blockvo.setLocation(rs.getString("Location"));
				blockvo.setBlockID(rs.getInt("BlockID"));

				block_list.add(blockvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
//			rs.close();
			con.close();
		}

		return block_list;
	}

	public String addblock(BlockRequestVO blockvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";
		

		try {
			con = getConnection();
			
			boolean flag;
			int i = 0;
			
			pstmt = con.prepareStatement("INSERT INTO block (BlockName, Location, MobileNumber, Email, CommunityID, CreatedByID, CreatedByRoleID, CreatedDate, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, 1, NOW(), NOW())");
			pstmt.setString(1, blockvo.getBlockName());
			pstmt.setString(2, blockvo.getLocation());
			pstmt.setString(3, blockvo.getMobileNumber());
			pstmt.setString(4, blockvo.getEmail());
			pstmt.setInt(5, blockvo.getCommunityID());
			pstmt.setInt(6, blockvo.getCreatedByID());

			if (pstmt.executeUpdate() > 0) {
				
				ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
				UserManagementRequestVO usermanagementvo = new UserManagementRequestVO();
				
				PreparedStatement pstmt1 = con.prepareStatement("SELECT MAX(block.BlockID) AS BlockID, community.CommunityName FROM block LEFT JOIN community ON community.CommunityID = block.CommunityID WHERE block.BlockName = ? AND block.CommunityID = ?");
				pstmt1.setString(1, blockvo.getBlockName());
				pstmt1.setInt(2, blockvo.getCommunityID());
				ResultSet rs = pstmt1.executeQuery();
				if(rs.next()) {
					usermanagementvo.setBlockID(blockvo.getBlockID());
					LoginDAO logindao = new LoginDAO();
					
					String userID = rs.getString("CommunityName")+blockvo.getBlockName();
					
					do {
						 flag = logindao.checkuserid(userID);
						 i++;
						 if(flag) {
							 userID = userID+i;	
							}
						 
					} while (flag == true);
					
					if(i<=1) {
						usermanagementvo.setUserID(rs.getString("CommunityName")+blockvo.getBlockName());	
					}else {
						usermanagementvo.setUserID(userID);
					}
					
				
				usermanagementvo.setUserName(blockvo.getBlockName());
				usermanagementvo.setUserPassword(Encryptor.encrypt(ExtraConstants.key1, ExtraConstants.key2, blockvo.getBlockName() + "@" + blockvo.getMobileNumber().substring(3, 7)));
				usermanagementvo.setRoleID(2);
				usermanagementvo.setCommunityID(blockvo.getCommunityID());
				usermanagementvo.setBlockID(rs.getInt("BlockID"));
				usermanagementvo.setLoggedInRoleID(1);
				usermanagementvo.setLoggedInUserID(blockvo.getLoggedInUserID());
				}
				
				if(managementsettingsdao.adduser(usermanagementvo).equalsIgnoreCase("Success")){
					
					ExtraMethodsDAO maildao = new ExtraMethodsDAO();
					MailRequestVO mailrequestvo = new MailRequestVO();
					
					mailrequestvo.setToEmail(blockvo.getEmail());
					mailrequestvo.setUserID(usermanagementvo.getUserID());
					mailrequestvo.setUserPassword(blockvo.getBlockName() + "@" + blockvo.getMobileNumber().substring(3, 7));
					
					result = maildao.sendmail(mailrequestvo);
					
					if(result.equalsIgnoreCase("Success")) {
						result = "Success";
					}else {
						result = "Block Registered Successfully but due to internal server Error Credentials have not been sent to your registered Mail ID. Please Contact Administrator";
					}
					
				} else {
					PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM block WHERE BlockID = (SELECT BlockID FROM block WHERE BlockName = ? AND CommunityID = ?)");
					pstmt2.setString(1, blockvo.getBlockName());
					pstmt2.setInt(2, blockvo.getCommunityID());
					
					if(pstmt2.executeUpdate() > 0) {
						result = "Failure";
					}
				}
				
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}

		return result;
	}

	public String editblock(BlockRequestVO blockvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();
			pstmt = con.prepareStatement("UPDATE block SET BlockName = ?, Location = ?, MobileNumber = ?, Email = ?, ModifiedDate = NOW() WHERE BlockID = ?");
			pstmt.setString(1, blockvo.getBlockName());
			pstmt.setString(2, blockvo.getLocation());
			pstmt.setString(3, blockvo.getMobileNumber());
			pstmt.setString(4, blockvo.getEmail());
			pstmt.setInt(5, blockvo.getBlockID());

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
	
	public String deleteblock(int blockID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("DELETE FROM block where BlockID=?");
		pstmt.setInt(1, blockID);
		
        if (pstmt.executeUpdate() > 0) {
        	result = "Success";
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}
	
	public boolean checkifhousesexist(int blockID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("select * from customermeterdetails where BlockID=?");
		pstmt.setInt(1, blockID);
		
		rs = pstmt.executeQuery();
        if (rs.next()) {
        	result = true;
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
		return result;
	}

	/* Customer */

	public List<CustomerResponseVO> getCustomerdetails(int roleid, int id) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<CustomerResponseVO> customer_list = null;
		CustomerResponseVO customervo = null;
		
		try {
			con = getConnection();
			customer_list = new LinkedList<CustomerResponseVO>();
		
			String query =
					"SELECT community.CommunityName, block.BlockName, customermeterdetails.CustomerID, customermeterdetails.HouseNumber, customermeterdetails.FirstName, customermeterdetails.LastName, \r\n" + 
							"customermeterdetails.Email, customermeterdetails.MobileNumber, customermeterdetails.MeterID, customermeterdetails.MeterSerialNumber, \r\n" + 
							"customermeterdetails.DefaultReading, tariff.TariffName, customermeterdetails.ModifiedDate, customermeterdetails.CreatedByID, customermeterdetails.CreatedByRoleID FROM customermeterdetails \r\n" + 
							"LEFT JOIN community ON community.CommunityID = customermeterdetails.communityID LEFT JOIN block ON block.BlockID = customermeterdetails.BlockID LEFT JOIN tariff ON tariff.TariffID = customermeterdetails.TariffID <change>";
							
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY customermeterdetails.CustomerID ASC" : (roleid == 2 || roleid == 5) ? "WHERE customermeterdetails.BlockID = "+id+ " ORDER BY customermeterdetails.CustomerID ASC" : (roleid == 3) ? "WHERE customermeterdetails.CustomerID = "+id:""));
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				customervo = new CustomerResponseVO();
				customervo.setCommunityName(rs.getString("CommunityName"));
				customervo.setBlockName(rs.getString("BlockName"));
				customervo.setFirstName(rs.getString("FirstName"));
				customervo.setLastName(rs.getString("LastName"));
				customervo.setEmail(rs.getString("Email"));
				customervo.setMobileNumber(rs.getString("MobileNumber"));
				customervo.setHouseNumber(rs.getString("HouseNumber"));
				customervo.setCustomerID(rs.getInt("CustomerID"));
				customervo.setMeterID(rs.getString("MeterID"));
				customervo.setDefaultReading(rs.getInt("DefaultReading"));
				customervo.setMeterSerialNumber(rs.getString("MeterSerialNumber"));
				customervo.setTariffName(rs.getString("TariffName"));
				
				pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					customervo.setCreatedByUserName(rs1.getString("UserName"));
					customervo.setCreatedByRoleDescription(rs1.getString("RoleDescription"));
				}
				
				customervo.setDate(rs.getString("ModifiedDate"));
				customer_list.add(customervo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return customer_list;
	}
	
	public String addcustomer(CustomerRequestVO customervo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String result = "Failure";
		

		try {
			con = getConnection();

			boolean flag;
			int i = 0;
			
			pstmt = con.prepareStatement(
					"INSERT INTO customermeterdetails (CommunityID, BlockID, HouseNumber, FirstName, LastName, Email, MobileNumber, MeterID, MeterSerialNumber, DefaultReading, TariffID, ActiveStatus, CRNNumber, CreatedByID, CreatedByRoleID, RegistrationDate, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?, ?, ?, Now(), Now() )");
			pstmt.setInt(1, customervo.getCommunityID());
			pstmt.setInt(2, customervo.getBlockID());
			pstmt.setString(3, customervo.getHouseNumber());
			pstmt.setString(4, customervo.getFirstName());
			pstmt.setString(5, customervo.getLastName());
			pstmt.setString(6, customervo.getEmail());
			pstmt.setString(7, customervo.getMobileNumber());
			pstmt.setString(8, customervo.getMeterID());
			pstmt.setString(9, customervo.getMeterSerialNumber());
			pstmt.setString(10, customervo.getDefaultReading());
			pstmt.setInt(11, customervo.getTariffID());
			pstmt.setString(12, customervo.getCRNNumber());
			pstmt.setInt(13, customervo.getCreatedByID());
			pstmt.setInt(14, customervo.getCreatedByRoleID());

			if (pstmt.executeUpdate() > 0) {
				
				ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
				UserManagementRequestVO usermanagementvo = new UserManagementRequestVO();
				
				pstmt1 = con.prepareStatement("SELECT CustomerID from customermeterdetails WHERE MeterID = ?");
				pstmt1.setString(1, customervo.getMeterID());
				ResultSet rs = pstmt1.executeQuery();
				if(rs.next()) {
					usermanagementvo.setBlockID(customervo.getBlockID());
					LoginDAO logindao = new LoginDAO();
					
					String userID = customervo.getFirstName().substring(0, 2)+ customervo.getFirstName().substring(customervo.getFirstName().length()-2) + customervo.getLastName().substring(0,2) +customervo.getLastName().substring(customervo.getLastName().length()-2)+customervo.getEmail().substring(0, customervo.getEmail().indexOf("@"));
					
					do {
						 flag = logindao.checkuserid(userID);
						 i++;
						 if(flag) {
							 userID = userID+i;	
							}
						 
					} while (flag == true);
					
					if(i<=1) {
						usermanagementvo.setUserID(customervo.getFirstName().substring(0, 2)+ customervo.getFirstName().substring(customervo.getFirstName().length()-2) + customervo.getLastName().substring(0,2) +customervo.getLastName().substring(customervo.getLastName().length()-2)+customervo.getEmail().substring(0, customervo.getEmail().indexOf("@")));	
					}else {
						usermanagementvo.setUserID(userID);
					}
					
				}
				usermanagementvo.setUserName(customervo.getFirstName() + " " + customervo.getLastName());
				usermanagementvo.setUserPassword(Encryptor.encrypt(ExtraConstants.key1, ExtraConstants.key2, customervo.getLastName()+"@"+ customervo.getMobileNumber().substring(3, 7)));
				usermanagementvo.setRoleID(3);
				usermanagementvo.setCommunityID(customervo.getCommunityID());
				usermanagementvo.setCustomerID(rs.getInt("CustomerID"));
				usermanagementvo.setBlockID(customervo.getBlockID());
				usermanagementvo.setLoggedInRoleID(customervo.getLoggedInRoleID());
				usermanagementvo.setLoggedInUserID(customervo.getLoggedInUserID());
				
				if(managementsettingsdao.adduser(usermanagementvo).equalsIgnoreCase("Success")){
					
					ExtraMethodsDAO maildao = new ExtraMethodsDAO();
					MailRequestVO mailrequestvo = new MailRequestVO();
					
					mailrequestvo.setToEmail(customervo.getEmail());
					mailrequestvo.setUserID(usermanagementvo.getUserID());
					mailrequestvo.setUserPassword(customervo.getLastName()+"@"+ customervo.getMobileNumber().substring(3, 7));
					
					result = maildao.sendmail(mailrequestvo);
					
					if(result.equalsIgnoreCase("Success")) {
						result = "Success";
					}else {
						result = "Customer Registered Successfully but due to internal server Error Credentials have not been sent to your registered Mail ID. Please Contact Administrator";
					}
					
					result = "Success";					
				} else {
					PreparedStatement pstmt2 = con.prepareStatement("DELETE FROM customermeterdetails WHERE CustomerID = (SELECT CustomerID FROM customermeterdetails WHERE MeterID = ?)");
					pstmt2.setString(1, customervo.getMeterID());
					
					if(pstmt2.executeUpdate() > 0) {
						result = "Failure";
					}
				}
				
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}

		return result;
	}
	
	public String editcustomer(CustomerRequestVO customervo) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();
			
			if(customervo.getLoggedInRoleID() == 3) {
				
				pstmt = con.prepareStatement("INSERT INTO (CustomerID, HouseNumber, FirstName, Email, MobileNumber, ToBeApprovedByID) updaterequestcustomermeterdetails VALUES (?, ?, ?, ?, ?, (SELECT CreatedByID FROM user WHERE CustomerID = ?))");
				pstmt.setInt(1, customervo.getCustomerID());
				pstmt.setString(2, customervo.getHouseNumber());
				pstmt.setString(3, customervo.getFirstName());
				pstmt.setString(4, customervo.getEmail());
				pstmt.setString(5, customervo.getMobileNumber());
				pstmt.setInt(6, customervo.getCustomerID());
				
				if (pstmt.executeUpdate() > 0) {
	            	result = "Request Submitted successfully and is pending for approval by Administrator";
	            }
				
			}else {
				pstmt = con.prepareStatement("UPDATE customermeterdetails SET HouseNumber=?, FirstName=?, Email=?, MobileNumber=?, ModifiedDate=NOW() WHERE CustomerID=?");
	            pstmt.setString(1, customervo.getHouseNumber());
	            pstmt.setString(2, customervo.getFirstName());
	            pstmt.setString(3, customervo.getEmail());
	            pstmt.setString(4, customervo.getMobileNumber());
	            pstmt.setInt(7, customervo.getCustomerID());

	            if (pstmt.executeUpdate() > 0) {
	            	result = "Success";
	            }
			}
			
            
		} catch (Exception e) {

	        e.printStackTrace();
	    } finally {
			pstmt.close();
			con.close();
	    }
		return result;
	}
	
	public String deletecustomer(CustomerRequestVO customervo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String result = "Failure";

		try {
			con = getConnection();

				pstmt = con.prepareStatement(
						"INSERT INTO customerdeletemeter (CustomerID, MeterID, Date) VALUES (?, (SELECT MeterID FROM Customermeterdetails where CustomerID = ?), NOW())");
				pstmt.setInt(1, customervo.getCustomerID());
				pstmt.setInt(2, customervo.getCustomerID());

				if (pstmt.executeUpdate() > 0) {
					
						pstmt1 = con.prepareStatement("DELETE FROM updaterequestcustomermeterdetails where CustomerID = ?");
						pstmt1.setInt(1, customervo.getCustomerID());
						
						if (pstmt1.executeUpdate() >= 0) {
							
							pstmt2 = con.prepareStatement("DELETE FROM user WHERE CustomerID = ?");
							pstmt2.setInt(1, customervo.getCustomerID());

							if (pstmt2.executeUpdate() > 0) {
								pstmt3 = con.prepareStatement("DELETE FROM customermeterdetails WHERE CustomerID=?");
								pstmt3.setInt(1, customervo.getCustomerID());
								if(pstmt3.executeUpdate() > 0) {
									result = "Success";	
								} else {
									PreparedStatement pstmt4 = con.prepareStatement("DELETE FROM customerdeletemeter where CustomerID = ?");
									pstmt4.setInt(1, customervo.getCustomerID());
									if(pstmt4.executeUpdate() > 0) {
										result = "Failure";
								}
								
							}

						}
							
						}
						
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			pstmt1.close();
			con.close();
		}

		return result;
	}
	
	public List<CustomerResponseVO> getCustomerUpdateRequestdetails(int blockid) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CustomerResponseVO> updaterequestlist = null;
		CustomerResponseVO customerresponsevo = null;
		try{
		con = getConnection();
		updaterequestlist = new LinkedList<CustomerResponseVO>();
		
		pstmt = con.prepareStatement("SELECT RequestID, CustomerID, HouseNumber, FirstName, Email, MobileNumber FROM updaterequestcustomermeterdetails WHERE BlockID = ?");
		pstmt.setInt(1, blockid);
		
		rs = pstmt.executeQuery();
        while (rs.next()) {
        	
        	customerresponsevo = new CustomerResponseVO();
        	customerresponsevo.setRequestID(rs.getInt("RequestID"));
        	customerresponsevo.setHouseNumber(rs.getString("HouseNumber"));
        	customerresponsevo.setFirstName(rs.getString("FirstName"));
        	customerresponsevo.setEmail(rs.getString("Email"));
        	customerresponsevo.setMobileNumber(rs.getString("MobileNumber"));
        	
        	PreparedStatement pstmt1 = con.prepareStatement("SELECT UserID FROM user WHERE CustomerID = ?");
        	pstmt1.setInt(1, rs.getInt("CustomerID"));
        
        	ResultSet rs1 = pstmt1.executeQuery();
        	if(rs1.next()) {
        		customerresponsevo.setUserID(rs1.getString("UserID"));
        	}
        	
			updaterequestlist.add(customerresponsevo);

        }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		return updaterequestlist;
	}
	
	public String approverequest(int requestid, int action) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();
			
			if(action == 1) {

				pstmt = con.prepareStatement("UPDATE customermeterdetails AS cmd INNER JOIN updaterequestcustomermeterdetails AS urcmd ON cmd.CustomerID = urcmd.CustomerID SET cmd.HouseNumber = urcmd.HouseNumber, cmd.FirstName = urcmd.FirstName, cmd.Email = urcmd.Email, cmd.MobileNumber = urcmd.MobileNumber, cmd.ModifiedDate = NOW() WHERE cmd.CustomerID = (SELECT CustomerID FROM updaterequestcustomermeterdetails WHERE RequestID = ?)");
	            pstmt.setInt(1, requestid);

	            if (pstmt.executeUpdate() > 0) {
	            	
	            	PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM updaterequestcustomermeterdetails WHERE RequestID = ?");
	            	pstmt1.setInt(1, requestid);
	            	if(pstmt1.executeUpdate() > 0) {
	            		result = "Success";	
	            	}
	            }

			}else {
				
				PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM updaterequestcustomermeterdetails WHERE RequestID = ?");
            	pstmt1.setInt(1, requestid);
            	if(pstmt1.executeUpdate() > 0) {
            		result = "Rejected";	
            	}
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}
	
	public boolean checkcustomer(String firstName, String lastName) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("SELECT FirstName, LastName from customermeterdetails where FirstName = ? AND LastName = ?");
		pstmt.setString(1, firstName.trim());
		pstmt.setString(2, lastName.trim());
		
		rs = pstmt.executeQuery();
        if (rs.next()) {
        	result = true;
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}
	
	public boolean checkpendingrequest(int customerID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try{
		con = getConnection();
		
		pstmt = con.prepareStatement("SELECT CustomerID from updaterequestcustomermeterdetails where CustomerID = ?");
		pstmt.setInt(1, customerID);
		
		rs = pstmt.executeQuery();
        if (rs.next()) {
        	result = true;
        	}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}
		
		return result;
	}

/*Tariff*/
	
	public List<TariffResponseVO> getTariffdetails() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TariffResponseVO> tariff_list = null;
		TariffResponseVO tariffvo = null;

		try {
			con = getConnection();
			tariff_list = new LinkedList<TariffResponseVO>();
			pstmt = con.prepareStatement("SELECT TariffID, Tariff, EmergencyCredit, AlarmCredit, FixedCharges, RegisteredDate FROM tariff");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tariffvo = new TariffResponseVO();
				tariffvo.setTariffID(rs.getInt("TariffID"));
				tariffvo.setTariff(rs.getString("Tariff"));
				tariffvo.setEmergencyCredit(rs.getString("EmergencyCredit"));
				tariffvo.setAlarmCredit(rs.getString("AlarmCredit"));
				tariffvo.setFixedCharges(rs.getString("FixedCharges"));
				tariffvo.setRegisteredDate(rs.getString("RegisteredDate"));
				tariff_list.add(tariffvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return tariff_list;
	}

	public String addtariff(TariffRequestVO tariffvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();
			pstmt = con.prepareStatement("INSERT INTO tariff (Tariff, EmergencyCredit, AlarmCredit, FixedCharges, RegisteredDate) VALUES(?, ?, ?, ?, NOW())");
			pstmt.setFloat(1, tariffvo.getTariff());
			pstmt.setFloat(2, tariffvo.getEmergencyCredit());
			pstmt.setFloat(3, tariffvo.getAlarmCredit());
			pstmt.setFloat(4, tariffvo.getFixedCharges());

			if (!pstmt.execute()) {

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

	public boolean checktariffamount(float tariff) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT * FROM tariff WHERE Tariff = ?");
			pstmt.setFloat(1, tariff);
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

}
