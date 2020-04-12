/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class AlertRequestVO {
	
	private int alertID;
	private int noAMRInterval;
	private float lowBatteryVoltage;
	private int timeOut;
	private String registeredDate;
	private int communityID;
	
	public int getCommunityID() {
		return communityID;
	}
	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	public int getAlertID() {
		return alertID;
	}
	public void setAlertID(int alertID) {
		this.alertID = alertID;
	}
	public int getNoAMRInterval() {
		return noAMRInterval;
	}
	public void setNoAMRInterval(int noAMRInterval) {
		this.noAMRInterval = noAMRInterval;
	}
	public float getLowBatteryVoltage() {
		return lowBatteryVoltage;
	}
	public void setLowBatteryVoltage(float lowBatteryVoltage) {
		this.lowBatteryVoltage = lowBatteryVoltage;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	
	
}
