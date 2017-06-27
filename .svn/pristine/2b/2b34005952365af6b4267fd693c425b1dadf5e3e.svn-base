/**
 * <p>Description: DBResultDataSet.java </p>
 * <p>Function:  </p>
 * <p>Copyright: KingstarGroup Copyright (c) 2004 </p>
 * <p>Company: Kingstar</p>
 * <p>Created on: 2006-1-12 16:29:38 </p>
 * @author katlao
 * @version 1.0
 * Modify History List:
 * Ver  Date       Who      What
 */

package com.common.util;

import java.io.Serializable;
import java.util.ArrayList;


public class DBResultDataSet implements Serializable {
	
	static final long		serialVersionUID	= 197701273221261715L;
	
	public static final String TYPE_STRING = "String";
	
	public static final String TYPE_DATE = "Date";
	public static final String TYPE_DATETIME = "DateTime";
	public static final String TYPE_TIME = "Time";
	
	public static final String TYPE_INTEGER = "Integer";
	
	public static final String TYPE_LONG = "Long";
	public static final String TYPE_BIGDECIMAL = "BigDecimal";
	
	public static final String TYPE_DEFAULT_DOUBLE = "Double";        //默认
	public static final String TYPE_DOUBLE_1_SCALE = "Double_1_Scale";//保留1位小数
	public static final String TYPE_DOUBLE_2_SCALE = "Double_2_Scale";//保留2位小数
	public static final String TYPE_DOUBLE_3_SCALE = "Double_3_Scale";//保留3位小数
	public static final String TYPE_DOUBLE_4_SCALE = "Double_4_Scale";//保留4位小数
	public static final String TYPE_DOUBLE_5_SCALE = "Double_5_Scale";//保留5位小数
	public static final String TYPE_DOUBLE_6_SCALE = "Double_6_Scale";//保留6位小数
	
	public static final String TYPE_DEFAULT_DOUBLE_KC = "Double_0_Scale_KC";        //默认
	public static final String TYPE_DOUBLE_1_SCALE_KC = "Double_1_Scale_KC";//保留1位小数,增加千位符号
	public static final String TYPE_DOUBLE_2_SCALE_KC = "Double_2_Scale_KC";//保留2位小数,增加千位符号
	public static final String TYPE_DOUBLE_3_SCALE_KC = "Double_3_Scale_KC";//保留3位小数,增加千位符号
	public static final String TYPE_DOUBLE_4_SCALE_KC = "Double_4_Scale_KC";//保留4位小数,增加千位符号
	public static final String TYPE_DOUBLE_5_SCALE_KC = "Double_5_Scale_KC";//保留5位小数,增加千位符号
	public static final String TYPE_DOUBLE_6_SCALE_KC = "Double_6_Scale_KC";//保留6位小数,增加千位符号
	//----------------------------------------
	private String[] _columnNames, _columnTypeNames;
	private ArrayList _table;
	private ArrayList _tableFlag;//yuyu add
	
	public DBResultDataSet(String[] columnNames, String[] columnTypeNames) {
		super();
		_table = new ArrayList();
		_columnNames = columnNames;
		_columnTypeNames = columnTypeNames;
	}
	
	public DBResultDataSet() {
		super();
		_table = new ArrayList();
		_tableFlag = new ArrayList();
	}
	
	public String[] getColumnNames() {
		return _columnNames;
	}
	public void setColumnNames(String[] columnNames) {
		_columnNames = columnNames;
	}
	
	public String[] getColumnTypeNames() {
		return _columnTypeNames;
	}
	public void setColumnTypeNames(String[] columnTypeNames) {
		_columnTypeNames = columnTypeNames;
	}
	
	public int getColumnCount() {
		if(_columnNames != null){
			return _columnNames.length;
		}
		else{
			if(_table.size() > 0){
				return ((String[])_table.get(0)).length;
			}
		}
		return 0;
	}
	
	/**
	 * @param index 行索引: 从0开始
	 * @return
	 * Created on: 2006-1-12 16:35:09
	 * @author katlao
	 */
	public String[] getRow(int rowIndex) {
		rowRangeCheck(rowIndex);
		return (String[])_table.get(rowIndex);
	}
	
	public void addRow(String[] row) {
		_table.add(row);
	}
	
	
	/**
	 * @param rowIndex
	 * @author yuyu
	 */
	public String[] getRowFlag(int rowIndex) {
		rowRangeCheck(rowIndex);
		return (String[])_tableFlag.get(rowIndex);
	}
	
