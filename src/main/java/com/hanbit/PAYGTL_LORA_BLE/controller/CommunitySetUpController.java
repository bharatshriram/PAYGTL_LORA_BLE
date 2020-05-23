/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.PAYGTL_LORA_BLE.bo.CommunitySetUpBO;
import com.hanbit.PAYGTL_LORA_BLE.dao.CommunitySetUpDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.BlockRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.CommunityRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.CustomerRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TariffRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.BlockResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.CommunityResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.CustomerResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TariffResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
@Controller
public class CommunitySetUpController {
	
	CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
	CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
	
	/* Community */

	@RequestMapping(value = "/community/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CommunityResponseVO communitydetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		CommunityResponseVO communityResponsevo = new CommunityResponseVO();
		
		communityResponsevo.setData(communitysetupdao.getCommunitydetails(roleid, id));

		return communityResponsevo;
	}

	@RequestMapping(value = "/community/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addcommunity(@RequestBody CommunityRequestVO communityvo)
			throws ClassNotFoundException, SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = communitysetupbo.addcommunity(communityvo);

		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/community/edit/{communityID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editcommunity(@PathVariable("communityID") int communityid,
			@RequestBody CommunityRequestVO communityvo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		communityvo.setCommunityID(communityid);

		try {
			responsevo = communitysetupbo.editcommunity(communityvo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	/* Block */

	@RequestMapping(value = "/block/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	BlockResponseVO blockdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		BlockResponseVO blockresponsevo = new BlockResponseVO();

		blockresponsevo.setData(communitysetupdao.getBlockdetails(roleid, id));

		return blockresponsevo;
	}

	@RequestMapping(value = "/block/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addblock(@RequestBody BlockRequestVO blockvo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			
			responsevo = communitysetupbo.addblock(blockvo);
		
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/block/edit/{blockID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editblock(@PathVariable("blockID") int blockid,
			@RequestBody BlockRequestVO blockvo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		blockvo.setBlockID(blockid);
		try {
			responsevo = communitysetupbo.editblock(blockvo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/block/delete/{blockID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deleteblock(@PathVariable("blockID") int blockid)
			throws BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try{

			responsevo = communitysetupbo.deleteblock(blockid);
		
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}
		
		return responsevo;
	}

	/* Customer */

	@RequestMapping(value = "/customer/{roleid}/{id}/{filterCid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CustomerResponseVO customerdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filterCid") int filterCid) throws SQLException {

		CustomerResponseVO customerresponsevo = new CustomerResponseVO();

		customerresponsevo.setData(communitysetupdao.getCustomerdetails(roleid, id, filterCid));

		return customerresponsevo;
	}

	@RequestMapping(value = "/customer/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addcustomer(@RequestBody CustomerRequestVO customervo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = communitysetupbo.addcustomer(customervo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/customer/edit/{CRNNumber}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editcustomer(@PathVariable("CRNNumber") String CRNNumber,
			@RequestBody CustomerRequestVO customervo) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		customervo.setCRNNumber(CRNNumber);

		try {
			responsevo = communitysetupbo.editcustomer(customervo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/customer/delete/{CRNNumber}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletecustomer(@PathVariable("CRNNumber") String CRNNumber)
			throws ClassNotFoundException, BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		CustomerRequestVO customervo = new CustomerRequestVO();

		customervo.setCRNNumber(CRNNumber);
		
		try{
		
			responsevo = communitysetupbo.deletecustomer(customervo);

		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}
		
		return responsevo;
	}
	
	@RequestMapping(value = "/customerupdatesrequest/{blockid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CustomerResponseVO customerupdatesrequest(@PathVariable("blockid") int blockid) throws SQLException {

		CustomerResponseVO customerresponsevo = new CustomerResponseVO();

		customerresponsevo.setData(communitysetupdao.getCustomerUpdateRequestdetails(blockid));

		return customerresponsevo;
	}
	
	@RequestMapping(value = "/approverequest/{requestID}/{action}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO approverequest(@PathVariable("requestID") int requestid, @PathVariable("action") int action) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = communitysetupdao.approverequest(requestid, action);
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	
	/* Tariff */

	@RequestMapping(value = "/tariff", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	TariffResponseVO tariffdetails() throws SQLException {

		TariffResponseVO tariffresponsevo = new TariffResponseVO();

		tariffresponsevo.setData(communitysetupdao.getTariffdetails());

		return tariffresponsevo;
	}

	@RequestMapping(value = "/tariff/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addtariff(@RequestBody TariffRequestVO tariffvo) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.addtariff(tariffvo);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/tariff/edit/{tariffID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO edittariff(@RequestBody TariffRequestVO tariffvo, @PathVariable("tariffID") int tariffID) throws ClassNotFoundException,
			SQLException, BusinessException {

		tariffvo.setTariffID(tariffID);
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.edittariff(tariffvo);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/tariff/delete/{tariffID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletetariff(@PathVariable("tariffID") int tariffID) throws ClassNotFoundException,
			SQLException, BusinessException {
		ResponseVO responsevo = new ResponseVO();
		try {
			 responsevo = communitysetupbo.deletetariff(tariffID);
			
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
}
