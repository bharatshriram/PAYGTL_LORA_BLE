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
import java.util.HashMap;
import java.util.List;

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
	
	public List<TopupDetailsResponseVO> gettopupdetails(String house) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TopupDetailsResponseVO topupdetailsresponsevo = new TopupDetailsResponseVO();
		List<TopupDetailsResponseVO> topupdetailslist = new ArrayList<TopupDetailsResponseVO>();
		
		try{
		con = getConnection();
		pstmt = con.prepareStatement("select cust_id from customer where house_no=?");
		pstmt.setString(1, house);
		rs = pstmt.executeQuery();
		if(rs.next()){
			String sql1 = "select tcm.meter_id as mid,tf.tariff as trf,tf.alarm_credit as acrdt,tf.emr_credit as ecrdt from customer_meter tcm,tariff tf where tcm.tariff_TypeId=tf.tariff_id and cust_id=?";
			ps = con.prepareStatement(sql1);
	        ps.setString(1, rs.getString("cust_id"));
	        ResultSet rs1 = ps.executeQuery();
	        if (rs1.next()) {
	        	topupdetailsresponsevo.setMeterid(rs1.getInt("mid"));
	        	topupdetailsresponsevo.setAlarmCredit(rs1.getFloat("acrdt"));
	        	topupdetailsresponsevo.setEmergencyCredit(rs1.getFloat("ecrdt"));
	        	topupdetailsresponsevo.setUnitRate(rs1.getFloat("trf"));
	            String sql = "SELECT co.cor_ip AS cip FROM meter_master mm,coordinator co WHERE mm.cor_id=co.cor_id AND mm.meter_id=?";
	            ps = con.prepareStatement(sql);
	            ps.setInt(1, topupdetailsresponsevo.getMeterid());
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                    String sql2 = "SELECT MAX(logdate) as ld FROM BalanceLog WHERE meterno=?";
	                    pstmt = con.prepareStatement(sql2);
	                    pstmt.setInt(1, topupdetailsresponsevo.getMeterid());
	                    ResultSet rs2 = pstmt.executeQuery();
	                    if (rs2.next()) {
	                    	topupdetailsresponsevo.setDateTime(rs2.getString("ld"));
	                        if (topupdetailsresponsevo.getDateTime() == null) {
	                        	topupdetailsresponsevo.setDateTime("0");
	                        	topupdetailsresponsevo.setCurrentBalance(0);
	                        } else {
	                            String sql4 = "SELECT tc.com_name AS cname,tb.name AS bname,tcu.house_no AS hno,tbl.meterno AS MID,tbl.reading AS reading,tbl.balance AS balance,tbl.logdate AS dt,tbl.Gas_tariff as tariff,tbl.Alarmcredit as alcredit,tbl.Emergencycredit as emcredit,tbl.SolonideStatus as satus,tbl.BatteryVoltage as bvolt,tbl.Emergency as em_check FROM community tc,block tb,customer tcu,customer_meter tcm,BalanceLog tbl WHERE tc.com_id=tb.com_id AND tb.block_id=tcu.block_id AND tcu.cust_id=tcm.cust_id AND tcm.meter_id=tbl.meterno AND tbl.meterno=? AND tbl.logdate='" + topupdetailsresponsevo.getDateTime() + "'";
	                            pstmt = con.prepareStatement(sql4);
	                            pstmt.setInt(1, topupdetailsresponsevo.getMeterid());
	                            ResultSet rs4 = pstmt.executeQuery();
	                            if (rs4.next()) {
	                                String em_check = rs4.getString("em_check");

	                                if (em_check.equals("1")) {
	                                   double reading = rs4.getDouble("balance");
	                                   topupdetailsresponsevo.setEmergencyCredit(rs4.getDouble("emcredit"));
	                                    reading = reading - topupdetailsresponsevo.getEmergencyCredit();
	                                    topupdetailsresponsevo.setCurrentBalance(reading);
	                                }
	                                if (em_check.equals("0")) {
	                                    double reading = rs4.getDouble("balance");
	                                    topupdetailsresponsevo.setCurrentBalance(reading);
	                                    
	                                    }
	                            }
	                        }
	                      
	                        
	                    }
	                }
	            }
	        topupdetailslist.add(topupdetailsresponsevo);
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
		
		return topupdetailslist;
	}
}
