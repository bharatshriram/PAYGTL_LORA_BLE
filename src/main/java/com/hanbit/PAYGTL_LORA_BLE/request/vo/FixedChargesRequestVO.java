/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author VmL
 *
 */
public class FixedChargesRequestVO {
	
	 private String houseNo;
	    private String firstName;
	    private String lastName;
	    private String meterNo;
	    private String minimumReading;
	    private String maximumReading;
	    private String unitsConsumed;
		private String minimumDate;
		private String maximumDate;
	    private String fixedCharges;
	    private String billingDate;
		private String billingMonth;
	    private String paymentStatus;
	    private String modifedDate;    
	    private String communityName;
	    private String blockName;
	    private int customerID;

	    public String getMinimumDate() {
			return minimumDate;
		}
		public void setMinimumDate(String minimumDate) {
			this.minimumDate = minimumDate;
		}
		public String getMaximumDate() {
			return maximumDate;
		}
		public void setMaximumDate(String maximumDate) {
			this.maximumDate = maximumDate;
		}
		public String getCommunityName() {
			return communityName;
		}
		public void setCommunityName(String communityName) {
			this.communityName = communityName;
		}
		public int getCustomerID() {
			return customerID;
		}
		public void setCustomerID(int customerID) {
			this.customerID = customerID;
		}    
	    public String getHouseNo() {
			return houseNo;
		}
		public void setHouseNo(String houseNo) {
			this.houseNo = houseNo;
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
		public String getMeterNo() {
			return meterNo;
		}
		public void setMeterNo(String meterNo) {
			this.meterNo = meterNo;
		}
		public String getMinimumReading() {
			return minimumReading;
		}
		public void setMinimumReading(String minimumReading) {
			this.minimumReading = minimumReading;
		}
		public String getMaximumReading() {
			return maximumReading;
		}
		public void setMaximumReading(String maximumReading) {
			this.maximumReading = maximumReading;
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
		public String getBillingDate() {
			return billingDate;
		}
		public void setBillingDate(String billingDate) {
			this.billingDate = billingDate;
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
		public String getModifedDate() {
			return modifedDate;
		}
		public void setModifedDate(String modifedDate) {
			this.modifedDate = modifedDate;
		}
		
		public String getBlockName() {
			return blockName;
		}
		public void setBlockName(String blockName) {
			this.blockName = blockName;
		}

}
