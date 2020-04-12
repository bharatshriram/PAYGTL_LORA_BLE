/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class FixedChargesResponseVO {

	private String communityName;
    private String blockName;
    private String houseNo;
    private String name;
    private String meterNo;
    private String unitsConsumed;
    private String fixedCharges;
    private String billingMonth;
    private String paymentStatus;
    
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMeterNo() {
		return meterNo;
	}
	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}
	public String getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(String unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
	public String getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(String fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public String getBillingMonth() {
		return billingMonth;
	}
	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
    
}
