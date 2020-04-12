/**
 * 
 */



$(function() {
	$.getJSON("/PAYGTL_LORA_BLE/communities/" + sessionStorage.getItem("roleID") + "/"
			+ sessionStorage.getItem("ID"), function(data) {
		var Options = "";
		$.each(data.dropDownCommunities, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#selectcommunityName').append(Options);
		$("#selectcommunityName").material_select();
	});
});
