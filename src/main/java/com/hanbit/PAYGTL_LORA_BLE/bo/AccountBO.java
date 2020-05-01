/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.bo;

import java.sql.SQLException;

import com.hanbit.PAYGTL_LORA_BLE.dao.AccountDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ConfigurationRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TopUpRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
public class AccountBO {

	AccountDAO accountdao = new AccountDAO();
	ResponseVO responsevo = new ResponseVO();
	
	/* TopUp */
	
	public ResponseVO addtopup(TopUpRequestVO topupvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if(accountdao.validateamount(topupvo)){
			throw new BusinessException("RECHARGE AMOUNT MUST BE GREATER THAN EMERGENCY CREDIT AND ALARM CREDIT");
		}
		
		if(accountdao.checktopup(topupvo.getMeterID())) {
			throw new BusinessException("PREVIOUS TOPUP REQUEST IS PENDING");
		}
		
		return accountdao.addtopup(topupvo);
	}
	
	/* Configuration */
	
	public ResponseVO addconfiguration(ConfigurationRequestVO configurationvo) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		
			if (accountdao.checkstatus(configurationvo.getMeterID())) {
				throw new BusinessException("PREVIOUS COMMAND REQUEST IS PENDING");
			}
			
			responsevo = accountdao.addconfiguration(configurationvo);
			
		return responsevo;
	}

}
