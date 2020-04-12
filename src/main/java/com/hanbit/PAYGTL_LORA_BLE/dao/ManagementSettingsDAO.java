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
import com.hanbit.PAYGTL_LORA_BLE.request.vo.AlertRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.HolidayRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.LoginVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserManagementRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.AlertResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.HolidayResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.UserManagementResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class ManagementSettingsDAO {

	LoginVO loginvo = new LoginVO();

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {

		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	/* User Management */

	public List<UserManagementResponseVO> getuserdetails(int roleid, int id) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<UserManagementResponseVO> user_list = null;
		UserManagementResponseVO usermanagementvo = null;

		try {
			con = getConnection();
			user_list = new LinkedList<UserManagementResponseVO>();
			
			String query = "SELECT user.ID, user.UserID, user.UserName, userrole.RoleDescription, community.CommunityName, block.BlockName, user.CreatedByID, user.ModifiedDate \r\n" + 
					"	FROM USER LEFT JOIN community ON community.CommunityID = user.CommunityID LEFT JOIN block ON block.BlockID = user.BlockID\r\n" + 
					"	LEFT JOIN userrole ON userrole.RoleID = user.RoleID <change> ";
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY user.CommunityID ASC" : (roleid == 2 || roleid == 5) ? "WHERE user.BlockID = "+id+ " ORDER BY user.BlockID ASC" : ""));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				usermanagementvo = new UserManagementResponseVO();
				usermanagementvo.setUserID(rs.getString("UserID"));
				usermanagementvo.setUserName(rs.getString("UserName"));
				usermanagementvo.setRole(rs.getString("RoleDescription"));
				usermanagementvo.setID(rs.getInt("ID"));
				usermanagementvo.setCommunityName(rs.getString("CommunityName"));
				usermanagementvo.setBlockName(rs.getString("BlockName"));

				if(rs.getInt("CreatedByID")>0) {
					pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
					rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
					usermanagementvo.setCreatedByUserName(rs1.getString("UserName"));
					usermanagementvo.setCreatedByRoleDescription(rs1.getString("RoleDescription"));
					}
				}
				
				user_list.add(usermanagementvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			pstmt1.close();
			rs.close();
			rs1.close();
			con.close();
		}
		return user_list;
	}

	public String adduser(UserManagementRequestVO usermanagementvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String result = "Failure";

		try {
			con = getConnection();

				String query = " INSERT INTO user (UserID, UserName, UserPassword, RoleID, ActiveStatus, CommunityID, BlockID, CustomerID, CreatedByID, CreatedByRoleID, ModifiedDate) values(?, ?, ?, ?, 1, <change>, ?, ?, NOW()) ";
			
			    pstmt = con.prepareStatement(query.replaceAll("<change>", (usermanagementvo.getRoleID() == 4) ? "0, 0, 0" : "?, ?, ?"));
			
				pstmt.setString(1, usermanagementvo.getUserID());
				pstmt.setString(2, usermanagementvo.getUserName());
				pstmt.setString(3, usermanagementvo.getUserPassword());
				pstmt.setInt(4, usermanagementvo.getRoleID());
				
				pstmt1 = con.prepareStatement("SELECT ID FROM user WHERE UserID = ? ");
				pstmt1.setString(1, usermanagementvo.getLoggedInUserID());
				rs = pstmt1.executeQuery();
				if(rs.next()) {
					
				if(usermanagementvo.getRoleID()!=4) {
					
					if(usermanagementvo.getRoleID()==3) {
						
						pstmt.setInt(5, usermanagementvo.getCommunityID());
						pstmt.setInt(6, usermanagementvo.getBlockID());
						pstmt.setInt(7, usermanagementvo.getCustomerID());
						pstmt.setInt(8, rs.getInt("ID"));
						pstmt.setInt(9, usermanagementvo.getLoggedInRoleID());
						
					}else {
						pstmt.setInt(5, usermanagementvo.getCommunityID());
						pstmt.setInt(6, usermanagementvo.getBlockID());
						pstmt.setInt(7, 0);
						pstmt.setInt(8, rs.getInt("ID"));
						pstmt.setInt(9, usermanagementvo.getLoggedInRoleID());
					}
					
				} else {
					pstmt.setInt(5, rs.getInt("ID"));	
					pstmt.setInt(6, usermanagementvo.getLoggedInRoleID());
				}
			}	
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

	/* Alert */

	public List<AlertResponseVO> getalertdetails() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AlertResponseVO> alert_settings_list = null;
		try {
			con = getConnection();
			AlertResponseVO alertvo = null;
			alert_settings_list = new LinkedList<AlertResponseVO>();
			pstmt = con.prepareStatement("SELECT alertsettings.AlertID, community.CommunityName, alertsettings.NoAMRInterval, alertsettings.LowBatteryVoltage, alertsettings.TimeOut, alertsettings.RegisteredDate FROM alertsettings LEFT JOIN community ON community.CommunityID = alertsettings.CommunityID");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				alertvo = new AlertResponseVO();
				alertvo.setCommunityName(rs.getString("CommunityName"));
				alertvo.setNoAMRInterval((rs.getString("NoAMRInterval")));
				alertvo.setLowBatteryVoltage(rs.getString("LowBatteryVoltage"));
				alertvo.setTimeOut(rs.getString("TimeOut"));
				alertvo.setRegisteredDate(rs.getString("RegisteredDate"));
				alertvo.setAlertID(rs.getInt("AlertID"));
				alert_settings_list.add(alertvo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return alert_settings_list;

	}

	public String addalert(AlertRequestVO alertvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		String result = "Failure";

		try {
			con = getConnection();

			ps = con.prepareStatement("INSERT INTO alertsettings (CommunityID, NoAMRInterval, LowBatteryVoltage, TimeOut, Active, RegisteredDate, ModifiedDate) VALUES (?, ?, ?, ?, 1, NOW(), NOW())");
			ps.setInt(1, alertvo.getCommunityID());
			ps.setInt(2, alertvo.getNoAMRInterval());
			ps.setFloat(3, alertvo.getLowBatteryVoltage());
			ps.setInt(4, alertvo.getTimeOut());

			if (ps.executeUpdate() > 0) {
				result = "Success";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

		return result;
	}

	public String editalert(AlertRequestVO alertvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		String result = "Failure";

		try {
			con = getConnection();

			ps = con.prepareStatement("UPDATE alertsettings SET NoAMRInterval = ?, LowBatteryVoltage = ?, TimeOut = ?, ModifiedDate = NOW() WHERE AlertID = ?");
			ps.setInt(1, alertvo.getNoAMRInterval());
			ps.setFloat(2, alertvo.getLowBatteryVoltage());
			ps.setInt(3, alertvo.getTimeOut());
			ps.setInt(4, alertvo.getAlertID());

			if (ps.executeUpdate() > 0) {
				result = "Success";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

		return result;
	}

	public boolean checkalertsettings(int communityid) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt1 = con.prepareStatement("select CommunityID from alertsettings where CommunityID = "+communityid);
			rs1 = pstmt1.executeQuery();

			if (rs1.next()) {
				result = true;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs1.close();
			pstmt1.close();
			con.close();
		}
		return result;
	}

	/* Holiday */

	public List<HolidayResponseVO> getholidaydetails() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HolidayResponseVO> holiday_settings_list = null;
		try {
			con = getConnection();
			holiday_settings_list = new LinkedList<HolidayResponseVO>();

			String sqlQuery = "select com_name,h.HolidayID,h.HolidayDate,h.HolidayName,h.LastModified from HolidayList h, community c order by HolidayDate";
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			HolidayResponseVO holidayvo = null;

			while (rs.next()) {
				holidayvo = new HolidayResponseVO();
				holidayvo.setCommunityName(rs.getString("com_name"));
				holidayvo.setHolidayDate((rs.getString("HolidayDate")));
				holidayvo.setHolidayName(rs.getString("HolidayName"));
				holidayvo.setId(rs.getInt("HolidayID"));
				holiday_settings_list.add(holidayvo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return holiday_settings_list;

	}

	public String addholiday(HolidayRequestVO holidayvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		ResultSet rs1 = null;
		String result = "Failure";
		int holidayID = 0;

		try {

			con = getConnection();

			ps = con.prepareStatement("select HolidayID from HolidayList where HolidayDate=?");
			ps.setString(1, holidayvo.getHolidayDate());
			rs1 = ps.executeQuery();
			if (!rs1.next()) {

				PreparedStatement ps1 = con
						.prepareStatement("select max(holidayid) as hid from holidaylist");
				ResultSet rs2 = ps1.executeQuery();

				if (rs2.next()) {
					holidayID = rs2.getInt("hid") + 1;
				}

				pstmt = con
						.prepareStatement("insert into HolidayList(HolidayID,HolidayDate,HolidayName,communityID,LastModified) values(?,?,?,?,SYSDATETIME())");
				pstmt.setInt(1, holidayID);
				pstmt.setString(2, holidayvo.getHolidayDate());
				pstmt.setString(3, holidayvo.getHolidayName());
				pstmt.setInt(4, LoginDAO.CommunityID);

				if (pstmt.executeUpdate() > 0) {
					result = "Success";
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			ps.close();
			con.close();
		}

		return result;
	}

	public String editholiday(HolidayRequestVO holidayvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();

			pstmt = con
					.prepareStatement("update HolidayList set HolidayName=?,HolidayDate=?,communityID=? where HolidayID=?");
			pstmt.setString(1, holidayvo.getHolidayName());
			pstmt.setString(2, holidayvo.getHolidayDate());
			pstmt.setInt(3, LoginDAO.CommunityID);
			pstmt.setInt(4, holidayvo.getHolidayID());

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

	public String deleteholiday(HolidayRequestVO holidayvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();
			pstmt = con
					.prepareStatement("delete from HolidayList where communityID=? and HolidayDate=?");
			pstmt.setInt(1, LoginDAO.CommunityID);
			pstmt.setString(2, holidayvo.getHolidayDate());

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

}
