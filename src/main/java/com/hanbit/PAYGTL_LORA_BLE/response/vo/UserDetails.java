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
	private String houseNo;
	private int communityID;
	private String userName;
	private int ID;
	private int customerID;
	private String communityName;
	private int pendingCommandID;
	private int pendingTransactionID;
	
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public int getroleID() {
		return roleID;
	}
	public void setroleID(int roleID) {
		this.roleID = roleID;
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
	public int getPendingCommandID() {
		return pendingCommandID;
	}
	public void setPendingCommandID(int pendingCommandID) {
		this.pendingCommandID = pendingCommandID;
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
	
}
