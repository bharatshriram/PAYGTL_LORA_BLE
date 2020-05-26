/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	AccountDAO accountdao = new AccountDAO();

	/* TopUp */

	@RequestMapping(value = "/topup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addtopup(@RequestBody TopUpRequestVO topupRequestVO) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = accountbo.addtopup(topupRequestVO);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	/* Status */

	@RequestMapping(value = "/status/{roleid}/{id}/{filterCid}/{day}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	StatusResponseVO statusdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filterCid") int filterCid, @PathVariable("day") int day) throws SQLException {

		StatusResponseVO statusresponsevo = new StatusResponseVO();

		statusresponsevo.setData(accountdao.getStatusdetails(roleid, id, filterCid, day));

		return statusresponsevo;
	}

	@RequestMapping(value = "/status/delete/{transactionID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletestatus(@PathVariable("transactionID") int transactionID)
			throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo = accountdao.deletestatus(transactionID);

		return responsevo;
	}
	
	@RequestMapping(value = "/status/print/{transactionID}", method = RequestMethod.GET, produces = "application/pdf")
	 public ResponseEntity<InputStreamResource> download(@PathVariable("transactionID") int transactionID) throws IOException, SQLException {
			
		ResponseVO responsevo = new ResponseVO();
		
		responsevo = accountdao.printreceipt(transactionID);
	
		File file = new File(responsevo.getLocation() + responsevo.getFileName());
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
		
		return response;
	
	}
	
	/* Configuration */

	@RequestMapping(value = "/configuration/{roleid}/{id}/{filterCid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ConfigurationResponseVO configurationdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filterCid") int filterCid) throws SQLException {

		ConfigurationResponseVO configurationresponsevo = new ConfigurationResponseVO();

		configurationresponsevo.setData(accountdao.getConfigurationdetails(roleid, id, filterCid));

		return configurationresponsevo;
	}

	@RequestMapping(value = "/configuration/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addconfiguration(@RequestBody ConfigurationRequestVO configurationvo)
			throws ClassNotFoundException, SQLException, BusinessException {

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
		ResponseVO responsevo = new ResponseVO();
		responsevo = accountdao.deleteconfiguration(transactionID);

		return responsevo;
	}

}
