package com.hanbit.PAYGTL_LORA_BLE.request.vo;


/**
 * @author K VimaL Kumar
 * 
 */

public class MailRequestVO {

	private String toEmail;
	private String userID;
	private String userPassword;
	
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
