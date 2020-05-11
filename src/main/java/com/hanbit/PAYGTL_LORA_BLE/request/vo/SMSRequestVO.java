/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author VmL
 *
 */
public class SMSRequestVO {
	
	private String toMobileNumber;
	private String message;
	
	public String getToMobileNumber() {
		return toMobileNumber;
	}
	public void setToMobileNumber(String toMobileNumber) {
		this.toMobileNumber = toMobileNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
