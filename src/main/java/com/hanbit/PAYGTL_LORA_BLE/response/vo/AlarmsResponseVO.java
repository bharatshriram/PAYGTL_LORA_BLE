/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class AlarmsResponseVO {

	private String blockName;
	private String houseNo;
	private String amrID;
	private String dateTime;
	private String tamper;
	private String batteryVoltage;
	private long difference;
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getAmrID() {
		return amrID;
	}
	public void setAmrID(String amrID) {
		this.amrID = amrID;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getTamper() {
		return tamper;
	}
	public void setTamper(String tamper) {
		this.tamper = tamper;
	}
	public String getBatteryVoltage() {
		return batteryVoltage;
	}
	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	public long getDifference() {
		return difference;
	}
	public void setDifference(long difference) {
		this.difference = difference;
	}

}
