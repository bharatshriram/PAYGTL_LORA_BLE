package com.hanbit.PAYGTL_LORA_BLE.constants;
/**
 * @author K Vimal Kumar
 *
 */
public class ExtraConstants {
	
	public static final String key1 = "Bar12345Bar12345"; // 128 bit key
	public static final String key2 = "ThisIsASecretKet";
	public static final String ApplicationURL = "http://183.82.122.196:8080/PAYGTL_LORA_BLE";
	public static final String LocalURL = "http://localhost:8084/PAYGTL_LORA_BLE";
	public static final String HANBITIMAGEURL=LocalURL+"/common/images/1-hanbit.png";
//	public static final String HANBITIMAGEURL= ApplicationURL+"/common/images/1-hanbit.png";
	public static final String CLIENTIMAGEURL= LocalURL+"/common/images/raychem.png";
//	public static final String CLIENTIMAGEURL=ApplicationURL+"/common/images/raychem.png";
	public static final String fromEmail = "alert@hanbitsolutions.com";
	public static final String fromEmailPassword = "hanbit@123"; 
	public static final String TataGatewayURL =  "https://data.iot.tatacommunications.com/rest/nodes/";
	public static final String ContentType = "text/plain";
//	public static final String QueryParams = "?port=2&fcnt=12&confirmed=true&mode=enqueue_on_busy&data_format=base64";
	public static final String QueryParams = "?port=2&confirmed=true&mode=enqueue_on_busy&data_format=base64";
	public static final String XM2MOrigin = "CF82BFF84-e6de215b";
	public static final String XM2MRI = "1100099992222";
	public static final String Accept = "application/vnd.onem2m-res+json";
	public static final String CacheControl = "no-cache";
	public static final String TataUserName = "nunnahareesh";
	public static final String TataPassword = "Hanbit@2020";
	public static final String SenderID = "&senderid="+"BLKSMS&msg=";
	public static final String SMSAPI = "https://sms.textmysms.com/app/smsapi/index.php?key=55EDE4350AF3B7&campaign=0&routeid=13&type=text&contacts=";
	public static final String RZPKeyID = "rzp_live_pB0wVRoGXV6NFM";
	public static final String RZPKeySecret = "8kzrJbkhOv8jddM8sksi7h9p";
	public static final String RZPBasicUrl = "https://api.razorpay.com/v1/";
	public static final String PaymentThemeColor = "#125e8c";
	public static final String PaymentButtonText = "Proceed to Pay With Razorpay";
	public static final String PaymentCurrency = "INR";
	public static final String CompanyName = "HANBIT";
	public static final int LowBalanceAlertCount  = 2;
	public static final String Disclaimer = "*This is a computer generated statement and does not require any signature or stamp";
}
