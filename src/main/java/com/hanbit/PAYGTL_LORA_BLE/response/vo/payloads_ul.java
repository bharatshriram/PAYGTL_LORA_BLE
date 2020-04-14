
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

public class payloads_ul {

	private long id;
    private int snr;
    private int fcnt;
    private int freq;
    private Boolean live;
    private int port;
    private int rssi;
    private Boolean early;
    private String deveui;
    private String cr_used;
    private long devaddr;
    private String dr_used;
    private Integer sf_used;
    private Boolean confirmed;
    private String dataFrame;
    private Boolean decrypted;
    private String timestamp;
    private String data_format;
    private Float time_0n_air_ms;
    private int device_redundancy;
	
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSnr() {
		return snr;
	}
	public void setSnr(int snr) {
		this.snr = snr;
	}
	public int getFcnt() {
		return fcnt;
	}
	public void setFcnt(int fcnt) {
		this.fcnt = fcnt;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public Boolean getLive() {
		return live;
	}
	public void setLive(Boolean live) {
		this.live = live;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public Boolean getEarly() {
		return early;
	}
	public void setEarly(Boolean early) {
		this.early = early;
	}
	public String getDeveui() {
		return deveui;
	}
	public void setDeveui(String deveui) {
		this.deveui = deveui;
	}
	public String getCr_used() {
		return cr_used;
	}
	public void setCr_used(String cr_used) {
		this.cr_used = cr_used;
	}
	public long getDevaddr() {
		return devaddr;
	}
	public void setDevaddr(long devaddr) {
		this.devaddr = devaddr;
	}
	public String getDr_used() {
		return dr_used;
	}
	public void setDr_used(String dr_used) {
		this.dr_used = dr_used;
	}
	public Integer getSf_used() {
		return sf_used;
	}
	public void setSf_used(Integer sf_used) {
		this.sf_used = sf_used;
	}
	public Boolean getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
	public String getDataFrame() {
		return dataFrame;
	}
	public void setDataFrame(String dataFrame) {
		this.dataFrame = dataFrame;
	}
	public Boolean getDecrypted() {
		return decrypted;
	}
	public void setDecrypted(Boolean decrypted) {
		this.decrypted = decrypted;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getData_format() {
		return data_format;
	}
	public void setData_format(String data_format) {
		this.data_format = data_format;
	}
	public Float getTime_0n_air_ms() {
		return time_0n_air_ms;
	}
	public void setTime_0n_air_ms(Float time_0n_air_ms) {
		this.time_0n_air_ms = time_0n_air_ms;
	}
	public int getDevice_redundancy() {
		return device_redundancy;
	}
	public void setDevice_redundancy(int device_redundancy) {
		this.device_redundancy = device_redundancy;
	}


}
