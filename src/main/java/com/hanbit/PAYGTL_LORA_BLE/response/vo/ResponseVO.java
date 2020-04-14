/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.response.vo;

import java.util.HashMap;
import java.util.List;

/**
 * @author VmL
 *
 */
public class ResponseVO {
	
	private List<CommunityResponseVO> communities;
	private List<BlockResponseVO> blocks;
	private List<CustomerResponseVO> customers;
	private List<DashboardResponseVO> dashboard;
	private List<StatusResponseVO> status;
	private List<FixedChargesResponseVO> fixedcharges;
	private List<ConfigurationResponseVO> configurations;
	private List<FinancialReportsResponseVO> financialreports;
	private List<UserConsumptionReportsResponseVO> userconsumptionreports;
	private List<TopUpSummaryResponseVO> topupsummary;
	private List<ValveReportsResponseVO> valvereports;
	private List<AlarmsResponseVO> alarms;
	private List<TariffResponseVO> tariffs;
	private List<UserManagementResponseVO> users;
	private List<AlertResponseVO> alerts;
	private List<HolidayResponseVO> holidays;
	private HashMap<Integer, String> dropDownCommunities;
	private HashMap<Integer, String> dropDownBlocks;
	private HashMap<Integer, String> dropDownHouses;
	private HashMap<Integer, String> dropDownTariffs;
	private TopupDetailsResponseVO topupdetails;
	private String result;
	private String Message;
	private UserDetails userDetails;
	
	public List<ValveReportsResponseVO> getValvereports() {
		return valvereports;
	}
	public void setValvereports(List<ValveReportsResponseVO> valvereports) {
		this.valvereports = valvereports;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String Message) {
		this.Message = Message;
	}
	public TopupDetailsResponseVO getTopupdetails() {
		return topupdetails;
	}
	public void setTopupdetails(TopupDetailsResponseVO topupdetails) {
		this.topupdetails = topupdetails;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<FinancialReportsResponseVO> getFinancialreports() {
		return financialreports;
	}
	public void setFinancialreports(
			List<FinancialReportsResponseVO> financialreports) {
		this.financialreports = financialreports;
	}
	public List<UserConsumptionReportsResponseVO> getUserconsumptionreports() {
		return userconsumptionreports;
	}
	public void setUserconsumptionreports(
			List<UserConsumptionReportsResponseVO> userconsumptionreports) {
		this.userconsumptionreports = userconsumptionreports;
	}
	public List<TopUpSummaryResponseVO> getTopupsummary() {
		return topupsummary;
	}
	public void setTopupsummary(List<TopUpSummaryResponseVO> topupsummary) {
		this.topupsummary = topupsummary;
	}
	public List<CommunityResponseVO> getCommunities() {
		return communities;
	}
	public void setCommunities(List<CommunityResponseVO> communities) {
		this.communities = communities;
	}
	public List<BlockResponseVO> getBlocks() {
		return blocks;
	}
	public void setBlocks(List<BlockResponseVO> blocks) {
		this.blocks = blocks;
	}
	public List<CustomerResponseVO> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerResponseVO> customers) {
		this.customers = customers;
	}
	public List<DashboardResponseVO> getDashboard() {
		return dashboard;
	}
	public void setDashboard(List<DashboardResponseVO> dashboard) {
		this.dashboard = dashboard;
	}
	public List<StatusResponseVO> getStatus() {
		return status;
	}
	public void setStatus(List<StatusResponseVO> status) {
		this.status = status;
	}
	public List<FixedChargesResponseVO> getFixedcharges() {
		return fixedcharges;
	}
	public void setFixedcharges(List<FixedChargesResponseVO> fixedcharges) {
		this.fixedcharges = fixedcharges;
	}
	public List<ConfigurationResponseVO> getConfigurations() {
		return configurations;
	}
	public void setConfigurations(List<ConfigurationResponseVO> configurations) {
		this.configurations = configurations;
	}
	public List<AlarmsResponseVO> getAlarms() {
		return alarms;
	}
	public void setAlarms(List<AlarmsResponseVO> alarms) {
		this.alarms = alarms;
	}
	public List<TariffResponseVO> getTariffs() {
		return tariffs;
	}
	public void setTariffs(List<TariffResponseVO> tariffs) {
		this.tariffs = tariffs;
	}
	public List<UserManagementResponseVO> getUsers() {
		return users;
	}
	public void setUsers(List<UserManagementResponseVO> users) {
		this.users = users;
	}
	public List<AlertResponseVO> getAlerts() {
		return alerts;
	}
	public void setAlerts(List<AlertResponseVO> alerts) {
		this.alerts = alerts;
	}
	public List<HolidayResponseVO> getHolidays() {
		return holidays;
	}
	public void setHolidays(List<HolidayResponseVO> holidays) {
		this.holidays = holidays;
	}
	public HashMap<Integer, String> getDropDownCommunities() {
		return dropDownCommunities;
	}
	public void setDropDownCommunities(HashMap<Integer, String> dropDownCommunities) {
		this.dropDownCommunities = dropDownCommunities;
	}
	public HashMap<Integer, String> getDropDownBlocks() {
		return dropDownBlocks;
	}
	public void setDropDownBlocks(HashMap<Integer, String> dropDownBlocks) {
		this.dropDownBlocks = dropDownBlocks;
	}
	public HashMap<Integer, String> getDropDownHouses() {
		return dropDownHouses;
	}
	public void setDropDownHouses(HashMap<Integer, String> dropDownHouses) {
		this.dropDownHouses = dropDownHouses;
	}
	public HashMap<Integer, String> getDropDownTariffs() {
		return dropDownTariffs;
	}
	public void setDropDownTariffs(HashMap<Integer, String> dropDownTariffs) {
		this.dropDownTariffs = dropDownTariffs;
	}
}
