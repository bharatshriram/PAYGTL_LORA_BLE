/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import com.hanbit.PAYGTL_LORA_BLE.constants.DataBaseConstants;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ValveRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.DashboardResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;

/**
 * @author k VimaL Kumar
 * 
 */
public class DashboardDAO {

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	public List<DashboardResponseVO> getDashboarddetails(int roleid, int id)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DashboardResponseVO> dashboard_list = null;
		DashboardResponseVO dashboardvo = null;
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();

			String query = "";
			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY customermeterdetails.CustomerID ASC" : (roleid == 2 || roleid == 5) ? "WHERE customermeterdetails.BlockID = "+id+ " ORDER BY customermeterdetails.CustomerID ASC" : (roleid == 3) ? "WHERE customermeterdetails.CustomerID = "+id:""));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dashboardvo = new DashboardResponseVO();
				dashboardvo.setBlock(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setGasTariff((rs.getFloat("tariff")*100));

				float reading = Float.parseFloat(rs.getString("Reading"));
				reading = reading / 100;
				DecimalFormat decForcost1 = new DecimalFormat("0000.00");
				String reading1 = decForcost1.format(reading);
				dashboardvo.setReading(reading1);

				float balance = Float.parseFloat(rs.getString("Balance"));
				DecimalFormat decForcost3 = new DecimalFormat("0000.00");
				String balance3 = decForcost3.format(balance);
				dashboardvo.setBalance(balance3);

				dashboardvo.setTimeStamp(rs.getString("dt"));
				dashboardvo.setEmergencyCredit(rs.getString("emcredit"));

				if (Integer.parseInt(rs.getString("status")) == 0) {
					dashboardvo.setValve("Open");
				} else {
					dashboardvo.setValve("Closed");
				}

				dashboardvo.setBattery(rs.getString("bvolt"));

				dashboard_list.add(dashboardvo);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return dashboard_list;
	}

