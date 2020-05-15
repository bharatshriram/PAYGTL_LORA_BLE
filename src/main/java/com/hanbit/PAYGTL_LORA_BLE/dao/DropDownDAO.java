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
import java.util.HashMap;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.LoginVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TopupDetailsResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
public class DropDownDAO {

	static LoginVO loginvo = new LoginVO();

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}
	
	public HashMap<Integer, String> getallcommunities(int roleid, String id) {
		// TODO Auto-generated method stub
		
		HashMap<Integer, String> communities = new HashMap<Integer, String>(); 
		Connection con = null;
		try {
			con = getConnection();
			

			String query = "SELECT c.CommunityID, c.CommunityName FROM community AS c <change> ";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid==2 || roleid==5) ? "LEFT JOIN block AS b ON b.CommunityID = c.CommunityID WHERE b.BlockID = "+id : (roleid == 3) ? "LEFT JOIN customermeterdetails AS cmd ON cmd.CommunityID = c.CommunityID WHERE cmd.CRNNumber = '"+id+"'": "ORDER BY c.CommunityID DESC"));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				communities.put(rs.getInt("CommunityID"), rs.getString("CommunityName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return communities;
	}

	public HashMap<Integer, String> getallblocks(int communityID, int roleid, String id) {
		// TODO Auto-generated method stub

		HashMap<Integer, String> blocks = new HashMap<Integer, String>(); 
		Connection con = null;

		try {
			con = getConnection();
			String query = "SELECT BlockID, BlockName FROM block WHERE CommunityID=? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY BlockID ASC" : (roleid == 2 || roleid == 5) ? "AND BlockID = "+id+ " ORDER BY BlockID ASC" : (roleid == 3) ? "AND BlockID = (SELECT BlockID FROM customermeterdetails WHERE CRNNumber = '"+id+"')":""));
			pstmt.setInt(1, communityID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				blocks.put(rs.getInt("BlockID"), rs.getString("BlockName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return blocks;
	}

	public HashMap<String, String> getallhouses(int blockID, int roleid, String id) {
		// TODO Auto-generated method stub
		HashMap<String, String> houses = new HashMap<String, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			String query = "SELECT CRNNumber, HouseNumber from customermeterdetails where BlockID = ? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 2 || roleid == 4 || roleid == 5) ? "ORDER BY CustomerID ASC" : (roleid == 3) ? " AND CRNNumber = '"+id+"'" :""));
			pstmt.setInt(1, blockID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				houses.put(rs.getString("CRNNumber"), rs.getString("HouseNumber"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return houses;
	}
	
	public TopupDetailsResponseVO gettopupdetails(String CRNNumber) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TopupDetailsResponseVO topupdetailsresponsevo = new TopupDetailsResponseVO();
		
		try{
			con = getConnection();
			
			topupdetailsresponsevo.setReconnectionCharges(0);
			LocalDateTime dateTime = LocalDateTime.now();
			
			ps = con.prepareStatement("SELECT cmd.MeterID, t.TariffID, t.TariffName, t.Tariff, t.EmergencyCredit, t.AlarmCredit, t.FixedCharges FROM customermeterdetails AS cmd LEFT JOIN tariff AS t ON t.TariffID = cmd.TariffID WHERE cmd.CRNNumber = ?");
	        ps.setString(1, CRNNumber);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	        	topupdetailsresponsevo.setMeterID(rs.getString("MeterID"));
	        	topupdetailsresponsevo.setAlarmCredit(rs.getFloat("AlarmCredit"));
	        	topupdetailsresponsevo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
	        	topupdetailsresponsevo.setTariffName(rs.getString("TariffName"));
	        	topupdetailsresponsevo.setTariff(rs.getFloat("Tariff"));
	        	topupdetailsresponsevo.setTariffID(rs.getInt("TariffID"));
	                    
	                    pstmt = con.prepareStatement("SELECT dbl.IoTTimeStamp, dbl.Balance, al.ReconnectionCharges, dbl.Minutes FROM displaybalanceLog AS dbl JOIN alertsettings AS al WHERE CRNNumber = ? ");
	                    pstmt.setString(1, CRNNumber);
	                    ResultSet rs1 = pstmt.executeQuery();
	                    if (rs1.next()) {
	                    	topupdetailsresponsevo.setIoTTimeStamp(rs1.getString("IoTTimeStamp"));
                        	topupdetailsresponsevo.setCurrentBalance(rs1.getFloat("Balance"));
                        	topupdetailsresponsevo.setReconnectionCharges(rs1.getInt("Minutes") != 0 ? rs1.getInt("ReconnectionCharges") : 0);
                        	
        					PreparedStatement pstmt2 = con.prepareStatement("SELECT MONTH(TransactionDate) AS previoustopupmonth from topup WHERE Status = 2 and CRNNumber = '"+CRNNumber+"'" + "ORDER BY TransactionID DESC LIMIT 0,1");
        					ResultSet rs2 = pstmt2.executeQuery();
        					
        					if(rs2.next()) {
        						
        						topupdetailsresponsevo.setFixedCharges(rs2.getInt("previoustopupmonth") != dateTime.getMonthValue() ? (rs.getInt("FixedCharges") * (dateTime.getMonthValue() - rs2.getInt("previoustopupmonth"))) : 0);
        					}

	                    	} else {
	        					
	        					topupdetailsresponsevo.setIoTTimeStamp("0");
	                        	topupdetailsresponsevo.setCurrentBalance(0);
	                        	topupdetailsresponsevo.setReconnectionCharges(0);
	                        	topupdetailsresponsevo.setFixedCharges(rs.getInt("FixedCharges"));
	                        }
	            }
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
	//		ps.close();
			rs.close();
			con.close();
		}
		
		return topupdetailsresponsevo;
	}

	public HashMap<Integer, String> getalltariffs() throws SQLException {
		// TODO Auto-generated method stub
	
		HashMap<Integer, String> tariffs = new HashMap<Integer, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT TariffID, TariffName FROM tariff");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				tariffs.put(rs.getInt("TariffID"), rs.getString("TariffName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return tariffs;
	}
}
