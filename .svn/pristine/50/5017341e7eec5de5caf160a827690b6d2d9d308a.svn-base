package com.pb.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * File name：MenuInfo
 * Date: 2012-4-25
 * Author: Administrator
 * Description：前台菜单展示用JSON结构
 * Modify History:
 */
public class MenuInfo implements Serializable,Cloneable {
	
	private String title;
	private String url;
	private String code;
	private List<MenuInfo> children = new ArrayList<MenuInfo>(0);
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<MenuInfo> getChildren() {
		return children;
	}
	public void setChildren(List<MenuInfo> children) {
		this.children = children;
	}

}
