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
import com.hanbit.PAYGTL_LORA_BLE.bo.AccountBO;
import com.hanbit.PAYGTL_LORA_BLE.dao.AccountDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ConfigurationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.StatusRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ConfigurationResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.FixedChargesResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.StatusResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */

@Controller
public class AccountController {

	Gson gson = new Gson();

	/* TopUp */

	@RequestMapping(value = "/topup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addtopup(@RequestBody TopUpRequestVO topupRequestVO) throws ClassNotFoundException,
			BusinessException, SQLException {

		String result = "Failure";
		AccountBO accountbo = new AccountBO();
		ResponseVO responsevo = new ResponseVO();

		try {
			result = accountbo.addtopup(topupRequestVO);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}

	/* Status */

	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String statusdetails() throws SQLException {

		AccountDAO accountdao = new AccountDAO();
		List<StatusResponseVO> statusdetailslist = new ArrayList<StatusResponseVO>();
		ResponseVO responsevo = new ResponseVO();

		statusdetailslist = accountdao.getStatusdetails();

		responsevo.setStatus(statusdetailslist);

		String statusdetails = gson.toJson(responsevo);

		return statusdetails;
	}

	@RequestMapping(value = "/status/delete/{transactionID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String deletestatus(@PathVariable("transactionID") int transactionID)
			throws SQLException {

		AccountDAO accountdao = new AccountDAO();
		StatusRequestVO statusvo = new StatusRequestVO();
		String result = "";

		statusvo.setTransID(transactionID);

		result = accountdao.deletestatus(statusvo);

		ResponseVO responsevo = new ResponseVO();

		responsevo.setResult(result);
		String flag = gson.toJson(responsevo);

		return flag;
	}

	@RequestMapping(value = "/status/print/{transactionID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String printreceipt(@PathVariable("transactionID") int transactionID)
			throws SQLException {

		String result = "";
		AccountDAO accountdao = new AccountDAO();
		ResponseVO responsevo = new ResponseVO();

		result = accountdao.printreceipt(transactionID);
		responsevo.setResult(result);

		String flag = gson.toJson(responsevo);
		return flag;
	}

	/* Fixed Charges */

	@RequestMapping(value = "/fixedcharges", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String fixedchargesdetails() throws SQLException {

		AccountDAO accountdao = new AccountDAO();
		List<FixedChargesResponseVO> fixedchargesdetailslist = new ArrayList<FixedChargesResponseVO>();
		ResponseVO responsevo = new ResponseVO();

		fixedchargesdetailslist = accountdao.getFixedChargesdetails();
		responsevo.setFixedcharges(fixedchargesdetailslist);

		String fixedchargesdetails = gson.toJson(responsevo);

		return fixedchargesdetails;
	}

	/* Configuration */

	@RequestMapping(value = "/configuration", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	String configurationdetails() throws SQLException {

		AccountDAO accountdao = new AccountDAO();
		List<ConfigurationResponseVO> configurationdetailslist = new ArrayList<ConfigurationResponseVO>();
		ResponseVO responsevo = new ResponseVO();

		configurationdetailslist = accountdao.getConfigurationdetails();
		responsevo.setConfigurations(configurationdetailslist);

		String configurationdetails = gson.toJson(responsevo);

		return configurationdetails;
	}

	@RequestMapping(value = "/configuration/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	String addconfiguration(@RequestBody String json)
			throws ClassNotFoundException, SQLException, BusinessException {

		String result = "";
		AccountBO accountbo = new AccountBO();
		ConfigurationRequestVO configurationvo = new ConfigurationRequestVO();
		ResponseVO responsevo = new ResponseVO();
		
		configurationvo = gson.fromJson(json, ConfigurationRequestVO.class);
		
		if(configurationvo.getCommandType().equals("Solenoid Close")){
			configurationvo.setCommandType("0");
		}else if(configurationvo.getCommandType().equals("Clear Tamper")){
			configurationvo.setCommandType("1");
		}else if(configurationvo.getCommandType().equals("Clear Meter")){
			configurationvo.setCommandType("3");
		}else if(configurationvo.getCommandType().equals("RTC")){
			configurationvo.setCommandType("5");
		}else if(configurationvo.getCommandType().equals("Set Default Read")){
			configurationvo.setCommandType("6");
		}else if(configurationvo.getCommandType().equals("Active Mode")){
			configurationvo.setCommandType("7");
		}else if(configurationvo.getCommandType().equals("Shutdown Mode")){
			configurationvo.setCommandType("8");
		}else if(configurationvo.getCommandType().equals("Maintenance Mode")){
			configurationvo.setCommandType("9");
		}else if(configurationvo.getCommandType().equals("Set Weekend")){
			configurationvo.setCommandType("10");
		}else if(configurationvo.getCommandType().equals("Solenoid Open")){
			configurationvo.setCommandType("40");
		}else{
			configurationvo.setCommandType("50");
		}
		
		try{
		result = accountbo.addconfiguration(configurationvo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}
		responsevo.setResult(result);
		String flag = gson.toJson(responsevo);

		return flag;
	}

	@RequestMapping(value = "/configuration/delete/{configurationID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String editcommunity(@PathVariable("configurationID") String configurationid)
			throws ClassNotFoundException, SQLException {

		String result = "";
		AccountDAO accountdao = new AccountDAO();
		ConfigurationRequestVO configurationvo = new ConfigurationRequestVO();

		configurationvo.setCommandTransID(configurationid);

		result = accountdao.deleteconfiguration(configurationvo);

		ResponseVO responsevo = new ResponseVO();

		responsevo.setResult(result);
		String flag = gson.toJson(responsevo);

		return flag;
	}

}
