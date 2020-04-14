/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class TopUpRequestVO {
	
	private int CommunityID;
	private int blockID;
	private int customerID;
	private String houseNumber;
	private String meterID;
	private float currentBalance;
	private int tariffID;
	private float amount;
	private String cardNumber;
	private String cardType;
	private int transactedByID;
	private int transactedByRoleID;
	
	public int getCommunityID() {
		return CommunityID;
	}
	public void setCommunityID(int communityID) {
		CommunityID = communityID;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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
	public float getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getTransactedByID() {
		return transactedByID;
	}
	public void setTransactedByID(int transactedByID) {
		this.transactedByID = transactedByID;
	}
	public int getTransactedByRoleID() {
		return transactedByRoleID;
	}
	public void setTransactedByRoleID(int transactedByRoleID) {
		this.transactedByRoleID = transactedByRoleID;
	}
	public int getTariffID() {
		return tariffID;
	}
	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}
	
}
