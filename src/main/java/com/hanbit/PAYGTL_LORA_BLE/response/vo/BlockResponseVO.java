/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class BlockResponseVO {
	
	private String communityName;	
	private String blockName;
	private String Location;
	private String email;
	private String mobile;
	private List<BlockResponseVO> data;
	private int blockID;

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

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public int getBlockID() {
		return blockID;
	}

	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}

	public List<BlockResponseVO> getData() {
		return data;
	}

	public void setData(List<BlockResponseVO> data) {
		this.data = data;
	}
	

}
