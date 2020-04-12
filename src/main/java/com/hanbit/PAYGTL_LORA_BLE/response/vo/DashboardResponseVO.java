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

	private String block;
	private String HouseNumber;
	private String reading;
	private String balance;
	private String emergencyCredit;
	private String battery;
	private String valve;
	private float gasTariff;
	private String tamper;
	private String timeStamp;
	private List<DashboardResponseVO> data;
	
	public float getGasTariff() {
		return gasTariff;
	}
	public void setGasTariff(float gasTariff) {
		this.gasTariff = gasTariff;
	}
	public String getTamper() {
		return tamper;
	}
	public void setTamper(String tamper) {
		this.tamper = tamper;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getReading() {
		return reading;
	}
	public void setReading(String reading) {
		this.reading = reading;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(String emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public String getValve() {
		return valve;
	}
	public void setValve(String valve) {
		this.valve = valve;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public List<DashboardResponseVO> getData() {
		return data;
	}
	public void setData(List<DashboardResponseVO> data) {
		this.data = data;
	}
	public String getHouseNumber() {
		return HouseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}
	
}
