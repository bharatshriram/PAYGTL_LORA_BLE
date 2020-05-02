/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class FilterVO {
	
	private String dateFrom;
	private String dateTo;
	private int readingFrom;
	private int readingTo;
	private float batteryVoltageFrom;
	private float batteryVoltageTo;
	private int tamperType;

	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public int getReadingFrom() {
		return readingFrom;
	}
	public void setReadingFrom(int readingFrom) {
		this.readingFrom = readingFrom;
	}
	public int getReadingTo() {
		return readingTo;
	}
	public void setReadingTo(int readingTo) {
		this.readingTo = readingTo;
	}
	public float getBatteryVoltageFrom() {
		return batteryVoltageFrom;
	}
	public void setBatteryVoltageFrom(float batteryVoltageFrom) {
		this.batteryVoltageFrom = batteryVoltageFrom;
	}
	public float getBatteryVoltageTo() {
		return batteryVoltageTo;
	}
	public void setBatteryVoltageTo(float batteryVoltageTo) {
		this.batteryVoltageTo = batteryVoltageTo;
	}
	public int getTamperType() {
		return tamperType;
	}
	public void setTamperType(int tamperType) {
		this.tamperType = tamperType;
	}

}
