/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class AddMeterResponseVO {
	
	private int meterID;
	private String meterSerialNo;
	private String defaultReading;
	
	private int id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public int getMeterID() {
		return meterID;
	}
	public void setMeterID(int meterID) {
		this.meterID = meterID;
	}
	public String getMeterSerialNo() {
		return meterSerialNo;
	}
	public void setMeterSerialNo(String meterSerialNo) {
		this.meterSerialNo = meterSerialNo;
	}
	public String getDefaultReading() {
		return defaultReading;
	}
	public void setDefaultReading(String defaultReading) {
		this.defaultReading = defaultReading;
	}
}
