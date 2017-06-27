package com.pb.controller.userRelated;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class CreateCode {
	/**
	   * 生成验证码方法 
	   * @return
	   */
	  public static String createRandom(){
		  			String retStr = "";
		  			String strTable = "1234567890abcdefghijkmnpqrstuvwxyz";
		  			int len = strTable.length();
		  			boolean bDone = true;
		  		do {
		  			retStr = "";
		  			int count = 0;
		  			for (int i = 0; i < 6; i++) {
		  				double dblR = Math.random() * len;
		  				int intR = (int) Math.floor(dblR);
		  				char c = strTable.charAt(intR);
		  				if (('0' <= c) && (c <= '9')) {
		  					count++;
		  					}
		  				retStr += strTable.charAt(intR);
		  				}
		  			if (count >= 2) {
		  				bDone = false;
		  				}
		  			} while (bDone);
		  		
		  		return retStr;
	  }
}
