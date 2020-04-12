/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.bo.DashboardBO;
import com.hanbit.PAYGTL_LORA_BLE.dao.DashboardDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ValveRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.DashboardResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;


/**
 * @author K VimaL Kumar
 * 
 */

@Controller
public class DashboardController {

	Gson gson = new Gson();

	@RequestMapping(value = "/dashboard/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DashboardResponseVO dashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();

		dasboardresponsevo.setData(dashboarddao.getDashboarddetails(roleid, id));
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/dashboard/{meterid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String userdashboarddetails(@PathVariable("meterid") int meterID) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		List<DashboardResponseVO> userdashboarddetailslist = new ArrayList<DashboardResponseVO>();
		ResponseVO responsevo = new ResponseVO();

		userdashboarddetailslist = dashboarddao.getUserDashboarddetails(meterID);
		
		responsevo.setDashboard(userdashboarddetailslist);
		
		String userdashboarddetails = gson.toJson(responsevo);
	
		return userdashboarddetails;
	}
	
	@RequestMapping(value = "/valve", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO setvalve(@RequestBody String json) throws SQLException, ClassNotFoundException,
	BusinessException, ParseException {

		String result = "";
		DashboardBO dashboardbo = new DashboardBO();
		ValveRequestVO valverequestvo = new ValveRequestVO();
		ResponseVO responsevo = new ResponseVO();

		valverequestvo = gson.fromJson(json, ValveRequestVO.class);
		
		try {
			
			result = dashboardbo.setvalve(valverequestvo);
		
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}
	
	@RequestMapping(value = "/inputdata", method = RequestMethod.POST, produces = "application/json", consumes = "application/vnd.onem2m-ntfy+json")
	public @ResponseBody
	ResponseVO publicvoidpushVogoAssetTrackerData(HttpEntity<String> httpEntity) {

	//	String result = "";
		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();
		String json = httpEntity.getBody();

		try {
			responsevo = dashboarddao.postDashboarddetails(json);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responsevo;
	}

}
