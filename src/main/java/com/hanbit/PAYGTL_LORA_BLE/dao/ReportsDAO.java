/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.AlarmRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.FinancialReportsRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpSummaryRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserConsumptionRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.AlarmsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.FinancialReportsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TopUpSummaryResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.UserConsumptionReportsResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class ReportsDAO {

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	/* Financial Reports */
	
	public List<FinancialReportsResponseVO> getFinancialReportsdetails(FinancialReportsRequestVO financialreportsrequestvo, int roleid, int id) throws SQLException { 

		 // TODO Auto-generated method stub
	 
	 Connection con = null; 
	 PreparedStatement pstmt = null; 
	 PreparedStatement pstmt1 = null;
	 PreparedStatement pstmt2 = null;
	 ResultSet rs = null;
	 ResultSet rs1 = null;
	 ResultSet rs2 = null;
	 
	 FinancialReportsResponseVO financialreportsresponsevo = null;
	 List<FinancialReportsResponseVO> financialreportsresponselist = null;
	 int totalAmountForSelectedPeriod = 0;
	 int totalUnitsForSelectedPeriod = 0;
	 
		try {
			con = getConnection();

			financialreportsresponselist = new ArrayList<FinancialReportsResponseVO>();
			
			String query = "SELECT c.CommunityName, b.BlockName, cmd.HouseNumber, cmd.FirstName, cmd.LastName, cmd.MeterID FROM customermeterdetails AS cmd LEFT JOIN community AS C on c.communityID = cmd.CommunityID LEFT JOIN block AS b on b.BlockID = cmd.BlockID <change>";
			pstmt1 = con.prepareStatement(query.replaceAll("<change>", (financialreportsrequestvo.getBlockID() == 0 && (roleid==1 || roleid==4)) ? "WHERE cmd.CommunityID = "+financialreportsrequestvo.getCommunityID() + " ORDER BY cmd.CustomerID ASC" : (financialreportsrequestvo.getBlockID() != 0 && (roleid==1 || roleid==4)) ? "WHERE cmd.BlockID = "+financialreportsrequestvo.getBlockID() + " ORDER BY cmd.CustomerID ASC" :(roleid==2 || roleid==5) ? "WHERE cmd.CommunityID = "+financialreportsrequestvo.getCommunityID() + " AND cmd.BlockID = "+id+" ORDER BY cmd.CustomerID ASC":""));
			rs1 = pstmt1.executeQuery();
			while(rs1.next()) {
				
				financialreportsresponsevo = new FinancialReportsResponseVO();
				financialreportsresponsevo.setCommunityName(rs1.getString("CommunityName"));
				financialreportsresponsevo.setBlockName(rs1.getString("BlockName"));
				financialreportsresponsevo.setHouseNumber(rs1.getString("HouseNumber"));
				financialreportsresponsevo.setMeterID(rs1.getString("MeterID"));
				
				String query1 = "SELECT SUM(Amount) AS Total FROM topup WHERE MeterID = ? AND YEAR(TransactionDate) = ? <change> AND STATUS = 2";
				query1 = query1.replaceAll("<change>", (financialreportsrequestvo.getMonth() > 0) ? "AND MONTH(TransactionDate) = "+financialreportsrequestvo.getMonth() : "");
				pstmt = con.prepareStatement(query1);
				pstmt.setString(1, rs1.getString("MeterID"));
				pstmt.setInt(2, financialreportsrequestvo.getYear());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					financialreportsresponsevo.setTotalAmount(rs.getInt("Total"));
					totalAmountForSelectedPeriod = financialreportsresponsevo.getTotalAmount() + totalAmountForSelectedPeriod;
				}
				
				String query2 = "SELECT ABS((SELECT Reading FROM balancelog WHERE MeterID = ? AND YEAR(IoTTimeStamp) = ? <change> ORDER BY ReadingID DESC LIMIT 0,1)\r\n" + 
						"- (SELECT Reading FROM balancelog WHERE MeterID = ? AND YEAR(IoTTimeStamp) = ? <change> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
				query2 = query2.replaceAll("<change>", (financialreportsrequestvo.getMonth() > 0) ? "AND MONTH(IoTTimeStamp) = "+financialreportsrequestvo.getMonth() : "");
				pstmt2 = con.prepareStatement(query2);
				pstmt2.setString(1, rs1.getString("MeterID"));
				pstmt2.setInt(2, financialreportsrequestvo.getYear());
				pstmt2.setString(3, rs1.getString("MeterID"));
				pstmt2.setInt(4, financialreportsrequestvo.getYear());
				rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					financialreportsresponsevo.setTotalUnits(rs2.getInt("Units"));
					totalUnitsForSelectedPeriod = financialreportsresponsevo.getTotalUnits() + totalUnitsForSelectedPeriod;
				}
				financialreportsresponselist.add(financialreportsresponsevo);
			}
			financialreportsresponsevo.setTotalAmountForSelectedPeriod(totalAmountForSelectedPeriod);
			financialreportsresponsevo.setTotalUnitsForSelectedPeriod(totalUnitsForSelectedPeriod);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			pstmt1.close();
			rs.close();
			rs1.close();
			con.close();
		}
		
	 return financialreportsresponselist; 
	 
	}
	 

	/* User Consumption Reports */

	public List<UserConsumptionReportsResponseVO> getuserconsumptionreportsdetails(
			UserConsumptionRequestVO userconsumptionreportsrequestvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<UserConsumptionReportsResponseVO> userconsumptionreportsresponselist = null;
		UserConsumptionReportsResponseVO userconsumptionreportsresponsevo = null;

		try {
			con = getConnection();
			userconsumptionreportsresponselist = new ArrayList<UserConsumptionReportsResponseVO>();

			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, cmd.CRNNumber, bl.ReadingID, bl.EmergencyCredit, \r\n" + 
					"bl.MeterID, bl.Reading, bl.Balance, bl.BatteryVoltage, bl.TariffAmount, bl.SolonideStatus, bl.TamperDetect, bl.IoTTimeStamp, bl.LogDate\r\n" + 
					"FROM balancelog AS bl LEFT JOIN community AS c ON c.communityID = bl.CommunityID LEFT JOIN block AS b ON b.BlockID = bl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = bl.CRNNumber WHERE bl.CRNNumber = ? AND bl.IoTTimeStamp BETWEEN ? AND ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, userconsumptionreportsrequestvo.getCRNNumber());
				pstmt.setString(2, userconsumptionreportsrequestvo.getFromDate()+ " :00.001");
				pstmt.setString(3,userconsumptionreportsrequestvo.getToDate()+ " :59.999");

				rs = pstmt.executeQuery();
				while (rs.next()) {

					userconsumptionreportsresponsevo = new UserConsumptionReportsResponseVO();
					
					userconsumptionreportsresponsevo.setCRNNumber(rs.getString("CRNNumber"));
					userconsumptionreportsresponsevo.setMeterID(rs.getString("MeterID"));
					userconsumptionreportsresponsevo.setReading(rs.getFloat("Reading"));
					userconsumptionreportsresponsevo.setBalance(rs.getFloat("Balance"));
					userconsumptionreportsresponsevo.setBattery(rs.getInt("BatteryVoltage"));
					userconsumptionreportsresponsevo.setTariff(rs.getFloat("TariffAmount"));
					userconsumptionreportsresponsevo.setEmergencyCredit(rs.getFloat("Emergencycredit"));
					userconsumptionreportsresponsevo.setDateTime(ExtraMethodsDAO.datetimeformatter(rs.getString("IoTTimeStamp")));
					
					userconsumptionreportsresponselist.add(userconsumptionreportsresponsevo);
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			 pstmt.close();
			 rs.close();
			con.close();
		}

		return userconsumptionreportsresponselist;
	}

	/* TopUp Summary */

	public List<TopUpSummaryResponseVO> gettopupsummarydetails(TopUpSummaryRequestVO topupsummaryrequestvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		List<TopUpSummaryResponseVO> topupsummarydetails = null;
		TopUpSummaryResponseVO topupsummaryresponsevo = null;

		try {
			con = getConnection();
			topupsummarydetails = new LinkedList<TopUpSummaryResponseVO>();
			
			String query = "SELECT DISTINCT t.TransactionID, cmd.FirstName, cmd.LastName, cmd.HouseNumber, cmd.MeterID, cmd.CRNNumber, t.Amount, t.Status, t.ModeOfPayment, t.PaymentStatus, t.RazorPayOrderID, t.RazorPayPaymentID, t.RazorPayRefundID, t.RazorPayRefundStatus, t.TransactionDate, t.CreatedByID FROM topup AS t \r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = t.CRNNumber WHERE t.CommunityID = ? AND t.TransactionDate BETWEEN ? AND ? <change>";
			
				pstmt = con.prepareStatement(query.replaceAll("<change>", (topupsummaryrequestvo.getBlockID() > 0 && !topupsummaryrequestvo.getCRNNumber().isEmpty()) ? " AND t.CRNNumber = '"+topupsummaryrequestvo.getCRNNumber()+"'" : (topupsummaryrequestvo.getCRNNumber().isEmpty() && topupsummaryrequestvo.getBlockID() > 0) ? " AND t.BlockID = "+topupsummaryrequestvo.getBlockID() : ""));
				
				pstmt.setInt(1, topupsummaryrequestvo.getCommunityID());
				pstmt.setString(2, topupsummaryrequestvo.getFromDate()+ " 00:00:01.001");
				pstmt.setString(3,topupsummaryrequestvo.getToDate()+ " 23:59:59.999");

				rs = pstmt.executeQuery();
				while (rs.next()) {

					topupsummaryresponsevo = new TopUpSummaryResponseVO();
					
					topupsummaryresponsevo.setTransactionID(rs.getInt("TransactionID"));
					topupsummaryresponsevo.setFirstName(rs.getString("FirstName"));
					topupsummaryresponsevo.setLastName(rs.getString("LastName"));
					topupsummaryresponsevo.setHouseNumber(rs.getString("HouseNumber"));
					topupsummaryresponsevo.setCRNNumber(rs.getString("CRNNumber"));
					topupsummaryresponsevo.setMeterID(rs.getString("MeterID"));
					topupsummaryresponsevo.setRechargeAmount(rs.getInt("Amount"));
					topupsummaryresponsevo.setModeOfPayment(rs.getString("ModeOfPayment"));
					topupsummaryresponsevo.setRazorPayOrderID(rs.getString("RazorPayOrderID"));
					topupsummaryresponsevo.setRazorPayPaymentID(rs.getString("RazorPayPaymentID"));
					topupsummaryresponsevo.setRazorPayRefundID((rs.getInt("PaymentStatus") == 3 ? rs.getString("RazorPayRefundID") : "---"));
					topupsummaryresponsevo.setRazorPayRefundStatus((rs.getInt("PaymentStatus") == 3 ? rs.getString("RazorPayRefundStatus") : "---"));
					topupsummaryresponsevo.setPaymentStatus((rs.getInt("PaymentStatus") == 1 ? "PAID" : (rs.getInt("PaymentStatus") == 2) ? "FAILED" : (rs.getInt("PaymentStatus") == 3) ? "REFUND INITITATED" : "NOT PAID"));
					topupsummaryresponsevo.setStatus((rs.getInt("Status") == 0) ? "Pending...waiting for acknowledge" : (rs.getInt("Status") == 1) ? "Pending" : (rs.getInt("Status") == 2) ? "Passed" :"Failed");
					topupsummaryresponsevo.setDateTime(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
					
					pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
					rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
						topupsummaryresponsevo.setTransactedByUserName(rs1.getString("UserName"));
						topupsummaryresponsevo.setTransactedByRoleDescription(rs1.getString("RoleDescription"));
					}
					
					topupsummarydetails.add(topupsummaryresponsevo);
				}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return topupsummarydetails;

	}

	/* Alarms */

	public List<AlarmsResponseVO> getAlarmdetails(int roleid, int id, int filterCid) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AlarmsResponseVO> alarmsResponseList = null;
		int noAMRInterval = 0;
		int lowBatteryVoltage = 0;
		
		try {

			con = getConnection();
			alarmsResponseList = new LinkedList<AlarmsResponseVO>();
			AlarmsResponseVO alarmsResponseVO = null;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getInt("LowBatteryVoltage");
			}
			
			String query = "SELECT c.CommunityName, b.BlockName, cmd.HouseNumber, cmd.FirstName, cmd.LastName, cmd.MeterID, cmd.CRNNumber FROM customermeterdetails AS cmd LEFT JOIN community AS C on c.communityID = cmd.CommunityID LEFT JOIN block AS b on b.BlockID = cmd.BlockID <change>";
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && (filterCid == -1)) ? " ORDER BY cmd.CustomerID ASC" : ((roleid == 1 || roleid == 4) && (filterCid != -1)) ? "WHERE cmd.CommunityID = "+filterCid+ " ORDER BY cmd.CustomerID ASC" : (roleid==2 || roleid==5) ? "WHERE cmd.BlockID = "+id : " ORDER BY cmd.CustomerID ASC"));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				PreparedStatement pstmt2 = con.prepareStatement("SELECT TIMESTAMPDIFF(MINUTE, (SELECT IoTTimeStamp FROM balancelog WHERE MeterID = ? ORDER BY ReadingID DESC LIMIT 1,1), NOW()) AS Minutes");
				pstmt2.setString(1, rs.getString("MeterID"));
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					
					if(rs2.getInt("Minutes")>noAMRInterval) {
						alarmsResponseVO = new AlarmsResponseVO();
						
						alarmsResponseVO.setCommunityName(rs.getString("CommunityName"));
						alarmsResponseVO.setBlockName(rs.getString("BlockName"));
						alarmsResponseVO.setHouseNumber(rs.getString("HouseNumber"));
						alarmsResponseVO.setCRNNumber(rs.getString("CRNNumber"));
						alarmsResponseVO.setMeterID(rs.getString("MeterID"));
						alarmsResponseVO.setDifference(rs2.getInt("Minutes"));
						PreparedStatement pstmt3 = con.prepareStatement("SELECT BatteryVoltage, TamperDetect, IoTTimeStamp, TamperDetect, LowBattery FROM displaybalancelog WHERE MeterID = ?");
						pstmt3.setString(1, rs.getString("MeterID"));
						ResultSet rs3 = pstmt3.executeQuery();
						if(rs3.next()) {
							alarmsResponseVO.setDateTime(ExtraMethodsDAO.datetimeformatter(rs3.getString("IotTimeStamp")));
							if(rs3.getInt("LowBattery")==1 || rs3.getInt("BatteryVoltage") < lowBatteryVoltage) {
								alarmsResponseVO.setBatteryVoltage(rs3.getString("BatteryVoltage"));	
							}else {
								alarmsResponseVO.setBatteryVoltage("---");
							}
							if(rs3.getInt("TamperDetect")==1) {
								alarmsResponseVO.setTamper("YES");	
							}else {
								alarmsResponseVO.setTamper("---");
							}
							
						}
						alarmsResponseList.add(alarmsResponseVO);
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
		return alarmsResponseList;
	}

	public List<AlarmsResponseVO> getAlarmreportsdetails(AlarmRequestVO alarmRequestVO) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AlarmsResponseVO> alarmsResponseList = null;
		
		try {

			con = getConnection();
			alarmsResponseList = new LinkedList<AlarmsResponseVO>();
			AlarmsResponseVO alarmsResponseVO = null;
//			bl.SolonideStatus = 1 OR 
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, cmd.CRNNumber, bl.ReadingID, bl.EmergencyCredit, \r\n" + 
					"bl.MeterID, bl.Reading, bl.Balance, bl.BatteryVoltage, bl.TariffAmount, bl.SolonideStatus, bl.TamperDetect, bl.TamperTimeStamp, bl.DoorOpenTimeStamp, bl.LowBattery, bl.IotTimeStamp, bl.LogDate\r\n" + 
					"FROM balancelog AS bl LEFT JOIN community AS c ON c.communityID = bl.CommunityID LEFT JOIN block AS b ON b.BlockID = bl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = bl.CRNNumber WHERE bl.CRNNumber = ? AND bl.IoTTimeStamp BETWEEN ? AND ? AND (bl.TamperDetect IN (1, 2, 3) OR bl.LowBattery = 1)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, alarmRequestVO.getCRNNumber());
				pstmt.setString(2, alarmRequestVO.getFromDate() + " :00.001");
				pstmt.setString(3,alarmRequestVO.getToDate()+ " :59.999");

				rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
						alarmsResponseVO = new AlarmsResponseVO();
						
						alarmsResponseVO.setCommunityName(rs.getString("CommunityName"));
						alarmsResponseVO.setBlockName(rs.getString("BlockName"));
						alarmsResponseVO.setHouseNumber(rs.getString("HouseNumber"));
						alarmsResponseVO.setCRNNumber(rs.getString("CRNNumber"));
						alarmsResponseVO.setMeterID(rs.getString("MeterID"));
						alarmsResponseVO.setBatteryVoltage(rs.getString("BatteryVoltage"));
						alarmsResponseVO.setTamper((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "MAG" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" : (rs.getInt("TamperDetect") == 3) ? "MAG;"+"DOOR OPEN" : "NO");
						alarmsResponseVO.setTamperTimeStamp((rs.getInt("TamperDetect") == 1) ? rs.getString("TamperTimeStamp") : (rs.getInt("TamperDetect") == 2) ? rs.getString("DoorOpenTimeStamp") : (rs.getInt("TamperDetect") == 3) ? rs.getString("TamperTimeStamp") +";"+ rs.getString("DoorOpenTimeStamp") : "---");
//						alarmsResponseVO.setSolonideStatus(rs.getInt("SolonideStatus") == 1 ? "CLOSED" : "OPEN");
						alarmsResponseVO.setDateTime(ExtraMethodsDAO.datetimeformatter(rs.getInt("TamperDetect") == 1 ? rs.getString("TamperTimeStamp")+":00" : rs.getInt("TamperDetect") == 2 ? rs.getString("DoorOpenTimeStamp")+":00" : rs.getString("IotTimeStamp")));
						alarmsResponseVO.setBatteryColor((rs.getInt("LowBattery") == 1 ) ? "RED" : "GREEN");
						alarmsResponseList.add(alarmsResponseVO);
					}
					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return alarmsResponseList;
	}

}
