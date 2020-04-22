/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class TopUpSummaryResponseVO {

	private String houseNumber;
	private String CRNNumber;
	private String meterID;
	private String firstName;
	private String lastName;
	private int transactionID;
	private int rechargeAmount;
	private String status;
	private String modeOfPayment;
	private String transactedByUserName;
	private String transactedByRoleDescription;
	private String dateTime;
	
	private List<TopUpSummaryResponseVO> data;

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

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(int rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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

	public List<TopUpSummaryResponseVO> getData() {
		return data;
	}

	public void setData(List<TopUpSummaryResponseVO> data) {
		this.data = data;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getCRNNumber() {
		return CRNNumber;
	}

	public void setCRNNumber(String cRNNumber) {
		CRNNumber = cRNNumber;
	}
	
}
