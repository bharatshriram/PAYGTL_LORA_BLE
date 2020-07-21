/**
 * 
 */



$(function() {
	
	if(sessionStorage.getItem("roleID") == 2 || sessionStorage.getItem("roleID") == 5){
		$("#selectHouseBasedonBlock").append("<option>" + "Select CRN" + "</option>");
		
		$.getJSON("/PAYGTL_LORA_BLE/customers/" + sessionStorage.getItem("roleID") + "/"
				+ sessionStorage.getItem("ID")+ "/" + sessionStorage.getItem("ID"), function(data) {
			var Options = "";
			$.each(data.dropDownHouses, function(key, value) {
				Options = Options + "<option value='" + key + "'>" + key
						+ "</option>";
			});
			$('#selectHouseBasedonBlock').append(Options);
			//$("#selectBlockBasedonCommunity").material_select();
		});
	}
	
	
	$.getJSON("/PAYGTL_LORA_BLE/communities/" + sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID"), function(data) {
		var Options = "<option value='-1'>Select  Community</option>";
		
		$.each(data.dropDownCommunities, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectcommunityName').append(Options);
		$('#filterselectcommunityName').append(Options);
		//$("#selectcommunityName").material_select();
	});
	
	
	$.getJSON("/PAYGTL_LORA_BLE/tariffs", function(data) {
		var Options = "<option value='-1'>Select  Tariff</option>";
		$.each(data.dropDownTariffs, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectTariffName').append(Options);
		$('#selectTariffNameEdit').append(Options);
		//$("#selectcommunityName").material_select();
	});
});



function showBlockbyCommunity(communityId){
	$("#selectBlockBasedonCommunity").find('option').remove();

	$("#selectBlockBasedonCommunity").append("<option>" + "Select Block" + "</option>");
	
	$("#filterselectBlockBasedonCommunity").find('option').remove();

	$("#filterselectBlockBasedonCommunity").append("<option>" + "Select Block" + "</option>");
	
	$("#selectHouseBasedonBlock").find('option').remove();

	$("#selectHouseBasedonBlock").append("<option>" + "Select CRN" + "</option>");
	
	$("#AMR_topup").find('option').remove();

	$("#AMR_topup").val("");
	
	$.getJSON("/PAYGTL_LORA_BLE/blocks/"+ sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID")+ "/" + communityId, function(data) {
		var Options = "";
		$.each(data.dropDownBlocks, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectBlockBasedonCommunity').append(Options);
		$('#filterselectBlockBasedonCommunity').append(Options);
		//$("#filterselectBlockBasedonCommunity").material_select();
	});
}



function showCustomerbyBlock(blockId){
	//alert("@@-->"+communityId);
	$("#selectHouseBasedonBlock").find('option').remove();

	$("#selectHouseBasedonBlock").append("<option>" + "Select CRN" + "</option>");
	
	$.getJSON("/PAYGTL_LORA_BLE/customers/" + sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID")+ "/" + blockId, function(data) {
		var Options = "";
		$.each(data.dropDownHouses, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + key
					+ "</option>";
		});
		$('#selectHouseBasedonBlock').append(Options);
		//$("#selectBlockBasedonCommunity").material_select();
	});
}

function showTopupDetails(customerId){
	
	$.getJSON("/PAYGTL_LORA_BLE/topupdetails/" + customerId, function(data) {
		//var Options = "";
		$("#AMR_topup").val(data.topupdetails.meterID).trigger("change");
		$("#formAMR_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#currentBalance_topup").val(data.topupdetails.currentBalance).trigger("change");
		$("#formcurrentBalance_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#dateTime_topup").val(data.topupdetails.IoTTimeStamp).trigger("change");
		$("#formdateTime_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#unit_topup").val(data.topupdetails.tariff).trigger("change");
		$("#formunit_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#emergency_topup").val(data.topupdetails.emergencyCredit).trigger("change");
		$("#formemergency_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#alarm_topup").val(data.topupdetails.alarmCredit).trigger("change");
		$("#formalarm_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#reconnection_topup").val(data.topupdetails.reconnectionCharges).trigger("change");
		$("#formreconnection_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#fixed_topup").val(data.topupdetails.fixedCharges).trigger("change");
		$("#formfixed_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
		$("#month_topup").val(data.topupdetails.noOfMonths).trigger("change");
		$("#formmonth_topup").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled")
		
	});
	
}


function showCommunitybyTypeuser(id){
	
	if(id=="Super"){
		$("#usercommunityId,#userblockId").hide();
	$("#formcomunityName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");
	$("#formblockName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");
	}else if(id =="Admin")
		{
		$("#formcomunityName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");
		$("#formblockName").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");
		$("#usercommunityId").show();
		$("#userblockId").show();
		
		}	
		
}





function showFieldsBasedONCommand(id){
	
	if(id=="6"){
		$("#confdefaultReading").show();
		$("#conftariff").hide();
		/*$("#formtariff").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");*/
		//$("#formdefaultReading").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");
		
		}else if(id =="10")
		{
		$("#conftariff").show();
		$("#confdefaultReading").hide();		
		/*$("#formtariff").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");
		$("#formdefaultReading").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");*/
		}	else {
			$("#conftariff").hide();
			$("#confdefaultReading").hide();
			$("#formtariff").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");
			$("#formdefaultReading").addClass("input-group form-group has-feedback has-success bmd-form-group is-filled");
		}
		
}
