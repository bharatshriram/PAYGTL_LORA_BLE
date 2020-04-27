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
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ConfigurationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.RestCallVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.StatusRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ConfigurationResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.StatusResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TataResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.utils.Encoding;
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
		String result = "Failure";
		Random randomNumber = new Random();
		Gson gson = new Gson();
		String hexaAmount = "";
		String hexaEmergencyCredit = "";
		String hexaAlarmCredit = "";
		String hexaTariff = "";
		
		try {
				con = getConnection();
				
				if(topupvo.getSource().equalsIgnoreCase("web")) {
					
					PreparedStatement pstmt1 = con.prepareStatement("SELECT tr.EmergencyCredit, tr.AlarmCredit, tr.TariffID, tr.Tariff FROM customermeterdetails as cmd LEFT JOIN tariff AS tr ON tr.TariffID = cmd.TariffID WHERE cmd.CRNNumber = ?");
					pstmt1.setString(1, topupvo.getCRNNumber());
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {

					hexaAmount = Integer.toHexString(Float.floatToIntBits(topupvo.getAmount())).toUpperCase();

					hexaAlarmCredit = Integer.toHexString(Float.floatToIntBits(rs1.getFloat("AlarmCredit"))).toUpperCase();

					hexaEmergencyCredit = Integer.toHexString(Float.floatToIntBits(rs1.getFloat("EmergencyCredit"))).toUpperCase();

					hexaTariff = Integer.toHexString(Float.floatToIntBits(rs1.getFloat("Tariff"))).toUpperCase();
					
					}
					
					String serialNumber = String.format("%04x", randomNumber.nextInt(65000));
					
					// 0A18000001020C0023  41200000  41200000  41200000   41200000           17
					//                    credit    lowcredit--Alarm EmgCredit lowemgcredit--TAriff
					String dataFrame = "0A1800" + serialNumber + "020C0023" + hexaAmount + hexaAlarmCredit	+ hexaEmergencyCredit + hexaTariff + "17";

					ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
					RestCallVO restcallvo = new RestCallVO();
					
					restcallvo.setDataFrame(Encoding.getHexBase644(dataFrame));
					restcallvo.setMeterID(topupvo.getMeterID().toLowerCase());
					
					String restcallresponse = extramethodsdao.restcallpost(restcallvo);
					
					TataResponseVO tataResponseVO = gson.fromJson(restcallresponse, TataResponseVO.class);
					
					//check for payment status with the payment gateway
					
					topupvo.setTransactionIDForTata(tataResponseVO.getId());
					topupvo.setStatus(tataResponseVO.getTransmissionStatus());
					
					result = inserttopup(topupvo);
					
				} else {
					topupvo.setTransactionIDForTata(0);
					result = inserttopup(topupvo);
				}
					
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// pstmt.close();
			// ps.close();
			con.close();
		}

		return result;
	}
	
	public String inserttopup(TopUpRequestVO topUpRequestVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		String result = "Failure";

		// check for payment status with the payment gateway

		try {
			con = getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT CommunityID, BlockID, CustomerID, MeterID, TariffID FROM customermeterdetails WHERE CRNNumber = ?");
			pstmt.setString(1, topUpRequestVO.getCRNNumber());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				
			String sql = "INSERT INTO topup (TataReferenceNumber, CommunityID, BlockID, CustomerID, MeterID, TariffID, Amount, Status, ModeOfPayment, PaymentStatus, Source, CreatedByID, CreatedByRoleID, CRNNumber, AcknowledgeDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, topUpRequestVO.getTransactionIDForTata());					
			ps.setInt(2, rs.getInt("CommunityID")); 
			ps.setInt(3, rs.getInt("BlockID"));
			ps.setInt(4, rs.getInt("CustomerID"));
			ps.setString(5, rs.getString("MeterID"));
			ps.setInt(6, rs.getInt("TariffID"));
			ps.setFloat(7, topUpRequestVO.getAmount());
			ps.setInt(8, topUpRequestVO.getStatus());
			ps.setString(9, topUpRequestVO.getModeOfPayment());
			ps.setInt(10, 0); // payment status from payment gateway
			ps.setString(11, topUpRequestVO.getSource());
			ps.setFloat(12, topUpRequestVO.getTransactedByID());
			ps.setInt(13, topUpRequestVO.getTransactedByRoleID());
			ps.setString(14, topUpRequestVO.getCRNNumber());

			if (ps.executeUpdate() > 0) {
				result = "Success";

			}
		}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	/* Status */

	public List<StatusResponseVO> getStatusdetails(int roleid, String id, HttpServletRequest req) throws SQLException {
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
			
			String query = "SELECT 	DISTINCT t.TransactionID, c.CommunityName, b.BlockName, cmd.FirstName, cmd.HouseNumber, cmd.CreatedByID, cmd.LastName, cmd.CRNNumber, t.MeterID, t.Amount, tr.AlarmCredit, tr.EmergencyCredit, t.Status, t.ModeOfPayment, t.PaymentStatus, t.TransactionDate, t.AcknowledgeDate FROM topup AS t \r\n" + 
							"LEFT JOIN community AS c ON t.CommunityID = c.CommunityID LEFT JOIN block AS b ON t.BlockID = b.BlockID LEFT JOIN tariff AS tr ON tr.TariffID = t.tariffID \r\n" + 
							"LEFT JOIN customermeterdetails AS cmd ON t.CRNNumber = cmd.CRNNumber <change>";
			
			query = query.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "WHERE t.BlockID = "+id : (roleid == 3) ? "WHERE t.CRNNumber = "+id:"");

			String columnNames[] = { "t.TransactionID", "c.CommunityName", "b.BlockName", "cmd.FirstName", "cmd.HouseNumber", "cmd.CreatedByID", "cmd.LastName", "cmd.CRNNumber", "t.MeterID", "t.Amount", "tr.AlarmCredit", "tr.EmergencyCredit", "t.Status", "t.ModeOfPayment", "t.PaymentStatus", "t.TransactionDate", "t.AcknowledgeDate" };
			String columnName = "";
			String direction = "";
			String globalSearchUnit = "";
			String searchSQL = "";
			String pageNo = req.getParameter("start");
			String pageSize = req.getParameter("length");
			Integer initial = null;
			Integer recordSize = null;
			String orderByColumnIndex = req.getParameter("order[0][column]");

			columnName = columnNames[Integer.parseInt(orderByColumnIndex)];
			direction = req.getParameter("order[0][dir]");

			globalSearchUnit = req.getParameter("search[value]");
			
			if (!StringUtils.isEmpty(pageNo)) {
				initial = Integer.parseInt(pageNo);
			}
			if (!StringUtils.isEmpty(pageNo)) {
				recordSize = Integer.parseInt(pageSize);
			}
			StringBuilder sql = new StringBuilder();
			sql.append(query);
			
			PreparedStatement pstmt3 = con.prepareStatement(query);

			ResultSet rs3 = pstmt3.executeQuery();
			rs3.beforeFirst();  
			rs3.last();  
			Integer totalRowCount =rs3.getRow();
			
			String globeSearch = "t.TransactionID like '%" + globalSearchUnit + "%'" + " or c.CommunityName like '%" + globalSearchUnit + "%'" + " or b.BlockName like '%" + 
					  globalSearchUnit  + "%'" + " or cmd.FirstName like '%" + globalSearchUnit + "%'" + " or cmd.LastName like '%" + globalSearchUnit + "%'" + " or cmd.HouseNumber like '%" + globalSearchUnit + "%'"
					  + " or cmd.CreatedByID like '%" + globalSearchUnit + "%'" + " or cmd.CRNNumber like '%" + globalSearchUnit + "%'" + " or t.MeterID like '%" + globalSearchUnit + "%'" + " or t.Amount like '%" + globalSearchUnit + "%'" +
					  " or tr.AlarmCredit like '%" + globalSearchUnit + "%'" + " or tr.EmergencyCredit like '%" + globalSearchUnit + "%'" + " or t.Status like '%" + globalSearchUnit + "%'" + " or t.ModeOfPayment like '%" + globalSearchUnit + "%'"
					  + " or t.PaymentStatus like '%" + globalSearchUnit + "%'" + " or t.TransactionDate like '%" + globalSearchUnit + "%'" + " or t.AcknowledgeDate like '%" + globalSearchUnit + "%'";

			if (!StringUtils.isEmpty(globalSearchUnit)) {
				searchSQL = globeSearch;
			}

			if (!StringUtils.isEmpty(searchSQL)) {
				sql.append((roleid == 1 || roleid == 4) ? " WHERE " : (roleid == 2 || roleid == 3 || roleid == 5) ? " AND " : "");
				sql.append(searchSQL);
				
			} else {
				sql.append("ORDER BY t.TransactionDate DESC");
			}
			
			if (columnName.equalsIgnoreCase("t.TransactionID")) {
				sql.append(" order by " + columnName + " " + direction);
			}
			
			PreparedStatement pstmt2 = con.prepareStatement(sql.toString());

			ResultSet rs2 = pstmt2.executeQuery();
			rs2.beforeFirst();  
			rs2.last();  
			  //size = rs2.getRow(); 
			Integer searchRowCount =rs2.getRow();
			
			sql.append(" limit " + recordSize + " offset " + initial);
			
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				statusvo = new StatusResponseVO();
				statusvo.setTransactionID(rs.getInt("TransactionID"));
				statusvo.setCommunityName(rs.getString("CommunityName"));
				statusvo.setBlockName(rs.getString("BlockName"));
				statusvo.setFirstName(rs.getString("FirstName"));
				statusvo.setLastName(rs.getString("LastName"));
				statusvo.setHouseNumber(rs.getString("HouseNumber"));
				statusvo.setCRNNumber(rs.getString("CRNNumber"));
				statusvo.setMeterID(rs.getString("MeterID"));
				statusvo.setAmount(rs.getString("Amount"));
				statusvo.setModeOfPayment(rs.getString("ModeOfPayment"));
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
				
				statusvo.setiTotalDisplayRecords(searchRowCount);
				statusvo.setiTotalRecords(totalRowCount);
				statuslist.add(statusvo);
			}
			
			if(statuslist.size() == 0) {
				statusvo = new StatusResponseVO();
				statusvo.setiTotalDisplayRecords(0);
				statusvo.setiTotalRecords(0);
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

	/* Configuration */

	public List<ConfigurationResponseVO> getConfigurationdetails(int roleid, String id, HttpServletRequest req)
			throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ConfigurationResponseVO> configurationdetailslist = null;
		try {
			con = getConnection();
			configurationdetailslist = new LinkedList<ConfigurationResponseVO>();

			String query = "SELECT cmd.TransactionID, cmd.CRNNumber, cmd.MeterID, cmd.CommandType, cmd.ModifiedDate, cmd.Status FROM command AS cmd \r\n" + 
					"LEFT JOIN customermeterdetails AS cm ON cm.CRNNumber = cmd.CRNNumber\r\n" + 
					"LEFT JOIN community AS c ON cm.CommunityID = c.CommunityID\r\n" + 
					"LEFT JOIN block AS b ON cm.BlockID = b.blockID <change>";
			
			query = query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? " ORDER BY cmd.ModifiedDate DESC" : (roleid == 2 || roleid == 5) ? "WHERE cm.BlockID = "+id+ " ORDER BY cmd.ModifiedDate DESC" : (roleid == 3) ? "WHERE cm.CRNNumber = "+id+ " ORDER BY cmd.ModifiedDate DESC":"");
			
			String columnNames[] = { "cmd.TransactionID", "cmd.CRNNumber", "cmd.MeterID", "cmd.CommandType", "cmd.ModifiedDate", "cmd.Status" };
			String columnName = "";
			String direction = "";
			String globalSearchUnit = "";
			String searchSQL = "";
			String pageNo = req.getParameter("start");
			String pageSize = req.getParameter("length");
			Integer initial = null;
			Integer recordSize = null;
			String orderByColumnIndex = req.getParameter("order[0][column]");

			columnName = columnNames[Integer.parseInt(orderByColumnIndex)];
			direction = req.getParameter("order[0][dir]");

			globalSearchUnit = req.getParameter("search[value]");
			
			if (!StringUtils.isEmpty(pageNo)) {
				initial = Integer.parseInt(pageNo);
			}
			if (!StringUtils.isEmpty(pageNo)) {
				recordSize = Integer.parseInt(pageSize);
			}
			StringBuilder sql = new StringBuilder();
			sql.append(query);
			
			PreparedStatement pstmt3 = con.prepareStatement(query);

			ResultSet rs3 = pstmt3.executeQuery();
			rs3.beforeFirst();  
			rs3.last();  
			Integer totalRowCount =rs3.getRow();
			
			String globeSearch = "cmd.CRNNumber like '%" + globalSearchUnit + "%'" + " or cmd.MeterID like '%" + globalSearchUnit + "%'" + " or cmd.CommandType like '%" + globalSearchUnit
								  + "%'" + " or cmd.ModifiedDate like '%" + globalSearchUnit + "%'" + " or cmd.Status like '%" + globalSearchUnit + "%'";

			if (!StringUtils.isEmpty(globalSearchUnit)) {
				searchSQL = globeSearch;
			}

			if (!StringUtils.isEmpty(searchSQL)) {
				sql.append((roleid == 1) ? "ORDER BY c.CommunityID DESC" : (roleid == 2 || roleid == 3 || roleid == 5) ? " AND " : "");
				sql.append(searchSQL);
				
			}
			
			if (!columnName.equalsIgnoreCase("c.CreatedDate")) {
				sql.append(" order by " + columnName + " " + direction);
			}
			
			PreparedStatement pstmt2 = con.prepareStatement(sql.toString());

			ResultSet rs2 = pstmt2.executeQuery();
			rs2.beforeFirst();  
			rs2.last();  
			  //size = rs2.getRow(); 
			Integer searchRowCount =rs2.getRow();
			
			sql.append(" limit " + recordSize + " offset " + initial);
			
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			ConfigurationResponseVO configurationvo = null;

			while (rs.next()) {
				configurationvo = new ConfigurationResponseVO();
				configurationvo.setMeterID(rs.getString("MeterID"));
				
				// 5, 3, 1, 40, 0 , 6, 10
				
				if (Integer.parseInt(rs.getString("CommandType")) == 40) {
					configurationvo.setCommandType("Solenoid Open");
				}
				else if (Integer.parseInt(rs.getString("CommandType")) == 0) {
					configurationvo.setCommandType("Solenoid Close");
				}
				else if (Integer.parseInt(rs.getString("CommandType")) == 3) {
					configurationvo.setCommandType("Clear Meter");
				}
				else if (Integer.parseInt(rs.getString("CommandType")) == 1) {
					configurationvo.setCommandType("Clear Tamper");
				}
				else if (Integer.parseInt(rs.getString("CommandType")) == 5) {
					configurationvo.setCommandType("RTC");
				}
				else if (Integer.parseInt(rs.getString("CommandType")) == 6) {
					configurationvo.setCommandType("Set Default Read");
				}
				else if (Integer.parseInt(rs.getString("CommandType")) == 10) {
					configurationvo.setCommandType("Set Tariff");
				}
				
				configurationvo.setModifiedDate(rs.getString("ModifiedDate"));

				if (Integer.parseInt(rs.getString("Status")) == 0) {
					configurationvo.setStatus("Pending...waiting for ack");
				}
				else if (Integer.parseInt(rs.getString("Status")) == 1) {
					configurationvo.setStatus("Pending");
				}
				else if (Integer.parseInt(rs.getString("Status")) == 2) {
					configurationvo.setStatus("Passed");
				}
				else {
					configurationvo.setStatus("Failed");
				}
				configurationvo.setTransactionID(rs.getInt("TransactionID"));
				
				configurationvo.setiTotalDisplayRecords(searchRowCount);
				configurationvo.setiTotalRecords(totalRowCount);
				
				configurationdetailslist.add(configurationvo);

			}
			
			if(configurationdetailslist.size() == 0) {
				configurationvo = new ConfigurationResponseVO();
				configurationvo.setiTotalDisplayRecords(0);
				configurationvo.setiTotalRecords(0);
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
		Random randomNumber = new Random();
		Gson gson = new Gson();
		String dataframe = "";

		try {
				con = getConnection();
				
					String serialNumber = String.format("%04x", randomNumber.nextInt(65000));
					
					/* RTC */
					
					if (configurationvo.getCommandType() == 5) {

						LocalDateTime now = LocalDateTime.now();

						String currentday = String.format("%02x", now.getDayOfMonth());

						String currentmonth = String.format("%02x", now.getMonthValue());

						String currentyear = String.format("%02x", now.getYear());

						String currenthour = String.format("%02x", now.getHour());

						String currentminute = String.format("%02x", now.getMinute());

						String currentsecond = String.format("%02x", now.getSecond());

						dataframe = "0A0F00" + serialNumber + "02090041" + currentday + currentmonth
								+ currentyear + currenthour + currentminute + currentsecond + "0017";

					}

					/* Clear Meter */

					else if (configurationvo.getCommandType() == 3) {

						dataframe = "0A0900" + serialNumber + "020800200117";

					}

					/* Clear Tamper */

					else if (configurationvo.getCommandType() == 1) {

						dataframe = "0A0900" + serialNumber + "020300200117";
					}

					else if (configurationvo.getCommandType() == 40) {

						dataframe = "0A0900" + serialNumber + "020301200017";
					}

					else if (configurationvo.getCommandType() == 0) {

						dataframe = "0A0900" + serialNumber + "020301200117";
					}

					// Set Default Read

					else if (configurationvo.getCommandType() == 6) {
						
							String defaultSetHexa = String.format("%08x", configurationvo.getDefaultReading());

							dataframe = "0A0C00" + serialNumber + "02000023" + defaultSetHexa + "17";
	
						}
					
					// set tariff 
					
					else if (configurationvo.getCommandType() == 10) {
						
						PreparedStatement pstmt1 = con.prepareStatement("SELECT Tariff FROM tariff WHERE TariffID = "+configurationvo.getTariffID());
						ResultSet rs1 = pstmt1.executeQuery();
						if(rs1.next()) {

						String tariffHexa = Float.toHexString(rs1.getFloat("Tariff")).toUpperCase();
						dataframe = "0A0C00" + serialNumber + "02070123" + tariffHexa + "17";
						}
						
						PreparedStatement pstmt2 = con.prepareStatement("UPDATE customermeterdetails SET TariffID = ? Where CRNNumber = ?");
						pstmt2.setInt(1, configurationvo.getTariffID());
						pstmt2.setString(2, configurationvo.getCRNNumber());
						pstmt2.executeUpdate();
						
					}
					
					ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
					RestCallVO restcallvo = new RestCallVO();
					
					restcallvo.setMeterID(configurationvo.getMeterID().toLowerCase());
					restcallvo.setDataFrame(Encoding.getHexBase644(dataframe));

					TataResponseVO tataResponseVO = gson.fromJson(extramethodsdao.restcallpost(restcallvo), TataResponseVO.class);
						
					PreparedStatement pstmt1 = con.prepareStatement("SELECT CustomerID FROM customermeterdetails WHERE CRNNumber = ?");
					pstmt1.setString(1, configurationvo.getCRNNumber());
					ResultSet rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
						configurationvo.setCustomerID(rs1.getInt("CustomerID"));
					}
					
					ps = con.prepareStatement("INSERT INTO command (TataReferenceNumber, CustomerID, MeterID, CommandType, Status, CRNNumber, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, NOW())");
					ps.setLong(1, tataResponseVO.getId());
					ps.setInt(2, configurationvo.getCustomerID());
					ps.setString(3, configurationvo.getMeterID());
					ps.setInt(4, configurationvo.getCommandType());
					ps.setInt(5, tataResponseVO.getTransmissionStatus());
					ps.setString(6, configurationvo.getCRNNumber());

					if (ps.executeUpdate() > 0) {
						result = "Success";
						}
						
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ps.close();
			con.close();
		}

		return result;

	}

	public String deleteconfiguration(int transactionID)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String result = "Failure";

		try {
			con = getConnection();

			pstmt = con.prepareStatement("DELETE FROM command WHERE TransactionID = ?");
			pstmt.setInt(1, transactionID);

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
			pstmt = con.prepareStatement("SELECT MeterID, Status FROM command WHERE MeterID = ? order by TransactionID DESC LIMIT 0,1");
			pstmt.setString(1, meterID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("Status").equals("0") || rs.getString("Status").equals("1")) {
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

	public boolean checktopup(String meterID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT MeterID, Status FROM topup WHERE MeterID = ? order by TransactionID DESC LIMIT 0,1");
			pstmt.setString(1, meterID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("Status").equals("0") || rs.getString("Status").equals("1")) {
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

	public boolean validateamount(TopUpRequestVO topupvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT tr.EmergencyCredit, tr.AlarmCredit, tr.TariffID, t.CustomerID FROM topup as t LEFT JOIN tariff AS tr ON tr.TariffID = t.TariffID WHERE t.CRNNumber = ?");
			pstmt.setString(1, topupvo.getCRNNumber());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(topupvo.getAmount() < rs.getFloat("EmergencyCredit") || topupvo.getAmount() < rs.getFloat("AlarmCredit"))
					result = true;
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
