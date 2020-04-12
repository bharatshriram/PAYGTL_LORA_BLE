/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class BlockRequestVO {
	
	private String blockName;
	private String location;
	private String mobileNumber;
	private String email;
	private int communityID;
	private int createdByID;
	private int createdByRoleID;
	private int blockID;
	private String loggedInUserID;
	
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getLoggedInUserID() {
		return loggedInUserID;
	}
	public void setLoggedInUserID(String loggedInUserID) {
		this.loggedInUserID = loggedInUserID;
	}
	
}
