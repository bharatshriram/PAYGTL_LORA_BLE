/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.HashMap;

/**
 * @author VmL
 *
 */
public class ResponseVO {
	
	
	private HashMap<Integer, String> dropDownCommunities;
	private HashMap<Integer, String> dropDownBlocks;
	private HashMap<String, String> dropDownHouses;
	private HashMap<Integer, String> dropDownTariffs;
	private TopupDetailsResponseVO topupdetails;
	private String result;
	private String Message;
	private String fileName;
	private String location;
	private UserDetails userDetails;
	private CheckoutDetails checkoutDetails;
	private String paymentMode;
	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String Message) {
		this.Message = Message;
	}
	public TopupDetailsResponseVO getTopupdetails() {
		return topupdetails;
	}
	public void setTopupdetails(TopupDetailsResponseVO topupdetails) {
		this.topupdetails = topupdetails;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public HashMap<Integer, String> getDropDownCommunities() {
		return dropDownCommunities;
	}
	public void setDropDownCommunities(HashMap<Integer, String> dropDownCommunities) {
		this.dropDownCommunities = dropDownCommunities;
	}
	public HashMap<Integer, String> getDropDownBlocks() {
		return dropDownBlocks;
	}
	public void setDropDownBlocks(HashMap<Integer, String> dropDownBlocks) {
		this.dropDownBlocks = dropDownBlocks;
	}
	public HashMap<String, String> getDropDownHouses() {
		return dropDownHouses;
	}
	public void setDropDownHouses(HashMap<String, String> dropDownHouses) {
		this.dropDownHouses = dropDownHouses;
	}
	public HashMap<Integer, String> getDropDownTariffs() {
		return dropDownTariffs;
	}
	public void setDropDownTariffs(HashMap<Integer, String> dropDownTariffs) {
		this.dropDownTariffs = dropDownTariffs;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public CheckoutDetails getCheckoutDetails() {
		return checkoutDetails;
	}
	public void setCheckoutDetails(CheckoutDetails checkoutDetails) {
		this.checkoutDetails = checkoutDetails;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
}
