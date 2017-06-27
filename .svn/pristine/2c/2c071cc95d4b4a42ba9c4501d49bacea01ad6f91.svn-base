package com.pb.json;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * File name：TableGridPage
 * Date: 2012-4-25
 * Author: Administrator
 * Description：TableGrid页面上传入数据
 * Modify History:
 */
public class TableGridPage implements Serializable,Cloneable {
	
	/**
	 * 字段数量
	 */
	private Integer iColumns;
	/**
	 * 参与排序的字段数量
	 */
	private Integer iSortingCols;
	/**
	 * 查询记录起始位置
	 */
	private Integer iDisplayStart;
	/**
	 * 分页记录数据(每页显示记录数)
	 */
	private Integer iDisplayLength;
	/**
	 * 服务器自动产生内容，将此数据放入TableGridJson的sEcho字段中
	 */
	private String sEcho;
	
	/**
	 * 排序顺序
	 */
	private List<Integer> iSortCol;
	/**
	 * 排序方式asc、desc
	 */
	private List<String> sSortDir;
	/**
	 * 字段列表
	 */
	private List<String> mDataProp;
	/**
	 * 是否参与排序
	 */
	private List<String> bSortable;
	
	/**
	 * 排序队列
	 */
	private List<Object> orders;
	
	/**
	 * 总记录数
	 */
	private Integer totalSize = 0;

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getiColumns() {
		return iColumns;
	}

	public void setiColumns(Integer iColumns) {
		this.iColumns = iColumns;
	}

	public Integer getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(Integer iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public Integer getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public List<Integer> getiSortCol() {
		return iSortCol;
	}

	public void setiSortCol(List<Integer> iSortCol) {
		this.iSortCol = iSortCol;
	}

	public List<String> getsSortDir() {
		return sSortDir;
	}

	public void setsSortDir(List<String> sSortDir) {
		this.sSortDir = sSortDir;
	}

	public List<String> getmDataProp() {
		return mDataProp;
	}

	public void setmDataProp(List<String> mDataProp) {
		this.mDataProp = mDataProp;
	}

	public List<String> getbSortable() {
		return bSortable;
	}

	public void setbSortable(List<String> bSortable) {
		this.bSortable = bSortable;
	}

	public List<Object> getOrders() {
		return orders;
	}

	public void setOrders(List<Object> orders) {
		this.orders = orders;
	}

}
