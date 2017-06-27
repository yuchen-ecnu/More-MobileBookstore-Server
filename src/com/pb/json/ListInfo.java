package com.pb.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * File name：MenuInfo
 * Date: 2012-4-25
 * Author: Administrator
 * Description：前台简单数据用JSON结构
 * Modify History:
 */
public class ListInfo implements Serializable,Cloneable {
	
	private List children;
	private List childrenUrl;
	
	public ListInfo(){
		children = new ArrayList(0);
		childrenUrl = new ArrayList(0);
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public List getChildrenUrl() {
		return childrenUrl;
	}

	public void setChildrenUrl(List childrenUrl) {
		this.childrenUrl = childrenUrl;
	}

}
