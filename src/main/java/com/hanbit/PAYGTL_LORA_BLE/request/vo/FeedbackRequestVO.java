/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class FeedbackRequestVO {

	private String CRNNumber;
	private String feedback;
	private String description;
	
	public String getCRNNumber() {
		return CRNNumber;
	}
	public void setCRNNumber(String cRNNumber) {
		CRNNumber = cRNNumber;
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

}
