/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K Vimal Kumar
 *
 */
public class FeedbackResponseVO {
	
	private int feedbackID;
	private String feedback;
	private String description;
	private String CRNNumber;
	private String meterID;
	private String houseNumber;
	private String name;
	private String date;
	
	private List<FeedbackResponseVO> data;

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCRNNumber() {
		return CRNNumber;
	}

	public void setCRNNumber(String cRNNumber) {
		CRNNumber = cRNNumber;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<FeedbackResponseVO> getData() {
		return data;
	}

	public void setData(List<FeedbackResponseVO> data) {
		this.data = data;
	}

	public String getMeterID() {
		return meterID;
	}

	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}

}
