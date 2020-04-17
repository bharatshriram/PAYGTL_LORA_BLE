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

	public HashMap<Integer, String> getallblocks(int communityID) {
		// TODO Auto-generated method stub

		HashMap<Integer, String> blocks = new HashMap<Integer, String>(); 
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT BlockID, BlockName FROM block WHERE CommunityID=?");
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

	public HashMap<Integer, String> getallhouses(int blockID) {
		// TODO Auto-generated method stubArrayList<String> blocklist=new
		// ArrayList<String>();
		HashMap<Integer, String> houses = new HashMap<Integer, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT CustomerID, HouseNumber from customermeterdetails where BlockID = ?");
			pstmt.setInt(1, blockID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				houses.put(rs.getInt("CustomerID"), rs.getString("HouseNumber"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return houses;
	}
	
	public TopupDetailsResponseVO gettopupdetails(int customerID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TopupDetailsResponseVO topupdetailsresponsevo = new TopupDetailsResponseVO();
		
		try{
			con = getConnection();
			ps = con.prepareStatement("SELECT cmd.MeterID, t.TariffID, t.TariffName, t.Tariff, t.EmergencyCredit, t.AlarmCredit FROM customermeterdetails AS cmd LEFT JOIN tariff AS t ON t.TariffID = cmd.TariffID WHERE cmd.CustomerID = ?");
	        ps.setInt(1, customerID);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	        	topupdetailsresponsevo.setMeterID(rs.getString("MeterID"));
	        	topupdetailsresponsevo.setAlarmCredit(rs.getFloat("AlarmCredit"));
	        	topupdetailsresponsevo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
	        	topupdetailsresponsevo.setTariffName(rs.getString("TariffName"));
	        	topupdetailsresponsevo.setTariff(rs.getFloat("Tariff"));
	        	topupdetailsresponsevo.setTariffID(rs.getInt("TariffID"));
	                    
	                    pstmt = con.prepareStatement("SELECT IoTTimeStamp, Balance FROM displaybalanceLog WHERE MeterID = ? ");
	                    pstmt.setString(1, topupdetailsresponsevo.getMeterID());
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
