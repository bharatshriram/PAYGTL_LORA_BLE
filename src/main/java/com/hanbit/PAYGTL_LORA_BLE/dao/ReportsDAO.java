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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.FinancialReportsRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpSummaryRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserConsumptionRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.AlarmsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.FinancialReportsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TopUpSummaryResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.UserConsumptionReportsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ValveReportsResponseVO;
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
			pstmt1 = con.prepareStatement(query.replaceAll("<change>", (roleid==2 || roleid==5) ? "WHERE cmd.BlockID = "+id : " ORDER BY cmd.CustomerID ASC"));
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

			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, bl.ReadingID, bl.EmergencyCredit, \r\n" + 
					"bl.MeterID, bl.Reading, bl.Balance, bl.BatteryVoltage, bl.TariffAmount, bl.AlarmCredit, bl.SolonideStatus, bl.TamperDetect, bl.IoTTimeStamp, bl.LogDate\r\n" + 
					"FROM balancelog AS bl LEFT JOIN community AS c ON c.communityID = bl.CommunityID LEFT JOIN block AS b ON b.BlockID = bl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerID = bl.CustomerID WHERE bl.CustomerID = ? AND bl.IoTTimeStamp BETWEEN ? AND ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, userconsumptionreportsrequestvo.getCustomerID());
				pstmt.setString(2, userconsumptionreportsrequestvo.getFromDate()+" 00:00:00");
				pstmt.setString(3,userconsumptionreportsrequestvo.getToDate()+" 23:59:59");

				rs = pstmt.executeQuery();
				while (rs.next()) {

					userconsumptionreportsresponsevo = new UserConsumptionReportsResponseVO();

					userconsumptionreportsresponsevo.setMeterID(rs.getString("MeterID"));
					userconsumptionreportsresponsevo.setReading(rs.getFloat("Reading"));
					userconsumptionreportsresponsevo.setBalance(rs.getFloat("Balance"));
					userconsumptionreportsresponsevo.setBattery(rs.getFloat("BatteryVoltage"));
					userconsumptionreportsresponsevo.setTariff(rs.getFloat("TariffAmount"));
					userconsumptionreportsresponsevo.setAlarmCredit(rs.getFloat("Alarmcredit"));
					userconsumptionreportsresponsevo.setEmergencyCredit(rs.getFloat("Emergencycredit"));
					userconsumptionreportsresponsevo.setDateTime(rs.getString("IoTTimeStamp"));
					
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

/*	public String getpdf(
			UserConsumptionRequestVO userconsumptionreportsrequestvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String result = "Failure";
		String com_name = "";

		try {
			con = getConnection();

			PreparedStatement pstmt5 = con
					.prepareStatement("select com_name from community where com_id=?");
			pstmt5.setInt(1, LoginDAO.CommunityID);
			ResultSet rs5 = pstmt5.executeQuery();
			if (rs5.next()) {
				com_name = rs5.getString("com_name");
			}

			pstmt2 = con
					.prepareStatement("select block_id from block where name = ? and com_id=?");
			pstmt2.setString(1, userconsumptionreportsrequestvo.getBlockName());
			pstmt2.setInt(2, LoginDAO.CommunityID);
			rs2 = pstmt2.executeQuery();
			if (rs2.next()) {

				pstmt3 = con
						.prepareStatement("select cust_id from customer where house_no=? and block_id=?");
				pstmt3.setString(1,
						userconsumptionreportsrequestvo.getHouseNo());
				pstmt3.setInt(2, rs2.getInt("block_id"));
				rs3 = pstmt3.executeQuery();
				if (rs3.next()) {

					if (!userconsumptionreportsrequestvo.getMonth().equals(
							"true")) {

						int block_id = rs2.getInt("block_id");
						int customer_id = rs3.getInt("cust_id");

						String block_name = userconsumptionreportsrequestvo
								.getBlockName();
						String house_no = userconsumptionreportsrequestvo
								.getHouseNo();
						int year = userconsumptionreportsrequestvo.getYear();
						String month = userconsumptionreportsrequestvo
								.getMonth();

						String meterId = "";
						String reading_id = "";
						String meterId_get = "";
						String reading = "";
						String balance = "";
						String battery_volt = "";
						String logdate = "";
						String gas_tariff = "";
						String alarm_credit = "";
						String emr_credit = "";

						String gas_leak = "";
						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd-HH-mm-ss");
						DateFormat dateFormat_new = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date date = new Date();

						String sql = "select tcm.meter_id as mid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id =tmm.meter_id and tc.com_id=? and tb.block_id=? and tcu.cust_id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, LoginDAO.CommunityID);
						pstmt.setInt(2, block_id);
						pstmt.setInt(3, customer_id);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							meterId = rs.getString("mid");
						}

						Document document = new Document(PageSize.A4);
						String file_name = com_name + "_" + block_name + "_"
								+ house_no + "_" + dateFormat.format(date);

						String head = head = com_name + " Community " + block_name + " Block " + house_no + " Flat " + year + "-" + month + " Month Consumption Report";

						
						 * if ("month".equals(type)) { head = com_name +
						 * " Community " + block_name + " Block " + house_no +
						 * " Flat " + year + "-" + month +
						 * " Month Consumption Report"; }
						 

						String drivename = "E:/ConsumptionReports/";
						File directory = new File(drivename);
						if (!directory.exists()) {
							directory.mkdir();
						}

						String logo = ExtraConstants.IMAGEURL;
						String copyrtext = "All  rights reserved by HANBIT ® Hyderabad";

						PdfWriter.getInstance(document, new FileOutputStream(
								drivename + file_name + ".pdf"));

						document.open();

						Image image = Image.getInstance(logo);
						// image.scaleAbsolute(100,100);
						image.setWidthPercentage(50);

						document.add(image);

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

						Chunk chunk1 = new Chunk("AMR ID:" + meterId);
						Font font1 = new Font(Font.TIMES_ROMAN);
						font1.setSize(12);
						chunk1.setFont(font1);

						// document.add(chunk);

						Paragraph paragraph1 = new Paragraph();
						paragraph1.add(chunk1);
						paragraph1.setAlignment(Element.ALIGN_LEFT);
						document.add(paragraph1);

						Font font_for_table = new Font(Font.TIMES_ROMAN);
						font_for_table.setSize(4);
						document.add(p);
						PdfPTable table = new PdfPTable(8);
						table.setWidthPercentage(100);

						document.add(table);

						PdfPTable tablenew = new PdfPTable(8);
						table.setWidthPercentage(100);
						PdfPTable tablenew1 = new PdfPTable(8);

						tablenew.addCell("Reading");
						tablenew.addCell("Balance");
						tablenew.addCell("Tariff");
						tablenew.addCell("Emergency Credit");
						tablenew.addCell("Alaram Credit");
						tablenew.addCell("Battery Voltage");
						tablenew.addCell("Gas Leak");
						tablenew.addCell("Date Time");

						String sql1 = "select ReadingID,MeterNo,Reading,Balance,BatteryVoltage,LogDate,Gas_Tariff,Alarmcredit,Emergencycredit,TamperDetect from BalanceLog where MeterNo=? and YEAR(LogDate)=? and MONTH(LogDate)=? order by ReadingID";
						pstmt = con.prepareStatement(sql1);
						pstmt.setString(1, meterId);
						pstmt.setInt(2, year);
						pstmt.setString(3, month);
						rs = pstmt.executeQuery();
						while (rs.next()) {

							reading_id = rs.getString("ReadingID");
							meterId_get = rs.getString("MeterNo");
							reading = rs.getString("reading");
							balance = rs.getString("Balance");
							battery_volt = rs.getString("BatteryVoltage");
							logdate = rs.getString("LogDate");
							gas_tariff = rs.getString("Gas_Tariff");
							alarm_credit = rs.getString("Alarmcredit");
							emr_credit = rs.getString("Emergencycredit");
							gas_leak = rs.getString("TamperDetect");

							tablenew.addCell(reading);
							tablenew.addCell(balance);
							tablenew.addCell(gas_tariff);
							tablenew.addCell(emr_credit);
							tablenew.addCell(alarm_credit);
							tablenew.addCell(battery_volt);
							tablenew.addCell(gas_leak);
							tablenew.addCell(logdate);
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

					} else {

						int block_id = rs2.getInt("block_id");
						int customer_id = rs3.getInt("cust_id");
						String block_name = userconsumptionreportsrequestvo
								.getBlockName();
						String house_no = userconsumptionreportsrequestvo
								.getHouseNo();
						int year = userconsumptionreportsrequestvo.getYear();

						String meterId = "";
						String reading_id = "";
						String meterId_get = "";
						String reading = "";
						String balance = "";
						String battery_volt = "";
						String logdate = "";
						String gas_tariff = "";
						String alarm_credit = "";
						String emr_credit = "";

						String gas_leak = "";
						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd-HH-mm-ss");
						DateFormat dateFormat_new = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date date = new Date();

						String sql = "select tcm.meter_id as mid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id =tmm.meter_id and tc.com_id=? and tb.block_id=? and tcu.cust_id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, LoginDAO.CommunityID);
						pstmt.setInt(2, block_id);
						pstmt.setInt(3, customer_id);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							meterId = rs.getString("mid");
						}

						Document document = new Document(PageSize.A4);
						String file_name = com_name + "_" + block_name + "_"
								+ house_no + "_" + dateFormat.format(date);

						String head = com_name + " Community " + block_name
								+ " Block " + house_no + " Flat " + year
								+ " Year Consumption Report";

						String drivename = "E:/ConsumptionReports/";
						File directory = new File(drivename);
						if (!directory.exists()) {
							directory.mkdir();
						}

						String logo = ExtraConstants.IMAGEURL;
						String copyrtext = "All  rights reserved by HANBIT ® Hyderabad";

						PdfWriter.getInstance(document, new FileOutputStream(
								drivename + file_name + ".pdf"));

						document.open();

						Image image = Image.getInstance(logo);
						// image.scaleAbsolute(100,100);
						image.setWidthPercentage(50);

						document.add(image);

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

						Chunk chunk1 = new Chunk("AMR ID:" + meterId);
						Font font1 = new Font(Font.TIMES_ROMAN);
						font1.setSize(12);
						chunk1.setFont(font1);

						// document.add(chunk);

						Paragraph paragraph1 = new Paragraph();
						paragraph1.add(chunk1);
						paragraph1.setAlignment(Element.ALIGN_LEFT);
						document.add(paragraph1);

						Font font_for_table = new Font(Font.TIMES_ROMAN);
						font_for_table.setSize(4);
						document.add(p);
						PdfPTable table = new PdfPTable(8);
						table.setWidthPercentage(100);

						document.add(table);

						PdfPTable tablenew = new PdfPTable(8);
						table.setWidthPercentage(100);
						PdfPTable tablenew1 = new PdfPTable(8);

						tablenew.addCell("Reading");
						tablenew.addCell("Balance");
						tablenew.addCell("Tariff");
						tablenew.addCell("Emergency Credit");
						tablenew.addCell("Alaram Credit");
						tablenew.addCell("Battery Voltage");
						tablenew.addCell("Gas Leak");
						tablenew.addCell("Date Time");

						String sql1 = "select ReadingID,MeterNo,Reading,Balance,BatteryVoltage,LogDate,Gas_Tariff,Alarmcredit,Emergencycredit,TamperDetect from BalanceLog where MeterNo=? and YEAR(LogDate)=? order by ReadingID";
						pstmt = con.prepareStatement(sql1);
						pstmt.setString(1, meterId);
						pstmt.setInt(2, year);

						rs = pstmt.executeQuery();
						while (rs.next()) {

							reading_id = rs.getString("ReadingID");
							meterId_get = rs.getString("MeterNo");
							reading = rs.getString("reading");
							balance = rs.getString("Balance");
							battery_volt = rs.getString("BatteryVoltage");
							logdate = rs.getString("LogDate");
							gas_tariff = rs.getString("Gas_Tariff");
							alarm_credit = rs.getString("Alarmcredit");
							emr_credit = rs.getString("Emergencycredit");
							gas_leak = rs.getString("TamperDetect");

							tablenew.addCell(reading);
							tablenew.addCell(balance);
							tablenew.addCell(gas_tariff);
							tablenew.addCell(emr_credit);
							tablenew.addCell(alarm_credit);
							tablenew.addCell(battery_volt);
							tablenew.addCell(gas_leak);
							tablenew.addCell(logdate);
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

					}
				}
			}
		}
		// }
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;

	}

	public String getexcel(
			UserConsumptionRequestVO userconsumptionreportsrequestvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String result = "Failure";
		String com_name = "";

		try {
			con = getConnection();
			
			PreparedStatement pstmt5 = con
					.prepareStatement("select com_name from community where com_id=?");
			pstmt5.setInt(1, LoginDAO.CommunityID);
			ResultSet rs5 = pstmt5.executeQuery();
			if (rs5.next()) {
				com_name = rs5.getString("com_name");
			}
			
			pstmt2 = con
					.prepareStatement("select block_id from block where name = ? and com_id=?");
			pstmt2.setString(1, userconsumptionreportsrequestvo.getBlockName());
			pstmt2.setInt(2, LoginDAO.CommunityID);

			rs2 = pstmt2.executeQuery();
			if (rs2.next()) {

				pstmt3 = con
						.prepareStatement("select cust_id from customer where house_no=? and block_id=?");
				pstmt3.setString(1,
						userconsumptionreportsrequestvo.getHouseNo());
				pstmt3.setInt(2, rs2.getInt("block_id"));
				rs3 = pstmt3.executeQuery();
				if (rs3.next()) {
					
					if (!userconsumptionreportsrequestvo.getMonth().equals(
							"true")) {

					int block_id = rs2.getInt("block_id");
					int customer_id = rs3.getInt("cust_id");

					String block_name = userconsumptionreportsrequestvo
							.getBlockName();
					String house_no = userconsumptionreportsrequestvo
							.getHouseNo();
					int year = userconsumptionreportsrequestvo.getYear();
					String month = userconsumptionreportsrequestvo.getMonth();

					String meterId = "";
					String meterId_get = "";
					String reading = "";
					String battery_volt = "";
					String logdate = "";
					String meter_type = "";
					String location_name = "";
					String head = "";
					String file_name = "";
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd-HH-mm-ss");
					DateFormat dateFormat_new = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date date = new Date();
					String sql1 = "";
					rs1 = null;
					String totaldata = "";
					String newLine = System.getProperty("line.separator");

					file_name = com_name + "_" + block_name + "_" + house_no
							+ "_" + dateFormat.format(date) + ".csv";

					head = com_name + " Community " + block_name + " Block "
							+ house_no + " Flat " + year + " Year Report";
					
					 * if ("month".equals(type)) { head = com_name +
					 * " Community " + block_name + " Block " + house_no +
					 * " Flat " + year + "-" + month + " Month Report"; }
					 

					String drivename = "E:/ConsumptionReports/";
					File directory = new File(drivename);
					if (!directory.exists()) {
						directory.mkdir();
					}

					String logo = ExtraConstants.IMAGEURL;
					String copyrtext = "All  rights reserved by HANBIT ® Hyderabad";

					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet spreadsheet = workbook
							.createSheet("consumption reports");
					XSSFRow row = spreadsheet.createRow(1);
					XSSFCell cell;
					XSSFCellStyle style2 = workbook.createCellStyle();
					style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					// style2.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);

					cell = row.createCell(1);
					cell.setCellValue("Flat");
					cell.setCellStyle(style2);

					cell = row.createCell(2);
					cell.setCellValue("AMR ID");
					cell.setCellStyle(style2);

					cell = row.createCell(3);
					cell.setCellValue("Reading");
					cell.setCellStyle(style2);
					// spreadsheet.addMergedRegion(new
					// CellRangeAddress(1,1,5,6));

					cell = row.createCell(4);
					cell.setCellValue("Balance");
					cell.setCellStyle(style2);

					cell = row.createCell(5);
					cell.setCellValue("Battery");
					cell.setCellStyle(style2);

					cell = row.createCell(6);
					cell.setCellValue("Date Time");
					cell.setCellStyle(style2);

					sql1 = "select distinct cu.house_no,bl.MeterNo,bl.Reading,bl.Balance,bl.BatteryVoltage,bl.LogDate from BalanceLog bl,customer cu,customer_meter tcm,meter_master tmm where bl.MeterNo=tmm.meter_id and cu.cust_id=tcm.cust_id and tcm.meter_id=tmm.meter_id and cu.cust_id=? and YEAR(LogDate)=?  and MONTH(LogDate)=? order by MeterNo desc";
					pstmt = con.prepareStatement(sql1);
					pstmt.setInt(1, customer_id);
					pstmt.setInt(2, year);
					pstmt.setString(3, month);
					rs1 = pstmt.executeQuery();

					int i = 2;
					while (rs1.next()) {

						row = spreadsheet.createRow(i);
						cell = row.createCell(1);
						cell.setCellValue(rs1.getInt("house_no"));
						cell.setCellStyle(style2);

						cell = row.createCell(2);
						cell.setCellValue(rs1.getString("MeterNo"));
						cell.setCellStyle(style2);

						cell = row.createCell(3);
						cell.setCellValue(rs1.getString("Reading"));
						cell.setCellStyle(style2);

						cell = row.createCell(4);
						cell.setCellValue(rs1.getString("Balance"));
						cell.setCellStyle(style2);

						cell = row.createCell(5);
						cell.setCellValue(rs1.getString("BatteryVoltage"));
						cell.setCellStyle(style2);

						cell = row.createCell(6);
						cell.setCellValue(rs1.getString("LogDate"));
						cell.setCellStyle(style2);
						i++;
					}
					FileOutputStream out1 = new FileOutputStream(drivename
							+ file_name + ".xlsx");
					// FileOutputStream out1 = new FileOutputStream(new
					// File("exceldatabase.xlsx"));
					workbook.write(out1);
					out1.close();
					result = "Success";
				}else{
					
					int block_id = rs2.getInt("block_id");
					int customer_id = rs3.getInt("cust_id");

					String block_name = userconsumptionreportsrequestvo
							.getBlockName();
					String house_no = userconsumptionreportsrequestvo
							.getHouseNo();
					int year = userconsumptionreportsrequestvo.getYear();
					String month = userconsumptionreportsrequestvo.getMonth();

					String meterId = "";
					String meterId_get = "";
					String reading = "";
					String battery_volt = "";
					String logdate = "";
					String meter_type = "";
					String location_name = "";
					String head = "";
					String file_name = "";
					DateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd-HH-mm-ss");
					DateFormat dateFormat_new = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date date = new Date();
					String sql1 = "";
					rs1 = null;
					String totaldata = "";
					String newLine = System.getProperty("line.separator");

					file_name = com_name + "_" + block_name + "_" + house_no
							+ "_" + dateFormat.format(date) + ".csv";

					head = head = com_name + " Community " + block_name + " Block " + house_no + " Flat " + year + "-" + month + " Month Report";

					String drivename = "E:/ConsumptionReports/";
					File directory = new File(drivename);
					if (!directory.exists()) {
						directory.mkdir();
					}

					String logo = ExtraConstants.IMAGEURL;
					String copyrtext = "All  rights reserved by HANBIT ® Hyderabad";

					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet spreadsheet = workbook
							.createSheet("consumption reports");
					XSSFRow row = spreadsheet.createRow(1);
					XSSFCell cell;
					XSSFCellStyle style2 = workbook.createCellStyle();
					style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
					// style2.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);

					cell = row.createCell(1);
					cell.setCellValue("Flat");
					cell.setCellStyle(style2);

					cell = row.createCell(2);
					cell.setCellValue("AMR ID");
					cell.setCellStyle(style2);

					cell = row.createCell(3);
					cell.setCellValue("Reading");
					cell.setCellStyle(style2);
					// spreadsheet.addMergedRegion(new
					// CellRangeAddress(1,1,5,6));

					cell = row.createCell(4);
					cell.setCellValue("Balance");
					cell.setCellStyle(style2);

					cell = row.createCell(5);
					cell.setCellValue("Battery");
					cell.setCellStyle(style2);

					cell = row.createCell(6);
					cell.setCellValue("Date Time");
					cell.setCellStyle(style2);

					sql1 = "select distinct cu.house_no,bl.MeterNo,bl.Reading,bl.Balance,bl.BatteryVoltage,bl.LogDate from BalanceLog bl,customer cu,customer_meter tcm,meter_master tmm where bl.MeterNo=tmm.meter_id and cu.cust_id=tcm.cust_id and tcm.meter_id=tmm.meter_id and cu.cust_id=? and YEAR(LogDate)=? order by MeterNo desc";
					pstmt = con.prepareStatement(sql1);
					pstmt.setInt(1, customer_id);
					pstmt.setInt(2, year);

					rs1 = pstmt.executeQuery();

					int i = 2;
					while (rs1.next()) {

						row = spreadsheet.createRow(i);
						cell = row.createCell(1);
						cell.setCellValue(rs1.getInt("house_no"));
						cell.setCellStyle(style2);

						cell = row.createCell(2);
						cell.setCellValue(rs1.getString("MeterNo"));
						cell.setCellStyle(style2);

						cell = row.createCell(3);
						cell.setCellValue(rs1.getString("Reading"));
						cell.setCellStyle(style2);

						cell = row.createCell(4);
						cell.setCellValue(rs1.getString("Balance"));
						cell.setCellStyle(style2);

						cell = row.createCell(5);
						cell.setCellValue(rs1.getString("BatteryVoltage"));
						cell.setCellStyle(style2);

						cell = row.createCell(6);
						cell.setCellValue(rs1.getString("LogDate"));
						cell.setCellStyle(style2);
						i++;
					}
					FileOutputStream out1 = new FileOutputStream(drivename
							+ file_name + ".xlsx");
					// FileOutputStream out1 = new FileOutputStream(new
					// File("exceldatabase.xlsx"));
					workbook.write(out1);
					out1.close();
					result = "Success";
					
				}
				}
			}
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		//	pstmt.close();
			con.close();
		}

		return result;
	}*/

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
			
				pstmt = con.prepareStatement("SELECT DISTINCT t.TransactionID, cmd.FirstName, cmd.LastName, cmd.HouseNumber, cmd.MeterID, t.Amount, t.ModeOfPayment, t.TransactionDate, t.CreatedByID FROM topup AS t \r\n" + 
						"LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerID = t.CustomerID WHERE t.CustomerID = ? AND t.TransactionDate BETWEEN ? AND ? ");
				
				pstmt.setInt(1, topupsummaryrequestvo.getCustomerID());
				pstmt.setString(2, topupsummaryrequestvo.getFromDate()+" 00:00:00");
				pstmt.setString(3,topupsummaryrequestvo.getToDate()+" 23:59:59");

				rs = pstmt.executeQuery();
				while (rs.next()) {

					topupsummaryresponsevo = new TopUpSummaryResponseVO();
					
					topupsummaryresponsevo.setTransactionID(rs.getInt("TransactionID"));
					topupsummaryresponsevo.setFirstName(rs.getString("FirstName"));
					topupsummaryresponsevo.setLastName(rs.getString("LastName"));
					topupsummaryresponsevo.setHouseNumber(rs.getString("HouseNumber"));
					topupsummaryresponsevo.setMeterID(rs.getString("MeterID"));
					topupsummaryresponsevo.setRechargeAmount(rs.getInt("Amount"));
					topupsummaryresponsevo.setModeOfPayment(rs.getString("ModeOfPayment"));
					
					if (rs.getInt("Status") == 2) {
						topupsummaryresponsevo.setStatus("Passed");
					} else if (rs.getInt("Status") == 1) {
						topupsummaryresponsevo.setStatus("Pending");
					} else if (rs.getInt("Status") == 0) {
						topupsummaryresponsevo.setStatus("Pending...waiting for acknowledge");
					}else {
						topupsummaryresponsevo.setStatus("Failed");
					}
					
					topupsummaryresponsevo.setDateTime(rs.getString("TransactionDate"));
					
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

	public List<AlarmsResponseVO> getAlarmdetails(int roleid, int id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AlarmsResponseVO> alarmsResponseList = null;
		int noAMRInterval = 0;
		double lowBatteryVoltage = 0.0;
		
		try {

			con = getConnection();
			alarmsResponseList = new LinkedList<AlarmsResponseVO>();
			AlarmsResponseVO alarmsResponseVO = null;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getFloat("LowBatteryVoltage");
			}
			
			String query = "SELECT c.CommunityName, b.BlockName, cmd.HouseNumber, cmd.FirstName, cmd.LastName, cmd.MeterID FROM customermeterdetails AS cmd LEFT JOIN community AS C on c.communityID = cmd.CommunityID LEFT JOIN block AS b on b.BlockID = cmd.BlockID <change>";
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid==2 || roleid==5) ? "WHERE cmd.BlockID = "+id : " ORDER BY cmd.CustomerID ASC"));
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
						alarmsResponseVO.setMeterID(rs.getString("MeterID"));
						alarmsResponseVO.setDifference(rs2.getInt("Minutes"));
						PreparedStatement pstmt3 = con.prepareStatement("SELECT BatteryVoltage, TamperDetect, IoTTimeStamp, TamperDetect, LowBattery FROM displaybalancelog WHERE MeterID = ?");
						pstmt3.setString(1, rs.getString("MeterID"));
						ResultSet rs3 = pstmt3.executeQuery();
						if(rs3.next()) {
							alarmsResponseVO.setDateTime(rs3.getString("IotTimeStamp"));
							if(rs3.getInt("LowBattery")==1 || rs3.getFloat("BatteryVoltage") < lowBatteryVoltage) {
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

}
