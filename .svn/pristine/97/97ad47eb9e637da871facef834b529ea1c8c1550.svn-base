package com.pb.json;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * File name：TreeNode
 * Date: 2012-4-25
 * Author: Administrator
 * Description：前台页面展示Tree结构使用的JSON结构
 * Modify History:
 */
public class TreeNode implements Serializable,Cloneable {
	
	private String id;
	private String name;//节点显示的名称字符串，标准 String 即可，所有特殊字符都会被自动转义
	private boolean checked;//节点是否选择
	private boolean chkDisabled;//节点是否可以禁用
	private boolean halfCheck;//节点强制半选状态
	private String icon;//点如果只设置 icon ，会导致展开、折叠时都使用同一个图标
	private String iconOpen;
	private String iconClose;
	private String iconSkin;//设置个性图标的 className
	private String isParent;//treeNode.isParent = true，即使无子节点数据，也会设置为父节点
	private boolean nocheck;//设置节点是否隐藏 checkbox / radio 
	private boolean open;//记录 treeNode 节点的 展开 / 折叠 状态
	private String target;
	private String url;
	private String level;//资源等级
	private String click;//节点点击事件
	private List<TreeNode> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	public boolean isHalfCheck() {
		return halfCheck;
	}
	public void setHalfCheck(boolean halfCheck) {
		this.halfCheck = halfCheck;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public String getIconClose() {
		return iconClose;
	}
	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public boolean isNocheck() {
		return nocheck;
	}
	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
