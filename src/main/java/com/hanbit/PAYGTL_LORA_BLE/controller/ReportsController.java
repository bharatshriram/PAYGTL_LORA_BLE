/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.dao.ReportsDAO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.FinancialReportsRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpSummaryRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserConsumptionRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.AlarmsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.FinancialReportsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TopUpSummaryResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.UserConsumptionReportsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ValveReportsResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */

@Controller
public class ReportsController {

	Gson gson = new Gson();
	ReportsDAO reportsdao = new ReportsDAO();
	
	/* Financial Reports */
	@RequestMapping(value = "/financialreports/{roleid}/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	FinancialReportsResponseVO getfinancialreports(FinancialReportsRequestVO financialreportsrequestvo, @PathVariable("roleid") int roleid, @PathVariable("id") int id) throws SQLException {

		FinancialReportsResponseVO financialReportsResponseVO = new FinancialReportsResponseVO();
		
		financialReportsResponseVO.setData(reportsdao.getFinancialReportsdetails(financialreportsrequestvo, roleid, id));

		return financialReportsResponseVO;
	}
	
	/* User Consumption Reports */
	
	@RequestMapping(value = "/userconsumptionreports", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	UserConsumptionReportsResponseVO userconsumptionreports(@RequestBody UserConsumptionRequestVO userConsumptionRequestVO) throws SQLException {

		UserConsumptionReportsResponseVO userConsumptionReportsResponseVO = new UserConsumptionReportsResponseVO();
		
		userConsumptionReportsResponseVO.setData(reportsdao.getuserconsumptionreportsdetails(userConsumptionRequestVO));
		
		return userConsumptionReportsResponseVO;
	}
	
	@RequestMapping(value = "/userconsumptionreports/pdf", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO pdfreports(@RequestBody UserConsumptionRequestVO userConsumptionRequestVO) throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		
//		responsevo.setResult(reportsdao.getpdf(userconsumptionreportsrequestvo));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/userconsumptionreports/excel", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO excelreports(@RequestBody UserConsumptionRequestVO userConsumptionRequestVO) throws SQLException {

		ResponseVO responsevo = new ResponseVO();
		
//		responsevo.setResult(reportsdao.getexcel(userconsumptionreportsrequestvo));
		
		return responsevo;
	}
	
	/* TopUp Summary */
	
	@RequestMapping(value = "/topupsummary", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	TopUpSummaryResponseVO topupsummary(@RequestBody TopUpSummaryRequestVO topupSummaryRequestVO) throws SQLException {

		TopUpSummaryResponseVO topUpSummaryResponseVO = new TopUpSummaryResponseVO();
		
		topUpSummaryResponseVO.setData(reportsdao.gettopupsummarydetails(topupSummaryRequestVO));
		
		return topUpSummaryResponseVO;
	}
	
	/*Valve Reports*/
	
	@RequestMapping(value = "/valvereports", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String valvereports() throws SQLException {

		List<ValveReportsResponseVO> valvereportslist = new ArrayList<ValveReportsResponseVO>();
		ResponseVO responsevo = new ResponseVO();

		valvereportslist = reportsdao.getvalvereports();
		responsevo.setValvereports(valvereportslist);
		
		String valvereportsdetails = gson.toJson(responsevo);

		return valvereportsdetails;
	}

	/* Alarms */

		@RequestMapping(value = "/alarm/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody
		AlarmsResponseVO alarmdetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id) throws SQLException {

			AlarmsResponseVO alarmsResponseVO = new AlarmsResponseVO();

			alarmsResponseVO.setData(reportsdao.getAlarmdetails(roleid, id));

			return alarmsResponseVO;
		}

}
