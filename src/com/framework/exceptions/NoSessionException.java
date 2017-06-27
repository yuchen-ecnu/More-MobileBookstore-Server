package com.framework.exceptions;

/**
 * session异常定义类
 * File name：NoSessionException
 * Date: 2013-6-6
 * Author: Admin
 * Description：
 * Modify History:
 * 2013-6-6 Admin Initial
 */
public class NoSessionException extends Exception {
	
	public NoSessionException() {
		super("No session found,relogin to try.");
	}

	public NoSessionException(String msg) {
		super(msg);
	}
}
