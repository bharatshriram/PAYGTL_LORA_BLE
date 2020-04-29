/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.bo;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hanbit.PAYGTL_LORA_BLE.dao.CommunitySetUpDAO;
import com.hanbit.PAYGTL_LORA_BLE.exceptions.BusinessException;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.BlockRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.CommunityRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.CustomerRequestVO;
import com.hanbit.PAYGTL_LORA_BLE.request.vo.TariffRequestVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class CommunitySetUpBO {

	/* Community */

	public String addcommunity(CommunityRequestVO communityvo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();

		String result = communitysetupdao.addcommunity(communityvo);

		return result;
	}

	public String editcommunity(CommunityRequestVO communityvo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();

		String result = communitysetupdao.editcommunity(communityvo);

		return result;
	}

	/* Block */

	public String addblock(BlockRequestVO blockvo) throws SQLException,
			BusinessException {
		// TODO Auto-generated method stub

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		
		if (blockvo.getCommunityID()==0
				|| blockvo.getBlockName().isEmpty()) {
			throw new BusinessException("ALL fields are Mandatory");
		}

		Pattern pattern = Pattern.compile("[A-Za-z0-9]");
		Matcher matcher = pattern.matcher(blockvo.getBlockName());

		if (!matcher.find()) {
			throw new BusinessException("Block Name Must be AlphaNumeric Only");
		}

				String result = communitysetupdao.addblock(blockvo);

		return result;
	}

	public String editblock(BlockRequestVO blockvo) throws SQLException,
			BusinessException {
		// TODO Auto-generated method stub

		boolean blockname = false;

		Pattern pattern = Pattern.compile("[A-Za-z0-9]");
		Matcher matcher = pattern.matcher(blockvo.getBlockName());
		if (!matcher.find()) {
			blockname = true;
		}

		if (blockname == true) {

			throw new BusinessException("Block Name Must be AlphaNumeric Only");

		}

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();

		String result = communitysetupdao.editblock(blockvo);

		return result;
	}

	public String deleteblock(int blockID) throws BusinessException {
		// TODO Auto-generated method stub

		String result = "";

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();

		try {
			boolean flag = false;

			flag = communitysetupdao.checkifhousesexist(blockID);

			if (flag) {
				throw new BusinessException(
						"DELETE ALL HOUSES/CUSTOMERS IN THE BLOCK BEFORE DELETING THE BLOCK");
			}

			result = communitysetupdao.deleteblock(blockID);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/* Customer */

	public String addcustomer(CustomerRequestVO customervo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();

		if (customervo.getCommunityID()==0
				|| customervo.getBlockID()==0
				|| customervo.getHouseNumber().isEmpty()
				|| customervo.getFirstName().isEmpty()
				|| customervo.getLastName().isEmpty()
				|| customervo.getEmail().isEmpty()
				|| customervo.getMobileNumber().isEmpty()
				|| customervo.getTariffID()==0) {
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}

		if (checkName(customervo.getFirstName()) == true || checkName(customervo.getLastName()) == true) {
			throw new BusinessException("NAME CAN CONTAIN ONLY ALPHABETS");
		}

		if (!checkEmailID(customervo.getEmail())) {
			throw new BusinessException("INVALID EMAIL ID");
		}

		if (!checkMobileNo(customervo.getMobileNumber())) {
			throw new BusinessException(
					"MOBILE NUMBER CAN CONTAIN ONLY NUMERIC VALUES OF EXACTLY 10 DIGITS");
		}
		
		if(communitysetupdao.checkcustomer(customervo.getFirstName(), customervo.getLastName(), customervo.getCRNNumber())) {
			throw new BusinessException("CUSTOMER/CRNNumber ALREADY REGISTERED");
		}

		return communitysetupdao.addcustomer(customervo);
	}

	public String editcustomer(CustomerRequestVO customervo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		
		if(communitysetupdao.checkpendingrequest(customervo.getCRNNumber())) {
			throw new BusinessException("PREVIOUS REQUEST IS PENDING FOR APPROVAL");
		}

		if (customervo.getHouseNumber().isEmpty()
				|| customervo.getFirstName().isEmpty()
				|| customervo.getEmail().isEmpty()
				|| customervo.getMobileNumber().isEmpty()
				|| customervo.getCRNNumber().isEmpty()) {
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}

		if (checkName(customervo.getFirstName()) == true) {
			throw new BusinessException("NAME CAN CONTAIN ONLY ALPHABETS");
		}

		if (!checkEmailID(customervo.getEmail())) {
			throw new BusinessException("INVALID EMAIL ID");
		}

		if (!checkMobileNo(customervo.getMobileNumber())) {
			throw new BusinessException(
					"MOBILE NUMBER CAN CONTAIN ONLY NUMERIC VALUES OF EXACTLY 10 DIGITS");
		}
		
		return communitysetupdao.editcustomer(customervo);
	}

	public String deletecustomer(CustomerRequestVO customervo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		String result = "";

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();

		try {

			result = communitysetupdao.deletecustomer(customervo);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	/* Tariff */

	public String addtariff(TariffRequestVO tariffvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		
		if(tariffvo.getTariff()==0 || tariffvo.getAlarmCredit()==0 || tariffvo.getEmergencyCredit()==0 || tariffvo.getFixedCharges()==0){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		if(communitysetupdao.checktariffamount(tariffvo.getTariff())) {
			throw new BusinessException("TARIFF AMOUNT ALREADY EXISTS");
		}

		return communitysetupdao.addtariff(tariffvo);
	}
	
	public String edittariff(TariffRequestVO tariffvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		
		if(tariffvo.getTariff()==0 || tariffvo.getAlarmCredit()==0 || tariffvo.getEmergencyCredit()==0 || tariffvo.getFixedCharges()==0){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		return communitysetupdao.edittariff(tariffvo);
	}
	
	public String deletetariff(int tariffID) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();
		
		if(communitysetupdao.checktariffIsSetToCustomers(tariffID)) {
			throw new BusinessException("TARIFF CANNOT BE DELETED AS IT IS ASSIGNED TO CUSTOMERS");
		}

		return communitysetupdao.deletetariff(tariffID);
	}

	/* Validations */

	private boolean checkName(String customerName) {
		// TODO Auto-generated method stub
		boolean result = false;

		Pattern pattern = Pattern.compile("[A-Za-z]");
		Matcher matcher = pattern.matcher(customerName);
		if (!matcher.find()) {
			result = true;
		}
		return result;
	}

	private boolean checkMobileNo(String contactNumber) {
		// TODO Auto-generated method stub
		boolean result = false;
		Pattern pattern = Pattern.compile("[0-9-]\\d{10}");
		Matcher matcher = pattern.matcher(contactNumber);
		
		if (!matcher.find()) {
			result = true;
		}
		return result;
	}

	private boolean checkEmailID(String emailId) {
		// TODO Auto-generated method stub

		boolean result = false;

		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@ [A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(emailId);

		if (!matcher.find()) {
			result = true;
		}

		return result;

	}

}
