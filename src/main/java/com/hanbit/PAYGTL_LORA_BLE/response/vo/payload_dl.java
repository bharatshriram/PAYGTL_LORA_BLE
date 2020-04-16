package com.hanbit.PAYGTL_LORA_BLE.response.vo;

public class payload_dl {
	
    private int port;
    private String deveui;
    private String tag;
    private String on_busy;
    private Boolean confirmed;
    private String data;
    private int transmissionStatus;
    
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDeveui() {
		return deveui;
	}
	public void setDeveui(String deveui) {
		this.deveui = deveui;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getOn_busy() {
		return on_busy;
	}
	public void setOn_busy(String on_busy) {
		this.on_busy = on_busy;
	}
	public Boolean getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getTransmissionStatus() {
		return transmissionStatus;
	}
	public void setTransmissionStatus(int transmissionStatus) {
		this.transmissionStatus = transmissionStatus;
	}
    
}
