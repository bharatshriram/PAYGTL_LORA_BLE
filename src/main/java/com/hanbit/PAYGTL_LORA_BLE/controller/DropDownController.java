/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.dao.DropDownDAO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
@Controller
public class DropDownController {
	
	Gson gson = new Gson();
	
	@RequestMapping(value = "/communities/{roleID}/{id}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallcommunities(@PathVariable("roleID") int roleid, @PathVariable("id") String id) {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		ResponseVO responsevo = new ResponseVO();
		
		responsevo.setDropDownCommunities(dropdowndao.getallcommunities(roleid, id));

		return responsevo;
	}
	
	@RequestMapping(value = "/blocks/{roleID}/{id}/{communityID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallblocks(@PathVariable("roleID") int roleid, @PathVariable("id") String id, @PathVariable ("communityID") int communityID) {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		ResponseVO responsevo = new ResponseVO();
		
		responsevo.setDropDownBlocks(dropdowndao.getallblocks(communityID, roleid, id));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/customers/{roleID}/{id}/{blockID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallhouses(@PathVariable("roleID") int roleid, @PathVariable("id") int id, @PathVariable ("blockID") int blockID) {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		ResponseVO responsevo = new ResponseVO();
		
		responsevo.setDropDownHouses(dropdowndao.getallhouses(blockID, roleid, id));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/topupdetails/{CRNNumber}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO gettopupdetails(@PathVariable ("CRNNumber") String CRNNumber) throws SQLException {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		ResponseVO responsevo = new ResponseVO();
		
		responsevo.setTopupdetails(dropdowndao.gettopupdetails(CRNNumber));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/tariffs",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseVO getalltariffs() throws SQLException {
		
		DropDownDAO dropdowndao = new DropDownDAO();
		ResponseVO responsevo = new ResponseVO();
		
		responsevo.setDropDownTariffs(dropdowndao.getalltariffs());

		return responsevo;
	}

}
