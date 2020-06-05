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
	
	private String id;
	private String entity;
	private int amount;
	private int amount_paid;
	private int amount_due;
	private String currency;
	private String receipt;
	private String status;
	private int attempts;
	private int payment_capture;
	private List<Object> notes;
	private long created_at;
	
	// for refund
	
	private AcquirerData acquirer_data;
	private String payment_id;
	private String speed_processed;
	private String speed_requested;
	
	// for payment capturing
	
	private String order_id;
	private String invoice_id;
	private Boolean international;
	private String method;
	private int amount_refunded;
	private String refund_status;
	private Boolean captured;
	private String description;
	private String cardId;
	private String bank;
	private String wallet;
	private String vpa;
	private String email;
	private String contact;
	private int fee;
	private int tax;
	private String error_code;
	private Object error_description;
	
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
	public int getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(int amount_paid) {
		this.amount_paid = amount_paid;
	}
	public int getAmount_due() {
		return amount_due;
	}
	public void setAmount_due(int amount_due) {
		this.amount_due = amount_due;
	}
	public void setNotes(List<Object> notes) {
		this.notes = notes;
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
	public long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}
	public AcquirerData getAcquirer_data() {
		return acquirer_data;
	}
	public void setAcquirer_data(AcquirerData acquirer_data) {
		this.acquirer_data = acquirer_data;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getSpeed_processed() {
		return speed_processed;
	}
	public void setSpeed_processed(String speed_processed) {
		this.speed_processed = speed_processed;
	}
	public String getSpeed_requested() {
		return speed_requested;
	}
	public void setSpeed_requested(String speed_requested) {
		this.speed_requested = speed_requested;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public Boolean getInternational() {
		return international;
	}
	public void setInternational(Boolean international) {
		this.international = international;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getAmount_refunded() {
		return amount_refunded;
	}
	public void setAmount_refunded(int amount_refunded) {
		this.amount_refunded = amount_refunded;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public Boolean getCaptured() {
		return captured;
	}
	public void setCaptured(Boolean captured) {
		this.captured = captured;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	public String getVpa() {
		return vpa;
	}
	public void setVpa(String vpa) {
		this.vpa = vpa;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public Object getError_description() {
		return error_description;
	}
	public void setError_description(Object error_description) {
		this.error_description = error_description;
	}
	public List<Object> getNotes() {
		return notes;
	}
	
}
