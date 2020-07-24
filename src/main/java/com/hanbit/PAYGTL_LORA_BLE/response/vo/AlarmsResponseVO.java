/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class AlarmsResponseVO {
	
	private String communityName;
	private String blockName;
	private String houseNumber;
	private String CRNNumber;
	private String meterID;
	private String dateTime;
	private String tamper;
	private String tamperTimeStamp;
	private String batteryVoltage;
	private long difference;
	private String solonideStatus;
	
	private List<AlarmsResponseVO> data;
	
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
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getMeterID() {
		return meterID;
	}
	public void setMeterID(String meterID) {
		this.meterID = meterID;
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
	public List<AlarmsResponseVO> getData() {
		return data;
	}
	public void setData(List<AlarmsResponseVO> data) {
		this.data = data;
	}
	public String getCRNNumber() {
		return CRNNumber;
	}
	public void setCRNNumber(String cRNNumber) {
		CRNNumber = cRNNumber;
	}
	public String getSolonideStatus() {
		return solonideStatus;
	}
	public void setSolonideStatus(String solonideStatus) {
		this.solonideStatus = solonideStatus;
	}
	public String getTamperTimeStamp() {
		return tamperTimeStamp;
	}
	public void setTamperTimeStamp(String tamperTimeStamp) {
		this.tamperTimeStamp = tamperTimeStamp;
	}
	
}
