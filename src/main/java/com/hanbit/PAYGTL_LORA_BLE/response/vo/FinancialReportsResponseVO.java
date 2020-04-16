/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class FinancialReportsResponseVO {
	
	private String communityName;
	private String blockName;
	private String houseNumber;
	private String meterID;
	private int totalAmount;
	private int totalForSelectedPeriod;
	private List<FinancialReportsResponseVO> data;

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<FinancialReportsResponseVO> getData() {
		return data;
	}

	public void setData(List<FinancialReportsResponseVO> data) {
		this.data = data;
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

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getMeterID() {
		return meterID;
	}

	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}

	public int getTotalForSelectedPeriod() {
		return totalForSelectedPeriod;
	}

	public void setTotalForSelectedPeriod(int totalForSelectedPeriod) {
		this.totalForSelectedPeriod = totalForSelectedPeriod;
	}
	
}
