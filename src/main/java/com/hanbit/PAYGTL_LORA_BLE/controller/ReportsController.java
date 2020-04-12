/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
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
	
	/* Financial Reports */
/*	@RequestMapping(value = "/financialreports", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	String getfinancialreports(@RequestBody String json) throws SQLException {

		ReportsDAO reportsdao = new ReportsDAO();
		List<FinancialReportsResponseVO> financialreportsdetailslist = new ArrayList<FinancialReportsResponseVO>();
		ResponseVO responsevo = new ResponseVO();
		FinancialReportsRequestVO financialreportsrequestvo = new FinancialReportsRequestVO();
		
		financialreportsrequestvo = gson.fromJson(json, FinancialReportsRequestVO.class);
		
		financialreportsdetailslist = reportsdao.getFinancialReportsdetails(financialreportsrequestvo);
		
		responsevo.setFinancialreports(financialreportsdetailslist);
		
		String financialreportsdetails = gson.toJson(responsevo);

		return financialreportsdetails;
	}*/
	
	/* User Consumption Reports */
	
	@RequestMapping(value = "/userconsumptionreports", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	String userconsumptionreports(@RequestBody String json) throws SQLException {

		ReportsDAO reportsdao = new ReportsDAO();
		List<UserConsumptionReportsResponseVO> userconsumptionreportsdetailslist = new ArrayList<UserConsumptionReportsResponseVO>();
		ResponseVO responsevo = new ResponseVO();
		UserConsumptionRequestVO userconsumptionreportsrequestvo = new UserConsumptionRequestVO();
		
		userconsumptionreportsrequestvo = gson.fromJson(json, UserConsumptionRequestVO.class);			
	
		userconsumptionreportsdetailslist = reportsdao.getuserconsumptionreportsdetails(userconsumptionreportsrequestvo);
		
		responsevo.setUserconsumptionreports(userconsumptionreportsdetailslist);
		
		String userconsumptionreportsdetails = gson.toJson(responsevo);

		return userconsumptionreportsdetails;
	}
	
	@RequestMapping(value = "/userconsumptionreports/pdf", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	String pdfreports(@RequestBody String json) throws SQLException {
		
		String result = "";
		ReportsDAO reportsdao = new ReportsDAO();

		ResponseVO responsevo = new ResponseVO();
		UserConsumptionRequestVO userconsumptionreportsrequestvo = new UserConsumptionRequestVO();
		
		userconsumptionreportsrequestvo = gson.fromJson(json, UserConsumptionRequestVO.class);
	
		result = reportsdao.getpdf(userconsumptionreportsrequestvo);
		
		responsevo.setResult(result);
		
		String flag = gson.toJson(responsevo);
		return flag;
	}
	
	@RequestMapping(value = "/userconsumptionreports/excel", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	String excelreports(@RequestBody String json) throws SQLException {

		String result = "";
		ReportsDAO reportsdao = new ReportsDAO();

		ResponseVO responsevo = new ResponseVO();
		UserConsumptionRequestVO userconsumptionreportsrequestvo = new UserConsumptionRequestVO();
		
		userconsumptionreportsrequestvo = gson.fromJson(json, UserConsumptionRequestVO.class);
	
		result = reportsdao.getexcel(userconsumptionreportsrequestvo);
		
		responsevo.setResult(result);
		
		String flag = gson.toJson(responsevo);
		return flag;
	}
	
	/* TopUp Summary */
	
	@RequestMapping(value = "/topupsummary", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	String topupsummary(@RequestBody String json) throws SQLException {

		ReportsDAO reportsdao = new ReportsDAO();
		List<TopUpSummaryResponseVO> topupsummarydetailslist = new ArrayList<TopUpSummaryResponseVO>();
		ResponseVO responsevo = new ResponseVO();
		TopUpSummaryRequestVO topupsummaryrequestvo = new TopUpSummaryRequestVO();
		
		topupsummaryrequestvo = gson.fromJson(json, TopUpSummaryRequestVO.class);
		
		topupsummarydetailslist = reportsdao.gettopupsummarydetails(topupsummaryrequestvo);
		
		responsevo.setTopupsummary(topupsummarydetailslist);
		
		String topupsummarydetails = gson.toJson(responsevo);

		return topupsummarydetails;
	}
	
	/*Valve Reports*/
	
	@RequestMapping(value = "/valvereports", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String valvereports() throws SQLException {

		ReportsDAO reportsdao = new ReportsDAO();
		List<ValveReportsResponseVO> valvereportslist = new ArrayList<ValveReportsResponseVO>();
		ResponseVO responsevo = new ResponseVO();

		valvereportslist = reportsdao.getvalvereports();
		responsevo.setValvereports(valvereportslist);
		
		String valvereportsdetails = gson.toJson(responsevo);

		return valvereportsdetails;
	}

	/* Alarms */

		@RequestMapping(value = "/alarm", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody
		String alarmdetails() throws SQLException {

			ReportsDAO reportsdao = new ReportsDAO();
			List<AlarmsResponseVO> alarmdetailslist = new ArrayList<AlarmsResponseVO>();
			ResponseVO responsevo = new ResponseVO();

			alarmdetailslist = reportsdao.getAlarmdetails();
			responsevo.setAlarms(alarmdetailslist);
			
			String alarmdetails = gson.toJson(responsevo);

			return alarmdetails;
		}

}
