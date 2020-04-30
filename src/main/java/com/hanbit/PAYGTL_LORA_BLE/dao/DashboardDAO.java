/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.DashboardRequestVO;
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
		
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			int nonCommunicating = 0;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getFloat("LowBatteryVoltage");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName,cmd.CRNNumber, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.TariffAmount, dbl.SolonideStatus, dbl.TamperDetect, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
					"FROM displaybalancelog AS dbl LEFT JOIN community AS c ON c.communityID = dbl.CommunityID LEFT JOIN block AS b ON b.BlockID = dbl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerID = dbl.CustomerID <change>";
				query =query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 2 || roleid == 5) ? "WHERE dbl.BlockID = "+id+ " ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 3) ? "WHERE dbl.CRNNumber = '"+id+"'":"");
			
			pstmt = con.prepareStatement(query);
			System.out.println("query==>"+query);
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
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				
				if(rs.getInt("SolonideStatus") == 0) {
					dashboardvo.setValveStatus("OPEN");	
				}else {
					dashboardvo.setValveStatus("CLOSED");
				}
				dashboardvo.setBattery(rs.getString("BatteryVoltage"));
				
				if(rs.getFloat("BatteryVoltage") < lowBatteryVoltage) {
					dashboardvo.setBatteryColor("RED");
				}else {
					dashboardvo.setBatteryColor("GREEN");
				}
				if(rs.getInt("TamperDetect") == 1) {
					dashboardvo.setTamperStatus("YES");	
				}else {
					dashboardvo.setTamperStatus("NO");
				}
				
				dashboardvo.setTimeStamp(rs.getString("IoTTimeStamp"));
				
				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());
/*
				if(minutes > noAMRInterval) {
					nonCommunicating++;
					dashboardvo.setDateColor("RED");
					dashboardvo.setCommunicationStatus("NO");
				}else {
					dashboardvo.setDateColor("GREEN");
					dashboardvo.setCommunicationStatus("YES");
				}
				dashboardvo.setNonCommunicating(nonCommunicating);*/
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
					
					pstmt = con.prepareStatement("INSERT INTO balancelog (MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, LowBattery, MeterSerialNumber, CRNNumber, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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
					pstmt.setInt(12, 0);// Balance Pending
					pstmt.setInt(13, dashboardRequestVO.getTamperStatus());
					pstmt.setInt(14, dashboardRequestVO.getLowBattery());
					pstmt.setString(15, rs.getString("MeterSerialNumber"));
					pstmt.setString(16, rs.getString("CRNNumber"));
					pstmt.setString(17, dashboardRequestVO.getTimeStamp());

					if (pstmt.executeUpdate() > 0) {
						
						result = "Success";
						
						PreparedStatement pstmt4 = con.prepareStatement("(SELECT MAX(ReadingID) as ReadingID FROM balancelog WHERE MeterID = ?)");
						pstmt4.setString(1, dashboardRequestVO.getMeterID());
						ResultSet rs2 = pstmt4.executeQuery();
						
						if(rs2.next()) {
							
							PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MeterID = ? ");
							pstmt3.setString(1, dashboardRequestVO.getMeterID());
							ResultSet rs1 = pstmt3.executeQuery();
							
							if(rs1.next()) {
								
								pstmt1 = con.prepareStatement("UPDATE displaybalancelog SET MainBalanceLogID = ?, MeterID = ?, Reading = ?, Balance = ?, CommunityID = ?, BlockID = ?, CustomerID = ?, BatteryVoltage = ?, TariffAmount = ?, EmergencyCredit = ?, MeterType = ?, SolonideStatus = ?, CreditStatus = ?, TamperDetect = ?, LowBattery = ?, IoTTimeStamp = ?, LogDate = NOW() WHERE MeterID = ? ");
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
								pstmt1.setString(16, dashboardRequestVO.getTimeStamp());
								pstmt1.setString(17, dashboardRequestVO.getMeterID());
								
							} else {
								
									pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, LowBattery, MeterSerialNumber, CRNNumber, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
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
									pstmt.setString(16, rs.getString("MeterSerialNumber"));
									pstmt.setString(17, rs.getString("CRNNumber"));
									pstmt1.setString(18, dashboardRequestVO.getTimeStamp());
									
							}
							
						}
						
						pstmt1.executeUpdate();
					}
					
				}
				
		} catch (Exception e) {
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
	
	public ResponseVO postDashboarddetails(TataRequestVO tataRequestVO) throws SQLException {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO();
		DashboardRequestVO dashboardRequestVO = new DashboardRequestVO();
		String iot_Timestamp = "";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rsch = null;

		try {
			
			con = getConnection();
			
				dashboardRequestVO.setMeterID(tataRequestVO.getDevEUI());
				byte[] decoded = Base64.getDecoder().decode(tataRequestVO.getDataFrame());

				String StartByte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
						.substring(0, 2);

				if (StartByte.equalsIgnoreCase("0A")) {

					// 0A 00 00 00 00 42 29 01 00 00 00 00 00 00 00 00 00 00 00 00 00 17

//					dashboardRequestVO.setMeterID(requestvo.getPayloads_ul().getDeveui());

					String meterReadingbyte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(2, 10);

					String meterStatusbyte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(10, 12);

					String batteryStatusbyte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(12, 14);

					String meterTypebyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(14, 16);

					String creditbyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase().substring(16,
							24);

					String tariffbyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase().substring(24,
							32);

					String emergencyCreditbyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(32, 40);

					String valveStatusbyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(40, 42);

					dashboardRequestVO.setReading(DashboardDAO.hexDecimal(meterReadingbyte));

					if (meterStatusbyte.equalsIgnoreCase("40") || meterStatusbyte.equalsIgnoreCase("00")) {
						dashboardRequestVO.setTamperStatus(0);
						dashboardRequestVO.setLowBattery(0);
					} else if (meterStatusbyte.equalsIgnoreCase("42")) {
						dashboardRequestVO.setTamperStatus(0);
						dashboardRequestVO.setLowBattery(1);
					} else if (meterStatusbyte.equalsIgnoreCase("44")) {
						dashboardRequestVO.setTamperStatus(1);
						dashboardRequestVO.setLowBattery(0);
					}

					dashboardRequestVO.setBatteryVoltage((float) (((DashboardDAO.hexDecimal(batteryStatusbyte)) * 3.6) / 256));

					dashboardRequestVO.setMeterType(DashboardDAO.hexDecimal(meterTypebyte));

					Long i = Long.parseLong(creditbyte, 16);
					dashboardRequestVO.setBalance(Float.intBitsToFloat(i.intValue()));

					Long j = Long.parseLong(tariffbyte, 16);
					dashboardRequestVO.setTariffAmount(Float.intBitsToFloat(j.intValue()));

					Long k = Long.parseLong(emergencyCreditbyte, 16);
					dashboardRequestVO.setEmergencyCredit(Float.intBitsToFloat(k.intValue()));

					dashboardRequestVO.setValveStatus(DashboardDAO.hexDecimal(valveStatusbyte));
					dashboardRequestVO.setTimeStamp(tataRequestVO.getTimestamp());
				
					pstmt = con.prepareStatement("SELECT IoTTimeStamp, MeterID FROM balanceLog WHERE MeterID = ? order by IoTTimeStamp DESC LIMIT 0,1");
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
	
}
