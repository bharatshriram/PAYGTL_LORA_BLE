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
    private payload_dl payload_dl;

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

	public payload_dl getPayload_dl() {
		return payload_dl;
	}

	public void setPayload_dl(payload_dl payload_dl) {
		this.payload_dl = payload_dl;
	}

}
