/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author k VimaL Kumar
 *
 */
public class CustomerRequestVO {
	
	private int customerID;
	private int communityID;
	private int blockID;
	private String houseNumber;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String meterID;
	private String meterSerialNumber;
	private String defaultReading;
	private String CRNNumber;
	private int createdByID;
	private int createdByRoleID;
	private int loggedInRoleID;
	private String loggedInUserID;
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getCommunityID() {
		return communityID;
	}
	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMeterID() {
		return meterID;
	}
	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}
	public String getMeterSerialNumber() {
		return meterSerialNumber;
	}
	public void setMeterSerialNumber(String meterSerialNumber) {
		this.meterSerialNumber = meterSerialNumber;
	}
	public String getDefaultReading() {
		return defaultReading;
	}
	public void setDefaultReading(String defaultReading) {
		this.defaultReading = defaultReading;
	}
	public int getCreatedByID() {
		return createdByID;
	}
	public void setCreatedByID(int createdByID) {
		this.createdByID = createdByID;
	}
	public int getCreatedByRoleID() {
		return createdByRoleID;
	}
	public void setCreatedByRoleID(int createdByRoleID) {
		this.createdByRoleID = createdByRoleID;
	}
	public String getCRNNumber() {
		return CRNNumber;
	}
	public void setCRNNumber(String cRNNumber) {
		CRNNumber = cRNNumber;
	}
	public int getLoggedInRoleID() {
		return loggedInRoleID;
	}
	public void setLoggedInRoleID(int loggedInRoleID) {
		this.loggedInRoleID = loggedInRoleID;
	}
	public String getLoggedInUserID() {
		return loggedInUserID;
	}
	public void setLoggedInUserID(String loggedInUserID) {
		this.loggedInUserID = loggedInUserID;
	}
	
}
