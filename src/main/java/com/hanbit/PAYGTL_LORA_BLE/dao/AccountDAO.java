/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ConfigurationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.RestCallVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ConfigurationResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.StatusResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TataResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.utils.Encoding;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
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

	public ResponseVO addtopup(TopUpRequestVO topupvo) throws SQLException, BusinessException {
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
					
					topupvo.setFixedCharges(0);
					topupvo.setReconnectionCharges(0);
					
					PreparedStatement pstmt1 = con.prepareStatement("SELECT tr.EmergencyCredit, tr.AlarmCredit, tr.FixedCharges, tr.TariffID, tr.Tariff FROM customermeterdetails as cmd LEFT JOIN tariff AS tr ON tr.TariffID = cmd.TariffID WHERE cmd.CRNNumber = ?");
					pstmt1.setString(1, topupvo.getCRNNumber());
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						
					LocalDateTime dateTime = LocalDateTime.now();  
						
					PreparedStatement pstmt = con.prepareStatement("SELECT MONTH(TransactionDate) AS previoustopupmonth from topup WHERE Status = 2 and CRNNumber = '"+topupvo.getCRNNumber()+"'" + "ORDER BY TransactionID DESC LIMIT 0,1");
					ResultSet rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						if(rs.getInt("previoustopupmonth") != dateTime.getMonthValue()) {
							topupvo.setFixedCharges((rs1.getInt("FixedCharges") * (dateTime.getMonthValue() - rs.getInt("previoustopupmonth"))));
					}
					
					} else {
						topupvo.setFixedCharges(rs1.getInt("FixedCharges"));
					}
					
					PreparedStatement pstmt2 = con.prepareStatement("SELECT al.ReconnectionCharges, dbl.Minutes FROM displaybalancelog AS dbl JOIN alertsettings AS al WHERE dbl.CRNNumber = '"+topupvo.getCRNNumber()+"'");
					ResultSet rs2 = pstmt2.executeQuery();
					
					if(rs2.next()) {
						
						if(rs2.getInt("Minutes") != 0) {
							topupvo.setReconnectionCharges(rs2.getInt("ReconnectionCharges"));
						}
						
					}
					
					if(topupvo.getAmount() <= topupvo.getFixedCharges() || topupvo.getAmount() <= topupvo.getReconnectionCharges()) {
						
						throw new BusinessException("RECHARGE AMOUNT MUST BE GREATER THAN FIXED CHARGES & RECONNECTION CHARGES");
						
					}
					
					hexaAmount = Integer.toHexString(Float.floatToIntBits(topupvo.getAmount() - (topupvo.getFixedCharges() + topupvo.getReconnectionCharges()))).toUpperCase();

					hexaAlarmCredit = Integer.toHexString(Float.floatToIntBits(rs1.getFloat("AlarmCredit"))).toUpperCase();

					hexaEmergencyCredit = Integer.toHexString(Float.floatToIntBits(rs1.getFloat("EmergencyCredit"))).toUpperCase();

					hexaTariff = Integer.toHexString(Float.floatToIntBits(rs1.getFloat("Tariff"))).toUpperCase();
					
					}
					
					String serialNumber = String.format("%04x", randomNumber.nextInt(65000));
					
					// 0A18000001020C0023  41200000  41200000  41200000   41200000           17
					//                    credit    lowcredit--Alarm EmgCredit lowemgcredit--TAriff
					String dataFrame = "0A1800" + serialNumber + "020C0023" + hexaAmount + hexaTariff + hexaEmergencyCredit + hexaAlarmCredit + "17";

					ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
					RestCallVO restcallvo = new RestCallVO();
					
					restcallvo.setDataFrame(Encoding.getHexBase644(dataFrame));
					restcallvo.setMeterID(topupvo.getMeterID().toLowerCase());
					System.out.println("topup: "+restcallvo.getDataFrame());
					String restcallresponse = extramethodsdao.restcallpost(restcallvo);
					
					TataResponseVO tataResponseVO = gson.fromJson(restcallresponse, TataResponseVO.class);
					
					//check for payment status with the payment gateway
					
					topupvo.setTransactionIDForTata(tataResponseVO.getId());
					topupvo.setStatus(tataResponseVO.getTransmissionStatus());
					
					if(inserttopup(topupvo).equalsIgnoreCase("Success")) {
						responsevo.setResult("Success");
						responsevo.setMessage("Topup Request Submitted Successfully");
					}else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Topup Request Failed");
					}
					
				} else {
					topupvo.setTransactionIDForTata(0);
					if(inserttopup(topupvo).equalsIgnoreCase("Success")) {
						responsevo.setResult(inserttopup(topupvo));
						responsevo.setMessage("Topup Request Inserted Successfully");
					}else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Topup Request Insertion Failed");
					}
					
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
				
			String sql = "INSERT INTO topup (TataReferenceNumber, CommunityID, BlockID, CustomerID, MeterID, TariffID, Amount, FixedCharges, ReconnectionCharges, Status, ModeOfPayment, PaymentStatus, Source, CreatedByID, CreatedByRoleID, CRNNumber, AcknowledgeDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
			ps = con.prepareStatement(sql);
			
			ps.setLong(1, topUpRequestVO.getTransactionIDForTata());
			ps.setInt(2, rs.getInt("CommunityID")); 
			ps.setInt(3, rs.getInt("BlockID"));
			ps.setInt(4, rs.getInt("CustomerID"));
			ps.setString(5, rs.getString("MeterID"));
			ps.setInt(6, rs.getInt("TariffID"));
			ps.setFloat(7, topUpRequestVO.getAmount());
			ps.setInt(8, topUpRequestVO.getFixedCharges());
			ps.setFloat(9, topUpRequestVO.getReconnectionCharges());
			ps.setInt(10, topUpRequestVO.getStatus());
			ps.setString(11, topUpRequestVO.getModeOfPayment());
			ps.setInt(12, 0); // payment status from payment gateway
			ps.setString(13, topUpRequestVO.getSource());
			ps.setFloat(14, topUpRequestVO.getTransactedByID());
			ps.setInt(15, topUpRequestVO.getTransactedByRoleID());
			ps.setString(16, topUpRequestVO.getCRNNumber());

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

	public List<StatusResponseVO> getStatusdetails(int roleid, String id, int filterCid, int day) throws SQLException {
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
							"LEFT JOIN customermeterdetails AS cmd ON t.CRNNumber = cmd.CRNNumber WHERE t.TransactionDate BETWEEN CONCAT(CURDATE() <day>, ' 00:00:00') AND CONCAT(CURDATE(), ' 23:59:59') <change>";
			query = query.replaceAll("<day>", (day==1) ? "" : "- INTERVAL 30 DAY");
			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && (filterCid == -1)) ? "ORDER BY t.TransactionDate DESC" : ((roleid == 1 || roleid == 4) && (filterCid != -1)) ? " AND t.CommunityID = "+filterCid+" ORDER BY t.TransactionDate DESC" : (roleid == 2 || roleid == 5) ? "AND t.BlockID = "+id+ " ORDER BY t.TransactionDate DESC" : (roleid == 3) ? "AND t.CRNNumber = '"+id+"' ORDER BY t.TransactionDate DESC" :""));
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
				statusvo.setTransactionDate(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
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
		String drivename = "C:/TopupReceipts/";

		try {
			con = getConnection();
			
			String query = "SELECT 	DISTINCT t.TransactionID, c.CommunityName, b.BlockName, cmd.FirstName, cmd.HouseNumber, cmd.CreatedByID, cmd.LastName, cmd.CRNNumber, t.MeterID, t.Amount, tr.AlarmCredit, tr.EmergencyCredit, t.Status, t.FixedCharges, t.ReconnectionCharges, t.ModeOfPayment, t.PaymentStatus, t.TransactionDate, t.AcknowledgeDate FROM topup AS t \r\n" + 
					"LEFT JOIN community AS c ON t.CommunityID = c.CommunityID LEFT JOIN block AS b ON t.BlockID = b.BlockID LEFT JOIN tariff AS tr ON tr.TariffID = t.tariffID \r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON t.CRNNumber = cmd.CRNNumber WHERE t.TransactionID = "+transactionID;
	
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {

				File directory = new File(drivename);
				if (!directory.exists()) { directory.mkdir(); }
				
				PdfWriter writer = new PdfWriter(drivename+transactionID+".pdf");
				PdfDocument pdfDocument = new PdfDocument(writer);
				pdfDocument.addNewPage();
				Document document = new Document(pdfDocument);
				Paragraph newLine = new Paragraph("\n");
				Paragraph head = new Paragraph("Receipt");
				Paragraph copyRight = new Paragraph("------------------------------------All  rights reserved by HANBIT ® Hyderabad-----------------------------------");
				PdfFont font = new PdfFontFactory().createFont(FontConstants.TIMES_BOLD);

				// change according to the image directory
				
				URL hanbiturl = new URL(ExtraConstants.HANBITIMAGEURL);
				URL clienturl = new URL(ExtraConstants.CLIENTIMAGEURL); 
			    Image hanbit = new Image(ImageDataFactory.create(hanbiturl));
			    Image client = new Image(ImageDataFactory.create(clienturl));
//			    Image technology = new Image(ImageDataFactory.create("C:/TopupReceipts/lorawan.png"));
//			    Image mode = new Image(ImageDataFactory.create("C:/TopupReceipts/bluetooth.png"));
			    
			    float [] headingWidths = { 200F, 130F, 200F };
				
			    Table headTable = new Table(headingWidths);
			    
			    Cell headtable1 = new Cell();
			    headtable1.add(hanbit);
			    headtable1.setTextAlignment(TextAlignment.LEFT);
			    
			    Cell headtable2 = new Cell();
			    headtable2.add(head.setFontSize(20));
			    headtable2.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setBold().setUnderline().setFont(font);
			    
			    Cell headtable3 = new Cell();
			    headtable3.add(client);
			    headtable3.setTextAlignment(TextAlignment.RIGHT);
			    
			    headTable.addCell(headtable1.setBorder(Border.NO_BORDER));
			    headTable.addCell(headtable2.setBorder(Border.NO_BORDER));
			    headTable.addCell(headtable3.setBorder(Border.NO_BORDER));
			    
			    document.add(headTable);
			    document.add(newLine);
			    
			    float [] headerWidths = { 200F, 180F, 170F };
			    
			    Table table1 = new Table(headerWidths);
			    
			    Cell table1cell1 = new Cell();
			    table1cell1.add("MIU ID: " + rs.getString("MeterID"));
			    table1cell1.setTextAlignment(TextAlignment.LEFT);
			    
			    Cell table1cell2 = new Cell();
			    table1cell2.add("CRN Number: " + rs.getString("CRNNumber"));
			    table1cell2.setTextAlignment(TextAlignment.CENTER);
			    
			    Cell table1cell3 = new Cell();
			    table1cell3.add("Invoice No. : " + rs.getInt("TransactionID"));
			    table1cell3.setTextAlignment(TextAlignment.RIGHT);
			    
			    table1.addCell(table1cell1.setBorder(Border.NO_BORDER));
			    table1.addCell(table1cell2.setBorder(Border.NO_BORDER));
			    table1.addCell(table1cell3.setBorder(Border.NO_BORDER));
			    
			    document.add(table1.setHorizontalAlignment(HorizontalAlignment.CENTER));
			    document.add(newLine);
			    
			    float [] columnWidths = { 400F, 150F };
			    
				Table datatable = new Table(columnWidths);
				
				Cell cell1 = new Cell();
				cell1.add("Customer Name: ");
				cell1.setTextAlignment(TextAlignment.CENTER);
				
				Cell customerName = new Cell();
				customerName.add(rs.getString("FirstName") + " " +rs.getString("LastName"));
				customerName.setTextAlignment(TextAlignment.CENTER);
				
				datatable.addCell(cell1);
				datatable.addCell(customerName);
				datatable.startNewRow();
				
				Cell cell2 = new Cell();
				cell2.add("Amount: ");
				cell2.setTextAlignment(TextAlignment.CENTER);
				
				Cell Amount = new Cell();
				Amount.add(rs.getString("Amount"));
				Amount.setTextAlignment(TextAlignment.CENTER);
				
				datatable.addCell(cell2);
				datatable.addCell(Amount);
				datatable.startNewRow();
				
				Cell cell3 = new Cell();
				cell3.add("FixedCharges(if any): ");
				cell3.setTextAlignment(TextAlignment.CENTER);
				
				Cell fixedCharges = new Cell();
				fixedCharges.add(rs.getString("FixedCharges"));
				fixedCharges.setTextAlignment(TextAlignment.CENTER);
				
				datatable.addCell(cell3);
				datatable.addCell(fixedCharges);
				datatable.startNewRow();
				
				Cell cell4 = new Cell();
				cell4.add("Reconnection Charges(if any): ");
				cell4.setTextAlignment(TextAlignment.CENTER);
				
				Cell reconnectionCharges = new Cell();
				reconnectionCharges.add(rs.getString("ReconnectionCharges"));
				reconnectionCharges.setTextAlignment(TextAlignment.CENTER);
				
				datatable.addCell(cell4);
				datatable.addCell(reconnectionCharges);
				datatable.startNewRow();
				
				Cell cell5 = new Cell();
				cell5.add("Amount Updated to Device After Deductions: ");
				cell5.setTextAlignment(TextAlignment.CENTER);
				
				Cell finalAmount = new Cell();
				finalAmount.add(Integer.toString((rs.getInt("Amount") - (rs.getInt("FixedCharges") + rs.getInt("ReconnectionCharges")))));
				finalAmount.setTextAlignment(TextAlignment.CENTER);
				
				datatable.addCell(cell5);
				datatable.addCell(finalAmount);
				datatable.startNewRow();
				
				Cell cell6 = new Cell();
				cell6.add("Mode of Payment: ");
				cell6.setTextAlignment(TextAlignment.CENTER);
				
				Cell modeOfPayment = new Cell();
				modeOfPayment.add(rs.getString("ModeOfPayment"));
				modeOfPayment.setTextAlignment(TextAlignment.CENTER);				
				
				datatable.addCell(cell6);
				datatable.addCell(modeOfPayment);
				datatable.startNewRow();
				
				Cell cell7 = new Cell();
				cell7.add("Transaction Initiated By: ");
				cell7.setTextAlignment(TextAlignment.CENTER);
				
				Cell transactedBy = new Cell();
				transactedBy.add(rs1.getString("UserName"));
				transactedBy.setTextAlignment(TextAlignment.CENTER);				
				
				datatable.addCell(cell7);
				datatable.addCell(transactedBy);
				datatable.startNewRow();
				
				Cell cell8 = new Cell();
				cell8.add("Date of Transaction: ");
				cell8.setTextAlignment(TextAlignment.CENTER);
				
				Cell transactionDate = new Cell();
				transactionDate.add(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
				transactionDate.setTextAlignment(TextAlignment.CENTER);
				
				datatable.addCell(cell8);
				datatable.addCell(transactionDate);
				datatable.startNewRow();
				
				Cell cell9 = new Cell();
				cell9.add("Acknowledge Date: ");
				cell9.setTextAlignment(TextAlignment.CENTER);
				
				Cell acknowledgeDate = new Cell();
				acknowledgeDate.add(ExtraMethodsDAO.datetimeformatter(rs.getString("AcknowledgeDate")));
				acknowledgeDate.setTextAlignment(TextAlignment.CENTER);
				
				datatable.addCell(cell9);
				datatable.addCell(acknowledgeDate);
				datatable.startNewRow();
				
				document.add(datatable.setHorizontalAlignment(HorizontalAlignment.CENTER));
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				document.add(newLine);
				
				document.add(copyRight.setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(font));				
				document.close();
				
				responsevo.setResult("Success");
				responsevo.setLocation(drivename);
				responsevo.setFileName(transactionID + ".pdf");
				
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("INTERNAL SERVER ERROR");
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return responsevo;

	}

	/* Configuration */

	public List<ConfigurationResponseVO> getConfigurationdetails(int roleid, String id, int filterCid)
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
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && (filterCid == -1)) ? " ORDER BY cmd.ModifiedDate DESC" : ((roleid == 1 || roleid == 4) && (filterCid != -1)) ? " WHERE cm.CommunityID = "+filterCid+" ORDER BY cmd.ModifiedDate DESC" : (roleid == 2 || roleid == 5) ? "WHERE cm.BlockID = "+id+ " ORDER BY cmd.ModifiedDate DESC" : (roleid == 3) ? "WHERE cm.CRNNumber = '"+id+ "' ORDER BY cmd.ModifiedDate DESC":""));
			
			rs = pstmt.executeQuery();
			ConfigurationResponseVO configurationvo = null;

			while (rs.next()) {
				configurationvo = new ConfigurationResponseVO();
				configurationvo.setMeterID(rs.getString("MeterID"));
				configurationvo.setCommandType((rs.getInt("CommandType") == 40) ? "Solenoid Open" : (rs.getInt("CommandType") == 0) ? "Solenoid Close" : (rs.getInt("CommandType") == 3) ? "Clear Meter" : (rs.getInt("CommandType") == 1) ? "Clear Tamper" : (rs.getInt("CommandType") == 5) ? "RTC" : (rs.getInt("CommandType") == 6) ? "Set Default Read" : (rs.getInt("CommandType") == 10) ? "Set Tariff" : "");
				configurationvo.setModifiedDate(ExtraMethodsDAO.datetimeformatter(rs.getString("ModifiedDate")));
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
						
						dataframe = "0A0F00" + serialNumber + "02090041" + String.format("%02x", now.getDayOfMonth()) + String.format("%02x", now.getMonthValue()) + String.format("%02x", (now.getYear()-2000)) + String.format("%02x", now.getHour()) + String.format("%02x", now.getMinute()) + String.format("%02x", now.getSecond()) + String.format("%02x", (now.getDayOfWeek().getValue()-1)) + "17";

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
					
					System.out.println("Frame:- "+dataframe);
					
					ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
					RestCallVO restcallvo = new RestCallVO();
					
					restcallvo.setMeterID(configurationvo.getMeterID().toLowerCase());
					restcallvo.setDataFrame(Encoding.getHexBase644(dataframe));
					
					System.out.println("64Frame:- "+restcallvo.getDataFrame());
					
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
						responsevo.setMessage("Command Request Submitted Successfully");
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
			pstmt = con.prepareStatement("SELECT tr.EmergencyCredit, tr.Tariff, tr.TariffID, t.CustomerID FROM topup as t LEFT JOIN tariff AS tr ON tr.TariffID = t.TariffID WHERE t.CRNNumber = ?");
			pstmt.setString(1, topupvo.getCRNNumber());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(topupvo.getAmount() < rs.getFloat("EmergencyCredit") || topupvo.getAmount() < rs.getFloat("Tariff"))
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
