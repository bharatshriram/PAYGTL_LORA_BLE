/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author VmL
 *
 */
public class ValveReportsResponseVO {
	
	private int meterID;
	private String openTime;
	private String closeTime;
	private String remark;
	private String dateTime;
	
	public int getMeterID() {
		return meterID;
	}
	public void setMeterID(int meterID) {
		this.meterID = meterID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
