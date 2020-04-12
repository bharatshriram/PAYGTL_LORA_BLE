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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;
		String result = "Failure";
		int customerID = 0;
		int tariffID = 0;
		try {
			con = getConnection();

			int community_id = LoginDAO.CommunityID;
			
			pstmt2 = con.prepareStatement("select block_id from block where name = ? and com_id=?");
			pstmt2.setString(1, topupvo.getBlockName());
			pstmt2.setInt(2, community_id);

			rs4 = pstmt2.executeQuery();
			if (rs4.next()) {
				int block_id = rs4.getInt("block_id");
				pstmt3 = con.prepareStatement("select cust_id from customer where house_no=? and block_id=?");
				pstmt3.setString(1,topupvo.getHouseNo());
				pstmt3.setInt(2,block_id);
				rs5 = pstmt3.executeQuery();
				if (rs5.next()) {
					customerID = rs5.getInt("cust_id");
				}
			}

			int meter_id = topupvo.getMeterID();
			String cor_ip = "1";
			float tariff = topupvo.getTariff();
			float emr_credit = topupvo.getEmergencyCredit();
			float alarm_credit = topupvo.getAlarmCredit();
			float amount = topupvo.getRechargeAmount();
			String set_tariff = topupvo.getSetTariff();
			
			String tariff_id = "";
			String com_name = "";
			String block_name = "";
			String house_no = "";
			String first_name = "";
			String last_name = "";
			String email = "";
			float totconsume;
			int pstatus = 0;
			float fcharges = 0;
			float amnt;
			int setTariff_Flag;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			DateFormat dateFormat_new = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = new Date();

			String sql2 = "select tc.com_name as comname,tb.name as bname,tcu.house_no as hno,tcu.first_name as fname,tcu.last_name as lname,tcu.email as email from community tc,block tb,customer tcu where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, customerID);
			ResultSet rs2 = ps2.executeQuery();
			if (rs2.next()) {
				com_name = rs2.getString("comname");
				block_name = rs2.getString("bname");
				house_no = rs2.getString("hno");
				first_name = rs2.getString("fname");
				last_name = rs2.getString("lname");
				email = rs2.getString("email");
			}

			String sql10 = "SELECT tc.com_name AS cname,ttf.custid AS cid,tcu.house_no AS hno,tcm.meter_id AS mid,ttf.TotalConsumption AS totconsume,ttf.FixedCharges AS fcharges,ttf.PaidStatus AS pstatus FROM community tc,block tb,customer tcu,customer_meter tcm,tbl_Tr_FixedCharge ttf WHERE tc.com_id=tb.com_id AND tb.block_id=tcu.block_id AND tcu.cust_id=tcm.cust_id AND tcm.meter_id=ttf.MeterNo AND tc.com_id=? and tcu.cust_id=?";
			PreparedStatement ps3 = con.prepareStatement(sql10);
			ps3.setInt(1, community_id);
			ps3.setInt(2, customerID);
			ResultSet rs3 = ps3.executeQuery();
			if (rs3.next()) {
				totconsume = rs3.getFloat("totconsume");
				pstatus = rs3.getInt("pstatus");
				fcharges = rs3.getFloat("fcharges");
			}

				// if(null==set_tariff)
				if ("false".equals(set_tariff)) {
					if (pstatus == 0) {
						amnt = amount - fcharges;
						setTariff_Flag = 1;
						String sql1 = "SELECT cu.tariff_id AS tid FROM community cu,tariff tf WHERE cu.tariff_id=tf.tariff_id AND cu.com_id=?";
						ps = con.prepareStatement(sql1);
						ps.setInt(1, community_id);
						rs = ps.executeQuery();
						if (rs.next()) {

							tariff_id = rs.getString("tid");
							String sql = "INSERT INTO recharge_transaction(meter_id,cust_id,tariff_id,coordinatorip,rechargeamt,alaramcredit,emergencycredit,setTariff_Flag,user_id) VALUES(?,?,?,?,?,?,?,?,?)";
							ps = con.prepareStatement(sql);
							ps.setInt(1, meter_id);
							ps.setInt(2, customerID);
							ps.setString(3, tariff_id);
							ps.setString(4, cor_ip);
							ps.setFloat(5, amnt);
							ps.setFloat(6, alarm_credit);
							ps.setFloat(7, emr_credit);
							ps.setInt(8, setTariff_Flag);
							ps.setString(9, topupvo.getUserID());
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
						ps.setInt(1, community_id);
						rs = ps.executeQuery();
						if (rs.next()) {
							tariff_id = rs.getString("tid");
							String sql = "INSERT INTO recharge_transaction(meter_id,cust_id,tariff_id,coordinatorip,rechargeamt,alaramcredit,emergencycredit,setTariff_Flag) VALUES(?,?,?,?,?,?,?,?)";
							ps = con.prepareStatement(sql);
							ps.setInt(1, meter_id);
							ps.setInt(2, customerID);
							ps.setString(3, tariff_id);
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
				if ("true".equals(set_tariff)) {

					String setTariff_Blockid = "";
					String setTariff_Custid = "";
					String setTariff_Meterid = "";
					setTariff_Flag = 2;
					String setTariff_Amount = "0";
					// String setTariff_Emecredit="0";
					// String setTariff_AlarmCredit="0";
					String sql1 = "SELECT cu.tariff_id AS tid FROM community cu,tariff tf WHERE cu.tariff_id=tf.tariff_id AND cu.com_id=?";
					ps = con.prepareStatement(sql1);
					ps.setInt(1, community_id);
					rs = ps.executeQuery();
					if (rs.next()) {
						tariff_id = rs.getString("tid");
						String sql3 = "select block_id from block where com_id=?";
						ps = con.prepareStatement(sql3);
						ps.setInt(1, community_id);
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
								ps.setString(3, tariff_id);
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

	public List<StatusResponseVO> getStatusdetails() throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StatusResponseVO> statuslist = null;
		StatusResponseVO statusvo = null;
		try {
			con = getConnection();
			statuslist = new LinkedList<StatusResponseVO>();
			String sqlQuery = "select tb.name as bname,tcu.house_no as hno,tcu.first_name as fname,tcu.last_name as lname,tcu.email as email,tcu.mobile as mobile,trt.Meter_id as MID,trt.Transid as TID,trt.CoOrdinatorIP as cop,trt.RechargeAmt as rmt,trt.AlaramCredit as ac,trt.EmergencyCredit as ec,trt.Send_DateTime as dt,trt.RecordInsertTime as ri,trt.Send_Status as ss,acks =CASE trt.ack_status WHEN 0 THEN 0 WHEN 1 THEN 1 ELSE 2 END,trt.user_id as uid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm,Recharge_Transaction trt where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id=trt.Meter_id and trt.Meter_id=tmm.meter_id and trt.setTariff_Flag=1 order by trt.RecordInsertTime DESC";
			pstmt = con.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				statusvo = new StatusResponseVO();
				statusvo.setTransID(rs.getInt("TID"));
				statusvo.setHouseNo(rs.getString("hno"));
				statusvo.setMeterID(rs.getString("mid"));
				statusvo.setRechargeAmount(rs.getString("rmt"));
				statusvo.setAlarmCredit(rs.getString("ac"));
				statusvo.setEmergencyCredit(rs.getString("ec"));
				statusvo.setRecordTime(rs.getString("ri"));
				statusvo.setUserID(rs.getString("uid"));

				if (Integer.parseInt(rs.getString("acks")) == 1) {
					statusvo.setAckStatus("Passed");
				} else if (Integer.parseInt(rs.getString("acks")) == 0) {
					statusvo.setAckStatus("Time Out... Resend");
				} else if (Integer.parseInt(rs.getString("acks")) == 2) {
					statusvo.setAckStatus("Pending...waiting for ack");
				}
				statusvo.setId(rs.getInt("TID"));
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

			pstmt = con
					.prepareStatement("delete from Recharge_Transaction where transid=?");
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

	public List<ConfigurationResponseVO> getConfigurationdetails()
			throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ConfigurationResponseVO> configurationdetailslist = null;
		try {
			con = getConnection();
			configurationdetailslist = new LinkedList<ConfigurationResponseVO>();
			String sqlQuery = "select tb.name as bname,tcu.house_no as hno,tcu.first_name as fname,tcu.last_name as lname,tcu.email as email,tcu.mobile as mobile,trc.Cust_Id as cid,trc.Meter_id as mid,trc.CommandType as ctype,trc.ModifiedDate as date,trc.HW_ACK as status,trc.Cmd_Trans_id AS ctransid,trc.CommandID AS Cmdid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm,CmdRequest trc where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id=trc.Meter_id and trc.Meter_id=tmm.meter_id and tc.com_id=? order by trc.ModifiedDate DESC";
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.setInt(1, LoginDAO.CommunityID);
			rs = pstmt.executeQuery();
			ConfigurationResponseVO configurationvo = null;

			while (rs.next()) {
				configurationvo = new ConfigurationResponseVO();
				configurationvo.setMeterID(rs.getString("mid"));

				if (Integer.parseInt(rs.getString("ctype")) == 40) {
					configurationvo.setCommandType("Solenoid Open");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 0) {
					configurationvo.setCommandType("Solenoid Close");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 3) {
					configurationvo.setCommandType("Clear Meter");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 1) {
					configurationvo.setCommandType("Clear Tamper");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 5) {
					configurationvo.setCommandType("RTC");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 6) {
					configurationvo.setCommandType("Set Default Read");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 7) {
					configurationvo.setCommandType("Active Mode");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 8) {
					configurationvo.setCommandType("Shutdown Mode");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 9) {
					configurationvo.setCommandType("Maintenance Mode");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 10) {
					configurationvo.setCommandType("Set Weekend");
				}
				if (Integer.parseInt(rs.getString("ctype")) == 50) {
					configurationvo.setCommandType("Set Weekend For All");
				}

				configurationvo.setModifiedDate(rs.getString("date"));

				if (Integer.parseInt(rs.getString("status")) == 0) {
					configurationvo.setStatus("Pending...waiting for ack");
				}
				if (Integer.parseInt(rs.getString("status")) == 1) {
					configurationvo.setStatus("Passed");
				}
				if (Integer.parseInt(rs.getString("status")) == 2) {
					configurationvo.setStatus("Time Out... Resend Command");
				}
				configurationvo.setId(rs.getInt("ctransid"));
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
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement ps = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		String result = "Failure";
		int cmd_id = 0;
		String get_meterid = "";
		String get_custid = "";

		try {
			con = getConnection();

			int community_id = LoginDAO.CommunityID;
			int block_id = 0;
			int customer_id = 0;
			pstmt2 = con
					.prepareStatement("select block_id from block where name = ? and com_id=?");
			pstmt2.setString(1, configurationvo.getBlockName());
			pstmt2.setInt(2, LoginDAO.CommunityID);

			rs4 = pstmt2.executeQuery();
			if (rs4.next()) {
				block_id = rs4.getInt("block_id");
			}

			pstmt3 = con
					.prepareStatement("select cust_id from customer where house_no=? and block_id=?");
			pstmt3.setString(1, configurationvo.getHouseNo());
			pstmt3.setInt(2, block_id);
			rs3 = pstmt3.executeQuery();
			if (rs3.next()) {
				customer_id = rs3.getInt("cust_id");
			}

			String meter_id = configurationvo.getMeterID();
			int command_type = Integer.parseInt(configurationvo
					.getCommandType());
			String cor_ip = "";
			int tariff_id = 0;
			int cmd_tra_id = 0;

			String sql5 = "SELECT TOP 1 Meter_id,HW_ACK FROM CmdRequest where Meter_id=? order by CommandID desc";
			String status = "";
			PreparedStatement ps5 = con.prepareStatement(sql5);
			ps5.setString(1, meter_id);
			ResultSet rs5 = ps5.executeQuery();
			if (rs5.next()) {
				status = rs5.getString("HW_ACK");
				if (status.equals("0")) {
					result = "Failure";
				} else {
					if (command_type == 11) {
						int get_command_type = 10;
						String sql2 = "select tcm.cust_id as cid,tcm.meter_id as mid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id=tmm.meter_id and tc.com_id=?";
						PreparedStatement ps2 = con.prepareStatement(sql2);
						ps2.setInt(1, community_id);
						rs2 = ps2.executeQuery();
						while (rs2.next()) {
							get_custid = rs2.getString("cid");
							get_meterid = rs2.getString("mid");

							String sql1 = "select MAX(CommandID) as cid from CmdRequest";
							ps = con.prepareStatement(sql1);
							rs1 = ps.executeQuery();
							if (rs1.next()) {
								cmd_id = rs1.getInt("cid");
								// cmd_tra_id=rs1.getInt("ctd");
								cmd_id = cmd_id + 1;
								// cmd_tra_id=cmd_tra_id+1;
								// System.out.println("cmd_tra_id:"+cmd_tra_id);
								String sql = "insert into CmdRequest(commandid,Cust_Id,Meter_id,CommandType) values(?,?,?,?)";
								ps = con.prepareStatement(sql);
								ps.setInt(1, cmd_id);
								// ps.setInt(2, cmd_tra_id);
								ps.setString(2, get_custid);
								ps.setString(3, get_meterid);
								ps.setInt(4, get_command_type);
								// ps.setString(4, cor_ip);

								if (!ps.execute()) {

								} else {
									result = "Failure";
								}
							}
						}
						result = "Success";
					} else {

						String sql1 = "select MAX(CommandID) as cid from CmdRequest";
						ps = con.prepareStatement(sql1);
						rs1 = ps.executeQuery();
						if (rs1.next()) {
							cmd_id = rs1.getInt("cid");
							// cmd_tra_id=rs1.getInt("ctd");
							cmd_id = cmd_id + 1;
							// cmd_tra_id=cmd_tra_id+1;
							// System.out.println("cmd_tra_id:"+cmd_tra_id); */

							String sql = "insert into CmdRequest(commandid,Cust_Id,Meter_id,CommandType) values(?,?,?,?)";
							ps = con.prepareStatement(sql);
							ps.setInt(1, cmd_id);
							// ps.setInt(2, cmd_tra_id);
							ps.setInt(2, customer_id);
							ps.setString(3, meter_id);
							ps.setInt(4, command_type);
							// ps.setString(4, cor_ip);

							if (!ps.execute()) {
								result = "Success";
							} else {
								result = "Failure";
							}
							// } }
						}
					}
				}
			}
			// else goes here
			else {
				if (command_type == 11) {
					int get_command_type = 10;
					String sql2 = "select tcm.cust_id as cid,tcm.meter_id as mid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id=tmm.meter_id and tc.com_id=?";
					PreparedStatement ps2 = con.prepareStatement(sql2);
					ps2.setInt(1, community_id);
					rs2 = ps2.executeQuery();

					while (rs2.next()) {
						get_custid = rs2.getString("cid");
						get_meterid = rs2.getString("mid");

						String sql1 = "select MAX(CommandID) as cid from CmdRequest";
						ps = con.prepareStatement(sql1);
						rs1 = ps.executeQuery();

						if (rs1.next()) {
							cmd_id = rs1.getInt("cid");
							// cmd_tra_id=rs1.getInt("ctd");
							cmd_id = cmd_id + 1;
							// cmd_tra_id=cmd_tra_id+1;
							// System.out.println("cmd_tra_id:"+cmd_tra_id);
							String sql = "insert into CmdRequest(commandid,Cust_Id,Meter_id,CommandType) values(?,?,?,?)";
							ps = con.prepareStatement(sql);
							ps.setInt(1, cmd_id);
							// ps.setInt(2, cmd_tra_id);
							ps.setString(2, get_custid);
							ps.setString(3, get_meterid);
							ps.setInt(4, get_command_type);
							// ps.setString(4, cor_ip);

							if (!ps.execute()) {
							} else {
								result = "Failure";
							}
						}
					}
					result = "Success";
				} else {

					String sql1 = "select MAX(CommandID) as cid from CmdRequest";
					ps = con.prepareStatement(sql1);
					rs1 = ps.executeQuery();
					if (rs1.next()) {
						cmd_id = rs1.getInt("cid");
						// cmd_tra_id=rs1.getInt("ctd");
						cmd_id = cmd_id + 1;
						// cmd_tra_id=cmd_tra_id+1;
						// System.out.println("cmd_tra_id:"+cmd_tra_id); */

						String sql = "insert into CmdRequest(commandid,Cust_Id,Meter_id,CommandType) values(?,?,?,?)";
						ps = con.prepareStatement(sql);
						ps.setInt(1, cmd_id);
						// ps.setInt(2, cmd_tra_id);
						ps.setInt(2, customer_id);
						ps.setString(3, meter_id);
						ps.setInt(4, command_type);
						// ps.setString(4, cor_ip);

						if (!ps.execute()) {
							result = "Success";
						} else {
							result = "Failure";
						}
					}
				}
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
					.prepareStatement("delete from CmdRequest where Cmd_Trans_id=?");
			pstmt.setString(1, configurationvo.getCommandTransID());

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
			String sql5 = "SELECT TOP 1 Meter_id,HW_ACK FROM CmdRequest where Meter_id=? order by CommandID desc";
			pstmt = con.prepareStatement(sql5);
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
