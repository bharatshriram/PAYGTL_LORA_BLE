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
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
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
		int lowBatteryVoltage = 0;
		float perUnitValue = 0.0f;
		
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			int nonCommunicating = 0;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut, PerUnitValue FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getInt("LowBatteryVoltage");
				perUnitValue = rs1.getFloat("PerUnitValue");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName,cmd.CRNNumber, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.TariffAmount, dbl.SolonideStatus, dbl.TamperDetect, dbl.Vacation, dbl.TamperTimeStamp, dbl.DoorOpenTimeStamp, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
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
				dashboardvo.setTariff((rs.getFloat("TariffAmount")));
				dashboardvo.setCRNNumber(rs.getString("CRNNumber"));
				dashboardvo.setReading(rs.getFloat("Reading"));
				dashboardvo.setConsumption((int) (dashboardvo.getReading() * perUnitValue));
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				dashboardvo.setValveStatus((rs.getInt("SolonideStatus") == 0) ? "OPEN" : (rs.getInt("SolonideStatus") == 1) ? "CLOSED" : "");
				
				dashboardvo.setValveStatusColor((rs.getInt("SolonideStatus") == 0) ? "GREEN" : (rs.getInt("SolonideStatus") == 1) ? "RED" : "");
				
				// change the full battery voltage value accordingly
				
