/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class StatusResponseVO {
	
	private int transactionID;
	private String communityName;
	private String blockName;
	private String houseNumber;
	private String firstName;
	private String lastName;
	private String meterID;
	private String amount;
	private String emergencyCredit;
	private String alarmCredit;
	private String transactionDate;
	private String acknowledgeDate;
	private String Status;
	private String modeOfPayment;
	private String paymentStatus;
	private String transactedByUserName;
	private String transactedByRoleDescription;

	private List<StatusResponseVO> data;
	
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(String emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public String getAlarmCredit() {
		return alarmCredit;
	}
	public void setAlarmCredit(String alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getAcknowledgeDate() {
		return acknowledgeDate;
	}
	public void setAcknowledgeDate(String acknowledgeDate) {
		this.acknowledgeDate = acknowledgeDate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getTransactedByUserName() {
		return transactedByUserName;
	}
	public void setTransactedByUserName(String transactedByUserName) {
		this.transactedByUserName = transactedByUserName;
	}
	public String getTransactedByRoleDescription() {
		return transactedByRoleDescription;
	}
	public void setTransactedByRoleDescription(String transactedByRoleDescription) {
		this.transactedByRoleDescription = transactedByRoleDescription;
	}
	public List<StatusResponseVO> getData() {
		return data;
	}
	public void setData(List<StatusResponseVO> data) {
		this.data = data;
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
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	
}
