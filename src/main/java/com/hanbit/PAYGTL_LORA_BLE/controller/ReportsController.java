package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.dao.ReportsDAO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.AlarmRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.FinancialReportsRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpSummaryRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserConsumptionRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.AlarmsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.FinancialReportsResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TopUpSummaryResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.UserConsumptionReportsResponseVO;

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
	FinancialReportsResponseVO getfinancialreports(@PathVariable("roleid") int roleid, @PathVariable("id") int id, @RequestBody FinancialReportsRequestVO financialreportsrequestvo) throws SQLException {

		FinancialReportsResponseVO financialReportsResponseVO = new FinancialReportsResponseVO();
		
		financialReportsResponseVO.setData(reportsdao.getFinancialReportsdetails(financialreportsrequestvo, roleid, id));
		financialReportsResponseVO.setTotalAmountForSelectedPeriod(financialReportsResponseVO.getData().size() == 0 ? 0 : financialReportsResponseVO.getData().get(financialReportsResponseVO.getData().size()-1).getTotalAmountForSelectedPeriod());
		financialReportsResponseVO.setTotalUnitsForSelectedPeriod(financialReportsResponseVO.getData().size() == 0 ? 0 : financialReportsResponseVO.getData().get(financialReportsResponseVO.getData().size()-1).getTotalUnitsForSelectedPeriod());

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
	
	/* TopUp Summary */
	
	@RequestMapping(value = "/topupsummary", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	TopUpSummaryResponseVO topupsummary(@RequestBody TopUpSummaryRequestVO topupSummaryRequestVO) throws SQLException {

		TopUpSummaryResponseVO topUpSummaryResponseVO = new TopUpSummaryResponseVO();
		
		topUpSummaryResponseVO.setData(reportsdao.gettopupsummarydetails(topupSummaryRequestVO));
		
		return topUpSummaryResponseVO;
	}
	
	/* Alarms */

	@RequestMapping(value = "/alarm/{roleid}/{id}/{filterCid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	AlarmsResponseVO alarmdetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id, @PathVariable("filterCid") int filterCid) throws SQLException {

		AlarmsResponseVO alarmsResponseVO = new AlarmsResponseVO();

		alarmsResponseVO.setData(reportsdao.getAlarmdetails(roleid, id, filterCid));

		return alarmsResponseVO;
	}
	
	@RequestMapping(value = "/alarmreports", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	AlarmsResponseVO alarmreports(@RequestBody AlarmRequestVO alarmRequestVO) throws SQLException {

		AlarmsResponseVO alarmsResponseVO = new AlarmsResponseVO();

		alarmsResponseVO.setData(reportsdao.getAlarmreportsdetails(alarmRequestVO));

		return alarmsResponseVO;

	}

}
