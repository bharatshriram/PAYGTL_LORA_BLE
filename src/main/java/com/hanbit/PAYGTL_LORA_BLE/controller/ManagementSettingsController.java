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
import com.hanbit.PAYGTL_LORA_BLE.bo.ManagementSettingsBO;
import com.hanbit.PAYGTL_LORA_BLE.constants.ExtraConstants;
import com.hanbit.PAYGTL_LORA_BLE.dao.ManagementSettingsDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.AlertRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.VacationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserManagementRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.AlertResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.VacationResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.UserManagementResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.utils.Encryptor;

/**
 * @author K VimaL kumar
 * 
 */
@Controller
public class ManagementSettingsController {

	Gson gson = new Gson();

	/* User */

	@RequestMapping(value = "/user/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	UserManagementResponseVO userdetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id) throws SQLException {

		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		UserManagementResponseVO usermanagementresponsevo = new UserManagementResponseVO();

		usermanagementresponsevo.setData(managementsettingsdao.getuserdetails(roleid,id));

		return usermanagementresponsevo;
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO adduser(@RequestBody UserManagementRequestVO usermanagementvo) throws ClassNotFoundException,
			SQLException, BusinessException {

		String result = "Failure";
		ManagementSettingsBO managementsettingsbo = new ManagementSettingsBO();
		ResponseVO responsevo = new ResponseVO();

		usermanagementvo.setUserPassword(Encryptor.encrypt(ExtraConstants.key1,
				ExtraConstants.key2, usermanagementvo.getUserPassword()));
		usermanagementvo.setConfirmPassword(Encryptor.encrypt(
				ExtraConstants.key1, ExtraConstants.key2,
				usermanagementvo.getConfirmPassword()));
		
		try{
		result = managementsettingsbo.adduser(usermanagementvo);
			} catch (BusinessException e) {
				String message = e.getMessage();
				responsevo.setMessage(message);
			}

		responsevo.setResult(result);

		return responsevo;
	}

	/* Alert */

	@RequestMapping(value = "/alert", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	AlertResponseVO alertdetails() throws SQLException {

		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		AlertResponseVO alertresponsevo = new AlertResponseVO();

		alertresponsevo.setData(managementsettingsdao.getalertdetails());

		return alertresponsevo;
	}

	@RequestMapping(value = "/alert/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addalert(@RequestBody AlertRequestVO alertvo) throws ClassNotFoundException,
			SQLException, BusinessException {

		ManagementSettingsBO managementsettingsbo = new ManagementSettingsBO();
		ResponseVO responsevo = new ResponseVO();

		try {
			 responsevo.setResult(managementsettingsbo.addalert(alertvo));
			
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}
		
		return responsevo;
	}

	@RequestMapping(value = "/alert/edit/{alertID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editalert(@PathVariable("alertID") int alertID,
			@RequestBody AlertRequestVO alertvo) throws ClassNotFoundException,
			BusinessException, SQLException {

		String result = "Failure";
		ManagementSettingsBO managementsettingsbo = new ManagementSettingsBO();
		ResponseVO responsevo = new ResponseVO();

		alertvo.setAlertID(alertID);
		try {
			result = managementsettingsbo.editalert(alertvo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}
	
	/* Vacation */

	@RequestMapping(value = "/vacation/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	VacationResponseVO vacationdetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id) throws SQLException {

		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		VacationResponseVO vacationResponseVO = new VacationResponseVO();

		vacationResponseVO.setData(managementsettingsdao.getvacationdetails(roleid, id));

		return vacationResponseVO;
	}

	@RequestMapping(value = "/vacation/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addvacation(@RequestBody VacationRequestVO vacationRequestVO) throws ClassNotFoundException,
			SQLException, BusinessException {

		String result = "Failure";
		ManagementSettingsBO managementsettingsbo = new ManagementSettingsBO();
		ResponseVO responsevo = new ResponseVO();
		
		try{
		result = managementsettingsbo.addvacation(vacationRequestVO);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}
		responsevo.setResult(result);

		return responsevo;
	}


}
