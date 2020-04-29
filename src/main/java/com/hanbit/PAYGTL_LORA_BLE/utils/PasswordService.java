/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hanbit.PAYGTL_LORA_BLE.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import org.myorg.SystemUnavailableException;
import sun.misc.BASE64Encoder;
//import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author PAWAN
 */
public class PasswordService {
  private static PasswordService instance;
 
  public PasswordService()
  {
  }
 
  public synchronized String encrypt(String plaintext) throws Exception
  {
    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance("SHA"); //step 2
    }
    catch(NoSuchAlgorithmException e)
    {
      throw new Exception(e.getMessage());
    }
    try
    {
      md.update(plaintext.getBytes("UTF-8")); //step 3
    }
    catch(UnsupportedEncodingException e)
    {
      throw new Exception(e.getMessage());
    }
 
    byte raw[] = md.digest(); //step 4
//    String hash = (new Base64().encodeToString(raw)); //step 5
    String hash = (new BASE64Encoder()).encode(raw); //step 5
    return hash; //step 6
  }
 
  public static synchronized PasswordService getInstance() //step 1
  {
    if(instance == null)
    {
       instance = new PasswordService();
    }
    return instance;
  } 
}