	/**
	 * @param row
	 * @param flag
	 * @author yuyu
	 */
	public void addRow(String[] row,String[] flag) {
		_table.add(row);
		_tableFlag.add(flag);
	}
	
	public void removeIndex(int i) {
		_table.remove(i);
	}
	
	
	/**
	 * @param i
	 * @author yuyu
	 */
	public void removeFullIndex(int i) {
		_table.remove(i);
		_tableFlag.remove(i);
	}
	
	/**
	 * 获取行数 
	 * @return
	 * Created on: 2006-1-12 16:38:38
	 * @author katlao
	 */
	public int getRowCount() {
		return _table.size();
	}
	
	public void setCell(int rowIndex, int columnIndex, String value) {
		rowRangeCheck(rowIndex);
		String[] row = (String[])_table.get(rowIndex);
		columnRangeCheck(columnIndex);
		row[columnIndex] = value;
	}
	
	
	/**
	 * @param rowIndex
	 * @param columnIndex
	 * @param value
	 * @author yuyu
	 */
	public void setCellFlag(int rowIndex, int columnIndex, String value) {
		rowRangeCheck(rowIndex);
		String[] row = (String[])_tableFlag.get(rowIndex);
		columnRangeCheck(columnIndex);
		row[columnIndex] = value;
	}
	
	public void setCell(int rowIndex, String columnName, String value) {
		int columnIndex = indexof(columnName.trim());
		if(columnIndex > -1){
			setCell(rowIndex, columnIndex, value);
		}
	}
	
	public String getCell(int rowIndex, int columnIndex) {
		rowRangeCheck(rowIndex);
		String[] row = (String[])_table.get(rowIndex);
		columnRangeCheck(columnIndex);
		return row[columnIndex];
	}
	
	
	/**
	 * @param rowIndex
	 * @param columnIndex
	 * @author yuyu
	 * @return CellFlag
	 */
	public String getCellFlag(int rowIndex, int columnIndex) {
		rowRangeCheck(rowIndex);
		String[] row = (String[])_tableFlag.get(rowIndex);
		columnRangeCheck(columnIndex);
		return row[columnIndex];
	}

	/**
	 * @param columnIndex
	 * Created on: 2006-1-13 14:49:40
	 * @author katlao
	 */
	private void columnRangeCheck(int columnIndex) {
		if(columnIndex < 0 && columnIndex >= _columnNames.length){
			throw new IllegalArgumentException("columnIndex不合法："+columnIndex);
		}
	}

	/**
	 * @param rowIndex
	 * Created on: 2006-1-13 14:48:51
	 * @author katlao
	 */
	private void rowRangeCheck(int rowIndex) {
		if(rowIndex < 0 && rowIndex >= _table.size()){
			throw new IllegalArgumentException("rowIndex不合法："+rowIndex);
		}
	}
	
	public String getCell(int rowIndex, String columnName) {
		int columnIndex = indexof(columnName.trim());
		if(columnIndex > -1){
			return getCell(rowIndex, columnIndex);
		}
		return null;
	}
	
	public String[] getColumn(int columnIndex) {
		columnRangeCheck(columnIndex);
		String[] column = new String[getRowCount()];
		for (int i = 0; i < column.length; i++) {
			column[i] = ((String[])_table.get(i))[columnIndex];
		}
		return column;
	}
	
	public String[] getColumn(String columnName) {
		int columnIndex = indexof(columnName.trim());
		if(columnIndex > -1){
			return getColumn(columnIndex);
		}
		return null;
	}
	
	private int indexof(String columnName){
		for (int i = 0; i < _columnNames.length; i++) {
			if(_columnNames[i].trim().equalsIgnoreCase(columnName))
				return i;
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() {
		try {
			DBResultDataSet v = (DBResultDataSet) super.clone();
			v._columnNames = new String[_columnNames.length];
			System.arraycopy(_columnNames, 0, v._columnNames, 0, _columnNames.length);

			v._columnTypeNames = new String[_columnTypeNames.length];
			System.arraycopy(_columnTypeNames, 0, v._columnTypeNames, 0, _columnTypeNames.length);
			
			v._table = (ArrayList)_table.clone();
			return v;
		} catch (CloneNotSupportedException e) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError();
		}
	}


	/**
	 * @return Returns the _table.
	 */
	public ArrayList getTable() {
		return _table;
	}

}