	public List<DashboardResponseVO> getUserDashboarddetails(int meterID)
			throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DashboardResponseVO> userdashboard_list = null;
		DashboardResponseVO dashboardvo = null;
		try {
			con = getConnection();
			userdashboard_list = new LinkedList<DashboardResponseVO>();

			pstmt = con
					.prepareStatement("select tc.com_name AS cname,tb.name AS bname,tcu.house_no AS hno,tcu.first_name as fname,tcu.last_name as lname,tcu.email as email,tcu.mobile as mobile,tcm.meter_id as MID,tbl.reading AS reading,tbl.balance AS balance,tbl.logdate AS dt,tbl.Gas_tariff as tariff,tbl.Alarmcredit as alcredit,tbl.Emergencycredit as emcredit,tbl.SolonideStatus as status,tbl.BatteryVoltage as bvolt,tbl.MeterType as mtype,tbl.CreditStatus as cs,tbl.TamperDetect as tdt,tbl.LowBattery as lw,tbl.CheckMeter as cm,tbl.Emergency as em,tbl.Regular as rg,tbl.CreditNegative as aneg,tbl.CreditExhausted as cexe,tbl.CreditLow as clow,tbl.CreditOk as cok,tbl.LowEmergenyCredit as lemc from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm ,(SELECT  meterno, MAX(logdate) as ld FROM BalanceLog group by meterno) bl,BalanceLog tbl where tc.com_id=tb.com_id  and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id=tmm.meter_id and tmm.meter_id =bl.MeterNo and tbl.logdate = bl.ld and tbl.MeterNo = bl.MeterNo and tbl.MeterNo=? order by dt desc");
			pstmt.setInt(1, meterID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dashboardvo = new DashboardResponseVO();
				dashboardvo.setBlock(rs.getString("bname"));
				dashboardvo.setHouseNumber(rs.getString("hno"));

				float reading = Float.parseFloat(rs.getString("reading"));
				reading = reading / 100;
				DecimalFormat decForcost1 = new DecimalFormat("0000.00");
				String reading1 = decForcost1.format(reading);
				dashboardvo.setReading(reading1);

				float balance = Float.parseFloat(rs.getString("balance"));
				DecimalFormat decForcost3 = new DecimalFormat("0000.00");
				String balance3 = decForcost3.format(balance);
				dashboardvo.setBalance(balance3);

				dashboardvo.setTimeStamp(rs.getString("dt"));
				dashboardvo.setEmergencyCredit(rs.getString("emcredit"));

				if (Integer.parseInt(rs.getString("status")) == 0) {
					dashboardvo.setValve("Open");
				} else {
					dashboardvo.setValve("Closed");
				}

				dashboardvo.setBattery(rs.getString("bvolt"));

				userdashboard_list.add(dashboardvo);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return userdashboard_list;
	}

	public String setvalve(ValveRequestVO valverequestvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		String result = "Failure";

		try {
			con = getConnection();
			
			pstmt2 = con
					.prepareStatement("select block_id from block where name = ? and com_id=?");
			pstmt2.setString(1, valverequestvo.getBlockName());
			pstmt2.setInt(2, LoginDAO.CommunityID);
			rs2 = pstmt2.executeQuery();
			if (rs2.next()) {
				pstmt3 = con.prepareStatement("select cust_id from customer where house_no=? and block_id=?");
				pstmt3.setString(1,valverequestvo.getHouseNo());
				pstmt3.setInt(2, rs2.getInt("block_id"));
				rs3 = pstmt3.executeQuery();
				if (rs3.next()) {
					pstmt4 = con.prepareStatement("select tcm.meter_id as mid from community tc,block tb,customer tcu,customer_meter tcm,meter_master tmm where tc.com_id=tb.com_id and tb.block_id=tcu.block_id and tcu.cust_id=tcm.cust_id and tcm.meter_id =tmm.meter_id and tc.com_id=? and tb.block_id=? and tcu.cust_id=?");

					pstmt4.setInt(1, LoginDAO.CommunityID);
					pstmt4.setInt(2, rs2.getInt("block_id"));
					pstmt4.setInt(3, rs3.getInt("cust_id"));
					rs4 = pstmt4.executeQuery();
					if (rs4.next()) {
			
									ps = con.prepareStatement("SELECT MAX(valve_id) AS vid FROM valve");
									rs = ps.executeQuery();
										if (rs.next()) {

											pstmt = con.prepareStatement("INSERT INTO valve(valve_id,meter_id,open_time,close_time,remark,record_insert_date) VALUES(?,?,?,?,?,SYSDATETIME())");
											pstmt.setInt(1, (rs.getInt("vid") + 1));
											pstmt.setString(2, rs4.getString("mid"));
											pstmt.setString(3, valverequestvo.getOpenTime());
											pstmt.setString(4, valverequestvo.getCloseTime());
											pstmt.setString(5, valverequestvo.getRemark());

												if (pstmt.executeUpdate() > 0) {
													result = "Success";
												}
										}
		
									}
								}
							}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ps.close();
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

	public ResponseVO postDashboarddetails(String json) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		String json1 = "";
		int tamper = 0;
		int lowbattery = 0;
		String timestamp = "";
		PreparedStatement pstmtch = null;
		ResultSet rsch = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();
		String date = "";
		String addminuteHour = "";
		String iot_Timestamp = "";

		try {

			con = getConnection();

			json = json.replaceAll("\\\\", "");

			json1 = json.replaceAll("\"rep\":\"", "\"rep\":");

			json1 = json1.replaceAll("\"con\":\"", "\"con\":");

			json1 = json1.replaceAll("}\r\n" + "						\"", "}");

			json1 = json1.replaceAll("}\"", "}");

			JSONObject jsonObj = new JSONObject(json1);
			if (jsonObj.has("m2m:sgn") && jsonObj.getJSONObject("m2m:sgn").has("nev")
					&& jsonObj.getJSONObject("m2m:sgn").getJSONObject("nev").has("rep")
					&& jsonObj.getJSONObject("m2m:sgn").getJSONObject("nev").getJSONObject("rep").has("m2m:cin")
					&& jsonObj.getJSONObject("m2m:sgn").getJSONObject("nev").getJSONObject("rep")
							.getJSONObject("m2m:cin").has("con")
					&& jsonObj.getJSONObject("m2m:sgn").getJSONObject("nev").getJSONObject("rep")
							.getJSONObject("m2m:cin").getJSONObject("con").has("payloads_ul")) {

				JSONObject payloadUplink = jsonObj.getJSONObject("m2m:sgn").getJSONObject("nev").getJSONObject("rep")
						.getJSONObject("m2m:cin").getJSONObject("con").getJSONObject("payloads_ul");

				timestamp = payloadUplink.get("timestamp").toString();

				String guid = payloadUplink.get("dataFrame").toString();

				// String guid="CgAAAED/AQAAAAAAAAAAARc=";

				byte[] decoded = Base64.getDecoder().decode(guid);

				String StartByte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
						.substring(0, 2);

				if (StartByte.equalsIgnoreCase("0A")) {

					// 0A 00 00 00 00 42 29 01 00 00 00 00 00 00 00 00 00 00 00 00 00 17

					String DeviceId = payloadUplink.get("deveui").toString();

					String meterReadingbyte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(2, 10);

					String meterStatusbyte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(10, 12);

					String batteryStatusbyte = (String) String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(12, 14);

					String meterTypebyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(14, 16);

					String creditbyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase().substring(16,
							24);

					String tariffbyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase().substring(24,
							32);

					String emergencyCreditbyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(32, 40);

					String valveStatusbyte = String.format("%044x", new BigInteger(1, decoded)).toUpperCase()
							.substring(40, 42);

					int meterReading = DashboardDAO.hexDecimal(meterReadingbyte);

					String meterStatus = meterStatusbyte;

					if (meterStatus.equalsIgnoreCase("40")) {
						tamper = 0;
						lowbattery = 0;
					} else if (meterStatus.equalsIgnoreCase("42")) {
						tamper = 0;
						lowbattery = 1;
					} else if (meterStatus.equalsIgnoreCase("44")) {
						tamper = 1;
						lowbattery = 0;
					}

					else if (meterStatus.equalsIgnoreCase("00")) {
						tamper = 0;
						lowbattery = 0;
					}

					int batteryStatus = DashboardDAO.hexDecimal(batteryStatusbyte);

					float batterylevel = (float) ((batteryStatus * 3.6) / 256);

					int meterType = DashboardDAO.hexDecimal(meterTypebyte);

					Long i = Long.parseLong(creditbyte, 16);
					Float amountAvailable = Float.intBitsToFloat(i.intValue());

					Long j = Long.parseLong(tariffbyte, 16);
					Float tariffAvailable = Float.intBitsToFloat(j.intValue());

					Long k = Long.parseLong(emergencyCreditbyte, 16);
					Float emergencyCreditAvailable = Float.intBitsToFloat(k.intValue());

					int valveStatus = DashboardDAO.hexDecimal(valveStatusbyte);

					String sql6 = "select Top 1 IoT_TimeStamp,MeterNo FROM BalanceLog where MeterNo=? order by LogDate desc";
					pstmtch = con.prepareStatement(sql6);
					pstmtch.setString(1, DeviceId);
					rsch = pstmtch.executeQuery();

					if (rsch.next()) {

						iot_Timestamp = rsch.getString("IoT_TimeStamp");

					}

					date = timestamp.replaceAll("T", " ");

					date = date.replaceAll("Z", "");

					String sql9 = "  SELECT DATEADD(hour,5, ?) 'FinalDate'";

					PreparedStatement dateupdated = con.prepareStatement(sql9);

					dateupdated.setString(1, date);

					ResultSet rsdateupdated = dateupdated.executeQuery();

					if (rsdateupdated.next()) {

						String addfiveHr = rsdateupdated.getString("FinalDate");

						String sql10 = "  SELECT DATEADD(minute,30, ?) 'mintueDate'";
						PreparedStatement mintueupdated = con.prepareStatement(sql10);

						mintueupdated.setString(1, addfiveHr);

						ResultSet rsmintueupdated = mintueupdated.executeQuery();

						if (rsmintueupdated.next()) {

							addminuteHour = rsmintueupdated.getString("mintueDate");

						}

					}

					if (!addminuteHour.equalsIgnoreCase(iot_Timestamp)) {

						String sql4 = "INSERT INTO balancelog (MeterID, Reading, Balance, BatteryVoltage, Tariff, AlarmCredit, EmergencyCredit, MeterType, SolonideStatus,"
								+ "CreditStatus, TamperDetect, LowBattery, IoTTimeStamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						pstmt = con.prepareStatement(sql4);

						pstmt.setString(1, DeviceId);
						pstmt.setInt(2, meterReading);
						pstmt.setFloat(3, amountAvailable);// Balance Pending
						pstmt.setFloat(4, batterylevel);
						pstmt.setFloat(5, tariffAvailable);
						pstmt.setInt(6, 0);
						pstmt.setFloat(7, emergencyCreditAvailable);
						pstmt.setInt(8, meterType);
						pstmt.setInt(9, valveStatus);
						pstmt.setInt(10, 0);// Balance Pending
						pstmt.setInt(11, tamper);
						pstmt.setInt(12, lowbattery);
						pstmt.setString(13, addminuteHour);

						if (pstmt.executeUpdate() > 0) {
							responsevo.setResult("Success");
							
							/*PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MeterID, Reading, Balance, BatteryVoltage, Tariff, AlarmCredit, EmergencyCredit, MeterType, SolonideStatus,\"\r\n" + 
									"CreditStatus, TamperDetect, LowBattery, IoTTimeStamp) SELECT ReadingID, MeterID, Reading, Balance, BatteryVoltage, Tariff, AlarmCredit, EmergencyCredit, MeterType, SolonideStatus," + 
									"CreditStatus, TamperDetect, LowBattery, IoTTimeStamp FROM balancelog WHERE (SELECT COUNT(*) FROM displaybalancelog WHERE MeterID = ?) = 0;");*/
							
//							INSERT IGNORE INTO testtable (dt, numb) VALUES ('2020-04-08 22:54:56', '100')
							
							PreparedStatement pstmt1 = con.prepareStatement("INSERT IGNORE INTO displaybalancelog (MainBalanceLogID, MeterID, Reading, Balance, BatteryVoltage, Tariff, AlarmCredit, EmergencyCredit, MeterType, SolonideStatus," + 
									"CreditStatus, TamperDetect, LowBattery, IoTTimeStamp) VALUES ((SELECT MAX(ReadingID) FROM balancelog WHERE MeterID = ?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
							pstmt1.setString(1, DeviceId);
							pstmt1.setString(2, DeviceId);
							pstmt1.setInt(3, meterReading);
							pstmt1.setFloat(4, amountAvailable);
							pstmt1.setFloat(5, batterylevel);
							pstmt1.setFloat(6, tariffAvailable);
							pstmt1.setInt(7, 0);
							pstmt1.setFloat(8, emergencyCreditAvailable);
							pstmt1.setInt(9, meterType);
							pstmt1.setInt(10, valveStatus);
							pstmt1.setInt(11, 0);
							pstmt1.setInt(12, tamper);
							pstmt1.setInt(13, lowbattery);
							pstmt1.setString(14, addminuteHour);
							pstmt1.executeUpdate();
						}

					} else {

						responsevo.setResult("No Data to update");
					}

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			// pstmt.close();
			con.close();

		}

		return responsevo;
	}

	private static int hexDecimal(String meterStatusbyte) {
		// TODO Auto-generated method stub
		String digits = "0123456789ABCDEF";
		int val = 0;
		for (int i = 0; i < meterStatusbyte.length(); i++) {
			char c = meterStatusbyte.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}

		return val;
	}
}
