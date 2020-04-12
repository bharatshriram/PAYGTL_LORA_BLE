package com.hanbit.PAYGTL_LORA_BLE.constants;

public class SQLConstants {
	
	public static final String usernameandpassword = "SELECT user_id,user_password,com_id FROM mdc_user";
	public static final String blockdetails = "SELECT tc.com_name,tb.name,tb.block_id FROM community tc,block tb WHERE tc.com_id=? ORDER BY tb.block_id"; 
}
