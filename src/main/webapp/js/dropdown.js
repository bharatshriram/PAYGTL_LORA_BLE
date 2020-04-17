/**
 * 
 */



$(function() {
	$.getJSON("/PAYGTL_LORA_BLE/communities/" + sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID"), function(data) {
		var Options = "<option value='-1'>Select  Community</option>";
		$.each(data.dropDownCommunities, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectcommunityName').append(Options);
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
	
	alert("@@-->"+communityId);
	
	
	$("#selectBlockBasedonCommunity").find('option').remove();

	$("#selectBlockBasedonCommunity").append("<option>" + "Select Block" + "</option>");
	
	$.getJSON("/PAYGTL_LORA_BLE/blocks/" + communityId, function(data) {
		var Options = "";
		$.each(data.dropDownBlocks, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectBlockBasedonCommunity').append(Options);
		//$("#selectBlockBasedonCommunity").material_select();
	});
	
	
}