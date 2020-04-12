/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class AssignMeterResponseVO {

	private String communityName;
	private String blockName;
	private String houseNo;
	private int meterID;
	private String tariffID;
	
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public int getMeterID() {
		return meterID;
	}
	public void setMeterID(int meterID) {
		this.meterID = meterID;
	}
	public String getTariffID() {
		return tariffID;
	}
	public void setTariffID(String tariffID) {
		this.tariffID = tariffID;
	}
}
