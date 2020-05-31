/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.request.vo;

/**
 * @author VmL
 *
 */
public class CheckOutRequestVO {
	
	private String RazorPayOrderID;
	private String RazorPayPaymentID;
	private String RazorPaySignature;
	private long transactionID;
	public RazorPayError error;
	
	public String getRazorPayOrderID() {
		return RazorPayOrderID;
	}
	public void setRazorPayOrderID(String razorPayOrderID) {
		RazorPayOrderID = razorPayOrderID;
	}
	public String getRazorPayPaymentID() {
		return RazorPayPaymentID;
	}
	public void setRazorPayPaymentID(String razorPayPaymentID) {
		RazorPayPaymentID = razorPayPaymentID;
	}
	public String getRazorPaySignature() {
		return RazorPaySignature;
	}
	public void setRazorPaySignature(String razorPaySignature) {
		RazorPaySignature = razorPaySignature;
	}
	public long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	public RazorPayError getError() {
		return error;
	}
	public void setError(RazorPayError error) {
		this.error = error;
	}
}
