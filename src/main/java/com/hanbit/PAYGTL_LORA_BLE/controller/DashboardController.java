/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.dao.DashboardDAO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.DashboardRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.FilterVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TataRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.DashboardResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.GraphResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.HomeResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;


/**
 * @author K VimaL Kumar
 * 
 */

@Controller
public class DashboardController {

	private static final Logger logger = Logger.getLogger(DashboardController.class);
	
	
	@RequestMapping(value = "/dashboard/{roleid}/{id}/{filter}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DashboardResponseVO dashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filter") int filter) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();

		dasboardresponsevo.setData(dashboarddao.getDashboarddetails(roleid, id, filter));
		dasboardresponsevo.setTotal(dasboardresponsevo.getData().size());
		dasboardresponsevo.setNonCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().get(dasboardresponsevo.getData().size()-1).getNonCommunicating());
		dasboardresponsevo.setCommunicating(dasboardresponsevo.getData().size()-dasboardresponsevo.getNonCommunicating());
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/homedashboard/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HomeResponseVO homedashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getHomeDashboardDetails(roleid, id);
	}
	
	@RequestMapping(value = "/graph/{year}/{month}/{CRNNumber}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GraphResponseVO homedashboarddetails(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("CRNNumber") String CRNNumber) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getGraphDashboardDetails(year, month, CRNNumber);
	}
	
	@RequestMapping(value = "/filterdashboard/{roleid}/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody DashboardResponseVO filterdashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @RequestBody FilterVO filtervo) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();

		dasboardresponsevo.setData(dashboarddao.getFilterDashboarddetails(roleid, id, filtervo));
		dasboardresponsevo.setTotal(dasboardresponsevo.getData().size());
		dasboardresponsevo.setNonCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().get(dasboardresponsevo.getData().size()-1).getNonCommunicating());
		dasboardresponsevo.setCommunicating(dasboardresponsevo.getData().size()-dasboardresponsevo.getNonCommunicating());
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/inputdata", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO postDashboardDetails(HttpEntity<String> httpEntity) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();
		
		Gson gson = new Gson();
		
		String json = httpEntity.getBody();
		
		TataRequestVO tataRequestVO = gson.fromJson(json, TataRequestVO.class);
		
		try {
			responsevo = dashboarddao.postDashboarddetails(tataRequestVO);
		} catch (Exception ex) {
			logger.error("This is Error message", ex);
			ex.printStackTrace();
		}
		return responsevo;
	}
	
	
	@RequestMapping(value = "/GET", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseVO getDashboardDetails() {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();

		try {
			responsevo = dashboarddao.getGETDashboarddetails();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responsevo;
	}
	
	
	@RequestMapping(value = "/datafrommobile", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO datafrommobile(@RequestBody DashboardRequestVO dashboardRequestVO) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();

		try {
			responsevo.setResult(dashboarddao.insertdashboard(dashboardRequestVO));
			responsevo.setMessage("Data Inserted Successfully");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("Data Insertion Failed");
		}
		return responsevo;
	}
	
}
