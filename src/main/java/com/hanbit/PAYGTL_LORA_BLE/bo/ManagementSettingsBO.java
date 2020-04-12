/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.bo;

import java.sql.SQLException;

import com.hanbit.PAYGTL_LORA_BLE.dao.LoginDAO;
import com.hanbit.PAYGTL_LORA_BLE.dao.ManagementSettingsDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.AlertRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.HolidayRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.UserManagementRequestVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class ManagementSettingsBO {

	/* UserManagement */

	public String adduser(UserManagementRequestVO usermanagementvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		LoginDAO logindao = new LoginDAO();
		
		if(usermanagementvo.getRoleID()==4) {
			
			if(usermanagementvo.getCommunityID()<0 || usermanagementvo.getBlockID()<0 ||usermanagementvo.getUserID().isEmpty() || usermanagementvo.getUserPassword().isEmpty() || usermanagementvo.getConfirmPassword().isEmpty()){
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

	public String addalert(AlertRequestVO alertvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		String result = "";

		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		
		if(alertvo.getCommunityID()==0 || alertvo.getNoAMRInterval()==0 || alertvo.getLowBatteryVoltage()==0 || alertvo.getTimeOut()==0){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		try {
			
			if (managementsettingsdao.checkalertsettings(alertvo.getCommunityID())) {
				throw new BusinessException("SETTINGS FOR THE SELECTED COMMUNITY ARE ALREADY ADDED");
			}
			
			result = managementsettingsdao.addalert(alertvo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public String editalert(AlertRequestVO alertvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		
		if(alertvo.getCommunityID()==0 || alertvo.getNoAMRInterval()==0 || alertvo.getLowBatteryVoltage()==0 || alertvo.getTimeOut()==0){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}

		return managementsettingsdao.editalert(alertvo);
		
	}

	/* Holiday */

	public String addholiday(HolidayRequestVO holidayvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		String result = "";

		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		
		if(holidayvo.getCommunityName().isEmpty() || holidayvo.getHolidayDate().isEmpty() || holidayvo.getHolidayName().isEmpty()){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}

		result = managementsettingsdao.addholiday(holidayvo);

		return result;
		
	}

	public String editholiday(HolidayRequestVO holidayvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		String result = "";

		ManagementSettingsDAO managementsettingsdao = new ManagementSettingsDAO();
		
		if(holidayvo.getCommunityName().isEmpty() || holidayvo.getHolidayDate().isEmpty() || holidayvo.getHolidayName().isEmpty()){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}

		result = managementsettingsdao.editholiday(holidayvo);

		return result;
		
	}

}
