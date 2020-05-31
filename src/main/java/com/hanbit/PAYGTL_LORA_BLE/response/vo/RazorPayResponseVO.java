/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K Vimal Kumar
 *
 */
public class RazorPayResponseVO {
	
	public String id;
	public String entity;
	public int amount;
	public int amountPaid;
	public int amountDue;
	public String currency;
	public String receipt;
	public String status;
	public int attempts;
	public int payment_capture;
	public List<Object> notes;
	public long createdAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public int getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(int amountDue) {
		this.amountDue = amountDue;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public int getPayment_capture() {
		return payment_capture;
	}
	public void setPayment_capture(int payment_capture) {
		this.payment_capture = payment_capture;
	}
	public List<Object> getNotes() {
		return notes;
	}
	public void setNotes(List<Object> notes) {
		this.notes = notes;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

}
