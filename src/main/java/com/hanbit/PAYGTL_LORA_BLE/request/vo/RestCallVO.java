package com.hanbit.PAYGTL_LORA_BLE.request.vo;
/**
 * @author K VimaL Kumar
 * 
 */
public class RestCallVO {
	
	private String meterID;
	private String tataTransactionID;
	private String dataFrame;
	private String urlExtension;
		
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
	public String getDataFrame() {
		return dataFrame;
	}
	public void setDataFrame(String dataFrame) {
		this.dataFrame = dataFrame;
	}
	public String getUrlExtension() {
		return urlExtension;
	}
	public void setUrlExtension(String urlExtension) {
		this.urlExtension = urlExtension;
	}

}
