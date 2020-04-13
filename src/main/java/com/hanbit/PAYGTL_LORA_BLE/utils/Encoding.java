/**
 * 
 */
package com.hanbit.PAYGTL_LORA_BLE.utils;



import java.math.BigInteger;
import org.apache.commons.codec.binary.Base64;


/**
 * @author USER
 *
 */
public class Encoding {
	
	public static String getHexBase644(String hexadecimal) {
		// TODO Auto-generated method stub
		
	    BigInteger bigint = new BigInteger(hexadecimal, 16);

	    StringBuilder sb = new StringBuilder();
	    byte[] ba = Base64.encodeInteger(bigint);
	    for (byte b : ba) {
	        sb.append((char)b);
	    }
		return sb.toString();
		
	}
	
	
	private static int hextoDecimal(final String cmd_id) {
		// TODO Auto-generated method stub
		String digits = "0123456789ABCDEF";
		int val = 0;
		for (int i = 0; i < cmd_id.length(); i++) {
			char c = cmd_id.charAt(i);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}
		//System.out.println("-------------->"+val);
		return val;

	}
	
	
}
