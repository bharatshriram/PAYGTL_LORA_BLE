/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class UserManagementResponseVO {
	
	private int ID;
	private String userID;
	private String userName;
	private String role;
	private String createdByUserName;
	private String createdByRoleDescription;
	private String communityName;
	private String blockName;
	private List<UserManagementResponseVO> data;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<UserManagementResponseVO> getData() {
		return data;
	}
	public void setData(List<UserManagementResponseVO> data) {
		this.data = data;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	public String getCreatedByRoleDescription() {
		return createdByRoleDescription;
	}
	public void setCreatedByRoleDescription(String createdByRoleDescription) {
		this.createdByRoleDescription = createdByRoleDescription;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	
}
