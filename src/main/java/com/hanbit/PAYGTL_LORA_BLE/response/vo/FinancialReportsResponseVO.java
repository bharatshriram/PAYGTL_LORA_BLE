/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class FinancialReportsResponseVO {
	
	private float totalAmount;
	private List<FinancialReportsResponseVO> data;

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<FinancialReportsResponseVO> getData() {
		return data;
	}

	public void setData(List<FinancialReportsResponseVO> data) {
		this.data = data;
	}
	
	
}
