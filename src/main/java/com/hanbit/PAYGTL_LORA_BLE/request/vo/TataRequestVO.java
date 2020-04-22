/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class TataRequestVO {
	
	private boolean confirmed;
	private String cr_used;
	private String dataFrame;
	private String data_format;
	private boolean decrypted;
	private long devaddr;
	private int device_redundancy;
	private String dr_used;
	private int fcnt;
	private long freq;
	private long id;
	private int port;
	private int rssi;
	private String session_id;
	private int sf_used;
	private int snr;
	private int time_on_air_ms;
	private String timestamp;
	
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public String getCr_used() {
		return cr_used;
	}
	public void setCr_used(String cr_used) {
		this.cr_used = cr_used;
	}
	public String getDataFrame() {
		return dataFrame;
	}
	public void setDataFrame(String dataFrame) {
		this.dataFrame = dataFrame;
	}
	public String getData_format() {
		return data_format;
	}
	public void setData_format(String data_format) {
		this.data_format = data_format;
	}
	public boolean isDecrypted() {
		return decrypted;
	}
	public void setDecrypted(boolean decrypted) {
		this.decrypted = decrypted;
	}
	public long getDevaddr() {
		return devaddr;
	}
	public void setDevaddr(long devaddr) {
		this.devaddr = devaddr;
	}
	public int getDevice_redundancy() {
		return device_redundancy;
	}
	public void setDevice_redundancy(int device_redundancy) {
		this.device_redundancy = device_redundancy;
	}
	public String getDr_used() {
		return dr_used;
	}
	public void setDr_used(String dr_used) {
		this.dr_used = dr_used;
	}
	public int getFcnt() {
		return fcnt;
	}
	public void setFcnt(int fcnt) {
		this.fcnt = fcnt;
	}
	public long getFreq() {
		return freq;
	}
	public void setFreq(long freq) {
		this.freq = freq;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public int getSf_used() {
		return sf_used;
	}
	public void setSf_used(int sf_used) {
		this.sf_used = sf_used;
	}
	public int getSnr() {
		return snr;
	}
	public void setSnr(int snr) {
		this.snr = snr;
	}
	public int getTime_on_air_ms() {
		return time_on_air_ms;
	}
	public void setTime_on_air_ms(int time_on_air_ms) {
		this.time_on_air_ms = time_on_air_ms;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
