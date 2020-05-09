/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ConfigurationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.RestCallVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.StatusRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ConfigurationResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
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
	
	Gson gson = new Gson();

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	/* TopUp */

	public ResponseVO addtopup(TopUpRequestVO topupvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		Random randomNumber = new Random();
		Gson gson = new Gson();
		String hexaAmount = "";
		String hexaEmergencyCredit = "";
		String hexaAlarmCredit = "";
		String hexaTariff = "";
		ResponseVO responsevo = new ResponseVO();
		
		try {
				con = getConnection();
				
				if(topupvo.getSource().equalsIgnoreCase("web")) {
					
					PreparedStatement pstmt1 = con.prepareStatement("SELECT tr.EmergencyCredit, tr.AlarmCredit, tr.FixedCharges, tr.TariffID, tr.Tariff FROM customermeterdetails as cmd LEFT JOIN tariff AS tr ON tr.TariffID = cmd.TariffID WHERE cmd.CRNNumber = ?");
					pstmt1.setString(1, topupvo.getCRNNumber());
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						
					LocalDateTime dateTime = LocalDateTime.now();  
						
					PreparedStatement pstmt = con.prepareStatement("SELECT MONTH(TransactionDate) AS previoustopupmonth from topup WHERE Status = 2 and CRNNumber = '"+topupvo.getCRNNumber()+"'" + "ORDER BY TransactionDate DESC LIMIT 0,1");
					ResultSet rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						if(rs.getInt("previoustopupmonth") != dateTime.getMonthValue()) {
							topupvo.setAmount(topupvo.getAmount() - (rs1.getInt("FixedCharges") * (rs.getInt("previoustopupmonth") - dateTime.getMonthValue())));
						}
						
					} else {
						topupvo.setAmount(topupvo.getAmount() - rs1.getInt("FixedCharges"));
					}
					
					System.out.println("topup amount:--"+topupvo.getAmount());
						
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
					
					responsevo.setResult(inserttopup(topupvo));
					responsevo.setMessage("Topup Request Submitted Successfully");
					
				} else {
					topupvo.setTransactionIDForTata(0);
					responsevo.setResult(inserttopup(topupvo));
					responsevo.setMessage("Topup Request Inserted Successfully");
				}
					
				
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			// pstmt.close();
			// ps.close();
			con.close();
		}

		return responsevo;
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

	public List<StatusResponseVO> getStatusdetails(int roleid, String id) throws SQLException {
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
							"LEFT JOIN customermeterdetails AS cmd ON t.CRNNumber = cmd.CRNNumber WHERE t.TransactionDate BETWEEN (CURDATE() - INTERVAL 30 DAY) AND CONCAT(CURDATE(), ' 23:59:59') <change>";
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY t.TransactionDate DESC" : (roleid == 2 || roleid == 5) ? "AND t.BlockID = "+id+ " ORDER BY t.TransactionDate DESC" : (roleid == 3) ? "AND t.CRNNumber = '"+id+"'" :""));
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
				statusvo.setStatus((rs.getInt("Status") == 0) ? "Pending...waiting for acknowledge" : (rs.getInt("Status") == 1) ? "Pending" : (rs.getInt("Status") == 2) ? "Passed" :"Failed");

				pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					statusvo.setTransactedByUserName(rs1.getString("UserName"));
					statusvo.setTransactedByRoleDescription(rs1.getString("RoleDescription"));
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

	public ResponseVO deletestatus(int transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();

			pstmt = con.prepareStatement("DELETE FROM topup where TransactionID = "+transactionID);

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Deleted Successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
			
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public ResponseVO printreceipt(int transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			String query = "SELECT 	DISTINCT t.TransactionID, c.CommunityName, b.BlockName, cmd.FirstName, cmd.HouseNumber, cmd.CreatedByID, cmd.LastName, cmd.CRNNumber, t.MeterID, t.Amount, tr.AlarmCredit, tr.EmergencyCredit, t.Status, t.ModeOfPayment, t.PaymentStatus, t.TransactionDate, t.AcknowledgeDate FROM topup AS t \r\n" + 
					"LEFT JOIN community AS c ON t.CommunityID = c.CommunityID LEFT JOIN block AS b ON t.BlockID = b.BlockID LEFT JOIN tariff AS tr ON tr.TariffID = t.tariffID \r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON t.CRNNumber = cmd.CRNNumber WHERE t.TransactionID = "+transactionID;
	
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				/*statusvo.setCommunityName(rs.getString("CommunityName"));
				statusvo.setBlockName(rs.getString("BlockName"));
				statusvo.setFirstName(rs.getString("FirstName"));
				statusvo.setLastName(rs.getString("LastName"));
				statusvo.setCRNNumber(rs.getString("CRNNumber"));
				statusvo.setModeOfPayment(rs.getString("ModeOfPayment"));
				statusvo.setTransactionDate(rs.getString("TransactionDate"));
				statusvo.setStatus((rs.getInt("Status") == 0) ? "Pending...waiting for acknowledge" : (rs.getInt("Status") == 1) ? "Pending" : (rs.getInt("Status") == 2) ? "Passed" :"Failed");
		
				pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					statusvo.setTransactedByUserName(rs1.getString("UserName"));
					statusvo.setTransactedByRoleDescription(rs1.getString("RoleDescription"));
				}*/

				Document document = new Document(PageSize.A4);
				
				String head = "TOPUP RECEIPT";

				String drivename = "C:/TopupReceipts/";
				File directory = new File(drivename);
				if (!directory.exists()) {
					directory.mkdir();
				}
				
				String copyrtext = "All  rights reserved by HANBIT ® Hyderabad";

				PdfWriter.getInstance(document, new FileOutputStream(drivename	+ transactionID + ".pdf"));

				document.open();
				
				Image image = Image.getInstance("Images/hanbit1.png");
				image.scaleAbsolute(100, 100);
				image.setWidthPercentage(50);

				document.add((Element) image);

				Paragraph newLine = new Paragraph(new Phrase("\n"));
				document.add(newLine);
				
				Chunk chunk = new Chunk(head);
				Font font = new Font(Font.TIMES_ROMAN);
				font.setSize(30);
				chunk.setFont(font);

				Paragraph paragraph = new Paragraph();
				paragraph.add(chunk);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);

				Chunk chunk1 = new Chunk("AMR ID:" + rs.getString("MeterID"));
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
				document.add(newLine);
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

				tablenew.addCell(rs.getString("TransactionID"));
				tablenew.addCell(rs.getString("MeterID"));
				tablenew.addCell(rs.getString("HouseNumber"));
				tablenew.addCell(rs.getString("Amount"));
				tablenew.addCell(rs.getString("EmergencyCredit"));
				tablenew.addCell(rs.getString("AlarmCredit"));
				tablenew.addCell(rs.getString("TransactionDate"));
				// tablenew.addCell(status);
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
				responsevo.setResult("Success");
				responsevo.setLocation(drivename);
				responsevo.setFileName(transactionID + ".pdf");
				
		} 
		}catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("INTERNAL SERVER ERROR");
		} finally {
			pstmt.close();
			// ps.close();
			rs.close();
			con.close();
		}
		return responsevo;

	}

	/* Configuration */

	public List<ConfigurationResponseVO> getConfigurationdetails(int roleid, String id)
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
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? " ORDER BY cmd.ModifiedDate DESC" : (roleid == 2 || roleid == 5) ? "WHERE cm.BlockID = "+id+ " ORDER BY cmd.ModifiedDate DESC" : (roleid == 3) ? "WHERE cm.CRNNumber = '"+id+ "' ORDER BY cmd.ModifiedDate DESC":""));
			
			rs = pstmt.executeQuery();
			ConfigurationResponseVO configurationvo = null;

			while (rs.next()) {
				configurationvo = new ConfigurationResponseVO();
				configurationvo.setMeterID(rs.getString("MeterID"));
				configurationvo.setCommandType((rs.getInt("CommandType") == 40) ? "Solenoid Open" : (rs.getInt("CommandType") == 0) ? "Solenoid Close" : (rs.getInt("CommandType") == 3) ? "Clear Meter" : (rs.getInt("CommandType") == 1) ? "Clear Tamper" : (rs.getInt("CommandType") == 5) ? "RTC" : (rs.getInt("CommandType") == 6) ? "Set Default Read" : (rs.getInt("CommandType") == 10) ? "Set Tariff" : "");
				configurationvo.setModifiedDate(rs.getString("ModifiedDate"));
				configurationvo.setStatus((rs.getInt("Status") == 0) ? "Pending...waiting for acknowledge" : (rs.getInt("Status") == 1) ? "Pending" : (rs.getInt("Status") == 2) ? "Passed" :"Failed");
				configurationvo.setTransactionID(rs.getInt("TransactionID"));
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

	public ResponseVO addconfiguration(ConfigurationRequestVO configurationvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		Random randomNumber = new Random();
		String dataframe = "";
		ResponseVO responsevo = new ResponseVO();

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
						
					PreparedStatement pstmt1 = con.prepareStatement("SELECT CustomerID, MeterID FROM customermeterdetails WHERE CRNNumber = ?");
					pstmt1.setString(1, configurationvo.getCRNNumber());
					ResultSet rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
						configurationvo.setCustomerID(rs1.getInt("CustomerID"));
						configurationvo.setMeterID(rs1.getString("MeterID"));
					}
					
					ps = con.prepareStatement("INSERT INTO command (TataReferenceNumber, CustomerID, MeterID, CommandType, Status, CRNNumber, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, NOW())");
					ps.setLong(1, tataResponseVO.getId());
					ps.setInt(2, configurationvo.getCustomerID());
					ps.setString(3, configurationvo.getMeterID());
					ps.setInt(4, configurationvo.getCommandType());
					ps.setInt(5, tataResponseVO.getTransmissionStatus());
					ps.setString(6, configurationvo.getCRNNumber());

					if (ps.executeUpdate() > 0) {
						responsevo.setResult("Success");
						responsevo.setMessage("Command Request Submitted SUccessfully");
						}
						
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			ps.close();
			con.close();
		}

		return responsevo;

	}

	public ResponseVO deleteconfiguration(int transactionID)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();

			pstmt = con.prepareStatement("DELETE FROM command WHERE TransactionID = ?");
			pstmt.setInt(1, transactionID);

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Deleted Successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
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
