/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.AlertRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.FeedbackRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.VacationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.LoginVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.RestCallVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserManagementRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.AlertResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.FeedbackResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TataResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.VacationResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.utils.Encoding;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.UserManagementResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class ManagementSettingsDAO {

	LoginVO loginvo = new LoginVO();
	Gson gson = new Gson();

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
			
			String query = "SELECT user.ID, user.UserID, user.UserName, userrole.RoleDescription, community.CommunityID, community.CommunityName, block.BlockID, block.BlockName, user.CreatedByID, user.ModifiedDate \r\n" + 
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
				usermanagementvo.setCommunityName((rs.getInt("CommunityID") != 0) ? rs.getString("CommunityName") : "NA");
				usermanagementvo.setBlockName((rs.getInt("BlockID") != 0) ? rs.getString("BlockName") : "NA");

				if(rs.getInt("CreatedByID")>0) {
					pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
					rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
					usermanagementvo.setCreatedByUserName(rs1.getString("UserName"));
					usermanagementvo.setCreatedByRoleDescription(rs1.getString("RoleDescription"));
					} 
				}else {
					usermanagementvo.setCreatedByUserName("NA");
					usermanagementvo.setCreatedByRoleDescription("NA");
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

	public ResponseVO adduser(UserManagementRequestVO usermanagementvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();

				String query = " INSERT INTO user (UserID, UserName, UserPassword, RoleID, ActiveStatus, CommunityID, BlockID, CustomerID, CreatedByID, CreatedByRoleID, CRNNumber, ModifiedDate) values(?, ?, ?, ?, 1, <change>, ?, ?, NOW()) ";
			
			    pstmt = con.prepareStatement(query.replaceAll("<change>", (usermanagementvo.getRoleID() == 4) ? "0, 0, 0, 0" : "?, ?, ?, ?"));
			
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
						pstmt.setString(10, usermanagementvo.getCRNNumber());
						
					}else {
						pstmt.setInt(5, usermanagementvo.getCommunityID());
						pstmt.setInt(6, usermanagementvo.getBlockID());
						pstmt.setInt(7, 0);
						pstmt.setInt(8, rs.getInt("ID"));
						pstmt.setInt(9, usermanagementvo.getLoggedInRoleID());
						pstmt.setString(10, usermanagementvo.getCRNNumber());
					}
					
				} else {
					pstmt.setInt(5, rs.getInt("ID"));	
					pstmt.setInt(6, usermanagementvo.getLoggedInRoleID());
				}
			}	
				if (pstmt.executeUpdate() > 0) {
					responsevo.setResult("Success");
					responsevo.setMessage("User Created Successfully");
				}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
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
			pstmt = con.prepareStatement("SELECT AlertID, NoAMRInterval, TimeOut, PerUnitValue, ReconnectionCharges, ModifiedDate FROM alertsettings");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				alertvo = new AlertResponseVO();
				alertvo.setNoAMRInterval((rs.getString("NoAMRInterval")));
				alertvo.setTimeOut(rs.getString("TimeOut"));
				alertvo.setPerUnitValue(rs.getFloat("PerUnitValue"));
				alertvo.setReconnectionCharges(rs.getInt("ReconnectionCharges"));
				alertvo.setRegisteredDate(ExtraMethodsDAO.datetimeformatter(rs.getString("ModifiedDate")));
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

	public ResponseVO addalert(AlertRequestVO alertvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();

			ps = con.prepareStatement("INSERT INTO alertsettings (NoAMRInterval, TimeOut, PerUnitValue, ReconnectionCharges, RegisteredDate, ModifiedDate) VALUES (?, ?, ?, ?, NOW(), NOW())");
			ps.setInt(1, alertvo.getNoAMRInterval());
			ps.setInt(2, alertvo.getTimeOut());
			ps.setFloat(3, alertvo.getPerUnitValue());
			ps.setInt(4, alertvo.getReconnectionCharges());

			if (ps.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Alert Settings Added Successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			ps.close();
			con.close();
		}

		return responsevo;
	}

	public ResponseVO editalert(AlertRequestVO alertvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();

			ps = con.prepareStatement("UPDATE alertsettings SET NoAMRInterval = ?, TimeOut = ?, PerUnitValue = ?, ReconnectionCharges = ?, ModifiedDate = NOW() WHERE AlertID = ?");
			ps.setInt(1, alertvo.getNoAMRInterval());
			ps.setInt(2, alertvo.getTimeOut());
			ps.setFloat(3, alertvo.getPerUnitValue());
			ps.setInt(4, alertvo.getReconnectionCharges());
			ps.setInt(5, alertvo.getAlertID());

			if (ps.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Alert Settings Edited Successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			ps.close();
			con.close();
		}

		return responsevo;
	}

	public boolean checkalertsettings() throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			con = getConnection();

			pstmt1 = con.prepareStatement("select * from alertsettings");
			rs = pstmt1.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt1.close();
			con.close();
		}
		return result;
	}

	/* Vacation */

	public List<VacationResponseVO> getvacationdetails(int roleid, String id, int filterCid) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<VacationResponseVO> vacationlist = null;
		VacationResponseVO vacationResponseVO = null;
		try {
			con = getConnection();
			vacationlist = new LinkedList<VacationResponseVO>();

			String query = "SELECT v.VacationID, v.VacationName, c.CommunityName, b.BlockName, cmd.HouseNumber, cmd.FirstName, cmd.LastName, cmd.MeterID, cmd.CRNNumber, v.StartDate, v.EndDate, "
					+ "v.Source, v.Status, v.Mode, v.RegisteredDate FROM Vacation AS V LEFT JOIN community AS C ON c.CommunityID = v.CommunityID LEFT JOIN block AS b ON b.blockID = v.BlockID "
					+ "LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerID = v.CustomerID <change>";
							
			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && (filterCid == -1)) ? "ORDER BY v.VacationID DESC" : 
				((roleid == 1 || roleid == 4) && (filterCid != -1)) ? " WHERE v.CommunityID = "+filterCid+" ORDER BY v.VacationID DESC" : (roleid == 2 || roleid == 5) ?
						" Where v.BlockID = "+id+ " ORDER BY v.VacationID DESC" : (roleid == 3) ? " WHERE v.CRNNumber = '"+id+ "' ORDER BY v.VacationID DESC" :""));
			
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				vacationResponseVO = new VacationResponseVO();
				vacationResponseVO.setVacationID(rs.getInt("VacationID"));
				vacationResponseVO.setCommunityName(rs.getString("CommunityName"));
				vacationResponseVO.setBlockName((rs.getString("BlockName")));
				vacationResponseVO.setHouseNumber(rs.getString("HouseNumber"));
				vacationResponseVO.setFirstName(rs.getString("FirstName"));
				vacationResponseVO.setLastName(rs.getString("LastName"));
				vacationResponseVO.setCRNNumber(rs.getString("CRNNumber"));
				vacationResponseVO.setMeterID(rs.getString("MeterID"));
				vacationResponseVO.setVacationName(rs.getString("VacationName"));
				vacationResponseVO.setStartDate(ExtraMethodsDAO.datetimeformatter(rs.getString("StartDate")));
				vacationResponseVO.setEndDate(ExtraMethodsDAO.datetimeformatter(rs.getString("EndDate")));
				vacationResponseVO.setStartDateForEdit(rs.getString("StartDate"));
				vacationResponseVO.setEndDateForEdit(rs.getString("EndDate"));
				vacationResponseVO.setMode(rs.getString("Mode"));
				vacationResponseVO.setRegisteredDate(ExtraMethodsDAO.datetimeformatter(rs.getString("RegisteredDate")));
				vacationResponseVO.setStatus((rs.getInt("Status") == 0) ? "Pending...waiting for acknowledge" : (rs.getInt("Status") == 1) ? "Pending" : (rs.getInt("Status") == 2) ? "Passed" :"Failed");
				vacationlist.add(vacationResponseVO);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return vacationlist;

	}

	public ResponseVO addvacation(VacationRequestVO vacationRequestVO) throws SQLException {
		// TODO Auto-generated method stub

		Random randomNumber = new Random();
		ResponseVO responsevo = new ResponseVO(); 
		PreparedStatement pstmt1 = null;
		Connection con = null;

		try {
			con = getConnection();
			pstmt1 = con.prepareStatement("SELECT CommunityID, BlockID, CustomerID, MeterID FROM customermeterdetails WHERE CRNNumber = '"+vacationRequestVO.getCRNNumber()+"'");
			ResultSet rs1 = pstmt1.executeQuery();

			if (rs1.next()) {
				vacationRequestVO.setMeterID(rs1.getString("MeterID"));
				vacationRequestVO.setCommunityID(rs1.getInt("CommunityID"));
				vacationRequestVO.setBlockID(rs1.getInt("BlockID"));
				vacationRequestVO.setCustomerID(rs1.getInt("CustomerID"));
				
				if (vacationRequestVO.getSource().equalsIgnoreCase("web")) {
					
						String serialNumber = String.format("%04x", randomNumber.nextInt(65000));
						System.out.println("serialNumber:-"+ serialNumber);

						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						LocalDateTime startDateTime = LocalDateTime.parse(vacationRequestVO.getStartDateTime()+":00", dtf);
						LocalDateTime endDateTime = LocalDateTime.parse(vacationRequestVO.getEndDateTime()+":00", dtf);

						String dataframe = "0A1600" + serialNumber + "020C0141" + String.format("%02x", startDateTime.getDayOfMonth()) + String.format("%02x", startDateTime.getMonthValue()) + 
								String.format("%02x", (startDateTime.getYear()-2000)) + String.format("%02x", startDateTime.getHour()) + String.format("%02x", startDateTime.getMinute()) + 
								String.format("%02x", 0) + String.format("%02x", vacationRequestVO.getStartDay()) + 
								String.format("%02x", endDateTime.getDayOfMonth()) + String.format("%02x", endDateTime.getMonthValue()) + String.format("%02x", (endDateTime.getYear()-2000)) + 
								String.format("%02x", endDateTime.getHour()) + String.format("%02x", endDateTime.getMinute()) +	String.format("%02x", 59) + String.format("%02x", vacationRequestVO.getEndDay()) + "17";
						
						System.out.println("dataframe in vacation:--"+dataframe);
						ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
						RestCallVO restcallvo = new RestCallVO();
						
						restcallvo.setDataFrame(Encoding.getHexBase644(dataframe));
						restcallvo.setMeterID(vacationRequestVO.getMeterID().toLowerCase());

						String restcallresponse = extramethodsdao.tatapost(restcallvo);
						
						TataResponseVO tataResponseVO = gson.fromJson(restcallresponse, TataResponseVO.class);
						
						vacationRequestVO.setTransactionIDForTata(tataResponseVO.getId());
						vacationRequestVO.setStatus(tataResponseVO.getTransmissionStatus());
						if(insertvacation(vacationRequestVO).equalsIgnoreCase("Success")) {
							responsevo.setResult("Success");
							responsevo.setMessage("Vacation Request Submitted Successfully");
						}else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Vacation Request Failed");
						}
						

					} else {
						vacationRequestVO.setTransactionIDForTata(0);
						if(insertvacation(vacationRequestVO).equalsIgnoreCase("Success")) {
							responsevo.setResult("Success");
							responsevo.setMessage("Vacation Request Inserted Successfully");
						}else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Vacation Request Insertion Failed");
						}
					}
		} 
			
		}catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		}
		return responsevo;
	}
	
	public String insertvacation(VacationRequestVO vacationRequestVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement(
						"INSERT INTO vacation (TataReferenceNumber, communityID, BlockID, CustomerID, MeterID, VacationName, StartDate, EndDate, Status, Source, CRNNumber, Mode, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'add', NOW())");

				pstmt.setLong(1, vacationRequestVO.getTransactionIDForTata());
				pstmt.setInt(2, vacationRequestVO.getCommunityID());
				pstmt.setInt(3, vacationRequestVO.getBlockID());
				pstmt.setInt(4, vacationRequestVO.getCustomerID());
				pstmt.setString(5, vacationRequestVO.getMeterID());
				pstmt.setString(6, vacationRequestVO.getVacationName());
				pstmt.setString(7, vacationRequestVO.getStartDateTime());
				pstmt.setString(8, vacationRequestVO.getEndDateTime());
				pstmt.setInt(9, vacationRequestVO.getStatus());
				pstmt.setString(10, vacationRequestVO.getSource());
				pstmt.setString(11, vacationRequestVO.getCRNNumber());

				if (pstmt.executeUpdate() > 0) {
					result = "Success";
				}
		} catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public ResponseVO editvacation(VacationRequestVO vacationRequestVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Random randomNumber = new Random();
		ResponseVO responsevo = new ResponseVO(); 
		
		try {
			con = getConnection();

			pstmt = con.prepareStatement(
					"SELECT MeterID FROM vacation WHERE VacationID = " + vacationRequestVO.getVacationID());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vacationRequestVO.setMeterID(rs.getString("MeterID"));
				vacationRequestVO.setMode("edit");

				if (vacationRequestVO.getSource().equalsIgnoreCase("web")) {

					String serialNumber = String.format("%04x", randomNumber.nextInt(65000));
					
					System.out.println("serialNumber:-"+ serialNumber);

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime startDateTime = LocalDateTime.parse(vacationRequestVO.getStartDateTime()+":00", dtf);
					LocalDateTime endDateTime = LocalDateTime.parse(vacationRequestVO.getEndDateTime()+":00", dtf);

					String dataframe = "0A1600" + serialNumber + "020C0141"
							+ String.format("%02x", startDateTime.getDayOfMonth())
							+ String.format("%02x", startDateTime.getMonthValue())
							+ String.format("%02x", (startDateTime.getYear()-2000))
							+ String.format("%02x", startDateTime.getHour())
							+ String.format("%02x", startDateTime.getMinute()) + String.format("%02x", 0)
							+ String.format("%02x", vacationRequestVO.getStartDay())
							+ String.format("%02x", endDateTime.getDayOfMonth())
							+ String.format("%02x", endDateTime.getMonthValue())
							+ String.format("%02x", (endDateTime.getYear()-2000))
							+ String.format("%02x", endDateTime.getHour())
							+ String.format("%02x", endDateTime.getMinute()) + String.format("%02x", 59)
							+ String.format("%02x", vacationRequestVO.getEndDay()) + "17";

					ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
					RestCallVO restcallvo = new RestCallVO();
					restcallvo.setDataFrame(dataframe);
					restcallvo.setMeterID(vacationRequestVO.getMeterID().toLowerCase());

					String restcallresponse = extramethodsdao.tatapost(restcallvo);

					TataResponseVO tataResponseVO = gson.fromJson(restcallresponse, TataResponseVO.class);

					vacationRequestVO.setTransactionIDForTata(tataResponseVO.getId());
					vacationRequestVO.setStatus(tataResponseVO.getTransmissionStatus());

					if (updatevacation(vacationRequestVO).equalsIgnoreCase("Success")) {
						responsevo.setResult("Success");
						responsevo.setMessage("Vacation Update request Submitted Successfully");
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Vacation Update request Failed");
					}
				} else {
					vacationRequestVO.setTransactionIDForTata(0);
					if (updatevacation(vacationRequestVO).equalsIgnoreCase("Success")) {
						responsevo.setResult("Success");
						responsevo.setMessage("Vacation Update request Inserted Successfully");
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Vacation Update request Failed to Insert");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		}
		return responsevo;
	}
	
	public String updatevacation(VacationRequestVO vacationRequestVO) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();

			pstmt = con.prepareStatement("UPDATE vacation SET TataReferenceNumber = ?, VacationName = ?, StartDate = ?, EndDate = ?, Status = ?, Source = ?, Mode = ?, ModifiedDate = NOW() WHERE VacationID = "+ vacationRequestVO.getVacationID());

			pstmt.setLong(1, vacationRequestVO.getTransactionIDForTata());
			pstmt.setString(2, vacationRequestVO.getVacationName());
			pstmt.setString(3, vacationRequestVO.getStartDateTime());
			pstmt.setString(4, vacationRequestVO.getEndDateTime());
			pstmt.setInt(5, vacationRequestVO.getStatus());
			pstmt.setString(6, vacationRequestVO.getSource());
			pstmt.setString(7, vacationRequestVO.getMode());
			
			if(pstmt.executeUpdate() > 0) {
				result = "Success";
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public ResponseVO deletevacation(int vacationID, String source) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Random randomNumber = new Random();
		VacationRequestVO vacationRequestVO = new VacationRequestVO();
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();

			pstmt = con.prepareStatement("SELECT MeterID, StartDate, EndDate, VacationName FROM vacation WHERE VacationID = " + vacationID);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				vacationRequestVO.setMeterID(rs.getString("MeterID"));
				vacationRequestVO.setMode("delete");
				vacationRequestVO.setSource(source);
				vacationRequestVO.setVacationName(rs.getString("VacationName"));
				vacationRequestVO.setVacationID(vacationID);

				if (source.equalsIgnoreCase("web")) {

					String serialNumber = String.format("%04x", randomNumber.nextInt(65000));

					String dataframe = "0A1600" + serialNumber + "020C0141FFFFFFFFFFFFFFFFFFFFFFFFFF17";

					ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
					RestCallVO restcallvo = new RestCallVO();

					restcallvo.setMeterID(vacationRequestVO.getMeterID().toLowerCase());
					restcallvo.setDataFrame(Encoding.getHexBase644(dataframe));

					String restcallresponse = extramethodsdao.tatapost(restcallvo);

					TataResponseVO tataResponseVO = gson.fromJson(restcallresponse, TataResponseVO.class);

					vacationRequestVO.setTransactionIDForTata(tataResponseVO.getId());
					vacationRequestVO.setStatus(tataResponseVO.getTransmissionStatus());
					vacationRequestVO.setStartDateTime(rs.getString("StartDate"));
					vacationRequestVO.setEndDateTime(rs.getString("EndDate"));

					if (updatevacation(vacationRequestVO).equalsIgnoreCase("Success")) {
						responsevo.setResult("Success");
						responsevo.setMessage("Vacation Delete request Submitted Successfully");
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Vacation delete request Failed");
					}
				} else {
					vacationRequestVO.setTransactionIDForTata(0);
					vacationRequestVO.setStatus(2);
					vacationRequestVO.setStartDateTime(rs.getString("StartDate"));
					vacationRequestVO.setEndDateTime(rs.getString("EndDate"));
					if (updatevacation(vacationRequestVO).equalsIgnoreCase("Success")) {
						responsevo.setResult("Success");
						responsevo.setMessage("Vacation Delete request Inserted Successfully");
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Vacation delete request Insertion Failed");
					}
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public boolean checkvacationsettings(VacationRequestVO vacationRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT MeterID, Status, StartDate, EndDate, Mode FROM vacation WHERE CRNNumber = ? order by VacationID DESC LIMIT 0,1");
			pstmt.setString(1, vacationRequestVO.getCRNNumber());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("Status").equals("0") || rs.getString("Status").equals("1")) {
					result = true;
				} else {
					PreparedStatement pstmt1 = con.prepareStatement("SELECT VacationID FROM vacation WHERE Status BETWEEN 0 AND 1 AND ? BETWEEN StartDate AND EndDate ");
					pstmt1.setString(1, vacationRequestVO.getStartDateTime());
					ResultSet rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
						result = true;
					}else {
						pstmt1 = con.prepareStatement("SELECT VacationID FROM vacation WHERE Status BETWEEN 0 AND 1 AND ? BETWEEN StartDate AND EndDate");
						pstmt1.setString(1, vacationRequestVO.getEndDateTime());
						rs1 = pstmt1.executeQuery();
						if(rs1.next()) {
							result = true;	
						}
					}
				}
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
	
	public boolean checkvacationpending(int vacationID, String source) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT Status FROM vacation WHERE VacationID = "+vacationID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("Status").equals("0") || rs.getString("Status").equals("1")) {
					result = true;
				}
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

	public List<FeedbackResponseVO> getfeedbackdetails(int blockid) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FeedbackResponseVO> feedbackList = null;
		FeedbackResponseVO feedbackResponseVO = null;
		try {
			con = getConnection();
			feedbackList = new LinkedList<FeedbackResponseVO>();

			pstmt = con.prepareStatement("SELECT f.FeedbackID, f.Feedback, f.Description, CONCAT(cmd.FirstName, ' ', cmd.LastName) AS CustomerName, cmd.HouseNumber, f.MeterID, f.CRNNumber, f.RegisteredDate \n" + 
					"FROM feedback AS f LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerID = f.CustomerID WHERE f.Status = 0 AND f.BlockID = "+blockid);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				feedbackResponseVO = new FeedbackResponseVO();
				
				feedbackResponseVO.setFeedbackID(rs.getInt("FeedbackID"));
				feedbackResponseVO.setFeedback(rs.getString("Feedback"));
				feedbackResponseVO.setDescription(rs.getString("Description"));
				feedbackResponseVO.setCRNNumber(rs.getString("CRNNumber"));
				feedbackResponseVO.setName(rs.getString("CustomerName"));
				feedbackResponseVO.setHouseNumber(rs.getString("HouseNumber"));
				feedbackResponseVO.setMeterID(rs.getString("MeterID"));
				feedbackResponseVO.setDate(ExtraMethodsDAO.datetimeformatter(rs.getString("RegisteredDate")));
				
				feedbackList.add(feedbackResponseVO);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return feedbackList;
	}

	public ResponseVO addfeedback(FeedbackRequestVO feedbackRequestVO) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResponseVO responsevo = new ResponseVO(); 

		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT CommunityID, BlockID, CustomerID, MeterID from customermeterdetails WHERE CRNNumber = '"+feedbackRequestVO.getCRNNumber()+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

			ps = con.prepareStatement("INSERT INTO feedback (Feedback, Description, CommunityID, BlockID, CustomerID, CRNNumber, MeterID, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())");
			ps.setString(1, feedbackRequestVO.getFeedback());
			ps.setString(2, feedbackRequestVO.getDescription());
			ps.setInt(3, rs.getInt("CommunityID"));
			ps.setInt(4, rs.getInt("BlockID"));
			ps.setInt(5, rs.getInt("CustomerID"));
			ps.setString(6, feedbackRequestVO.getCRNNumber());
			ps.setString(7, rs.getString("MeterID"));

			if (ps.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Feedback/Complaint Submitted Successfully");
			}
			
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			ps.close();
			con.close();
		}

		return responsevo;
	}
	
	public ResponseVO feedbackaction(int feedbackID, int action, String remarks) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			if(action == 1) {
				
				pstmt = con.prepareStatement("UPDATE feedback SET Status = 1, Remarks = ?, ModifiedDate = NOW() WHERE FeedbackID = ?");
	            pstmt.setString(1, remarks);
				pstmt.setInt(2, feedbackID);

	            if (pstmt.executeUpdate() > 0) {
	            	
	            	responsevo.setResult("Success");
            		responsevo.setMessage("Resolved Successfully");
	            	
	            }

			}else {
				
				pstmt = con.prepareStatement("UPDATE feedback SET Status = 2, Remarks = ?, ModifiedDate = NOW() WHERE FeedbackID = ?");
				pstmt.setString(1, remarks);
				pstmt.setInt(2, feedbackID);
				
            	if(pstmt.executeUpdate() > 0) {
            		responsevo.setResult("Success");
            		responsevo.setMessage("Rejected Successfully");
            	}
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}
		
		return responsevo;
	}

}
