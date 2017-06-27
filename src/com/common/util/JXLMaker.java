/**
 * <p>Description: JXLMaker.java </p>
 * <p>Function:  </p>
 * <p>Copyright: KingstarGroup Copyright (c) 2004 </p>
 * <p>Company: Kingstar</p>
 * <p>Created on: 2006-1-19 0:05:02 </p>
 * @author katlao
 * @version 1.0
 * Modify History List:
 * Ver  Date       Who      What
 */

package com.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JXLMaker {
	public static final String HTML_BLANK = "&nbsp;";

	private WritableWorkbook _workbook;

	private WritableSheet _sheet;

	private OutputStream _outputStream;

	public JXLMaker(String fileName) throws IOException {
		_workbook = Workbook.createWorkbook(new java.io.File(fileName));
		_sheet = _workbook.createSheet("Sheet", 0);
	}
	
	/**
	 * @param fileName
	 * @return
	 */
	public static Sheet getDataSetSheet(String sourceFile){
		Workbook workbook = null;;
		Sheet sheet = null;
		try {
			workbook = Workbook.getWorkbook(new File(sourceFile));
			sheet =  workbook.getSheet(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	public JXLMaker(OutputStream outputStream) throws IOException {
		_outputStream = outputStream;
		_workbook = Workbook.createWorkbook(outputStream);
		_sheet = _workbook.createSheet("Sheet", 0);
	}

	public boolean write() throws Exception {
		try {
			_workbook.write();
			_workbook.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public void input(int rowStartIndex, String[] header, int[] order, DBResultDataSet data) throws Exception {
		int body_rowStartIndex = rowStartIndex + 1;
		int[] width = new int[order.length];
		if (header != null) {
			for (int i = 0; i < header.length; i++) {
				addStringCell(header[i], rowStartIndex, i, true);
				width[i] = getLength(header[i]);
			}
		}
		//String[] colNameTypes = type;
		int rowCountIndex = data.getRowCount();
		for (int i = 0; i < rowCountIndex; i++) {
			String[] row = data.getRow(i);
			for (int j = 0; j < order.length; j++) {
				//String cellType = colNameTypes[j];// old 
				String cell =( order[j]>=(row.length)?null:row[order[j]].trim()); // 单元格值
				if(cell == null){
					continue;
				}
				String cellType = getCellType(cell);// 单元格属性
				// 设置最大列宽
				width[j] = Math.max(width[j], getLength(cell));

				try {
					if (cellType
							.startsWith(DBResultDataSet.TYPE_DEFAULT_DOUBLE)) {
						if (cellType
								.equals(DBResultDataSet.TYPE_DEFAULT_DOUBLE)) {
							addDoubleDefaultCell(cell, i + body_rowStartIndex,
									j);
						} else {
							int pos1 = cellType.indexOf('_');
							int pos2 = cellType.indexOf('_', pos1 + 1);
							if (pos1 > 0 && pos2 > pos1) {
								int scale = Integer.parseInt(cellType
										.substring(pos1 + 1, pos2));
								boolean addKilocharacter = cellType
										.lastIndexOf("KC") > 0;
								if (addKilocharacter == true) {
									char[] chTmpCell = cell.toCharArray();
									StringBuffer sb = new StringBuffer();
									for (int x = 0; x < chTmpCell.length; x++) {
										if (chTmpCell[x] != ',')
											sb.append(chTmpCell[x]);
									}
									cell = sb.toString();
								}
								this.addDoubleCell(cell,
										i + body_rowStartIndex, j, scale,
										addKilocharacter);
							}
						}
					} else if (cellType.equals(DBResultDataSet.TYPE_INTEGER)) {
						addDoubleCell(cell, i + body_rowStartIndex, j, 0, false);
					} else if (cellType.equals(DBResultDataSet.TYPE_STRING)) {
						addStringDefaultCell(cell, i	+ body_rowStartIndex, j);
					} else if (cellType.equals(DBResultDataSet.TYPE_DATE)) {
						addDateCell(cell, i + body_rowStartIndex, j);
					} else if (cellType.equals(DBResultDataSet.TYPE_DATETIME)) {
						addDateTimeCell(cell, i + body_rowStartIndex, j);
					} else if (cellType.equals(DBResultDataSet.TYPE_TIME)) {
						addTimeCell(cell, i + body_rowStartIndex, j);
					}
				} catch (Exception e) {
					 e.printStackTrace();
					addStringDefaultCell(cell, i + body_rowStartIndex, j);//new add on 2006-05-11
				}
			}
		}
		setColumnsView(width.length, width);
	}
	
	private void input(int rowStartIndex, String[] header, int[] order, DBResultDataSet data, String[] type) throws Exception {
		int body_rowStartIndex = rowStartIndex + 1;
		int[] width = new int[order.length];
		if (header != null) {
			for (int i = 0; i < header.length; i++) {
				addStringCell(header[i], rowStartIndex, i, true);
				width[i] = getLength(header[i]);
			}
		}
		//String[] colNameTypes = type;
		int rowCountIndex = data.getRowCount();
		for (int i = 0; i < rowCountIndex; i++) {
			String[] row = data.getRow(i);
			for (int j = 0; j < order.length; j++) {
				//String cellType = colNameTypes[j];// old 
				String cell = ( order[j]>=(row.length)?null:row[order[j]].trim()); // 单元格值
				if(cell == null){
					continue;
				}
				String cellType = "";
				if(type != null)
				   cellType = type[order[j]].trim();// 单元格属性
				else
				   cellType = getCellType(cell);// 单元格属性

				// 设置最大列宽
				width[j] = Math.max(width[j], getLength(cell));

				try {
					if (cellType
							.startsWith(DBResultDataSet.TYPE_DEFAULT_DOUBLE)) {
						if (cellType
								.equals(DBResultDataSet.TYPE_DEFAULT_DOUBLE)) {
							addDoubleDefaultCell(cell, i + body_rowStartIndex,
									j);
						} else {
							int pos1 = cellType.indexOf('_');
							int pos2 = cellType.indexOf('_', pos1 + 1);
							if (pos1 > 0 && pos2 > pos1) {
								int scale = Integer.parseInt(cellType
										.substring(pos1 + 1, pos2));
								boolean addKilocharacter = cellType
										.lastIndexOf("KC") > 0;
								if (addKilocharacter == true) {
									char[] chTmpCell = cell.toCharArray();
									StringBuffer sb = new StringBuffer();
									for (int x = 0; x < chTmpCell.length; x++) {
										if (chTmpCell[x] != ',')
											sb.append(chTmpCell[x]);
									}
									cell = sb.toString();
								}
								this.addDoubleCell(cell,
										i + body_rowStartIndex, j, scale,
										addKilocharacter);
							}
						}
					} else if (cellType.equals(DBResultDataSet.TYPE_INTEGER)) {
						addDoubleCell(cell, i + body_rowStartIndex, j, 0, false);
					} else if (cellType.equals(DBResultDataSet.TYPE_STRING)) {
						addStringDefaultCell(cell, i	+ body_rowStartIndex, j);
					} else if (cellType.equals(DBResultDataSet.TYPE_DATE)) {
						addDateCell(cell, i + body_rowStartIndex, j);
					} else if (cellType.equals(DBResultDataSet.TYPE_DATETIME)) {
						addDateTimeCell(cell, i + body_rowStartIndex, j);
					} else if (cellType.equals(DBResultDataSet.TYPE_TIME)) {
						addTimeCell(cell, i + body_rowStartIndex, j);
					}
				} catch (Exception e) {
					 e.printStackTrace();
					addStringDefaultCell(cell, i + body_rowStartIndex, j);//new add on 2006-05-11
				}
			}
		}
		setColumnsView(width.length, width);
	}
	
	
	/**
	   * 判断是否是是数字类型
	   * @author yuyu
	   * @param inString
	   * @Created on 2006-05-11
	   */
	  public boolean isIntegerString(String inString) {
			if (inString.length() == 0)
				return false;
			for (int i = 0; i < inString.length(); i++) {
				if (inString.charAt(i) != '0' && inString.charAt(i) != '1'
						&& inString.charAt(i) != '2' && inString.charAt(i) != '3'
						&& inString.charAt(i) != '4' && inString.charAt(i) != '5'
						&& inString.charAt(i) != '6' && inString.charAt(i) != '7'
						&& inString.charAt(i) != '8' && inString.charAt(i) != '9'
						&& inString.charAt(i) != ',') {
					return false;
				}
			}
			return true;
		}
	
	
	/**
	 * 获得单元格属性，以属性标志区分 
	 * 如果判断错误，则转为字符类型
	 * @author yuyu
	 * @param strTmpCell
	 * @Created on 2006-05-11
	 */
  public String getCellType(String strTmpCell) {
		String columnType = "";
		if (strTmpCell.indexOf(".") > -1) {
			int pointAfter = strTmpCell.length() - strTmpCell.indexOf(".");
			if (strTmpCell.indexOf(',') > -1) {
				if (pointAfter == 2)
					columnType = DBResultDataSet.TYPE_DOUBLE_1_SCALE_KC;
				else if (pointAfter == 3)
					columnType = DBResultDataSet.TYPE_DOUBLE_2_SCALE_KC;
				else if (pointAfter == 4)
					columnType = DBResultDataSet.TYPE_DOUBLE_3_SCALE_KC;
				else if (pointAfter == 5)
					columnType = DBResultDataSet.TYPE_DOUBLE_4_SCALE_KC;
				else if (pointAfter == 6)
					columnType = DBResultDataSet.TYPE_DOUBLE_5_SCALE_KC;
				else if (pointAfter == 7)
					columnType = DBResultDataSet.TYPE_DOUBLE_6_SCALE_KC;
				else
					columnType = DBResultDataSet.TYPE_DEFAULT_DOUBLE_KC;
			} else {
				if (pointAfter == 2)
					columnType = DBResultDataSet.TYPE_DOUBLE_1_SCALE;
				else if (pointAfter == 3)
					columnType = DBResultDataSet.TYPE_DOUBLE_2_SCALE;
				else if (pointAfter == 4)
					columnType = DBResultDataSet.TYPE_DOUBLE_3_SCALE;
				else if (pointAfter == 5)
					columnType = DBResultDataSet.TYPE_DOUBLE_4_SCALE;
				else if (pointAfter == 6)
					columnType = DBResultDataSet.TYPE_DOUBLE_5_SCALE;
				else if (pointAfter == 7)
					columnType = DBResultDataSet.TYPE_DOUBLE_6_SCALE;
				else
					columnType = DBResultDataSet.TYPE_DEFAULT_DOUBLE;
			}
		} else if (strTmpCell.indexOf("-") == 4) {
			if(DateUtil.checkDateValidity(strTmpCell,"yyyy-MM-dd")){
				columnType = DBResultDataSet.TYPE_DATE;
			}
			else 
				columnType = DBResultDataSet.TYPE_STRING;
		} 
		else if (isIntegerString(strTmpCell) == true) {
			if (strTmpCell.indexOf(',') > -1) {
				columnType = DBResultDataSet.TYPE_DEFAULT_DOUBLE_KC;
			} else {
				if (strTmpCell.startsWith("0") && strTmpCell.length()>1)
					columnType = DBResultDataSet.TYPE_STRING;
				else
					columnType = DBResultDataSet.TYPE_INTEGER;
			}
		} else {
			columnType = DBResultDataSet.TYPE_STRING;
		}
		if(strTmpCell.equalsIgnoreCase("2.0317")){
			System.out.println("katlao debug: strTmpCell:"+strTmpCell+", columnType:"+columnType);
		}
		return columnType;
	}

	public void setColumnView(int column, int width) {
		_sheet.setColumnView(column, width);
	}

	public void insertRow(int row) {
		_sheet.insertRow(row);
	}

	public void insertColumn(int column) {
		_sheet.insertColumn(column);
	}

	public void setRowView(int row, int height) throws Exception {
		_sheet.setRowView(row, height);
	}

	public void setColumnsView(int columnCount, int[] width) {
		for (int i = 0; i < columnCount; i++) {
			if(width!=null && width.length!=0){
				_sheet.setColumnView(i, width[i]);
			}
//			else{
//				_sheet.setColumnView(i, 10);
//			}
		}
	}

	public void addEmptyCell(int row, int column) throws Exception {
		Label label = new Label(column, row, "");
		_sheet.addCell(label);
	}

	public void addZeroCell(int row, int column) throws Exception {
		Number n = new Number(column, row, 0);
		_sheet.addCell(n);
	}

	public void addStringDefaultCell(String strText, int row, int column)
			throws Exception {
		// Label label = new Label(column, row, strText);
		// _sheet.addCell(label);
		addStringCell(strText, row, column, false);
	}

	public void addStringCell(String strText, int row, int column, boolean bold)
			throws Exception {
		addStringCell(strText, row, column, bold, Alignment.CENTRE);
	}

	/**
	 * @param alignment
	 *            1:Alignment.LEFT, 2:Alignment.CENTRE, 3:Alignment.RIGHT
	 * @throws Exception
	 *             Created on: 2006-1-23 13:31:52
	 * @author katlao
	 */
	public void addStringCell(String strText, int row, int column,
			boolean bold, int alignment) throws Exception {
		addStringCell(strText, row, column, bold,
				alignment == 1 ? Alignment.LEFT
						: (alignment == 3 ? Alignment.RIGHT : Alignment.CENTRE));
	}

	public void addStringCell(String strText, int row, int column,
			boolean bold, Alignment pos) throws Exception {
		if (strText.equals(HTML_BLANK)) {
			addEmptyCell(row, column);
			return;
		}
		WritableFont font = new WritableFont(WritableFont.ARIAL,
				WritableFont.DEFAULT_POINT_SIZE, bold ? WritableFont.BOLD
						: WritableFont.NO_BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		format.setAlignment(pos);
		Label label = new Label(column, row, strText, format);
		_sheet.addCell(label);
	}

	public void addDoubleCell(String dbData, int row, int column)
			throws Exception {
		addDoubleCell(dbData, row, column, 2, true);
	}

	public void addDoubleDefaultCell(String dbData, int row, int column)
			throws Exception {
		if (dbData.equals(HTML_BLANK)) {
			this.addZeroCell(row, column);
			return;
		}
		Number n = new Number(column, row, (new Double(dbData)).doubleValue());
		_sheet.addCell(n);
	}

	public void addDoubleCell(String dbData, int row, int column, int scale,
			boolean addKilocharacter) throws Exception {
		if (dbData.equals(HTML_BLANK)) {
			this.addZeroCell(row, column);
			return;
		}
		// NumberFormat dp3 = new NumberFormat("#,###.####");
		// NumberFormat dp3 = new NumberFormat("#.##");
		NumberFormat dp3 = new NumberFormat(addwell(scale, addKilocharacter));
		WritableCellFormat dp3cell = new WritableCellFormat(dp3);
		// dp3cell.setWrap(true);
		dp3cell.setAlignment(Alignment.RIGHT);
		Number number = new Number(column, row, (new Double(dbData))
				.doubleValue(), dp3cell);
		_sheet.addCell(number);
	}

	public void addDateCell(String dbData, int row, int column)
			throws Exception {
		if (dbData.equals(HTML_BLANK)) {
			addEmptyCell(row, column);
			return;
		}
		WritableCellFormat cf1 = new WritableCellFormat(DateFormats.FORMAT1);
		DateTime dt = new DateTime(column, row, DateUtil.getDate(dbData),
				cf1);
		_sheet.addCell(dt);
	}

	public void addTimeCell(String dbData, int row, int column)
			throws Exception {
		if (dbData.equals(HTML_BLANK)) {
			addEmptyCell(row, column);
			return;
		}
		WritableCellFormat cf1 = new WritableCellFormat(DateFormats.FORMAT8);
		DateTime dt = new DateTime(column, row, DateUtil.getDate(dbData, "HH:mm:ss"),
				cf1);
		_sheet.addCell(dt);
	}

	public void addDateTimeCell(String dbData, int row, int column)
			throws Exception {
		if (dbData.equals(HTML_BLANK)) {
			addEmptyCell(row, column);
			return;
		}
		WritableCellFormat cf1 = new WritableCellFormat(DateFormats.FORMAT9);
		DateTime dt = new DateTime(column, row, DateUtil.getDateTime(dbData), cf1);
		_sheet.addCell(dt);
	}

	public Range mergeCells(int row1, int col1, int row2, int col2)
			throws Exception {
		return _sheet.mergeCells(col1, row1, col2, row2);
	}

	private String addwell(int scale, boolean addKilocharacter) {
		if (scale <= 0) {
			return addKilocharacter == false ? "0" : "#,##0";
		}
		String str = addKilocharacter == false ? "0." : "#,##0.";
		for (int i = 0; i < scale; i++) {
			str += "0";
		}
		return str;
	}

	public static int getLength(String src) {
		char[] arr = src.toCharArray();
		int len = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 255) {// 汉字
				len += 2;
			} else {
				len++;
			}
		}
		return len + 1;
	}

	public static void main(String[] arg) throws Exception {
		JXLMaker maker = new JXLMaker("d://test_1.xls");
		String[] head = new String[] { "你好啊独立", "毒素扶绥", "哭诉多幅", "决定法卡", "随地若","决定法卡", "随地若",
				"你好啊独立", "毒素扶绥", "哭诉多幅", "决定法卡", "随地若","决定法卡", "随地若",
				"你好啊独立", "毒素扶绥", "哭诉多幅", "决定法卡", "随地若","决定法卡", "随地若",
				"你好啊独立", "毒素扶绥", "哭诉多幅", "决定法卡", "随地若","决定法卡", "随地若",
				"你好啊独立", "毒素扶绥", "哭诉多幅", "决定法卡", "随地若","决定法卡", "随地若",
				"你好啊独立", "毒素扶绥", "哭诉多幅", "决定法卡", "随地若","决定法卡", "随地若"};
		String[] type = new String[] { "Double", "Double_3_Scale","Double_5_Scale_KC", "String", "Double", "Date", "Time",
				"Double", "Double_3_Scale","Double_5_Scale_KC", "String", "Double", "Date", "Time",
				"Double", "Double_3_Scale","Double_5_Scale_KC", "String", "Double", "Date", "Time",
				"Double", "Double_3_Scale","Double_5_Scale_KC", "String", "Double", "Date", "Time",
				"Double", "Double_3_Scale","Double_5_Scale_KC", "String", "Double", "Date", "Time",
				"Double", "Double_3_Scale","Double_5_Scale_KC", "String", "Double", "Date", "Time",};
		String[] title = { "成交单", "2006-01-20", "2005-12-01 ~ 2006-01-02" };
		String[] tail = { "总计：", "12245434.777" };

		int[] order = { 0, 1, 2, 3, 4, 5, 6,
				 0, 1, 2, 3, 4, 5, 6,
				 0, 1, 2, 3, 4, 5, 6,
				 0, 1, 2, 3, 4, 5, 6,
				 0, 1, 2, 3, 4, 5, 6,
				 0, 1, 2, 3, 4, 5, 6};

		int index = title != null ? (title.length + 1) : 0;
		if (title != null) {
			for (int i = 0; i < title.length; i++) {
				maker.mergeCells(i, 0, i, head.length - 1);
				// maker.setRowView(i, 600);
				maker.addStringCell(title[i], i, 0, true,
						i == title.length - 1 ? Alignment.RIGHT
								: Alignment.CENTRE);
			}
		}
		DBResultDataSet data = new DBResultDataSet(head, type);
		String []v= null;
		for(int i=0;i<50000;i++){
		v = new String[] { "2.7194", "2.0307", "2333e5", "打发我发送的发生的发生的发送的发送的发送的","-2.44545", "2001-2-1", "14:23:1",
				"2.7194", "2.0307", "2333e5", "放弃我而且我而发生多次vxvasfafadsfadsfasdfrweqrqwerqwefasd","-2.44545", "2001-2-1", "14:23:1",
				"2.7194", "2.0307", "2333e5", "佛挡杀佛芙蓉区我让发的发送的人情味儿发的是发送的惹我日期发的发送的","-2.44545", "2001-2-1", "14:23:1",
				"2.7194", "2.0307", "2333e5", "发生的废弃物而成的发生的服务而范德萨发士大夫","-2.44545", "2001-2-1", "14:23:1",
				"2.7194", "2.0307", "2333e5", "发生的番茄网二期为辅的发生的发生的感情我热的发生的日期沃尔沃而非燃烧的发送的方式大法师的燃气网二期为发送的方式大法师的人情味","-2.44545", "2001-2-1", "14:23:1",
				"2.7194", "2.0307", "2333e5", "发生的废弃物而且玩儿请我二期为确认其沃尔法产生的发送的","-2.44545", "2001-2-1", "14:23:1"};
		data.addRow(v);
		}

//		maker.input(index, head, type, order, data);
		maker.input(index, head, order, data);
		index += data.getRowCount() + 1;
		if (tail != null && tail.length == 2) {
			maker.mergeCells(index, 1, index, head.length - 1);
			// maker.setRowView(i, 600);
			maker.addStringCell(tail[0], index, 0, true);
			maker.addDoubleCell(tail[1], index, 1, 2, true);
		}
		maker.write();
	}
	
	
	
	public static byte[] getExcelFileData(String[] head, int[] order, DBResultDataSet data)throws Exception {
		return getExcelFileData(head,order,null,data,null,null);
	}
	

	/**
	 * 允许用户指定字段类型
	 * xqh add on 2006-9-16
	 * 增加 String[] type 参数
	 */
	
	public static byte[] getExcelFileData(String[] head, int[] order, String[] title, 
			DBResultDataSet data, String[] tail,String[] type)throws Exception {
		
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		JXLMaker maker = new JXLMaker(byteOut);
		int index = title != null ? (title.length + 1) : 0;
		if (title != null) {
			for (int i = 0; i < title.length; i++) {
				maker.mergeCells(i, 0, i, head.length - 1);
				maker.addStringCell(title[i], i, 0, true,
						i == title.length - 1 ? Alignment.RIGHT
								: Alignment.CENTRE);
			}
		}
		maker.input(index, head, order, data,type);
		index += data.getRowCount() + 1;
		if (tail != null && tail.length == 2) {
			maker.mergeCells(index, 1, index, head.length - 1);
			maker.addStringCell(tail[0], index, 0, true);
			maker.addDoubleCell(tail[1], index, 1, 2, true);
		}
		maker.write();
		byteOut.flush();
		byteOut.close();
		byte[] buf = byteOut.toByteArray();
		return buf;
	}
	
	public static byte[] getExcelFileData(String[] head, int[] order, String[] title, String[] title2,
			DBResultDataSet data, DBResultDataSet data2,String[] type)throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		JXLMaker maker = new JXLMaker(byteOut);
		int index = title != null ? (title.length + 1) : 0;
		if (title != null) {
			for (int i = 0; i < title.length; i++) {
				maker.mergeCells(i, 0, i, head.length - 1);
				maker.addStringCell(title[i], i, 0, true,
						i == title.length - 1 ? Alignment.RIGHT
								: Alignment.CENTRE);
			}
		}
		maker.input(index, head, order, data,type);
		index += data.getRowCount() + 2;
		if (title2 != null && title2.length == 2 ) {
			for (int i = 0; i < title2.length; i++) {
				maker.mergeCells(index+i, 0, index+i, head.length - 1);
				maker.addStringCell(title2[i], index+i, 0, true,
						i == title2.length - 1 ? Alignment.RIGHT
								: Alignment.CENTRE);
			}
			maker.input(index+2, head, order, data2,type);
		}
		maker.write();
		byteOut.flush();
		byteOut.close();
		byte[] buf = byteOut.toByteArray();
		return buf;
	}

	public static byte[] getExcelFileHead(String filename, int data_len) {
		return (filename + ".xls," + data_len).getBytes();
	}

	public static JXLMaker createInstance() throws Exception {
		return new JXLMaker(new ByteArrayOutputStream());
	}

	public static byte[] getExcelFileData(JXLMaker maker) throws Exception {
		if (maker._outputStream == null
				|| maker._outputStream instanceof ByteArrayOutputStream == false) {
			throw new IllegalArgumentException(
					"maker 参数不合法, maker应该由createInstance()创建");
		}
		maker.write();
		maker._outputStream.flush();
		maker._outputStream.close();
		byte[] buf = ((ByteArrayOutputStream) maker._outputStream)
				.toByteArray();
		return buf;
	}

	public WritableSheet getSheet() {
		return _sheet;
	}

	public void setSheet(WritableSheet sheet) {
		this._sheet = sheet;
	}
	
	
}
