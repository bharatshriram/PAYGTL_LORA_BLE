/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class ConfigurationRequestVO {
	
	private String meterID;
	private String blockName;
	private String houseNo;
	private String commandType;
	
	private String customerID;
	private String commandTransID;
	private String commandID;

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
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getMeterID() {
		return meterID;
	}
	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}
	public String getCommandType() {
		return commandType;
	}
	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	public String getCommandTransID() {
		return commandTransID;
	}
	public void setCommandTransID(String commandTransID) {
		this.commandTransID = commandTransID;
	}
	public String getCommandID() {
		return commandID;
	}
	public void setCommandID(String commandID) {
		this.commandID = commandID;
	}
	
}
