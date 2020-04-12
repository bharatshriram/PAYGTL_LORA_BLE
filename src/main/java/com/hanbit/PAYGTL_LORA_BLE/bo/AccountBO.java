/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.bo;

import java.sql.SQLException;

import com.hanbit.PAYGTL_LORA_BLE.dao.AccountDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ConfigurationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpRequestVO;

/**
 * @author K VimaL Kumar
 *
 */
public class AccountBO {

	/* TopUp */
	
	public String addtopup(TopUpRequestVO topupvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		String result = "";

		AccountDAO accountdao = new AccountDAO();
		
		if(!(topupvo.getRechargeAmount() > topupvo.getEmergencyCredit()) && !(topupvo.getRechargeAmount() > topupvo.getAlarmCredit())){
			throw new BusinessException("RECHARGE AMOUNT MUST BE GREATER THAN EMERGENCY CREDIT AND ALARM CREDIT");
		}
		
		result = accountdao.addtopup(topupvo);
		
		return result;
	}
	
	/* Configuration */
	
	public String addconfiguration(ConfigurationRequestVO configurationvo) throws BusinessException {
		// TODO Auto-generated method stub
		
		String result = "";

		AccountDAO accountdao = new AccountDAO();
		
		try {
			boolean flag;
			
			flag = accountdao.checkstatus(configurationvo.getMeterID());
			
			if (flag) {
				throw new BusinessException("PREVIOUS COMMAND IS PENDING");
			}
			
			result = accountdao.addconfiguration(configurationvo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
