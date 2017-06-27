/**
 * File name:      PswdProcessUtil.java
 * Date:           2011-9-2
 * Description:    // 用于详细说明此程序文件完成的主要功
 *                 // 能与其他模块或函数的接口，输出值、
 *                 // 取值范围、含义及参数间的关系
 **********************************************************************
 */
package com.common.util;

public class PswdProcessUtil {
	  /**
	   * 加密，用于执行SQL之前
	   * @param pass 原密码
	   * @return 加密密码
	   */
	  public static String hidePass(String pass){
//		  MD5 m = new MD5();
//		  return m.getMD5ofStr(pass)
		  StringEncrypt encryptor = new StringEncrypt();
		  return encryptor.Encrypt(pass, "SHA-256");
	  }
	  
	  public static void main(String[] args){
	    if(args.length==1)
	    {
	      System.out.println(hidePass(args[0]));
	    }else{
	      System.out.println(hidePass("111111"));
	    }
	  }
}