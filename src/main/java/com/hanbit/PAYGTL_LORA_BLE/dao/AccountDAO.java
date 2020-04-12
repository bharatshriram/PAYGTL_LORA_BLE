/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ConfigurationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.StatusRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ConfigurationResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.FixedChargesResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.StatusResponseVO;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author K VimaL Kumar
 * 
 */
public class AccountDAO {

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	/* TopUp */

	public String addtopup(TopUpRequestVO topupvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "Failure";
		int customerID = 0;
		try {
			con = getConnection();

			String cor_ip = "1";
			float emr_credit = topupvo.getEmergencyCredit();
			float alarm_credit = topupvo.getAlarmCredit();
			float amount = topupvo.getAmount();
			
			int pstatus = 0;
			float fcharges = 0;
			float amnt;
			int setTariff_Flag;

			String sql10 = "SELECT tc.com_name AS cname,ttf.custid AS cid,tcu.house_no AS hno,tcm.meter_id AS mid,ttf.TotalConsumption AS totconsume,ttf.FixedCharges AS fcharges,ttf.PaidStatus AS pstatus FROM community tc,block tb,customer tcu,customer_meter tcm,tbl_Tr_FixedCharge ttf WHERE tc.com_id=tb.com_id AND tb.block_id=tcu.block_id AND tcu.cust_id=tcm.cust_id AND tcm.meter_id=ttf.MeterNo AND tc.com_id=? and tcu.cust_id=?";
			PreparedStatement ps3 = con.prepareStatement(sql10);
			ps3.setInt(1, topupvo.getCommunityID());
			ps3.setInt(2, customerID);
			ResultSet rs3 = ps3.executeQuery();
			if (rs3.next()) {
				pstatus = rs3.getInt("pstatus");
				fcharges = rs3.getFloat("fcharges");
			}

				if (topupvo.getSetTariff()==0) {
					if (pstatus == 0) {
						amnt = amount - fcharges;
						setTariff_Flag = 1;
						String sql1 = "SELECT cu.tariff_id AS tid FROM community cu,tariff tf WHERE cu.tariff_id=tf.tariff_id AND cu.com_id=?";
						ps = con.prepareStatement(sql1);
						ps.setInt(1, topupvo.getCommunityID());
						rs = ps.executeQuery();
						if (rs.next()) {

							String sql = "INSERT INTO recharge_transaction(meter_id,cust_id,tariff_id,coordinatorip,rechargeamt,alaramcredit,emergencycredit,setTariff_Flag,user_id) VALUES(?,?,?,?,?,?,?,?,?)";
							ps = con.prepareStatement(sql);
							ps.setString(1, topupvo.getMeterID());
							ps.setInt(2, customerID);
							ps.setString(3, rs.getString("tid"));
							ps.setString(4, cor_ip);
							ps.setFloat(5, amnt);
							ps.setFloat(6, alarm_credit);
							ps.setFloat(7, emr_credit);
							ps.setInt(8, setTariff_Flag);
//							ps.setString(9, topupvo.getUserID());
							if (!ps.execute()) {
								result = "Success";
								
							} else {
								result = "Failure";
							}
						}
						String sql15 = "update tbl_Tr_FixedCharge set PaidStatus=1 where custid=?";
						ps = con.prepareStatement(sql15);
						ps.setInt(1, customerID);
						if (!ps.execute()) {

						} else {

						}

					} else {
						setTariff_Flag = 1;
						String sql1 = "SELECT cu.tariff_id AS tid FROM community cu,tariff tf WHERE cu.tariff_id=tf.tariff_id AND cu.com_id=?";
						ps = con.prepareStatement(sql1);
						ps.setInt(1, topupvo.getCommunityID());
						rs = ps.executeQuery();
						if (rs.next()) {
							String sql = "INSERT INTO recharge_transaction(meter_id,cust_id,tariff_id,coordinatorip,rechargeamt,alaramcredit,emergencycredit,setTariff_Flag) VALUES(?,?,?,?,?,?,?,?)";
							ps = con.prepareStatement(sql);
							ps.setString(1, topupvo.getMeterID());
							ps.setInt(2, customerID);
							ps.setString(3, rs.getString("tid"));
							ps.setString(4, cor_ip);
							ps.setFloat(5, amount);
							ps.setFloat(6, alarm_credit);
							ps.setFloat(7, emr_credit);
							ps.setInt(8, setTariff_Flag);
							if (!ps.execute()) {
								result = "Success";
							} else {
								result = "Failure";
							}
						}
					}
				}
				if (topupvo.getSetTariff()==1) {

					String setTariff_Blockid = "";
					String setTariff_Custid = "";
					String setTariff_Meterid = "";
					setTariff_Flag = 2;
					String setTariff_Amount = "0";
					// String setTariff_Emecredit="0";
					// String setTariff_AlarmCredit="0";
					String sql1 = "SELECT cu.tariff_id AS tid FROM community cu,tariff tf WHERE cu.tariff_id=tf.tariff_id AND cu.com_id=?";
					ps = con.prepareStatement(sql1);
					ps.setInt(1, topupvo.getCommunityID());
					rs = ps.executeQuery();
					if (rs.next()) {
						String sql3 = "select block_id from block where com_id=?";
						ps = con.prepareStatement(sql3);
						ps.setInt(1, topupvo.getCommunityID());
						ResultSet rs1 = ps.executeQuery();
						while (rs1.next()) {
							setTariff_Blockid = rs1.getString("block_id");
							String sql4 = "select tc.cust_id as cid,tcm.meter_id as mid from customer tc,customer_meter tcm,meter_master tmm where tc.cust_id = tcm.cust_id and tcm.meter_id = tmm.meter_id and tc.block_id=? order by tc.cust_id";
							ps = con.prepareStatement(sql4);
							ps.setString(1, setTariff_Blockid);
							ResultSet rs10 = ps.executeQuery();
							while (rs10.next()) {
								setTariff_Custid = rs10.getString("cid");
								setTariff_Meterid = rs10.getString("mid");
								String sql = "INSERT INTO recharge_transaction(meter_id,cust_id,tariff_id,coordinatorip,rechargeamt,alaramcredit,emergencycredit,setTariff_Flag) VALUES(?,?,?,?,?,?,?,?)";
								ps = con.prepareStatement(sql);
								ps.setString(1, setTariff_Meterid);
								ps.setString(2, setTariff_Custid);
								ps.setString(3, rs.getString("tid"));
								ps.setString(4, cor_ip);
								ps.setString(5, setTariff_Amount);
								ps.setFloat(6, alarm_credit);
								ps.setFloat(7, emr_credit);
								ps.setInt(8, setTariff_Flag);
								// System.out.println("ps==>" + ps);
								if (!ps.execute()) {
									// out.println("AMR ID "+meter_id+" Topup Sent Successfully...");
									System.out.println("tariff set for "
											+ setTariff_Meterid
											+ " Sucessfully...");
								} else {
									// out.println("Error...");
								}
							}
						}
						result = "Success";
					}
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// pstmt.close();
			// ps.close();
			rs.close();
			con.close();
		}

		return result;
	}

	/* Status */

	public List<StatusResponseVO> getStatusdetails(int roleid, int id) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<StatusResponseVO> statuslist = null;
		StatusResponseVO statusvo = null;
		try {
			con = getConnection();
			statuslist = new LinkedList<StatusResponseVO>();
			
			// hit tata gateway api for the status of the pending transactions----code from TopupDAO in lora_Gasprepaid project
			
			String query = "SELECT 	DISTINCT t.TransactionID, c.CommunityName, b.BlockName, cmd.FirstName, cmd.LastName, t.MeterID, t.Amount, t.AlarmCredit, t.EmergencyCredit, t.Status, t.PaymentStatus, t.TransactionDate, t.AcknowledgeDate FROM topup AS t \r\n" + 
							"LEFT JOIN community AS c ON t.CommunityID = c.CommunityID LEFT JOIN block AS b ON t.CommunityID = b.blockID\r\n" + 
							"LEFT JOIN customermeterdetails AS cmd ON t.CustomerID = cmd.CustomerID <change>";
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY t.TransactionDate ASC" : (roleid == 2 || roleid == 5) ? "WHERE t.BlockID = "+id+ " ORDER BY t.TransactionDate ASC" : (roleid == 3) ? "WHERE t.CustomerID = "+id:""));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				statusvo = new StatusResponseVO();
				statusvo.setTransactionID(rs.getInt("TransactionID"));
				statusvo.setCommunityName(rs.getString("CommunityName"));
				statusvo.setBlockName(rs.getString("BlockName"));
				statusvo.setFirstName(rs.getString("FirstName"));
				statusvo.setLastName(rs.getString("LastName"));
				statusvo.setHouseNumber(rs.getString("HouseNumber"));
				statusvo.setMeterID(rs.getString("MeterID"));
				statusvo.setAmount(rs.getString("Amount"));
				statusvo.setAlarmCredit(rs.getString("AlarmCredit"));
				statusvo.setEmergencyCredit(rs.getString("EmergencyCredit"));
				statusvo.setTransactionDate(rs.getString("TransactionDate"));

				pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					statusvo.setTransactedByUserName(rs1.getString("UserName"));
					statusvo.setTransactedByRoleDescription(rs1.getString("RoleDescription"));
				}

				if (rs.getInt("Status") == 2) {
					statusvo.setStatus("Passed");
				} else if (rs.getInt("Status") == 1) {
					statusvo.setStatus("Pending");
				} else if (rs.getInt("Status") == 0) {
					statusvo.setStatus("Pending...waiting for acknowledge");
				}else {
					statusvo.setStatus("Failed");
				}
				statuslist.add(statusvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return statuslist;
	}

	public String deletestatus(StatusRequestVO statusvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();

			pstmt = con.prepareStatement("DELETE FROM topup where TransactionID = ?");
			pstmt.setInt(1, statusvo.getTransID());

			if (pstmt.executeUpdate() > 0) {
				result = "Success";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}

		return result;
	}

	public String printreceipt(int transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "Failure";

		try {
			con = getConnection();

			pstmt = con
					.prepareStatement("select tb.name as bname,tcu.house_no as hno,tcu.first_name as fname,tcu.last_name as lname,tcu.email as email,tcu.mobile as mobile,trt.Meter_id as MID,trt.Transid as TID,trt.CoOrdinatorIP as cop,trt.RechargeAmt as rmt,trt.AlaramCredit as ac,trt.EmergencyCredit as ec,trt.Send_DateTime as dt,trt.RecordInsertTime as ri,trt.Send_Status as ss,acks =CASE trt.ack_status WHEN 0 THEN 0 WHEN 1 THEN 1 ELSE 2 END from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm,Recharge_Transaction trt where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id=trt.Meter_id and trt.Meter_id=tmm.meter_id and trt.setTariff_Flag=1 and trt.Transid=?");
			pstmt.setInt(1, transactionID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String house_no = rs.getString("hno");
				String meter_id = rs.getString("MID");

				String bill_no = "";
				String Recharge_amnt = "";
				String e_credit = "";
				String a_credit = "";
				String date_time = "";
				String head = "";

				Document document = new Document(PageSize.A4);
				head = "Topup Receipt for the house no. : " + house_no;

				String drivename = "E:/ConsumptionReports/";
				File directory = new File(drivename);
				if (!directory.exists()) {
					directory.mkdir();
				}
				
				String logo = ExtraConstants.IMAGEURL;
		//		String logo = "../../images/logo.jpg";
				String copyrtext = "All  rights reserved by HANBIT ® Hyderabad";

				PdfWriter.getInstance(document, new FileOutputStream(drivename
						+ transactionID + ".pdf"));

				document.open();
				
	//			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/src/main/webapp/images/logo.jpg"));
				Image image = Image.getInstance(logo);
				image.scaleAbsolute(100, 100);
				image.setWidthPercentage(50);

				document.add((Element) image);

				String myPhrase = "\n";
				Paragraph p = new Paragraph(new Phrase(myPhrase));
				document.add(p);
				Chunk chunk = new Chunk(head);
				Font font = new Font(Font.TIMES_ROMAN);
				font.setSize(15);
				chunk.setFont(font);

				// document.add(chunk);

				Paragraph paragraph = new Paragraph();
				paragraph.add(chunk);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);

				Chunk chunk1 = new Chunk("AMR ID:" + meter_id);
				Font font1 = new Font(Font.TIMES_ROMAN);
				font1.setSize(10);
				chunk1.setFont(font1);

				// document.add(chunk);

				Paragraph paragraph1 = new Paragraph();
				paragraph1.add(chunk1);
				paragraph1.setAlignment(Element.ALIGN_LEFT);
				document.add(paragraph1);

				Font font_for_table = new Font(Font.TIMES_ROMAN);
				font_for_table.setSize(5);
				document.add(p);
				PdfPTable table = new PdfPTable(7);
				// table.setWidthPercentage(100);

				document.add(table);

				PdfPTable tablenew = new PdfPTable(7);
				tablenew.setWidthPercentage(100);
				PdfPTable tablenew1 = new PdfPTable(7);

				tablenew.addCell("Bill No");
				tablenew.addCell("Meter ID");
				tablenew.addCell("House no");
				tablenew.addCell("Recharge Amount");
				tablenew.addCell("Emergency Credit");
				tablenew.addCell("Alarm Credit");
				tablenew.addCell("Date Time");
				// tablenew.addCell("Status");

				String sql = "select rt.Transid,rt.Meter_id,c.house_no,rt.RechargeAmt,rt.EmergencyCredit,rt.AlaramCredit,rt.Send_DateTime from Recharge_Transaction rt join customer c on c.cust_id=rt.CUST_ID where rt.Transid=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, transactionID);
				ResultSet rs1 = ps.executeQuery();

				while (rs1.next()) {

					bill_no = rs1.getString("Transid");
					meter_id = rs1.getString("Meter_id");
					house_no = rs1.getString("house_no");
					Recharge_amnt = rs1.getString("RechargeAmt");
					e_credit = rs1.getString("EmergencyCredit");
					a_credit = rs1.getString("AlaramCredit");
					date_time = rs1.getString("Send_DateTime");
					// status=rs1.getString("Send_Status");
					// System.out.println("status"+status);

					tablenew.addCell(bill_no);
					tablenew.addCell(meter_id);
					tablenew.addCell(house_no);
					tablenew.addCell(Recharge_amnt);
					tablenew.addCell(e_credit);
					tablenew.addCell(a_credit);
					tablenew.addCell(date_time);
					// tablenew.addCell(status);
				}
				table.addCell(tablenew1);
				document.add(tablenew);
				String myPhrase3 = "\n";
				Paragraph p3 = new Paragraph(new Phrase(myPhrase3));
				document.add(p3);
				String myPhrase4 = "\n";
				Paragraph p4 = new Paragraph(new Phrase(myPhrase4));
				document.add(p4);
				String myPhrase5 = "\n";
				Paragraph p5 = new Paragraph(new Phrase(myPhrase5));
				document.add(p5);
				String myPhrase6 = "\n";
				Paragraph p6 = new Paragraph(new Phrase(myPhrase6));
				document.add(p6);
				String myPhrase7 = copyrtext;
				Paragraph p7 = new Paragraph(new Phrase(myPhrase7));
				p7.setAlignment(Element.ALIGN_CENTER);
				// document.setFooter(footer);
				document.add(p7);
				document.close();
				result = "Success";
				// out.println("PDF Created Sucessfully...");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			// ps.close();
			rs.close();
			con.close();
		}
		return result;

	}

	/* Fixed Charges */

	public List<FixedChargesResponseVO> getFixedChargesdetails()
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FixedChargesResponseVO> fixedchargeslist = null;
		try {
			con = getConnection();
			fixedchargeslist = new LinkedList<FixedChargesResponseVO>();
			pstmt = con
					.prepareStatement("select tc.com_name as cname,tb.name as bname,ttf.custid as cid, tcu.house_no as hno,tcu.first_name as fname,tcu.last_name as lname,tcm.meter_id as mid,ttf.TotalConsumption as totconsume,ttf.BillingMonth as bmonth,ttf.FixedCharges as fcharges,ttf.PaidStatus as pstatus from community tc,block tb,customer tcu,customer_meter tcm,tbl_Tr_FixedCharge ttf where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id=ttf.MeterNo and tc.com_id=?");
			pstmt.setInt(1, LoginDAO.CommunityID);
			rs = pstmt.executeQuery();
			FixedChargesResponseVO fixedhargesvo = null;

			while (rs.next()) {
				fixedhargesvo = new FixedChargesResponseVO();

				fixedhargesvo.setCommunityName(rs.getString("cname"));
				fixedhargesvo.setBlockName(rs.getString("bname"));
				fixedhargesvo.setName(rs.getString("fname") + " "
						+ rs.getString("lname"));
				fixedhargesvo.setHouseNo(rs.getString("hno"));
				fixedhargesvo.setMeterNo(rs.getString("mid"));
				fixedhargesvo.setUnitsConsumed(rs.getString("totconsume"));
				fixedhargesvo.setBillingMonth(rs.getString("bmonth"));
				fixedhargesvo.setFixedCharges(rs.getString("fcharges"));
				if (Integer.parseInt(rs.getString("pstatus")) == 1) {
					fixedhargesvo.setPaymentStatus("Paid");
				} else {
					fixedhargesvo.setPaymentStatus("UnPaid");
				}

				fixedchargeslist.add(fixedhargesvo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return fixedchargeslist;

	}

	/* Configuration */

	public List<ConfigurationResponseVO> getConfigurationdetails(int roleid, int id)
			throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ConfigurationResponseVO> configurationdetailslist = null;
		try {
			con = getConnection();
			configurationdetailslist = new LinkedList<ConfigurationResponseVO>();

			// complete the query for Admin and Superadmin level login
			
			
			pstmt = con.prepareStatement("SELECT TransactionID, CustomerID, MeterID, CommandType, ModifiedDate, HW_ACK FROM command");
			
			rs = pstmt.executeQuery();
			ConfigurationResponseVO configurationvo = null;

			while (rs.next()) {
				configurationvo = new ConfigurationResponseVO();
				configurationvo.setMeterID(rs.getString("MeterID"));
				
				// 5, 3, 1, 40, 0 , 6
				
				if (Integer.parseInt(rs.getString("CommandType")) == 40) {
					configurationvo.setCommandType("Solenoid Open");
				}
				if (Integer.parseInt(rs.getString("CommandType")) == 0) {
					configurationvo.setCommandType("Solenoid Close");
				}
				if (Integer.parseInt(rs.getString("CommandType")) == 3) {
					configurationvo.setCommandType("Clear Meter");
				}
				if (Integer.parseInt(rs.getString("CommandType")) == 1) {
					configurationvo.setCommandType("Clear Tamper");
				}
				if (Integer.parseInt(rs.getString("CommandType")) == 5) {
					configurationvo.setCommandType("RTC");
				}
				if (Integer.parseInt(rs.getString("CommandType")) == 6) {
					configurationvo.setCommandType("Set Default Read");
				}
				
				configurationvo.setModifiedDate(rs.getString("date"));

				if (Integer.parseInt(rs.getString("HW_ACK")) == 0) {
					configurationvo.setStatus("Pending...waiting for ack");
				}
				if (Integer.parseInt(rs.getString("HW_ACK")) == 1) {
					configurationvo.setStatus("Passed");
				}
				if (Integer.parseInt(rs.getString("HW_ACK")) == 2) {
					configurationvo.setStatus("Time Out... Resend Command");
				}
				configurationvo.setId(rs.getInt("TransactionID"));
				configurationdetailslist.add(configurationvo);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return configurationdetailslist;
	}

	public String addconfiguration(ConfigurationRequestVO configurationvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		String result = "Failure";

		try {
			con = getConnection();
					
								ps = con.prepareStatement("INSERT INTO command (CustomerID, MeterID, CommandType, HW_ACK, ModifiedDate) VALUES (?, ?, ?, 0, NOW())");
								ps.setInt(1, configurationvo.getCustomerID());
								ps.setString(2, configurationvo.getMeterID());
								ps.setInt(3, configurationvo.getCommandType());

								if (ps.executeUpdate() >0) {
									result = "Success";
									
									//send command request to tata gateway
								}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

		return result;

	}

	public String deleteconfiguration(ConfigurationRequestVO configurationvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();

			pstmt = con
					.prepareStatement("DELETE FROM command where TransactionID = ?");
			pstmt.setString(1, configurationvo.getTransactionID());

			if (pstmt.executeUpdate() > 0) {
				result = "Success";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			con.close();
		}

		return result;
	}

	public boolean checkstatus(String meterID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT TOP 1 MeterID, HW_ACK FROM command WHERE MeterID = ? order by TransactionID desc");
			pstmt.setString(1, meterID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("HW_ACK").equals("0")) {
					result = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

}
