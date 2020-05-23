/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

/**
 * @author VmL
 *
 */
public class HomeResponseVO {
	
	private int active;
	private int inActive;
	private int live;
	private int nonLive;
	private int emergency;
	private int lowBattery;
	private int consumption;
	private int topup;
	private int amr;
	
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getInActive() {
		return inActive;
	}
	public void setInActive(int inActive) {
		this.inActive = inActive;
	}
	public int getLive() {
		return live;
	}
	public void setLive(int live) {
		this.live = live;
	}
	public int getNonLive() {
		return nonLive;
	}
	public void setNonLive(int nonLive) {
		this.nonLive = nonLive;
	}
	public int getEmergency() {
		return emergency;
	}
	public void setEmergency(int emergency) {
		this.emergency = emergency;
	}
	public int getLowBattery() {
		return lowBattery;
	}
	public void setLowBattery(int lowBattery) {
		this.lowBattery = lowBattery;
	}
	public int getConsumption() {
		return consumption;
	}
	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
	public int getTopup() {
		return topup;
	}
	public void setTopup(int topup) {
		this.topup = topup;
	}
	public int getAmr() {
		return amr;
	}
	public void setAmr(int amr) {
		this.amr = amr;
	}
	
}
