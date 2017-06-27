/**
 * File name:      DescUtil.java
 * Date:           2011-9-11
 * Description:    // 用于详细说明此程序文件完成的主要功
 *                 // 能与其他模块或函数的接口，输出值、
 *                 // 取值范围、含义及参数间的关系
 **********************************************************************
 */
package com.common.util;

import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created on 2011-9-11
 * <p>Title:       汇环_[子系统统名]_[模块名]/p>
 * <p>Description: [描述该类概要功能介绍]</p>
 * @version        1.0
 */
public class DescUtil {
  private static final String PASSWORD_CRYPT_KEY = "Jeff_Key";

  private final static String DES = "DES";

  /** 
   * 加密 
   * @param src 数据源 
   * @param key 密钥，长度必须是8的倍数 
   * @return  返回加密后的数据 
   * @throws Exception 
   */

  public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
    //DES算法要求有一个可信任的随机数源 
    SecureRandom sr = new SecureRandom();
    // 从原始密匙数据创建DESKeySpec对象 
    DESKeySpec dks = new DESKeySpec(key);

    // 创建一个密匙工厂，然后用它把DESKeySpec转换成 
    // 一个SecretKey对象 
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
    SecretKey securekey = keyFactory.generateSecret(dks);

    // Cipher对象实际完成加密操作 

    Cipher cipher = Cipher.getInstance(DES);
    // 用密匙初始化Cipher对象 
    cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
    // 现在，获取数据并加密 
    // 正式执行加密操作 
    return cipher.doFinal(src);
  }

  /** 
   * 解密 
   * @param src 数据源 
   * @param key 密钥，长度必须是8的倍数 
   * @return   返回解密后的原始数据 
   * @throws Exception 
   */

  public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
    // DES算法要求有一个可信任的随机数源 
    SecureRandom sr = new SecureRandom();
    // 从原始密匙数据创建一个DESKeySpec对象 
    DESKeySpec dks = new DESKeySpec(key);
    // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成 

    // 一个SecretKey对象 
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
    SecretKey securekey = keyFactory.generateSecret(dks);
    // Cipher对象实际完成解密操作 
    Cipher cipher = Cipher.getInstance(DES);
    // 用密匙初始化Cipher对象 
    cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
    // 现在，获取数据并解密 
    // 正式执行解密操作 
    return cipher.doFinal(src);
  }

  /** 
   * 密码解密 
   * @param data 
   * @return 
   * @throws Exception 
   */

  public final static String decrypt(String data) {
    try {
      return new String(decrypt(hex2byte(data.getBytes()),
      PASSWORD_CRYPT_KEY.getBytes()));
    } catch (Exception e) {
    }
    return null;
  }

  /** 
   * 密码加密 
   * @param password 
   * @return 
   * @throws Exception 
   */
  public final static String encrypt(String password) {
    try {
      return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
    } catch (Exception e) {
    }
    return null;
  }

  /** 
   * 二行制转字符串 
   * @param b 
   * @return 
   */
  public static String byte2hex(byte[] b) {
    String hs = "";
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
      if (stmp.length() == 1){
        hs = hs + "0" + stmp;
      }else{
        hs = hs + stmp;
      }
    }
    return hs.toUpperCase();
  }

  public static byte[] hex2byte(byte[] b) {
    if ((b.length % 2) != 0)
      throw new IllegalArgumentException("长度不是偶数");
    byte[] b2 = new byte[b.length / 2];
    for (int n = 0; n < b.length; n += 2) {
      String item = new String(b, n, 2);
      b2[n / 2] = (byte) Integer.parseInt(item, 16);
    }
    return b2;
  }
  
  public static void main(String args[]){
    System.out.println("请输参数：1:加密；2：解密");
    Scanner input=new Scanner(System.in);
    String method = input.nextLine();
    if(method==null || (!method.equals("1") && !method.equals("2"))){
      System.out.println("输参数错误!");
      return;
    }
    
    System.out.println("请输入密码：");
    input=new Scanner(System.in);
    String str=input.nextLine();
    
    if(method.equals("1")){
      System.out.println("["+str + "] 加密后: ");
      System.out.println(encrypt(str));
    }else{
      System.out.println("["+str + "] 解密后: ");
      System.out.println(decrypt(str));
    }
//      System.out.println("解密后: "+decrypt(encrypt(str)));
    }
}
