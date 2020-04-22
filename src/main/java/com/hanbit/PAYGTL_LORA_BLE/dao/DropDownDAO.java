/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public HashMap<Integer, String> getallcommunities(int roleid, int id) {
		// TODO Auto-generated method stub
		
		HashMap<Integer, String> communities = new HashMap<Integer, String>(); 
		Connection con = null;
		try {
			con = getConnection();
			
			String query = "SELECT CommunityID, CommunityName FROM community <change> ";
			query = query.replaceAll("<change>", (roleid==2 || roleid==3 || roleid==5) ? "WHERE CommunityID = "+id : "ORDER BY CommunityID ASC");
			PreparedStatement pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				communities.put(rs.getInt("CommunityID"), rs.getString("CommunityName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return communities;
	}

	public HashMap<Integer, String> getallblocks(int communityID, int roleid, int id) {
		// TODO Auto-generated method stub

		HashMap<Integer, String> blocks = new HashMap<Integer, String>(); 
		Connection con = null;

		try {
			con = getConnection();
			String query = "SELECT BlockID, BlockName FROM block WHERE CommunityID=? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY BlockID ASC" : (roleid == 2 || roleid == 5) ? "AND BlockID = "+id+ " ORDER BY BlockID ASC" : (roleid == 3) ? "AND BlockID = (SELECT BlockID FROM customermeterdetails WHERE CustomerID = "+id+")":""));
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

	public HashMap<String, String> getallhouses(int blockID, int roleid, int id) {
		// TODO Auto-generated method stub
		HashMap<String, String> houses = new HashMap<String, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			String query = "SELECT CustomerID, HouseNumber from customermeterdetails where BlockID = ? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 2 || roleid == 4 || roleid == 5) ? "ORDER BY CustomerID ASC" : (roleid == 3) ? " AND CustomerID = "+id :""));
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

			ps = con.prepareStatement("SELECT cmd.MeterID, t.TariffID, t.TariffName, t.Tariff, t.EmergencyCredit, t.AlarmCredit FROM customermeterdetails AS cmd LEFT JOIN tariff AS t ON t.TariffID = cmd.TariffID WHERE cmd.CRNNumber = ?");
	        ps.setString(1, CRNNumber);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	        	topupdetailsresponsevo.setMeterID(rs.getString("MeterID"));
	        	topupdetailsresponsevo.setAlarmCredit(rs.getFloat("AlarmCredit"));
	        	topupdetailsresponsevo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
	        	topupdetailsresponsevo.setTariffName(rs.getString("TariffName"));
	        	topupdetailsresponsevo.setTariff(rs.getFloat("Tariff"));
	        	topupdetailsresponsevo.setTariffID(rs.getInt("TariffID"));
	                    
	                    pstmt = con.prepareStatement("SELECT IoTTimeStamp, Balance FROM displaybalanceLog WHERE CRNNumber = ? ");
	                    pstmt.setString(1, CRNNumber);
	                    ResultSet rs1 = pstmt.executeQuery();
	                    if (rs1.next()) {
	                    	if (rs1.getString("IoTTimeStamp") == null) {
	                        	topupdetailsresponsevo.setIoTTimeStamp("0");
	                        	topupdetailsresponsevo.setCurrentBalance(0);
	                        } else {
	                        	topupdetailsresponsevo.setIoTTimeStamp(rs1.getString("IoTTimeStamp"));
	                        	topupdetailsresponsevo.setCurrentBalance(rs1.getFloat("Balance"));
	                        }
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
