/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 * 
 */
public class DashboardResponseVO {

	private String communityName;
	private String blockName;
	private String HouseNumber;
	private String firstName;
	private String lastName;
	private String meterID;
	private float reading;
	private float balance;
	private float emergencyCredit;
	private float alarmCredit;
	private String battery;
	private String valveStatus;
	private float tariff;
	private String tariffName;
	private String tamperStatus;
	private String timeStamp;
	private String dateColor;
	private String batteryColor;
	
	private List<DashboardResponseVO> data;

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
		return HouseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMeterID() {
		return meterID;
	}

	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}

	public float getReading() {
		return reading;
	}

	public void setReading(float reading) {
		this.reading = reading;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getEmergencyCredit() {
		return emergencyCredit;
	}

	public void setEmergencyCredit(float emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getValveStatus() {
		return valveStatus;
	}

	public void setValveStatus(String valveStatus) {
		this.valveStatus = valveStatus;
	}

	public float getTariff() {
		return tariff;
	}

	public void setTariff(float tariff) {
		this.tariff = tariff;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public String getTamperStatus() {
		return tamperStatus;
	}

	public void setTamperStatus(String tamperStatus) {
		this.tamperStatus = tamperStatus;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDateColor() {
		return dateColor;
	}

	public void setDateColor(String dateColor) {
		this.dateColor = dateColor;
	}

	public String getBatteryColor() {
		return batteryColor;
	}

	public void setBatteryColor(String batteryColor) {
		this.batteryColor = batteryColor;
	}

	public List<DashboardResponseVO> getData() {
		return data;
	}

	public void setData(List<DashboardResponseVO> data) {
		this.data = data;
	}

	public float getAlarmCredit() {
		return alarmCredit;
	}

	public void setAlarmCredit(float alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	
}
