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
import com.hanbit.PAYGTL_LORA_BLE.response.vo.GraphResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.HomeResponseVO;
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

	public List<DashboardResponseVO> getDashboarddetails(int roleid, String id, int filter)
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
				lowBatteryVoltage = rs1.getDouble("LowBatteryVoltage");
				perUnitValue = rs1.getFloat("PerUnitValue");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName,cmd.CRNNumber, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.TariffAmount, dbl.SolonideStatus, dbl.TamperDetect, dbl.Vacation, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
					"FROM displaybalancelog AS dbl LEFT JOIN community AS c ON c.communityID = dbl.CommunityID LEFT JOIN block AS b ON b.BlockID = dbl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = dbl.CRNNumber WHERE 1=1 <change>";
		
			query = query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "" : (roleid == 2 || roleid == 5) ? "AND dbl.BlockID = "+id : (roleid == 3) ? "AND dbl.CRNNumber = '"+id+"'":"");
			
			StringBuilder stringBuilder = new StringBuilder(query);
			if(roleid !=3 && filter != 0) {
				
//				1 = valve open(active), 2 = valve close(inactive) 3 = communicating(live), 4 = non-communicating(non-live) 5 = low battery 6 = emergency credit
				
				stringBuilder.append((filter == 1 || filter == 2) ? " AND dbl.SolonideStatus = "+ (filter == 1 ? 0 : 1) : (filter == 3 || filter == 4) ? (filter == 3 ? " AND dbl.IotTimeStamp >= (NOW() - INTERVAL (SELECT NoAMRInterval/(24*60) FROM alertsettings) DAY) " : " AND dbl.IotTimeStamp <= (NOW() - INTERVAL (SELECT NoAMRInterval/(24*60) FROM alertsettings) DAY) " ) :  (filter == 5) ? " AND dbl.BatteryVoltage < "+ lowBatteryVoltage : (filter == 6) ? " AND dbl.Balance <= 0" : "");
				
			}
			stringBuilder.append(" ORDER BY dbl.IoTTimeStamp DESC");
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
				// send tariff id/TariffName after fetching from db
				dashboardvo.setTariff((rs.getFloat("TariffAmount")));
				dashboardvo.setCRNNumber(rs.getString("CRNNumber"));
				dashboardvo.setReading(rs.getFloat("Reading"));
				dashboardvo.setConsumption((dashboardvo.getReading() * perUnitValue));
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				dashboardvo.setValveStatus((rs.getInt("SolonideStatus") == 0) ? "OPEN" : (rs.getInt("SolonideStatus") == 1) ? "CLOSED" : "");
				dashboardvo.setValveStatusColor((rs.getInt("SolonideStatus") == 0) ? "GREEN" : (rs.getInt("SolonideStatus") == 1) ? "RED" : "");
				// change the full battery voltage value accordingly
				dashboardvo.setBattery((int)((rs.getFloat("BatteryVoltage"))*(100/3.5) > 100 ? 100 : (rs.getFloat("BatteryVoltage"))*(100/3.5)));
				dashboardvo.setBatteryColor((rs.getFloat("BatteryVoltage") < lowBatteryVoltage) ? "RED" : "GREEN");
				dashboardvo.setTamperStatus((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "MAG" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" :"NO");
				dashboardvo.setTamperColor((rs.getInt("TamperDetect") == 0) ? "GREEN" : "RED");
				dashboardvo.setVacationStatus(rs.getInt("Vacation") == 1 ? "YES" : "NO");
				dashboardvo.setVacationColor(rs.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
				dashboardvo.setTimeStamp(ExtraMethodsDAO.datetimeformatter(rs.getString("IoTTimeStamp")));
				
				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());

				if(minutes > noAMRInterval) {
					nonCommunicating++;
					dashboardvo.setDateColor("RED");
					dashboardvo.setCommunicationStatus("NO");
				}else if(minutes > 1440 || minutes < noAMRInterval) {
					dashboardvo.setDateColor("ORANGE");
					dashboardvo.setCommunicationStatus("YES");
				} else {
					dashboardvo.setDateColor("GREEN");
					dashboardvo.setCommunicationStatus("YES");
				}
				dashboardvo.setNonCommunicating(nonCommunicating);
				
				if(roleid==3) {
					PreparedStatement pstmt2 = con.prepareStatement("SELECT Amount, TransactionDate FROM topup WHERE CRNNumber = '"+rs.getString("CRNNumber")+"' AND STATUS BETWEEN 0 AND 2 ORDER BY TransactionID DESC LIMIT 0,1") ;
					ResultSet rs2 = pstmt2.executeQuery();
					if(rs2.next()) {
						dashboardvo.setLastTopupAmount(rs2.getInt("Amount"));
						dashboardvo.setLastRechargeDate(ExtraMethodsDAO.datetimeformatter(rs2.getString("TransactionDate")));
					}
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
					stringBuilder.append(" AND dbl.BatteryVoltage BETWEEN " + (filtervo.getBatteryVoltageFrom() != 0 ? ((filtervo.getBatteryVoltageFrom()*3.5)/100) : 0) + " AND " + (filtervo.getBatteryVoltageTo() != 0 ? ((filtervo.getBatteryVoltageTo()*3.5)/100) : 10));
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
				dashboardvo.setBattery((int)((rs.getFloat("BatteryVoltage"))*(100/3.5) > 100 ? 100 : (rs.getFloat("BatteryVoltage"))*(100/3.5)));
				dashboardvo.setBatteryColor((rs.getFloat("BatteryVoltage") < lowBatteryVoltage) ? "RED" : "GREEN");
				dashboardvo.setTamperStatus((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "MAG" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" :"NO");
				dashboardvo.setTamperColor((rs.getInt("TamperDetect") == 0) ? "GREEN" : "RED");
				dashboardvo.setVacationStatus(rs.getInt("Vacation") == 1 ? "YES" : "NO");
				dashboardvo.setVacationColor(rs.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
				dashboardvo.setTimeStamp(ExtraMethodsDAO.datetimeformatter(rs.getString("IoTTimeStamp")));
				
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
	
	public HomeResponseVO getHomeDashboardDetails(int roleid, String id)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		
		HomeResponseVO homeResponseVO = null;
		int noAMRInterval = 0;
		double lowBatteryVoltage = 0.0;
		int nonLive = 0;
		int live = 0;
		int active = 0;
		int inActive = 0;
		int emergency = 0;
		int lowBattery = 0;
		int amr = 0;
		int consumption = 0;
		
		try {
			con = getConnection();
			homeResponseVO = new HomeResponseVO();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getFloat("LowBatteryVoltage");
			}
			
			String query = "SELECT DISTINCT cmd.CRNNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.SolonideStatus, dbl.TamperDetect, dbl.Minutes, dbl.IoTTimeStamp, dbl.LogDate FROM displaybalancelog AS dbl LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = dbl.CRNNumber <change>";

			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 2 || roleid == 5) ? "WHERE dbl.BlockID = "+id+ " ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 3) ? "WHERE dbl.CRNNumber = '"+id+"'":""));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				amr++;

				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());
				
				if(minutes > noAMRInterval) { nonLive++; } else { live++; }
				if(rs.getInt("SolonideStatus") == 0) { active++; } else { inActive++; }
				if(rs.getInt("Minutes") != 0) { emergency++; }
				if(rs.getFloat("BatteryVoltage") < lowBatteryVoltage) { lowBattery++; }
				
			}
			
			homeResponseVO.setLive(live);
			homeResponseVO.setLivePercentage((live*100/amr));
			homeResponseVO.setNonLive(nonLive);
			homeResponseVO.setNonLivePercentage((nonLive*100/amr));
			homeResponseVO.setActive(active);
			homeResponseVO.setActivePercentage((active*100/amr));
			homeResponseVO.setInActive(inActive);
			homeResponseVO.setInActivePercentage((inActive*100/amr));
			homeResponseVO.setEmergency(emergency);
			homeResponseVO.setEmergencyPercentage((emergency*100/amr));
			homeResponseVO.setLowBattery(lowBattery);
			homeResponseVO.setLowBatteryPercentage((lowBattery*100/amr));
			homeResponseVO.setAmr(amr);
			homeResponseVO.setAmrPercentage(100);
			
			String query1 = "SELECT SUM(Amount) AS topup FROM topup WHERE TransactionDate BETWEEN CONCAT(CURDATE(), ' 00:00:00') AND CONCAT(CURDATE(), ' 23:59:59') <change>";
			pstmt2 = con.prepareStatement(query1.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "AND BlockID = "+id :""));
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) { homeResponseVO.setTopup(rs2.getInt("topup")); } else { homeResponseVO.setTopup(0); }
			
			String query2 = "SELECT MeterID FROM customermeterdetails <change>";
			pstmt3 = con.prepareStatement(query2.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "WHERE BlockID = "+id + " ORDER BY CustomerID ASC" :""));
			rs3 = pstmt3.executeQuery();
			while(rs3.next()) {
				
				String query3 = "SELECT ABS((SELECT Reading FROM balancelog WHERE MeterID = ? AND IoTTImeStamp BETWEEN (NOW() - INTERVAL 1 DAY) AND NOW() ORDER BY ReadingID DESC LIMIT 0,1)\r\n" + 
						"- (SELECT Reading FROM balancelog WHERE MeterID = ? AND IoTTImeStamp BETWEEN (NOW() - INTERVAL 1 DAY) AND NOW() ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";

				pstmt4 = con.prepareStatement(query3);
				pstmt4.setString(1, rs3.getString("MeterID"));
				pstmt4.setString(2, rs3.getString("MeterID"));
				rs4 = pstmt4.executeQuery();
				if(rs4.next()) {
					consumption = rs4.getInt("Units") + consumption;
				}
			}
			
			homeResponseVO.setConsumption(consumption);
			
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return homeResponseVO;
	}
	
	public GraphResponseVO getGraphDashboardDetails(int year, int month, String CRNNumber) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		GraphResponseVO graphResponseVO = new GraphResponseVO();
		List<String> xAxis;
		List<Integer> yAxis;
		
		try {
			con = getConnection();
			xAxis = new LinkedList<String>();
			yAxis = new LinkedList<Integer>();
			
			if(year == 0 && month == 0) {
				
				for(int i = 30; i>0; i-- ) {
					
					String query = "SELECT ((SELECT Reading FROM balancelog WHERE CRNNumber = ? AND IoTTImeStamp BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
										 "- (SELECT Reading FROM balancelog WHERE CRNNumber = ? AND IoTTImeStamp BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
					
					pstmt = con.prepareStatement(query.replaceAll("<day>", ""+i));
					pstmt.setString(1, CRNNumber);
					pstmt.setString(2, CRNNumber);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						xAxis.add(rs.getString("consumptiondate"));
						yAxis.add(rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						
						}
				}
			} else if (year != 0 &&  month == 0) {
				
				for(int i = 1; i<=12; i++ ) {
					
					String query = "SELECT ((SELECT Reading FROM balancelog WHERE CRNNumber = ? AND YEAR(IotTimeStamp) = ? AND MONTH(IotTimeStamp) = <month> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
									      "-(SELECT Reading FROM balancelog WHERE CRNNumber = ? AND YEAR(IotTimeStamp) = ? AND MONTH(IotTimeStamp) = <month> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
					
					pstmt = con.prepareStatement(query.replaceAll("<month>", ""+i));
					pstmt.setString(1, CRNNumber);
					pstmt.setInt(2, year);
					pstmt.setString(3, CRNNumber);
					pstmt.setInt(4, year);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						xAxis.add(i==1 ? "JAN" : i==2 ? "FEB" : i==3 ? "MAR" : i==4 ? "APR" : i==5 ? "MAY" : i==6 ? "JUN" : i==7 ? "JUL" : i==8 ? "AUG" : i==9 ? "SEP" : i==10 ? "OCT" : i==11 ? "NOV" : i==12 ? "DEC" : "");
						yAxis.add(rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						
						}
				}
				
			} else if(year != 0 && month != 0) {
				
				int j = (month == 2 ? 28 : (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ? 31 : 30);
				
				for(int i = 1; i <= j ; i++) {
					
					String query = "SELECT ((SELECT Reading FROM balancelog WHERE CRNNumber = ? AND YEAR(IotTimeStamp) = ? AND MONTH(IotTimeStamp) = ? AND DAY(IotTimeStamp) = <day> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
										 "- (SELECT Reading FROM balancelog WHERE CRNNumber = ? AND YEAR(IotTimeStamp) = ? AND MONTH(IotTimeStamp) = ? AND DAY(IotTimeStamp) = <day> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
					
					pstmt = con.prepareStatement(query.replaceAll("<day>", ""+i));
					pstmt.setString(1, CRNNumber);
					pstmt.setInt(2, year);
					pstmt.setInt(3, month);
					pstmt.setString(4, CRNNumber);
					pstmt.setInt(5, year);
					pstmt.setInt(6, month);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						xAxis.add(Integer.toString(i));
						yAxis.add(rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						
						}
					}
				}
			
			graphResponseVO.setXAxis(xAxis);
			graphResponseVO.setYAxis(yAxis);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return graphResponseVO;
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
					// 0 = no tamper 1 = magnetic; 2 = door open
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
						alertMessage = "The Battery in Meter with CRN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is low.";
						
//						sendalertmail("Low Battery Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
//						sendalertsms(0, alertMessage, dashboardRequestVO.getMeterID());
					} 
					
					if (dashboardRequestVO.getTamperStatus() == 1 || dashboardRequestVO.getTamperStatus() == 2) {
						alertMessage = "There is a <tamper> Tamper in Meter with CRN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block>.";
						alertMessage = alertMessage.replaceAll("<tamper>", dashboardRequestVO.getTamperStatus() == 2 ? "Door Open" : dashboardRequestVO.getTamperStatus() == 1 ? "Magnetic" : "");
//						sendalertmail("Tamper Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
//						sendalertsms(0, alertMessage, dashboardRequestVO.getMeterID());
					}

					// change low balance alert after discussion with team
					
					if(dashboardRequestVO.getBalance() < (dashboardRequestVO.getTariffAmount() * 2)) {
						alertMessage = "Balance in your Meter with CRN: <CRN> is low. Please Recharge again.";
						
//						sendalertmail("Low Balance Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
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
			
			pstmt = con.prepareStatement("SELECT cmd.MobileNumber AS customerMobileNumber, b.MobileNumber AS adminMobileNumber, cmd.HouseNumber, cmd.CRNNumber, b.BlockName as BlockName, c.CommunityName as CommunityName FROM customermeterdetails as cmd LEFT JOIN block AS b ON b.BlockID = cmd.BlockID LEFT JOIN community AS c ON c.CommunityID = cmd.CommunityID WHERE cmd.MeterID = '"+ meterID+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				smsRequestVO.setToMobileNumber(i == 1 ? rs.getString("customerMobileNumber") : rs.getString("adminMobileNumber"));
				message = message.replaceAll("<CRN>", rs.getString("CRNNumber"));
				if(i!=1) {
					message = message.replaceAll("<community>", rs.getString("CommunityName"));
					message = message.replaceAll("<block>", rs.getString("BlockName"));
					message = message.replaceAll("<house>", rs.getString("HouseNumber"));	
				}
				
				smsRequestVO.setMessage(message);
				
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
			
			pstmt = con.prepareStatement("SELECT cmd.Email AS customerEmail, b.Email AS adminEmail, b.BlockName as BlockName, c.CommunityName as CommunityName, cmd.CRNNumber, cmd.HouseNumber FROM customermeterdetails as cmd LEFT JOIN block AS b ON b.BlockID = cmd.BlockID LEFT JOIN community AS c ON c.CommunityID = cmd.CommunityID WHERE cmd.MeterID = '"+ meterID+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mailRequestVO.setToEmail(subject.equalsIgnoreCase("Low Balance Alert!!!") ? rs.getString("customerEmail") : rs.getString("adminEmail"));
				mailRequestVO.setSubject(subject);
				message = message.replaceAll("<CRN>", rs.getString("CRNNumber"));
				if(!subject.equalsIgnoreCase("Low Balance Alert!!!")) {
					message = message.replaceAll("<community>", rs.getString("CommunityName"));
					message = message.replaceAll("<block>", rs.getString("BlockName"));
					message = message.replaceAll("<house>", rs.getString("HouseNumber"));	
				}
				
				mailRequestVO.setMessage(subject.equalsIgnoreCase("Low Balance Alert!!!") ? "Dear Customer, \n \n" : "Dear Admin, \n \n");
				
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
