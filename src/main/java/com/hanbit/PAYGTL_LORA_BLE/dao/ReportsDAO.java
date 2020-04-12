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

	/*
	 * public List<FinancialReportsResponseVO>
	 * getFinancialReportsdetails(FinancialReportsRequestVO
	 * financialreportsrequestvo) throws SQLException { // TODO Auto-generated
	 * method stub
	 * 
	 * Connection con = null; PreparedStatement pstmt = null; PreparedStatement
	 * pstmt1 = null; PreparedStatement pstmt2 = null; PreparedStatement pstmt3
	 * = null; PreparedStatement pstmt4 = null; ResultSet rs = null; ResultSet
	 * rs1 = null; ResultSet rs2 = null; ResultSet rs3 = null; ResultSet rs4 =
	 * null;
	 * 
	 * FinancialReportsResponseVO financialreportseresponsevo = null;
	 * List<FinancialReportsResponseVO> financialreportsresponselist = null;
	 * 
	 * try{ con = getConnection();
	 * 
	 * financialreportsresponselist = new
	 * ArrayList<FinancialReportsResponseVO>();
	 * 
	 * pstmt1 =
	 * con.prepareStatement("select com_id from community where com_name =?");
	 * pstmt1.setString(1, financialreportsrequestvo.getCommunityName()); rs1 =
	 * pstmt1.executeQuery(); if(rs1.next()){
	 * 
	 * pstmt2 =
	 * con.prepareStatement("select block_id from block where name = ? and com_id=?"
	 * ); pstmt2.setString(1, financialreportsrequestvo.getBlockName());
	 * pstmt2.setInt(2, rs1.getInt("com_id")); rs2 = pstmt2.executeQuery();
	 * if(rs2.next()){
	 * 
	 * pstmt3 = con.prepareStatement(
	 * "select cust_id from customer where house_no=? and block_id=?");
	 * pstmt3.setString(1, financialreportsrequestvo.getHouseNo());
	 * pstmt3.setInt(2, rs2.getInt("block_id")); rs3 = pstmt3.executeQuery();
	 * if(rs3.next()){
	 * 
	 * pstmt4 = con.prepareStatement(
	 * "select tcm.meter_id as mid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id =tmm.meter_id and tc.com_id=? and tb.block_id=? and tcu.cust_id=?"
	 * ); pstmt4.setInt(1, rs1.getInt("com_id")); pstmt4.setInt(2,
	 * rs2.getInt("block_id")); pstmt4.setInt(3, rs3.getInt("cust_id")); rs4 =
	 * pstmt4.executeQuery(); if(rs4.next()){ pstmt=con.prepareStatement(
	 * "select RechargeAmt as amount from Recharge_Transaction where YEAR(RecordInsertTime)=? and month(RecordInsertTime)=? and Meter_id=? and Ack_Status=1"
	 * ); pstmt.setInt(1,financialreportsrequestvo.getYear());
	 * pstmt.setString(2,financialreportsrequestvo.getMonth()); pstmt.setInt(3,
	 * rs4.getInt("mid")); rs=pstmt.executeQuery(); while(rs.next()) {
	 * financialreportseresponsevo = new FinancialReportsResponseVO();
	 * financialreportseresponsevo.setRechargeAmount(rs.getFloat("amount"));
	 * financialreportsresponselist.add(financialreportseresponsevo); } } } } }
	 * 
	 * } catch (Exception ex) { ex.printStackTrace(); } finally { pstmt.close();
	 * pstmt1.close(); pstmt2.close(); pstmt3.close(); pstmt4.close();
	 * rs.close(); rs1.close(); rs2.close(); rs3.close(); rs4.close();
	 * con.close(); }
	 * 
	 * return financialreportsresponselist; }
	 */

	/* User Consumption Reports */

	public List<UserConsumptionReportsResponseVO> getuserconsumptionreportsdetails(
			UserConsumptionRequestVO userconsumptionreportsrequestvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;

		List<UserConsumptionReportsResponseVO> userconsumptionreportsresponselist = null;
		UserConsumptionReportsResponseVO userconsumptionreportsresponsevo = null;

		try {
			con = getConnection();
			userconsumptionreportsresponselist = new ArrayList<UserConsumptionReportsResponseVO>();

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
					pstmt4 = con
							.prepareStatement("select tcm.meter_id as mid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id =tmm.meter_id and tc.com_id=? and tb.block_id=? and tcu.cust_id=?");

					pstmt4.setInt(1, LoginDAO.CommunityID);
					pstmt4.setInt(2, rs2.getInt("block_id"));
					pstmt4.setInt(3, rs3.getInt("cust_id"));
					rs4 = pstmt4.executeQuery();
					if (rs4.next()) {
						
						if (!userconsumptionreportsrequestvo.getMonth().equals(
								"true")) {
							String sql1 = "select ReadingID,MeterNo,Reading,Balance,BatteryVoltage,LogDate,Gas_Tariff,Alarmcredit,Emergencycredit,TamperDetect from BalanceLog where MeterNo=? and YEAR(LogDate)=? and MONTH(LogDate)=? order by ReadingID desc";
							pstmt = con.prepareStatement(sql1);
							pstmt.setInt(1, rs4.getInt("mid"));
							pstmt.setInt(2,
									userconsumptionreportsrequestvo.getYear());
							pstmt.setString(3,
									userconsumptionreportsrequestvo.getMonth());

							rs = pstmt.executeQuery();
							while (rs.next()) {

								userconsumptionreportsresponsevo = new UserConsumptionReportsResponseVO();
								userconsumptionreportsresponsevo.setMeterID(rs4
										.getInt("mid"));

								float reading = Float.parseFloat(rs
										.getString("Reading"));
								reading = reading / 100;
								DecimalFormat decForcost1 = new DecimalFormat(
										"0000.00");
								String reading1 = decForcost1.format(reading);

								userconsumptionreportsresponsevo
										.setReading(reading1);
								userconsumptionreportsresponsevo.setBalance(rs
										.getFloat("Balance"));
								userconsumptionreportsresponsevo.setBattery(rs
										.getFloat("BatteryVoltage"));

								float tariff = rs.getFloat("Gas_Tariff") * 100;
								DecimalFormat decForcost2 = new DecimalFormat(
										"00.00");
								String tariff1 = decForcost2.format(tariff);

								userconsumptionreportsresponsevo
										.setTariff(tariff1);
								userconsumptionreportsresponsevo
										.setAlarmCredit(rs
												.getFloat("Alarmcredit"));
								userconsumptionreportsresponsevo
										.setEmergencyCredit(rs
												.getFloat("Emergencycredit"));
								if (rs.getInt("TamperDetect") == 1) {
									userconsumptionreportsresponsevo
											.setGasLeak("YES");
								} else {
									userconsumptionreportsresponsevo
											.setGasLeak("NO");
								}
								userconsumptionreportsresponsevo.setDateTime(rs
										.getString("LogDate"));
								userconsumptionreportsresponselist
										.add(userconsumptionreportsresponsevo);
							}
						} else {
							String sql1 = "select ReadingID,MeterNo,Reading,Balance,BatteryVoltage,LogDate,Gas_Tariff,Alarmcredit,Emergencycredit,TamperDetect from BalanceLog where MeterNo=? and YEAR(LogDate)=? order by ReadingID desc";
							pstmt = con.prepareStatement(sql1);
							pstmt.setInt(1, rs4.getInt("mid"));
							pstmt.setInt(2,
									userconsumptionreportsrequestvo.getYear());

							rs = pstmt.executeQuery();
							while (rs.next()) {

								userconsumptionreportsresponsevo = new UserConsumptionReportsResponseVO();
								userconsumptionreportsresponsevo.setMeterID(rs4
										.getInt("mid"));

								float reading = Float.parseFloat(rs
										.getString("Reading"));
								reading = reading / 100;
								DecimalFormat decForcost1 = new DecimalFormat(
										"0000.00");
								String reading1 = decForcost1.format(reading);

								userconsumptionreportsresponsevo
										.setReading(reading1);
								userconsumptionreportsresponsevo.setBalance(rs
										.getFloat("Balance"));
								userconsumptionreportsresponsevo.setBattery(rs
										.getFloat("BatteryVoltage"));

								float tariff = rs.getFloat("Gas_Tariff") * 100;
								DecimalFormat decForcost2 = new DecimalFormat(
										"00.00");
								String tariff1 = decForcost2.format(tariff);

								userconsumptionreportsresponsevo
										.setTariff(tariff1);
								userconsumptionreportsresponsevo
										.setAlarmCredit(rs
												.getFloat("Alarmcredit"));
								userconsumptionreportsresponsevo
										.setEmergencyCredit(rs
												.getFloat("Emergencycredit"));
								if (rs.getInt("TamperDetect") == 1) {
									userconsumptionreportsresponsevo
											.setGasLeak("YES");
								} else {
									userconsumptionreportsresponsevo
											.setGasLeak("NO");
								}
								userconsumptionreportsresponsevo.setDateTime(rs
										.getString("LogDate"));
								userconsumptionreportsresponselist
										.add(userconsumptionreportsresponsevo);
							}
						}
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			/*
			 * pstmt.close(); pstmt2.close(); pstmt3.close(); pstmt4.close();
			 * rs.close(); rs2.close(); rs3.close(); rs4.close();
			 */
			con.close();
		}

		return userconsumptionreportsresponselist;
	}

	public String getpdf(
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

						/*
						 * if ("month".equals(type)) { head = com_name +
						 * " Community " + block_name + " Block " + house_no +
						 * " Flat " + year + "-" + month +
						 * " Month Consumption Report"; }
						 */

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
			/*pstmt.close();
			rs.close();*/
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
					/*
					 * if ("month".equals(type)) { head = com_name +
					 * " Community " + block_name + " Block " + house_no +
					 * " Flat " + year + "-" + month + " Month Report"; }
					 */

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
	}

	/* TopUp Summary */

	public List<TopUpSummaryResponseVO> gettopupsummarydetails(
			TopUpSummaryRequestVO topupsummaryrequestvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;

		List<TopUpSummaryResponseVO> topupsummarydetails = null;
		TopUpSummaryResponseVO topupsummaryresponsevo = null;

		try {
			con = getConnection();
			topupsummarydetails = new LinkedList<TopUpSummaryResponseVO>();

			pstmt2 = con
					.prepareStatement("select block_id from block where name = ? and com_id=?");
			pstmt2.setString(1, topupsummaryrequestvo.getBlockName());
			pstmt2.setInt(2, LoginDAO.CommunityID);
			rs2 = pstmt2.executeQuery();
			if (rs2.next()) {
				pstmt3 = con
						.prepareStatement("select cust_id from customer where house_no=? and block_id=?");
				pstmt3.setString(1, topupsummaryrequestvo.getHouseNo());
				pstmt3.setInt(2, rs2.getInt("block_id"));
				rs3 = pstmt3.executeQuery();
				if (rs3.next()) {
					pstmt4 = con
							.prepareStatement("select tcm.meter_id as mid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id =tmm.meter_id and tc.com_id=? and tb.block_id=? and tcu.cust_id=?");
					pstmt4.setInt(1, LoginDAO.CommunityID);
					pstmt4.setInt(2, rs2.getInt("block_id"));
					pstmt4.setInt(3, rs3.getInt("cust_id"));
					rs4 = pstmt4.executeQuery();
					if (rs4.next()) {

						if (topupsummaryrequestvo.getMonth().equals(
								"true")) {
							pstmt = con
									.prepareStatement("select mm.meter_id,mm.meter_serial_no,rt.CUST_ID,rt.Transid,rt.RechargeAmt,rt.RecordInsertTime,c.house_no,c.first_name,c.last_name,rt.Ack_Status from meter_master mm join Recharge_Transaction rt on mm.meter_id=rt.Meter_id join customer c on c.CUST_ID=rt.CUST_ID WHERE  mm.meter_id=? AND YEAR(RecordInsertTime)=? and rt.Ack_Status=1 ORDER BY rt.RecordInsertTime desc");
							pstmt.setInt(1, rs4.getInt("mid"));
							pstmt.setInt(2, topupsummaryrequestvo.getYear());
							rs = pstmt.executeQuery();
							while (rs.next()) {
								topupsummaryresponsevo = new TopUpSummaryResponseVO();
								topupsummaryresponsevo.setHouseNo(rs
										.getString("house_no"));
								topupsummaryresponsevo.setMeterSerialNo(rs
										.getString("meter_serial_no"));
								topupsummaryresponsevo.setName(rs
										.getString("first_name")
										+ " "
										+ rs.getString("last_name"));
								topupsummaryresponsevo.setBillNo(rs
										.getInt("Transid"));
								topupsummaryresponsevo.setRechargeAmount(rs
										.getInt("RechargeAmt"));
								if(rs.getString("Ack_Status").equals("1")){
									topupsummaryresponsevo.setStatus("Passed");
								}else if(rs.getString("Ack_Status").equals("0")){
									topupsummaryresponsevo.setStatus("Time Out... Resend");
								}else{
									topupsummaryresponsevo.setStatus("Pending...waiting for ack");
								}
								topupsummaryresponsevo.setDateTime(rs
										.getString("RecordInsertTime"));
								topupsummarydetails.add(topupsummaryresponsevo);
							}
						} else {
							pstmt = con
									.prepareStatement("select mm.meter_id,mm.meter_serial_no,rt.CUST_ID,rt.Transid,rt.RechargeAmt,rt.RecordInsertTime,c.house_no,c.first_name,c.last_name,rt.Ack_Status from meter_master mm join Recharge_Transaction rt on mm.meter_id=rt.Meter_id join customer c on c.CUST_ID=rt.CUST_ID WHERE  mm.meter_id=? AND YEAR(RecordInsertTime)=? AND MONTH(RecordInsertTime)=? and rt.Ack_Status=1 ORDER BY rt.RecordInsertTime desc");
							pstmt.setInt(1, rs4.getInt("mid"));
							pstmt.setInt(2, topupsummaryrequestvo.getYear());
							pstmt.setString(3, topupsummaryrequestvo.getMonth());
							rs = pstmt.executeQuery();
							while (rs.next()) {
								topupsummaryresponsevo = new TopUpSummaryResponseVO();
								topupsummaryresponsevo.setHouseNo(rs
										.getString("house_no"));
								topupsummaryresponsevo.setMeterSerialNo(rs
										.getString("meter_serial_no"));
								topupsummaryresponsevo.setName(rs
										.getString("first_name")
										+ " "
										+ rs.getString("last_name"));
								topupsummaryresponsevo.setBillNo(rs
										.getInt("Transid"));
								topupsummaryresponsevo.setRechargeAmount(rs
										.getInt("RechargeAmt"));
								topupsummaryresponsevo.setStatus(rs
										.getString("Ack_Status"));
								topupsummaryresponsevo.setDateTime(rs
										.getString("RecordInsertTime"));
								topupsummarydetails.add(topupsummaryresponsevo);
							}
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			pstmt2.close();
			pstmt3.close();
			pstmt4.close();
			rs.close();
			rs2.close();
			rs3.close();
			rs4.close();
			con.close();
		}

		return topupsummarydetails;

	}

	/* Valve Reports */

	public List<ValveReportsResponseVO> getvalvereports() throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ValveReportsResponseVO> valvereportslist = null;
		ValveReportsResponseVO valvereportsresponsevo = null;
		try {
			con = getConnection();

			valvereportslist = new ArrayList<ValveReportsResponseVO>();
			// write query
			pstmt = con
					.prepareStatement("select meter_id,open_time,close_time,record_insert_date,remark from valve");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				valvereportsresponsevo = new ValveReportsResponseVO();

				valvereportsresponsevo.setMeterID(rs.getInt("meter_id"));
				valvereportsresponsevo.setOpenTime(rs.getString("open_time"));
				valvereportsresponsevo.setCloseTime(rs.getString("close_time"));
				valvereportsresponsevo.setDateTime(rs
						.getString("record_insert_date"));
				valvereportsresponsevo.setRemark(rs.getString("remark"));

				valvereportslist.add(valvereportsresponsevo);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return valvereportslist;
	}

	/* Alarms */

	public List<AlarmsResponseVO> getAlarmdetails() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		long noamrintervaltime = 0;
		double lowbatteryvol = 0.0d;
		long no_amr = 0;
		String meterId = "";
		String timeOfReading = "";
		long Diff = 0;
		double batteryVoltage = 0.0d;
		String tamper = "";
		String comName = "";
		String blockName = "";
		String houseNo = "";
		List<AlarmsResponseVO> no_amr_list = null;
		try {

			con = getConnection();
			no_amr_list = new LinkedList<AlarmsResponseVO>();
			AlarmsResponseVO alarmvo = null;
			sql = "select no_amr,battery_volt from alert_settings where com_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, LoginDAO.CommunityID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				noamrintervaltime = rs.getLong("no_amr");
				lowbatteryvol = rs.getDouble("battery_volt");
			}

			pstmt = con
					.prepareStatement("select past.MeterNo as meter_id,MAX(past.LogDate) as maxtime,DATEDIFF(MI,max(past.LogDate),SYSDATETIME()) diff from BalanceLog past inner join (select c.com_id cid,c.com_name as cname,b.name as bname,cs.house_no as hno,cr.MeterNo as mid1 from community c,block b,customer cs,customer_meter cm,BalanceLog cr where c.com_id=b.com_id and b.block_id=cs.block_id and cs.cust_id=cm.cust_id and cm.meter_id=cr.MeterNo and c.com_id=?) AS  com ON com.mid1=past.MeterNo GROUP BY past.MeterNo order by past.MeterNo");
			pstmt.setInt(1, LoginDAO.CommunityID);
			ResultSet rs1 = pstmt.executeQuery();
			while (rs1.next()) {
				alarmvo = new AlarmsResponseVO();
				meterId = rs1.getString("meter_id");
				timeOfReading = rs1.getString("maxtime");
				Diff = rs1.getLong("diff");
				alarmvo.setAmrID(rs1.getString("meter_id"));
				alarmvo.setDateTime(rs1.getString("maxtime"));
				long setdiff = rs1.getLong("diff");
				setdiff = (setdiff / (60 * 24));
				alarmvo.setDifference(setdiff);
				// alarmvo.setBatteryVoltageConstant(rs.getDouble("battery_volt"));
				// alarmvo.setNoAmrIntervalTime(rs.getLong("no_amr"));
				String sql2 = "select BatteryVoltage as bvolt,TamperDetect as tamper from BalanceLog where MeterNo=? and LogDate=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, meterId);
				pstmt.setString(2, timeOfReading);
				// pstmt.setDouble(3, lowbatteryvol);
				ResultSet rs2 = pstmt.executeQuery();
				if (rs2.next()) {
					batteryVoltage = rs2.getDouble("bvolt");
					tamper = rs2.getString("tamper");

					if (batteryVoltage < lowbatteryvol) {
						alarmvo.setBatteryVoltage(Double
								.toString(batteryVoltage));
					} else {
						alarmvo.setBatteryVoltage("----");
					}
					if (rs2.getInt("tamper") == 1) {
						alarmvo.setTamper("YES");
					} else {
						alarmvo.setTamper("----");
					}

					if (Diff > noamrintervaltime
							|| batteryVoltage < lowbatteryvol
							|| tamper.equals("1")) {
						String sql3 = "select tc.com_name as cname,tb.name as bname,tcu.house_no as hno from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id=tmm.meter_id and tcm.meter_id=?";
						pstmt = con.prepareStatement(sql3);
						pstmt.setString(1, meterId);
						ResultSet rs3 = pstmt.executeQuery();
						if (rs3.next()) {

							comName = rs3.getString("cname");
							blockName = rs3.getString("bname");
							houseNo = rs3.getString("hno");
							// alarmvo.setCommunityName(rs3.getString("cname"));
							alarmvo.setBlockName(rs3.getString("bname"));
							alarmvo.setHouseNo(rs3.getString("hno"));
							no_amr_list.add(alarmvo);
						}
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
		return no_amr_list;
	}

}