//				dashboardvo.setBattery((int)((rs.getFloat("BatteryVoltage"))*(100/3.5) > 100 ? 100 : (rs.getFloat("BatteryVoltage"))*(100/3.5)));
				dashboardvo.setBattery(rs.getInt("BatteryVoltage"));
				dashboardvo.setBatteryColor((rs.getInt("BatteryVoltage") < lowBatteryVoltage) ? "RED" : "GREEN");
				
				// 0 = no tamper 1 = magnetic; 2 = door open
				
				dashboardvo.setTamperStatus((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "MAG" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" : (rs.getInt("TamperDetect") == 3) ? "MAG;"+"DOOR OPEN" : "NO");
				dashboardvo.setTamperColor((rs.getInt("TamperDetect") == 0) ? "GREEN" : "RED");
				dashboardvo.setTamperTimeStamp((rs.getInt("TamperDetect") == 1) ? rs.getString("TamperTimeStamp") : (rs.getInt("TamperDetect") == 2) ? rs.getString("DoorOpenTimeStamp") : (rs.getInt("TamperDetect") == 3) ? rs.getString("TamperTimeStamp") +";"+ rs.getString("DoorOpenTimeStamp") : "---");
				dashboardvo.setVacationStatus(rs.getInt("Vacation") == 1 ? "YES" : "NO");
				dashboardvo.setVacationColor(rs.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
				dashboardvo.setTimeStamp(ExtraMethodsDAO.datetimeformatter(rs.getString("IoTTimeStamp")));
				
				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());

				if(minutes > noAMRInterval) {
					nonCommunicating++;
					dashboardvo.setDateColor("RED");
					dashboardvo.setCommunicationStatus("NO");
				}else if(minutes > 1440 && minutes < noAMRInterval) {
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
		int lowBatteryVoltage = 0;
		float perUnitValue = 0.0f;
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut, PerUnitValue FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getInt("LowBatteryVoltage");
				perUnitValue = rs1.getFloat("PerUnitValue");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName,cmd.CRNNumber, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.TariffAmount, dbl.SolonideStatus, dbl.TamperDetect, dbl.Vacation,  dbl.TamperTimeStamp, dbl.DoorOpenTimeStamp, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
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
					stringBuilder.append(" AND dbl.BatteryVoltage BETWEEN " + (filtervo.getBatteryVoltageFrom() != 0 ? (filtervo.getBatteryVoltageFrom()) : 0) + " AND " + (filtervo.getBatteryVoltageTo() != 0 ? (filtervo.getBatteryVoltageTo()) : 100));
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
				dashboardvo.setConsumption((int) (dashboardvo.getReading() * perUnitValue));
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				dashboardvo.setValveStatus((rs.getInt("SolonideStatus") == 0) ? "OPEN" : (rs.getInt("SolonideStatus") == 1) ? "CLOSED" : "");	
				dashboardvo.setValveStatusColor((rs.getInt("SolonideStatus") == 0) ? "GREEN" : (rs.getInt("SolonideStatus") == 1) ? "RED" : "");
//				dashboardvo.setBattery((int)((rs.getInt("BatteryVoltage"))*(100/3.5) > 100 ? 100 : (rs.getFloat("BatteryVoltage"))*(100/3.5)));
				dashboardvo.setBattery(rs.getInt("BatteryVoltage"));
				dashboardvo.setBatteryColor((rs.getInt("BatteryVoltage") < lowBatteryVoltage) ? "RED" : "GREEN");
				dashboardvo.setTamperStatus((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "MAG" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" : (rs.getInt("TamperDetect") == 3) ? "MAG;"+"DOOR OPEN" : "NO");
				dashboardvo.setTamperTimeStamp((rs.getInt("TamperDetect") == 1) ? rs.getString("TamperTimeStamp") : (rs.getInt("TamperDetect") == 2) ? rs.getString("DoorOpenTimeStamp") : (rs.getInt("TamperDetect") == 3) ? rs.getString("TamperTimeStamp") +";"+ rs.getString("DoorOpenTimeStamp") : "---");
				dashboardvo.setTamperColor((rs.getInt("TamperDetect") == 0) ? "GREEN" : "RED");
				dashboardvo.setTamperTimeStamp((rs.getInt("TamperDetect") == 1) ? rs.getString("TamperTimeStamp") : (rs.getInt("TamperDetect") == 2) ? rs.getString("DoorOpenTimeStamp") : "---");
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
				lowBatteryVoltage = rs1.getInt("LowBatteryVoltage");
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
				if(rs.getInt("Balance") <= 0) { emergency++; }
				if(rs.getInt("BatteryVoltage") < lowBatteryVoltage) { lowBattery++; }
				
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
			
			String query1 = "SELECT SUM(Amount) AS topup FROM topup WHERE Status = 2 AND PaymentStatus = 1 AND TransactionDate BETWEEN CONCAT(CURDATE(), ' 00:00:00') AND CONCAT(CURDATE(), ' 23:59:59') <change>";
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
						
						xAxis.add(i==1 ? "JAN-"+year : i==2 ? "FEB-"+year : i==3 ? "MAR-"+year : i==4 ? "APR-"+year : i==5 ? "MAY-"+year : i==6 ? "JUN-"+year : i==7 ? "JUL-"+year : i==8 ? "AUG-"+year : i==9 ? "SEP-"+year : i==10 ? "OCT-"+year : i==11 ? "NOV-"+year : i==12 ? "DEC-"+year : "");
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
						
						xAxis.add(Integer.toString(i)+"-"+month+"-"+year);
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

		dashboardRequestVO.setSource("Lora");
		
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
					
					// 0A 00 00 00 83 08 FF 03 00 00 00 00 41 A0 00 00 00 00 00 00 00 00 54 65 46 78 54 65 47 69 01 17  live frame
					// 01 23 45 67 89 01 23 45 67 89 01 23 45 67 89 01 23 45 67 89 01 23 45 67 89 01 23 45 67 89 01 23  count
					//               10             20             30             40             50             60   
					
					dashboardRequestVO.setReading(DashboardDAO.hexDecimal(sb.substring(2, 10)));					
					
					// Bit 7 = good, 6 = low battery, 5 = Mag, 4 = Door open, 3 = Vacation 
					// 0 = no tamper 1 = magnetic; 2 = door open; 3 = both
					
					String statusByte = new BigInteger(sb.substring(10, 12), 16).toString(2);
					statusByte = String.format("%0"+ (8 - statusByte.length() )+"d%s",0 ,statusByte); 
					
					
					
					dashboardRequestVO.setLowBattery(statusByte.charAt(6) == 49 ? 1 : 0);
					dashboardRequestVO.setVacation(statusByte.charAt(3) == 49 ? 1 : 0);
					dashboardRequestVO.setTamperStatus(statusByte.charAt(5) == 49 ? 1 : statusByte.charAt(4) == 49 ? 2 : (statusByte.charAt(4) == 49 && statusByte.charAt(5) == 49) ? 3 : 0);
//					dashboardRequestVO.setLowBattery(sb.substring(10, 12).equalsIgnoreCase("02") ? 1: 0);					
//					dashboardRequestVO.setTamperStatus(sb.substring(10, 12).equalsIgnoreCase("04") ? 1: sb.substring(10, 12).equalsIgnoreCase("08") ? 2: sb.substring(10, 12).equalsIgnoreCase("0C") ? 3: 0);
//					dashboardRequestVO.setVacation(sb.substring(10, 12).equalsIgnoreCase("10") ? 1: 0);
//					dashboardRequestVO.setBatteryVoltage((int) (((DashboardDAO.hexDecimal(sb.substring(12, 14))) * 3.6) / 256));
					dashboardRequestVO.setBatteryVoltage((int) ((DashboardDAO.hexDecimal(sb.substring(12, 14)))));
					dashboardRequestVO.setMeterType(DashboardDAO.hexDecimal(sb.substring(14, 16)));

					Long i = Long.parseLong(sb.substring(16, 24), 16);
					dashboardRequestVO.setBalance(Float.intBitsToFloat(i.intValue()));
					
					Long j = Long.parseLong(sb.substring(24, 32), 16);
					dashboardRequestVO.setTariffAmount(Float.intBitsToFloat(j.intValue()));
					
					Long k = Long.parseLong(sb.substring(32, 40), 16);
					dashboardRequestVO.setEmergencyCredit(Float.intBitsToFloat(k.intValue()));
					
					dashboardRequestVO.setMinutes(DashboardDAO.hexDecimal(sb.substring(40, 44)));
					
					int tamperTimeStamp = DashboardDAO.hexDecimal(sb.substring(44, 52));
					int doorOpenTimeStamp = DashboardDAO.hexDecimal(sb.substring(52, 60));
					
					if(tamperTimeStamp != 0) {
						
						Instant instant = Instant.ofEpochSecond(tamperTimeStamp);
						  dashboardRequestVO.setTamperTimeStamp(instant.toString().replaceAll("T", " ").substring(0, 19));
					} else {
					dashboardRequestVO.setTamperTimeStamp("");
					}
					
					if(doorOpenTimeStamp != 0) {
						Instant instant1 = Instant.ofEpochSecond(doorOpenTimeStamp);
						  dashboardRequestVO.setDoorOpenTimeStamp(instant1.toString().replaceAll("T", " ").substring(0, 19));
					} else {
					dashboardRequestVO.setDoorOpenTimeStamp("");
					} 

					dashboardRequestVO.setValveStatus(DashboardDAO.hexDecimal(sb.substring(60, 62)));
					dashboardRequestVO.setCreditStatus(dashboardRequestVO.getBalance() < (dashboardRequestVO.getTariffAmount() * ExtraConstants.LowBalanceAlertCount) ? 1 : 0);
					dashboardRequestVO.setTimeStamp(tataRequestVO.getTimestamp());
					
					pstmt = con.prepareStatement("SELECT IoTTimeStamp, MeterID FROM balancelog WHERE MeterID = ? order by IoTTimeStamp DESC LIMIT 0,1");
					pstmt.setString(1, dashboardRequestVO.getMeterID());
					rsch = pstmt.executeQuery();

					if (rsch.next()) {
						iot_Timestamp =  rsch.getString("IoTTimeStamp");
					}

					Instant instant2 = Instant.parse(dashboardRequestVO.getTimeStamp());
			        
			        LocalDateTime datetime2 = LocalDateTime.ofInstant(instant2, ZoneId.of("Asia/Kolkata"));
			        dashboardRequestVO.setTimeStamp(datetime2.toString().replaceAll("T", " ").substring(0, 19));
			        
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
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		String result = "Failure";
		String alertMessage = "";
		
		try {
			con = getConnection();
			
				PreparedStatement pstmt2 = con.prepareStatement("SELECT CommunityID, BlockID, CustomerID, TariffID, MeterSerialNumber, CRNNumber from customermeterdetails WHERE MeterID = ?");
				pstmt2.setString(1, dashboardRequestVO.getMeterID());
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next()) {
					
					pstmt = con.prepareStatement("INSERT INTO balancelog (MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, TamperTimeStamp, DoorOpenTimeStamp, LowBattery, Vacation, MeterSerialNumber, CRNNumber, Minutes, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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
					pstmt.setInt(12, dashboardRequestVO.getCreditStatus());
					pstmt.setInt(13, dashboardRequestVO.getTamperStatus());
					pstmt.setString(14, dashboardRequestVO.getTamperTimeStamp());
					pstmt.setString(15, dashboardRequestVO.getDoorOpenTimeStamp());
					pstmt.setInt(16, dashboardRequestVO.getLowBattery());
					pstmt.setInt(17, dashboardRequestVO.getVacation());
					pstmt.setString(18, rs.getString("MeterSerialNumber"));
					pstmt.setString(19, rs.getString("CRNNumber"));
					pstmt.setInt(20, dashboardRequestVO.getMinutes());
					pstmt.setString(21, dashboardRequestVO.getTimeStamp());

					if (pstmt.executeUpdate() > 0) {
						
						PreparedStatement pstmt4 = con.prepareStatement("(SELECT MAX(ReadingID) as ReadingID FROM balancelog WHERE MeterID = ?)");
						pstmt4.setString(1, dashboardRequestVO.getMeterID());
						ResultSet rs2 = pstmt4.executeQuery();
						
						if(rs2.next()) {
							
							PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MeterID = ? ");
							pstmt3.setString(1, dashboardRequestVO.getMeterID());
							ResultSet rs1 = pstmt3.executeQuery();
							
							if(rs1.next()) {
								
								pstmt1 = con.prepareStatement("UPDATE displaybalancelog SET MainBalanceLogID = ?, MeterID = ?, Reading = ?, Balance = ?, CommunityID = ?, BlockID = ?, CustomerID = ?, BatteryVoltage = ?, TariffAmount = ?, EmergencyCredit = ?, MeterType = ?, SolonideStatus = ?, CreditStatus = ?, TamperDetect = ?, TamperTimeStamp = ?, DoorOpenTimeStamp = ?,  LowBattery = ?, Vacation = ?, Minutes = ?, IoTTimeStamp = ?, LogDate = NOW() WHERE MeterID = ? ");
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
								pstmt1.setInt(13, dashboardRequestVO.getCreditStatus());
								pstmt1.setInt(14, dashboardRequestVO.getTamperStatus());
								pstmt1.setString(15, dashboardRequestVO.getTamperTimeStamp());
								pstmt1.setString(16, dashboardRequestVO.getDoorOpenTimeStamp());
								pstmt1.setInt(17, dashboardRequestVO.getLowBattery());
								pstmt1.setInt(18, dashboardRequestVO.getVacation());
								pstmt1.setInt(19, dashboardRequestVO.getMinutes());
								pstmt1.setString(20, dashboardRequestVO.getTimeStamp());
								pstmt1.setString(21, dashboardRequestVO.getMeterID());
								
							} else {
								
									pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MeterID, Reading, Balance, CommunityID, BlockID, CustomerID, BatteryVoltage, TariffAmount, EmergencyCredit, MeterType, SolonideStatus, CreditStatus, TamperDetect, TamperTimeStamp, DoorOpenTimeStamp, LowBattery, Vacation, MeterSerialNumber, CRNNumber, Minutes, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
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
									pstmt1.setInt(13, dashboardRequestVO.getCreditStatus());
									pstmt1.setInt(14, dashboardRequestVO.getTamperStatus());
									pstmt1.setString(15, dashboardRequestVO.getTamperTimeStamp());
									pstmt1.setString(16, dashboardRequestVO.getDoorOpenTimeStamp());
									pstmt1.setInt(17, dashboardRequestVO.getLowBattery());
									pstmt1.setInt(18, dashboardRequestVO.getVacation());
									pstmt1.setString(19, rs.getString("MeterSerialNumber"));
									pstmt1.setString(20, rs.getString("CRNNumber"));
									pstmt1.setInt(21, dashboardRequestVO.getMinutes());
									pstmt1.setString(22, dashboardRequestVO.getTimeStamp());
									
							}
							
						}
						
						pstmt1.executeUpdate();
						result = "Success";
						
						String query = "SELECT IoTTimeStamp, MeterID, TamperDetect, LowBattery, CreditStatus FROM balancelog WHERE MeterID = ? AND CRNNumber = ? AND <condition> AND IoTTimeStamp BETWEEN (CONCAT(CURDATE(), ' 00:00:00')) AND NOW() ORDER BY IoTTimeStamp DESC";
						
						if (dashboardRequestVO.getLowBattery() == 1) {
							
							ps = con.prepareStatement(query.replaceAll("<condition>", dashboardRequestVO.getLowBattery() == 1 ? "LowBattery = 1" : ""));
							ps.setString(1, dashboardRequestVO.getMeterID());
							ps.setString(2, rs.getString("CRNNumber"));
							resultSet = ps.executeQuery(); 

							int size =0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "The Battery in Meter with CRN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is low.";
								
								sendalertmail("Low Battery Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
								sendalertsms(0, alertMessage, dashboardRequestVO.getMeterID());
							}
						} 
						
						if (dashboardRequestVO.getTamperStatus() == 1 || dashboardRequestVO.getTamperStatus() == 2 || dashboardRequestVO.getTamperStatus() == 3) {
							ps = con.prepareStatement(query.replaceAll("<condition>", dashboardRequestVO.getTamperStatus() == 1 ? "TamperDetect = 1" : dashboardRequestVO.getTamperStatus() == 2 ? "TamperDetect = 2" : dashboardRequestVO.getTamperStatus() == 3 ? "TamperDetect = 3" : ""));
							ps.setString(1, dashboardRequestVO.getMeterID());
							ps.setString(2, rs.getString("CRNNumber"));
							resultSet = ps.executeQuery(); 
							
							int size =0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "There is a <tamper> Tamper at <timestamp>, in Meter with CRN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block>.";
								alertMessage = alertMessage.replaceAll("<tamper>", dashboardRequestVO.getTamperStatus() == 2 ? "Door Open" : dashboardRequestVO.getTamperStatus() == 1 ? "Magnetic" : dashboardRequestVO.getTamperStatus() == 3 ? "Magnetic and Door Open" : "");
								alertMessage = alertMessage.replaceAll("<timestamp>", dashboardRequestVO.getTamperStatus() == 1 ? ExtraMethodsDAO.datetimeformatter(dashboardRequestVO.getTamperTimeStamp()) : dashboardRequestVO.getTamperStatus() == 2 ? ExtraMethodsDAO.datetimeformatter(dashboardRequestVO.getDoorOpenTimeStamp()) : dashboardRequestVO.getTamperStatus() == 3 ? ExtraMethodsDAO.datetimeformatter(dashboardRequestVO.getTamperTimeStamp())+" and "+ExtraMethodsDAO.datetimeformatter(dashboardRequestVO.getTamperTimeStamp()) : "");
								sendalertmail("Tamper Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
								sendalertsms(0, alertMessage, dashboardRequestVO.getMeterID());
							}
						}

						if(dashboardRequestVO.getCreditStatus() == 1) {
							
							ps = con.prepareStatement(query.replaceAll("<condition>", dashboardRequestVO.getCreditStatus() == 1 ? "CreditStatus = 1" : ""));
							ps.setString(1, dashboardRequestVO.getMeterID());
							ps.setString(2, rs.getString("CRNNumber"));
							resultSet = ps.executeQuery(); 

							int size =0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "Balance in your Meter with CRN: <CRN> is low. Please Recharge again.";
								
								sendalertmail("Low Balance Alert!!!", alertMessage, dashboardRequestVO.getMeterID());
								sendalertsms(1, alertMessage, dashboardRequestVO.getMeterID());								
							}
							
						}
						
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
				
				mailRequestVO.setMessage(subject.equalsIgnoreCase("Low Balance Alert!!!") ? "Dear Customer, \n \n"+message : "Dear Admin, \n \n"+ message);
				
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
