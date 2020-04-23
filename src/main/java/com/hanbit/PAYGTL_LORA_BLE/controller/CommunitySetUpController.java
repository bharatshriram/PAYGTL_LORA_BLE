/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hanbit.PAYGTL_LORA_BLE.bo.CommunitySetUpBO;
import com.hanbit.PAYGTL_LORA_BLE.dao.CommunitySetUpDAO;
import com.hanbit.PAYGTL_LORA_BLE.dao.ExtraMethodsDAO;
import com.hanbit.PAYGTL_LORA_BLE.dao.LoginDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.BlockRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.CommunityRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.CustomerRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.RestCallVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TariffRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.BlockResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.CommunityResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.CustomerResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.ResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TariffResponseVO;
import com.hanbit.PAYGTL_LORA_BLE.response.vo.TataResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
@Controller
public class CommunitySetUpController {

	/* Community */

	@RequestMapping(value = "/community/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CommunityResponseVO communitydetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		CommunityResponseVO communityResponsevo = new CommunityResponseVO();
		
		communityResponsevo.setData(communitysetupdao.getCommunitydetails(roleid, id));

		return communityResponsevo;
	}

	@RequestMapping(value = "/community/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addcommunity(@RequestBody CommunityRequestVO communityvo)
			throws ClassNotFoundException, SQLException, BusinessException {

		String result = "Failure";
		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();

		try {
			result = communitysetupbo.addcommunity(communityvo);

		} catch (BusinessException e) {
			result = "Failure";
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}

	@RequestMapping(value = "/community/edit/{communityID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editcommunity(@PathVariable("communityID") int communityid,
			@RequestBody CommunityRequestVO communityvo) throws ClassNotFoundException,
			BusinessException, SQLException {

		String result = "Failure";
		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();

		communityvo.setCommunityID(communityid);

		try {
			result = communitysetupbo.editcommunity(communityvo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}

	/* Block */

	@RequestMapping(value = "/block/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	BlockResponseVO blockdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		BlockResponseVO blockresponsevo = new BlockResponseVO();

		blockresponsevo.setData(communitysetupdao.getBlockdetails(roleid, id));

		return blockresponsevo;
	}

	@RequestMapping(value = "/block/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addblock(@RequestBody BlockRequestVO blockvo) throws ClassNotFoundException,
			BusinessException, SQLException {

		String result = "Failure";
		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();

		try {
			
			result = communitysetupbo.addblock(blockvo);
		
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}

	@RequestMapping(value = "/block/edit/{blockID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editblock(@PathVariable("blockID") int blockid,
			@RequestBody BlockRequestVO blockvo) throws ClassNotFoundException,
			BusinessException, SQLException {

		String result = "Failure";
		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();

		blockvo.setBlockID(blockid);
		try {
			result = communitysetupbo.editblock(blockvo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}

	@RequestMapping(value = "/block/delete/{blockID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deleteblock(@PathVariable("blockID") int blockid)
			throws BusinessException, SQLException {

		String result = "Failure";
		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();

		try{

		result = communitysetupbo.deleteblock(blockid);
		
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}
		
		responsevo.setResult(result);

		return responsevo;
	}

	/* Customer */

	@RequestMapping(value = "/customer/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CustomerResponseVO customerdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		CustomerResponseVO customerresponsevo = new CustomerResponseVO();

		customerresponsevo.setData(communitysetupdao.getCustomerdetails(roleid, id));

		return customerresponsevo;
	}

	@RequestMapping(value = "/customer/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addcustomer(@RequestBody CustomerRequestVO customervo) throws ClassNotFoundException,
			BusinessException, SQLException {

		String result = "Failure";
		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();

		try {
			result = communitysetupbo.addcustomer(customervo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}

	@RequestMapping(value = "/customer/edit/{CRNNumber}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO editcustomer(@PathVariable("CRNNumber") String CRNNumber,
			@RequestBody CustomerRequestVO customervo) throws ClassNotFoundException,
			BusinessException, SQLException {

		String result = "Failure";
		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();

		customervo.setCRNNumber(CRNNumber);

		try {
			result = communitysetupbo.editcustomer(customervo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}

	@RequestMapping(value = "/customer/delete/{CRNNumber}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletecustomer(@PathVariable("CRNNumber") String CRNNumber)
			throws ClassNotFoundException, BusinessException, SQLException {

		String result = "Failure";
		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		CustomerRequestVO customervo = new CustomerRequestVO();
		ResponseVO responsevo = new ResponseVO();

		customervo.setCRNNumber(CRNNumber);
		
		try{
		
			result = communitysetupbo.deletecustomer(customervo);

		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}
		
		responsevo.setResult(result);

		return responsevo;
	}
	
	@RequestMapping(value = "/customerupdatesrequest/{blockid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	CustomerResponseVO customerupdatesrequest(@PathVariable("blockid") int blockid) throws SQLException {

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		CustomerResponseVO customerresponsevo = new CustomerResponseVO();

		customerresponsevo.setData(communitysetupdao.getCustomerUpdateRequestdetails(blockid));

		return customerresponsevo;
	}
	
	@RequestMapping(value = "/approverequest/{requestID}/{action}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO approverequest(@PathVariable("requestID") int requestid, @PathVariable("action") int action) throws ClassNotFoundException,
			BusinessException, SQLException {

		String result = "Failure";
		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		ResponseVO responsevo = new ResponseVO();

		try {
			result = communitysetupdao.approverequest(requestid, action);
		} catch (Exception e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		responsevo.setResult(result);

		return responsevo;
	}

	
	/* Tariff */

	@RequestMapping(value = "/tariff", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	TariffResponseVO tariffdetails() throws SQLException {

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		TariffResponseVO tariffresponsevo = new TariffResponseVO();

		tariffresponsevo.setData(communitysetupdao.getTariffdetails());

		return tariffresponsevo;
	}

	@RequestMapping(value = "/tariff/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addtariff(@RequestBody TariffRequestVO tariffvo) throws ClassNotFoundException,
			SQLException, BusinessException {

		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();
		
		try {
			 responsevo.setResult(communitysetupbo.addtariff(tariffvo));
			
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/tariff/edit/{tariffID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO edittariff(@RequestBody TariffRequestVO tariffvo, @PathVariable("tariffID") int tariffID) throws ClassNotFoundException,
			SQLException, BusinessException {

		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();
		tariffvo.setTariffID(tariffID);
		
		try {
			 responsevo.setResult(communitysetupbo.edittariff(tariffvo));
			
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/tariff/delete/{tariffID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletetariff(@PathVariable("tariffID") int tariffID) throws ClassNotFoundException,
			SQLException, BusinessException {

		CommunitySetUpBO communitysetupbo = new CommunitySetUpBO();
		ResponseVO responsevo = new ResponseVO();
		
		try {
			 responsevo.setResult(communitysetupbo.deletetariff(tariffID));
			
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}

		return responsevo;
	}
	
	/*@RequestMapping(value="/dowhile/{i}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String dowhile(@PathVariable("i") String userid) throws SQLException {
		
		boolean flag;
		int i = 0;
		LoginDAO logindao = new LoginDAO();
		do {
			System.out.println(i);
			System.out.println(userid);
			
			flag = logindao.checkuserid(userid);
			System.out.println("flag in while---"+flag);
			i++;
			if(flag) {
				userid = userid+i;	
			}
			
		} while (flag == true);
		
		System.out.println("i value---"+i);
		if(i<=1) {
			System.out.println("in if");
		}else {
			System.out.println("in else");
		}
		
		i = 0;
		String conv = userid.replace("\"", "");
		return conv;
	}*/
	
/*	@RequestMapping(value="/dowhile", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody String dowhile(@RequestBody String json) throws SQLException, JSONException {
		
		Gson gson = new Gson();
		
		RequestVO requestvo = new RequestVO();
		
		JSONObject jsonObj = new JSONObject(json);
		
		requestvo = gson.fromJson(jsonObj.getJSONObject("m2m:cin").getString("con"), RequestVO.class);
		
		System.out.println(requestvo.getPayloads_ul().getDataFrame());
		
		return null;
	}*/
	
	/*@RequestMapping(value="/dowhile/{meterid}/{id}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<TataResponseVO> dowhile(@PathVariable("meterid") String meterid, @PathVariable("id") String id) throws SQLException, IOException {
		
		ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
		RestCallVO restcallvo = new RestCallVO();
		
		restcallvo.setMeterID(meterid.toLowerCase());
		restcallvo.setDataFrame("ChgASBECDAAjQ8gAAEEgAABBoAAAQkgAABc=");
		restcallvo.setUrlExtension("/payloads/dl/");
		restcallvo.setTataTransactionID(id);
		
		ResponseEntity<TataResponseVO> restcallresponse = extramethodsdao.restcallget(restcallvo);
		
		return restcallresponse;
	}*/
	
}
