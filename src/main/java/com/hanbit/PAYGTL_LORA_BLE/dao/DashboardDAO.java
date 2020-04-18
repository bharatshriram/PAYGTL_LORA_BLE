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

import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.DashboardInsertVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.DashboardRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TataResponseVO;
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

	public List<DashboardResponseVO> getDashboarddetails(int roleid, int id)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DashboardResponseVO> dashboard_list = null;
		DashboardResponseVO dashboardvo = null;
		int noAMRInterval = 0;
		double lowBatteryVoltage = 0.0;
		int timeOut = 0;
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getFloat("LowBatteryVoltage");
				timeOut = rs1.getInt("TimeOut");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.TariffAmount, dbl.AlarmCredit, dbl.SolonideStatus, dbl.TamperDetect, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
					"FROM displaybalancelog AS dbl LEFT JOIN community AS c ON c.communityID = dbl.CommunityID LEFT JOIN block AS b ON b.BlockID = dbl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerID = dbl.CustomerID <change>";
		
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 2 || roleid == 5) ? "WHERE dbl.BlockID = "+id+ " ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 3) ? "WHERE dbl.CustomerID = "+id:""));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dashboardvo = new DashboardResponseVO();
				dashboardvo.setCommunityName(rs.getString("CommunityName"));
				dashboardvo.setBlockName(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setFirstName(rs.getString("FirstName"));
				dashboardvo.setLastName(rs.getString("LastName"));
				dashboardvo.setMeterID(rs.getString("MeterID"));
				dashboardvo.setTariff((rs.getFloat("TariffAmount")));
				// send tariff id/TariffName after fetching from db
				dashboardvo.setReading(rs.getFloat("Reading"));
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				dashboardvo.setAlarmCredit(rs.getFloat("AlarmCredit"));
				
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
				
				long difference = currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime(); 
				long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);

				if(minutes > noAMRInterval) {
					dashboardvo.setDateColor("RED");
				}else {
					dashboardvo.setDateColor("GREEN");
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

	public ResponseVO postDashboarddetails(String json) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		int tamper = 0;
		int lowbattery = 0;
		PreparedStatement pstmtch = null;
		ResultSet rsch = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResponseVO responsevo = new ResponseVO();
		String iot_Timestamp = "";

		try {

			con = getConnection();

			Gson gson = new Gson();
			
			TataResponseVO requestvo = new TataResponseVO();
			
			JSONObject jsonObj = new JSONObject(json);
			
			requestvo = gson.fromJson(jsonObj.getJSONObject("m2m:cin").getString("con"), TataResponseVO.class);

				// String guid="CgAAAED/AQAAAAAAAAAAARc=";
			
				byte[] decoded = Base64.getDecoder().decode(requestvo.getPayloads_ul().getDataFrame());

				String StartByte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
						.substring(0, 2);

				if (StartByte.equalsIgnoreCase("0A")) {

					// 0A 00 00 00 00 42 29 01 00 00 00 00 00 00 00 00 00 00 00 00 00 17

					String DeviceId = requestvo.getPayloads_ul().getDeveui();

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

					int meterReading = DashboardDAO.hexDecimal(meterReadingbyte);

					if (meterStatusbyte.equalsIgnoreCase("40") || meterStatusbyte.equalsIgnoreCase("00")) {
						tamper = 0;
						lowbattery = 0;
					} else if (meterStatusbyte.equalsIgnoreCase("42")) {
						tamper = 0;
						lowbattery = 1;
					} else if (meterStatusbyte.equalsIgnoreCase("44")) {
						tamper = 1;
						lowbattery = 0;
					}

					int batteryStatus = DashboardDAO.hexDecimal(batteryStatusbyte);

					float batterylevel = (float) ((batteryStatus * 3.6) / 256);

					int meterType = DashboardDAO.hexDecimal(meterTypebyte);

					Long i = Long.parseLong(creditbyte, 16);
					Float amountAvailable = Float.intBitsToFloat(i.intValue());

					Long j = Long.parseLong(tariffbyte, 16);
					Float tariffAvailable = Float.intBitsToFloat(j.intValue());

					Long k = Long.parseLong(emergencyCreditbyte, 16);
					Float emergencyCreditAvailable = Float.intBitsToFloat(k.intValue());

					int valveStatus = DashboardDAO.hexDecimal(valveStatusbyte);

					pstmtch = con.prepareStatement("SELECT IoTTimeStamp, MeterID FROM balanceLog WHERE MeterID = ? order by IoTTimeStamp DESC LIMIT 0,1");
					pstmtch.setString(1, DeviceId);
					rsch = pstmtch.executeQuery();

					if (rsch.next()) {

						iot_Timestamp = rsch.getString("IoTTimeStamp");

					}

					Instant instant = Instant.parse(requestvo.getPayloads_ul().getTimestamp());
			        ZoneId.of("Asia/Kolkata");
			        LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Kolkata"));
			        requestvo.getPayloads_ul().setTimestamp(datetime.toString().replaceAll("T", " ").substring(0, 19));
					
					if (!requestvo.getPayloads_ul().getTimestamp().equalsIgnoreCase(iot_Timestamp)) {
						
						
						PreparedStatement pstmt2 = con.prepareStatement("SELECT CommunityID, BlockID, CustomerID, TariffID from customermeterdetails WHERE MeterID = ?");
						pstmt2.setString(1, DeviceId);
						ResultSet rs = pstmt2.executeQuery();
						if(rs.next()) {
							
							String sql4 = "INSERT INTO balancelog (MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, AlarmCredit, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, LowBattery, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							pstmt = con.prepareStatement(sql4);

							pstmt.setString(1, DeviceId);
							pstmt.setInt(2, meterReading);
							pstmt.setFloat(3, amountAvailable);// Balance Pending
							pstmt.setInt(4, rs.getInt("CommunityID"));
							pstmt.setInt(5, rs.getInt("BlockID"));
							pstmt.setInt(6, rs.getInt("CustomerID"));
							pstmt.setFloat(7, batterylevel);
							pstmt.setFloat(8, tariffAvailable);
							pstmt.setInt(9, 0);
							pstmt.setFloat(10, emergencyCreditAvailable);
							pstmt.setInt(11, meterType);
							pstmt.setInt(12, valveStatus);
							pstmt.setInt(13, 0);// Balance Pending
							pstmt.setInt(14, tamper);
							pstmt.setInt(15, lowbattery);
							pstmt.setString(16, requestvo.getPayloads_ul().getTimestamp());

							if (pstmt.executeUpdate() > 0) {
								
								responsevo.setResult("Success");
								
								PreparedStatement pstmt4 = con.prepareStatement("(SELECT MAX(ReadingID) as ReadingID FROM balancelog WHERE MeterID = ?)");
								pstmt4.setString(1, DeviceId);
								ResultSet rs2 = pstmt4.executeQuery();
								
								if(rs2.next()) {
									
									PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MeterID = ? ");
									pstmt3.setString(1, DeviceId);
									ResultSet rs1 = pstmt3.executeQuery();
									
									if(rs1.next()) {
										
										pstmt1 = con.prepareStatement("UPDATE displaybalancelog SET MainBalanceLogID = ?, MeterID = ?, Reading = ?, Balance = ?, CommunityID = ?, BlockID = ?, CustomerID = ?, BatteryVoltage = ?, TariffAmount = ?, AlarmCredit = ?, EmergencyCredit = ?, MeterType = ?, SolonideStatus = ?, CreditStatus = ?, TamperDetect = ?, LowBattery = ?, IoTTimeStamp = ?, LogDate = NOW() WHERE MeterID = ? ");
										pstmt1.setInt(1, rs2.getInt("ReadingID"));
										pstmt1.setString(2, DeviceId);
										pstmt1.setInt(3, meterReading);
										pstmt1.setFloat(4, amountAvailable);
										pstmt1.setInt(5, rs.getInt("CommunityID"));
										pstmt1.setInt(6, rs.getInt("BlockID"));
										pstmt1.setInt(7, rs.getInt("CustomerID"));
										pstmt1.setFloat(8, batterylevel);
										pstmt1.setFloat(9, tariffAvailable);
										pstmt1.setInt(10, 0);
										pstmt1.setFloat(11, emergencyCreditAvailable);
										pstmt1.setInt(12, meterType);
										pstmt1.setInt(13, valveStatus);
										pstmt1.setInt(14, 0);// Balance Pending
										pstmt1.setInt(15, tamper);
										pstmt1.setInt(16, lowbattery);
										pstmt1.setString(17, requestvo.getPayloads_ul().getTimestamp());
										pstmt1.setString(18, DeviceId);
										
									} else {
										
											pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, AlarmCredit, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, LowBattery, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
											pstmt1.setInt(1, rs2.getInt("ReadingID"));
											pstmt1.setString(2, DeviceId);
											pstmt1.setInt(3, meterReading);
											pstmt1.setFloat(4, amountAvailable);
											pstmt1.setInt(5, rs.getInt("CommunityID"));
											pstmt1.setInt(6, rs.getInt("BlockID"));
											pstmt1.setInt(7, rs.getInt("CustomerID"));
											pstmt1.setFloat(8, batterylevel);
											pstmt1.setFloat(9, tariffAvailable);
											pstmt1.setInt(10, 0);
											pstmt1.setFloat(11, emergencyCreditAvailable);
											pstmt1.setInt(12, meterType);
											pstmt1.setInt(13, valveStatus);
											pstmt1.setInt(14, 0);// Balance Pending
											pstmt1.setInt(15, tamper);
											pstmt1.setInt(16, lowbattery);
											pstmt1.setString(17, requestvo.getPayloads_ul().getTimestamp());
											
									}
									
								}
								
								pstmt1.executeUpdate();
							}
							
						}
						
					} else {

						responsevo.setResult("No Data to update");
					}

				}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			// pstmt.close();
			con.close();

		}

		return responsevo;
	}
	
	public ResponseVO datafrommobile(DashboardRequestVO dashboardRequestVO) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	public static String insertdashboard (DashboardInsertVO dashboardInsertVO) {
		
		Connection con = null;
		ResultSet rsch = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String iot_Timestamp = "";
		String result = "Failure";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT IoTTimeStamp, MeterID FROM balanceLog WHERE MeterID = ? order by IoTTimeStamp DESC LIMIT 0,1");
			pstmt.setString(1, dashboardInsertVO.getMeterID());
			rsch = pstmt.executeQuery();

			if (rsch.next()) {

				iot_Timestamp =  rsch.getString("IoTTimeStamp");

			}

			Instant instant = Instant.parse(dashboardInsertVO.getIoTTimeStamp());
	        ZoneId.of("Asia/Kolkata");
	        LocalDateTime datetime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Kolkata"));
	        dashboardInsertVO.setIoTTimeStamp(datetime.toString().replaceAll("T", " ").substring(0, 19));
			
			if (!dashboardInsertVO.getIoTTimeStamp().equalsIgnoreCase(iot_Timestamp)) {
				
				
				PreparedStatement pstmt2 = con.prepareStatement("SELECT CommunityID, BlockID, CustomerID, TariffID from customermeterdetails WHERE MeterID = ?");
				pstmt2.setString(1, dashboardInsertVO.getMeterID());
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next()) {
					
					String sql4 = "INSERT INTO balancelog (MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, AlarmCredit, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, LowBattery, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					pstmt = con.prepareStatement(sql4);

					pstmt.setString(1, dashboardInsertVO.getMeterID());
					pstmt.setFloat(2, dashboardInsertVO.getReading());
					pstmt.setFloat(3, dashboardInsertVO.getBalance());// Balance Pending
					pstmt.setInt(4, dashboardInsertVO.getCommunityID());
					pstmt.setInt(5, dashboardInsertVO.getBlockID());
					pstmt.setInt(6, dashboardInsertVO.getCutomerID());
					pstmt.setFloat(7, dashboardInsertVO.getBatteryVoltage());
					pstmt.setFloat(8, dashboardInsertVO.getTariff());
					pstmt.setInt(9, 0);
					pstmt.setFloat(10, dashboardInsertVO.getEmergencyCredit());
					pstmt.setInt(11, dashboardInsertVO.getMeterType());
					pstmt.setInt(12, dashboardInsertVO.getValveStatus());
					pstmt.setInt(13, 0);// Balance Pending
					pstmt.setInt(14, dashboardInsertVO.getTamper());
					pstmt.setInt(15, dashboardInsertVO.getLowBatteryVoltage());
					pstmt.setString(16, dashboardInsertVO.getIoTTimeStamp());

					if (pstmt.executeUpdate() > 0) {
						
						result = "Success";
						
						PreparedStatement pstmt4 = con.prepareStatement("(SELECT MAX(ReadingID) as ReadingID FROM balancelog WHERE MeterID = ?)");
						pstmt4.setString(1, dashboardInsertVO.getMeterID());
						ResultSet rs2 = pstmt4.executeQuery();
						
						if(rs2.next()) {
							
							PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MeterID = ? ");
							pstmt3.setString(1, dashboardInsertVO.getMeterID());
							ResultSet rs1 = pstmt3.executeQuery();
							
							if(rs1.next()) {
								
								pstmt1 = con.prepareStatement("UPDATE displaybalancelog SET MainBalanceLogID = ?, MeterID = ?, Reading = ?, Balance = ?, CommunityID = ?, BlockID = ?, CustomerID = ?, BatteryVoltage = ?, TariffAmount = ?, AlarmCredit = ?, EmergencyCredit = ?, MeterType = ?, SolonideStatus = ?, CreditStatus = ?, TamperDetect = ?, LowBattery = ?, IoTTimeStamp = ?, LogDate = NOW() WHERE MeterID = ? ");
								pstmt1.setInt(1, rs2.getInt("ReadingID"));
								pstmt1.setString(2, dashboardInsertVO.getMeterID());
								pstmt1.setFloat(3, dashboardInsertVO.getReading());
								pstmt1.setFloat(4, dashboardInsertVO.getBalance());
								pstmt1.setInt(5, dashboardInsertVO.getCommunityID());
								pstmt1.setInt(6, dashboardInsertVO.getBlockID());
								pstmt1.setInt(7, dashboardInsertVO.getCutomerID());
								pstmt1.setFloat(8, dashboardInsertVO.getBatteryVoltage());
								pstmt1.setFloat(9, dashboardInsertVO.getTariff());
								pstmt1.setInt(10, 0);
								pstmt1.setFloat(11, dashboardInsertVO.getEmergencyCredit());
								pstmt1.setInt(12, dashboardInsertVO.getMeterType());
								pstmt1.setInt(13, dashboardInsertVO.getValveStatus());
								pstmt1.setInt(14, 0);// Balance Pending
								pstmt1.setInt(15, dashboardInsertVO.getTamper());
								pstmt1.setInt(16, dashboardInsertVO.getLowBatteryVoltage());
								pstmt1.setString(17, dashboardInsertVO.getIoTTimeStamp());
								pstmt1.setString(18, dashboardInsertVO.getMeterID());
								
							} else {
								
									pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, AlarmCredit, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, LowBattery, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
									pstmt1.setInt(1, rs2.getInt("ReadingID"));
									pstmt1.setString(2, dashboardInsertVO.getMeterID());
									pstmt1.setFloat(3, dashboardInsertVO.getReading());
									pstmt1.setFloat(4, dashboardInsertVO.getBalance());
									pstmt1.setInt(5, dashboardInsertVO.getCommunityID());
									pstmt1.setInt(6, dashboardInsertVO.getBlockID());
									pstmt1.setInt(7, dashboardInsertVO.getCutomerID());
									pstmt1.setFloat(8, dashboardInsertVO.getBatteryVoltage());
									pstmt1.setFloat(9, dashboardInsertVO.getTariff());
									pstmt1.setInt(10, 0);
									pstmt1.setFloat(11, dashboardInsertVO.getEmergencyCredit());
									pstmt1.setInt(12, dashboardInsertVO.getMeterType());
									pstmt1.setInt(13, dashboardInsertVO.getValveStatus());
									pstmt1.setInt(14, 0);// Balance Pending
									pstmt1.setInt(15, dashboardInsertVO.getTamper());
									pstmt1.setInt(16, dashboardInsertVO.getLowBatteryVoltage());
									pstmt1.setString(17, dashboardInsertVO.getIoTTimeStamp());
									
							}
							
						}
						
						pstmt1.executeUpdate();
					}
					
				}
				
			} else {

				result = "No Data to update";
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
	
}
