package com.common.util;

import java.awt.Color;

import com.lowagie.text.Cell;
import com.lowagie.text.Table;

public class PdfTable {
	public Table _table = null;				//公有属性：表格对象
	private int colCount = 0;				//私有属性：表格的总列数，描述填充数据时的最大列数
	private int lastHeaderRow = 0;			//私有属性：表头的最后一行的行数

	public PdfTable(int colCount) throws Exception {
		this.colCount = colCount;
		_table = new Table(colCount);
		_table.setWidth(100);				//默认可显示宽度的100%
		_table.setBorder(15);				//默认存在边框
		_table.setBorderWidth(0);			//默认边框的宽度为0，表格将全部是细线
		_table.setPadding(1);				//默认设置单元格填充间距为1
		_table.setSpacing(0);				//默认设置单元格之间间距为0
//		_table.setDefaultHorizontalAlignment(Table.ALIGN_CENTER);//默认表格在文档的水平居中
		_table.setCellsFitPage(true);		//设置单元格内容不会因换页，而显示在第一页页尾和第二页页头部（全部显示在第二页）
	}

	/**
	 * 功能描述：获取表格
	 * @return Table
	 */
	public Table getTable() {
		return _table;
	}

	/**
	 * 功能描述：设置表格占可显示宽度的百分比
	 * @param widthPercent float
	 */
	public void setDisplayWidth(float widthPercent) {
		_table.setWidth(widthPercent);
	}

	/**
	 * 功能描述：设置表格每个单元格占总表格显示宽度的百分比数
	 * @param colWidths 每个单元格显示百分比的数组，不需要总和为100，实际显示为每个数字占总和的百分比
	 * @throws Exception
	 */
	public void setColWidths(int[] colWidths) throws Exception {
		if (colWidths != null && colWidths.length == this.colCount) {
			_table.setWidths(colWidths);
		} else
			throw new Exception("PDF Table set columns width error");
	}
	
	/**
	 * 功能描述：设置表格的边框属性
	 * @param border int 可以为Table.TOP=1, Table.BOTTOM=2, Table.LEFT=4, Table.RIGHT=8, Table.NO_BORDER=0，或者前四种和组合（加法）
	 * @param borderWidth float 表格的边框宽度
	 * @param color Color 边框颜色，可以为null
	 * @throws Exception
	 */
	public void setBorder(int border, float borderWidth, Color color)
			throws Exception {
		if (border >= 0 && border <= 15)
			_table.setBorder(border);
		else
			throw new Exception("PDF Table border must between 0 and 15");
		_table.setBorderWidth(borderWidth);
		if (color != null)
			_table.setBorderColor(color);
	}
	
	/**
	 * 功能描述：设置表头结束，本功能在加入表头单元格结束后调用，只能调用一次
	 * @throws Exception
	 */
	public void setEndHeader()throws Exception{
		if(lastHeaderRow == 0){
			lastHeaderRow = _table.endHeaders();
		}
		else throw new Exception("PDF Table only set end header one time");
	}

	/**
	 * 功能描述：表格加入单元格
	 * @param cell 单元格，可以使用PdfUtil.createCell()方法创建
	 */
	public void addCell(Cell cell) {
		_table.addCell(cell);
	}

	/**
	 * 功能描述：设置表格内单元格的填充间距
	 * @param padding float 单元格的填充间距
	 */
	public void setPadding(float padding) {
		_table.setPadding(padding);
	}

	/**
	 * 功能描述：设置表格内单元格之间的间距
	 * @param spacing float 单元格之间的间距
	 */
	public void setSpacing(float spacing) {
		_table.setSpacing(spacing);
	}

//	/**
//	 * 功能描述：设置表格在文档中水平的对齐方式
//	 * @param alignment
//	 */
//	public void setTableHAlign(int alignment) {
//		_table.setDefaultHorizontalAlignment(alignment);
//	}
//
//	/**
//	 * 功能描述：设置表格在文档中垂直的对齐方式
//	 * @param alignment
//	 */
//	public void setTableVAlign(int alignment) {
//		_table.setDefaultHorizontalAlignment(alignment);
//	}

	/**
	 * 功能描述：设置单元格是否只能显示在一页内，多用在换页时单元格内容被分隔在上下两页
	 * @param isFitPage true:只显示在一页内，false:可显示在上下两页
	 */
	public void setCellsFitPage(boolean isFitPage){
		_table.setCellsFitPage(isFitPage);
	}
}
