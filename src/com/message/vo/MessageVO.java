package com.message.vo;

import java.io.Serializable;

public class MessageVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3374844583410077374L;
	private String sender; // 消息发送者唯一编码，若为系统发送，则为SYSTEM
	private String msg; // 消息内容
	private String type; // 消息类型，logout：强制退出；normal：业务
	private boolean lazy; // 是否异步发送消息
	
	public MessageVO(){
		
	}
	
	public MessageVO(String msg, String type){
		this(null, msg, type, false);
	}
	
	public MessageVO(String sender, String msg, String type, boolean lazy){
		this.sender = sender;
		this.msg = msg;
		this.type = type;
		this.lazy = lazy;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isLazy() {
		return lazy;
	}
	public void setLazy(boolean lazy) {
		this.lazy = lazy;
	}
}
