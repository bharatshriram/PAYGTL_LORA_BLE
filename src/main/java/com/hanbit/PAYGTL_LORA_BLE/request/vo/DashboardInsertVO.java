/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class DashboardInsertVO {
	
	private String meterID;
	private float reading;
	private int communityID;
	private int blockID;
	private int cutomerID;
	private float balance;
	private float batteryVoltage;
	private float tariff;
	private float emergencyCredit;
	private int meterType;
	private int valveStatus;
	private int tamper;
	private String ioTTimeStamp;
	private int lowBatteryVoltage;
	
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
	public int getCommunityID() {
		return communityID;
	}
	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public int getCutomerID() {
		return cutomerID;
	}
	public void setCutomerID(int cutomerID) {
		this.cutomerID = cutomerID;
	}
	public float getBatteryVoltage() {
		return batteryVoltage;
	}
	public void setBatteryVoltage(float batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public float getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(float emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public int getMeterType() {
		return meterType;
	}
	public void setMeterType(int meterType) {
		this.meterType = meterType;
	}
	public int getValveStatus() {
		return valveStatus;
	}
	public void setValveStatus(int valveStatus) {
		this.valveStatus = valveStatus;
	}
	public int getTamper() {
		return tamper;
	}
	public void setTamper(int tamper) {
		this.tamper = tamper;
	}
	public String getIoTTimeStamp() {
		return ioTTimeStamp;
	}
	public void setIoTTimeStamp(String ioTTimeStamp) {
		this.ioTTimeStamp = ioTTimeStamp;
	}
	public int getLowBatteryVoltage() {
		return lowBatteryVoltage;
	}
	public void setLowBatteryVoltage(int lowBatteryVoltage) {
		this.lowBatteryVoltage = lowBatteryVoltage;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
}
