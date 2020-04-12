/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.bo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.hanbit.PAYGTL_LORA_BLE.dao.DashboardDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.ValveRequestVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class DashboardBO {

	public String setvalve(ValveRequestVO valverequestvo) throws BusinessException, SQLException, ParseException {
		// TODO Auto-generated method stub
		String result = "";

		DashboardDAO dashboarddao = new DashboardDAO();
	
		if(valverequestvo.getOpenTime().isEmpty() || valverequestvo.getCloseTime().isEmpty()){
			throw new BusinessException("All Fields Are Mandatory");
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
		Date openTime = format.parse(valverequestvo.getOpenTime());
		Date closeTime = format.parse(valverequestvo.getCloseTime());
		
		long duration = closeTime.getTime() - openTime.getTime();

		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
		
		if(diffInDays<0 || diffInHours<0 || diffInMinutes<0 || diffInSeconds<0){
			throw new BusinessException("Difference in Open Time and Close Time is not valid");
		}
		
		result = dashboarddao.setvalve(valverequestvo);

		return result;
	}
}
