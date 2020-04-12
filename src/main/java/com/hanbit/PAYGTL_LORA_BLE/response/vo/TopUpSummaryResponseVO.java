/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class TopUpSummaryResponseVO {

	private String houseNo;
	private String meterSerialNo;
	private String name;
	private int billNo;
	private int rechargeAmount;
	private String status;
	private String dateTime;
	
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getMeterSerialNo() {
		return meterSerialNo;
	}
	public void setMeterSerialNo(String meterSerialNo) {
		this.meterSerialNo = meterSerialNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
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
}
