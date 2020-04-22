/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class TataResponseVO {
	
	private String userid;
    private payloads_ul payloads_ul;
    private payloads_dl payloads_dl;
    
    private long id;
    private String data;
    private int fcnt;
    private int port;
    private int transmissionStatus;
    private String session_id;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

	public payloads_ul getPayloads_ul() {
		return payloads_ul;
	}

	public void setPayloads_ul(payloads_ul payloads_ul) {
		this.payloads_ul = payloads_ul;
	}

	public payloads_dl getPayloads_dl() {
		return payloads_dl;
	}

	public void setPayloads_dl(payloads_dl payloads_dl) {
		this.payloads_dl = payloads_dl;
	}
	
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getFcnt() {
		return fcnt;
	}

	public void setFcnt(int fcnt) {
		this.fcnt = fcnt;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTransmissionStatus() {
		return transmissionStatus;
	}

	public void setTransmissionStatus(int transmissionStatus) {
		this.transmissionStatus = transmissionStatus;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
}
