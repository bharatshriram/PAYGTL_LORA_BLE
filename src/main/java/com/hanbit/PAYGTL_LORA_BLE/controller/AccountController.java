/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;

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
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.StatusResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */

@Controller
public class AccountController {

	Gson gson = new Gson();
	AccountBO accountbo = new AccountBO();
	ResponseVO responsevo = new ResponseVO();

	/* TopUp */

	@RequestMapping(value = "/topup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addtopup(@RequestBody TopUpRequestVO topupRequestVO) throws ClassNotFoundException,
			BusinessException, SQLException {

		try {
			responsevo = accountbo.addtopup(topupRequestVO);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	/* Status */

	@RequestMapping(value = "/status/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	StatusResponseVO statusdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		AccountDAO accountdao = new AccountDAO();
		StatusResponseVO statusresponsevo = new StatusResponseVO();

		statusresponsevo.setData(accountdao.getStatusdetails(roleid, id));

		return statusresponsevo;
	}

	@RequestMapping(value = "/status/delete/{transactionID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletestatus(@PathVariable("transactionID") int transactionID)
			throws SQLException {

		AccountDAO accountdao = new AccountDAO();
		StatusRequestVO statusvo = new StatusRequestVO();
		ResponseVO responsevo = new ResponseVO();

		statusvo.setTransID(transactionID);

		responsevo.setResult(accountdao.deletestatus(statusvo));

		return responsevo;
	}

	@RequestMapping(value = "/status/print/{transactionID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO printreceipt(@PathVariable("transactionID") int transactionID)
			throws SQLException {

		AccountDAO accountdao = new AccountDAO();
		ResponseVO responsevo = new ResponseVO();

		responsevo.setResult(accountdao.printreceipt(transactionID));

		return responsevo;
	}

	/* Configuration */

	@RequestMapping(value = "/configuration/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ConfigurationResponseVO configurationdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		AccountDAO accountdao = new AccountDAO();
		ConfigurationResponseVO configurationresponsevo = new ConfigurationResponseVO();

		configurationresponsevo.setData(accountdao.getConfigurationdetails(roleid, id));

		return configurationresponsevo;
	}

	@RequestMapping(value = "/configuration/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addconfiguration(@RequestBody ConfigurationRequestVO configurationvo)
			throws ClassNotFoundException, SQLException, BusinessException {

		AccountBO accountbo = new AccountBO();
		ResponseVO responsevo = new ResponseVO();
		
		try{
			responsevo = accountbo.addconfiguration(configurationvo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/configuration/delete/{transactionID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO editcommunity(@PathVariable("transactionID") int transactionID)
			throws ClassNotFoundException, SQLException {

		AccountDAO accountdao = new AccountDAO();
		ResponseVO responsevo = new ResponseVO();

		responsevo.setResult(accountdao.deleteconfiguration(transactionID));

		return responsevo;
	}

}
