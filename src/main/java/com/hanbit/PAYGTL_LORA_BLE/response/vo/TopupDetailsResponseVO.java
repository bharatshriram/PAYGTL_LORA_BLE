/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class TopupDetailsResponseVO {
	
	private int meterid;
	private double currentBalance;
	private String dateTime;
	private float unitRate;
	private double emergencyCredit;
	private float alarmCredit;
	
	public int getMeterid() {
		return meterid;
	}
	public void setMeterid(int meterid) {
		this.meterid = meterid;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public float getUnitRate() {
		return unitRate;
	}
	public void setUnitRate(float unitRate) {
		this.unitRate = unitRate;
	}
	public double getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(double emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public float getAlarmCredit() {
		return alarmCredit;
	}
	public void setAlarmCredit(float alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	
	
}
