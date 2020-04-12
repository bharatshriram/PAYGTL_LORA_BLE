/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class AlarmRequestVO {
	
	private String amrID;
	private String dateTime;
	private long difference;
	private double batteryVoltageConstant;
	private long noAmrIntervalTime;
	private String batteryVoltage;
	private String tamper;
	private String communityName;	
	private String blockName;
	private String houseNo;
	
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
	public long getDifference() {
		return difference;
	}
	public void setDifference(long difference) {
		this.difference = difference;
	}
	public double getBatteryVoltageConstant() {
		return batteryVoltageConstant;
	}
	public void setBatteryVoltageConstant(double batteryVoltageConstant) {
		this.batteryVoltageConstant = batteryVoltageConstant;
	}
	public long getNoAmrIntervalTime() {
		return noAmrIntervalTime;
	}
	public void setNoAmrIntervalTime(long noAmrIntervalTime) {
		this.noAmrIntervalTime = noAmrIntervalTime;
	}
	public String getBatteryVoltage() {
		return batteryVoltage;
	}
	public void setBatteryVoltage(String batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	public String getTamper() {
		return tamper;
	}
	public void setTamper(String tamper) {
		this.tamper = tamper;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
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
		
}
