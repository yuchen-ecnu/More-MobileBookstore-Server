package com.pb.json;

import java.io.Serializable;

/**
 * 
 * File name：BaseJson
 * Date: 2012-4-25
 * Author: Administrator
 * Description：JSON基础结构，包含响应码和响应消息，反馈给前台页面
 * Modify History:
 */
public class BaseJson implements Serializable,Cloneable {
	
	private String retcode;//响应代码
	private String errorMsg;//错误消息
	
	private Object obj;
	public BaseJson()
	{
		retcode = "0000";
	}
	
	public BaseJson(String retcode){
		this.retcode = retcode;
	}
	
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
