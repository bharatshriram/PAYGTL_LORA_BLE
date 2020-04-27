/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class UserDetails {
	
	private int roleID;
	private int blockID;
	private String email;
	private String mobileNumber;
	private String houseNo;
	private int communityID;
	private String userName;
	private int ID;
	private int customerID;
	private String CRNNumber;
	private String communityName;
	private int pendingCommandType;
	private int pendingTransactionID;
	
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public int getCommunityID() {
		return communityID;
	}
	public void setCommunity(int communityID) {
		this.communityID = communityID;
	}
	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getPendingCommandType() {
		return pendingCommandType;
	}
	public void setPendingCommandType(int pendingCommandType) {
		this.pendingCommandType = pendingCommandType;
	}
	public int getPendingTransactionID() {
		return pendingTransactionID;
	}
	public void setPendingTransactionID(int pendingTransactionID) {
		this.pendingTransactionID = pendingTransactionID;
	}
	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	public String getCRNNumber() {
		return CRNNumber;
	}
	public void setCRNNumber(String cRNNumber) {
		CRNNumber = cRNNumber;
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
}
