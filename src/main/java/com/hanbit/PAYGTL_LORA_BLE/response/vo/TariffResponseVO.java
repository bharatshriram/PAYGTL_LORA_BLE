/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class TariffResponseVO {
	
	private int tariffID;
	private String tariff;
	private String emergencyCredit;
	private String alarmCredit;
	private String fixedCharges;
	private String RegisteredDate;
	private List<TariffResponseVO> data;
	
	public int getTariffID() {
		return tariffID;
	}
	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}
	public String getTariff() {
		return tariff;
	}
	public void setTariff(String tariff) {
		this.tariff = tariff;
	}
	public String getEmergencyCredit() {
		return emergencyCredit;
	}
	public void setEmergencyCredit(String emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}
	public String getAlarmCredit() {
		return alarmCredit;
	}
	public void setAlarmCredit(String alarmCredit) {
		this.alarmCredit = alarmCredit;
	}
	public String getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(String fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public String getRegisteredDate() {
		return RegisteredDate;
	}
	public void setRegisteredDate(String RegisteredDate) {
		this.RegisteredDate = RegisteredDate;
	}
	public List<TariffResponseVO> getData() {
		return data;
	}
	public void setData(List<TariffResponseVO> data) {
		this.data = data;
	}
	
}
