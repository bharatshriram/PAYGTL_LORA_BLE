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

}
