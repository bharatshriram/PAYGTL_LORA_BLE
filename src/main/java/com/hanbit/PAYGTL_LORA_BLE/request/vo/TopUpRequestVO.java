/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class TopUpRequestVO {
	
	private String communityName;
	private String blockName;
	private String houseNo;
	private int meterID;
	private float currentBalance;
	private String dateTime;
	private float tariff;
	private float emergencyCredit;
	private float alarmCredit;
	private float rechargeAmount;
	private String setTariff;
	private String userID;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public int getMeterID() {
		return meterID;
	}
	public void setMeterID(int meterID) {
		this.meterID = meterID;
	}
	public float getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
	public float getAlarmCredit() {
		return alarmCredit;
	}
	public void setAlarmCredit(float alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	public float getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(float rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public String getSetTariff() {
		return setTariff;
	}
	public void setSetTariff(String setTariff) {
		this.setTariff = setTariff;
	}	

}
