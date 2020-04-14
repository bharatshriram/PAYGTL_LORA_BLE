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
		
		AccountDAO accountdao = new AccountDAO();
		
		if(accountdao.validateamount(topupvo)){
			throw new BusinessException("RECHARGE AMOUNT MUST BE GREATER THAN EMERGENCY CREDIT AND ALARM CREDIT");
		}
		
		if(!accountdao.checktopup(topupvo.getMeterID())) {
			throw new BusinessException("PREVIOUS TOPUP REQUEST IS PENDING");
		}
		
		return accountdao.addtopup(topupvo);
	}
	
	/* Configuration */
	
	public String addconfiguration(ConfigurationRequestVO configurationvo) throws BusinessException {
		// TODO Auto-generated method stub
		
		String result = "";

		AccountDAO accountdao = new AccountDAO();
		
		try {
			
			if (accountdao.checkstatus(configurationvo.getMeterID())) {
				throw new BusinessException("PREVIOUS COMMAND REQUEST IS PENDING");
			}
			
			result = accountdao.addconfiguration(configurationvo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
