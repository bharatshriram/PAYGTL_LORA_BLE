/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class TopUpRequestVO {
	
	// add parameters based on payment gateway and add columns in db also
	private String source;
	private String CRNNumber;
	private String meterID;
	private int amount;
	private String cardNumber;
	private String cardType;
	private String modeOfPayment;
	private int status;
	private int transactedByID;
	private int transactedByRoleID;
	private long transactionIDForTata;
	private int fixedCharges;
	private int reconnectionCharges;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
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
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public long getTransactionIDForTata() {
		return transactionIDForTata;
	}
	public void setTransactionIDForTata(long transactionIDForTata) {
		this.transactionIDForTata = transactionIDForTata;
	}
	public String getMeterID() {
		return meterID;
	}
	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCRNNumber() {
		return CRNNumber;
	}
	public void setCRNNumber(String cRNNumber) {
		CRNNumber = cRNNumber;
	}
	public int getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(int fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public int getReconnectionCharges() {
		return reconnectionCharges;
	}
	public void setReconnectionCharges(int reconnectionCharges) {
		this.reconnectionCharges = reconnectionCharges;
	}
	
}
