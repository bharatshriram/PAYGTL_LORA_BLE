/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.bo;

import java.sql.SQLException;

import com.hanbit.PAYGTL_LORA_BLE.dao.LoginDAO;
import com.hanbit.PAYGTL_LORA_BLE.dao.ManagementSettingsDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.AlertRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.VacationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserManagementRequestVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class ManagementSettingsBO {
	
	ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
	ResponseVO responsevo = new ResponseVO();

	/* UserManagement */

	public ResponseVO adduser(UserManagementRequestVO usermanagementvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		LoginDAO logindao = new LoginDAO();
		
		if(usermanagementvo.getRoleID()==4) {
			
			if(usermanagementvo.getUserID().isEmpty() || usermanagementvo.getUserPassword().isEmpty() || usermanagementvo.getConfirmPassword().isEmpty()){
				throw new BusinessException("ALL FIELDS ARE MANDATORY");
			}			
		} else {
			
			if(usermanagementvo.getCommunityID()<=0 || usermanagementvo.getBlockID()<=0 ||usermanagementvo.getUserID().isEmpty() || usermanagementvo.getUserPassword().isEmpty() || usermanagementvo.getConfirmPassword().isEmpty()){
				throw new BusinessException("ALL FIELDS ARE MANDATORY");
			}			
		}
		
		if(!usermanagementvo.getUserPassword().contentEquals((usermanagementvo.getConfirmPassword()))){
			throw new BusinessException("PASSWORD AND CONFIRM PASSWORD DOESNT MATCH");
		}
		
		if(logindao.checkuserid(usermanagementvo.getUserID())){
			throw new BusinessException("USERID ALREADY EXIST PLEASE CHOOOSE ANOTHER USER-ID");
		}

		return managementsettingsdao.adduser(usermanagementvo);
		
	}

	/* Alert */

	public ResponseVO addalert(AlertRequestVO alertvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if(alertvo.getCommunityID()==0 || alertvo.getNoAMRInterval()==0 || alertvo.getLowBatteryVoltage()==0 || alertvo.getTimeOut()==0){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		try {
			
			if (managementsettingsdao.checkalertsettings()) {
				throw new BusinessException("SETTINGS ARE ALREADY ADDED");
			}
			
			responsevo = managementsettingsdao.addalert(alertvo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responsevo;
	}

	public ResponseVO editalert(AlertRequestVO alertvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if(alertvo.getNoAMRInterval()==0 || alertvo.getLowBatteryVoltage()==0 || alertvo.getTimeOut()==0){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}

		return managementsettingsdao.editalert(alertvo);
		
	}

	/* Vacation */

	public ResponseVO addvacation(VacationRequestVO vacationRequestVO) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		if(vacationRequestVO.getCommunityID() < 0 || vacationRequestVO.getBlockID() < 0 || vacationRequestVO.getVacationName().isEmpty() 
				|| vacationRequestVO.getCRNNumber().isEmpty() || vacationRequestVO.getStartDateTime().isEmpty() || vacationRequestVO.getEndDateTime().isEmpty()){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		if (managementsettingsdao.checkvacationsettings(vacationRequestVO)) {
			throw new BusinessException("PREVIOUS VACATION REQUEST IS PENDING");
		}

		return managementsettingsdao.addvacation(vacationRequestVO);
		
	}

	public ResponseVO editvacation(VacationRequestVO vacationRequestVO) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		
		if(vacationRequestVO.getVacationID() < 0 || vacationRequestVO.getCommunityID() < 0 || vacationRequestVO.getBlockID() < 0 || vacationRequestVO.getVacationName().isEmpty() 
				|| vacationRequestVO.getCRNNumber().isEmpty() || vacationRequestVO.getStartDateTime().isEmpty() || vacationRequestVO.getEndDateTime().isEmpty()){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		/*if (managementsettingsdao.checkvacationsettingsdoneby(vacationRequestVO)) {
			throw new BusinessException("PLEASE DO THE UPDATE FROM MOBILE APP AS THE VACATION HAS BEEN RAISED FROM MOBILE APP ONLY");
		}*/
		
		return managementsettingsdao.editvacation(vacationRequestVO);
	}

}
