/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.dao.DashboardDAO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.DashboardResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;


/**
 * @author K VimaL Kumar
 * 
 */

@Controller
public class DashboardController {

	Gson gson = new Gson();

	@RequestMapping(value = "/dashboard/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DashboardResponseVO dashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();

		dasboardresponsevo.setData(dashboarddao.getDashboarddetails(roleid, id));
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/inputdata", method = RequestMethod.POST, produces = "application/json", consumes = "application/vnd.onem2m-ntfy+json")
	public @ResponseBody
	ResponseVO publicvoidpushVogoAssetTrackerData(HttpEntity<String> httpEntity) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();
		String json = httpEntity.getBody();

		try {
			responsevo = dashboarddao.postDashboarddetails(json);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responsevo;
	}
	
}
