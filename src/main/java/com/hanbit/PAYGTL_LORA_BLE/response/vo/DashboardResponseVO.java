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
	private float reading;
	private float balance;
	private float emergencyCredit;
	private String battery;
	private int valve;
	private float tariff;
	private int tamper;
	private String timeStamp;
	private String dateColor;
	private String batteryColor;
	private List<DashboardResponseVO> data;
	
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getHouseNumber() {
		return HouseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
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
	public int getValve() {
		return valve;
	}
	public void setValve(int valve) {
		this.valve = valve;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public int getTamper() {
		return tamper;
	}
	public void setTamper(int tamper) {
		this.tamper = tamper;
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

}
