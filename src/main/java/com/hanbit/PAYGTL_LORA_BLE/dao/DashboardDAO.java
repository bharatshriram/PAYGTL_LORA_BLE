/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.DashboardRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.FilterVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.MailRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.SMSRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TataRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.DashboardResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;

/**
 * @author k VimaL Kumar
 * 
 */
public class DashboardDAO {

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	public List<DashboardResponseVO> getDashboarddetails(int roleid, String id)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DashboardResponseVO> dashboard_list = null;
		DashboardResponseVO dashboardvo = null;
		int noAMRInterval = 0;
		double lowBatteryVoltage = 0.0;
		float perUnitValue = 0.0f;
		
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			int nonCommunicating = 0;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut, PerUnitValue FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getFloat("LowBatteryVoltage");
				perUnitValue = rs1.getFloat("PerUnitValue");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName,cmd.CRNNumber, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.TariffAmount, dbl.SolonideStatus, dbl.TamperDetect, dbl.Vacation, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
					"FROM displaybalancelog AS dbl LEFT JOIN community AS c ON c.communityID = dbl.CommunityID LEFT JOIN block AS b ON b.BlockID = dbl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = dbl.CRNNumber <change>";
		
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 2 || roleid == 5) ? "WHERE dbl.BlockID = "+id+ " ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 3) ? "WHERE dbl.CRNNumber = '"+id+"'":""));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dashboardvo = new DashboardResponseVO();
				dashboardvo.setCommunityName(rs.getString("CommunityName"));
				dashboardvo.setBlockName(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setFirstName(rs.getString("FirstName"));
				dashboardvo.setMeterSerialNumber(rs.getString("MeterSerialNumber"));
				dashboardvo.setLastName(rs.getString("LastName"));
				dashboardvo.setMeterID(rs.getString("MeterID"));
				dashboardvo.setTariff((rs.getFloat("TariffAmount")));
				dashboardvo.setCRNNumber(rs.getString("CRNNumber"));
				// send tariff id/TariffName after fetching from db
				dashboardvo.setReading(rs.getFloat("Reading"));
				dashboardvo.setConsumption((dashboardvo.getReading() * perUnitValue));
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				dashboardvo.setValveStatus((rs.getInt("SolonideStatus") == 0) ? "OPEN" : (rs.getInt("SolonideStatus") == 1) ? "CLOSED" : "");
				dashboardvo.setValveStatusColor((rs.getInt("SolonideStatus") == 0) ? "GREEN" : (rs.getInt("SolonideStatus") == 1) ? "RED" : "");
				// change the full battery voltage value accordingly
				dashboardvo.setBattery((int)((rs.getFloat("BatteryVoltage"))*(100/3.5)));
				dashboardvo.setBatteryColor((rs.getFloat("BatteryVoltage") < lowBatteryVoltage) ? "RED" : "GREEN");
				dashboardvo.setTamperStatus((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "YES" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" :"NO");
				dashboardvo.setTamperColor((rs.getInt("TamperDetect") == 0) ? "GREEN" : "RED");
				dashboardvo.setVacationStatus(rs.getInt("Vacation") == 1 ? "YES" : "NO");
				dashboardvo.setVacationColor(rs.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
				dashboardvo.setTimeStamp(rs.getString("IoTTimeStamp"));
				
				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());

				if(minutes > noAMRInterval) {
					nonCommunicating++;
					dashboardvo.setDateColor("RED");
					dashboardvo.setCommunicationStatus("NO");
				}else {
					dashboardvo.setDateColor("GREEN");
					dashboardvo.setCommunicationStatus("YES");
				}
				dashboardvo.setNonCommunicating(nonCommunicating);
				dashboard_list.add(dashboardvo);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return dashboard_list;
	}
	
	public List<DashboardResponseVO> getFilterDashboarddetails(int roleid, String id, FilterVO filtervo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DashboardResponseVO> dashboard_list = null;
		DashboardResponseVO dashboardvo = null;
		int noAMRInterval = 0;
		double lowBatteryVoltage = 0.0;
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getFloat("LowBatteryVoltage");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName,cmd.CRNNumber, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.TariffAmount, dbl.SolonideStatus, dbl.TamperDetect, dbl.Vacation, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
					"FROM displaybalancelog AS dbl LEFT JOIN community AS c ON c.communityID = dbl.CommunityID LEFT JOIN block AS b ON b.BlockID = dbl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = dbl.CRNNumber Where 1=1 <change> ";
			
			query = query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "" : (roleid == 2 || roleid == 5) ? " AND dbl.BlockID = "+id : (roleid == 3) ? "AND dbl.CRNNumber = '"+id+"'":"");
			StringBuilder stringBuilder = new StringBuilder(query);
			if(roleid !=3) {
				
				LocalDateTime dateTime = LocalDateTime.now();  
			    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				
				if(!filtervo.getDateFrom().equalsIgnoreCase("null") || !filtervo.getDateTo().equalsIgnoreCase("null")) {
					stringBuilder.append(" AND dbl.IoTTimeStamp BETWEEN '" + filtervo.getDateFrom() + "' AND '" + (filtervo.getDateTo() != null ? filtervo.getDateTo()+"'" : "'"+dateTime.format(dateTimeFormat)+"'"));
				}
				if(filtervo.getReadingFrom() != 0 || filtervo.getReadingTo() != 0) {
					stringBuilder.append(" AND dbl.Reading BETWEEN " + (filtervo.getReadingFrom() != 0 ? filtervo.getReadingFrom() : 0) + " AND " + (filtervo.getReadingTo() != 0 ? filtervo.getReadingTo() : 9999999));
				}
				if(filtervo.getBatteryVoltageFrom() != 0 || filtervo.getBatteryVoltageFrom() != 0) {
					System.out.println("@@");
					stringBuilder.append(" AND dbl.BatteryVoltage BETWEEN " + (filtervo.getBatteryVoltageFrom() != 0 ? ((filtervo.getBatteryVoltageFrom()*3.5)/100) : 0) + " AND " + (filtervo.getBatteryVoltageTo() != 0 ? ((filtervo.getBatteryVoltageFrom()*3.5)/100) : 10));
				}
				if(filtervo.getTamperType() > 0) {
					stringBuilder.append(" AND dbl.TamperDetect = " + filtervo.getTamperType());
				}
					stringBuilder.append(" ORDER BY dbl.IoTTimeStamp DESC");
			}
			
			pstmt = con.prepareStatement(stringBuilder.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dashboardvo = new DashboardResponseVO();
				dashboardvo.setCommunityName(rs.getString("CommunityName"));
				dashboardvo.setBlockName(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setFirstName(rs.getString("FirstName"));
				dashboardvo.setMeterSerialNumber(rs.getString("MeterSerialNumber"));
				dashboardvo.setLastName(rs.getString("LastName"));
				dashboardvo.setMeterID(rs.getString("MeterID"));
				dashboardvo.setTariff((rs.getFloat("TariffAmount")));
				dashboardvo.setCRNNumber(rs.getString("CRNNumber"));
				dashboardvo.setReading(rs.getFloat("Reading"));
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				dashboardvo.setValveStatus((rs.getInt("SolonideStatus") == 0) ? "OPEN" : (rs.getInt("SolonideStatus") == 1) ? "CLOSED" : "");	
				dashboardvo.setBattery((int)((rs.getFloat("BatteryVoltage"))*(100/3.5)));
				dashboardvo.setBatteryColor((rs.getFloat("BatteryVoltage") < lowBatteryVoltage) ? "RED" : "GREEN");
				dashboardvo.setTamperStatus((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "YES" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" :"NO");
				dashboardvo.setTamperColor((rs.getInt("TamperDetect") == 0) ? "GREEN" : "RED");
				dashboardvo.setVacationStatus(rs.getInt("Vacation") == 1 ? "YES" : "NO");
				dashboardvo.setVacationColor(rs.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
				dashboardvo.setTimeStamp(rs.getString("IoTTimeStamp"));
				
				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());

				if(minutes > noAMRInterval) {
					dashboardvo.setDateColor("RED");
					dashboardvo.setCommunicationStatus("NO");
				}else {
					dashboardvo.setDateColor("GREEN");
					dashboardvo.setCommunicationStatus("YES");
				}
				dashboard_list.add(dashboardvo);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return dashboard_list;
	}

	public ResponseVO postDashboarddetails(TataRequestVO tataRequestVO) throws SQLException {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO();
		DashboardRequestVO dashboardRequestVO = new DashboardRequestVO();
		String iot_Timestamp = "";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rsch = null;
		
		final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

		String alertMessage = "";
		
		try {
			
			con = getConnection();
			
				dashboardRequestVO.setMeterID(tataRequestVO.getDevEUI());
				byte[] decoded = Base64.getDecoder().decode(tataRequestVO.getDataFrame());

				StringBuffer sb = new StringBuffer(decoded.length * 2);
		        for (int i = 0; i < decoded.length; i++) {
		            sb.append(DIGITS[(decoded[i] >>> 4) & 0x0F]);
		            sb.append(DIGITS[decoded[i] & 0x0F]);
		        }
		        
		        
				if (sb.substring(0, 2).equalsIgnoreCase("0A")) {
					
					// 01 23 45 67 89 01 23 45 67 89 01 23 45 67 89 01 23 45 67 89 01 23 45 67
					//               10             20             30             40   
					
					dashboardRequestVO.setReading(DashboardDAO.hexDecimal(sb.substring(2, 10)));					
					dashboardRequestVO.setLowBattery(sb.substring(10, 12).equalsIgnoreCase("02") ? 1: 0);
					// 0 = no tamper 1 = tamper; 2 = door open
					dashboardRequestVO.setTamperStatus(sb.substring(10, 12).equalsIgnoreCase("04") ? 1: sb.substring(10, 12).equalsIgnoreCase("08") ? 2: 0);
					dashboardRequestVO.setVacation(sb.substring(10, 12).equalsIgnoreCase("10") ? 1: 0);
					dashboardRequestVO.setBatteryVoltage((float) (((DashboardDAO.hexDecimal(sb.substring(12, 14))) * 3.6) / 256));
					dashboardRequestVO.setMeterType(DashboardDAO.hexDecimal(sb.substring(14, 16)));

					Long i = Long.parseLong(sb.substring(16, 24), 16);
					dashboardRequestVO.setBalance(Float.intBitsToFloat(i.intValue()));
					
					Long j = Long.parseLong(sb.substring(24, 32), 16);
					dashboardRequestVO.setTariffAmount(Float.intBitsToFloat(j.intValue()));
					
					Long k = Long.parseLong(sb.substring(32, 40), 16);
					dashboardRequestVO.setEmergencyCredit(Float.intBitsToFloat(k.intValue()));
					
					dashboardRequestVO.setMinutes(DashboardDAO.hexDecimal(sb.substring(40, 44)));
					dashboardRequestVO.setValveStatus(DashboardDAO.hexDecimal(sb.substring(44, 46)));
					dashboardRequestVO.setTimeStamp(tataRequestVO.getTimestamp());
					
					if (dashboardRequestVO.getLowBattery() == 1) {
						alertMessage = "The Battery in Meter with MIU ID: " + dashboardRequestVO.getMeterID() + " installed at H.No: <house> with CRNNumber: <CRN> is low. Consider replacing it for uninterrupted Service.";
						
						sendalertmail("Low Battery Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
						sendalertsms(0, alertMessage, dashboardRequestVO.getMeterID());
					} 
					
					if (dashboardRequestVO.getTamperStatus() == 1 || dashboardRequestVO.getTamperStatus() == 2) {
						alertMessage = "There is a <change> Tamper in Meter with MIU ID: " + dashboardRequestVO.getMeterID() + " installed at H.No: <house> with CRNNumber: <CRN>. Clear it for uninterrupted Service.";
						alertMessage = alertMessage.replaceAll("<change>", dashboardRequestVO.getTamperStatus() == 2 ? "Door Open" : "");
						sendalertmail("Tamper Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
						sendalertsms(0, alertMessage, dashboardRequestVO.getMeterID());
					}

					// change low balance alert after discussion with team
					
					if(dashboardRequestVO.getBalance() < (dashboardRequestVO.getTariffAmount() * 3)) {
						alertMessage = "Balance in your Meter with MIU ID: " + dashboardRequestVO.getMeterID() + " is low. Recharge now for uninterrupted Service.";
						
						sendalertmail("Low Balance Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
//						sendalertsms(1, alertMessage, dashboardRequestVO.getMeterID());
					}

					pstmt = con.prepareStatement("SELECT IoTTimeStamp, MeterID FROM balancelog WHERE MeterID = ? order by IoTTimeStamp DESC LIMIT 0,1");
					pstmt.setString(1, dashboardRequestVO.getMeterID());
					rsch = pstmt.executeQuery();

					if (rsch.next()) {
						iot_Timestamp =  rsch.getString("IoTTimeStamp");
					}

					Instant instant = Instant.parse(dashboardRequestVO.getTimeStamp());
			        ZoneId.of("Asia/Kolkata");
			        LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Kolkata"));
			        dashboardRequestVO.setTimeStamp(datetime.toString().replaceAll("T", " ").substring(0, 19));
			        
					if (!dashboardRequestVO.getTimeStamp().equalsIgnoreCase(iot_Timestamp)) {
						
						responsevo.setResult(insertdashboard(dashboardRequestVO));
				
				} else {

					responsevo.setResult("No Data to update");
				}
					
				} else {
					responsevo.setResult("Invalid Frame");
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return responsevo;
	}

	public String insertdashboard (DashboardRequestVO dashboardRequestVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String result = "Failure";
		
		try {
			con = getConnection();
			
				PreparedStatement pstmt2 = con.prepareStatement("SELECT CommunityID, BlockID, CustomerID, TariffID, MeterSerialNumber, CRNNumber from customermeterdetails WHERE MeterID = ?");
				pstmt2.setString(1, dashboardRequestVO.getMeterID());
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next()) {
					
					pstmt = con.prepareStatement("INSERT INTO balancelog (MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, LowBattery, Vacation, MeterSerialNumber, CRNNumber, Minutes, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

					pstmt.setString(1, dashboardRequestVO.getMeterID());
					pstmt.setFloat(2, dashboardRequestVO.getReading());
					pstmt.setFloat(3, dashboardRequestVO.getBalance());// Balance Pending
					pstmt.setInt(4, rs.getInt("CommunityID"));
					pstmt.setInt(5, rs.getInt("BlockID"));
					pstmt.setInt(6, rs.getInt("CustomerID"));
					pstmt.setFloat(7, dashboardRequestVO.getBatteryVoltage());
					pstmt.setFloat(8, dashboardRequestVO.getTariffAmount());
					pstmt.setFloat(9, dashboardRequestVO.getEmergencyCredit());
					pstmt.setInt(10, dashboardRequestVO.getMeterType());
					pstmt.setInt(11, dashboardRequestVO.getValveStatus());
					pstmt.setInt(12, 0);
					pstmt.setInt(13, dashboardRequestVO.getTamperStatus());
					pstmt.setInt(14, dashboardRequestVO.getLowBattery());
					pstmt.setInt(15, dashboardRequestVO.getVacation());
					pstmt.setString(16, rs.getString("MeterSerialNumber"));
					pstmt.setString(17, rs.getString("CRNNumber"));
					pstmt.setInt(18, dashboardRequestVO.getMinutes());
					pstmt.setString(19, dashboardRequestVO.getTimeStamp());

					if (pstmt.executeUpdate() > 0) {
						
						PreparedStatement pstmt4 = con.prepareStatement("(SELECT MAX(ReadingID) as ReadingID FROM balancelog WHERE MeterID = ?)");
						pstmt4.setString(1, dashboardRequestVO.getMeterID());
						ResultSet rs2 = pstmt4.executeQuery();
						
						if(rs2.next()) {
							
							PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MeterID = ? ");
							pstmt3.setString(1, dashboardRequestVO.getMeterID());
							ResultSet rs1 = pstmt3.executeQuery();
							
							if(rs1.next()) {
								
								pstmt1 = con.prepareStatement("UPDATE displaybalancelog SET MainBalanceLogID = ?, MeterID = ?, Reading = ?, Balance = ?, CommunityID = ?, BlockID = ?, CustomerID = ?, BatteryVoltage = ?, TariffAmount = ?, EmergencyCredit = ?, MeterType = ?, SolonideStatus = ?, CreditStatus = ?, TamperDetect = ?, LowBattery = ?, Vacation = ?, Minutes = ?, IoTTimeStamp = ?, LogDate = NOW() WHERE MeterID = ? ");
								pstmt1.setInt(1, rs2.getInt("ReadingID"));
								pstmt1.setString(2, dashboardRequestVO.getMeterID());
								pstmt1.setFloat(3, dashboardRequestVO.getReading());
								pstmt1.setFloat(4, dashboardRequestVO.getBalance());
								pstmt1.setInt(5, rs.getInt("CommunityID"));
								pstmt1.setInt(6, rs.getInt("BlockID"));
								pstmt1.setInt(7, rs.getInt("CustomerID"));
								pstmt1.setFloat(8, dashboardRequestVO.getBatteryVoltage());
								pstmt1.setFloat(9, dashboardRequestVO.getTariffAmount());
								pstmt1.setFloat(10, dashboardRequestVO.getEmergencyCredit());
								pstmt1.setInt(11, dashboardRequestVO.getMeterType());
								pstmt1.setInt(12, dashboardRequestVO.getValveStatus());
								pstmt1.setInt(13, 0);// Balance Pending
								pstmt1.setInt(14, dashboardRequestVO.getTamperStatus());
								pstmt1.setInt(15, dashboardRequestVO.getLowBattery());
								pstmt1.setInt(16, dashboardRequestVO.getVacation());
								pstmt1.setInt(17, dashboardRequestVO.getMinutes());
								pstmt1.setString(18, dashboardRequestVO.getTimeStamp());
								pstmt1.setString(19, dashboardRequestVO.getMeterID());
								
							} else {
								
									pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, LowBattery, Vacation, MeterSerialNumber, CRNNumber, Minutes, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
									pstmt1.setInt(1, rs2.getInt("ReadingID"));
									pstmt1.setString(2, dashboardRequestVO.getMeterID());
									pstmt1.setFloat(3, dashboardRequestVO.getReading());
									pstmt1.setFloat(4, dashboardRequestVO.getBalance());
									pstmt1.setInt(5, rs.getInt("CommunityID"));
									pstmt1.setInt(6, rs.getInt("BlockID"));
									pstmt1.setInt(7, rs.getInt("CustomerID"));
									pstmt1.setFloat(8, dashboardRequestVO.getBatteryVoltage());
									pstmt1.setFloat(9, dashboardRequestVO.getTariffAmount());
									pstmt1.setFloat(10, dashboardRequestVO.getEmergencyCredit());
									pstmt1.setInt(11, dashboardRequestVO.getMeterType());
									pstmt1.setInt(12, dashboardRequestVO.getValveStatus());
									pstmt1.setInt(13, 0);// Balance Pending
									pstmt1.setInt(14, dashboardRequestVO.getTamperStatus());
									pstmt1.setInt(15, dashboardRequestVO.getLowBattery());
									pstmt1.setInt(16, dashboardRequestVO.getVacation());
									pstmt1.setString(17, rs.getString("MeterSerialNumber"));
									pstmt1.setString(18, rs.getString("CRNNumber"));
									pstmt1.setInt(19, dashboardRequestVO.getMinutes());
									pstmt1.setString(20, dashboardRequestVO.getTimeStamp());
									
							}
							
						}
						
						pstmt1.executeUpdate();
						result = "Success";
					}
					
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String sendalertsms(int i, String message, String meterID) {
		// TODO Auto-generated method stub
		ExtraMethodsDAO extraMethodsDao = new ExtraMethodsDAO();
		SMSRequestVO smsRequestVO = new SMSRequestVO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String result = "Failure";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT cmd.MobileNumber AS customerMobileNumber, b.MobileNumber AS adminMobileNumber, cmd.HouseNumber, cmd.CRNNumber FROM customermeterdetails as cmd LEFT JOIN block AS b ON b.BlockID = cmd.BlockID WHERE cmd.MeterID = '"+ meterID+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				smsRequestVO.setToMobileNumber(i == 1 ? rs.getString("customerMobileNumber") : rs.getString("adminMobileNumber"));
				message = message.replaceAll("<CRN>", rs.getString("CRNNumber"));
				smsRequestVO.setMessage(message.replaceAll("<house>", rs.getString("HouseNumber")));
				
				result = extraMethodsDao.sendsms(smsRequestVO).toString();				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private String sendalertmail(String subject, String message, String meterID) {
		// TODO Auto-generated method stub
		
		ExtraMethodsDAO extraMethodsDao = new ExtraMethodsDAO();
		MailRequestVO mailRequestVO = new MailRequestVO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String result = "Failure";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT cmd.Email AS customerEmail, b.Email AS adminEmail, cmd.CRNNumber, cmd.HouseNumber FROM customermeterdetails as cmd LEFT JOIN block AS b ON b.BlockID = cmd.BlockID WHERE cmd.MeterID = '"+ meterID+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mailRequestVO.setToEmail(subject.equalsIgnoreCase("Low Balance Alert!!!") ? rs.getString("customerEmail") : rs.getString("adminEmail"));
				mailRequestVO.setSubject(subject);
				message = message.replaceAll("<CRN>", rs.getString("CRNNumber"));
				mailRequestVO.setMessage("Dear Customer, \n \n" + message.replaceAll("<house>", rs.getString("HouseNumber")));
				
				result = extraMethodsDao.sendmail(mailRequestVO);				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private static int hexDecimal(String meterStatusbyte) {
		// TODO Auto-generated method stub
		String digits = "0123456789ABCDEF";
		int val = 0;
		for (int i = 0; i < meterStatusbyte.length(); i++) {
			char c = meterStatusbyte.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}

		return val;
	}

}
