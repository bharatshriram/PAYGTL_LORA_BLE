/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class UserConsumptionReportsResponseVO {
	
	private String meterID;
	private float reading;
	private float balance;
	private float battery;
	private float tariff;
	private float alarmCredit;
	private float emergencyCredit;
	private String dateTime;
	
	private List<UserConsumptionReportsResponseVO> data;
	
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
	public float getBattery() {
		return battery;
	}
	public void setBattery(float battery) {
		this.battery = battery;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public float getAlarmCredit() {
		return alarmCredit;
	}
	public void setAlarmCredit(float alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	public float getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(float emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public List<UserConsumptionReportsResponseVO> getData() {
		return data;
	}
	public void setData(List<UserConsumptionReportsResponseVO> data) {
		this.data = data;
	}
	
}
