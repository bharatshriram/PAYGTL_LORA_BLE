package com.hanbit.PAYGTL_LORA_BLE.request.vo;
/**
 * @author K VimaL Kumar
 * 
 */
public class RestCallVO {
	
	private String urlExtension;
	private String data;
	private String meterID;
	private String tataTransactionID;
		
	public String getUrlExtension() {
		return urlExtension;
	}
	public void setUrlExtension(String urlExtension) {
		this.urlExtension = urlExtension;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMeterID() {
		return meterID;
	}
	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}
	public String getTataTransactionID() {
		return tataTransactionID;
	}
	public void setTataTransactionID(String tataTransactionID) {
		this.tataTransactionID = tataTransactionID;
	}

}
