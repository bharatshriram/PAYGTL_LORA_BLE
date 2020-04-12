/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class UserManagementRequestVO {
	
	private int communityID;
	private int blockID;
	private String userID;
	private String userName;
	private String userPassword;
	private String confirmPassword;
	private int roleID;
	private int loggedInRoleID;
	private String loggedInUserID;
	private String oldPassword;
	private String newPassword;
	
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
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
}
