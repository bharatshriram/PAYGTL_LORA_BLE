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
	
	ManagementSettingsBO managementsettingsbo = new ManagementSettingsBO();
	ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
	Gson gson = new Gson();

	/* User */

	@RequestMapping(value = "/user/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	UserManagementResponseVO userdetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id) throws SQLException {

		UserManagementResponseVO usermanagementresponsevo = new UserManagementResponseVO();

		usermanagementresponsevo.setData(managementsettingsdao.getuserdetails(roleid,id));

		return usermanagementresponsevo;
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO adduser(@RequestBody UserManagementRequestVO usermanagementvo) throws ClassNotFoundException,
			SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();

		usermanagementvo.setUserPassword(Encryptor.encrypt(ExtraConstants.key1,
				ExtraConstants.key2, usermanagementvo.getUserPassword()));
		usermanagementvo.setConfirmPassword(Encryptor.encrypt(
				ExtraConstants.key1, ExtraConstants.key2,
				usermanagementvo.getConfirmPassword()));
		
		try{
		responsevo = managementsettingsbo.adduser(usermanagementvo);
			} catch (BusinessException e) {
				responsevo.setMessage(e.getMessage());
				responsevo.setResult("Failure");
			}

		return responsevo;
	}

	/* Alert */

	@RequestMapping(value = "/alert", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	AlertResponseVO alertdetails() throws SQLException {

		AlertResponseVO alertresponsevo = new AlertResponseVO();

		alertresponsevo.setData(managementsettingsdao.getalertdetails());

		return alertresponsevo;
	}

	@RequestMapping(value = "/alert/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addalert(@RequestBody AlertRequestVO alertvo) throws ClassNotFoundException,
			SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();

		try {
			 responsevo = managementsettingsbo.addalert(alertvo);
			
		} catch (BusinessException e) {
			responsevo.setMessage(e.getMessage());
			responsevo.setResult("Failure");
		}
		
		return responsevo;
	}

	@RequestMapping(value = "/alert/edit/{alertID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editalert(@PathVariable("alertID") int alertID,
			@RequestBody AlertRequestVO alertvo) throws ClassNotFoundException,
			BusinessException, SQLException {

		ResponseVO responsevo = new ResponseVO();

		alertvo.setAlertID(alertID);
		try {
			responsevo = managementsettingsbo.editalert(alertvo);
		} catch (BusinessException e) {
			responsevo.setMessage(e.getMessage());
			responsevo.setResult("Failure");
		}

		return responsevo;
	}
	
	/* Vacation */

	@RequestMapping(value = "/vacation/{roleid}/{id}/{filterCid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	VacationResponseVO vacationdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filterCid") int filterCid) throws SQLException {

		VacationResponseVO vacationResponseVO = new VacationResponseVO();

		vacationResponseVO.setData(managementsettingsdao.getvacationdetails(roleid, id, filterCid));

		return vacationResponseVO;
	}

	@RequestMapping(value = "/vacation/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addvacation(@RequestBody VacationRequestVO vacationRequestVO) throws ClassNotFoundException,
			SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		
		try{
			responsevo = managementsettingsbo.addvacation(vacationRequestVO);
		} catch (BusinessException e) {
			responsevo.setMessage(e.getMessage());
			responsevo.setResult("Failure");
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/vacation/edit/{vacationID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editvacation(@RequestBody VacationRequestVO vacationRequestVO, @PathVariable("vacationID") int vacationID) throws ClassNotFoundException,
			SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		vacationRequestVO.setVacationID(vacationID);
		
		try{
			responsevo = managementsettingsbo.editvacation(vacationRequestVO);
		} catch (BusinessException e) {
			responsevo.setMessage(e.getMessage());
			responsevo.setResult("Failure");
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/vacation/delete/{source}/{vacationID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletevacation(@PathVariable("vacationID") int vacationID, @PathVariable("source") String source) throws ClassNotFoundException,
			SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		
		try{
			responsevo = managementsettingsbo.deletevacation(vacationID, source);
		} catch (Exception e) {
			e.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		}

		return responsevo;
	}

}
