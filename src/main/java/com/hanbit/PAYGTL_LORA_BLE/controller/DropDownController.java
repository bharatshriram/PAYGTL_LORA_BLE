/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.dao.DropDownDAO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TopupDetailsResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
@Controller
public class DropDownController {
	
	Gson gson = new Gson();
	
	@RequestMapping(value = "/communities/{roleID}/{id}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallcommunities(@PathVariable("roleID") int roleid, @PathVariable("id") int id) {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		ResponseVO responsevo = new ResponseVO();
		
		responsevo.setDropDownCommunities(dropdowndao.getallcommunities(roleid, id));

		return responsevo;
	}
	
	@RequestMapping(value = "/blocks/{communityID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallblocks(@PathVariable ("communityID") int communityID) {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		ResponseVO responsevo = new ResponseVO();
		
		responsevo.setDropDownBlocks(dropdowndao.getallblocks(communityID));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/customers/{blockID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallhouses(@PathVariable ("blockID") int blockID) {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		ResponseVO responsevo = new ResponseVO();
		
		responsevo.setDropDownHouses(dropdowndao.getallhouses(blockID));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/topupdetails/{house}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody String gettopupdetails(@PathVariable ("house") String house) throws SQLException {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		List<TopupDetailsResponseVO> topupdetailslist = new ArrayList<TopupDetailsResponseVO>();
		ResponseVO responsevo = new ResponseVO();
		
		topupdetailslist = dropdowndao.gettopupdetails(house);
		responsevo.setTopupdetails(topupdetailslist);
		
		String meterids = gson.toJson(responsevo);
		
		return meterids;
	}

}
